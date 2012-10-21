package oliver.test.lucene.index;

import java.io.IOException;

import junit.framework.Assert;
import oliver.test.lucene.TestUtil;

import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.Before;
import org.junit.Test;

/**
 * test lucene indexing
 * 
 * @author lichengwu
 * @version 1.0
 * @created 2012-10-21 下午8:11
 */
public class IndexingTest {

    private String[] ids = { "1", "2" };

    private String[] unindexed = { "Netherlands", "Italy" };

    private String[] unstored = { "Amsterdam has lots of bridges", "Venice has lots of canals" };

    private String[] text = { "Amsterdam", "Venice" };

    Directory directory;

    @Before
    public void setUp() throws IOException {
        directory = new RAMDirectory();

        IndexWriter writer = getWriter();

        for (int i = 0; i < ids.length; i++) {
            Document document = new Document();

            document.add(new Field("id", ids[i], Field.Store.YES, Field.Index.NOT_ANALYZED));
            document.add(new Field("country", unindexed[i], Field.Store.YES, Field.Index.NO));
            document.add(new Field("contents", unstored[i], Field.Store.NO, Field.Index.ANALYZED));
            document.add(new Field("city", text[i], Field.Store.YES, Field.Index.ANALYZED));

            writer.addDocument(document);

        }
        writer.close();

    }

    private IndexWriter getWriter() throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_34, new WhitespaceAnalyzer(
                Version.LUCENE_34));
        return new IndexWriter(directory, config);
    }

    private int getHitCount(String fieldName, String searchString) throws IOException {
        IndexSearcher searcher = new IndexSearcher(directory);
        Term term = new Term(fieldName, searchString);
        int hit = TestUtil.hitCount(searcher, new TermQuery(term));
        searcher.close();
        return hit;
    }

    @Test
    public void testIndexWriter() throws IOException {
        IndexWriter writer = getWriter();
        Assert.assertEquals(ids.length, writer.numDocs());
        writer.close();
    }

    @Test
    public void testIndexReader() throws IOException {
        IndexReader reader = IndexReader.open(directory);
        Assert.assertEquals(ids.length, reader.maxDoc());
        Assert.assertEquals(ids.length, reader.numDocs());
        reader.close();
    }

    @Test
    public void testDeleteBeforeOptimize() throws IOException {
        IndexWriter writer = getWriter();
        Assert.assertEquals(2, writer.numDocs());
        writer.deleteDocuments(new Term("id", "1"));
        writer.commit();
        Assert.assertTrue(writer.hasDeletions());
        Assert.assertEquals(2, writer.maxDoc());
        Assert.assertEquals(1, writer.numDocs());
        writer.close();
    }

    @Test
    public void testDeleteAfterOptimize() throws IOException {
        IndexWriter writer = getWriter();
        Assert.assertEquals(2, writer.numDocs());
        writer.deleteDocuments(new Term("id", "1"));
        writer.commit();
        writer.optimize();
        boolean condition = writer.hasDeletions();
        Assert.assertFalse(condition);
        Assert.assertEquals(1, writer.numDocs());
        Assert.assertEquals(1, writer.maxDoc());
        writer.close();

    }

    @Test
    public void testUpdate() throws IOException {
        Assert.assertEquals(1, getHitCount("city", "Amsterdam"));

        IndexWriter writer = getWriter();
        Document document = new Document();
        document.add(new Field("id", "1", Field.Store.YES, Field.Index.NOT_ANALYZED));
        document.add(new Field("country", "Netherlands", Field.Store.YES, Field.Index.NO));
        document.add(new Field("contents", "Den Haag has a lot of museums", Field.Store.NO,
                Field.Index.ANALYZED));
        document.add(new Field("city", "DenHaag", Field.Store.YES, Field.Index.ANALYZED));

        writer.updateDocument(new Term("id", "1"), document);
        writer.close();

        Assert.assertEquals(0, getHitCount("city", "Amsterdam"));
        Assert.assertEquals(1, getHitCount("city", "DenHaag"));

    }

}

package oliver.test.lucene;

import junit.framework.Assert;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.IOException;

/**
 * lunene近实时搜索测试
 * 
 * @version 1.0
 * @athor lichengwu
 * @created 2012-10-28 7:31 PM
 */
public class NearRealTimeSearchTest {

    @Test
    public void test() throws IOException {
        Directory dir = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_34, new SimpleAnalyzer(
                Version.LUCENE_34));
        IndexWriter writer = new IndexWriter(dir, config);
        for (int i = 0; i < 10; i++) {
            Document doc = new Document();
            doc.add(new Field("id", String.valueOf(i), Field.Store.YES,
                    Field.Index.NOT_ANALYZED_NO_NORMS));
            doc.add(new Field("content", "content", Field.Store.NO,
                    Field.Index.NOT_ANALYZED_NO_NORMS));
            writer.addDocument(doc);
        }

        IndexReader reader = IndexReader.open(writer, true);
        IndexSearcher searcher = new IndexSearcher(reader);
        Query query = new TermQuery(new Term("content", "content"));
        TopDocs docs = searcher.search(query, 100);
        Assert.assertEquals(10, docs.totalHits);

        writer.deleteDocuments(new Term("id", "5"));

        Document doc = new Document();
        doc.add(new Field("id", "20", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
        doc.add(new Field("content", "content2", Field.Store.NO, Field.Index.NOT_ANALYZED_NO_NORMS));
        writer.addDocument(doc);

        IndexReader newReader = reader.reopen();
        Assert.assertEquals(false, newReader == reader);
        reader.close();
        searcher = new IndexSearcher(newReader);

        docs = searcher.search(query, 100);
        Assert.assertEquals(9, docs.totalHits);

        query = new TermQuery(new Term("content", "content2"));

        docs = searcher.search(query, 100);
        Assert.assertEquals(1, docs.totalHits);

        writer.close();
        reader.close();

    }
}

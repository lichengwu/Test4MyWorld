package oliver.test.lucene.index;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;

/**
 * Indexer
 * User: lichengwu
 * Date: 9/27/12
 * Time: 9:42 PM
 */
public class Indexer {

    private IndexWriter writer;

    /**
     * constrator
     *
     * @param indexDir directory to index
     * @throws IOException
     */
    public Indexer(String indexDir) throws IOException {
        Directory dir = FSDirectory.open(new File(indexDir));
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_34, new StandardAnalyzer(Version.LUCENE_34));
        writer = new IndexWriter(dir, config);
    }

    /**
     * colse ${@link IndexWriter}
     *
     * @throws IOException
     */
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }

    /**
     * index file
     *
     * @param dataDir index data dir
     * @return
     * @throws IOException
     */
    public int index(String dataDir) throws IOException {
        File[] files = new File(dataDir).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                indexFile(file);
            } else {
                index(file.getCanonicalPath());
            }
        }
        return writer.numDocs();
    }

    /**
     * inde file
     *
     * @param file
     * @throws IOException
     */
    private void indexFile(File file) throws IOException {
        System.out.println("Indexing " + file.getCanonicalPath());
        Document doc = getDocument(file);
        writer.addDocument(doc);
    }

    /**
     * get ${@link Document} from file
     *
     * @param file
     * @return
     * @throws IOException
     */
    protected Document getDocument(File file) throws IOException {
        Document doc = new Document();
        doc.add(new Field("contents", new FileReader(file)));
        doc.add(new Field("filename", file.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field("fullpath", file.getCanonicalPath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        return doc;
    }

}

package cn.lichengwu.test.lucene.index;

import org.apache.log4j.Logger;
import org.apache.lucene.queryParser.ParseException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * test ${@link Indexer} and ${@link Searcher}
 * User: lichengwu
 * Date: 9/27/12
 * Time: 10:18 PM
 */
public class TestFile {

    Logger log = Logger.getLogger(TestFile.class);

    private static final String INDEXER_DIR = "E:\\workspace\\projects\\openjdk";

    private static final String DATA_DIR = "E:\\temp\\lucene\\index";

    @Test
    public void testIndexer() throws IOException {

        File dataFile = new File(DATA_DIR);
        dataFile.mkdirs();
        for (File file : dataFile.listFiles()) {
            if (file.isFile()) {
                file.delete();
            }
        }
        long start = System.currentTimeMillis();
        Indexer indexer = null;
        int numIndex = 0;
        try {
            indexer = new Indexer(DATA_DIR);
            numIndex = indexer.index(INDEXER_DIR);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            indexer.close();
        }

        log.info("Indexing " + numIndex + " files took " + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    public void testSearcher() throws IOException, ParseException {
         Searcher.search(DATA_DIR,"ClipboardOwner");
    }
}

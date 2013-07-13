package cn.lichengwu.test.lucene;

import java.io.IOException;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

/**
 * lucene test util
 * 
 * @author lichengwu
 * @version 1.0
 * @created 2012-10-21 下午8:07
 */
public class TestUtil {

    /**
     * get hit count
     * 
     * @param searcher
     * @param query
     * @return
     * @throws IOException
     */
    public static int hitCount(IndexSearcher searcher, Query query) throws IOException {
        return searcher.search(query, 1).totalHits;
    }
}

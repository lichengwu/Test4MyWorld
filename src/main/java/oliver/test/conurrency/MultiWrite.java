package oliver.test.conurrency;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lichengwu
 * @version 1.0
 * @created 2013-01-08 12:11 AM
 */
public class MultiWrite {

    private static final int SIZE = 1024 * 1024 * 1024;

    ExecutorService exec = Executors.newFixedThreadPool(5);

    public void start() {
        final File source = new File("");
        long size = source.length();
        final File store = new File("");

        for (long position = 0; position < size; position = position + SIZE) {
            exec.execute(new WriteTask(source, store, position));
        }

    }

    public class WriteTask implements Runnable {

        private final File store;

        private final File source;

        private final long position;

        public WriteTask(File source, File store, long position) {
            this.store = store;
            this.position = position;
            this.source = source;
        }

        public void run() {
            try {

                RandomAccessFile in = new RandomAccessFile(source, "r");

                // lock part of store
                RandomAccessFile out = new RandomAccessFile(store, "rw");
                FileChannel channel = out.getChannel();
                FileLock lock;
                while (true) {
                    try {
                        lock = channel.tryLock(position, SIZE, false);
                        break;
                    } catch (Exception e) {
                        // deal with
                    }

                }

                out.seek(position);
                in.seek(position);
                byte[] data = new byte[SIZE];
                in.read(data);
                out.write(data);
                // release
                lock.release();
                channel.close();
                out.close();
                in.close();
            } catch (IOException e) {
                // deal with
            }
        }
    }
}

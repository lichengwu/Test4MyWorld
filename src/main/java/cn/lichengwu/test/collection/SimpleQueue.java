package cn.lichengwu.test.collection;

/**
 * 简单的队列(数组实现)
 *
 * @author 佐井
 * @version 1.0
 * @created 2013-10-26 11:27 PM
 */
public class SimpleQueue<T> {

    private Object[] arr;
    private int size;
    private int font;
    private int end;

    public SimpleQueue(int size) {
        arr = new Object[size];
    }

    public T deQueue() {
        if (size <= 0) {
            throw new NullPointerException();
        }
        ensure();
        Object val = arr[font];
        font--;
        if (font < 0) {
            font += arr.length;
        }
        size--;
        return (T) val;
    }

    public int size() {
        return size;
    }

    public void enQueue(T data) {
        ensure();
        arr[end] = data;
        size++;
        --end;
        if (end < 0) {
            end += arr.length;
        }

    }

    private void ensure() {
        if (size == arr.length) {
            Object[] newArr = new Object[size * 2];
            int begin = size / 2 + size;
            for (int i = 0; i < size; i++) {
                newArr[begin] = arr[font];
                --font;
                if (font < 0) {
                    font += arr.length;
                }
                begin--;
            }
            arr = newArr;
            end = size / 2;
            font = size / 2 + size;
        }
    }

}

package cn.lichengwu.test.conurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * thread safe queue
 *
 * @author lichengwu
 * @version 1.0
 * @created 2013-01-29 11:29 PM
 */
public class LinkedQueue<E> {

    private final Node<E> dummy = new Node<E>(null, null);

    private final AtomicReference<Node> tail = new AtomicReference<Node>(dummy);

    private final AtomicReference<Node> head = new AtomicReference<Node>(dummy);

    public boolean offer(E o) {
        Node<E> newNode = new Node<>(o, null);

        while (true) {
            Node<E> currentTail = tail.get();
            Node<E> nextTail = currentTail.next.get();

            if (currentTail == tail.get()) {
                //intermediate state
                if (nextTail != null) {
                    tail.compareAndSet(currentTail, nextTail);
                } else {
                    // normal state
                    if (currentTail.next.compareAndSet(nextTail, newNode)) {
                        tail.compareAndSet(currentTail, newNode);
                        //System.out.println("offer : " + o);
                        return true;
                    }
                }
            }
        }
    }

    public synchronized Set<E> toSet() {
        Set<E> set = new HashSet<>();

        Node<E> current = dummy.next.get();
        E e;

        while (current != null && (e = current.item) != null) {
            set.add(e);
            current = current.next.get();
        }

        return set;
    }


    public E poll() {


        Node<E> top = dummy.next.get();

        if (top == null) {
            return null;
        }

        Node<E> next = top.next.get();

        while (true) {
            if (dummy.next.get() == top) {
                if (dummy.next.compareAndSet(top, next)) {
                    top.next.compareAndSet(next, null);
                    return top.item;
                } else {
                    top.next.compareAndSet(next, null);
                    return top.item;
                }
            }
        }
    }


    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }

    }
}

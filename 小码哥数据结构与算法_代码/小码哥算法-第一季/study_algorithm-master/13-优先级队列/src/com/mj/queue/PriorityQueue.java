package com.mj.queue;

import com.mj.heap.BinaryHeap;

import java.util.Comparator;

public class PriorityQueue<E> {

    private BinaryHeap<E> heap;

    public PriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public PriorityQueue() {
        heap = new BinaryHeap<>();
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void enQueue(E element) {
        heap.add(element);
    }

    public E deQueue() {
        return heap.remove();
    }

    public E front() {
        return heap.get();
    }

    public void clear() {
        heap.clear();
    }
}

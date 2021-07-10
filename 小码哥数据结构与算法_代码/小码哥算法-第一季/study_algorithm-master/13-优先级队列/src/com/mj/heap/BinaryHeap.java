package com.mj.heap;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class BinaryHeap<E> extends AbstractHeap<E> {

    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap() {
        this(null, null);
    }

    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            size = elements.length;
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }

    public BinaryHeap(E[] elements) {
        this(elements, null);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);

        elements[size++] = element;
        siftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();
        E root = elements[0];
        int lastIndex = --size;
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        siftDown(0);

        return root;
    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    // 批量建堆
    private void heapify() {
        // 自上而下的上滤,O(n*logn)
        for (int i = 1; i < size; i++) {
            siftUp(i);
        }

        // 自下而上的下滤，时间复杂度为O(n)
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

    }

    // 让index位置的元素上滤
    private void siftUp(int index) {
        // 3*O(logn)
//        E e = elements[index];
//        while (index > 0) {
//            int pIndex = (index - 1) >> 1;
//            E p = elements[pIndex];
//            if (compare(e, p) <= 0) return;
//
//            // 交换index，pIndex位置的内容
//            E tmp = elements[index];
//            elements[index] = elements[pIndex];
//            elements[pIndex] = tmp;
//            // 重新赋值index
//            index = pIndex;
//        }

        // O(logn) + 1
        E element = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(element, parent) <= 0) break;

            // 将父元素存储在index位置
            elements[index] = elements[parentIndex];
            // 重新赋值index
            index = parentIndex;
        }
        elements[index] = element;
    }

    // 下滤
    private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1;
        // 第一个叶子节点的索引 == 非叶子节点的数量
        while (index < half) { // 必须保证index位置的节点是非叶子节点(叶子节点无需也无法下滤)
            // index的节点有两种情况
            // 1.只有左子节点
            // 2.同时有左右子节点

            // 默认为左子节点,跟左子节点进行比较
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;
            // 选出左右子节点中最大的那个
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }

            if (compare(element, child) >= 0) break;

            // 将子节点存放到index位置
            elements[index] = child;
            index = childIndex;

        }
        elements[index] = element;
    }



    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        // 如果现有容量可以满足要求，则直接返回
        if(oldCapacity >= capacity) return;

        // 如果现有容量不可以满足，则重新创建一个新数组

        // 新容量为旧容量的1.5倍(java的推荐？ios一般推荐1.6)
        // >>为位移操作(二进制操作),效率很高。右移1位，相当于除以2。为什么，可以自己想想看。
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];

        for(int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}

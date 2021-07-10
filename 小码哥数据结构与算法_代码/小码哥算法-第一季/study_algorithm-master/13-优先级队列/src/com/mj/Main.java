package com.mj;

import com.mj.queue.PriorityQueue;

public class Main {

    public static void main(String args[]) {
        PriorityQueue<Person> queue = new PriorityQueue<>();
        queue.enQueue(new Person("kasumi", 10));
        queue.enQueue(new Person("honoka", 2));
        queue.enQueue(new Person("kokoro", 5));
        queue.enQueue(new Person("marie", 3));

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }


    }
}

package com.lesson.structure.linked;

/**
 * @author zhengshijun
 * @version created on 2018/9/26.
 */
public class IntLinkedList {

    private transient Node first;

    private transient Node last;

    class Node {
        int value;
        LinkedList.Node pre;
        LinkedList.Node next;
        private Node(int value) {
            this.value = value;
        }
    }
}

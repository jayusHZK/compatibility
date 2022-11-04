package com.jayus.leetCode;

import java.util.LinkedList;

/**
 * @author : h zk
 * @date : 2022/7/1 18:22
 * @description : 逆序相加两个链表
 **/
public class Two2 {
    public static void main(String[] args) {
        Node node = new Node(7);
        Node node1 = new Node(8);
        Node node2 = new Node(9);
        node1.setNext(node2);
        node.setNext(node1);

        Node node4 = new Node(3);
        Node node5 = new Node(2);
        Node node6 = new Node(1);
        node5.setNext(node6);
        node4.setNext(node5);

        System.out.println(addTwoNodeNumbers(node,node4));
    }

    public static Node addTwoNodeNumbers(Node node1, Node node2) {
        Node pre = new Node();
        Node cur = pre;
        int carry = 0;
        while (node1 != null || node2 != null) {
            int x = node1 == null ? 0 : node1.num;
            int y = node2 == null ? 0 : node2.num;
            // 考虑满十进一
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            // 叠加node
            cur.next = new Node(sum);
            cur = cur.next;
            if (node1 != null) node1 = node1.next;
            if (node2 != null) node2 = node2.next;
        }
        if (carry == 1) {
            cur.next = new Node(carry);
        }
        return pre.next;
    }

    //链表类
    public static class Node {
        Integer num;
        Node next;

        public Node() {
        }

        public Node(Integer num) {
            this.num = num;
        }

        public boolean hasNext() {
            return next == null;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", next=" + next +
                    '}';
        }
    }
}

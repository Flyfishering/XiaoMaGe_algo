package com.mj;

public class Hanoi {
    /**
     * 将n个盘子从p1挪动到p3
     * @param p2 中间的柱子
     */
    void hanoi(int n, String p1, String p2, String p3) {
        if (n == 1) {
            move(n, p1, p3);
            return;
        }
        // 先将n-1个盘子从p1挪动到p2
        hanoi(n-1, p1, p3, p2);
        // 将第n个盘子挪动到p3
        move(n, p1, p3);
        // 将n-1个盘子从p2挪动到p3
        hanoi(n-1, p2, p1, p3);
    }

    void move(int no, String from, String to) {
        System.out.println("将" + no + "号盘子从" + from + "移动到" + to);
    }

    public static void main(String args[]) {
        new Hanoi().hanoi(5, "A", "B", "C");
    }
}

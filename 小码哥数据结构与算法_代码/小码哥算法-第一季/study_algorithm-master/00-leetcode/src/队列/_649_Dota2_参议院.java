package 队列;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/dota2-senate/
 */
public class _649_Dota2_参议院 {
    // 队列实现
    public String predictPartyVictory(String senate) {
        Queue<Integer> queueRadiant = new LinkedList<>();
        Queue<Integer> queueDire = new LinkedList<>();
        int len = senate.length();

        for (int i = 0; i < len; i++) {
            char c = senate.charAt(i);
            if (c == 'R') {
                queueRadiant.offer(i);
            } else {
                queueDire.offer(i);
            }
        }
        while (!queueRadiant.isEmpty() && !queueDire.isEmpty()) {
            int r = queueRadiant.poll();
            int d = queueDire.poll();
            if (r > d) {
                queueDire.offer(d + len);
            } else {
                queueRadiant.offer(r + len);
            }
        }
        if (queueRadiant.isEmpty()) {
            return "Dire";
        } else {
            return "Radiant";
        }
    }

    // https://leetcode-cn.com/problems/dota2-senate/solution/java-649dota2can-yi-yuan-chao-9961-by-mu-0dqh/
    public String predictPartyVictory2(String senate) {

        int countR = 0;
        int countD = 0;
        int curBanR = 0;
        int curBanD = 0;
        int totalBanR = 0;
        int totalBanD = 0;

        // 第一次循环时用于记录R与D的总数
        boolean firstFlg = true;

        char[] cArray = senate.toCharArray();
        while (true) {
            for (int i = 0; i < cArray.length; i++) {
                char c = cArray[i];
                if (c == 'R') {
                    if (firstFlg) {
                        countR++;
                    }
                    if (curBanR > 0) {
                        curBanR--;
                        cArray[i] = 'r';
                    } else {
                        curBanD++;
                        totalBanD++;
                        if (!firstFlg && totalBanD >=countD) {
                            return "Radiant";
                        }
                    }
                } else if (c == 'D') {
                    if (firstFlg) {
                        countD++;
                    }
                    if (curBanD > 0) {
                        curBanD--;
                        cArray[i] = 'd';
                    } else {
                        curBanR++;
                        totalBanR++;
                        if (!firstFlg && totalBanR >=countR) {
                            return "Dire";
                        }
                    }
                }
            }
            firstFlg = false;
        }
    }
}

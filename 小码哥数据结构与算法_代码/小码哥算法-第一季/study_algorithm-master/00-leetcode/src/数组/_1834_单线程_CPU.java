package 数组;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/contest/weekly-contest-237/problems/single-threaded-cpu/
 * TODO
 */
public class _1834_单线程_CPU {
    class Task {
        int idx;
        int et;
        int pt;

        public Task(int idx, int et, int pt) {
            this.idx = idx;
            this.et = et;
            this.pt = pt;
        }
    }
    public int[] getOrder(int[][] tasks) {
        PriorityQueue<Task> queue = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.et != o2.et) {
                    return o1.et - o2.et;
                } else if (o1.pt != o2.pt) {
                    return o1.pt - o2.pt;
                } else {
                    return o1.idx - o2.idx;
                }
            }
        });
        Task[] taskInfos = new Task[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            taskInfos[i] = new Task(i, tasks[i][0], tasks[i][1]);
            queue.offer(taskInfos[i]);
        }

        int[] ans = new int[tasks.length];
        int idx = 0;
        while (!queue.isEmpty()) {
            ans[idx++] = queue.remove().idx;
        }
        return ans;
    }
}

package 图;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/course-schedule-ii/
 */
public class _210_课程表_II {
    // 本质是拓扑排序
    // https://leetcode-cn.com/problems/course-schedule-ii/solution/java-jian-dan-hao-li-jie-de-tuo-bu-pai-xu-by-kelly/
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] resArray = new int[numCourses];
        int index = 0;
        int[] inDegrees = new int[numCourses]; // 每个元素的入度
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < prerequisites.length; i++) {
            inDegrees[prerequisites[i][0]]++;
        }

        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            resArray[index++] = course;
            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == course) {
                    inDegrees[prerequisites[i][0]]--;
                    if (inDegrees[prerequisites[i][0]] == 0) {
                        queue.offer(prerequisites[i][0]);
                    }
                }
            }
        }

        if (index == numCourses) return resArray;

        return new int[0];
    }

    // 对1的优化。主要是找了一个map来存储一门课程的所有后继课程
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        int[] resArray = new int[numCourses];
        int index = 0;
        int[] inDegrees = new int[numCourses]; // 每个元素的入度
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, List<Integer>> predecessor = new HashMap<>(); // key为前驱，value为前驱对应的所有后继
        for (int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]]++;
            List<Integer> list = predecessor.getOrDefault(prerequisite[1], new ArrayList<>());
            list.add(prerequisite[0]);
            predecessor.put(prerequisite[1], list);
        }

        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            resArray[index++] = course;
            if (predecessor.containsKey(course)) {
                for (Integer successor : predecessor.get(course)) {
                    if (--inDegrees[successor] == 0) {
                        queue.offer(successor);
                    }
                }
            }
        }

        if (index == numCourses) return resArray;

        return new int[0];
    }
}

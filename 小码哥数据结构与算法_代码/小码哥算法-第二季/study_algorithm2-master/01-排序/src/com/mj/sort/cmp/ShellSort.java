package com.mj.sort.cmp;

import com.mj.sort.Sort;

import java.util.ArrayList;
import java.util.List;

public class ShellSort<E extends Comparable<E>> extends Sort<E> {

    @Override
    protected void sort() {
        // 步长序列
        List<Integer> stepSequence = shellStepSequence();
        for (Integer step : stepSequence) {
            sort(step);
        }
    }

    // 分成step列进行排序
    private void sort(int step) {
        // col:第几列。column的简称
        for (int col = 0; col < step; col++) {
            for (int begin = col + step; begin < array.length; begin += step) {
                int cur = begin;
                E v = array[cur];
                while (cur > col && cmp(v, array[cur-step]) < 0) { // 注意这个<，若变成<=，就是不稳定的排序了
                    array[cur] = array[cur = cur - step];  // 由每次都交换优化为了挪动
                }
                array[cur] = v;
            }
        }
    }

    private List<Integer> shellStepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int step = array.length;

        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }

        return stepSequence;
    }
}

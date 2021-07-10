package 数组排序_01;

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/sort-colors
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 注意临界值的判断

public class _02_75_颜色分类 {
    static public void sortColors(int[] nums) {
        int left = 0;
        int cur = 0;
        int right = nums.length - 1;

        while (cur <= right) {
            if (nums[cur] == 0) {
               swap(nums,cur++,left++);
            } else if(nums[cur] == 1) {
                cur++;
            } else {
                swap(nums, cur, right--);
            }
        }
    }

    static public void swap(int[] nums,int i,int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        sortColors(new int[]{2,0,2,1,1,0});
    }
}

package 数组;

/**
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 */
public class _74_搜索二维矩阵 {
    // 一次二分(可以继续优化，行，列都使用二分搜索TODO)
    public boolean searchMatrix(int[][] matrix, int target) {
        int begin = 0;
        int end = matrix.length;
        // 搜索每行第一个元素，找到最后一个小于target的行
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (target < matrix[mid][0]) {
                end = mid;
            } else if (target > matrix[mid][0]){
                begin = mid + 1;
            } else {
                return true;
            }
        }
        // 上面的二分搜索找到的是第一个大于target的位置。所以要减1
        begin = begin - 1;
        if (begin < 0) return false;

        return binarySearchCol(matrix[begin], target);
    }

    private boolean binarySearchCol(int[] nums, int target) {
        int begin = 0;
        int end = nums.length;

        while (begin < end){
            int mid = (begin + end) >> 1;
            if (nums[mid] > target) {
                end = mid;
            } else if (nums[mid] < target) {
                begin = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{2,3,5,7},{10,11,16,20},{23,30,34,60}};
        new _74_搜索二维矩阵().searchMatrix(matrix, 1);
    }

}

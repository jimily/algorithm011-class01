//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。 
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法 
// 👍 120 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*class Solution {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        int count = 0;
        if (left >= right) {
            return count;
        }
        int mid = (left + right) >> 1;
        count += mergeSort(nums, left, mid);
        count += mergeSort(nums, mid + 1, right);
        count += merge(nums, left, right, mid);
        return count;
    }
    private int merge(int[] nums, int left, int right, int mid) {
        int[] temp = new int[right - left + 1];
        int count = 0;
        int i1 = left, i2 = left;
        int j = mid + 1, k = 0;
        while (j <= right) {
            while (i1 <= mid && nums[i1] / 2.0 <= nums[j]) i1 ++;
            count += mid - i1 + 1;
            while (i2 <= mid && nums[i2] < nums[j]) temp[k ++] = nums[i2 ++];
            temp[k ++] = nums[j ++];
        }
        while (i2 <= mid) temp[k ++] = nums[i2 ++];
        System.arraycopy(temp, 0, nums, left, temp.length);
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{2,4,3,5,1};
        System.out.println(solution.reversePairs(nums));
        for (int num : nums) {
            System.out.print(num + ",");
        }
    }
}*/
//leetcode submit region end(Prohibit modification and deletion)

//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 
//
// 说明: 
//
// 
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。 
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。 
// 
//
// 
//
// 示例: 
//
// 输入:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6] 
// Related Topics 数组 双指针


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        for (int index1 = m - 1, index2 = n - 1, target = m + n - 1; target >= 0; target --) {
            if (index2 < 0 || (index1 >= 0 && nums1[index1] > nums2[index2])) {
                nums1[target] = nums1[index1 --];
                continue;
            }
            nums1[target] = nums2[index2 --];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

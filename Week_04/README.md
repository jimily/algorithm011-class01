学习笔记

### LeetCode-153 寻找旋转排序数组中的最小值

#### 题意
- 一个升序排序的数组，在预先未知的某个点上进行了旋转。
- 例如：数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2]
- 请找出其中最小的元素。
- 你可以假设数组中不存在重复元素。

#### 前提
从题意可知：
1. 原数组是一个有上下界的单调递增的数组
2. 不一定是连续的

二分查找的前提：
1. 目标具有单调性：单调递增或单调递减
2. 存在上下界
3. 能够通过索引访问

#### 思路
首先，题目满足二分查找的前提规则，模拟推算一下是否可以用二分查找完成，称发生旋转的点为“旋转点”， 对应索引为x。以题目中的示例解读：数组 [4,5,6,7,0,1,2]：
 1. nums[x]为0
 2. 如果旋转点存在的话，以旋转点截断分为左右数组，满足以下规则：
    - 左数组为单调递增，即nums[0] < nums[x - 1]
    - 右数组为单调递增，即nums[x] < nums[nums.length - 1]
 3. 如果数组中不存在旋转点，则nums[left] < nums[right]
 4. 如果数组中存在旋转点，则nums[left] > nums[right]

#### 代码
直接套用二分查找模板
```java
public int findMin(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
        int mid = (right + left) / 2;
        if (nums[mid] < nums[right]) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return nums[left];
}
```
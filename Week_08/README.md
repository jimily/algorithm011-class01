学习笔记

### 归并排序
> 利用分治思想，先拆解成子问题，将每个子问题进行排序合并。
```java
private void mergeSort(int[] nums, int begin, int end) {
    if (begin >= end) {
        return ;
    }
    int mid = (begin + end) >> 1;
    mergeSort(nums, begin, mid);
    mergeSort(nums, mid + 1, end);
    merge(nums, begin, end, mid);
}

private void merge(int[] nums, int left, int right, int mid) {
    int[] temp = new int[right - left + 1];
    int i = left, j = mid + 1, k = 0;
    while(i <= mid && j <= right) {
        temp[k ++] = nums[i] <= nums[j] ? nums[i ++] : nums[j ++];
    }
    while(i <= mid) {
        temp[k ++] = nums[i ++];
    }
    while(j <= right) {
        temp[k ++] = nums[j ++];
    }
    System.arraycopy(temp, 0, nums, left, temp.length);
}
```
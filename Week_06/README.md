学习笔记

### 动态规划
#### 概念
本质上是动态递推，将一个复杂问题拆解成多个子问题进行分治解决，而每个子问题存在最优子结构，且每个子问题都取最优的状态，能够推导出一个全局最优的值。

#### 关键点
动态规划、递归、分治没有根本上的区别。
共性：都需要找到重复子问题
差异性：动态规划存在最优子结构，过程中可以淘汰次优解。

#### 解题思路
自顶向下：分治 + 记忆化搜索
自底向上：迭代法

#### 解题步骤
1、最优子结构
2、存储中间状态
3、递推公式
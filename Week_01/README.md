学习笔记

#### 第一题：用 add first 或 add last 这套新的 API 改写 Deque 的代码

原题：
```java
public static void main(String[] args)  {
    Deque<String> deque = new LinkedList<>();

    deque.push("a");
    deque.push("b");
    deque.push("c");
    System.out.println(deque);

    String str = deque.peek();
    System.out.println(str);
    System.out.println(deque);

    while (deque.size() > 0) {
        System.out.println(deque.pop());
    }
    System.out.println(deque);
}
```
用 add first 或 add last 改写
```java
public static void main(String[] args)  {

    Deque<String> deque = new LinkedList<>();

    deque.addFirst("a");
    deque.addFirst("b");
    deque.addFirst("c");
    System.out.println(deque);

    String str = deque.peek();
    System.out.println(str);
    System.out.println(deque);

    while(deque.size() > 0) {
        System.out.println(deque.pop());
    }
    System.out.println(deque);
}
```

#### 第二题：分析 Queue 和 Priority Queue 的源码
##### Queue

###### 定义
先进先出的结构，一般用数组或者链表实现（例如：ArrayBlockingQueue是用数组实现的队列，LinkedBlockingQueue是用链表实现的队列），只允许在队列尾部添加元素，只允许在队列头部取出元素。

###### 基础操作
Queue在JAVA中是以接口形式定义，定义了队列的一些基础操作。

* boolean add(E e) <br/>
    >往队列里添加一个元素，如果容量足够则添加成功，返回true；若容量不足则抛出IllegalStateException 异常。
 
* boolean offer(E e) <br/>
    >往队列里添加一个元素，如果容量足够则添加成功，返回true；若容量不足则添加失败，返回false（**不会抛异常**）。
    
* E remove() <br/>
    >将队列头部的元素移除并返回，如果队列为空则抛出NoSuchElementException异常。
    
* E poll() <br/>
    >将队列头部的元素移除并返回，如果队列为空则返回null（**不会抛出异常**）。
    
* E element() <br/>
    >查看队列头部的元素，不会操作移除，如果队列为空则抛出NoSuchElementException异常。

* E peek() <br/>
    >查看队列头部的元素，不会操作移除，如果队列为空则返回null（**不会抛出异常**）。


##### PriorityQueue

###### 定义
一个基于优先级堆的、无界的优先级队列。按数据类型的自然排序或指定的比较器进行排序。队列的头元素是遵循比较器比较结果的最小元素。<br/>
PriorityQueue是基于数组实现，默认初始化大小为11，达到扩容条件时，会进行扩容操作。

###### 构造函数
提供了可以指定队列初始化大小、及比较器的相关构造函数。
以及提供可基于指定的Collection、PriorityQueue、SortedSet构造一个新的PriorityQueue对象的构造函数。

###### 队列的基础操作

* boolean add(E e) <br/>
* boolean offer(E e) <br/>
    >由于PriorityQueue是理论上无界的队列，所以容量不足时会进行扩容，不会抛出IllegalStateException 异常。所以在PriorityQueue中，add()和offer()是一样的逻辑。<br/>
    <br/>
    PriorityQueue将完全二叉树的结构用数组存储，通过数组下标运算寻找父节点，保持比较后的较小的值是在父节点的位置，即父节点永远比子节点的值小。所以是小顶堆的算法。
    <br/><br/>
    插入一个元素时，先校验队列容量，不足则扩容。通过小顶堆的算法，重新确认新节点与溯源链路上的所有父节点的位置，更新后则完成插入操作。
* boolean remove(Object o) <br/>
    >此函数用于删除值于o匹配的元素（如果有并列的话，只删坐标小的那个），删除成功返回true，删除失败（找不到匹配的元素）返回false。
    <br/><br/>
    先通过遍历数组，找到匹配o的元素所在数组下标。<br/>
    如果待删除的元素是数组的最后一个元素，则直接将其置为null；<br/>
    如果待删除的元素非尾元素，先将指定位置置为null，然后调整小顶堆与此位置相关联的节点。
* E poll() <br/>
    >此函数用于移除队列头部的元素，即下标为0的位置，如果该位置为空，则直接返回null。
    <br/><br/>
    把数组最后一个位置置为null，将原本在数组的尾元素，作为新元素，指定插入下标为0的位置，调用小顶堆的调整算法，调整相关节点的位置即可。
* E element() <br/>
    >PriorityQueue没有重写该方法，继承的是AbstractQueue的方法。即如果peek()的返回值不为null，则直接返回；若peek()的返回值为null，则抛出NoSuchElementException异常。
* E peek() <br/>
   >直接返回数组下标为0的元素。
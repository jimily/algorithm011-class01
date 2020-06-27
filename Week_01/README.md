学习笔记

##### 第一题：用 add first 或 add last 这套新的 API 改写 Deque 的代码

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

##### Queue

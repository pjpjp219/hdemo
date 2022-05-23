package com.nixx.jdk.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author nixx
 */
public class CreateStream {
    public static void main(String[] args) {
        //1）使用数组创建一个流
        Integer[] ints = {1, 2, 3, 4, 5};
        Stream<Integer> stream = Arrays.stream(ints);

        //2）使用集合创建一个流
        List<Integer> aList = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream1 = aList.stream();

        //3）创建一个并行流
        List<Integer> aList1 = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream2 = aList1.parallelStream();
        //或者
        Stream<Integer> stream3 = aList1.stream().parallel();

        //使用Stream 里面的静态方法创建一个整数流，里面有 1，2，3，4，5 五个整数
        Stream<Integer> stream4 = Stream.iterate(1, (x) -> x + 1).limit(5);
        Stream<Integer> stream5 = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> stream6 = Stream.generate(() -> 1).limit(3);
    }
}

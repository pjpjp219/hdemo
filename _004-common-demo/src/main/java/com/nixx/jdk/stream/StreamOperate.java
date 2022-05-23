package com.nixx.jdk.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author nixx
 */
public class StreamOperate {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Student {
        String name;
        Integer age;
        String address;
    }

    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student("张三", 18, "长沙"),
                new Student("李四", 38, "北京"),
                new Student("王五", 60, "上海"),
                new Student("陈六", 38, "深圳"),
                new Student("陈六", 38, "深圳")
        );

        /* ============= Stream 的筛选与切片操作 ===============*/
        //过滤出年龄大于18岁的数据
        list.stream().filter(e -> e.getAge() >= 18).forEach(System.out::println);
        System.out.println();

        //过滤出年龄大于18岁的数据，再去掉重复的数据
        list.stream().filter(e -> e.getAge() >= 18).distinct().forEach(System.out::println);
        System.out.println();

        // 截取前面两条数据，也就是张三和李四
        list.stream().limit(2).forEach(System.out::println);
        System.out.println();

        // 跳过前面 n 条数据，跟 limit 的作用正好相反
        list.stream().skip(2).forEach(System.out::println);
        System.out.println();
        /* ============= Stream 的筛选与切片操作 ===============*/

        /* ============= Stream 的映射操作 ================ */
        list.stream().map(e -> e.getName()).forEach(System.out::println);
        System.out.println();

        list.stream().mapToDouble(e -> e.getAge()).forEach(System.out::println);
        System.out.println();

        String str = "i love u";
        Stream.of(str.split(" "))
                .flatMap(e -> e.chars().boxed())
                .forEach(i -> System.out.println((char)i.intValue()));
        System.out.println();
        /* ============= Stream 的映射操作 ================ */


        /* ============= Stream 的排序操作 ================= */
        // 使用年龄排序
        list.stream().sorted(Comparator.comparing(Student::getAge).thenComparing(Student::getName)).forEach(System.out::println);
        System.out.println();
        /* ============= Stream 的排序操作 ================= */

        // 将处理后的流生成一个新的集合
        List<String> collect = list.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println();

        // 基于 reduce() 得到所有人的年龄总和
        Integer reduce = list.stream().map(Student::getAge).reduce(0, (a1, a2) -> a1 + a2);
        System.out.println(reduce);
        System.out.println();

        // 基于max() / min() 获取年龄最大和最小的信息
        Optional<Student> max = list.stream().max(Comparator.comparingInt(Student::getAge));
        System.out.println(max.get());
        System.out.println();

        // 使用 toArray() 方法将元素转变为一个数组
        Object[] objects = list.stream().toArray();
        System.out.println(objects[2]);
        System.out.println();

        // findFirst 和 findAny 方法
        Optional<Student> first = list.stream().findFirst();
        Optional<Student> any = list.stream().findAny();
        System.out.println(any.get());
        System.out.println();
        System.out.println(first.get());
        System.out.println();

        boolean b1 = list.stream().anyMatch(e -> e.getAge() == 18);
        System.out.println(b1);
        /* ============= Stream 的终止操作 =================*/
    }

}

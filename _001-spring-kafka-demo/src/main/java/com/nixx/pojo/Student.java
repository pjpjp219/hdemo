package com.nixx.pojo;

/**
 * @author nixx
 */
public class Student {

    Long id;

    String name;

    String birtyDay;

    Integer age;

    public Student() {
    }

    public Student(Long id, String name, String birtyDay, Integer age) {
        this.id = id;
        this.name = name;
        this.birtyDay = birtyDay;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirtyDay() {
        return birtyDay;
    }

    public void setBirtyDay(String birtyDay) {
        this.birtyDay = birtyDay;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

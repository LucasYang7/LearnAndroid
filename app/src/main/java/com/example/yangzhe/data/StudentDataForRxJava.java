package com.example.yangzhe.data;

import java.util.List;

/**
 * Created by yangzhe on 16-7-10.
 */
public class StudentDataForRxJava {
    private String name;
    private int age;
    private List<Course> courses;

    public StudentDataForRxJava(){

    }

    public StudentDataForRxJava(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setCourses(List<Course> courses){
        this.courses = courses;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public List<Course> getCourses(){
        return courses;
    }

    // 移到StudentDataForRxJava的外部
    public class Course {
        private String courseName;

        public Course(String courseName) {
            this.courseName = courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseName() {
            return courseName;
        }
    }
}

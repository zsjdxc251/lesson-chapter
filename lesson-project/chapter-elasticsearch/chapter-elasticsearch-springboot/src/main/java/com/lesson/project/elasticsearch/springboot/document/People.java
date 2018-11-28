package com.lesson.project.elasticsearch.springboot.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @author zhengshijun
 * @version created on 2018/11/27.
 *
 * https://blog.csdn.net/larger5/article/details/79777319
 */
@Document(indexName = "people",type = "man")
public class People {
    @Id
    private String id;

    private Integer age;

    private String country;

    private Date date;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

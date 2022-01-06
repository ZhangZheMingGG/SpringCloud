package com.kuang.springcloud.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 实体类
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)  //链式写法
public class Dept implements Serializable {

    private Long deptno;

    private String dname;

    //这个数据是存在哪个数据库的字段~ 微服务，一个服务对应一个数据库
    private String db_source;

    public Dept(String dname){
        this.dname = dname;
    }

    // 扩展 什么是链式写法呢？
    /*
    Dept dept = new Dept();

    一般写法：
    dept.setDeptno(11);
    dept.setDname("xxxx");
    链式写法：
    dept.setDeptno(11).setDname("xxxx");

     */
}

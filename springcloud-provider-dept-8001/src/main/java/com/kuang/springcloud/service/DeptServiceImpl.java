package com.kuang.springcloud.service;

import com.kuang.springcloud.pojo.Dept;
import com.kuang.springcloud.dao.DeptDao;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{

    @Autowired
    private DeptDao deptDao;

    @Override
    public boolean addDept(Dept dept) {
        return deptDao.addDept(dept);
    }

    @Override
    public Dept queryById(Long id) {
        return deptDao.queryById(id);
    }

    @Override
    public List<Dept> queryAll() {
        return deptDao.queryAll();
    }


    public static void main(String[] args) {
        System.out.println(DateUtils.addDays(new Date(), 1));
        DeptService deptService  = new DeptServiceImpl();
        System.out.println(deptService.queryAll());
        System.out.println(deptService.addDept(new Dept("dsad")));
    }
}

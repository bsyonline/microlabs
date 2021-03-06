package com.rolex.alphax.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rolex.alphax.dao.UserDao;
import com.rolex.alphax.model.Gender;
import com.rolex.alphax.model.Skill;
import com.rolex.alphax.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void insert() {
        User user = new User("tom", 19, Gender.Male, Skill.Java);
        int count = userDao.save(user);
        System.out.println("insert record count : " + count);
    }

    @Test
    public void findAll() {
        List<User> users = userDao.findAll();
        System.out.println(users);
    }

    @Test
    public void findByCondition() {
        User user = new User();
        user.setAge(20);
        user.setGender(Gender.Male);
        List<User> users = userDao.findByCondition(user);
        System.out.println(users);
    }

    @Test
    public void findByAnyCondition() {
        User user = new User();
//        user.setAge(20);
        user.setGender(Gender.Male);
        List<User> users = userDao.findByAnyCondition(user);
        System.out.println(users);
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(2);
        user.setGender(Gender.Female);
        userDao.update(user);
    }

    @Test
    public void bulkInsert() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User("user" + (i+1), new Random().nextInt(20) + 10, Gender.nameOf(new Random().nextInt(2) + 1), Skill.Java);
            list.add(user);
        }
        int count = userDao.batchSave(list);
        System.out.println("insert records count : " + count);
    }

    @Test
    public void bulkQuery() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<User> users = userDao.batchQuery(list);
        System.out.println(users);
    }

    @Test
    public void bulkDelete() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        int count = userDao.batchDelete(list);
        System.out.println("delete records count : " + count);
    }

    @Test
    public void bulkUpdate() {
        User user = new User(3, "alice", 29, Gender.Female, Skill.Java);
        User user1 = new User(4, "jim", 29, Gender.Male, Skill.CPP);
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);
        userDao.batchUpdate(list);
    }

    @Test
    public void testCache() {
        List<User> users = userDao.findAll();
        System.out.println(users);
        List<User> users1 = userDao.findAll();
        System.out.println(users1);
    }

    @Test
    public void listForPage(){
        PageHelper.startPage(200, 5);
        List<User> list = userDao.listForPage();
        PageInfo page = new PageInfo(list);
        //测试PageInfo全部属性
        //PageInfo包含了非常全面的分页属性
//        assertEquals(2, page.getPageNum());
//        assertEquals(5, page.getPageSize());
//        assertEquals(6, page.getStartRow());
//        assertEquals(10, page.getEndRow());
//        assertEquals(100, page.getTotal());
//        assertEquals(20, page.getPages());
//        assertEquals(true, page.isHasPreviousPage());
//        assertEquals(true, page.isHasNextPage());
        System.out.println(page.getList());
    }

    @Test
    public void findByNameAndAge(){
        List<User> list = userDao.findByNameAndAge("user9", 12);
        System.out.println(list);
    }

    @Test
    public void findByAge(){
        List<User> list = userDao.findByAge(20);
        System.out.println(list);
    }

    @Test
    public void groupByColumn(){
        List<Map<Integer, Integer>> list = userDao.groupByColumn("age");
        System.out.println(list);
    }
}

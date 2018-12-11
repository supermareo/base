package com.superychen.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.superychen.base.mybatis.mapper.test.TestMapper;
import com.superychen.base.redis.repository.RedisRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private RedisRepository redisRepository;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        com.superychen.base.mybatis.entity.test.Test query = new com.superychen.base.mybatis.entity.test.Test();
        query.setId(1);
        com.superychen.base.mybatis.entity.test.Test test = testMapper.selectByPrimaryKey(query);
        System.out.println(test);
        List<com.superychen.base.mybatis.entity.test.Test> tests = testMapper.selectAll();
        Assert.assertEquals(5, tests.size());
        tests.forEach(System.out::println);
        Page<com.superychen.base.mybatis.entity.test.Test> tests1 = PageHelper.startPage(1, 2).doSelectPage(() -> testMapper.selectAll());
        System.out.println(tests1.getPageNum());
        System.out.println(tests1.getPages());
        System.out.println(tests1.getPageSize());
        System.out.println(tests1.getTotal());
        System.out.println("==============");
        PageHelper.startPage(1, 2);
        List<com.superychen.base.mybatis.entity.test.Test> tests2 = testMapper.selectAll();
        Page<com.superychen.base.mybatis.entity.test.Test> page = (Page<com.superychen.base.mybatis.entity.test.Test>) tests2;
        System.out.println(page.getPageNum());
        System.out.println(page.getPages());
        System.out.println(page.getPageSize());
        System.out.println(page.getTotal());
        System.out.println(new Gson().toJson(page));
    }

    @Test
    public void testRedis() {
        redisRepository.set("a", "simple string");
        String a = redisRepository.get("a");
        System.out.println(a);
        com.superychen.base.mybatis.entity.test.Test test = new com.superychen.base.mybatis.entity.test.Test();
        test.setId(1);
        test.setEmail("963999353@qq.com");
        test.setName("superychen");
        redisRepository.set("b", test);
        com.superychen.base.mybatis.entity.test.Test b = redisRepository.getTest("b");
        System.out.println(new Gson().toJson(b));
    }

}

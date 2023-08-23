package com.example.demo;

import com.example.demo.Pojo.Emp;
import com.example.demo.Pojo.User;
import com.example.demo.mapper.EmpMapper;
import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {


	@Autowired
	private UserMapper userMapper;

	@Test
	public void testList(){
		List<User> userList = userMapper.list();
		for (User user : userList) {
			System.out.println(user);
		}
	}

	@Autowired //从Spring的IOC容器中，获取类型是EmpMapper的对象并注入
	private EmpMapper empMapper;

	@Test
	public void testDel(){
		//调用删除方法
		empMapper.delete(16);
	}

	@Test
	public void testInsert(){
		//创建员工对象
		Emp emp = new Emp();
		emp.setUsername("tom");
		emp.setName("汤姆");
		emp.setImage("1.jpg");
		emp.setGender((short)1);
		emp.setJob((short)1);
		emp.setEntrydate(LocalDate.of(2000,1,1));
		emp.setCreateTime(LocalDateTime.now());
		emp.setUpdateTime(LocalDateTime.now());
		emp.setDeptId(1);
		//调用添加方法
		empMapper.insert(emp);
	}

	@Test
	public void testUpdate(){
		//要修改的员工信息
		Emp emp = new Emp();
		emp.setId(23);
		emp.setUsername("songdaxia");
		emp.setPassword(null);
		emp.setName("老宋");
		emp.setImage("2.jpg");
		emp.setGender((short)1);
		emp.setJob((short)2);
		emp.setEntrydate(LocalDate.of(2012,1,1));
		emp.setCreateTime(null);
		emp.setUpdateTime(LocalDateTime.now());
		emp.setDeptId(2);
		//调用方法，修改员工数据
		empMapper.update(emp);
	}

	@Test
	public void testGetById(){
		Emp emp = empMapper.getById(1);
		System.out.println(emp);
	}

}

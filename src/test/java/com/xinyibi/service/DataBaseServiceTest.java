package com.xinyibi.service;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyibi.XinyiBiResource;
import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class DataBaseServiceTest {
	@Autowired
	private DataBaseService service;
	
	@Autowired
	private XinyiBiResource resource;
	@Before
	public void setUp() throws Exception {
		String workDir = resource.getWorkDir();
		log.info(workDir);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void run1(){
		PageEntry page = new PageEntry();
		page.setPage(1);
		page.setSize(10);
		Message<?> queryForPage = service.queryForPage(page);
		System.out.println(queryForPage);
	}
	
	@Test
	public void run2(){
		DatabaseInfo databaseInfo = new DatabaseInfo();
		databaseInfo.setUname("root");
		databaseInfo.setUpwd("123456");
		databaseInfo.setUrl("jdbc:mysql://localhost:3306/sqlite");
		databaseInfo.setDriverClassName("com.mysql.jdbc.Driver");
		databaseInfo.setAddTime(new Date());
		databaseInfo.setDatabaseType("MySQL");
		databaseInfo.setName("XYBI");
		Message<?> msg = service.addDataBase(databaseInfo);
		System.out.println(msg);
	}
	
	@Test
	public void run3(){
		Message<?> structure = service.getStructure("DB0961114747");
		System.out.println(structure);
	}

}

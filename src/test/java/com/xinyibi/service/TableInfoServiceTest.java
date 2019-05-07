package com.xinyibi.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TableInfoServiceTest {

	@Autowired
	private TableInfoService tableInfoService;

	@Before
	public void setUp() throws Exception { }

	@Test
	public void testQueryForPage() {
		PageEntry pageEntry = new PageEntry();
		pageEntry.setPage(2);
		pageEntry.setSize(5);
		Message<PageInfo<DataTableInfo>> queryForPage = tableInfoService.queryForPage(pageEntry);
		System.out.println(queryForPage);
	}

	public void destory(){ 
	}
}

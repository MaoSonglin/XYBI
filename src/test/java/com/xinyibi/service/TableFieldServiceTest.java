package com.xinyibi.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;
import com.xinyibi.vo.PageEntry.PageEntryItem;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TableFieldServiceTest {

	@Autowired
	private TableFieldService fieldService;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		PageEntry pageEntry = new PageEntry(1,10);
		PageEntryItem pageEntryItem = new PageEntryItem();
		pageEntryItem.setPropName("tbid");
		pageEntryItem.setParameters(new String[]{"TB0674054549"});
		pageEntry.getConditions().add(pageEntryItem);
		Message<PageInfo<TableFieldInfo>> page = fieldService.queryForPage(pageEntry);
		System.out.println(page);
	}

}

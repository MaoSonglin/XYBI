package com.xinyibi.adapter;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;
import com.xinyibi.mapper.DataTableInfoMapper;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.DataTableInfoExample;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JdbcDataAdapterTest {

	@Autowired
	private ApplicationContext context;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetDataTable() {
		fail("Not yet implemented");
	}

	@Test
	public void run1(){
		DataTableInfoMapper tableInfoMapper = context.getBean(DataTableInfoMapper.class);
		List<DataTableInfo> list = tableInfoMapper.selectByExample(new DataTableInfoExample());
		for (DataTableInfo dataTableInfo : list) {
			System.out.println(dataTableInfo.getId());
		}
	}
	/**
	 * 测试获取单个数据表中的数据是否成功
	 * @throws Exception 
	 */
	@Test
	public void run2() throws Exception{
		DataTableInfo dataTableInfo = context.getBean(DataTableInfoMapper.class).selectByPrimaryKey("TB0961394190");
		DataAdapter adapter = new JdbcDataAdapter();
		DataTable<DataMap> dataTable = adapter.getDataTable(dataTableInfo);
		String str = new ObjectMapper().writeValueAsString(dataTable);
		System.out.println(str);
	}
	
	
	@Test
	public void run3(){
		
	}
	
}

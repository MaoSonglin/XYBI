package com.xinyibi.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyibi.exception.ServiceException;
import com.xinyibi.model.ViewDetailModel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DataViewServiceTest {

	@Autowired
	private DataViewService service;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetGraphInfo() throws ServiceException {
		@SuppressWarnings("deprecation")
		ViewDetailModel graphInfo = service.getGraphInfo("TV0630135515");
		System.out.println(graphInfo);
	}

	public void test(){
		
	}
}

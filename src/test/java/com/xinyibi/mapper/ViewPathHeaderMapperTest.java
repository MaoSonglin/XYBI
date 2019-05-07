package com.xinyibi.mapper;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyibi.model.ViewGraphModel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ViewPathHeaderMapperTest {

	@Autowired
	private ViewPathHeaderMapper viewPathMapper;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindViewGraphByViewId() {
		List<ViewGraphModel> model = viewPathMapper.findViewGraphByViewId("TV1");
		System.out.println(model.size());
	}

}

package com.xinyibi.mapper;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyibi.pojo.ViewField;
import com.xinyibi.pojo.ViewFieldExample;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ViewFieldMapperTest {
	
	@Autowired
	ViewFieldMapper fieldMapper;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSelectByExample() {
		fail("Not yet implemented");
	}

	@Test
	public void run2(){
		List<ViewField> list = fieldMapper.selectByExample(new ViewFieldExample());
		for (ViewField viewField : list) {
			System.out.println(viewField.getItems());
		}
	}
}

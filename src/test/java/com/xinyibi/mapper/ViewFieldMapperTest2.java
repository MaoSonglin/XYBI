package com.xinyibi.mapper;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ViewFieldMapperTest2 {
	
	@Autowired
	private ViewFieldMapper mapper;
	
	@Test
	public void testDeleteByViewIdAndDataFieldIds() {
		fail("Not yet implemented");
	}
	
	@Test
	public void run2() {
		List<String> ids = new ArrayList<>();
		ids.add("23423view_field");
		ids.add("2342");
		ids.add("009");
		int updated = mapper.deleteByViewIdAndDataFieldIds("1", ids);
		System.out.println(updated);
	}

}

package com.xinyibi.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class DatabaseInfoMapperTest {
	
	@Autowired
	private DatabaseInfoMapper mapper;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		for(int i = 0; i < 10; i ++){
			long start = System.currentTimeMillis();
			mapper.findByViewId("TV0630135515");
			long end = System.currentTimeMillis();
			long start2 = System.currentTimeMillis();
			mapper.findByViewIdByExists("TV0630135515");
			long end2 = System.currentTimeMillis();
			log.info("join "+(end-start)+" , exists "+ (end2-start2));
		}
	}

}

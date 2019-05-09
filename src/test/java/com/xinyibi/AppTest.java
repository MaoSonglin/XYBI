package com.xinyibi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
    	List<Integer> arrayList = new ArrayList<>();
    	for(int i = 1; i < 10; i ++){
    		arrayList.add(i);
    	}
    	
    	List<Integer> collect = arrayList.stream().peek(new Consumer<Integer>() {

			@Override
			public void accept(Integer t) {
				
			}
		}).collect(Collectors.toList());///.collect(Collectors.toList());
    	System.out.println(collect);
    }
    
    @Test
    public void run2(){
    	String s = ""/**~{
				ID为'%s'的字段所在的数据表与ID为'%s'的视图关联的数据表之间没有关联关系
			}*/;
    	System.out.println(s);
    }
}

package com.xinyibi.vo;

import com.xinyibi.pojo.Account;

import lombok.Data;

@Data
public class AccountState {
	
	private long time;
	
	private Account account;
	
	private String token;
	
	public boolean isOver(){
		return System.currentTimeMillis() - time > 30 * 60 * 1000;
	}
	
	public void refresh(){
		time = System.currentTimeMillis();
	}
}

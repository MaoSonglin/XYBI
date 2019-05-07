package com.xinyibi.model;

import com.xinyibi.pojo.Account;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UserDetailInfo extends Account {
	private Integer personId;
	private String name;
	private String nickname;
	private String cellphone;
	private String email;
	private String address;
	private String gender;
	private Integer age;
	private Integer birthday;
	private String comment;
	private Long photo;
	private String avatar;
}

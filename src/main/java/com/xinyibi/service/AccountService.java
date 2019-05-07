package com.xinyibi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinyibi.exception.ServiceException;
import com.xinyibi.mapper.AccountMapper;
import com.xinyibi.mapper.PersonMapper;
import com.xinyibi.model.UserDetailInfo;
import com.xinyibi.pojo.Account;
import com.xinyibi.pojo.AccountExample;
import com.xinyibi.pojo.AccountExample.Criteria;
import com.xinyibi.pojo.Person;
import com.xinyibi.util.StrUtils;
import com.xinyibi.vo.Message;

@Service
public class AccountService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7512473754536951894L;
	
	@Autowired
	private ApplicationContext context;
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private PersonMapper personMapper;
	
	public Message<String> login(Account account){
		
		AccountExample example = new AccountExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo(account.getUserName()); 
		
		List<Account> list = accountMapper.selectByExample(example);
		if(list.isEmpty()){
			return Message.fail("用户名不存在", "用户名不存在");
		}else if(list.get(0).getUserPwd().equals(account.getUserPwd())){
			return Message.success("登录成功",StrUtils.createToken());
		}
		return Message.fail("密码错误", "密码错误");
	}

	@Transactional
	public Message<?> createAccount(Account account) {
		// 检查用户名是否存在
		String userName = account.getUserName();
		AccountExample example = new AccountExample();
		example.createCriteria().andUserNameEqualTo(userName);
		
		long countByExample = this.accountMapper.countByExample(example);
		if(countByExample > 0){
			return Message.fail("", "用户名以存在");
		}
		 
		account.setSalt(StrUtils.randStr(6));
		// 将用户插入到系统中
		accountMapper.insert(account);
		
		// 创建一个person对象
		Person person = new Person();
		person.setName(account.getUserName());
		personMapper.insert(person);
		
		// 关联对象
		account.setPid(person.getId());
		accountMapper.updateByPrimaryKey(account);
		
		return Message.success("注册成功", "注册成功");
	}

	public Message<?> existAdmin() {
		long countByExample = accountMapper.countByExample(new AccountExample());
		return Message.success("", countByExample);
	}

	public Account getUserInfo(String userName) throws ServiceException {
		UserDetailInfo userDetailByUserName = accountMapper.findUserDetailByUserName(userName);
		if(userDetailByUserName==null) throw new ServiceException("用户名不存在");
		Long photo = userDetailByUserName.getPhoto();

		FileService fileService = context.getBean(FileService.class);
		String url = fileService.getFileUrl(photo);
		userDetailByUserName.setAvatar(url);
		
		return userDetailByUserName;
	}

	
}

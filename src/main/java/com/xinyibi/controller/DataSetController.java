package com.xinyibi.controller;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.xinyibi.pojo.Account;
import com.xinyibi.pojo.DataSet;
import com.xinyibi.service.DataSetService;
import com.xinyibi.util.StrUtils;
import com.xinyibi.vo.Message;

/**
 * @author MaoSonglin
 * 数据包管理控制器
 */
@Controller
@RequestMapping("/dataset")
public class DataSetController implements Serializable{

	private static final long serialVersionUID = -5422780586108954860L;
	
	@Autowired
	private DataSetService dataSetService;

	/**
	 * 添加数据集
	 * @param dataSet
	 * @param session
	 * @return
	 */
	@RequestMapping("/add")
	public @ResponseBody Object add(DataSet dataSet,HttpSession session){
		Integer accountId = dataSet.getAccountId();
		if(accountId == null){
			// 设置用户ID
			Account account = (Account) session.getAttribute(Account.class.getName());
			if(account != null){
				dataSet.setAccountId(account.getId());
			}else{
				// 获取用户ID失败
				return Message.fail("用户ID不能为空", dataSet);
			}
		}
		// 检查数据集名称
		String dataSetName = dataSet.getDataSetName();
		if(StringUtil.isEmpty(dataSetName)){
			return Message.fail("数据集名称不能为空", dataSetName);
		}
		// 设置数据集ID
		dataSet.setId(StrUtils.getNextId("PKG"));
		Message<DataSet> message = dataSetService.add(dataSet);
		return message;
	}
	
	/**
	 * 修改数据集信息
	 * @param dataSet
	 * @return
	 */
	@RequestMapping("/udpate")
	public @ResponseBody Object udpate(DataSet dataSet){
		String id = dataSet.getId();
		if(StringUtils.isEmpty(id)){
			return Message.fail("数据集ID不能为空", dataSet);
		}
		return dataSetService.update(dataSet);
	}
}

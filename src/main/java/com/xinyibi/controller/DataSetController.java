package com.xinyibi.controller;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xinyibi.exception.ServiceException;
import com.xinyibi.pojo.Account;
import com.xinyibi.pojo.DataSet;
import com.xinyibi.service.DataSetService;
import com.xinyibi.util.StrUtils;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;

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
			Account account = (Account) session.getAttribute("Account");
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
	@RequestMapping("/update")
	public @ResponseBody Object udpate(DataSet dataSet){
		String id = dataSet.getId();
		if(StringUtils.isEmpty(id)){
			return Message.fail("数据集ID不能为空", dataSet);
		}
		return dataSetService.update(dataSet);
	}
	
	
	/**
	 * 查询
	 * @param entry
	 * @return
	 */
	@RequestMapping("/page")
	public @ResponseBody Object list(PageEntry entry){
		PageInfo<DataSet> page = dataSetService.queryForPage(entry);
		Message<PageInfo<DataSet>> success = Message.success("查询成功", page);
		return success;
	}
	
	
	/**
	 * 删除数据包
	 * @param id 数据包ID
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Message<?> drop(String id){
		try {
			boolean f = dataSetService.dropById(id);
			return f ? Message.success("删除成功", id) : Message.fail("删除失败", f);
		} catch (ServiceException e) {
			return Message.error(e.getMessage(), e);
		}
	}
	
}

package com.xinyibi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyibi.mapper.ElementMapper;
import com.xinyibi.mapper.ReportMapper;
import com.xinyibi.pojo.Element;
import com.xinyibi.pojo.ElementExample;
import com.xinyibi.pojo.Report;
import com.xinyibi.pojo.ReportExample;
import com.xinyibi.util.StrUtils;
import com.xinyibi.vo.PageEntry;
import com.xinyibi.vo.PageEntry.PageEntryItem;

@Service
public class ReportService {

	@Autowired
	private ApplicationContext context;
	
	@Transactional
	public boolean addNewReport(Report report) {
		report.setId(StrUtils.getNextId());
		ReportMapper mapper = context.getBean(ReportMapper.class);
		int insert = mapper.insert(report);
		return insert > 0;
	}
	
	
	@Transactional
	public boolean drop(Long id) {
		// 删除报表
		int i = context.getBean(ReportMapper.class).deleteByPrimaryKey(id);
		// 查找报表下的元素
		List<Element> elements = context.getBean(ElementMapper.class).findByReportId(id);
		// 报表下的元素的ID
		List<Long> elementIds = elements.stream().map(e->e.getId()).collect(Collectors.toList());
		// 删除报表下的元素
		ElementExample example = new ElementExample();
		example.createCriteria().andIdIn(elementIds);
		context.getBean(ElementMapper.class).deleteByExample(example);
		return i > 0;
	}
	
	public PageInfo<Report> list(PageEntry entry){
		// 分页插件
		PageHelper.startPage(entry.getPage(), entry.getSize());
		
		// 查询条件
		ReportExample example = new ReportExample();
		List<PageEntryItem> conditions = entry.getConditions();
		if(conditions != null && ! conditions.isEmpty()) {
			for (PageEntryItem entryItem : conditions) {
				String propName = entryItem.getPropName();
				if("name".equalsIgnoreCase(propName)) {
					example.createCriteria().andNameLike("%"+entryItem.getParameters()[0]+"%");
				}
			}
		}
		
		// 查询结果
		List<Report> list = context.getBean(ReportMapper.class).selectByExample(example);
		
		// 处理每张报表的缩略图
		list.forEach(report->{
			try {
				String thumbnail = report.getThumbnail();
				String fileUrl = context.getBean(FileService.class).getFileUrl(Long.valueOf(thumbnail));
				report.setThumbnail(fileUrl);
			} catch (BeansException | NumberFormatException e) {
				e.printStackTrace();
			}
		});
		
		// 封装查询结果
		PageInfo<Report> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	@Transactional
	public boolean update(Report report) {
		int i = context.getBean(ReportMapper.class).updateByPrimaryKeySelective(report);
		return i > 0;
	}
}

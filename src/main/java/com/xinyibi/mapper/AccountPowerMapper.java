package com.xinyibi.mapper;

import com.xinyibi.pojo.AccountPower;
import com.xinyibi.pojo.AccountPowerExample;
import com.xinyibi.pojo.AccountPowerKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountPowerMapper {

	long countByExample(AccountPowerExample example);

	int deleteByExample(AccountPowerExample example);

	int deleteByPrimaryKey(AccountPowerKey key);

	int insert(AccountPower record);

	int insertSelective(AccountPower record);

	List<AccountPower> selectByExample(AccountPowerExample example);

	AccountPower selectByPrimaryKey(AccountPowerKey key);

	int updateByExampleSelective(@Param("record") AccountPower record, @Param("example") AccountPowerExample example);

	int updateByExample(@Param("record") AccountPower record, @Param("example") AccountPowerExample example);

	int updateByPrimaryKeySelective(AccountPower record);

	int updateByPrimaryKey(AccountPower record);
}
package com.xinyibi.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Deprecated
public class SequenceDao {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public Integer next(){
		try(SqlSession openSession = sqlSessionFactory.openSession()){
			sqlSessionFactory.getConfiguration().addLoadedResource("com/xinyibi/dao/ViewGraphDao.xml");
			int nextId = openSession.selectOne("com.xinyibi.mapper.AccountMapper.next");
			return nextId;
		}
	}
	
	public boolean plus(int val){
		try(SqlSession openSession = sqlSessionFactory.openSession()){
			int update = openSession.update("com.xinyibi.mapper.AccountMapper.plus", val);
			return update>0;
		}
	}
}

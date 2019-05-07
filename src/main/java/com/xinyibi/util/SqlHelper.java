package com.xinyibi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xinyibi.pojo.DatabaseInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlHelper {
	
	private String url;
	
	private String username;
	
	private String password;
	
	public <T> List<T> query(String sql,ResultSetHelper<T> handler,Object...args) throws SQLException{
		List<T> list = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url,username,password);
				PreparedStatement ps = conn.prepareStatement(sql)){
			int length = args.length;
			for(int i = 1; i <= length; i++){
				ps.setObject(i, args[i]);
			}
			try(ResultSet rs = ps.executeQuery()){
				if(handler != null){
					int i = 0;
					while(rs.next()){
						T map = handler.map(i++,rs);
						list.add(map);
					}
				}
			}
		}
		return list;
	}
	
	public SqlHelper(DatabaseInfo database) {
		this(database.getUrl(),database.getUname(),database.getUpwd());
	}

	public static interface ResultSetHelper<T>{
		
		T map(int row,ResultSet rs) throws SQLException;
	}
}

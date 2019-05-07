package com.xinyibi.service;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.activation.MimetypesFileTypeMap;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyibi.mapper.FileInfoMapper;
import com.xinyibi.pojo.FileInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FileServiceTest {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private FileInfoMapper mapper;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetFile() {
		fail("Not yet implemented");
	}
	
	@Test
	public void addFile() throws SQLException, IOException{
		FileInfo fileInfo = new FileInfo();
		fileInfo.setAccountId(1);
		fileInfo.setCreateDate(new Date());
		
		File file = new File("C:\\Users\\MaoSonglin\\Pictures\\QQ图片20190308172247.jpg");
		fileInfo.setFileName(file.getName());
		String contentType = new MimetypesFileTypeMap().getContentType(file);
		fileInfo.setMime(contentType);
		long sizeOf = FileUtils.sizeOf(file);
		fileInfo.setFileSize(sizeOf / 8d);
		fileInfo.setStatus(1);
		
		mapper.insert(fileInfo);
	}
	
	@Test
	public void writeFile() throws SQLException, IOException{
		File file = new File("C:\\Users\\MaoSonglin\\Pictures\\证件照.jpg");
		Connection conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("update file_info set body = ? where id = 1");
		byte[] readFileToByteArray = FileUtils.readFileToByteArray(file);
		ps.setObject(1, readFileToByteArray);
//		ps.setBlob(parameterIndex, inputStream);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	@Test
	public void writeFile1() throws Exception{
		File file = new File("D:\\original\\Pictures\\35a5c7c999544eed454b412761285c06.jpg");
		Connection conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("update file_info set body = ? where id = 2");
		
//		ps.setblob
//		FileInputStream openInputStream = FileUtils.openInputStream(file); 
		ps.setObject(1, FileUtils.readFileToByteArray(file));
		ps.executeUpdate();
		ps.close();
		conn.close();
	}
	@Test
	public void writeFile2() throws Exception{
		File file = new File("C:\\Users\\MaoSonglin\\Pictures\\QQ图片20190308172247.jpg");
		Connection conn = dataSource.getConnection();
		Statement ps = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = ps.executeQuery("select * from file_info");
		rs.next();
		rs.next();
//		ps.setblob
		FileInputStream openInputStream = FileUtils.openInputStream(file);
//		Blob blob = rs.getBlob(1);
//		OutputStream outputStream = blob.setBinaryStream(1);
//		StreamUtils.copy(openInputStream, outputStream);
//		
//		outputStream.close();
//		openInputStream.close();
//		rs.updateBlob("body", openInputStream);
		rs.updateBinaryStream("body", openInputStream);
		rs.updateRow();
		ps.close();
		conn.close();
	}
	
	@Test
	public void run2() throws IOException{
		FileInfo fileInfo = new FileInfo();
		fileInfo.setAccountId(1);
		fileInfo.setCreateDate(new Date());
		
		File file = new File("D:\\original\\Pictures\\lovewallpaper\\c2ed62a81424a5f873ac3e7e7f7bbd2e.jpg");
		fileInfo.setFileName(file.getName());
		String contentType = new MimetypesFileTypeMap().getContentType(file);
		fileInfo.setMime(contentType);
		long sizeOf = FileUtils.sizeOf(file);
		fileInfo.setFileSize(sizeOf / 8d);
		fileInfo.setStatus(1);
		fileInfo.setBody(FileUtils.readFileToByteArray(file));
		fileInfo.setFileName(file.getName());
		
		mapper.insert(fileInfo);
	}
}

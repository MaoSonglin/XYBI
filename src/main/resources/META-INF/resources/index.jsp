<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>这就是主页</title>
</head>
<body>
<a href="${pageContext.request.contextPath }/fileUpload.jsp">上传文件</a>
<div>
	<form action="${pageContext.request.contextPath }/user/login" method="post">
		<fieldset>
			<legend>用户登录</legend>
			<table>
				<tr>
					<td><label>用户名:</label></td>
					<td><input name="userName" id="userName"></td>
				</tr>
				<tr>
					<td><label>密码:</label></td>
					<td><input name="userPwd" id="userPwd" type="password"></td>
				</tr>
				<tr>
					<td>
					<td><button>登录</button>
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>
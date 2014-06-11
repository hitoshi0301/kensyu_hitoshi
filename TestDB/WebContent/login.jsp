<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>フォーム認証</title>
</head>
<body>
<form method="POST" action="j_security_check"> <!-- AuthServlet -->
<table border="0">
	<tr>
		<th align="right">ユーザ名</th>
		<td><input type="text" name="j_username" size="15" /></td>
	</tr>
	<tr>
		<th align="right">パスワード：</th>
		<td><input type="password" name="j_password" size="15" /></td>
	</tr>
	<tr>
		<td rowspan="2">
			<input type="submit" value="ログイン" />
			<input type="reset" value="取り消し" />
		</td>
	</tr>
</table>
</form>
</body>
</html>
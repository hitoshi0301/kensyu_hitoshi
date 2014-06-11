<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>タスクの一覧</title>
</head>
<body>
<table border="1">
	<tr>
			<th>番号</th>
			<th>タイトル</th>
			<th>タスク内容</th>
			<th>期限</th>
			<th>最終更新</th>
			<th>ユーザーID</th>
			<th>状況</th>
			<th>詳細画面</th>
			<th>添付ファイル</th>
	</tr>
	<c:forEach items="${todoList}" var="vo" >
	<tr>
		<td><c:out value="${vo.id }" /></td>
		<td><c:out value="${vo.title }" /></td>
		<td><c:out value="${vo.task }" /></td>
		<td><fmt:formatDate value="${vo.limitdate }" pattern="yyyy-MM-dd" /></td>
		<td><fmt:formatDate value="${vo.lastupdate }"  pattern="yyyy-MM-dd" /></td>
		<td><c:out value="${vo.userid }" /></td>
		<td><c:out value="${vo.label }" /></td>
		<td><a href="detail?id=<c:out value="${vo.id }" />">詳細画面へ</a></td>
		<td>
			<c:choose>
				<c:when test="${vo.filename != null }">
					<a href="download?id=<c:out value="${vo.id }" />">▼ダウンロード▼</a>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	</c:forEach>
</table>
<br />
●<a href="input">タスクの新規登録</a>
●<a href="search">タスクの一覧の再表示</a>
</body>
</html>
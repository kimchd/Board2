<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<C:forEach items="${list}" var="list">

${list.bno}</br>
${list.title}</br>
${list.writer}</br>
${list.regdate}</br>
${list.updatedate}</br>
${list.content}

</C:forEach>
	<C:forEach items="${list}" var="list">
		<div align="center">
			<h1>답글 쓰기</h1>
			<form method="post">

				<div>
					<input type="hidden" name="title" value="re:${list.title}">

				</div>
				<label>Content</label>
				<div>
					<textarea rows=5 cols="30" name="content">sample 내용</textarea>
				</div>
				<label>Writer</label>
				<div>
					<input type="text" name="writer" value="user01">
				</div>

				<input type="hidden" name="gno" value="${list.gno}">
				<input type="hidden" name="parent" value="${list.bno}">
				<input type="hidden" name="gord" value="${list.gord}">
				<button>답글 등록</button>
			</form>
		</div>
	</C:forEach>


</body>
</html>
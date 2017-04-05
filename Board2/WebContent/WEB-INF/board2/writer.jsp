<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h1>글 쓰기</h1>
	<form method= "post" >
		<label>Title</label>
		<div>
			<input type= "text" name="title" value="sample 한글">	
		</div>
		<label>Content</label>
		<div>
			<textarea rows= 5 cols="30" name="content">sample 내용</textarea>	
		</div>
		<label>Writer</label>
		<div>
			<input type= "text" name="writer" value="user01">	
		</div>
		
		<button>Register</button>
	</form>
</div>
</body>
</html>
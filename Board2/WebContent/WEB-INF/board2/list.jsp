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


	<h1>List Page</h1>
	<style>
.pagination li {
	list-style: none;
	float: left;
	margin: 10px;
	border: 1px solid blue;
	width: 20px;
	text-align: center;
}

a {
	text-decoration: none;
}

.important {
	background-color: fuchsia;
}

.list {
	list-style: none;
}
</style>


	<div class="list">
		<C:forEach items="${list}" var="list">

			<a href='view?bno=${list.bno}'><li>${list.bno}${" "}${list.title} ${" "} ${list.writer} ${" "}
				${list.regdate} ${" "} ${list.updatedate}</li></a>

		</C:forEach>
	</div>
	<form method="post">
	<button>새글등록</button>
	
	</form>
	<ul class="pagination">
		<C:if test="${pager.prev}">
			<li><a href='list?pageNum=${pager.start -1 }'> << </a></li>
		</C:if>

		<C:forEach begin="${pager.start}" end="${pager.end }" var="num">
			<li class='${num==pager.current?'important':''}'><a
				href="list?pageNum=${num}">${num}</a></li>
		</C:forEach>

		<C:if test="${pager.next}">
			<li><a href='list?pageNum=${pager.end +1 }'> >> </a></li>
		</C:if>

	</ul>
	


</body>
</html>
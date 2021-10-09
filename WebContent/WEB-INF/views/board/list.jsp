<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<div class="container">

<h1>게시글 목록</h1>
<hr>

<table class="table table-striped table-hover table-condensed">
<tr>
	<th>글번호</th>
	<th>제목</th>
	<th>아이디</th>
	<th>조회수</th>
	<th>작성일</th>
</tr>

<c:forEach items="${boardList }" var="board">
<tr>
	<td>${board.boardno }</td>
	<td>
		<a href="/board/view?boardno=${board.boardno }">
		${board.title }
		</a>
	</td>
	<td>${board.userid }</td>
	<td>${board.hit }</td>
	<td>${board.writeDate }</td>
</tr>
</c:forEach>

</table>

</div>

<c:import url="/WEB-INF/views/layout/paging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />

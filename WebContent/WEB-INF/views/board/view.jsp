<%@page import="web.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/board/list");
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {
	});
	
});
</script>

<div class="container">

<h1>게시글 상세보기</h1>
<hr>

<table class="table table-bordered">
<tr>
<td class="info">글번호</td><td colspan="3">${viewBoard.boardno }</td>
</tr>

<tr>
<td class="info">제목</td><td colspan="3">${viewBoard.title }</td>
</tr>

<tr>
<td class="info">아이디</td><td>${viewBoard.userid }</td>
<td class="info">닉네임</td><td>[ 추후 추가 ]</td>
</tr>

<tr>
<td class="info">조회수</td><td>${viewBoard.hit }</td>
<td class="info">추천수</td><td>[ 추후 추가 ]</td>
</tr>

<tr>
<td class="info">작성일</td><td colspan="3">${viewBoard.writeDate }</td>
</tr>

<tr><td class="info"  colspan="4">본문</td></tr>

<tr><td colspan="4">${viewBoard.content }</td></tr>

</table>

<div class="text-center">	
	<button id="btnList" class="btn btn-primary">목록</button>
	<button id="btnUpdate" class="btn btn-info">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
</div>

</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />

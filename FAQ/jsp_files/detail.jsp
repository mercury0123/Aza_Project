<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>FAQ_Detail_Page</title>
	
	
	<link rel="stylesheet" href="/resources/css/faq/detail.css">
	<link rel="stylesheet" href="/resources/css/layout/style.css">
	<jsp:include page="/WEB-INF/views/layout/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	<section>
		<div id="pageTitle"><h1>FAQ 관리 페이지</h1></div>
		
		<div id="detailContainer">
			<div id="table">
				<table style="border: 1px solid #FAE100;">
				<thead>
					<tr>
					  <th>No.${FaqVO.no }</th>
					  <th>${FaqVO.title }</th>
					</tr>
				</thead>
				<tbody>
					<tr>					  
					  <td colspan="2">${FaqVO.content } </td>
					</tr>
				</tbody>
				</table>
			</div>
			<hr>
			<div id="btn-zone">
				<a href="/faq/update/${FaqVO.no }"><button type="button">수정</button></a>
				&nbsp;&nbsp;<a href="/faq/delete/${FaqVO.no }"><button type="button" onclick="delCheck()">삭제</button></a>
				&nbsp;&nbsp;<a href="/faq/list"><button type="button" >목록</button></a>
			</div>
		
		</div>
	</section>
	<script type="text/javascript" src="/resources/javascript/Faq.js"></script>
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</body>
</html>
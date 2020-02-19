<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
	Exception e = (Exception)request.getAttribute("exception");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERROR</title>
<style>
	.jb-wrap {
				width: 40%;
				margin: 10px;
				margin-left: 500px;
			}
	.jb-wrap img {
				padding-top: 100px;
				margin-right: 200px;
				width: 120%;
				vertical-align: middle;
			}
	.jb-text {
				padding: 5px 10px;
				text-align: left;
				position: absolute;
				top: 28%;
				left: 55%;
				transform: translate( -50%, -90% );
			}
</style>
</head>
<body>
<%@ include file="/views/common/header.jsp"%>
<%--@ include file="header.jsp" --%>
		<div class="jb-wrap">
			<div class="jb-image"><img src="/PETNER/resources/img/error.png"></div>
			<div class="jb-text">
				<h2><span style="font-weight: 600;">서비스 에러 발생!!!</span></h2>
		<br><br>
		<h3 >ERROR : 
		<%if(e !=null){ %>
		<%= e.getMessage() %>
		<%}else{ %>
		<%= msg %>
		<%} %>
		</h3>
		<h4>서비스 수행 중 에러가 발생했습니다.관리자에게 연락하시기 바랍니다.</h4>
			</div>
		</div>
<%@ include file="/views/common/footer.jsp"%>
</body>
</html>
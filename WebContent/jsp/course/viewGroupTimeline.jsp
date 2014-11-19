<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Time Line - Grupo ${nomeGroup}</title>
	<style type="text/css">
		input
		{
			cursor: pointer;
			background: #ffffff;
			color: #000000;
			border: none;
		}
		input.class1
		{
			font-size:12px;			
		}
		input.class2
		{
			font-size:15px;
		}
		input.class3
		{
			font-size:18px;
		}
		input.class4
		{
			font-size:21px;
		}
		input.class5
		{
			font-size:24px;
		}
		input.class6
		{
			font-size:27px;
		}
		input.class7
		{
			font-size:30px;
		}
		input.class8
		{
			font-size:33px;
		}
		input.class9
		{
			font-size:36px;
		}
		input.class10
		{
			font-size:39px;
		}
		div.cloud
		{
			border: 1px solid grey;
		}
	</style>
	<script type="text/javascript">
		function openJ(date)
		{
			var groupId = <%= request.getAttribute("groupId")%>;
			var external = window.open("http://"+'localhost:8080/amadeuslms' + "/course.do?method=showViewGroupDayTimeline&groupId="+groupId+"&data="+date,'Grupo',
			'height=450,width=500,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no ,modal=yes');
		}
		function resize()
		{
			window.resizeTo(480,600);
		}
	</script>
</head>
<body onLoad="resize();">
	<h2>Time Line - Grupo ${nomeGroup}</h2>
	<div class="cloud">
		<c:forEach  var="tag" items="${timeline}" varStatus="status">
			<input type="button" onClick="openJ('${tag.date}')" class="${tag.classe}" value="${tag.date}"/>
		</c:forEach>

	</div>
</body>
</html>
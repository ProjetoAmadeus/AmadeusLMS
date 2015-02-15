<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Time Line - Grupo ${nomeGroup}</title>
	<link href="themes/default/css/css.css" rel="stylesheet" type="text/css" />
	<link href="themes/default/css/amadeus.css" rel="stylesheet" type="text/css" />
	<link href="themes/default/css/groups.css" rel="stylesheet" type="text/css"></link>
	<script type="text/javascript">
		function openJ(date)
		{
			var ctx = "${pageContext.request.contextPath}";
			var groupId = <%= request.getAttribute("groupId")%>;
			var external = window.open(ctx + "/course.do?method=showViewGroupDayTimeline&groupId="+groupId+"&data="+date,'Grupo',
			'height=450,width=500,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no ,modal=yes');
		}
		function resize()
		{
			window.resizeTo(480,600);
		}
	</script>
</head>
<body onLoad="resize();">
	<h2>Timeline do grupo ${nomeGroup}</h2>
	<div class="cloud">
		<c:forEach  var="tag" items="${timeline}" varStatus="status">
			<input type="button" onClick="openJ('${tag.date}')" class="${tag.classe}" value="${tag.date}"/>
		</c:forEach>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Game</title>
<script type="text/javascript" src="js/managementActivity.js"></script>
<script type="text/javascript">

	function isReady()
	{
		document["Game"].sendIsReady(true);		
	}
	
	function getUserId()
	{
		document["Game"].sendUserId("${user.id}");
	}

	function getUserName()
	{
		document["Game"].sendUserName("${user.person.name}");
	}

	function saveDados(level, score, tempo, meta)
	{
		var g = parseInt("${game.id}",0);
		log(g,level,tempo,score,meta);
		
	}

	function resize()
	{
		window.resizeTo(10,10);
		window.resizeTo(811,661);
	}
	
</script>
</head>
<body onload="resize();">
<div>
	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
    	id="ExternalInterfaceExample" width="780" height="580"
	    codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
    	<param name="movie" value="${game.url}" />
    	<param name="quality" value="high" />
	    <param name="bgcolor" value="#869ca7" />
	    <param name="allowScriptAccess" value="sameDomain" />
		<embed src="${game.url}" quality="high" bgcolor="#869ca7"
    		width="780" height="580" name="Game" align="middle"
    		play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
	        type="application/x-shockwave-flash"
           	pluginspage="http://www.macromedia.com/go/getflashplayer">
	    </embed>
	</object>
</div>
</body>
</html>
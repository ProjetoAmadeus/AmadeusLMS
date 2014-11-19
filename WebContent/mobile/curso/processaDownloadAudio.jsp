<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_web.facade.Facade"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material"%>

<%
	Facade amadeusFacade = Facade.getInstance();
	int fileId = Integer.parseInt(request.getParameter("audioId"));
	Material materialAudio = amadeusFacade.getMaterialByID(fileId);
	
	String fileName = materialAudio.getArchiveName()+".mp3";
	response.setContentType("audio/mpeg");
	response.addHeader("Content-Disposition", "attachment; filename=" + fileName);

	OutputStream fluxoSaida = response.getOutputStream();

	try {
		fluxoSaida.write(materialAudio.getArchive().getArchive());
		fluxoSaida.flush();
		fluxoSaida.close();

	} catch (IOException e) {
		e.printStackTrace();
	}

%>
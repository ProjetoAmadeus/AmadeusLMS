/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_sdmm.general;

/*
 * ConvertXMLtoHTML.java
 *
 * Created on December 23, 2007, 12:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConvertXMLtoHTML {
	private static final String VIEWER_XML_PATH_FORMAT = "http://localhost:8080/AmadeusSDMM/VideoViewerXML.jsp?id=%d";
	private static final String SEARCH_XML_PATH_FORMAT = "http://localhost:8080/AmadeusSDMM/PageSearchVideoXML.jsp?tipo=%s&valor=%s&page=%d";
	private static final String VIEWER_PATH_FORMAT = "http://localhost:8080/AmadeusLMS/jsp/course/content_management/activities/ViewMultimediaActivities.jsp?id=%d";

	public static String viewerConvert(int videoId) {
		final String MODEL_PATH = "http://localhost:8080/AmadeusLMS/ModelViewer.html";
		String html = null;
		try {
			InputStream xmlStream = new URL(String.format(VIEWER_XML_PATH_FORMAT, videoId)).openStream();
			Document xmlDoc = parseXmlFile(xmlStream);
			NodeList childNodes = xmlDoc.getFirstChild().getChildNodes();
			Hashtable<String, String> table = new Hashtable<String, String>();
			for (int i = 1; i < childNodes.getLength(); i += 2) {
				Node item = childNodes.item(i);
				String nodeName = item.getNodeName();
				String nodeValue = item.getFirstChild().getNodeValue();
				table.put(nodeName, nodeValue);
			}
			xmlStream.close();

			InputStream modelStream = new URL(MODEL_PATH).openStream();
			byte[] bytes = new byte[modelStream.available()];
			modelStream.read(bytes);
			modelStream.close();

			String param = new String(bytes, Charset.forName("ISO-8859-1"));
            //String param = new String(bytes);
			String lengthInKB = String.valueOf(Long.parseLong(table.get("length"))/1024);
			html = String.format(param,
					table.get("url"),			
					table.get("name"),
					table.get("author"),
					table.get("description"),
					table.get("tags"),
					table.get("license"),
					table.get("width"),
					table.get("height"),
					lengthInKB,
					table.get("date-of-insertion")
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html;
	}

	public static String searchConvert(String tipo, String valor, int pageNumber) {
		final String internalFormat =			
			"<tr><td><center><a href='javascript:void(0)' onclick='viewMultimedia(%s);'><img src='%s' width='160' height='120' /></a></center></td></tr>\n" +
			"<tr><td><b>Nome: </b>%s</td></tr>\n" +
			"<tr><td><b>Autor: </b>%s</td></tr>\n" +
			"<tr><td><b>Descrição: </b>%s</td></tr>\n" +
			"<tr><td><b>Tags: </b>%s</td></tr>\n" +
			"<tr><td><b>Data de Inserção: </b>%s</td></tr>\n";
		final String internalLineDivisor = "<tr><td><hr size='1'/></td></tr>\n";

		final String MODEL_PATH = "http://localhost:8080/AmadeusLMS/modelSearch.html";
		String html = null;
		try {
			InputStream xmlStream = new URL(String.format(SEARCH_XML_PATH_FORMAT, tipo, valor, pageNumber)).openStream();
			Document xmlDoc = parseXmlFile(xmlStream);
			NodeList childNodes = xmlDoc.getFirstChild().getChildNodes();
			ArrayList<Hashtable<String, String>> table = new ArrayList<Hashtable<String,String>>();
			for (int i = 1; i < childNodes.getLength(); i += 2) {
				NodeList video = childNodes.item(i).getChildNodes();
				table.add(new Hashtable<String, String>());
				for (int j = 1; j < video.getLength(); j += 2) {
					Node item = video.item(j);
					String nodeName = item.getNodeName();
					String nodeValue = item.getFirstChild().getNodeValue();
					table.get((i-1)/2).put(nodeName, nodeValue);
				}
			}
			xmlStream.close();

			InputStream modelStream = new URL(MODEL_PATH).openStream();
			byte[] bytes = new byte[modelStream.available()];
			modelStream.read(bytes);
			modelStream.close();

			String param = new String(bytes);

			String concatInterno = "";
			String idObjeto;
			for (int i = 0; i < table.size(); i++) {
				Hashtable<String, String> hash = table.get(i);
				idObjeto = hash.get("id");
				String thumb = hash.get("thumbnail");
				thumb = (thumb != null) ? thumb : "http://localhost:8080/AmadeusSDMM/images/thumbnail1239.jpg";
				String subHtml = String.format(internalFormat, 
						idObjeto,
						thumb,
						hash.get("name"),
						hash.get("author"),
						hash.get("description"),
						hash.get("tags"),
						hash.get("date-of-insertion")
				);
				concatInterno += subHtml;
				if (i < table.size() - 1) {
					concatInterno += internalLineDivisor + "\n";
				}
			}
			html = String.format(param, concatInterno);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html;
	}

	private static Document parseXmlFile(InputStream inStream) {
		Document doc = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			doc = factory.newDocumentBuilder().parse(inStream);
		} catch (Exception e) { }
		return doc;
	}

	public static void main(String[] args) {
		String convert = ConvertXMLtoHTML.searchConvert("author","armando",-1);
		System.out.println(convert);
	}

}
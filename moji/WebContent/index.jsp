<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.http.impl.client.*,org.apache.http.client.entity.*,org.apache.http.*,org.apache.http.client.methods.*, java.io.*,java.util.*" %>
<%
 	String hotImagesUrl = "http://ugc.moji001.com/sns/GetBoutiquePictureFlow?UserID=16396308&Platform=Android&Version=10023702&BaseOSVer=10&PartnerKey=7777&Model=MB525&Device=phone&VersionType=1&DV=200&SnsID=5532435&Position=&Step=10";

	DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
		
	HttpGet get = new HttpGet(hotImagesUrl);
	

	get.setHeader("User-Agent", "Apache-HttpClient/UNAVAILABLE (java 1.4)");
	get.setHeader("Connection", " Keep-Alive");
	get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	get.setHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
	get.setHeader("Accept-Encoding", "gzip,deflate,sdch");
	get.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
	get.setHeader("Cache-Control", "max-age=60000");
	get.setHeader("Connection", "keep-alive");
	
	
	HttpResponse httpResponse = null;
	
	try {
		httpResponse = localDefaultHttpClient.execute(get);
	} catch (IOException ex) {
		ex.printStackTrace();
	}
	
	httpResponse.setEntity(new GzipDecompressingEntity(httpResponse.getEntity()));
	HttpEntity entity = httpResponse.getEntity();
	

	InputStream is = entity.getContent();
	Scanner sc = new Scanner(is);
	while (sc.hasNext()) {
	 System.out.println(sc.next());
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>热图</title>
</head>
<body>
33
</body>
</html>
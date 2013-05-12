<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.http.impl.client.*,org.apache.http.client.entity.*,org.apache.http.*,org.apache.http.client.methods.*, java.io.*,java.util.*" %>
<%@ page import="org.codehaus.jackson.*,org.codehaus.jackson.map.*" %>

<%!
	private String getReplayUrl (HttpServletRequest request) throws Exception{
		Map<String,String> param = new HashMap<String,String>(50);
		param.put("UserID","16296308");
		param.put("Platform","Android");
		param.put("Version","10023702");
		param.put("BaseOSVer","10");
		param.put("PartnerKey","5068");
		param.put("Model","MB525");
		param.put("Device","phone");
		param.put("VersionType","1");
		param.put("PicID",request.getParameter("picId"));
		param.put("PicSnsId",request.getParameter("picSnsId"));
		param.put("PicUserId",request.getParameter("picUserId"));
		param.put("SnsID","5452225"); //param.put("SnsID",request.getParameter("snsId"));
		param.put("Reply",new String(request.getParameter("replay").getBytes("ISO8859-1"),"UTF-8")); 
		param.put("CityID","333");
		

		
		StringBuffer sb = new StringBuffer(50);
		sb.append("http://ugc.moji001.com/sns/MakePictureReply?");
		for(Map.Entry<String,String> entry : param.entrySet()){
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		return sb.substring(0, sb.length()-1).toString();


	}


	private HttpEntity replay (String url) throws Exception{
		DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
		
		HttpGet get = new HttpGet(url);
		

		get.setHeader("User-Agent", "Apache-HttpClient/UNAVAILABLE (java 1.4)");
		get.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		
		
		HttpResponse httpResponse = null;
		
		try {
			httpResponse = localDefaultHttpClient.execute(get);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		httpResponse.setEntity(new GzipDecompressingEntity(httpResponse.getEntity()));
		return httpResponse.getEntity();
	}
%>
<%
	String url = getReplayUrl(request);
	System.out.println(url);
	HttpEntity entity =  replay(url);
	
	Scanner sc = new Scanner(entity.getContent());
	while (sc.hasNext()) {
		System.out.println(sc.next());
	}
	
%>


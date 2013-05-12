<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.http.impl.client.*,org.apache.http.client.entity.*,org.apache.http.*,org.apache.http.client.methods.*, java.io.*,java.util.*" %>
<%!
	private String getHotPictureUrl (){
		Map<String,String> param = new HashMap<String,String>(50);
		param.put("UserID","16396308");
		param.put("Platform","Android");
		param.put("Version","10023702");
		param.put("BaseOSVer","10");
		param.put("PartnerKey","7777");
		param.put("Model","MB525");
		param.put("Device","phone");
		param.put("VersionType","1");
		param.put("DV","200");
		param.put("SnsID","5532435");
		param.put("Position","");
		param.put("Step","20");
		
		StringBuffer sb = new StringBuffer(50);
		sb.append("http://ugc.moji001.com/sns/GetBoutiquePictureFlow?");
		for(Map.Entry<String,String> entry : param.entrySet()){
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		return sb.substring(0, sb.length()-1).toString();
	}


	private HttpEntity getEntity (String url) throws Exception{
		DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
		
		HttpGet get = new HttpGet(url);
		

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
		return httpResponse.getEntity();
	}
%>
<%
	String url = getHotPictureUrl();
	Object obj = session.getAttribute("preTime");
	String xml;
	
	if(request.getParameter("clearcache") != null){
		obj = null;
	}
	
	long time = new Date().getTime();
	long preTime =  obj == null ?0:(Long)obj;
	
	if( time - preTime >= 1000*60*3){
		System.out.println("refresh data");	
		Scanner sc = new Scanner(getEntity(url).getContent());
		StringBuffer sb = new StringBuffer(50);
		while (sc.hasNext()) {
			sb.append(sc.next()).append(" ");
		}
		
		session.setAttribute("preTime",new Date().getTime());
		session.setAttribute("xml", sb.toString());
	}
	xml = (String)session.getAttribute("xml");
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>热图</title>
<script type="text/javascript" src="js/jquery.js"></script>
<style type="text/css">
img {
	padding:3px;
	margin:2px;
	border: 1px solid white;
}

#hot-picture {
	float:left;
}
#comment{
	margin-left:600px;
}
img.select {
	border: 1px solid red;
}
</style>
</head>
<body>
<div>
	<a href="<%=url%>" target="_blank">xml 模型</a>
</div>
<div id="hot-picture">
</div>
<div id="comment">
		<form id="comment-form"  action="replay.jsp">
	<!-- 	<div>
			<label>snsId</label><input style="width:510px;" type="text" />
		</div> -->
		<div>
			<label>评论</label>
			<textarea name="replay" rows="20" cols="70"></textarea>
		</div>
		<div>
			<label>定时发送</label>
			<input type="text"  value="2013-05-12" name="date" >
			<select id="hour" name="hour" >
				<option value="00">00</option><option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option><option value="05">05</option><option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13" selected="">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option>
			</select>
			<select id="minute" name="minute" >
   		 		<option value="00">00</option><option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04" selected="">04</option><option value="05">05</option><option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option><option value="32">32</option><option value="33">33</option><option value="34">34</option><option value="35">35</option><option value="36">36</option><option value="37">37</option><option value="38">38</option><option value="39">39</option><option value="40">40</option><option value="41">41</option><option value="42">42</option><option value="43">43</option><option value="44">44</option><option value="45">45</option><option value="46">46</option><option value="47">47</option><option value="48">48</option><option value="49">49</option><option value="50">50</option><option value="51">51</option><option value="52">52</option><option value="53">53</option><option value="54">54</option><option value="55">55</option><option value="56">56</option><option value="57">57</option><option value="58">58</option><option value="59">59</option>
   		 	</select>
		</div>
		<div>
			<input id="form-submt"  type="button" value="提交">
		</div>
	</form>
</div>
<script type="text/javascript">
(function ($){
	var $xml = $($('<%=xml%>').get(1)),
		$hotpicutre = $("#hot-picture"),
		shuang = 1,
		imgs = '';
	
	
	$xml.find("picture").each(function (k,v){
		var $picture = $(v);
		
		imgs += '<img picId="'+$picture.attr("id")+'" picSnsId="'+$picture.attr("snsid")+'" picUserId="'+$picture.attr("userid")+'"  src="'+$picture.attr("purl")+'" />';
		
		if(shuang%2 === 0){
			$hotpicutre.append('<div class="images-wrapper">'+imgs+'</div>');
			imgs = '';
		}
		
		++shuang;
	});
	
	$hotpicutre.delegate("img","click",function (e){
		if ($(this).hasClass("select")){
			$(this).removeClass("select");
		}else{
			$(this).addClass("select");
		}
	});
	
	$("#form-submt").click(function (e){
		var $form = $("#comment-form"),
			 json = {
				replay : $form.find("textarea[name]").val(),
				//pics :[],
			};
		$hotpicutre.find("img.select").each(function (){
			var $this = $(this);
			/*json.pics.push({
				picId : $this.attr("picId"),
				picUserId :$this.attr("picUserId"),
				picSnsId :$this.attr("picSnsId")
			});*/
			json.picId = $this.attr("picId");
			json.picUserId = $this.attr("picUserId");
			json.picSnsId = $this.attr("picSnsId");
		});
		
		
		 $.ajax({
			url : "replay.jsp",
			method : "get",
			data : json,
			success: function (data){
				
			}
		}) ;
	});
	
})(jQuery);
</script>
</body>
</html>
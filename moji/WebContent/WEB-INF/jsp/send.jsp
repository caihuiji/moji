<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="common/resource.jsp" %>

</head>
<body >
	<div id="container">
		<%@ include file="common/header.jsp" %>
		<div id="main" class="clearfix">
			<div id="rightContent">
				<div class="userinfo">
					<img src="" title="" class="userimg">
					<div class="username">
						<a target="_blank" href="http://weibo.com/1213601622">白_芨</a>
					</div>
					<div class="mtm">
						<a href="http://sgj.feibo.cn/oauth/logout" class="mtl">注销</a>
					</div>
				</div>
			</div>
			<div id="mainContent">
				<div class="pal clearfix">
					<ul class="tabs clearfix">
						<li><a href="http://sgj.feibo.cn/app/post#" class="selected">发布图片</a></li>
						<!--<li><a href="#">批量发布</a></li>-->
					</ul>
					<div class="postWrap postForm ptl">
						<div class="postContent postbg">
							<div id="publisher_info_0" class="wordNumBg">
								<div class="surplus">
									你还可以输入<span class="pipsLim">140</span>字
								</div>
								<div class="over">
									已超出<span class="pipsLim">0</span>字
								</div>
							</div>
							<textarea id="publish_editor_0" class="publish_textarea"
								autocomplete="off"></textarea>
						</div>
						<div class="clearfix">
							<div class="postOption mvs">
								<ul>
									<!-- <li class="po_faces"><a id="po_faces_0"
										href="javascript:void(0)" class="OnTit"><i></i>表情</a></li> -->
									<li class="po_image"><a id="po_image_0"
										href="javascript:void(0)" class="OnTit"> <i></i>图片
											<form target="ifr_upload_img" enctype="multipart/form-data"
												method="post" autocomplete="off" action='file/uploadFile'
												>
												<div class="wrap">
													<input type="file" id="ajaxImagesUpload" class="iptFile" value=""
														name="fileToUpload" >
												</div>
											</form>
									</a></li>
									<li>
										<span class="uploading"></span>
									</li>
								<!-- 	<li class="po_video"><a id="po_video_0"
										href="javascript:void(0)" class="OnTit"><i></i>视频</a></li>
									<li class="po_music"><a id="po_music_0"
										href="javascript:void(0)" class="OnTit"><i></i>音乐</a></li>
									<li class="po_topic"><a id="po_topic_0"
										href="javascript:void(0)" class="OnTit"><i></i>话题</a></li> -->
								</ul>
							</div>
							<div class="rfloat">
								<button class="postTimeBtn uiBtnp mrs">
									<span>定时发送</span>
								</button>
								<button class="postNowBtn uiBtng">
									<span>立即发送</span>
								</button>
							</div>
						</div>
						<div class="date grayTip postTime clear">
							<em class="up"></em>
							<div class="wrap">
								定时发送时间： <input type="text" readonly="" id="pickdater_0"
									value="${currentDate}" name="date" class="hasDatepicker mrs"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:MM ',minDate:'%y-%M-%d'})"
									autocomplete="off"> <select id="hour_0" name="hour"
									autocomplete="off">
									<option value="00">00</option>
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23" >23</option>
								</select> : <select id="minute_0" name="minute" autocomplete="off">
									<option value="00">00</option>
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04" >04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
									<option value="30">30</option>
									<option value="31">31</option>
									<option value="32">32</option>
									<option value="33">33</option>
									<option value="34">34</option>
									<option value="35">35</option>
									<option value="36">36</option>
									<option value="37">37</option>
									<option value="38">38</option>
									<option value="39">39</option>
									<option value="40">40</option>
									<option value="41">41</option>
									<option value="42">42</option>
									<option value="43">43</option>
									<option value="44">44</option>
									<option value="45">45</option>
									<option value="46">46</option>
									<option value="47">47</option>
									<option value="48">48</option>
									<option value="49">49</option>
									<option value="50">50</option>
									<option value="51">51</option>
									<option value="52">52</option>
									<option value="53">53</option>
									<option value="54">54</option>
									<option value="55">55</option>
									<option value="56">56</option>
									<option value="57">57</option>
									<option value="58">58</option>
									<option value="59">59</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<!--
<div style="margin:0 50px; border:1px solid #FFCB67; background:#FEF9E8; padding:10px;">因配合新浪微博api升级，本时光机应用将于2012-08-31下午4：00 进行升级，
为保证为您提供更好的服务，升级后请您重新授权本时光机。</div>
-->
				<div class="pbl clear">
					<div class="postrecordWrap">
						<table width="100%" class="precordGrid uiGrid girdS1">
							<tbody>
								<tr>
									<td class="first">2013-05-04 12:01</td>
									<td class="hLeft">
										<div class="precord_index_text">
											为什么Facebook是蓝色的——从最初到最新的版本，几经设计更迭，始终不变的主色调都是蓝色？想知道答案就看看吧！
											<div class="mts">
												<a target="_blank" class="tinyico-img fcmb"
													href="http://sgj.feibo.cn/wbimg/2013/05/04/20130504113120gqhart.jpg">查看图片</a>
											</div>
										</div>

									</td>
									<td>发布成功
										<div>
											<a class="fcmb tinyico-view" target="_blank"
												href="http://api.t.sina.com.cn/1213601622/statuses/3574203888465982">查看微博</a>
										</div>
									</td>
									<td>
										<div>
											<a class="fcmb tinyico-delmsg"
												onclick="del_posted_status_ajax(this,4204266)">删除微博</a>
										</div>
										<div>
											<a class="fcmb tinyico-delrecord"
												onclick="del_post(this,4204266)">删除记录</a>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<%@include file="common/footer.jsp" %>
	</div>
<script src="js/publishmoji.js"></script>
</body>
</html>
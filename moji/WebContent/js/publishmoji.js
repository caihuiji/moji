$(function (){
	
	window.SendMoji = function (){
		this.initialize();
		
	};
	
	SendMoji.prototype = {
		
		_$container : null ,
		_$postTimeBtn : null,
		_$postNowBtn : null,
		_$publish_textarea : null,
		_$uploadImage : null,
		_$uploading : null,
		_$date : null,
		_$hour : null,
		_$minute : null,
		
		data : {
			time : null,
			image : null,
			text : null,
		},
		
		
		initialize : function (){
			this._$container = $(".postForm");
			this._$postTimeBtn = this._$container.find(".postTimeBtn");
			this._$postNowBtn  = this._$container.find(".postNowBtn");
			this._$publish_textarea = this._$container.find(".publish_textarea");
			this._$date = this._$container.find("#pickdater_0");
			this._$hour = this._$container.find("#hour_0");
			this._$minute = this._$container.find("#minute_0");
			this._$uploading = this._$container.find(".uploading");
			
			this.attachEvent();
		},
		
		generateDate : function (){
			var currentDate = this._$date.val() +" "+ this._$hour.val() +":"+ this._$minute.val()+":00";
			console.log(currentDate);
			return currentDate;
		},
		
		sendTime : function (e){
			alert("sendTime");
		},
		
		
		sendNow : function (e){
			alert("sendNow");
		},
		
		attachEvent : function (){
			var self = this;
			this._$postTimeBtn.click(function (e){
				self.validate() && self.sendTime(e) ;
			});
			
			this._$postNowBtn.click(function (e){
				self.sendNow(e);
			});
			
			var listener = function (){
				self._$uploadImage = self._$container.find("#ajaxImagesUpload");
				self._$uploadImage.off("change").change(function (e){
					self.updateImages();
				});
				
				setTimeout(function (){
					listener();
				},100);
			};
			
			listener();
			
		},
		
		validate : function (){
			if (new Date().getTime() <= new Date(this.generateDate()).getTime()){
				alert("时间过期");
				return false;
			}
			return true;
		},
		
		
		updateImages : function (){
		        var self = this;
		        
		        
		        $.ajaxFileUpload
		        (
		            {
		            	url:'file/uploadFile',
						secureuri:false,
						fileElementId:'ajaxImagesUpload',
						dataType: 'json',
						ajaxSend:function()
						{
							self._$uploading.text("正在上传...");
						},
						ajaxComplete:function()
						{
						
						},			
						success: function (data, status)
						{
							if(data.status == 'error')
							{
									alert("上传文件失败!");
							}
							self.data.path = data.path;
							self._$uploading.html('上传成功(<a target="_blank" href="'+data.path+'">查看图片</a>)');
						
						},
						error: function (data, status, e)
						{
							alert(e);
						}
		            }
		        );
		}
		
	};
	
	new SendMoji();
	
})
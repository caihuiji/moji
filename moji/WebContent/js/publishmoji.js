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
		
		_$date : null,
		_$hour : null,
		_$minute : null,
		
		
		initialize : function (){
			this._$container = $(".postForm");
			this._$postTimeBtn = this._$container.find(".postTimeBtn");
			this._$postNowBtn  = this._$container.find(".postNowBtn");
			this._$publish_textarea = this._$container.find(".publish_textarea");
			this._$date = this._$container.find("#pickdater_0");
			this._$hour = this._$container.find("#hour_0");
			this._$minute = this._$container.find("#minute_0");
			this._$uploadImage = this._$container.find(".iptFile");
			
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
		},
		
		validate : function (){
			if (new Date().getTime() <= new Date(this.generateDate()).getTime()){
				alert("时间过期");
				return false;
			}
			return true;
		}
		
	};
	
	new SendMoji();
	
})
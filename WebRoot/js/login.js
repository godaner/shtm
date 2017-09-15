/**
 * index界面的js
 */

/**
 * 变量申明
 */
var verifyCodeImg;
var loginBtn;
var loginForm;
$(function(){
	
	initVar();
	
	loadUI();
	
	/**
	 * 监听事件
	 */
	initLis();
	
	
	
	

	/**
	 * 初始化变量
	 */
	function initVar(){
		verifyCodeImg = $("#verifyCodeImg");
		loginBtn = $("#loginBtn");
		loginForm = $('#loginForm');
	}
	/**
	 * 加载界面
	 */
	function loadUI(){

	}
	/**
	 * 监听事件
	 */
	function initLis(){
		/**
		 * 点击验证码刷新
		 */
		verifyCodeImg.on("click",function(){
			verifyCodeImg.attr("src",getWebProjectName()+"/admins/verifyCode.action?t="+(new Date().getTime()));
			
		});
		/**
		 * 点击登录按钮
		 */
		loginBtn.on("click",function(){
			loginForm.form('submit', {    
				url:getWebProjectName()+"/admins/login.action?t="+(new Date().getTime()),
			    onSubmit: function(){    
			    	return loginForm.form('validate');  
			    },    
			    success:function(data){    
			    	a(data)
			    }    
			});   
		});
		

		
	}
	
	
	
});


;(function(window){
function isProd(b){
	var domainManager;
	if(b){
	//prod
	 domainManager={
		SevenSeconds:'www.newzhuan.cn',	
		Article:'www.newzhuan.cn',	
		BookStore:'book.newzhuan.cn',	
		ConvertMall:'mall.newzhuan.cn',	
		ActivityCenter:'activity.newzhuan.cn',
		AppCenter:'app.newzhuan.cn',
		FinancialCenter:'fin.newzhuan.cn',
		ScheduleJob:'task.newzhuan.cn',	
		Push:'push.newzhuan.cn',	
		MyWangEditor:'img.newzhuan.cn',	
		};
	return domainManager;
	}else{
	//dev
	domainManager={
		SevenSeconds:'192.168.7.111',	//七秒服务器
		Article:'192.168.7.111',	//文章服务器
		BookStore:'192.168.7.111:8082',	//书城服务器
		ConvertMall:'192.168.7.111:8081',	//兑换商城服务器
		ActivityCenter:'192.168.7.111:8083',	//活动中心服务器
		AppCenter:'192.168.7.111:8084',	//游戏中心服务器
		FinancialCenter:'192.168.7.111:8085',//金融中心服务器
		ScheduleJob:'192.168.7.111:8001',	//任务计划服务器
		Push:'192.168.7.111:8002',	//推送服务器
		MyWangEditor:'img.newzhuan.cn',	//图片服务器
		};
	}
	return domainManager;
}
//window.domainManager=isProd(true);//生产
window.domainManager=isProd(false);//本地
})(window);
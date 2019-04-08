//定义业务根变量；
var business={
	//私有对象放置地
	self:{
		 //存放全局传递参数
		 attribute:{},
		//记录上次的元素
		 elewrap:null,
		//显示增加model 
		//text标题，elewrap元素包裹，element元素
		 showModel:function(text,elewrap,element){
			$("#mySellerModal").click();
			$("#mySellerModalLabel").text(text);
			console.log($("#mySellerModalBody").html())
			//还原上次的元素
			$(business.self.elewrap).html($("#mySellerModalBody").html());
			//包裹元素，原来的元素消失
			$("#mySellerModalBody").html($(element));
			//记录本次元素包裹
			business.self.elewrap=elewrap;
		 },
		 
	},
	//定义全局域名
	domainUrl:	myUtils.getDomain(),
	//全局登录账户id	
	sessionAccountId:null,
	//全局登录账户	
	sessionAccount:null,
	//全局登录账户
	sessionPhone:"",
	//全局登录角色id
	sessionRoleId:null,
	//全局登录角色
	sessionRole:{},
	//全局所有角色
	roleList:[],
	//请求原始参数初始
	originParams:{
	      startNum:1,//初始化个数
	      currentPage:1,//当前页
	      pageNum:1,//获取的第几个开始
	      pageSize:10,//每页的个数
	      total:0//总数
	 },
	//请求参数初始
	params:{
	      startNum:1,//初始化个数
	      currentPage:1,//当前页
	      pageNum:1,//获取的第几个开始
	      pageSize:10,//每页的个数
	      total:0//总数
	 },
	//验证是否已经登录
	initIslogin:function(){
		$.ajax({
			url:business.domainUrl+"/account/islogin",
			type:"get",
			async:false,
			success:function(data){
				console.log(data);
				if(data.code==200){
					business.sessionAccount=data.data[0];
					business.sessionRole=data.data[0].role;
					business.sessionPhone=data.data[0].phone;
					business.sessionAccountId=data.data[0].accountId;
					business.sessionRoleId=data.data[0].roleId;
					delete business.sessionAccount.role
				$(".name_top a").html(business.sessionAccount.realname||business.sessionPhone);
				}else{
					location.replace(business.domainUrl+"/login.html");
				}
			}	
		});
	},
	//获取所有角色
	initRoleList:function(){
		$.ajax({
			url:business.domainUrl+"/role/list",
			type:"get",
			async:false,
			success:function(data){
				if(data.code==200){

					business.roleList=data.data;
				for(var i=0;i<business.roleList.length;i++){
					if(business.roleList[i].roleId==business.sessionRoleId){
						business.sessionRole=business.roleList[i];
					$(".role_name").text(business.sessionRole.name);
					}
					//给定角色的权限
				}
                    console.log(business.sessionRole.name)
				setInterval(function(){
				//权限默认不显示
				$(
				"#accountListMenu," +
				"#accountMenu"
				).hide();
				//一级导航级别
				//所有可见，自身账户
                    $("#accountMenu").css("display","block");
				//仅管理员可见
                if(business.sessionRole.name=="超级管理员"){
                    $("#accountListMenu").css("display","block");
				}
				//仅管理员可见
				if(business.sessionRole.name!="超级管理员"){
                    business.load({
                        url:"/account/loginout?accountId="+business.sessionAccount.accountId,
                        success:function(){
                            location.href="/";
                        }
                    })
				}
				},100);
				
				}
			}	
		});
	},
	 //点击左边导航
	clickLeftMenu:function (item,$this){
	 	//location.href=business.domainUrl+"/main.html?uri=/main/"+item+".html";
		//标记是从菜单栏来的
        business.self.attribute.isFromMenu=true;
		$(".content_main").load(business.domainUrl+"/main/"+item+".html");
	 },
	 
	 //分页条信息
	showPage:function(params,list){
		 $(".page_font").empty();
			var totalPage=params.totalPage;
			var nowPage=params.currentPage;
			var pageSize=params.pageSize;
			var totalSize=params.total;
			var pagination=[];
			
			pagination=myUtils.myPaginationHandler().getShowPageNumber(totalPage,params.pageNum,5,nowPage)
			//显示分页文字信息 
			$(".page_font").append("总共"
					+totalPage+"页，当前第"
					+nowPage+"页，每页"
					+pageSize+"条数据，总共"
					+totalSize+"条数据");
			
		    //显示分页条信息	
		    $("#di ul").empty();
		    var firstPage=$("<li></li>").append("<a href='#' aria-label='Previous'><span aria-hidden='true'>首页</span>");
		    var lastPage=$("<li></li>").append("<a href='#' aria-label='Previous'><span aria-hidden='true'>尾页</span>");
		    
		    var previousPage=$("<li></li>").append("<a href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span>");
			var nextPage=$("<li></li>").append("<a href='#' aria-label='Previous'><span aria-hidden='true'>&raquo;</span>");
			
			//添加首页、上一页 
			$("#di ul").append(firstPage)
	 		   		   .append(previousPage)
	 		//设置如果是第一页：那么首页和上一页不可点击，最后一页：那么尾页和下一页不可点击 	
	  		if(nowPage==1){
				firstPage.addClass("disabled");
				previousPage.addClass("disabled");
			}   

			if(nowPage==totalPage){
				nextPage.addClass("disabled");
				lastPage.addClass("disabled");
			}   		   
	 		//为首页，上一页，下一页，尾页 添加点击事件  
					firstPage.click(function(){
						if(nowPage!=1){
							business.toPage(1,list);
						}
					});
					previousPage.click(function(){
						if(nowPage-1>0){
							business.toPage(nowPage-1,list);
						}
					});
					nextPage.click(function(){
						if(nowPage+1<=totalPage){
							business.toPage(nowPage+1,list);
						}
					});
					lastPage.click(function(){
						if(nowPage!=totalPage){
							business.toPage(totalPage,list);
						}
					});
		    //获取每页显示的页码 
	 		$.each(pagination,function(i,e){
	 			var pages=$("<li></li>").append("<a href='#'>"+e+"</a>");
	 			$("#di ul").append(pages);
	 			if(nowPage==e){
	 				pages.addClass("active");
	 			}
	 			pages.click(function(){
	 				business.toPage(e,list);
				}); 
	 		});  
	 		   		   
	  		$("#di ul").append(nextPage)
			   		   .append(lastPage); 	
	 },
	 //初始化分页
	 initPage:function (list){
		 //初始化参数初始化，防止不同数据混淆
		 business.params={
		      startNum:1,//初始化个数
		      currentPage:1,//当前页
		      pageNum:1,//获取的第几个开始
		      pageSize:10,//每页的个数
		      total:0//总数
		 },
		 //显示数据后获取总页数
		list(function(){
			business.params.totalPage=(business.params.total)%(business.params.pageSize)==0?(business.params.total)/(business.params.pageSize):parseInt((business.params.total)/(business.params.pageSize))+1,//总页数
			business.showPage(business.params,list);			
		})
	},
	//分页页面跳转
	toPage:function(currentPage,list){
		//获取当前页
		business.params.currentPage=currentPage;
		//获取当前数
		business.params.pageNum=(business.params.currentPage-1)*business.params.pageSize+business.params.startNum;
		list(function(){
		business.showPage(business.params,list);
		})
	},	
	 //退出 
	 loginout:function(){
		myUtils.myLoginOut("确定退出吗？", function() {
			/**
		     * 获取单个
		     * p.url 列表url
		     * p.data 返回列表
		     * p.success 成功回调
		     */
			business.load({
				url:"/account/loginout?accountId="+business.sessionAccount.accountId,
				success:function(){
					location.href="/";
				}
			})
		})
	 },
	 /**
     * 获取列表
     * p.countUrl 数量url
     * p.listUrl 列表url
     * p.data 返回列表
     * params 请求参数
     * params.total 总数
     * p.success 成功回调
     */
	 getList:function(p,params){
		 //是否清空
		 p.isClear=p.isClear==false?false:true;
		 $.ajax({
				url:p.countUrl,
				type:"post",
				async:p.async==true?true:false,
				data:params,
	            withCredentials: true,
				success:function(data){
				if(data.code==200){
					//console.log(data.data.total)
					params.total=data.data[0];
					if(p.isClear&&params.total<=0 ){
						//myUtils.myLoadingToast("暂无数据")
						//清空table body
						$("#tableTbody").html("");
						//隐藏分页
						$(".page_div").css("display","none");
						//放入全局business
						var tableTbody="<div class='text-center' id='noList'>暂无数据</div>";
						if(!$("#noList")[0]){
							$("table").after(tableTbody);							
						}
		                return ;
		            }else{
		            	//去掉显示的
		            	$("#noList").remove();
		            	//显示分页
						$(".page_div").css("display","block");
		            }
					$.ajax({
						url:p.listUrl,
						data:params,
						async:p.async==true?true:false,
						withCredentials: true,
						type:"post",
						success:function(data){
							if(data.code==200){
								//myUtils.myLoadingToast(data.msg);
								business[p.data]=data.data;
								  if(typeof p.success=='function'){
				                         p.success(business[p.data]);
				                        }
									}else{
										//myUtils.myLoadingToast("暂无数据")
									}
						},
						error:function(){
							myUtils.myLoadingToast("异常")
						}	
					});
				}
				},
				error:function(){
					myUtils.myLoadingToast("异常")
				}
			});
	 },

	/**
     * 获取单个
     * p.url 列表url
     * p.data 返回列表
     * p.success 成功回调
     */
	 load:function (p) {
        //获取
    	$.ajax({
            method:"post",
            url:p.url,
            withCredentials: true,
            success: function(data){
            	if (data.code == 200) {
                    //console.log(data.data)
            		//myUtils.myLoadingToast(data.msg);
                    //属性获取
                    for(var name in data.data){
                    	business[p.data]=name;
                    }
                    if(typeof p.success=='function'){
                        p.success(business[p.data]);
                    }
                } else {
                	myUtils.myLoadingToast("暂无数据")
                }
            },
			error:function(){
				myUtils.myLoadingToast("异常")
			}
            });
	   },
	   /**
	     * 增加
	     * p.addUrl 增加url
	     * p.requestObject 请求参数对象
	     * p.success 成功回调
	     */
	    add:function(p){
	    	$.ajax({
	              method:"post",
	              url:p.url,
	              data:business[p.requestObject],
	              withCredentials: true,
	              success:function(data){
	            	  if (data.code === 200) {
	            		  myUtils.myLoadingToast(data.msg);
	                      if(typeof p.success=='function'){
	                          p.success();
	                      }
	                } else {
	                	myUtils.myLoadingToast(data.msg)
	                }  
	              },
					error:function(){
						myUtils.myLoadingToast("异常")
					}
	              })
	    },
	    /**
	     * 修改
	     * p.url 修改url
	     * p.requestObject 请求参数对象
	     * p.success 成功回调
	     */
	    update:function(p){
	    	$.ajax({
                method:"post",
                url:p.url,
                data:business[p.requestObject],
                withCredentials: true,
                success:function(data){
            	 if (data.code === 200) {
            		 myUtils.myLoadingToast(data.msg);
                     if(typeof p.success=='function'){
                         p.success();
                     }
                }else{
                	myUtils.myLoadingToast(data.msg)
                }
            },
			error:function(){
				myUtils.myLoadingToast("异常")
			}
	    	});
	    },
	    /**
	     * 删除
	     * p.url 修改url
	     * p.requestObject 请求参数对象
	     * p.success 成功回调
	     */
	    delete:function(p){
	    	myUtils.myConfirm("确定删除吗？", function() {
	    		$.ajax({
					method:"post",
					url:p.url,
					data:business[p.requestObject],
					withCredentials: true,
					success:function(data){
						if (data.code === 200) {
							myUtils.myLoadingToast(data.msg);
							if(typeof p.success=='function'){
								p.success();
							}
						}else {
							myUtils.myLoadingToast(data.msg);
						}
					},
					error:function(){
						myUtils.myLoadingToast("异常")
					}
				})
			})
	 },
};
	//初始化判断是否登陆
	business.initIslogin();
	//初始化所有角色
	business.initRoleList();
	//初始化左边导航
	$('#leftBar').metisMenu();
	//首页初始化
	business.clickLeftMenu('index');	
	
    
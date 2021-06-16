var login=new Vue({
	el:"#yc_login",//对应div的id,即这个vue的作用域
	data:{
		onlogin:false,
		outlogin:true,
		loginName:'匿名',
		loginId:"",//记录登录用户的id
		carts:{},
		goodsCount:0
	},
	mounted:function(){//数据挂载之前，相当于jquery中的$(function(){}),页面加载完成
		axios({
			url:'menber.action?op=check',
			method:'get'
		}).then(result=>{
			if(result.data.menber){//说明登录了
				this.onlogin=true;
				this.outlogin=false;
				this.loginName=result.data.menber.nickName;
				this.loginId=result.data.menber.mno;//将登录用户的会员编号存储在loginId中
				if(result.data.carts){
					this.carts=result.data.carts;
					this.goodsCount=result.data.carts.length;
				}
			}else{
				this.onlogin=false;
				this.outlogin=true;
				alert('请先登录。。。');
				location.href='login.html';
			}
		});
	}
});



















<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<title>资产库存管理系统登录界面</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
 	<link href="${pageContext.request.contextPath }/others/bootstrap3/font/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/others/bootstrap3/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/others/bootstrap3/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/others/bootstrap3/font/css/templatemo_style.css" rel="stylesheet" type="text/css"> 	
	
	<script type="text/javascript">
		function checkForm(){
			var userName = document.getElementById('username').value;
			var passWord = document.getElementById('password').value;
			var flag = true;
			if(userName=="" || userName==null){
				document.getElementById('tip').innerHTML = "用户名不能为空".fontcolor('red');
				flag = false;
			}
			else if(passWord=="" || passWord==null){
				document.getElementById('tip').innerHTML = "密码不能为空".fontcolor('red');
				flag = false;
			}
			return flag;
		}
		
		function resetValue(){
			document.getElementById('username').value = "";
			document.getElementById('password').value = "";
		}
		
		function removeTip(){
			document.getElementById('tip').innerHTML = "";
		}
		
	</script>
</head>
<body class="templatemo-bg-gray">
	<div style="background-color: #008EAD;height:360px;"></div>
	<div class="container" >
	   <div class="col-md-12" style="margin-top: -200px;padding-left:330px;" >
	   								
		 <div style="background-color: white;width:500px;border-radius:7px;">
			<form onsubmit="return checkForm()" action="${pageContext.request.contextPath }/user/login.html" method="post" class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" >
		        <div class="form-group" >
		          <div class="col-xs-12">
		            <div class="control-wrapper">
		            	<label for="username" class="control-label fa-label"><i class="fa fa-user fa-medium"></i></label>
		            	<input autocomplete="off" type="text" class="form-control" id="username" name="userName" value="${user.userName }" onfocus="removeTip() " placeholder="请输入您的用户名">
		            </div>		            	            
		          </div>              
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		            	<label for="password" class="control-label fa-label"><i class="fa fa-lock fa-medium"></i></label>
		            	<input type="password" class="form-control" id="password" name="passWord" value="${user.passWord }" onfocus="removeTip() " placeholder="请输入您的密码">
		            </div>
		          </div>
		        </div>
		        <div class="form-group">
		          <%-- <div class="col-md-12">
	             	<div class="checkbox control-wrapper">
						<div style="height:10px;"></div>
		          		<div style="margin-top: -27px;"><a style="outline: none" href="${pageContext.request.contextPath }/index/goFindpwd.html" class="text-right pull-right" target="_bank">找回密码</a></div>
	              	</div>
		          </div> --%>
		        </div>
		        <hr>
		        <div class="form-group" style="margin-top: -10px;">
		          <div class="col-md-12" >
		          	<span style="padding-left:20px;" id="tip"><font color="red">${errorMsg }</font></span>
		          	<div style="padding-left: 380px;margin-top:-20px;">
		          		<input style="outline: none" type="submit" value="登陆" class="btn btn-primary" >
		          	</div>
		          	<div style="padding-left: 300px;margin-top:-33px;">
		          		<input style="outline: none" type="button" value="重置" onclick="resetValue()" class="btn btn-default" >
		          	</div>
		          </div>
		          </div>
		        </div>
		      </form>
		   </div>
	</div>
</body>
</html>
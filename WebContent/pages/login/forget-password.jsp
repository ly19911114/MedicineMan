<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<link href="${pageContext.request.contextPath }/others/bootstrap3/font/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/others/bootstrap3/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/others/bootstrap3/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/others/bootstrap3/font/css/templatemo_style.css" rel="stylesheet" type="text/css"> 	
<title>找回密码</title>
	<script type="text/javascript">
		
		function checkEmail(){
			var email = document.getElementById('email').value;
			var flag = true;
			if(email==""){
				document.getElementById('tip').innerHTML = "请输入您的用户信息里的邮箱地址".fontcolor('red');;
				flag = false;
			}else{
				var regex = new RegExp("^\\w+@\\w+(\\.\\w+)+$");
				if(regex.test(email)){
				
				}else{
					document.getElementById('tip').innerHTML = "请输入正确的邮箱格式".fontcolor('red');
					flag = false;
				}
			}
			return flag;
		}
		
		function removeTip(){
			document.getElementById('tip').innerHTML = "";
		}
	</script>
</head>
<body class="templatemo-bg-gray">
	<div class="container">
		<div class="col-md-12" style="padding-top: 150px;">
			<form action="${pageContext.request.contextPath }/user/findpwd.html" method="post" onsubmit="return checkEmail()"  class="form-horizontal templatemo-forgot-password-form templatemo-container" role="form" >	
				<div class="form-group">
		          <div class="col-md-12">
		          	请输入您的用户信息里填写的邮箱地址，以便找回你的用户密码.&nbsp;&nbsp;&nbsp;
		          </div>
		        </div>		
		        <div class="form-group">
		          <div class="col-md-12">
		            <input type="text" autocomplete="off" name="email" value="${u.email }" class="form-control" id="email" placeholder="请输入您的用户信息里绑定的的邮箱地址" onfocus="removeTip() ">
		          </div>              
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	&nbsp;&nbsp;&nbsp;<span id="tip"><font color='red'>${Msg }</font></span>
                    <button type="submit" class="btn btn-primary" style="float: right;outline: none" onclick="checkEmail()">
  						<span class="glyphicon glyphicon-send"></span>&nbsp;发送
					</button>
		          </div>
		        </div>
		      </form>
		</div>
	</div>
</body>
</html>
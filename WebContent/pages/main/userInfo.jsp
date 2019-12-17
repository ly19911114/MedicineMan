<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

	<style type="text/css">
		th{
			text-align: right;
			background-color: #EFEFEF;
		}
		.table tr{
			height:40px;
		}
	</style>

	<script type="text/javascript">
		
		var url;
	
		function updateInfo(){
			$("#dlg").dialog("open").dialog("setTitle","修改个人信息");
			url = "${pageContext.request.contextPath }/user/update_personal.html?id=${currentUser.id}";
		}
		
		function saveUser_personal(){
			$("#fm").form("submit",{
				url:url,
				onSubmit:function(){
					if($("#sex").combobox("getValue")==""){
						$.messager.alert("系统提示","<font size='3' color='green'>请选择性别!<font>");
						return false;
					}
					return $(this).form("validate");
				},
				success:function(result){
					var result=eval('('+result+')');
					if(result.success){
						$("#dlg").dialog("close");
	 					$.messager.show({
							title:'系统提示',
							msg:"<font size='3' color='green'>修改个人信息成功!!</font>",
							timeout:2000,
							showType:'slide',
							width:300,
							height:150,
							style:{
								top:'',
								left:'',
								right:0,
								bottom:document.body.scrollTop+document.documentElement.scrollTop
							}
						});
	 					window.setTimeout(function(){
	 						window.location.reload();
	 					}, 2000)
					}else{
	 					$.messager.show({
							title:'系统提示',
							msg:"<font size='3' color='green'>修改个人信息失败</font>",
							timeout:2000,
							showType:'slide',
							width:300,
							height:150,
							style:{
								top:'',
								left:'',
								right:0,
								bottom:document.body.scrollTop+document.documentElement.scrollTop
							}
						});
						return;
					}
				}
			});
		}
		
		function closeUserDialog(){
			$("#dlg").dialog("close");
		}
	</script>
</head>
<body style="margin:1px;">
<center>
<div style="padding-left: 30px;padding-top: 20px;">
	<div id="p" class="easyui-panel" title="个人详细信息" style="width:900px;height: 400px;">
		<div class="table" style="padding-left:23px;padding-top:40px;">
		  <table width="840" border="1" bordercolor="#CBCBCB" cellpadding="5" cellspacing="1">
	        <col width="110" />
	        <col width="140" />
	        <col width="110" />
	        <col width="140" />
	        <tr>
	          <th>真实姓名：</th>
	          <td colspan="3">&nbsp;&nbsp;${currentUser.trueName }</td>
	        </tr>
	        <tr>
	          <th>性别：</th>
	          <td>&nbsp;&nbsp;${currentUser.sex }</td>
	          <th>身份证号：</th>
	          <td>&nbsp;&nbsp;${currentUser.IDCard }</td>
	        </tr>
	        <tr>
	          <th>用户名：</th>
	          <td>&nbsp;&nbsp;${currentUser.userName }</td>
	          <th>密码：</th>
	          <td>&nbsp;&nbsp;${currentUser.passWord }</td>
	        </tr>    
	        <tr>
	          <th>电子邮箱：</th>
	          <td>&nbsp;&nbsp;${currentUser.email }</td>
	          <th>联系电话：</th>
	          <td>&nbsp;&nbsp;${currentUser.phone }</td>
	        </tr>
	        <tr>
	          <th>当前身份：</th>
	          <td>&nbsp;&nbsp;${currentUser.identity.identityName }</td>
	          <th>联系地址：</th>
	          <td>&nbsp;&nbsp;${currentUser.address }</td>
	       </tr>  
	   </table>
	   </div>
	   <center>
		  <div style="padding-top: 20px;">
			  <a id="btn" href="javascript:updateInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">修改信息</a>  
		  </div>
	   </center>
	</div>
</div>
</center>
 	<div id="dlg" class="easyui-dialog" style="width: 570px;height:300px;padding: 10px 20px"
	  closed="true" buttons="#dlg-buttons">
	 	<form id="fm" method="post">
	 		<table cellspacing="8px">
	 			<tr>
	 				<td>真实姓名：</td>
	 				<td><input type="text" id="trueName" name="trueName" class="easyui-validatebox" required="true" value="${currentUser.trueName }"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>用户名：</td>
	 				<td><input type="text" id="userName" name="userName" class="easyui-validatebox" required="true" value="${currentUser.userName }"/></td>
	 			</tr>
	 			<tr>
	 				<td>身份证：</td>
	 				<td><input type="text" id="IDCard" name="IDCard" class="easyui-validatebox" required="true" value="${currentUser.IDCard }" readonly="readonly"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>身份：</td>
	 				<td><input type="text" id="identity" class="easyui-validatebox" required="true" value="${currentUser.identity.identityName }" readonly="readonly"/></td>
	 			</tr>
	 			<tr>
	 				<td>密码：</td>
	 				<td><input type="text" id="passWord" name="passWord" class="easyui-validatebox" required="true" value="${currentUser.passWord }"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>性别：</td>
	 				<td>
	 					<select class="easyui-combobox" id="sex" name="sex" style="width: 143px;" editable="false" panelHeight="auto">
	 						<option value="">请选择性别</option>
	 						<option value="男">男</option>
	 						<option value="女">女</option>
	 					</select>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>邮件：</td>
	 				<td><input type="text" id="email" name="email" class="easyui-validatebox" validType="email" required="true" value="${currentUser.email }"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>联系电话：</td>
	 				<td><input type="text" id="phone" name="phone" class="easyui-validatebox" required="true" value="${currentUser.phone }"/></td>
	 			</tr>
	 			<tr>
	 				<td>联系地址：</td>
	 				<td colspan="4">
	 					<input type="text" id="address" name="address" class="easyui-validatebox" required="true" style="width: 385px;" value="${currentUser.address }"/>
	 				</td>
	 			</tr>
	 		</table>
	 	</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveUser_personal()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
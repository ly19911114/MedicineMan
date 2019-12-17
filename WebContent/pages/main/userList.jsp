<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	
	var url;
	
	function searchUser(){
		$("#dg").datagrid('load',{
				"trueName":$("#s_trueName").val()
			});
		}
	
	function deleteUser(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","<font size='3' color='green'>请选择要删除的数据！<font>");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath }/user/deleteUser.html",{ids:ids},function(result){
					if(result.success){
						$("#dg").datagrid("reload");
	 					$.messager.show({
							title:'系统提示',
							msg:"<font size='3' color='green'>该用户已删除成功！</font>",
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
					}else{
	 					$.messager.show({
							title:'系统提示',
							msg:"<font size='3' color='green'>删除该用户失败</font>",
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
					}
				},"json");
			}
		});
		
	}
	
	
	function openUserAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加用户信息");
		url = "${pageContext.request.contextPath }/user/saveUser.html";
	}
	
	
	function addUser(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				if($("#identity").combobox("getValue")==""){
					$.messager.alert("系统提示","<font size='3' color='green'>请选择对应的身份!<font>");
					return false;
				}
				if($("#sex").combobox("getValue")==""){
					$.messager.alert("系统提示","<font size='3' color='green'>请选择性别!<font>");
					return false;
				}
				return $(this).form("validate");
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.success){
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
 					$.messager.show({
						title:'系统提示',
						msg:"<font size='3' color='green'>添加用户信息成功</font>",
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
				}else{
 					$.messager.show({
						title:'系统提示',
						msg:"<font size='3' color='green'>添加用户信息失败</font>",
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
	
	function updateUser(){
		$("#fm2").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.success){
					resetValue();
					$("#dlg2").dialog("close");
					$("#dg").datagrid("reload");
 					$.messager.show({
						title:'系统提示',
						msg:"<font size='3' color='green'>修改用户信息成功</font>",
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
				}else{
 					$.messager.show({
						title:'系统提示',
						msg:"<font size='3' color='green'>修改用户信息失败</font>",
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
	
	function openUserModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","<font size='3' color='green'>请选择一条要编辑的数据！<font>");
			return;
		}
		var row=selectedRows[0];
		$("#dlg2").dialog("open").dialog("setTitle","编辑用户信息");
		$("#trueName2").val(row.trueName);
		$("#userName2").val(row.userName);
		$("#passWord2").val(row.passWord);
		$("#sex2").combobox("setValue",row.sex);
		$("#identity2").combobox("setValue",row.identity.id);
		$("#IDCard2").val(row.IDCard);
		$("#email2").val(row.email);
		$("#phone2").val(row.phone);
		$("#address2").val(row.address);
		url="${pageContext.request.contextPath }/user/update.html?id="+row.id;
	}
	
	function resetValue(){
		$("#trueName").val("");
		$("#userName").val("");
		$("#passWord").val("");
		$("#sex").combobox("setValue","");
		$("#IDCard").val("");
		$("#email").val("");
		$("#phone").val("");
		$("#address").val("");
		$("#identity").val("");
		$("#dept").val("");
	}
	
	function closeUserDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function closeUpdateUserDialog(){
		$("#dlg2").dialog("close");
	}
</script>
</head>
<body style="margin:1px;">
	<table id="dg" title="用户列表" class="easyui-datagrid"
	 fitColumns="true" pagination="true" rownumbers="true" 
	 url="${pageContext.request.contextPath }/user/userlist.html" fit="true" toolbar="#tb">
	 <thead>
	 	<tr>
	 		<th field="cb" checkbox="true" align="center"></th>
	 		<th field="id" width="50" align="center">编号</th>
	 		<th field="trueName" width="50" align="center">真实姓名</th>
	 		<th field="sex" width="50" align="center">性别</th>
	 		<th field="IDCard" width="150" align="center">身份证</th>
	 		<th field="email" width="150" align="center">邮件</th>
	 		<th field="phone" width="100" align="center">联系电话</th>
	 		<th field="address" width="120" align="center">联系地址</th>	 		
	 		<th field="userName" width="80" align="center">用户名</th>
	 		<!-- 修改jquery.easyui.min.js的8670行的代码可以使field使用点连接取二级属性 -->
	 		<th field="identity.identityName" width="100" align="center">系统权限</th>
	 	</tr>
	 </thead>
	</table>
	<div id="tb" style="padding-bottom:5px; padding-top:5px; ">
		<div style="width: 200px;">
			<a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openUserModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div style="padding-left:176px;margin-top:-25px;">
			<a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true" style="outline: none">搜索</a>
			<input type="text" id="s_trueName" style="width:220px;" size="20" onkeydown="if(event.keyCode==13) searchUser()" placeholder="请输入您要查找用户的真实姓名"/>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 570px;height:280px;padding:10px 20px"
	  closed="true" buttons="#dlg-buttons">
	 	<form id="fm" method="post">
	 		<table cellspacing="8px">
	 			<tr>
	 				<td>真实姓名：</td>
	 				<td><input type="text" id="trueName" name="trueName" class="easyui-validatebox" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>用户名：</td>
	 				<td><input type="text" id="userName" name="userName" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>身份证：</td>
	 				<td><input type="text" id="IDCard" name="IDCard" class="easyui-validatebox" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>身份：</td>
	 				<td>
	 					<select class="easyui-combobox" id="identity" name="identity" style="width: 143px;" editable="false" panelHeight="auto">
	 						<option value="">请选择对应身份</option>
	 						<c:forEach var="identity" items="${identityList }">
	 							<option value="${identity.id }">${identity.identityName }</option>
	 						</c:forEach>
	 					</select>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>密码：</td>
	 				<td><input type="text" id="passWord" name="passWord" class="easyui-validatebox" required="true"/></td>
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
	 				<td><input type="text" id="email" name="email" class="easyui-validatebox" validType="email" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>联系电话：</td>
	 				<td><input type="text" id="phone" name="phone" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>联系地址：</td>
	 				<td colspan="4">
	 					<input type="text" id="address" name="address" class="easyui-validatebox" required="true" style="width: 385px;"/>
	 				</td>
	 			</tr>
	 		</table>
	 	</form>
	</div>
	
	<div id="dlg2" class="easyui-dialog" style="width: 570px;height:280px;padding: 10px 20px"
	  closed="true" buttons="#dlg-buttons2">
	 	<form id="fm2" method="post">
	 		<table cellspacing="8px">
	 			<tr>
	 				<td>真实姓名：</td>
	 				<td><input type="text" id="trueName2" name="trueName" class="easyui-validatebox" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>用户名：</td>
	 				<td><input type="text" id="userName2" name="userName" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>身份证：</td>
	 				<td><input type="text" id="IDCard2" name="IDCard" class="easyui-validatebox" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>身份：</td>
	 				<td>
	 					<select class="easyui-combobox" id="identity2" name="identity" style="width: 143px;" editable="false" panelHeight="auto">
	 						<option value="">请选择对应身份</option>
	 						<c:forEach var="identity" items="${identityList }">
	 							<option value="${identity.id }">${identity.identityName }</option>
	 						</c:forEach>
	 					</select>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>密码：</td>
	 				<td><input type="text" id="passWord2" name="passWord" class="easyui-validatebox" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>性别：</td>
	 				<td>
	 					<select class="easyui-combobox" id="sex2" name="sex" style="width: 143px;" editable="false" panelHeight="auto">
	 						<option value="">请选择性别</option>
	 						<option value="男">男</option>
	 						<option value="女">女</option>
	 					</select>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>邮件：</td>
	 				<td><input type="text" id="email2" name="email" class="easyui-validatebox" validType="email" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>联系电话：</td>
	 				<td><input type="text" id="phone2" name="phone" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>联系地址：</td>
	 				<td colspan="4">
	 					<input type="text" id="address2" name="address" class="easyui-validatebox" required="true" style="width: 385px;"/>
	 				</td>
	 			</tr>
	 		</table>
	 	</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:addUser()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<div id="dlg-buttons2">
		<a href="javascript:updateUser()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeUpdateUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
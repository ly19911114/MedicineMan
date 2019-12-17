<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物品类别管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	
	var url;
	
	function searchType(){
		$("#dg").datagrid('load',{
			"typeName":$("#name").val()
		});
	}
	
	function deleteType(){
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
		$.messager.confirm("系统提示","删除类别会删除该类别下的所有药物记录，您确认要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath }/medicineType/deleteType.html",{ids:ids},function(result){
					if(result.success){
						$("#dg").datagrid("reload");
	 					$.messager.show({
							title:'系统提示',
							msg:"<font size='3' color='green'>该物品类别已删除成功！</font>",
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
							msg:"<font size='3' color='green'>删除该物品类别失败</font>",
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
	
	function openTypeAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加物品类别信息");
		url = "${pageContext.request.contextPath }/medicineType/add.html";
	}
	
	function saveType(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
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
						msg:"<font size='3' color='green'>保存物品类别信息成功</font>",
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
						msg:"<font size='3' color='green'>保存物品类别信息失败</font>",
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
	
	function openTypeModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","<font size='3' color='green'>请选择一条要编辑的数据！<font>");
			return;
		}
		var row=selectedRows[0];
		$("#dlg2").dialog("open").dialog("setTitle","编辑物品类别信息");
		$("#cTypeName2").val(row.cTypeName);
		$("#eTypeName2").val(row.eTypeName);
		$("#typeDesc2").val(row.typeDesc);
		url="${pageContext.request.contextPath }/medicineType/update.html?id="+row.id;
	}
	
	function updateType(){
		$("#fm2").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.success){
					$("#dlg2").dialog("close");
					$("#dg").datagrid("reload");
 					$.messager.show({
						title:'系统提示',
						msg:"<font size='3' color='green'>更新物品类别信息成功</font>",
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
						msg:"<font size='3' color='green'>更新物品类别信息失败</font>",
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
	
	function resetValue(){
		$("#cTypeName").val("");
		$("#eTypeName").val("");
		$("#typeDesc").val("");
	}
	
	function closeTypeDialog1(){
		$("#dlg").dialog("close");
		resetValue();
	}
	function closeTypeDialog2(){
		$("#dlg2").dialog("close");
	}
</script>
</head>
<body style="margin:1px;">
<center>
<div style="width:840px;height:400px;padding-top:20px;">
	<table id="dg" title="物品类别信息管理" class="easyui-datagrid"
	  pagination="true" rownumbers="true"
	 url="${pageContext.request.contextPath }/medicineType/medicineTypeList.html" fit="true" toolbar="#tb">
	 <thead>
	 	<tr>
	 		<th field="cb" checkbox="true" align="center"></th>
	 		<th field="id" width="150" align="center">编号</th>
	 		<th field="cTypeName" width="150" align="center">物品类别</th>
	 		<th field="eTypeName" width="150" align="center">Medicine Type</th>
	 		<th field="typeDesc" width="300" align="center">类别描述</th>
	 	</tr>
	 </thead>
	</table>
</div>
 </center>
	<div id="tb" style="padding-bottom:5px; padding-top:5px; ">
		<div style="width: 200px;">
			<a href="javascript:openTypeAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openTypeModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteType()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div style="padding-left:176px;margin-top:-25px;">
			<a href="javascript:searchType()" class="easyui-linkbutton" iconCls="icon-search" plain="true" style="outline: none">搜索</a>
			<input type="text" id="name" style="width:220px;" size="20" onkeydown="if(event.keyCode==13) searchType()" placeholder="请输入您要查找的中文物品类别名称"/>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:600px;height:200px;padding: 10px 20px"
	  closed="true" buttons="#dlg-buttons">
	 	<form id="fm" method="post">
	 		<table cellspacing="8px">
	 			<tr style="height:5px;"></tr>
	 			<tr>
	 				<td>物品类别(中)：</td>
	 				<td><input type="text" id="cTypeName" name="cTypeName" class="easyui-validatebox" required="true" autocomplete="off"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>物品类别(英)：</td>
	 				<td><input type="text" id="eTypeName" name="eTypeName" class="easyui-validatebox" required="true" autocomplete="off"/></td>
	 			</tr>
				<tr>
	 				<td>类别描述：</td>
	 				<td colspan="4">
	 					<input type="text" id="typeDesc" name="typeDesc" class="easyui-validatebox" required="true" style="width:408px;"/>
	 				</td>
	 			</tr>
	 		</table>
	 	</form>
	</div>
	
	<div id="dlg2" class="easyui-dialog" style="width:600px;height:200px;padding: 10px 20px"
	  closed="true" buttons="#dlg-buttons2">
	 	<form id="fm2" method="post">
	 		<table cellspacing="8px">
	 		    <tr style="height:5px;"></tr>
	 			<tr>
	 				<td>文档类别(中)：</td>
	 				<td><input type="text" id="cTypeName2" name="cTypeName" class="easyui-validatebox" required="true" autocomplete="off"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>文档类别(英)：</td>
	 				<td><input type="text" id="eTypeName2" name="eTypeName" class="easyui-validatebox" required="true" autocomplete="off"/></td>
	 			</tr>
				<tr>
	 				<td>类别描述：</td>
	 				<td colspan="4">
	 					<input type="text" id="typeDesc2" name="typeDesc" class="easyui-validatebox" required="true" style="width: 408px;"/>
	 				</td>
	 			</tr>
	 		</table>
	 	</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveType()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeTypeDialog1()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div> 
	<div id="dlg-buttons2">
		<a href="javascript:updateType()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeTypeDialog2()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div> 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>库存信息列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/others/easyui/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	
	var url;
	
	function searchMedicine(){
		$("#dg").datagrid('load',{
			"trueName":$("#s_trueName").val()
		});
	}
	
	function openInOrderJDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","<font size='3' color='green'>请选择需要审核的订单！<font>");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","审核订单");
 		url = "${pageContext.request.contextPath }/inOrder/jOrder.html?id="+row.id;
	}
	
	
	function addInOrder(){
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
						msg:"<font size='3' color='green'>进货订单生成成功！</font>",
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
						msg:"<font size='3' color='green'>进货订单生成失败！</font>",
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
	
	function deleteOrder(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","<font size='3' color='green'>请选择要删除的订单！<font>");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删除这<font color=red>"+selectedRows.length+"</font>条订单吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath }/inOrder/delete.html",{ids:ids},function(result){
					if(result.success){
						$("#dg").datagrid("reload");
	 					$.messager.show({
							title:'系统提示',
							msg:"<font size='3' color='green'>该订单已删除成功！</font>",
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
							msg:"<font size='3' color='green'>删除该订单失败</font>",
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
	
	function resetValue(){
		$("#in_nums").val("");
	}
	
	function closeInOrderDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
</script>
</head>
<body style="margin:1px;">
	<table id="dg" title="库存信息" class="easyui-datagrid"
	 fitColumns="true" pagination="true" rownumbers="true" 
	 url="${pageContext.request.contextPath }/inOrder/Nlist.html" fit="true" toolbar="#tb">
	 <thead>
	 	<tr>
	 		<th field="cb" checkbox="true" align="center"></th>
	 		<th field="id" width="30" align="center">编号</th>
	 		<th field="cName" width="100" align="center">中文名称</th>
	 		<th field="eName" width="100" align="center">英文名称</th>
	 		<th field="price" width="30" align="center">进价</th>
	 		<th field="nums" width="30" align="center">申领数量</th>
	 		<th field="createDate" width="60" align="center">下单日期</th>
	 		<th field="user.trueName" width="80" align="center">下单者</th>
	 		<th field="orderStatus.statusName" width="50" align="center">是否批准</th>
	 	</tr>
	 </thead>
	</table>
	<div id="tb" style="padding-bottom:5px; padding-top:5px; ">
		<div style="width: 200px;">
			<a href="javascript:openInOrderJDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">审核订单</a>
			<a href="javascript:deleteOrder()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div style="padding-left:140px;margin-top:-25px;">
			<a href="javascript:searchMedicine()" class="easyui-linkbutton" iconCls="icon-search" plain="true" style="outline: none">搜索</a>
			<input type="text" id="s_trueName" style="width:220px;" size="20" onkeydown="if(event.keyCode==13) searchMedicine()" placeholder="请输入下单人的真实姓名"/>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:270px;height:150px;padding: 10px 20px"
	  closed="true" buttons="#dlg-buttons">
	 	<form id="fm" method="post" enctype="multipart/form-data">
	 		<table cellspacing="8px">
	 			<tr>
	 				<td>
	 					操作：
	 				</td>
	 				<td>
	 					<input type="radio" id="yes" name="jstatus" value="2" />批准通过
	 					<input type="radio" id="no" name="jstatus" value="1" checked="checked"/>不予批准
	 				</td>
	 			</tr>
	 		</table>
	 	</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:addInOrder()" class="easyui-linkbutton" iconCls="icon-ok">确认</a>
		<a href="javascript:closeInOrderDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
	</div>
</body>
</html>
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
			"cName":$("#s_trueName").val()
		});
	}
	
	$(function(){
		$('#s_typeId').combobox({   
	         onChange:function(){  
	        	 $("#dg").datagrid('load',{
	     			"typeId":$('#s_typeId').combobox('getValue')
	     		});
	         }
	    })   
	});
	
	function openInOrderAddDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","<font size='3' color='green'>请从下面选择需要进货的物品！<font>");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","添加进货信息");
		$("#cName").val(row.cName);
		$("#eName").val(row.eName);
		$("#nums").val(row.nums);
		$("#price").val(row.oldPrice);
		$("#userId").val("${currentUser.trueName}");
 		url = "${pageContext.request.contextPath }/inOrder/add.html?userId=${currentUser.id}&id="+row.id; 
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
						msg:"<font size='3' color='green'>进货订单生产成功！</font>",
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
						msg:"<font size='3' color='green'>进货订单生产失败！</font>",
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
	 url="${pageContext.request.contextPath }/medicine/list.html" fit="true" toolbar="#tb">
	 <thead>
	 	<tr>
	 		<th field="cb" checkbox="true" align="center"></th>
	 		<th field="id" width="30" align="center">编号</th>
	 		<th field="cName" width="120" align="center">中文名称</th>
	 		<th field="eName" width="120" align="center">英文名称</th>
	 		<th field="price" width="30" align="center">价格</th>
	 		<th field="oldPrice" width="30" align="center">进价</th>
	 		<th field="nums" width="30" align="center">库存</th>
	 		<th field="manufacturer" width="100" align="center">生产商</th>
	 		<th field="type.cTypeName" width="50" align="center">物品类别</th>
	 	</tr>
	 </thead>
	</table>
	<div id="tb" style="padding-bottom:5px; padding-top:5px; ">
		<div style="width: 200px;">
			<a href="javascript:openInOrderAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">进货</a>
		</div>
		<div style="padding-left:56px;margin-top:-25px;">
			<a href="javascript:searchMedicine()" class="easyui-linkbutton" iconCls="icon-search" plain="true" style="outline: none">搜索</a>
			<input type="text" id="s_trueName" style="width:220px;" size="20" onkeydown="if(event.keyCode==13) searchMedicine()" placeholder="请输入您要查找的中文药名"/>
		</div>
		<div style="margin-left:356px;margin-top:-25px;">
			<select class="easyui-combobox" id="s_typeId" name="typeId" style="width: 143px;" editable="false" panelHeight="auto">
	 			<option value="">---请选择物品类别---</option>
	 			<c:forEach var="type" items="${typeList }">
	 				<option value="${type.id }">${type.cTypeName }</option>
	 			</c:forEach>
	 		</select>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 570px;height:300px;padding:10px 20px"
	  closed="true" buttons="#dlg-buttons">
	 	<form id="fm" method="post">
	 		<table cellspacing="8px">
	 			<tr>
	 				<td>中文名称：</td>
	 				<td><input type="text" id="cName" name="cName" class="easyui-validatebox"  readonly="readonly"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>英文名称：</td>
	 				<td><input type="text" id="eName" name="eName" class="easyui-validatebox" readonly="readonly"/></td>
	 			</tr>
	 			<tr>
	 				<td>剩余库存：</td>
	 				<td><input type="text" id="nums" name="nums" class="easyui-validatebox" readonly="readonly"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>进价：</td>
	 				<td><input type="text" id="price" name="price" class="easyui-validatebox" /></td>
	 			</tr>
	 			<tr>
	 				<td>下单者：</td>
	 				<td><input type="text" id="userId" class="easyui-validatebox" readonly="readonly"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>进货数量：</td>
	 				<td><input type="text" id="in_nums" name="in_nums" class="easyui-validatebox"  required="true"/></td>
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
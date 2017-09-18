<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <base href="<%=basePath%>">
   <META HTTP-EQUIV="pragma" CONTENT="no-cache">
   <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
   <META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
    <meta charset="UTF-8">
    <title>权限管理</title>
    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/demo/demo.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
</head>
<body>
    <div style="margin:20px 0;"></div>
    <div class="easyui-layout" style="width:100%;height:100%;">
        <div data-options="region:'center',title:'信息',iconCls:'icon-ok'">
        <table id="dg" title="权限信息" style="table-layout:fixed;width:100%"></table>
        </div>
        <div id="div" data-options="region:'center',title:'信息',iconCls:'icon-ok'">
        <table id="so" title="资源" style="table-layout:fixed;width:100%"></table>
        </div>
        <div style="margin:20px 0;"></div>
    <div id="toolbar">
        <a href="authority/toAddAuthority" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAuthority()">添加权限</a>
    <div style="margin:20px 0;"></div>
        <input class="easyui-searchbox" data-options="prompt:'请输入资源名',searcher:doSearch" label="资源名" style="width:25%">
        <div id="dlg" class="easyui-dialog" style="width:auto"
            closed="true" buttons="#dlg-buttons">
                <form action="" id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <div style="margin-bottom:10px">
                <input name="id" id="id" class="easyui-textbox" type="hidden">
            </div>
            <div style="margin-bottom:10px">
                <input name="authorities_name" class="easyui-textbox" required="true" label="权限名:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="authorities_desc" class="easyui-textbox" required="true"  label="描述:" style="width:100%">
            </div>

        <div style="margin-bottom:20px">
            <select class="easyui-combobox" id="status" name="status" label="状态:" labelPosition="left" style="width:100%;">
                <option value="0">--请选择--</option>
                <option value="1">1</option>
                <option value="2">0</option>
            </select>
        </div>
        <div style="margin-bottom:20px">
        <table id="tt" title="资源列表" checkbox="true" style="table-layout:fixed;width:100%"></table>
        </div>
        </form>
        </div>
       <script type="text/javascript">
        $(function(){ 
	    $("#tt").datagrid( {
		fitColumns : true,
		height : ($("#body").height()),
		width : $("#body").width(),
		idField : 'resources_name',
		url : "authority/getAllResource",
		rownumbers : false,
		showPageList : false,
		checkOnSelect:true,
		selectOnCheck:true,
		columns : [ [ {
		    field:'ck',
			checkbox:true,
		},{
			field : 'resources_name',
			title : '资源名',
			width : 100,
			halign : "center",
			align : "left"
		}]],
		
	});
})   

        $(function(){
        $("#div").hide();
		})   
       
       $(function(){
	    $("#dg").datagrid( {
		fitColumns : true,
		height : ($("#body").height()),
		width : $("#body").width(),
		idField : 'id',
		toolbar : "#toolbar",
/*  	pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],  */
		url : "authority/getAllAuthority",
		singleSelect : true,
		rownumbers : false,
		showPageList : false,
		columns : [ [ {
			field : 'id',
			title : 'id',
			width : 100,
			halign : "center",
			align : "left",
			hidden: true
		}, {
			field : 'authorities_name',
			title : '权限',
			width : 100,
			halign : "center",
			align : "left",
		}, {
        	field : 'authorities_desc',
			title : '描述',
			width : 100,
			halign : "center",
			align : "left",
		}, {
			field : 'status',
			title : '状态',
			width : 100,
			halign : "center",
			align : "left",
		}, {
			field : 'resources',
			title : 'URL',
			width : 100,
			halign : "center",
			align : "left",
			formatter:function(value,row,index){
			var str = "";
			str += '<a id="resource" class="easyui-linkbutton" href="javascript:resource('+row.id+')"/>';
			return str; 
			}
		}, {
			field : 'edit',
			title : '编辑',
			width : 130,
			halign : "center",
			align : "left",
			formatter:function(value,row){
			var str = "";
			str += '<a id="edit" class="easyui-linkbutton" href="authority/getAuthority?id='+row.id+'"/>';
			str += '<a id="remove" class="easyui-linkbutton" href="javascript:removeAuthority('+row.id+')"/>';
			return str;
			}
		}]],
		toolbar : '#toolbar',
		pagination : false,
		onLoadSuccess:function(data){
	        $("a[id='edit']").linkbutton({text:'修改',plain:true,iconCls:'icon-edit'});
	        $("a[id='remove']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
	        $("a[id='resource']").linkbutton({text:'资源列表',plain:true,iconCls:'icon-resource'});
	        }
		
	});
})

       function removeAuthority(id){
		$.messager.confirm('提示', '此操作不可撤销，是否确认删除?', function(flag) {
			if (flag) {
				$.ajax({  
			        type : "post",  
			        async : false,
			        url : "authority/delAuthority?id="+id,  
			        data : {},  
			        dataType : "json", //返回数据形式为json  
			        success : function(result) {
			            if (result) {
			            	if (!result.success) {
								$.messager.show( {
									title : 'Error',
									msg : result.msg
								});
							} else {
								$.messager.alert("提示", "删除成功！");
								var url = "authority/AllAuthority";
								window.location.href = encodeURI(url);
							}
			            }  
			        },  
			        error : function(errorMsg) {  
			            alert("数据请求失败，请联系系统管理员!");  
			        }  
			   }); 
			}
		});
	}
       function newAhthority(){
            url = "authority/AllAuthority";
        }
        
        function doSearch(value){
 			$("#tt").datagrid( {
				fitColumns : true,
				height : ($("#body").height()),
				width : $("#body").width(),
				idField : 'resources_name',
				url : "authority/getAllResource",
				rownumbers : false,
				showPageList : false,
				checkOnSelect:true,
				selectOnCheck:true,
				columns : [ [ {
				    field:'ck',
					checkbox:true,
				},{
					field : 'resources_name',
					title : '角色名',
					width : 100,
					halign : "center",
					align : "left"
				}]],      
			onLoadSuccess:function(data){  
			if(data){
			$.each(data.rows, function(index, item){
        	var rows = $("#dg").datagrid("getRows");
        	for(var i=0;i<rows.length;i++){
                var rowID = rows[i].authorities_name;
                var id = rows[i].id; 
                if(rowID==value){
						    $.ajax( {
							url : 'authority/getResource?id='+id,
							data : {
							},
							type : 'post',
							async : false,
							dataType : 'json',
							success : function(result) {
							b = result.rows;
							},
							error : function() {
								alert("获取数据失败，请联系系统管理员！");
							}
						});
				    var c = eval(b);
					for(var j=0;j<c.length;j++)
					{
			        if(item.resources_name==c[j].resources_name){
			        $('#tt').datagrid('checkRow', index);
			        }
			        }
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','用户信息');
                $('#fm').form('load',rows[i]);
            }
        }
        })
        }
        }
        })
        }
        
        function resource(id){
        $("#div").show();
        $("#so").datagrid( {
		fitColumns : true,
		height : ($("#body").height()),
		width : $("#body").width(),
		idField : 'id',
		url : "authority/getResource?id="+id,
		rownumbers : false,
		showPageList : false,
		columns : [ [ {
			field : 'id',
			title : 'id',
			width : 100,
			halign : "center",
			align : "left",
			hidden: true
		},{
			field : 'resources_name',
			title : 'URL',
			width : 100,
			halign : "center",
			align : "left"
		}]],
		
			});
 /*        $("#div").show(); */
        }
    </script>
    </div>
</body>
</html>
 
 

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
    <title>角色管理</title>
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
        <div data-options="region:'center',title:'',iconCls:'icon-ok'">
    
    <div id="toolbar">
       <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <div style="margin-bottom:20px;font-size:14px;border-bottom:1px solid #ccc">角色编辑</div>
            <div style="margin-bottom:10px">
                <input name="id" id="id" class="easyui-textbox" type="hidden" value="${role.id}">
            </div>
            <div style="margin-bottom:10px">
                <input name="roleName" class="easyui-textbox" required="true" value="${role.roleName}" label="角色名:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="roleDesc" class="easyui-textbox" required="true" value="${role.roleDesc}" label="描述:" style="width:100%">
            </div>
        <div style="margin-bottom:20px">
            <select class="easyui-combobox" id="roleStatus" name="roleStatus" value="${role.roleStatus}" label="状态:" labelPosition="left" style="width:100%;">
                <option value="${role.roleStatus}">${role.roleStatus}</option>
                <option value="1">1</option>
                <option value="2">0</option>
            </select>
        </div>
            <div style="margin-bottom:20px">
                <table id="tt" title="权限列表" checkbox="true" style="table-layout:fixed;width:100%"></table>
            </div>
     
    <div id="dlg-buttons">
        <a href="role/AllRole" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRole()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    </div>
    </div>
    <script type="text/javascript">
        $(function(){
		    showdatagrid();
		})
		
		function showdatagrid(){
	    $("#tt").datagrid( {
		fitColumns : true,
		height : ($("#body").height()),
		width : $("#body").width(),
		idField : 'authorities_name',
		url : "role/getAllAuthority",
		rownumbers : false,
		showPageList : false,
		checkOnSelect:true,
		selectOnCheck:true,
		columns : [ [ {
		    field:'ck',
			checkbox:true,
		},{
			field : 'authorities_name',
			title : '权限名',
			width : 100,
			halign : "center",
			align : "left"
		}]],
				onLoadSuccess:function(data){                
			        if(data){
			        $.each(data.rows, function(index, item){
			        	    var a = $("#id").val();
			        	    var b;
			        	    var c
						    $.ajax( {
							url : 'role/getAuthority?id='+a,
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
						c = eval(b);
					for(var i=0;i<c.length;i++)
					{
			        if(item.authorities_name==c[i].authorities_name){
			        $('#tt').datagrid('checkRow', index);
			        }
			        }
			        });
			        }
			        }                   
	});
}
        function saveRole(){
         var status = $('#status').combobox('getValue');
        /*  var row = $('#tt').datagrid('getChecked'); */
         var url;
          url = "role/updateRole"+"?&status="+status;
            $('#fm').form('submit',{
                url: "role/updateRole",
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
    </script>
</body>
</html>
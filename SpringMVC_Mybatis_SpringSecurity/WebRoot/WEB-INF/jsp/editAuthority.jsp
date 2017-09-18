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
    <title>资源管理</title>
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
       <form action="" id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <div style="margin-bottom:20px;font-size:14px;border-bottom:1px solid #ccc">权限信息</div>
            <div style="margin-bottom:10px">
                <input name="id" id="id" class="easyui-textbox" type="hidden" value="${authority.id}">
            </div>
           <div style="margin-bottom:10px">
                <input name="authorityName" class="easyui-textbox" required="true" label="权限:" value="${authority.authorities_name}" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="authorityDesc" class="easyui-textbox" required="true" label="描述:" value="${authority.authorities_desc}" style="width:100%">
            </div>
        <div style="margin-bottom:20px">
            <select class="easyui-combobox" id="status" name="status" value="${authority.status}" label="状态:" labelPosition="left" style="width:100%;">
                <option value="${authority.status}">${authority.status}</option>
                <option value="Status1">1</option>
                <option value="Status2">0</option>
            </select>
        </div>

        <div style="margin-bottom:20px">
        <table id="tt" title="资源列表" checkbox="true" style="table-layout:fixed;width:100%"></table>
        </div>
        </form>
    </div> 
    
    <div id="dlg-buttons">
        <a href="authority/AllAuthority" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveAuthority()" style="width:90px">保存</a>
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
			title : 'URL',
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
							url : 'authority/getResource?id='+a,
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
			        if(item.resources_name==c[i].resources_name){
			        $('#tt').datagrid('checkRow', index);
			        }
			        }
			        });
			        }
			        }                   	
	});
}

        function saveAuthority(){
         var status = $('#status').combobox('getValue');
         var url;
          url = "authority/updateAuthority"+"?status="+status;
            $('#fm').form('submit',{
                url: url,
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
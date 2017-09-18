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
    <title>实时数据</title>
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
        <table id="dg" title="My Datas" style="table-layout:fixed;width:100%"></table>
            <script type="text/javascript">
       $(function(){
	    $("#dg").datagrid( {
		fitColumns : true,
		height : ($("#body").height()),
		width : $("#body").width(),
		idField : 'id',
		toolbar : "#toolbar",
/*  	pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],  */
		url : "data/getAllData",
		singleSelect : false,
		rownumbers : false,
		showPageList : false,
		columns : [ [ {
			field : 'electricity',
			title : '焊接电流',
			width : 130,
			halign : "center",
			align : "left"
		}, {
			field : 'voltage',
			title : '焊接电压',
			width : 130,
			halign : "center",
			align : "left"
		}, {
			field : 'sensor_Num',
			title : '传感器量',
			width : 130,
			halign : "center",
			align : "left"
		}, {
			field : 'machine_id',
			title : '焊机id',
			width : 140,
			halign : "center",
			align : "left"
		}, {
			field : 'welder_id',
			title : '焊工id',
			width : 160,
			halign : "center",
			align : "left",
		}, {
			field : 'code',
			title : '编码',
			width : 130,
			halign : "center",
			align : "left"
		}, {
			field : 'year',
			title : '年',
			width : 140,
			halign : "center",
			align : "left",
					}, {
			field : 'month',
			title : '月',
			width : 140,
			halign : "center",
			align : "left",
			}, {
			field : 'day',
			title : '日',
			width : 140,
			halign : "center",
			align : "left",
			}, {
			field : 'hour',
			title : '时',
			width : 140,
			halign : "center",
			align : "left",
			}, {
			field : 'minute',
			title : '分',
			width : 140,
			halign : "center",
			align : "left",
			}, {
			field : 'second',
			title : '秒',
			width : 140,
			halign : "center",
			align : "left",
			}, {
			field : 'status',
			title : '状态',
			width : 140,
			halign : "center",
			align : "left",

		}]]
		
	});
})
    </script>
    </div>
</body>
</html>

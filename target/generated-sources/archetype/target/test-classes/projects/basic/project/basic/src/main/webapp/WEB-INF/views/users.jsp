<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 下面两个必须引用 -->
<script type="text/javascript"
	src="commons/jsExt/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript"
	src="commons/jsExt/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<!-- 国际化文件 -->
<script type="text/javascript"
	src="commons/jsExt/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<!-- 默认样式 -->
<link rel="stylesheet"
	href="commons/jsExt/jquery-easyui-1.5.2/themes/default/easyui.css">
<!-- 图标 -->
<link rel="stylesheet"
	href="commons/jsExt/jquery-easyui-1.5.2/themes/icon.css">
<!-- 自定义js -->
<script type="text/javascript" src="commons/js/teacherFormatter.js"></script>

</head>
<body>
	<div class="easyui-layout" style="margin: 20px 20px 20px 20px;">
		<table id="dg" class="easyui-datagrid" title="用户列表"
			style="width: 100%; height: 400px;"
			data-options="rownumbers:true,striped:true,fitColumns:true,singleSelect:true,autoRowHeight:true,pagination:true,
                pageSize:10,url:'${pageContext.request.contextPath}/getUsers',method:'get',toolbar:'#toolbar'">
			<thead>
				<tr>
					<th data-options="field:'id',width:100">ID</th>
					<th data-options="field:'name',width:100">name</th>
					<th data-options="field:'age',width:100">Age</th>
					<th data-options="field:'gender',width:60">Gender</th>
					<th data-options="field:'email',width:150">Email</th>
					<th data-options="field:'createTime',width:150">Create Time</th>
					<th data-options="field:'loginTime',width:150">Login Time</th>
					<th data-options="field:'teacher',width:100"
						formatter="teacherFormatter">Teacher Name</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" onclick="insert()">添加</a> <a
				href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit'" onclick="edit()">编辑</a> <a
				href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove'" onclick="del()">删除</a>
		</div>
	</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css"/>
</head>
<body class="skin-blue sidebar-mini" style="height: auto; min-height: 100%;">
<div class="wrapper">
    <jsp:include page="../includes/navi.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper" style="min-height: 915.8px;">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="https://adminlte.io/themes/AdminLTE/index2.html#"><i class="fa fa-dashboard"></i> 首页</a>
                </li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容分类</h3>
                            <c:if test="${baseResult != null}">
                                <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible"
                                     style="margin-top: 10px">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×
                                    </button>
                                    <h4>
                                        <i class="icon fa fa-${baseResult.status==200?"check":"ban"}"></i> ${baseResult.message}
                                    </h4>
                                </div>
                            </c:if>
                            <div class="box-body">
                                <a href="/content/category/form" type="button" class="btn btn-default btn-sm"><i class="fa fa-fw fa-plus"></i>增加</a>
                                <button id="delte-btn" type="button" class="btn btn-default btn-sm"><i class="fa fa-fw fa-trash-o"></i>批量删除</button>
                                <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-fw fa-download"></i></i>导入</a>
                                <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-fw fa-upload"></i></i>导出</a>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body table-responsive">
                                <table id="treeTable" class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>id</th>
                                        <th>名称</th>
                                        <th>排序</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${ContentCategories}" var="category">
                                        <tr id="${category.id}" pId="${category.parent.id}">
                                            <td>${category.id}</td>
                                            <td>${category.name}</td>
                                            <td>${category.sortOrder}</td>
                                            <td>
                                                <a href="/content/category/form?id=${category.id}" type="button" class="btn btn-primary btn-sm"><i class="fa fa-fw fa-edit"></i>编辑</a>
                                                <button type="button" class="btn btn-danger btn-sm" onclick="APP.initHandlerDelSingle('/content/category/delete','${category.id}')"><i class="fa fa-fw fa-trash"></i>删除</button>
                                                <a href="/content/category/form?parent.id=${category.id}&parent.name=${category.name}" type="button" class="btn btn-default btn-sm"><i class="fa fa-fw fa-plus"></i>新增下级菜单</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                    </div>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../includes/copyright.jsp"/>
</div>
<jsp:include page="../includes/footer.jsp"/>
<script src="/static/assets/plugins/treeTable/jquery.treeTable.min.js"></script>
<sys:modal title="警告：危险操作"/>
<script>
    $(function () {
        $('#treeTable').treeTable({
            expandLevel : 2,
            column : 1
        });
    });

</script>
</body>
</html>
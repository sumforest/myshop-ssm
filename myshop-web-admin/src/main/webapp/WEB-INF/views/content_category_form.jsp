<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 信息管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.css">

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
                信息管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="https://adminlte.io/themes/AdminLTE/index2.html#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <!-- Horizontal Form -->
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">${content.id==null? "新增":"修改"}分类</h3>
                    </div>
                    <c:if test="${failedmessage != null}">
                        <div class="alert alert-${failedmessage.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                            <h4><i class="icon fa fa-ban"></i> ${failedmessage.message}</h4>
                        </div>
                    </c:if>

                    <!-- /.box-header -->
                    <!-- form start -->
                    <form:form action="/content/category/save" id="inputForm" cssClass="form-horizontal" method="post" modelAttribute="contentCategory">
                        <form:hidden path="id"/>
                        <div class="box-body">
                            <div class="form-group">
                                <label for="parent.id" class="col-sm-2 control-label">父级类目</label>
                                <div class="col-sm-10">
                                    <form:input cssClass="hidden" path="parent.id"/>
                                    <input class="form-control required" id="parentName" data-toggle="modal" data-target="#modal-default" placeholder="请选择" readonly="true" value="${contentCategory.parent.name}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">分类名称</label>
                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required" path="name" placeholder="分类名称"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="sortOrder" class="col-sm-2 control-label">排列序号</label>
                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required digits" path="sortOrder" placeholder="排列序号"/>
                                </div>
                            </div>
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <button type="button" class="btn btn-default" onclick="history.go(-1)">撤销</button>
                            <button id="tagmodaltbn" type="submit" class="btn btn-info pull-right">提交</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        </section>
    </div>
    <jsp:include page="../includes/copyright.jsp"/>
</div>
    <jsp:include page="../includes/footer.jsp"/>
    <script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<%-- 自定义模态框--%>
<sys:modal title="请选择一个类目" modalmsg='<ul id="myTree" class="ztree"></ul>'/>
<script>
    $(function () {
        APP.intiHandlerzTree("/content/category/tree",["id"],function (nodes) {
                var text = nodes[0];
                $("#parentId").val(text.id);
                $("#parentName").val(text.name);
                $("#modal-default").modal('hide');
        })
    });
</script>
</body>
</html>
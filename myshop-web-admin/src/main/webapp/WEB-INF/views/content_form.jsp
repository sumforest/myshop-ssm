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
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/dropzone.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css">

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
                        <h3 class="box-title">${content.id==null? "新增":"修改"}信息</h3>
                    </div>
                    <c:if test="${failedmessage != null}">
                        <div class="alert alert-${failedmessage.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                            <h4><i class="icon fa fa-ban"></i> ${failedmessage.message}</h4>
                        </div>
                    </c:if>

                    <!-- /.box-header -->
                    <!-- form start -->
                    <form:form action="/content/save" id="inputForm" cssClass="form-horizontal" method="post" modelAttribute="content">
                        <form:hidden path="id"/>
                        <div class="box-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">内容类目</label>
                                <div class="col-sm-10">
                                    <form:input id="categoryId" cssClass="hidden" path="contentCategory.id"/>
                                    <input class="form-control required" id="categoryName" data-toggle="modal" data-target="#modal-default" placeholder="内容类目" readonly="true" value="${content.contentCategory.name}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="title" class="col-sm-2 control-label">内容标题</label>
                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required" path="title" placeholder="内容标题"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="subTitle" class="col-sm-2 control-label">子标题</label>
                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required" path="subTitle" placeholder="子标题"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>
                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required" path="titleDesc" placeholder="标题描述"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="url" class="col-sm-2 control-label">链接</label>
                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required" path="url" placeholder="链接"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="pic" class="col-sm-2 control-label">图片绝对路径</label>
                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required" path="pic" placeholder="图片绝对路径"/>
                                    <div id="dropz" class="dropzone" style="border: 2px dashed #0087F7;border-radius: 5px;"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="pic2" class="col-sm-2 control-label">图片2</label>
                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required" path="pic2" placeholder="图片2"/>
                                    <div id="dropz2" class="dropzone" style="border: 2px dashed #0087F7;border-radius: 5px;"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">详情</label>
                                <div class="col-sm-10">
                                    <form:input cssClass="hidden" id="contentText" path="content"/>
                                    <div id="editor">${content.content}</div>
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
    <script src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
    <script src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>
<%-- 自定义模态框--%>
<sys:modal title="请选择一个类目" modalmsg='<ul id="myTree" class="ztree"></ul>'/>
<script>
    //zTree
    $(function () {
        APP.intiHandlerzTree("/content/category/tree",["id"],function (nodes) {
                var text = nodes[0];
                $("#categoryId").val(text.id);
                $("#categoryName").val(text.name);
                $("#modal-default").modal('hide');
        });
        initWangEditor();
    });

    APP.intHandlerDropZone("#dropz", {
        url: "/file/upload",
        init: function () {
            this.on("success", function (file, data) {
                $("#pic").val(data.fileName);
            });

        }
    });
    APP.intHandlerDropZone("#dropz2", {
        url: "/file/upload",
        init: function () {
            this.on("success", function (file, data) {
                $("#pic2").val(data.fileName);
            });

        }
    });

    //wangeitor初始化方法
    function initWangEditor() {
        var E = window.wangEditor;
        var editor = new E('#editor');
        // 配置服务器端地址
        editor.customConfig.uploadImgServer = '/file/upload';
        editor.customConfig.uploadFileName = 'editorFile';
        editor.create();
        $("#tagmodaltbn").click(function () {
            var html = editor.txt.html();
            $("#contentText").val(html);
        });
    }

</script>
</body>
</html>
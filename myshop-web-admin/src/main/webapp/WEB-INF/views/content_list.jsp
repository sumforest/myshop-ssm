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
                <li><a href="https://adminlte.io/themes/AdminLTE/index2.html#"><i class="fa fa-dashboard"></i> 首页</a>
                </li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- /.row -->
            <div class="box box-info search-hight" style="display: none">
                <div class="box-header">
                    <h3 class="box-title">高级搜索</h3>
                    <div class="row form-horizontal" style="margin-top: 15px">
                        <div class="col-xs-12">
                                <div class="col-xs-3">
                                    <div class="form-group">
                                        <label for="searchTitle" class="col-sm-3 control-label">标题</label>
                                        <div class="col-sm-9">
                                            <input id="searchTitle" name="title" class="form-control" placeholder="标题">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-3">
                                    <div class="form-group">
                                        <label for="searchSubTitle" class="col-sm-3 control-label">子标题</label>
                                        <div class="col-sm-9">
                                            <input id="searchSubTitle" name="subTitle" class="form-control" placeholder="子标题">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-3">
                                    <div class="form-group">
                                        <label for="SearchTitleDesc" class="col-sm-3 control-label">标题描述</label>
                                        <div class="col-sm-9">
                                            <input id="SearchTitleDesc" name="titleDesc" class="form-control" placeholder="标题描述">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-1">
                                    <button type="button" onclick="search()" class="btn btn-default btn-sm"><i class="fa fa-search"></i> 搜索</button>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容列表</h3>
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
                                <a href="/content/form" type="button" class="btn btn-default btn-sm"><i
                                        class="fa fa-fw fa-plus"></i>增加</a>
                                <button id="delte-btn" type="button" class="btn btn-default btn-sm"
                                        onclick="APP.deleteHandlerMuilti('/content/delete')"><i class="fa fa-fw fa-trash-o"></i>批量删除</button>
                                <a href="#" type="button" class="btn btn-default btn-sm"><i
                                        class="fa fa-fw fa-download"></i></i>导入</a>
                                <a href="#" type="button" class="btn btn-default btn-sm"><i
                                        class="fa fa-fw fa-upload"></i></i>导出</a>
                                <button type="button" class="btn btn-default btn-sm"
                                        onclick="$('.search-hight').css('display')=='none'? $('.search-hight').show('fast'):$('.search-hight').hide('fast')">
                                    <i class="fa fa-search"></i> 搜索
                                </button>
                            </div>

                            <!-- /.box-header -->
                            <div class="box-body table-responsive">
                                <table id="datable-mytables" class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" class="minimal ichecked_master"></th>
                                        <th>ID</th>
                                        <th>所属类目</th>
                                        <th>内容标题</th>
                                        <th>子标题</th>
                                        <th>标题描述</th>
                                        <th>链接</th>
                                        <th>图片绝对路径</th>
                                        <th>图片2绝对路径</th>
                                        <th>更新时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
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
<sys:modal/>
</body>
<script>
    var searchTable;
    /**
     * 向服务端请求数据初始化DataTables
     */
    $(function () {
        var _columns = [
            {"data": function ( row, type, val, meta ) {
                    return '<input id="'+ row.id +'" type="checkbox" class="minimal icheck_all"/>';
                }
            },
            { "data": "id" },
            { "data": "contentCategory.name" },
            { "data": "title" },
            { "data": "subTitle" },
            { "data": "titleDesc" },
            {
                "data": function (row, type, val, meta) {
                    if (row.url == null) {
                        return '';
                    }
                    return "<a href='"+ row.url+"'>查看</a>";
                }
            },
            {
                "data": function (row, type, val, meta) {
                    if (row.pic == null) {
                        return '';
                    }
                    return "<a href='"+ row.pic +"'>查看</a>";
                }
            },
            {
                "data": function (row, type, val, meta) {
                    if (row.pic2 == null) {
                        return '';
                    }
                    return "<a href='"+ row.pic2 +"'>查看</a>";
                }
            },
            {
                "data": function (row, type, val, meta) {
                    return DateTime.format(row.updated, "yyyy-MM-dd HH:mm:ss");
                }
            },
            {
                "data": function (row, type, val, meta) {
                    var url = "/user/detail?id=" + row.id;
                    return '<button id="detail-btn" type="button" onclick="APP.initHandlerShowDetail(\'' + url + '\')" class="btn btn-default btn-sm"><i class="fa fa-fw fa-search"></i>查看</button>' +
                        '<a href="/content/form?id=' + row.id + '" type="button" class="btn btn-primary btn-sm"><i class="fa fa-fw fa-edit"></i>编辑</a>' +
                        '<a href="#" type="button" class="btn btn-danger btn-sm"><i class="fa fa-fw fa-trash"></i>删除</a>';
                }
            }
        ];
        searchTable = APP.initHandlerPage("/content/page", _columns);
    })

    /**
     * 搜索方法
     */
    function search() {
        var title = $("#searchTitle").val();
        var subTitle = $("#searchSubTitle").val();
        var titleDesc = $("#SearchTitleDesc").val();
        var param = {
            "title":title,
            "subTitle":subTitle,
            "titleDesc":titleDesc

        };
        searchTable.settings()[0].ajax.data = param;
        searchTable.ajax.reload();
    }
</script>
</html>
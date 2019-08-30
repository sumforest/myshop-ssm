<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar" style="height: auto;">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="/static/assets/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>${user.email}</p>
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>
            <ul class="sidebar-menu tree" data-widget="tree">
                <li class="header">功能菜单</li>
                <li class="active treeview menu-open">
                    <a href="#">
                        <i class="fa fa-dashboard"></i> <span>用户管理</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/user/list"><i class="fa fa-circle-o"></i> 用户列表 </a></li>
                        <li><a href="/user/form"><i class="fa fa-circle-o"></i> 新增用户</a></li>
                    </ul>
                </li>
                <li class="active treeview menu-open">
                    <a href="https://adminlte.io/themes/AdminLTE/index2.html#">
                        <i class="fa fa-table"></i> <span>内容管理</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                         </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/content/category/list"><i class="fa fa-circle-o"></i> 内容分类</a></li>
                        <li><a href="/content/list"><i class="fa fa-circle-o"></i> 内容列表</a></li>
                    </ul>
                </li>
            </ul>
        </section>
    </aside>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <table class="table table-hover">
        <tr>
            <td>用户名</td>
            <td>${tbUser.username}</td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>${tbUser.email}</td>
        </tr>
        <tr>
            <td>更新时间</td>
            <td><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
        </tr>
        <tr>
            <td>创建时间</td>
            <td><fmt:formatDate value="${tbUser.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
        </tr>
    </table>
</body>
</html>
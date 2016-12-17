<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/jsp/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>首页</title>

</head>

<body class="main">
    <div class="default-cnt clearfix">
        <div class="change-skin">
            <i class="skin-ico"></i>
        </div>
        <div class="nav">
            <img src="<%=rootPath%>/theme/gray/images/logo.png" alt="小积快跑后台管理系统" />
            <ul id="menu" class="menu">
            <!-- 通过js去拼装 -->
            </ul>
        </div>
        <!-- 右侧主内容 -->
        <div class="aside-rgt">
            <div class="aside-cnt">
                <div class="head-tit">
                    <h1 class="cnt-tit" id="cnt-title">标题提示</h1>
                    <div class="user-info">
                        <img src="<%=rootPath%>/theme/gray/images/po.jpg" class="portrait" />
                        <em class="user-xx">您好，<i>${sessionScope.loginSysOp.opName }</i></em>
                        <em class="logout" ><a href="<%=rootPath%>/logincontroller/logout">注销</a></em>
                    </div>
                </div>
                <div class="">
                    <iframe src="demo/grid.html" frameborder="0" scrolling="no" height="2300px" width="100%" id="indexiframe"></iframe>
                </div>
            </div>
        </div>
        <!-- foot-->
        <div class="clearfix"></div>
        <div class="footer">
            <span>联系电话：xxxxxx</span>
            <span>传真号码：xxxxxx</span>
            <span>地址：xxxxxx</span>
            <span>版权：xxxxxx</span>
        </div>
    </div>

    <script src="<%=rootPath%>/page/common/js/index_page.js"></script>
    <script src="<%=rootPath%>/page/index.js"></script>
</body>

</html>
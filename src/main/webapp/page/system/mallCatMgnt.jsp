<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/jsp/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>类目列表</title>
</head>

<body>
    <div class="asideR-cont">
        <div class="query-head clearfix">
            <span class="query-item mr20">
                <!-- <label class="query-txt">用户类型</label> -->
                <button class="query-txt" >类目管理</button>
            </span>
        </div>
        
        <div class="grid-com com-line" id="queryGrid">
        </div>
    </div>
    
    <script src="<%=rootPath %>/page/system/js/mallCatMgnt.js"></script>
</body>

</html>
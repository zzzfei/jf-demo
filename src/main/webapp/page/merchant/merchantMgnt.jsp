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
    <title>广告位</title>
</head>

<body>
    <div class="asideR-cont">
        <div class="query-head clearfix">
        	
        	<input type="hidden" id="providerShortName" name="providerShortName" >
        
            <span class="query-item mr20">
                <label class="query-txt">供应商类型</label>
                <div class="combo" id="advStateCombo"></div>
                <input type="hidden" id="providerShortName" name="providerShortName" >
            </span>
            
            <span class="query-item">
                <input class="query-input" id="providerName" name="providerName" placeholder="请输入供应商名称">
            </span>
            <span class="query-btns">
                 <button type="submit" class="btn-search" title="查询" onclick="query()"></button>
                 <button type="submit" class="btn-reset" onclick="reset()" title="重置"></button>
             </span>
        </div>
        <div class="grid-com com-line" id="queryGrid">
        </div>
    </div>
    
    <script src="<%=rootPath %>/page/merchant/js/merchantMgnt.js"></script>
</body>

</html>
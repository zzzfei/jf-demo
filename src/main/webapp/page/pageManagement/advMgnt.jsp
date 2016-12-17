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
        
            <span class="query-item mr20">
                <label class="query-txt">广告位状态</label>
                <div class="combo" id="advStateCombo"></div>
                <input type="hidden" id="advState" name="advState" >
            </span>
            
            <span class="query-item mr20">
                <label class="query-txt">广告位位置</label>
                <div class="combo" id="advPosCombo"></div>
                <input type="hidden" id="advPos" name="advPos" >
            </span>
            
            <br> <br>
            
            <span class="query-item mr20">
                <label class="query-txt">区域</label>
                <div class="combo" id="areaNameCombo"></div>
                <input type="hidden" id="areaId" name="areaId" >
                <input type="hidden" id="supAreaId" name="supAreaId" >
            </span>
           <!--  <span class="query-item mr20">   
                <label class="query-txt">国家</label>
                <div class="combo" id="areaNameCombo"></div>
                <input type="hidden" id="areaId" name="areaId" >
                <input type="hidden" id="supAreaId" name="supAreaId" >
                
                <label class="query-txt">省</label>
                <div class="combo" id="areaNameCombo2"></div>
                
                <label class="query-txt">市</label>
                <div class="combo" id="areaNameCombo3"></div>
                
                <label class="query-txt">区/县</label>
                <div class="combo" id="areaNameCombo4"></div>
            </span> -->
            
            <span class="query-item">
                <input class="query-input" id="advName" placeholder="请输入广告位名称">
            </span>
            <span class="query-btns">
                 <button type="submit" class="btn-search" title="查询" onclick="query()"></button>
                 <button type="submit" class="btn-reset" onclick="reset()" title="重置"></button>
             </span>
        </div>
        <div class="grid-com com-line" id="queryGrid">
        </div>
    </div>
    
    <script src="<%=rootPath %>/page/pageManagement/js/advMgnt.js"></script>
</body>

</html>
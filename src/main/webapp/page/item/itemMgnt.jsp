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
    <title>区域商品管理</title>
</head>

<body>
    <div class="asideR-cont">
        <div class="query-head clearfix">
        
            <span class="query-item mr20">
                <label class="query-txt">商品类型</label>
                <div class="combo" id="itemTypeCombo"></div>
                <input type="hidden" id="itemType" name="itemType" >
            </span>
            
            <span class="query-item mr20">
                <label class="query-txt">商品类别</label>
                <div class="combo" id="mallCatCombo"></div>
                <input type="hidden" id="mallCatId" name="mallCatId" >
            </span>
            
            <span class="query-item mr20">
                <label class="query-txt">销售状态</label>
                <div class="combo" id="saleAttrCombo"></div>
                <input type="hidden" id="itemSaleAttr" name="itemSaleAttr" >
            </span>
            <br><br>
            <span class="query-item">
           		<label class="query-txt">商品</label>
                <input class="query-input" id="itemName" name="itemName" placeholder="请输入商品名称或编码">
            </span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span class="query-item mr20">
                <label class="query-txt">商家</label>
                <div class="combo" id="mchtCombo"></div>
                <input type="hidden" id="mchtId" name="mchtId" >
            </span>
            
            <span class="query-item mr20">
                <label class="query-txt">审批状态</label>
                <div class="combo" id="itemApprStateCombo"></div>
                <input type="hidden" id="itemApprState" name="itemApprState" >
            </span>
            <br><br>
            <span class="query-btns">
                 <button type="submit" class="btn-search" title="查询" onclick="query()"></button>
                 <button type="submit" class="btn-reset" onclick="reset()" title="重置"></button>
             </span>
        </div>
       		 <div class="grid-com com-line" id="queryGrid">
        </div>
    </div>
    
    <script src="<%=rootPath %>/page/item/js/itemMgnt.js"></script>
</body>

</html>
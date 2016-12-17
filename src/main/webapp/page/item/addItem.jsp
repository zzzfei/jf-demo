<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/jsp/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>添加商品</title>
    <!-- 日期控件CSS -->
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/skinex/skinex.css" />
</head>
<body>
    <div class="asideR-cont">
        <div class="add-cnt">
       	 <form action="" id="addIcItem" method="post">
       	 <!-- 放置类型和id的隐藏域 -->
       	 <input type="hidden" id="type" name="type" value="${type }" >
       	 <input type="hidden" id="itemId" name="itemId" value="${icItem.itemId }" >
       	 <input type="hidden" id="itemUdState" name="itemUdState" value="${icItem.itemUdState }" >
       	 <input type="hidden" id="itemApprState" name="itemApprState" value="${icItem.itemApprState }" >
       	 
            <ul class="add-lst">
            	 <li>
                    <label class="lbl-txt">商品类型：</label>
                    <span class="query-item">
                        <div class="combo" id="itemTypeCombo"></div>
                    </span>
                    <span class="require">*</span>
                    <input type="hidden" id="itemType" name="itemType" value="${icItem.itemType }" >
                </li>
                <li>
                    <label class="lbl-txt">商品类别：</label>
                    <span class="query-item">
                        <div class="combo" id="mallCatCombo"></div>
                    </span>
                    <span class="require">*</span>
                    <input type="hidden" id="mallCatId" name="mallCatId" value="${icItem.mallCatId }" >
                </li>
                <li>
                    <label class="lbl-txt">商品销售状态：</label>
                    <span class="query-item">
                        <div class="combo" id="saleAttrCombo"></div>
                    </span>
                    <span class="require">*</span>
                    <input type="hidden" id="itemSaleAttr" name="itemSaleAttr" value="${icItem.itemSaleAttr }" >
                </li>
                <li>
                    <label class="lbl-txt">商家：</label>
                    <span class="query-item">
                        <div class="combo" id="mchtCombo"></div>
                    </span>
                    <span class="require">*</span>
                    <input type="hidden" id="mchtId" name="mchtId" value="${icItem.mchtId }" >
                    <input type="hidden" id="mchtName" name="mchtName" value="${icItem.mchtName }" >
                </li>
                <li>
                    <label class="lbl-txt">商品名称：</label>
                    <input type="text" name="itemName" value="${icItem.itemName }" class="input-text ver-right" />
                    <span class="require">*</span>
                </li>
                <li>
                    <label class="lbl-txt">商品数量：</label>
                    <input type="text" name="itemNum" value="${icItem.itemNum }" class="input-text ver-right" />
                    <span class="require">*</span>
                </li>
                <li>
                    <label class="lbl-txt">商品原价：</label>
                    <input type="text" name="itemMarketPrice" value="${icItem.itemMarketPrice }" class="input-text ver-right" />
                    <span class="require">*</span>积分币
                </li>
                <li>
                    <label class="lbl-txt">商品售价：</label>
                    <input type="text" name="itemSalePrice" value="${icItem.itemSalePrice }" class="input-text ver-right" />
                    <span class="require">*</span>积分币
                </li>
                <li>
                    <label class="lbl-txt">有效期起：</label>
                    <span class="posR">
                        <input readonly="readonly" id="startDate" name="itemValidStart" value="${itemValidStart }" type="text" class="input-text">
                        <i class="cal-ico" id="startDateBox"></i>
                        <span class="require">*</span>
                    </span>
                    &nbsp;&nbsp;
                    <label class="lbl-txt">有效期止：</label>  
                    <span class="posR">
                        <input readonly="readonly" id="endDate" name="itemValidEnd" value="${itemValidEnd }" type="text" class="input-text">
                        <i class="cal-ico" id="endDateBox"></i>
                        <span class="require">*</span>
                    </span>
                </li>
                <li>
                    <label class="lbl-txt">商品描述信息：</label>
                    <textarea class="textarea textarea-lg" id="text" name="itemDesc">
肯德基优惠卷 10 元
                    </textarea>
                </li>
                <li>
                    <label class="lbl-txt">购买须知：</label>
                    <textarea class="textarea textarea-lg" id="text" name="itemDesc1">
详有效期：
2015-05-08至2015-12-16
规则提醒：
全场通用、可与其他优惠券叠加使用，全场通用、可与其他优惠券叠加使用全场通用、可与
                    </textarea>
                </li>
                <li>
                    <label class="lbl-txt">使用流程：</label>
                    <textarea class="textarea textarea-lg" id="text" name="itemDesc2">
第一步：
拍下心仪的优惠券并使用积分付款，付款成功后收到系统发的验证码短信
第二部：
客户在验证码有效期内（即日起30天内），到店消费将优惠券密码告知店员
第三步：
店员对优惠券进行验证确认，抵扣消费金额。
第四步：
客户对订单进行评价，可获得小积快跑积分
                    </textarea>
                </li>
                
                <li>
                    <label class="lbl-txt">商品主图：</label>
                    <c:if test="${empty detail }">
	                    <div class="upload-box">
	                        <input type="text" class="input-text"  name="path" id="path" value="${icItemPicture.itemPicUrl }" />
	                        <input type="file" id="uploadFile" name="uploadFile" class="file-upload" onchange="uploadPath()"/>
	                        <span class="require">*</span>
	                        <button class="browse-btn">浏览</button>
	                    </div>  
	                     <input type="button" class="upload-btn" name="uploadImg" id="uploadImg" value="上传"> 
	                     <input type="hidden" name="itemPicUrl" id="itemPicUrl" value="${icItemPicture.itemPicUrl }" > 
	                     <p style="color:red;margin-left:120px">建议上传图片选取400×300像素图片，大小在300K以下，以达到最佳显示效果。</p>
                     </c:if>
                     <c:if test="${!empty detail }">
                     	<img src="<%=rootPath %>/${icItemPicture.itemPicUrl }" width="160" height="120">
                     </c:if>
                </li>
              </ul>
              </form>
            <br> <br>
            <c:if test="${empty detail }">
	            <div class="form-aciton">
	                <button class="submit-btn" onclick="saveIcAdv()">确定</button>
	                <button class="quit-btn" id="cancle">取消</button>
	            </div>
            </c:if>
            <c:if test="${!empty detail }">
            	<div class="form-aciton">
	                <button class="quit-btn" id="cancle">返回</button>
	            </div>
            </c:if>
        </div>
    </div>
    <!-- 日期控件JS  -->
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/js/dateField.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/jquery/plugins/jquery.ajaxfileupload.js"></script>
    <script src="<%=rootPath %>/page/item/js/addItem.js"></script>
</body>

</html>
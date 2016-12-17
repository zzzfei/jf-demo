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
    <title>添加广告</title>
    <!-- 日期控件CSS -->
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/skinex/skinex.css" />
</head>
<body>
    <div class="asideR-cont">
        <div class="add-cnt">
       	 <form action="" id="addIcAdv" method="post">
       	 <!-- 放置类型和id的隐藏域 -->
       	 <input type="hidden" id="type" name="type" value="${type }" >
       	 <input type="hidden" id="advId" name="advId" value="${icAdv.advId }" >
       	 <input type="hidden" id="advOrder" name="advOrder" value="${icAdv.advOrder }" >
       	 
            <ul class="add-lst">
            	<li>
            		<label class="lbl-txt">广告位位置：</label>&nbsp;&nbsp;
            		<input type="radio" name="advPos" value="1" id="p1" 
            		<c:if test="${icAdv.advPos == 1 }">
            			checked="checked"
            		</c:if> ><label for="p1">首页banner位</label>
            		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            		<input type="radio" name="advPos" value="2" id="p2" 
            		<c:if test="${icAdv.advPos == 2 }">
            			checked="checked"
            		</c:if> ><label for="p2">特价区banner位</label>
            	</li>
            	 <li>
                    <label class="lbl-txt">区域：</label>
                    <span class="query-item">
                        <div class="combo" id="areaNameCombo"></div>
                    </span>
                    <span class="require">*</span>
                    <input type="hidden" id="areaId" name="advAreaId" value="${icAdv.advAreaId }" >
                </li>
            	<li>
                    <label class="lbl-txt">广告位名称：</label>
                    <input type="text" name="advName" value="${icAdv.advName }" class="input-text ver-right" />
                    <span class="require">*</span>
                </li>
                <li>
                    <label class="lbl-txt">广告位超链接：</label>
                    <input type="text" name="advUrl" value="${icAdv.advUrl }" class="input-text ver-right" />
                    <span class="require">*</span>
                </li>
                <li>
                    <label class="lbl-txt">广告位说明：</label>
                    <textarea class="textarea textarea-lg" id="text" name="advDesc">${icAdv.advDesc }</textarea>
                    <span class="require">*</span>
                </li>
                <li>
                    <label class="lbl-txt">播放时间：</label>
                    <span class="posR">
                        <input readonly="readonly" id="startDate" name="advStartTime" value="${advStartTime }" type="text" class="input-text">
                        <i class="cal-ico" id="startDateBox"></i>
                        <span class="require">*</span>
                    </span> -  
                    <span class="posR">
                        <input readonly="readonly" id="endDate" name="advEndTime" value="${advEndTime }" type="text" class="input-text">
                        <i class="cal-ico" id="endDateBox"></i>
                        <span class="require">*</span>
                    </span>
                </li>
                <li>
                    <label class="lbl-txt">广告图片：</label>
                    <c:if test="${empty detail }">
	                    <div class="upload-box">
	                        <input type="text" class="input-text"  name="path" id="path" value="${icAdv.advPic }" />
	                        <input type="file" id="uploadFile" name="uploadFile" class="file-upload" onchange="uploadPath()"/>
	                        <span class="require">*</span>
	                        <button class="browse-btn">浏览</button>
	                    </div>  
	                     <input type="button" class="upload-btn" name="uploadImg" id="uploadImg" value="上传"> 
	                     <input type="hidden" name="advPic" id="advPic" value="${icAdv.advPic }" > 
	                     <p style="color:red;margin-left:120px">建议上传图片选取400×300像素图片，大小在300K以下，以达到最佳显示效果。</p>
                     </c:if>
                     <c:if test="${!empty detail }">
                     	<img src="<%=rootPath %>/${icAdv.advPic }">
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
	                <button class="quit-btn" id="cancle">取消</button>
	            </div>
            </c:if>
        </div>
    </div>
    <!-- 日期控件JS  -->
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/js/dateField.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/jquery/plugins/jquery.ajaxfileupload.js"></script>
    <script src="<%=rootPath %>/page/pageManagement/js/addAdv.js"></script>
</body>

</html>
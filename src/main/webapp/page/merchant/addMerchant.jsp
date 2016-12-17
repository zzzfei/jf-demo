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
    <title>添加积分供应商</title>
    <!-- 日期控件CSS -->
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/skinex/skinex.css" />
</head>
<body>
    <div class="asideR-cont">
        <div class="add-cnt">
       	 <form action="" id="addPcProvider" method="post">
       	 <!-- 放置类型和id的隐藏域 -->
       	 <input type="hidden" id="type" name="type" value="${type }" >
       	 <input type="hidden" id="providerId" name="providerId" value="${pcProvider.providerId }" >
       	 
            <ul class="add-lst">
            	 <li>
                    <label class="lbl-txt">积分供应商名称：</label>
                    <input type="text" name="providerName" value="${pcProvider.providerName }" class="input-text ver-right" />
                    <span class="require">*</span>
                 </li>
                 
                 <li>
                    <label class="lbl-txt">积分供应商logo：</label>
                    <c:if test="${empty detail }">
	                    <div class="upload-box">
	                        <input type="text" class="input-text"  name="path" id="picpath" value="${pcProvider.providerPicUrl }" />
	                        <input type="file" id="uploadFile" name="uploadFile" class="file-upload" onchange="uploadPath()"/>
	                        <span class="require">*</span>
	                        <button class="browse-btn">浏览</button>
	                    </div>  
	                     <input type="button" class="upload-btn" name="uploadProviderPic" id="uploadProviderPic" value="上传"> 
	                     <input type="hidden" name="providerPicUrl" id="providerPicUrl" value="${pcProvider.providerPicUrl }" > 
	                     <p style="color:red;margin-left:120px">建议上传图片选取400×300像素图片，大小在300K以下，以达到最佳显示效果。</p>
                     </c:if>
                     <c:if test="${!empty detail }">
                     	<img src="<%=rootPath %>/${pcProvider.providerPicUrl }" width="120" height="90">
                     </c:if>
                </li>
                
                <li>
                   <label class="lbl-txt">积分供应商描述：</label>
                   <input type="text" name="providerDesc" value="${pcProvider.providerDesc }" class="input-text ver-right" />
                </li>
                
                <li>
                   <label class="lbl-txt">供应商别名：</label>
                   <input type="text" name="providerShortName" value="${pcProvider.providerShortName }" class="input-text ver-right" />
                </li>
                
                <li>
                   <label class="lbl-txt">积分兑换比例：</label>
                   <input type="text" name="providerPointRatio" value="${pcProvider.providerPointRatio }" class="input-text ver-right" />
                   <span class="require">*</span>
                </li>
                
                <li>
                   <label class="lbl-txt">积分兑换费率：</label>
                   <input type="text" name="providerPointFee" value="${pcProvider.providerPointFee }" class="input-text ver-right" />
                   <span class="require">*</span>
                </li>
                
                <li>
                    <label class="lbl-txt">结算周期：</label>
                    <span class="query-item">
                        <div class="combo" id="balanceTermCombo"></div>
                    </span>
                    <span class="require">*</span>
                    <input type="hidden" id="balanceTerm" name="balanceTerm" value="${pcProvider.balanceTerm }" >
                </li>
                
                <li>
                    <label class="lbl-txt">结算方式：</label>
                    <span class="query-item">
                        <div class="combo" id="balanceTypeCombo"></div>
                    </span>
                    <span class="require">*</span>
                    <input type="hidden" id="balanceType" name="balanceType" value="${pcProvider.balanceType }" >
                </li>
                
                <li>
                    <label class="lbl-txt">企业营业执照：</label>
                    <c:if test="${empty detail }">
	                    <div class="upload-box">
	                        <input type="text" class="input-text"  name="path" id="licpath" value="${pcProvider.bproviderLicense }" />
	                        <input type="file" id="licenceFile" name="uploadFile" class="file-upload" onchange="uploadPath2()"/>
	                        <span class="require">*</span>
	                        <button class="browse-btn">浏览</button>
	                    </div>  
	                     <input type="button" class="upload-btn" name="uploadLicense" id="uploadLicense" value="上传"> 
	                     <input type="hidden" name="bproviderLicense" id="bproviderLicense" value="${pcProvider.bproviderLicense }" > 
                     </c:if>
                     <c:if test="${!empty detail }">
                     	<img src="<%=rootPath %>/${pcProvider.bproviderLicense }" width="120" height="90">
                     </c:if>
                </li>
                
                <li>
                    <label class="lbl-txt">合同扫描件：</label>
                    <c:if test="${empty detail }">
	                    <div class="upload-box">
	                        <input type="text" class="input-text"  name="path" id="conpath" value="${pcProvider.bproviderContract }" />
	                        <input type="file" id="contractFile" name="uploadFile" class="file-upload" onchange="uploadPath3()"/>
	                        <span class="require">*</span>
	                        <button class="browse-btn">浏览</button>
	                    </div>  
	                     <input type="button" class="upload-btn" name="uploadContract" id="uploadContract" value="上传"> 
	                     <input type="hidden" name="bproviderContract" id="bproviderContract" value="${pcProvider.bproviderContract }" > 
                     </c:if>
                     <c:if test="${!empty detail }">
                     	<img src="<%=rootPath %>/${pcProvider.bproviderContract }" width="120" height="90">
                     </c:if>
                </li>
                
           	    <li>
                   <label class="lbl-txt">地址：</label>
                   <span class="query-item">
                       <div class="combo" id="provCombo"></div>
                   </span>
                   <span class="query-item">
                       <div class="combo" id="cityCombo"></div>
                   </span>
                   <span class="query-item">
                       <div class="combo" id="regionCombo"></div>
                   </span>
                   <span class="require">*</span>
                   <input type="hidden" id="provId" name="provId" value="${pcProvider.provId }" >
                   <input type="hidden" id="cityId" name="cityId" value="${pcProvider.cityId }" >
                   <input type="hidden" id="regionId" name="regionId" value="${pcProvider.regionId }" >
               </li>
               
               <li>
                  <label class="lbl-txt">详细地址：</label>
                  <input type="text" name="providerAddress" value="${pcProvider.providerAddress }" class="input-text ver-right" />
               </li>
               
               <li>
                   <label class="lbl-txt">联系人：</label>
                   <input type="text" name="contactName" value="${pcProvider.contactName }" class="input-text ver-right" />
               </li>
               
               <li>
                   <label class="lbl-txt">电话：</label>
                   <input type="text" name="contactPhone" value="${pcProvider.contactPhone }" class="input-text ver-right" />
               </li>
               
               <li>
                   <label class="lbl-txt">手机：</label>
                   <input type="text" name="contactPhone2" value="${pcProvider.contactPhone2}" class="input-text ver-right" />
               </li>
                
           </ul>
          </form>
            <br> <br>
            <c:if test="${empty detail }">
	            <div class="form-aciton">
	                <button class="submit-btn" onclick="savePcPro()">确定</button>
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
    <script src="<%=rootPath %>/page/merchant/js/addMerchant.js"></script>
</body>

</html>
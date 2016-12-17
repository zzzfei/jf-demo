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
    <title>添加类目</title>
    
</head>
<body>
    <div class="asideR-cont">
        <div class="add-cnt">
       	 <form action="" id="addMallCat" method="post">
       	 <!-- 放置类型和id的隐藏域 -->
       	 <input type="hidden" id="type" name="type" value="${type }" >
       	 <input type="hidden" id="mallCatId" name="mallCatId" value="${icMallCat.mallCatId }" >
       	 
            <ul class="add-lst">
            	 <li>
                    <label class="lbl-txt">类目名称：</label>
                    <input type="text" name="mallCatName" value="${icMallCat.mallCatName }" class="input-text ver-right" />
                    <span class="require">*</span>
                </li>
                <br> <br>
                <li>
                    <label class="lbl-txt">图片上传：</label>
                    <div class="upload-box">
                        <input type="text" class="input-text"  name="path" id="path" />
                        <input type="file" id="uploadFile" name="uploadFile" class="file-upload" onchange="uploadPath()"/>
                        <span class="require">*</span>
                        <button class="browse-btn">浏览</button>
                    </div>  
                     <input type="button" class="upload-btn" name="uploadImg" id="uploadImg" value="上传"> 
                     <input type="hidden" name="mallCatPicUrl" id="mallCatPicUrl" value="${icMallCat.mallCatPicUrl }" > 
                     <p style="color:red;margin-left:120px">建议上传图片选取400×300像素图片，大小在300K以下，以达到最佳显示效果。</p>
                </li>
                <br> <br>
                <li>
                    <label class="lbl-txt">类目描述：</label>
                    <textarea class="textarea textarea-lg" name="mallCatDesc" id="text">
						${icMallCat.mallCatDesc }
                    </textarea>
                    <span class="require">*</span>
                </li>
              </ul>
            </form>
            <br> <br>
            <div class="form-aciton">
                <button class="submit-btn" onclick="saveMallCat()">确定</button>
                <button class="quit-btn" id="cancle">取消</button>
            </div>
        </div>
    </div>
	<script type="text/javascript" src="<%=rootPath %>/res/jquery/plugins/jquery.ajaxfileupload.js"></script>
    <script src="<%=rootPath %>/page/system/js/addMallCat.js"></script>
</body>

</html>
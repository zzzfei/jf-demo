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
    <title>评论详情</title>
</head>
<script type="text/javascript">
	$(function(){
		$("#cancle").click(function(){
			
			//手动跳转页面
			window.location.href = rootPath + "/page/item/itemEvaluationMgnt.jsp";
			
		});
	});

</script>
<body>
    <div class="asideR-cont">
        <div class="add-cnt">
            <br> <br>
            
            <center>
            	<table>
            		<tr>
            			<td>商家名称：</td>
            			<td>${itemEvaluationBean.mchtName }</td>
            		</tr>
            		<tr>
            			<td>商品名称：</td>
            			<td>${itemEvaluationBean.itemName }</td>
            		</tr>
            		<tr>
            			<td>评价分值：</td>
            			<td>${itemEvaluationBean.evaluationGrade }</td>
            		</tr>
            		<tr>
            			<td>评论时间：</td>
            			<td>${itemEvaluationBean.evaluationTime }</td>
            		</tr>
            		<tr>
            			<td>评论内容：</td>
            			<td>${itemEvaluationBean.evaluationContent }</td>
            		</tr>
            		<tr>
            			<td colspan="2" align="left">晒单图片：</td>
            		</tr>
            		<tr>
            			<td colspan="2">
            				<img src="<%=rootPath %>/${itemEvaluationBean.itemPicUrl }">
            			</td>
            		</tr>
            	</table>
            </center>
            
            <div class="form-aciton">
                <button class="quit-btn" id="cancle">返回</button>
            </div>
        </div>
    </div>
</body>

</html>
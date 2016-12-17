Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', rootPath + '/res/extjs/ux/');
Ext.require([
    'Ext.data.*',
    'Ext.grid.*',
    'Ext.util.*',
    'Ext.form.field.ComboBox',
    'Ext.form.FieldSet',
    'Ext.tip.QuickTipManager',
    'Ext.ux.data.PagingMemoryProxy'
    
]);

var data, store, columns, queryGrid,pager;
Ext.onReady(function(){
    onfocus();
    initButton();
});

function initButton(){
	
	// 取消操作
	$("#cancle").click(function(){
		window.location.href = rootPath + "/page/system/mallCatMgnt.jsp";
	});
	
	$("#uploadImg").click(function(){
		// 验证图片格式
		var ext = '.jpg.jpeg.gif.bmp.png.';
		var f = $("#uploadFile").val();
		if (f == "") {
			alert("请选择文件！");
			return false;
		}
		f = f.substr(f.lastIndexOf('.') + 1).toLowerCase();
		if (ext.indexOf('.' + f + '.') == -1) {
			alert("图片格式不正确！");
			return false;
		}
		
		 $.ajaxFileUpload({
			 url:rootPath + "/system/uploadFile",
		 	 dataType:"json",
		 	 fileElementId:"uploadFile",
		 	 success:function(result){
		 		 // 接收图片路径，给隐藏域赋值
		 		 alert(result.message);
		 		 $("#mallCatPicUrl").val(result.fileName);
		 		 alert(result.fileName);
		 	 },
		 	 error:function(result){
		 		alert("图片上传失败，请联系管理员");
		 	 }
		 });
	});
}

/*
 * 点击确定按钮
 */
function saveMallCat(){
	
	// 判断  新增 / 修改
	var type = $("#type").val();
	// 声明要提交的url
	var url = "";
	
	if(type == "add"){
		url = rootPath + "/system/add";
	}else{
		url = rootPath + "/system/modify";
	}
	
	// ajax 方式提交表单
	//1、获取表单参数
	var data = $("#addMallCat").serialize();
	var type = "json";
	$.post(url,data,function(result){
		alert(result.msg);
		//手动跳转页面
		window.location.href = rootPath + "/page/system/mallCatMgnt.jsp";
	},type);
	
	//alert("保存成功");
	//手动跳转页面
	//window.location.href = rootPath + "/page/user/mallCatMgnt.jsp";
	
}


/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}

//图片路径赋值 
function uploadPath(){
	 var path = $("#uploadFile").val();
     $("#path").val(path);
     $("#img").attr('src',path);
     $("#img").next().css('display','none'); 
}


function onfocus(){
    $("input[type='text']").focus(function(){
        $(this).addClass("blur");
    })
     $("input[type='text']").blur(function(){
        $(this).removeClass("blur");
    })
}
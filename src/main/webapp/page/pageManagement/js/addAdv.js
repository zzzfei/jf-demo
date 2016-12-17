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

var data, store, queryGrid, pager;
Ext.onReady(function(){
    
    initCombo();
    /*自定义table的滚动条*/
    initDateTime();
    initButton();
});

// 日期
function initDateTime() {
	// 开始时间
	$("#startDateBox").live("click", function() {
		WdatePicker({
					el : "startDate",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
	// 结束时间
	$("#endDateBox").live("click", function() {
		WdatePicker({
					el : "endDate",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
}

//初始化区域下拉框 1级
function initCombo(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['areaCode', 'areaName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	areaLevel : '2'
            },
	        type: 'ajax',
	        url : rootPath + '/areaDef/getAreaDefForAdv'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'areaNameCombo',
        editable: false, // 不允许输入
        // 下拉框显示的值
        displayField: 'areaName',
        // 下拉框提交的值
        valueField: 'areaCode',
        editable: false,
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给areaId赋值
        		$("#areaId").val(this.getValue());
        	},
			// 当页面重新渲染时，设置下拉框选中回显
			render:function(value){
				//重新加载数据
				store.load();
				//回显下拉框
				this.setValue($("#areaId").val());
			}
        }
    });
}


function initButton(){
	
	// 取消操作
	$("#cancle").click(function(){
		window.location.href = rootPath + "/page/pageManagement/advMgnt.jsp";
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
			 url:rootPath + "/icAdv/uploadFile",
		 	 dataType:"json",
		 	 fileElementId:"uploadFile",
		 	 success:function(result){
		 		 alert(result.message);
		 		 
		 		// 接收图片路径，给隐藏域赋值
		 		 $("#advPic").val(result.fileName);
		 		 alert(result.fileName);
		 	 },
		 	 error:function(result){
		 		alert("图片上传失败，请联系管理员");
		 	 }
		 });
	});
}

// 图片页面路径赋值(只是显示用) 
function uploadPath(){
	 var path = $("#uploadFile").val();
     $("#path").val(path);
     $("#img").attr('src',path);
     $("#img").next().css('display','none'); 
}


/**
 * 保存操作
 */
function saveIcAdv(){
	
	//alert($("#startDate").val());
	
	// 判断当前操作是保存还是修改
	var type = $("#type").val();
	
	var url = "";
	// 根据不同的操作给url赋值
	if(type == "add"){
		url = rootPath + "/icAdv/add";
	}else{
		url = rootPath + "/icAdv/update";
	}
	
	// ajax 方式提交表单
	// 获取表单参数
	var data = $("#addIcAdv").serialize();
	
	var type = "json";
	var callback = function(result){
		alert(result.msg);
		// 页面跳转
		window.location.href = rootPath + "/page/pageManagement/advMgnt.jsp";
	};
	
	$.post(url,data,callback,type);
	
}



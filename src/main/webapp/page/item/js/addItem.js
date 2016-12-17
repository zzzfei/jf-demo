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
    
    /*自定义table的滚动条*/
    initDateTime();
    initButton();
    initCombo("itemTypeCombo");
    initCombo2("mallCatCombo");
    initCombo3("saleAttrCombo");
    initCombo4("mchtCombo");
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

//初始化商品类型下拉框
function initCombo(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['itemType', 'itemTypeName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
	        type: 'ajax',
	        url : rootPath + '/itemType/getItemType'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'itemTypeCombo',
        editable: false, // 不允许输入 
        // 下拉框显示的值
        displayField: 'itemTypeName',
        // 下拉框提交的值
        valueField: 'itemType',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给advState赋值
        		$("#itemType").val(this.getValue());
        	},
			// 当页面重新渲染时，设置下拉框选中回显
			render:function(value){
				//重新加载数据
				store.load();
				//回显下拉框
				this.setValue($("#itemType").val());
			}
        }
    });
}

// 初始化商品类别下拉框
function initCombo2(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['mallCatId', 'mallCatName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
	        type: 'ajax',
	        url : rootPath + '/system/getIcMallCatForCombo'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'mallCatCombo',
        editable: false, // 不允许输入
        // 下拉框显示的值
        displayField: 'mallCatName',
        // 下拉框提交的值
        valueField: 'mallCatId',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给advPos赋值
        		$("#mallCatId").val(this.getValue());
        	},
			// 当页面重新渲染时，设置下拉框选中回显
			render:function(value){
				//重新加载数据
				store.load();
				//回显下拉框
				this.setValue($("#mallCatId").val());
			}
        }
    });
}

//初始化商品销售状态下拉框
function initCombo3(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['codeValue', 'codeName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	codeType : '1004'
            },
	        type: 'ajax',
	        url : rootPath + '/codeValue/getCodeValue'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'saleAttrCombo',
        // 下拉框显示的值
        displayField: 'codeName',
        // 下拉框提交的值
        valueField: 'codeValue',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给opKind赋值
        		$("#itemSaleAttr").val(this.getValue());
        	},
			// 当页面重新渲染时，设置下拉框选中回显
			render:function(value){
				//重新加载数据
				store.load();
				//回显下拉框
				this.setValue($("#itemSaleAttr").val());
			}
        }
    });
}

//初始化商家下拉框
function initCombo4(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['mchtId', 'mchtName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
	        type: 'ajax',
	        url : rootPath + '/ScMerchantController/getMchtForCombo'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'mchtCombo',
        editable: false, // 不允许输入
        // 下拉框显示的值
        displayField: 'mchtName',
        // 下拉框提交的值
        valueField: 'mchtId',
        editable: false,
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给areaId赋值
        		$("#mchtId").val(this.getValue());
        		$("#mchtName").val(this.getRawValue());
        	},
			// 当页面重新渲染时，设置下拉框选中回显
			render:function(value){
				//重新加载数据
				store.load();
				//回显下拉框
				this.setValue($("#mchtId").val());
			}
        }
    });
}

/*
 * 图片上传操作
 */
function initButton(){
	
	// 取消操作
	$("#cancle").click(function(){
		window.location.href = rootPath + "/page/item/itemMgnt.jsp";
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
			 url:rootPath + "/icItemController/uploadFile",
		 	 dataType:"json",
		 	 fileElementId:"uploadFile",
		 	 success:function(result){
		 		 alert(result.message);
		 		 
		 		// 接收图片路径，给隐藏域赋值
		 		 $("#itemPicUrl").val(result.fileName);
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
	
	alert($("#startDate").val());
	
	// 判断当前操作是保存还是修改
	var type = $("#type").val();
	
	var url = "";
	// 根据不同的操作给url赋值
	if(type == "add"){
		url = rootPath + "/icItemController/add";
	}else{
		url = rootPath + "/icItemController/update";
	}
	
	// ajax 方式提交表单
	// 获取表单参数
	var data = $("#addIcItem").serialize();
	
	var type = "json";
	var callback = function(result){
		alert(result.msg);
		// 页面跳转
		window.location.href = rootPath + "/page/item/itemMgnt.jsp";
	};
	
	$.post(url,data,callback,type);
	
}



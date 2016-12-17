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

var data, store, store2, store3, queryGrid, pager;
Ext.onReady(function(){
    
    initCombo1("balanceTermCombo");
    initCombo2("balanceTypeCombo");
    initCombo3("provCombo");
    initCombo4("cityCombo");
    initCombo5("regionCombo");

    initButton();
    initButton2();
    initButton3();
});

//初始化结算周期下拉框
function initCombo1(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['codeValue', 'codeName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	codeType : '1005'
            },
	        type: 'ajax',
	        url : rootPath + '/codeValue/getCodeValue'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'balanceTermCombo',
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
        		$("#balanceTerm").val(this.getValue());
        	},
        	// 当页面重新渲染时，设置下拉框选中回显
    		render:function(value){
    			//重新加载数据
    			store.load();
    			//回显下拉框
    			this.setValue($("#balanceTerm").val());
    		}
        }
        
    });
}

//初始化结算方式下拉框
function initCombo2(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['codeValue', 'codeName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	codeType : '1006'
            },
	        type: 'ajax',
	        url : rootPath + '/codeValue/getCodeValue'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'balanceTypeCombo',
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
        		$("#balanceType").val(this.getValue());
        	},
        	// 当页面重新渲染时，设置下拉框选中回显
    		render:function(value){
    			//重新加载数据
    			store.load();
    			//回显下拉框
    			this.setValue($("#balanceType").val());
    		}
        }
    });
}

var provId;
//初始化区域下拉框provCombo
function initCombo3(){
	// 生成下拉框数据源
    store = Ext.create('Ext.data.Store', {
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
	        url : rootPath + '/areaDef/getAreaDef'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'provCombo',
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
        		// 给provId赋值
        		$("#provId").val(this.getValue());
        		provId = this.getValue();
        		// 重新生成市级的url地址
        		store2.proxy.url = rootPath + '/areaDef/getAreaDef?supAreaId='+provId;
        		store2.load();
        		
        	},
			// 当页面重新渲染时，设置下拉框选中回显
			render:function(value){
				//重新加载数据
				store.load();
				//回显下拉框
				this.setValue($("#provId").val());
			}
        }
    });
}

var cityId;
//初始化区域下拉框cityCombo
function initCombo4(){
	// 生成下拉框数据源
    store2 = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['areaCode', 'areaName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	areaLevel : '3'
            },
	        type: 'ajax',
	        url : rootPath + '/areaDef/getAreaDef'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'cityCombo',
        editable: false, // 不允许输入
        // 下拉框显示的值
        displayField: 'areaName',
        // 下拉框提交的值
        valueField: 'areaCode',
        editable: false,
        width: 220,
        labelWidth: 130,
        store: store2,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给cityId赋值
        		$("#cityId").val(this.getValue());
        		cityId = this.getValue();
        		// 重新生成区级的url地址
        		store3.proxy.url = rootPath + '/areaDef/getAreaDef?supAreaId='+cityId;
        		store3.load();
        	},
			// 当页面重新渲染时，设置下拉框选中回显
			render:function(value){
				//重新加载数据
				store2.load();
				//回显下拉框
				this.setValue($("#cityId").val());
			}
        }
    });
}

//初始化区域下拉框regionCombo
function initCombo5(){
	// 生成下拉框数据源
    store3 = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['areaCode', 'areaName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	areaLevel : '4'
            },
	        type: 'ajax',
	        url : rootPath + '/areaDef/getAreaDef'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'regionCombo',
        editable: false, // 不允许输入
        // 下拉框显示的值
        displayField: 'areaName',
        // 下拉框提交的值
        valueField: 'areaCode',
        editable: false,
        width: 220,
        labelWidth: 130,
        store: store3,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给regionId赋值
        		$("#regionId").val(this.getValue());
        	},
			// 当页面重新渲染时，设置下拉框选中回显
			render:function(value){
				//重新加载数据
				store3.load();
				//回显下拉框
				this.setValue($("#regionId").val());
			}
        }
    });
}


function initButton(){
	
	// 取消操作
	$("#cancle").click(function(){
		window.location.href = rootPath + "/page/merchant/merchantMgnt.jsp";
	});
	
	// 上传logo
	$("#uploadProviderPic").click(function(){
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
			 url:rootPath + "/pcProvider/uploadFile",
		 	 dataType:"json",
		 	 fileElementId:"uploadFile",
		 	 success:function(result){
		 		 alert(result.message);
		 		 
		 		// 接收图片路径，给隐藏域赋值
		 		 $("#providerPicUrl").val(result.fileName);
		 		 alert(result.fileName);
		 	 },
		 	 error:function(result){
		 		alert(result.message);
		 	 }
		 });
	});
	
}

function initButton2(){
	
	// 上传营业执照
	$("#uploadLicense").click(function(){
		// 验证图片格式
		var ext = '.jpg.jpeg.gif.bmp.png.';
		var f = $("#licenceFile").val();
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
			 url:rootPath + "/pcProvider/uploadFile",
		 	 dataType:"json",
		 	 fileElementId:"licenceFile",
		 	 success:function(result){
		 		 alert(result.message);
		 		 
		 		// 接收图片路径，给隐藏域赋值
		 		 $("#bproviderLicense").val(result.fileName);
		 		 alert(result.fileName);
		 	 },
		 	 error:function(result){
		 		alert(result.message);
		 	 }
		 });
	});
	
}

function initButton3(){
	
	// 上传合同扫描件
	$("#uploadContract").click(function(){
		// 验证图片格式
		var ext = '.jpg.jpeg.gif.bmp.png.';
		var f = $("#contractFile").val();
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
			 url:rootPath + "/pcProvider/uploadFile",
		 	 dataType:"json",
		 	 fileElementId:"contractFile",
		 	 success:function(result){
		 		 alert(result.message);
		 		 
		 		// 接收图片路径，给隐藏域赋值
		 		 $("#bproviderContract").val(result.fileName);
		 		 alert(result.fileName);
		 	 },
		 	 error:function(result){
		 		alert(result.message);
		 	 }
		 });
	});
	
}

// 图片页面路径赋值(只是显示用) 
function uploadPath(){
	 var path = $("#uploadFile").val();
     $("#picpath").val(path);
     $("#img").attr('src',path);
     $("#img").next().css('display','none');
     
}

function uploadPath2(){
	var path = $("#licenceFile").val();
    $("#licpath").val(path);
    $("#img").attr('src',path);
    $("#img").next().css('display','none'); 
}

function uploadPath3(){
    var path = $("#contractFile").val();
    $("#conpath").val(path);
    $("#img").attr('src',path);
    $("#img").next().css('display','none'); 
}


/**
 * 保存操作
 */
function savePcPro(){
	
	//alert($("#startDate").val());
	
	// 判断当前操作是保存还是修改
	var type = $("#type").val();
	
	var url = "";
	// 根据不同的操作给url赋值
	if(type == "add"){
		url = rootPath + "/pcProvider/savePcProvider";
	}else{
		url = rootPath + "/pcProvider/updatePcProvider";
	}
	
	// ajax 方式提交表单
	// 获取表单参数
	var data = $("#addPcProvider").serialize();
	
	var type = "json";
	var callback = function(result){
		alert(result.msg);
		// 页面跳转
		if(result.result == 'success'){
			window.location.href = rootPath + "/page/merchant/merchantMgnt.jsp";
		}
	};
	
	$.post(url,data,callback,type);
	
}



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

var data, store, store3, store4, columns, queryGrid,pager;
Ext.onReady(function(){
    Ext.QuickTips.init();
    Ext.EventManager.onWindowResize(function(){ 
        queryGrid.getView().refresh() ;
    });
    initGrid();
    initCombo("advStateCombo");
    initCombo2("advPosCombo");
    initCombo3("areaNameCombo");
    //initCombo4("areaNameCombo2");
    //initCombo5("areaNameCombo3");
    //initCombo6("areaNameCombo4");
});

// 初始化广告状态下拉框
function initCombo(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['codeValue', 'codeName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	codeType : '1002'
            },
	        type: 'ajax',
	        url : rootPath + '/codeValue/getCodeValue'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'advStateCombo',
        editable: false, // 不允许输入 
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
        		// 给advState赋值
        		$("#advState").val(this.getValue());
        	}
        }
    });
}

// 初始化广告位位置下拉框
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
            	codeType : '2001'
            },
	        type: 'ajax',
	        url : rootPath + '/codeValue/getCodeValue'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'advPosCombo',
        editable: false, // 不允许输入
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
        		// 给advPos赋值
        		$("#advPos").val(this.getValue());
        	}
        }
    });
}

//初始化区域下拉框 1级
function initCombo3(){
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
        	}
        }
    });
}

/*
var proId;
//初始化区域下拉框 2级
function initCombo4(){
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
	        url : rootPath + '/areaDef/getAreaDef'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'areaNameCombo2',
        editable: false, // 不允许输入
        // 下拉框显示的值
        displayField: 'areaName',
        // 下拉框提交的值
        valueField: 'areaCode',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给areaId赋值
        		$("#areaId").val(this.getValue());
        		// 给supAreaId赋值
        		proId = this.getValue();
        		store3.proxy = new Ext.data.HttpProxy({
        			url:rootPath + '/areaDef/getAreaDef?areaLevel=3&supAreaId='+proId,
        			type: 'ajax'
        		});
        		store3.load();
        	}
        }
    });
}
var cityId;
//初始化区域下拉框 3级
function initCombo5(){
	// 生成下拉框数据源
	store3 = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        autoSync:true,
        // 指定下拉框属性
        fields: ['areaCode', 'areaName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
        		areaLevel : '3',
            	supAreaId: proId
            },
	        type: 'ajax',
	        url : rootPath + '/areaDef/getAreaDef'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'areaNameCombo3',
        editable: false, // 不允许输入
        // 下拉框显示的值
        displayField: 'areaName',
        // 下拉框提交的值
        valueField: 'areaCode',
        width: 220,
        labelWidth: 130,
        store: store3,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给areaId赋值
        		$("#areaId").val(this.getValue());
        		// 给supAreaId赋值
        		//$("#supAreaId").val(this.getValue());
        		cityId = this.getValue();
        		
        		store4.load();
        	}
        }
    });
}

// 初始化4及区域下拉框
function initCombo6(){
	// 生成下拉框数据源
	store4 = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['areaCode', 'areaName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
        		areaLevel : '4',
            	supAreaId : cityId
            },
	        type: 'ajax',
	        url : rootPath + '/areaDef/getAreaDef'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'areaNameCombo4',
        // 下拉框显示的值
        displayField: 'areaName',
        editable: false, // 不允许输入
        // 下拉框提交的值
        valueField: 'areaCode',
        width: 220,
        labelWidth: 130,
        store: store4,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给areaId赋值
        		$("#areaId").val(this.getValue());
        	}
        }
    });
}*/

// 初始化列表
function initGrid(){

    // 创建列表需要的数据源 store
    store = Ext.create('Ext.data.Store', {
    	// 自动加载
    	autoLoad: true,
        fields: [
           {name: 'advId', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'advName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'advPos', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'areaName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'advPic', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'advDesc', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'advUrl', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'advStartTime', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'advEndTime', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'advOrder', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'advState', type: 'auto', convert: null, defaultValue: undefined}
         
        ],  
        remoteSort: true,
        // 设置单页显示数量
        pageSize: 4,
        proxy: {
            type: 'ajax',
            url: rootPath + '/icAdv/getIcAdv',
            data: data,
            reader: {
                type: 'json',
                // 指定根节点使用的数据
                //root: 'rows',
                root: 'list', // PageHelper 
                // 指定总记录数
	            totalProperty: 'total'
          
            }
        }
    });
    // width确定的宽度
    columns = [
				{
				    text     : 'ID',
				    width    : 0,
				    align 	 : 'center', 
				    sortable : true,
				    dataIndex: 'advId'
				},
				{
				    text     : '名称',
				    align 	 : 'center', 
				    width    : 90,
				    sortable : true,
				    dataIndex: 'advName',
				    renderer : qtips
				},
				{
				    text     : '广告位',
				    align 	 : 'center', 
				    width    : 60,
				    sortable : true,
				    dataIndex: 'advPos',
				    renderer : thePos
				},
				{
				    text     : '区域',
				    align 	 : 'center', 
				    width    : 30,
				    sortable : true,
				    dataIndex: 'areaName',
				    renderer : qtips
				},
				{
				    text     : '广告位图片',
				    align 	 : 'center', 
				    width    : 130,
				    sortable : true,
				    dataIndex: 'advPic',
				    renderer : imgPath
				},
				{
				    text     : '广告位说明',
				    align 	 : 'center', 
				    width    : 110,
				    sortable : true,
				    dataIndex: 'advDesc',
				    renderer : qtips
				},
				{
				    text     : '链接地址',
				    align 	 : 'center', 
				    width    : 100,
				    sortable : true,
				    dataIndex: 'advUrl',
				    renderer : qtips
				},
				{
				    text     : '播放开始时间',
				    align 	 : 'center', 
				    width    : 110,
				    sortable : true,
				    dataIndex: 'advStartTime',
				    renderer : qtips
				},
				{
				    text     : '播放结束时间',
				    align 	 : 'center', 
				    width    : 110,
				    sortable : true,
				    dataIndex: 'advEndTime',
				    renderer : qtips
				},
				{
				    text     : '调整顺序',
				    align 	 : 'center', 
				    width    : 80,
				    sortable : true,
				    dataIndex: 'advState',
				    renderer : adjustOrder
				},
				{
				    text     : '广告位状态',
				    align 	 : 'center', 
				    width    : 80,
				    sortable : true,
				    dataIndex: 'advState',
				    renderer : advState
				},
				{
				    text: '操作',
				    menuDisabled: true,
				    sortable: false,
				    width: 100,
				    dataIndex:'advState',
				    align   : 'center',
				    renderer: buttonRender
				}
        ];
    // 操作区域
    var dockedItems = [/*{
            xtype: 'toolbar',
            dock: 'bottom',
            ui: 'footer',
            layout: {
                pack: 'center'
            }
        }, */{
            xtype: 'toolbar',
            items: [{
                text:'',
                tooltip:'新建',
                minWidth: 30,
                minHeight:30,
                iconCls:'new-ico',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							window.location.href = rootPath + "/icAdv/toAdd?type=add";
						}
					}
				}
            }]
        }];
    // 多选
    var selModel = Ext.create('Ext.selection.CheckboxModel', {
        listeners: {
            selectionchange: function(sm, selections) {
            }
        }
    });
    //pager
    pager = Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            displayMsg : '显示第 {0} 条到 {1} 条记录,一共 {2} 条'
    });
    // create the Grid
    queryGrid = Ext.create('Ext.grid.Panel', {
        store: store,
        stateful: true,
        collapsible: false,
        multiSelect: true,
        stateId: 'stateGrid',
        columns: columns,
        selModel: selModel,
        dockedItems: dockedItems,
        autoHeight: true,
        autoWidth: true,
        renderTo: 'queryGrid',
        /*resizable: {
          handles: 's',
          minHeight: 100
        },*/
        bbar: pager,
        viewConfig: {
            stripeRows: true,
            enableTextSelection: true,
            deferRowRender : false,
            forceFit : true,
            emptyText : "<font class='emptyText'>没有符合条件的记录</font>",
            autoScroll:true,
            scrollOffset:-10
        }
    });
    store.load();
   
}


/*
 * 调整顺序
 */
function adjustOrder(value, meta, record, rowIndex, colIndex, store){
	var returnValue = "";
	var advId = record.data.advId;
	var advOrder = record.data.advOrder;
	if(value == '1'){
		if(advOrder == '1'){
			returnValue += '<button onclick="orderDown('+advId+')">下移</button><br><br>';
		}else{
			returnValue += '<button onclick="orderUp('+advId+')">上移</button><br><br>' + 
						   '<button onclick="orderDown('+advId+')">下移</button><br><br>';
		}
	}
	
	return returnValue;
}

/**
 * 上移操作
 */
function orderUp(advId){
	var url = rootPath + "/icAdv/orderUp";
	var data = {
		advId:advId,
		type:"up"
	};
	var callback = function(result){
		alert(result.msg);
		// 重新加载数据
		store.load();
	};
	var type = "json";
	$.post(url, data, callback, type);
}

/**
 * 下移操作
 */
function orderDown(advId){
	var url = rootPath + "/icAdv/orderUp";
	var data = {
		advId:advId,
		type:"down"
	};
	var callback = function(result){
		alert(result.msg);
		// 重新加载数据
		store.load();
	};
	var type = "json";
	$.post(url, data, callback, type);
}


/*
 * 广告位
 */
function thePos(value){
	if(value == '1'){
		return "首页";
	}else{
		return "特价区";
	}
	
}

/*
 * 广告位状态
 */
function advState(value){
	if(value == '1'){
		return '已上架';
	}else if(value == '2'){
		return '已下架';
	}else{
		return '待审核';
	}
}


/*
* 操作
*/
function buttonRender(value, meta, record, rowIndex, colIndex, store) {
    var returnValue = "";
    var state = record.data.state;
    var advId = record.data.advId;
    
    if(value == '3'){
    	returnValue += '<em class="page-ico" title="查看" onclick="detail('+advId+')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看</em><br><br>'+
	    '<em class="modify-ico" title="修改" onclick="modify('+advId+')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</em><br><br>'+
	    '<em class="del-ico" title="删除" onclick="deleteAdv('+advId+')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</em><br><br>' + 
	    '<em class="acc-ico" title="发布" onclick="upRe('+advId+')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布</em><br><br>';
    }else if(value == '2'){
    	returnValue += '<em class="page-ico" title="查看" onclick="detail('+advId+')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看</em><br><br>'+
	    '<em class="modify-ico" title="修改" onclick="modify('+advId+')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</em><br><br>'+
	    '<em class="del-ico" title="删除" onclick="deleteAdv('+advId+')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</em><br><br>' + 
	    '<em class="set-ico" title="上架" onclick="upRe('+advId+')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上架</em>';
    	
    }else{
   	 	returnValue += '<em class="page-ico" title="查看" onclick="detail('+advId+')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看</em><br><br>'+
   	    '<em class="supp-ico" title="下架" onclick="downRe('+advId+')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下架</em>';
    }
    
    return returnValue;
}

/*
 * 图片路径
 */
function imgPath(value){
	return "<img src=" + rootPath + value + " height='80' width='80'>";
}

/*
 * 上架操作
 */
function upRe(advId){
	var url = rootPath + "/icAdv/update";
	var data = {
			advId:advId,
			advState:1
	};
	var callback = function(result){
		alert(result.msg);
		// 重新加载数据
		store.load();
	};
	var type = "json";
	$.post(url, data, callback, type);
	
}

/*
 * 下架操作
 */
function downRe(advId){
	var url = rootPath + "/icAdv/update";
	var data = {
			advId:advId,
			advState:2
	};
	var callback = function(result){
		alert(result.msg);
		// 重新加载数据
		store.load();
	};
	var type = "json";
	$.post(url, data, callback, type);
	
}

/*
 * 查看详情
 */
function detail(advId){
	window.location.href = rootPath + "/icAdv/toAdd?type=detail&advId="+advId;
}


/*
 * 执行修改操作
 */
function modify(advId){
	window.location.href = rootPath + "/icAdv/toAdd?type=modify&advId="+advId;
}

/*
 * 执行删除操作
 */
function deleteAdv(advId){
	if(!confirm("您确定要删除吗？")){
		return false;
	}
	
	// 获取store中的  所有记录数、单页展示记录数、当前页码
	var totalCount = store.getTotalCount();
	var pageSize = store.pageSize;
	var curPage = store.currentPage;
	
	// 找出记录号和记录条数
	// 当前页展示的起始记录号
	var fromRecord = ((curPage - 1) * pageSize) + 1; 
    // 当前页展示的结尾记录号
    var toRecord = Math.min(curPage * pageSize, totalCount); 
    // 当前页展示的记录条数 
    var totalOnCurPage = toRecord - fromRecord + 1; 
    // 总的页数  
    var totalPage = Math.ceil(totalCount / pageSize); 
    
    // 若当前页是最后一页，且不是仅有的一页，且删除的记录数是当前页上的最后一条（因为我们是单条删除）
    if (curPage === totalPage && totalPage != 1 && totalOnCurPage == 1)  
    {  
    	// 更改store的当前页为前一页
    	store.currentPage = store.currentPage-1;  
    }    
	
	var url = rootPath + "/icAdv/delete";
	var data = {advId:advId};
	var callback = function(result){
		alert(result.msg);
		// 重新加载数据
		store.load();
	};
	var type = "json";
	$.post(url, data, callback, type);
	
}

/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}

/*
 * 按条件查询
 */
function query(){
	store.proxy.extraParams = {
		advState:$("#advState").val(),
		advPos:$("#advPos").val(),
		advAreaId:$("#areaId").val(),
		advName:$("#advName").val()
	},
	
	// 加载store数据
	store.load();
}

/*
 * 重置查询
 */
function reset(){
	window.location.href = rootPath + "/page/pageManagement/advMgnt.jsp";
}

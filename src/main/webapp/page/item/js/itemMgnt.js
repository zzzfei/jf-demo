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
    initCombo("itemTypeCombo");
    initCombo2("mallCatCombo");
    initCombo3("saleAttrCombo");
    initCombo4("mchtCombo");
    initCombo5("itemApprStateCombo");
    
});

// 初始化商品类型下拉框
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
        	extraParams:{
            	areaLevel : '2'
            },
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
        	}
        }
    });
}

//初始化商品审批状态下拉框
function initCombo5(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['codeValue', 'codeName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	codeType : '1001'
            },
	        type: 'ajax',
	        url : rootPath + '/codeValue/getCodeValue'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'itemApprStateCombo',
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
        		$("#itemApprState").val(this.getValue());
        	}
        }
    });
}


// 初始化列表
function initGrid(){

    // 创建列表需要的数据源 store
    store = Ext.create('Ext.data.Store', {
    	// 自动加载
    	autoLoad: true,
        fields: [
           {name: 'itemId', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemCode', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemPicUrl', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemTypeName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'mallCatName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemMarketPrice', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemSalePrice', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemValidStart', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemValidEnd', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemSaleAttr', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemApprState', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemUdState', type: 'auto', convert: null, defaultValue: undefined}
        ],  
        remoteSort: true,
        // 设置单页显示数量
        pageSize: 4,
        proxy: {
            type: 'ajax',
            url: rootPath + '/icItemController/getIcItemForList',
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
				    dataIndex: 'itemId'
				},
				{
				    text     : '编码',
				    align 	 : 'center', 
				    width    : 50,
				    sortable : true,
				    dataIndex: 'itemCode',
				    renderer : qtips
				},
				{
				    text     : '名称',
				    align 	 : 'center', 
				    width    : 120,
				    sortable : true,
				    dataIndex: 'itemName',
				    renderer : qtips
				},
				{
				    text     : '商品主图',
				    align 	 : 'center', 
				    width    : 120,
				    sortable : true,
				    dataIndex: 'itemPicUrl',
				    renderer : imgPath
				},
				{
				    text     : '类型',
				    align 	 : 'center', 
				    width    : 60,
				    sortable : true,
				    dataIndex: 'itemTypeName'
				},
				{
				    text     : '类别',
				    align 	 : 'center', 
				    width    : 60,
				    sortable : true,
				    dataIndex: 'mallCatName'
				},
				{
				    text     : '原价',
				    align 	 : 'center', 
				    width    : 40,
				    sortable : true,
				    dataIndex: 'itemMarketPrice',
				    renderer : qtips
				},
				{
				    text     : '售价',
				    align 	 : 'center', 
				    width    : 40,
				    sortable : true,
				    dataIndex: 'itemSalePrice',
				    renderer : qtips
				},
				{
				    text     : '有效期起',
				    align 	 : 'center', 
				    width    : 100,
				    sortable : true,
				    dataIndex: 'itemValidStart',
				    renderer : qtips
				},
				{
				    text     : '有效期止',
				    align 	 : 'center', 
				    width    : 100,
				    sortable : true,
				    dataIndex: 'itemValidEnd',
				    renderer : qtips
				},
				{
				    text     : '销售状态',
				    align 	 : 'center', 
				    width    : 130,
				    sortable : true,
				    dataIndex: 'itemSaleAttr',
				    renderer : saleState
				},
				{
				    text     : '审批状态',  
				    align 	 : 'center', 
				    width    : 0,
				    sortable : true,
				    dataIndex: 'itemApprState',
				    renderer : apprState
				},
				{
				    text     : '上架状态',  
				    align 	 : 'center', 
				    width    : 90,
				    sortable : true,
				    dataIndex: 'itemUdState',
				    renderer : udState
				},
				{
				    text: '操作',
				    menuDisabled: true,
				    sortable: false,
				    width: 100,
				    dataIndex:'itemApprState',
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
							window.location.href = rootPath + "/icItemController/toAdd?type=add";
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
    
    // 加载store数据
    store.load();
}

/*
 * 销售状态
 */
function saleState(value){
	if(value == '00'){
		return '非免预约，非随时退';
	}else if(value == '01'){
		return '非免预约，随时退';
	}else if(value == '10'){
		return '免预约，非随时退';
	}else{
		return '免预约，随时退';
	}
}

/*
 * 审批状态
 */
function apprState(value){
	if(value == '1'){
		return '未提交';
	}else if(value == '2'){
		return '待审批';
	}else if(value == '3'){
		return '审批通过';
	}else{
		return '审判不通过';
	}
}

/*
 * 上架状态
 */
function udState(value){
	if(value == '1'){
		return '已上架';
	}else if(value == '2'){
		return '已下架';
	}else if(value == '3'){
		return '已强制下架';
	}else{
		return '已替换下架 ';
	}
}

/*
* 操作
*/
function buttonRender(value, meta, record, rowIndex, colIndex, store) {
    var returnValue = "";
    var state = record.data.state;
    // 获取商品ID
    var itemId = record.data.itemId;
    // 获取上架状态
    var itemUdState = record.data.itemUdState;
    // 获取审判状态
    var itemApprState = record.data.itemApprState;
    
    var detail = '<button title="查看" onclick="detail('+itemId+')">查看详情</button><br><br>';
    var modify = '<em class="modify-ico" title="修改" onclick="modify('+itemId+')"></em><br><br>';
    var apprPass = '<button onclick="apprPass('+itemId+')">审批通过</button><br><br>';
    var apprStop = '<button onclick="apprStop('+itemId+')"><span style="color:red">审批不通过</span></button><br><br>';
    var udOn = '<button onclick="udOn('+itemId+')">上架</button><br><br>';
    var udDown = '<button onclick="udDown('+itemId+')">强制下架</button><br><br>';
    var deleteItem = '<button onclick="deleteItem('+itemId+')">屏蔽</button><br><br>';
    var waitAppr = '<em title="待审批">待审批</em><br><br>';
    
    if(itemApprState == '2'){
    	// 待审判状态可以进行审批（通过和不通过） (移动到审批列表进行审批)
    	// 通过后为下架状态
    	returnValue += detail + '待审批'/*+ apprPass + apprStop*/;
    }else if(itemApprState == '4'){
    	// 审判不通过商品可以进行修改，修改完之后变为下架状态
    	returnValue += detail + modify + apprStop;
    }
    
    if(itemUdState == '1' && itemApprState == '3'){
    	// 已上架商品可进行下架和修改操作，修改完变为待审批
    	returnValue += detail + modify + udDown;
    }else if(itemUdState == '2' && itemApprState == '3'){
    	// 已下架商品可进行上架和修改操作，修改完成变为待审批
    	returnValue += detail + modify + udOn;
    }else if(itemUdState == '3' || itemUdState == '4' && itemApprState == '3'){
    	// 已替换下架、强制下架商品可进行上架操作
    	returnValue += detail + udOn;
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
 * 审批通过
 */
function apprPass(itemId){
	var url = rootPath + "/icItemController/updateState";
	var data = {
			itemId:itemId,
			itemApprState:3
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
 * 审批不通过
 */
function apprStop(itemId){
	var url = rootPath + "/icItemController/updateState";
	var data = {
			itemId:itemId,
			itemApprState:4
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
 * 上架操作
 */
function udOn(itemId){
	var url = rootPath + "/icItemController/updateState";
	var data = {
			itemId:itemId,
			itemUdState:1
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
function udDown(itemId){
	var url = rootPath + "/icItemController/updateState";
	var data = {
			itemId:itemId,
			itemUdState:2
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
function detail(itemId){
	window.location.href = rootPath + "/icItemController/toAdd?type=detail&itemId="+itemId;
}


/*
 * 执行修改操作
 */
function modify(itemId){
	window.location.href = rootPath + "/icItemController/toAdd?type=modify&itemId="+itemId;
}

/*
 * 执行删除操作
 */
function deleteItem(itemId){
	if(!confirm("您确定要屏蔽吗？")){
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
	
	var url = rootPath + "/icItemController/delete";
	var data = {itemId:itemId};
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
		itemType:$("#itemType").val(),
		mallCatId:$("#mallCatId").val(),
		itemSaleAttr:$("#itemSaleAttr").val(),
		itemName:$("#itemName").val(),
		mchtId:$("#mchtId").val(),
		itemApprState:$("#itemApprState").val()
	},
	
	// 加载store数据
	store.load();
}

/*
 * 重置查询
 */
function reset(){
	window.location.href = rootPath + "/page/item/itemMgnt.jsp";
}

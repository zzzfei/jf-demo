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
    //initCombo2("advPosCombo");
    //initCombo3("areaNameCombo");
    //initCombo4("areaNameCombo2");
    //initCombo5("areaNameCombo3");
    //initCombo6("areaNameCombo4");
});

// 初始化供应商类型下拉框
function initCombo(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['providerShortName', 'providerShortName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
	        type: 'ajax',
	        url : rootPath + '/pcProvider/getPcProviderForCombo'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'advStateCombo',
        editable: false, // 不允许输入 
        // 下拉框显示的值
        displayField: 'providerShortName',
        // 下拉框提交的值
        valueField: 'providerShortName',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给advState赋值
        		$("#providerShortName").val(this.getValue());
        	}
        }
    });
}

/*
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
*/
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
           {name: 'providerId', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'providerName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'providerShortName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'providerPointRatio', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'providerPointFee', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'balanceTerm', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'balanceType', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'contactName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'contactPhone', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'contactPhone2', type: 'auto', convert: null, defaultValue: undefined}
        ],  
        remoteSort: true,
        // 设置单页显示数量
        pageSize: 4,
        proxy: {
            type: 'ajax',
            url: rootPath + '/pcProvider/getPcProvider',
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
				    dataIndex: 'providerId'
				},
				{
				    text     : '供应商名称',
				    align 	 : 'center', 
				    width    : 120,
				    sortable : true,
				    dataIndex: 'providerName',
				    renderer : providerName
				},
				{
				    text     : '供应商类型',
				    align 	 : 'center', 
				    width    : 90,
				    sortable : true,
				    dataIndex: 'providerShortName'
				},
				{
				    text     : '兑换比例',
				    align 	 : 'center', 
				    width    : 120,
				    sortable : true,
				    dataIndex: 'providerPointRatio',
				    renderer : providerPointRatio
				},
				{
				    text     : '兑换手续费',
				    align 	 : 'center', 
				    width    : 110,
				    sortable : true,
				    dataIndex: 'providerPointFee'
				},
				{
				    text     : '结算周期',
				    align 	 : 'center', 
				    width    : 110,
				    sortable : true,
				    dataIndex: 'balanceTerm',
				    renderer : balanceTerm
				},
				{
				    text     : '结算方式',
				    align 	 : 'center', 
				    width    : 100,
				    sortable : true,
				    dataIndex: 'balanceType',
				    renderer : balanceType
				},
				{
				    text     : '联系人',
				    align 	 : 'center', 
				    width    : 110,
				    sortable : true,
				    dataIndex: 'contactName',
				    renderer : qtips
				},
				{
				    text     : '联系方式',
				    align 	 : 'center', 
				    width    : 130,
				    sortable : true,
				    dataIndex: 'contactPhone',
				    renderer : contactPhone
				},
				{
				    text     : '联系方式2',
				    align 	 : 'center', 
				    width    : 0,
				    sortable : true,
				    dataIndex: 'contactPhone2'
				},
				{
				    text: '操作',
				    menuDisabled: true,
				    sortable: false,
				    width: 110,
				    dataIndex:'providerId',
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
							window.location.href = rootPath + "/pcProvider/toAdd?type=add";
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
* 操作
*/
function buttonRender(value, meta, record, rowIndex, colIndex, store) {
    var returnValue = "";
    var state = record.data.state;
    var providerId = record.data.providerId;
    
    returnValue += '<em class="modify-ico" title="修改" onclick="modify('+providerId+')"></em>'+
    			   '<em class="del-ico" title="删除" onclick="deleteAdv('+providerId+')"></em>';
    
    return returnValue;
}

/*
 * 点击供应商名称查看详情
 */
function providerName(value, meta, record, rowIndex, colIndex, store){
	var state = record.data.state;
    var providerId = record.data.providerId;
    var href = rootPath + '/pcProvider/toAdd?type=detail&providerId='+providerId;
    
	return "<a href="+href+">"+value+"</a>";
}
	
/*
 * 兑换比例展示
 */
function providerPointRatio(value){
	return value + " : 1"
}

/*
 * 电话号码展示
 */
function contactPhone(value, meta, record, rowIndex, colIndex, store){
    var state = record.data.state;
    var contactPhone = record.data.contactPhone;
    var contactPhone2 = record.data.contactPhone2;
    
    if(contactPhone != '' && contactPhone2 != ''){
    	return contactPhone + '<br><br>' + contactPhone2;
    }else if(contactPhone != '' && contactPhone2 == ''){
    	return contactPhone;
    }else if(contactPhone == '' && contactPhone2 != ''){
    	return contactPhone2;
    }
}

/*
 * 结算周期
 */
function balanceTerm(value){
	if(value == '1'){
		return '日结';
	}else if(value == '2'){
		return '周结';
	}else if(value == '3'){
		return '月结';
	}else if(value == '4'){
		return '季结';
	}
}

/*
 * 结算方式
 */
function balanceType(value){
	if(value == '1'){
		return '线上';
	}else if(value == '2'){
		return '线下';
	}
}

/*
 * 查看详情
 */
function detail(providerId){
	window.location.href = rootPath + "/pcProvider/toAdd?type=detail&providerId="+providerId;
}


/*
 * 执行修改操作
 */
function modify(providerId){
	window.location.href = rootPath + "/pcProvider/toAdd?type=modify&providerId="+providerId;
}

/*
 * 执行删除操作
 */
function deleteAdv(providerId){
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
	
	var url = rootPath + "/pcProvider/deletePcProvider";
	var data = {providerId:providerId};
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
		providerShortName : $("#providerShortName").val(),
		providerName : $("#providerName").val()
	},
	
	// 加载store数据
	store.load();
}

/*
 * 重置查询
 */
function reset(){
	window.location.href = rootPath + "/page/merchant/merchantMgnt.jsp";
}

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
	// tips 悬浮窗
    Ext.QuickTips.init();
    // 自适应宽高
    Ext.EventManager.onWindowResize(function(){ 
        queryGrid.getView().refresh() ;
    });
    // 初始化列表
    initGrid();
    // 初始化下拉框
    initCombo();
});

// 初始化下拉框
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
            	codeType : '1001'
            },
	        type: 'ajax',
	        url : rootPath + '/codeValue/getCodeValue'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'simpleCombo',
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
        		$("#evaluationStatus").val(this.getValue());
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
           {name: 'evaluationId', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'userNickName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemPicUrl', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'evaluationGrade', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'evaluationContent', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'itemPicUrlEv', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'mchtName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'evaluationTime', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'evaluationStatus', type: 'auto', convert: null, defaultValue: undefined}
           
        ],  
        remoteSort: true,
        // 设置单页显示数量
        pageSize: 7,
        proxy: {
            type: 'ajax',
            url: rootPath + '/item/getItemEvaluation',
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
                text     : '评论ID',
                width    : 50,
                align 	 : 'center', 
                sortable : true,
                dataIndex: 'evaluationId'
            },
            {
                text     : '评论帐号',
                align 	 : 'center', 
                width    : 60,
                sortable : true,
                dataIndex: 'userNickName',
                renderer : qtips
            },
            {
                text     : '商品名称',
                align 	 : 'center', 
                width    : 150,
                sortable : true,
                dataIndex: 'itemName',
                renderer : qtips
            },
            {
                text     : '商品图片',
                align 	 : 'center', 
                width    : 110,
                sortable : true,
                dataIndex: 'itemPicUrl',
                renderer : imgPath
                
            },
            {
                text     : '评论分值',
                align 	 : 'center', 
                width    : 80,
                sortable : true,
                dataIndex: 'evaluationGrade',
                renderer : qtips
            },
            {
                text     : '评论内容',
                align 	 : 'center', 
                width    : 150,
                sortable : true,
                dataIndex: 'evaluationContent',
                renderer : qtips
            },
            {
                text     : '评论图片',
                align 	 : 'center', 
                width    : 110,
                sortable : true,
                dataIndex: 'itemPicUrlEv',
                renderer : imgPath
                
            },
            {
                text     : '评论店铺',
                align 	 : 'center', 
                width    : 110,
                sortable : true,
                dataIndex: 'mchtName',
                renderer : qtips
            },
            {
                text     : '评论时间',
                align 	 : 'center', 
                width    : 110,
                sortable : true,
                dataIndex: 'evaluationTime',
                renderer : qtips
            },
            {
                text: '操作',
                menuDisabled: true,
                sortable: false,
                width: 100,
                dataIndex:'evaluationStatus',
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
							window.location.href = rootPath + "/user/toAdd?type=add";
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
* 操作按钮
*/
function buttonRender(value, meta, record, rowIndex, colIndex, store) {
    var returnValue = "";
    var state = record.data.state;
    var evaluationId = record.data.evaluationId;
    
    if(value == '1'){
    	return '<button onclick="detail('+evaluationId+')">详情</button><br><br>' +
    	'<button onclick="toExamine('+evaluationId+')">审核</button><br><br>' + 
    	'<button onclick="shield('+evaluationId+')">屏蔽</button>';
    }else if(value == '2'){
    	return '<button onclick="detail('+evaluationId+')">详情</button><br><br>' +
    	"<span style='color:green'>审核通过</span><br><br>" + 
    	'<button onclick="shield('+evaluationId+')">屏蔽</button>';
    }else if(value == '3'){
    	return '<button onclick="detail('+evaluationId+')">详情</button><br><br>' +
    	"<span style='color:red'>审核不通过</span><br><br>" + 
    	'<button onclick="shield('+evaluationId+')">屏蔽</button>';
    }
    
    /*returnValue += '<em class="modify-ico" title="修改" onclick="modify('+opId+')"></em>'+
                    '<em class="del-ico" title="删除" onclick="deleteSysOp('+opId+')"></em>';*/
    
    //return returnValue;
}

/*
 * 图片路径
 */
function imgPath(value){
	return "<img src=" + rootPath + value + " height='80' width='80'>";
}


/*
 * 查看详情 
 */
function detail(evaluationId){
	window.location.href = rootPath + '/item/detail?evaluationId='+evaluationId;
}

/*
 * 执行审批操作
 */
function toExamine(evaluationId){
	var url = "";
	var data = {};
	if(confirm("是否审判通过此评论？？")){
		url = rootPath + "/item/examine";
		data = {
			evaluationStatus:2,
			evaluationId:evaluationId
		};
	}else{
		url = rootPath + "/item/examine";
		data = {
			evaluationStatus:3,
			evaluationId:evaluationId
		};
	}
	
	var type = 'json';
	$.post(url,data,function(result){
		alert(result.msg);
		// 重新加载列表数据
		store.load();
	},type);
}

/*
 * 执行屏蔽操作
 */
function shield(evaluationId){
	if(!confirm("您确定要屏蔽这条评论吗？")){
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
	
	var url = rootPath + "/item/delete";
	var data = {evaluationId:evaluationId};
	var callback = function(result){
		alert(result.msg);
		// 重新加载数据
		store.load();
	};
	var type = "json";
	$.post(url, data, callback, type);
	
}


/*
 * 是否锁定判断
 */
function ifLockRender(value){
	if(value == '1'){
		return '未锁定';
	}else{
		return '已锁定';
	}
}

/*
 * 用户类型判断
 */
function typeRender(value){
	if(value == '1'){
		return '超级管理员';
	}else if(value == '2'){
		return '管理员';
	}else{
		return '普通用户';
	}
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
		'evaluationStatus':$("#evaluationStatus").val(),
		'userNickName':$("#userNickName").val(),
	},
	
	// 加载store数据
	store.load();
}

/*
 * 重置查询
 */
function reset(){
	window.location.href = rootPath + "/page/user/userMgnt.jsp";
}
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
    Ext.QuickTips.init();
    Ext.EventManager.onWindowResize(function(){ 
        queryGrid.getView().refresh() ;
    });
    initGrid();
});

// 初始化列表
function initGrid(){

    // 创建列表需要的数据源 store
    store = Ext.create('Ext.data.Store', {
    	// 自动加载
    	autoLoad: true,
        fields: [
           {name: 'mallCatId', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'mallCatName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'mallCatPicUrl', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'mallCatDesc', type: 'auto', convert: null, defaultValue: undefined}
        ],  
        remoteSort: true,
        // 设置单页显示数量
        pageSize: 3,
        proxy: {
            type: 'ajax',
            url: rootPath + '/system/getMallCat',
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
                text     : '类目编码',
                width    : 110,
                align 	 : 'center', 
                sortable : true,
                dataIndex: 'mallCatId'
            },
            {
                text     : '类目名称',
                align 	 : 'center', 
                width    : 110,
                sortable : true,
                dataIndex: 'mallCatName',
                renderer : qtips
            },
            {
                text     : '图表',
                align 	 : 'center', 
                width    : 250,
                sortable : true,
                dataIndex: 'mallCatPicUrl',
                renderer : imgPath
                
            },
            {
                text     : '类目描述',
                align 	 : 'center', 
                flex     : 1,
                sortable : false,
                dataIndex: 'mallCatDesc',
                renderer : qtips
            },
            {
                text: '操作',
                menuDisabled: true,
                sortable: false,
                width: 100,
                renderer: buttonRender,
                align   : 'center'
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
                tooltip:'新增类目',
                minWidth: 30,
                minHeight:30,
                iconCls:'new-ico',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							window.location.href = rootPath + "/system/toAdd?type=add";
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
    var mallCatId = record.data.mallCatId;
    returnValue += '<em class="modify-ico" title="修改" onclick="modify('+mallCatId+')"></em>'+
                    '<em class="del-ico" title="删除" onclick="deleteMallCat('+mallCatId+')"></em>';
    
    return returnValue;
}

/*
 * 执行修改操作
 */
function modify(mallCatId){
	window.location.href = rootPath + "/system/toAdd?type=modify&mallCatId="+mallCatId;
}

/*
 * 执行删除操作
 */
function deleteMallCat(mallCatId){
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
	
	var url = rootPath + "/system/delete";
	var data = {mallCatId:mallCatId};
	var callback = function(result){
		alert(result.msg);
		// 重新加载数据
		store.load();
	};
	var type = "json";
	$.post(url, data, callback, type);
	
}

/*
 * 图片路径
 */
function imgPath(value){
	return "<img src=" + rootPath + value + " height='80' width='80'>";
}

/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}


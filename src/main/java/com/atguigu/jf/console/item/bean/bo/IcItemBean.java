package com.atguigu.jf.console.item.bean.bo;

import java.math.BigDecimal;
import java.util.Date;

public class IcItemBean {

	private Long itemId; // ITEM_ID

	private Long mallCatId; // MALL_CAT_ID

	private Long itemCode; // ITEM_CODE

	private Long mchtId; // MCHT_ID

	private String itemPicUrl; // ITEM_PIC_URL

	private String mchtName; // MCHT_NAME

	private Integer itemType; // ITEM_TYPE

	private String itemName; // ITEM_NAME

	private BigDecimal itemMarketPrice; // ITEM_MARKET_PRICE

	private BigDecimal itemSalePrice; // ITEM_SALE_PRICE

	private Date itemValidStart; // ITEM_VALID_START

	private Date itemValidEnd; // ITEM_VALID_END

	private String itemSaleAttr; // ITEM_SALE_ATTR

	private Short dataState; // DATA_STATE

	private String mallCatName; // MALL_CAT_NAME

	private String itemTypeName; // ITEM_TYPE_NAME

	private Integer itemApprState; // ITEM_APPR_STATE

	private Integer itemUdState; // ITEM_UD_STATE

	public Integer getItemApprState() {
		return itemApprState;
	}

	public void setItemApprState(Integer itemApprState) {
		this.itemApprState = itemApprState;
	}

	public Integer getItemUdState() {
		return itemUdState;
	}

	public void setItemUdState(Integer itemUdState) {
		this.itemUdState = itemUdState;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getMallCatId() {
		return mallCatId;
	}

	public void setMallCatId(Long mallCatId) {
		this.mallCatId = mallCatId;
	}

	public Long getItemCode() {
		return itemCode;
	}

	public void setItemCode(Long itemCode) {
		this.itemCode = itemCode;
	}

	public Long getMchtId() {
		return mchtId;
	}

	public void setMchtId(Long mchtId) {
		this.mchtId = mchtId;
	}

	public String getItemPicUrl() {
		return itemPicUrl;
	}

	public void setItemPicUrl(String itemPicUrl) {
		this.itemPicUrl = itemPicUrl;
	}

	public String getMchtName() {
		return mchtName;
	}

	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getItemMarketPrice() {
		return itemMarketPrice;
	}

	public void setItemMarketPrice(BigDecimal itemMarketPrice) {
		this.itemMarketPrice = itemMarketPrice;
	}

	public BigDecimal getItemSalePrice() {
		return itemSalePrice;
	}

	public void setItemSalePrice(BigDecimal itemSalePrice) {
		this.itemSalePrice = itemSalePrice;
	}

	public Date getItemValidStart() {
		return itemValidStart;
	}

	public void setItemValidStart(Date itemValidStart) {
		this.itemValidStart = itemValidStart;
	}

	public Date getItemValidEnd() {
		return itemValidEnd;
	}

	public void setItemValidEnd(Date itemValidEnd) {
		this.itemValidEnd = itemValidEnd;
	}

	public String getItemSaleAttr() {
		return itemSaleAttr;
	}

	public void setItemSaleAttr(String itemSaleAttr) {
		this.itemSaleAttr = itemSaleAttr;
	}

	public Short getDataState() {
		return dataState;
	}

	public void setDataState(Short dataState) {
		this.dataState = dataState;
	}

	public String getMallCatName() {
		return mallCatName;
	}

	public void setMallCatName(String mallCatName) {
		this.mallCatName = mallCatName;
	}

	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

}
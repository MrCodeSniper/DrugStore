package com.example.android.chaoshi.bean.shop;

import java.io.Serializable;

public class ItemDangDang implements Serializable {
	/** 商品名，商品简介*/
	public String itemShopName;
	/** 商品图片img*/
	public String itemShopImg;
	/** 商品详情链接*/
	public String itemShopHref;
	/** 价格*/
	public String itemShopProce;
	/** 类型，特点等*/
	public String itemShopType;
	/** 商品星级*/
	public String itemShopStyle;
	/** 评论条数*/
	public String itemShopPingLun;
	/** 卖家名*/
	public String itemShopMaiJiaName;
	/** 卖家链接*/
	public String itemShopMaiJiaHref;
	public String originalCost;
	public String discountPrice;

	@Override
	public String toString() {
		return "ItemDangDang{" +
				"itemShopName='" + itemShopName + '\'' +
				", itemShopImg='" + itemShopImg + '\'' +
				", itemShopHref='" + itemShopHref + '\'' +
				", itemShopProce='" + itemShopProce + '\'' +
				", itemShopType='" + itemShopType + '\'' +
				", itemShopStyle='" + itemShopStyle + '\'' +
				", itemShopPingLun='" + itemShopPingLun + '\'' +
				", itemShopMaiJiaName='" + itemShopMaiJiaName + '\'' +
				", itemShopMaiJiaHref='" + itemShopMaiJiaHref + '\'' +
				", originalCost='" + originalCost + '\'' +
				", discountPrice='" + discountPrice + '\'' +
				'}';
	}
}

package com.example.android.chaoshi.bean.shop;

public class ItemTitlePager {
	/** 图片左边需要显示的文字*/
	public String left;
	/** 图片的链接*/
	public String img;
	/** 点击图片后的详情*/
	public String href;

	public String getImg() {
		return img;
	}

	public String getHref() {
		return href;
	}

	public String getLeft() {
		return left;
	}

	@Override
	public String toString() {
		return "ItemTitlePager{" +
				"left='" + left + '\n' +
				", img='" + img + '\n' +
				", href='" + href + '\n' +
				'}';
	}
}

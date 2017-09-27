package com.shtm.manage.po;

import com.shtm.po.GoodsImgs;

/**
 * Title:GoodsImgsReplier
 * <p>
 * Description:自定义商品图片po
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月27日 上午11:32:37
 * @version 1.0
 */
public class GoodsImgsReplier extends GoodsImgs {
	//商品图片path(取自于files表),如a.png
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
}

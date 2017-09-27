package com.shtm.manage.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.shtm.manage.groups.GoodsGroups.UpdateGoodGroups;
import com.shtm.manage.groups.GoodsGroups.UploadGoodsImgsGroups;
import com.shtm.po.Goods;

/**
 * Title:GoodsReceiver
 * <p>
 * Description:goods的信息接受者;
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月23日 下午2:22:52
 * @version 1.0
 */
/**
 * Title:GoodsReceiver
 * <p>
 * Description:
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月26日 下午2:14:15
 * @version 1.0
 */
public class GoodsReceiver extends Goods {
	// 排序方式
	private String order;

	// 排序字段
	private String sort;

	// 当前多少页数
	private Integer page;

	// 一页的行数
	private Integer rows;

	// 分页开始
	private Integer start;

	// 分页结束
	private Integer end;

	public Integer getStart() {
		return (page - 1) * rows + 1;
	}

	public Integer getEnd() {
		return getStart() + rows - 1;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}
	
	

	//商品类型参数
	@NotNull(message="{goods.clazzs.notnull.error}",groups={UpdateGoodGroups.class})
	@Size(min=0,max=3,message="{goods.clazzs.range.error}",groups={UpdateGoodGroups.class})
	private String[] clazzs;
	
	

	public String[] getClazzs() {
		return clazzs;
	}

	public void setClazzs(String[] clazzs) {
		this.clazzs = clazzs;
	}

	//獲取圖片所需參數
	
	private String size;
	private String imgName;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	
	@NotNull(message="{goods.file.notnull.error}",groups={UploadGoodsImgsGroups.class})
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}

package com.shtm.manage.mapper;

import java.util.List;

import com.shtm.po.Clazzs;

/**
 * Title:CustomClazzsMapper
 * <p>
 * Description:自定義ClazzsMapper;
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月26日 上午10:24:45
 * @version 1.0
 */
public interface CustomClazzsMapper {
	/**
	 * Title:selectClazzs
	 * <p>
	 * Description:查詢clazzs;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月26日 上午10:25:17
	 * @version 1.0
	 * @return
	 */
	public List<Clazzs> selectClazzs();
}

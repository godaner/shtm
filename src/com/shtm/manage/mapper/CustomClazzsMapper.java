package com.shtm.manage.mapper;

import java.util.List;

import com.shtm.manage.po.ClazzsReceiver;
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
	 * Description:查詢所有clazzs;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月26日 上午10:25:17
	 * @version 1.0
	 * @return
	 */
	public List<Clazzs> selectAllClazzs() throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:查询easyui所需的clazzs列表;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月28日 下午3:04:49
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	public List<Clazzs> selectClazzsDatagrid(ClazzsReceiver receiver) throws Exception;

	
	/**
	 * Title:
	 * <p>
	 * Description:查询clazzs数量;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月28日 下午3:04:49
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	public Integer selectClazzsNum(ClazzsReceiver receiver) throws Exception;
}

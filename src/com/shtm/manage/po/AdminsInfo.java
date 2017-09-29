package com.shtm.manage.po;

import java.io.Serializable;
import java.util.List;

import com.shtm.po.Admins;
import com.shtm.po.Permissions;

/**
 * Title:AdminsInfo
 * <p>
 * Description:管理员的身份信息;
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月29日 上午11:46:22
 * @version 1.0
 */
public class AdminsInfo extends Admins implements Serializable{
	private List<Permissions> permissions;
}

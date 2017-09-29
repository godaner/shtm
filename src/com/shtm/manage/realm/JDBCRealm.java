package com.shtm.manage.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.shtm.manage.mapper.CustomAdminsMapper;
import com.shtm.po.Admins;
import com.shtm.util.Util;

/**
 * Title:JDBCRealm
 * <p>
 * Description:连接数据库的realm;
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月29日 上午10:04:39
 * @version 1.0
 */
public class JDBCRealm extends AuthorizingRealm {
	@Autowired
	private CustomAdminsMapper customAdminsMapper;
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		SimpleAuthenticationInfo info = null;  
	    try {
	    	 //token中储存着输入的用户名和密码  
		    UsernamePasswordToken upToken = (UsernamePasswordToken)token;  
		    //获得用户名与密码  
		    String username = upToken.getUsername();  
		    String password = String.valueOf(upToken.getPassword());  
		    //TODO 与数据库中用户名和密码进行比对。比对成功则返回info，比对失败则抛出对应信息的异常AuthenticationException
		    Admins dbAd = null;
	    	dbAd = customAdminsMapper.selectAdminByUsername(username);
	    	/**
	    	 * 比较密码
	    	 */
	    	Util.eject(dbAd == null,"管理员不存在失败");
	    	Util.eject(!dbAd.getPassword().equals(Util.md5(password+dbAd.getSalt())),"密码错误");
	    	
	    	//验证成功
	    	info = new SimpleAuthenticationInfo(username, password .toCharArray(),getName());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AuthenticationException(e.getMessage());
		}
	    return info;  
	}
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userName = (String) getAvailablePrincipal(principals);  
	    //TODO 通过用户名获得用户的所有资源，并把资源存入info中  
	   /* …………………….  
	    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
	    info.setStringPermissions(set集合);  
	    info.setRoles(set集合);  
	    info.setObjectPermissions(set集合);  
	    return info;*/
		return null;
	}


}

package com.shtm.manage.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.shtm.manage.service.AdminsServiceI;
import com.shtm.po.Admins;
import com.shtm.po.Permissions;
import com.shtm.po.Roles;
import com.shtm.util.Util;

/**
 * Title:JDBCRealm
 * <p>
 * Description:连接数据库的realm;
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月29日 上午10:04:39
 * @version 1.0
 */
public class JDBCRealm extends AuthorizingRealm {

	private AdminsServiceI adminsService;

	
	public AdminsServiceI getAdminsService() {
		return adminsService;
	}

	public void setAdminsService(AdminsServiceI adminsService) {
		this.adminsService = adminsService;
	}

	/**
	 * 直接认证成功(两头验证机制)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken)
			throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		String password = String.valueOf(token.getPassword());
		Admins ad = null;
		try {
			ad = adminsService.selectAdminByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new SimpleAuthenticationInfo(ad,password, getName());

	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = ((Admins) principals.getPrimaryPrincipal()).getUsername();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		Admins dbAdmin = null;
		try {
			dbAdmin = adminsService.selectAdminByUsername(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 根据用户名查询当前用户拥有的角色
		List<Roles> roles = Util.getList();
		try {
			roles = adminsService.selectRoles(dbAdmin.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set<String> roleNames = new HashSet<String>();
		for (Roles role : roles) {
			roleNames.add(role.getName());
		}
		// 将角色名称提供给info

		authorizationInfo.setRoles(roleNames);
		// 根据用户名查询当前用户权限
		List<Permissions> permissions = Util.getList();
		try {
			permissions = adminsService
					.selectPermissions(dbAdmin.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set<String> permissionNames = new HashSet<String>();
		for (Permissions permission : permissions) {
			permissionNames.add(permission.getCode());
		}
		// 将权限名称提供给info
		authorizationInfo.setStringPermissions(permissionNames);

		return authorizationInfo;
	}
	
	/**
	 * Title:
	 * <p>
	 * Description:清除shiro缓存,达到动态刷新权限的效果;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月3日 下午10:27:50
	 * @version 1.0
	 */
	public void clearCached(){
		PrincipalCollection principals = SecurityUtils.getSubject().getPreviousPrincipals();
		super.clearCache(principals);
	}
}

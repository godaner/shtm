package com.shtm.manage.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shtm.manage.service.AdminsServiceI;
import com.shtm.po.Admins;
import com.shtm.po.Permissions;
import com.shtm.po.Roles;
import com.shtm.util.Util;
import com.shtm.util.Static.ADMINS_STATUS;

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
@Component
public class JDBCRealm extends AuthorizingRealm {

	@Autowired
	private AdminsServiceI adminsService;

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken)
			throws AuthenticationException {

		// UsernamePasswordToken对象用来存放提交的登录信息
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		// 是否正确提交了用户信息
		if (token.getUsername() == null
				|| token.getUsername().trim().equals("")) {
			throw new UnknownAccountException();
		}
		if (token.getPassword() == null
				|| token.getPassword().toString().trim().equals("")) {
			throw new UnknownAccountException();
		}

		// 查出是否有此用户
		Admins admin = null;
		try {
			admin = adminsService.selectAdminByUsername(token.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AuthenticationException();
		}
		// 用户不存在
		if (admin == null) {
			throw new UnknownAccountException();
		}

		// 用户被冻结
		if (admin.getStatus() == ADMINS_STATUS.FROZEN) {
			// 用户被管理员锁定抛出异常
			throw new LockedAccountException();
		}
		// 若存在，将此用户存放到登录认证info中
		return new SimpleAuthenticationInfo(admin.getUsername(),
				admin.getPassword(), getName());

	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		Admins dbAdmin = null;
		try {
			dbAdmin = adminsService.selectAdminByUsername(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 根据用户名查询当前用户拥有的角色
		List<Roles> roles = null;
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
		List<Permissions> permissions = null;
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
}

package com.hhh.security;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.web.model.RoleBean;
import com.hhh.fund.web.model.UserBean;
import com.hhh.security.util.EncryptHelper;

public class ShiroDBRealm extends AuthorizingRealm {
//	private final static Logger logger = LoggerFactory.getLogger(ShiroDBRealm.class);
	@Autowired
	private UserCenterService userService;
	
	/**
	 * 认证回调函数，登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) 
			throws AuthenticationException {
		System.out.println("login1111111");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		UserBean user = userService.findByUsername(token.getUsername());
		
		if(user == null){
			throw new UnknownAccountException("用户名/密码错误");
		}
		
		if(Boolean.TRUE.equals(user.isLocked())){
			throw new LockedAccountException("用户被锁定了");
		}
		ShiroPrincipal subject = new ShiroPrincipal(user);
		Set<String> permissSet = userService.getUserPermission(token.getUsername());
		Set<RoleBean> beans = userService.getUserRole(token.getUsername());
		Set<String> roles = new HashSet<>();
		if(beans != null){
			for(RoleBean bean : beans){
				roles.add(bean.getId());
			}
		}
		subject.setAuthorities(permissSet);
		subject.setRoles(roles);
		subject.setAuthorized(true);
		String pass = String.valueOf(token.getPassword());
		String pd="";
		System.out.println(pass+"pass"+pass.indexOf(";;;"));
		if(pass.indexOf(";;;") != -1){
			
			String[] strs = pass.split(";;;");
			pd=strs[1];
		}
		if(pd.equals("key")){
			System.out.println(pass+"passwrodd");
			return new SimpleAuthenticationInfo(subject, user.getKeyId()+";;;key", 
					 getName());
		}else{
			HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(EncryptHelper.HASH_ALGORITHM);
			matcher.setHashIterations(EncryptHelper.HASH_INTERATIONS);
			
			setCredentialsMatcher(matcher);
			return new SimpleAuthenticationInfo(subject, user.getPassword(), 
					ByteSource.Util.bytes(user.getSalt()), getName());
		}
	}
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//获取当前登录的用户名
		ShiroPrincipal subject = (ShiroPrincipal)super.getAvailablePrincipal(principals);
		String username = subject.getUsername();
		if(!subject.isAuthorized()) {
			Set<String> permissSet = userService.getUserPermission(username);
			Set<RoleBean> beans = userService.getUserRole(username);
			Set<String> roles = new HashSet<>();
			if(beans != null){
				for(RoleBean bean : beans){
					roles.add(bean.getId());
				}
			}
			subject.setAuthorities(permissSet);
			subject.setRoles(roles);
			subject.setAuthorized(true);
		}		
		info.addStringPermissions(subject.getAuthorities());
		info.addRoles(subject.getRoles());
		return info;
	}
	
	@PostConstruct
	public void initCredentialsMatcher() {
		
	}
}

package cc.ysf.dx.util.security;

import cc.ysf.dx.base.enums.StatusEnums;
import cc.ysf.dx.pojo.entity.User;
import cc.ysf.dx.service.UserSerivce;
import cc.ysf.dx.util.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;


/**
 * >>> Shiro 认证域
 */
public class ShiroDBRealm extends AuthorizingRealm {
	@Autowired
	private UserSerivce userSerivce;
	@Autowired
	private HttpSession session;

	/**
	 * >>> 当需要授权的时候，会调用该方法
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}

	/**
	 * >>> 当需要认证的时候，会调用该方法
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// 因为登录所使用的方式是“登录名+登陆密码”的形式，因此需要把AuthenticationToken进行转换
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		// 通过UsernamePasswordToken能够获得登录的用户名、密码当前登录用户的IP地址等等信息
		// 通过用户名获得对应的用户对象
		try {
			User user = userSerivce.getUserByCellphone(token.getUsername());
			//判断该user是否存在是否处于激活状态
			if(user!=null&&user.getStatus()== StatusEnums.STATUS_ENABLE.getCode()){
				//获得用户密码进行加密
				String password = MD5Util.encrypt(new String(token.getPassword()));
				//加密后将密码重新加入到token中
				token.setPassword(password.toCharArray());
				//将密码交给shiro，shiro进行密码的对比
				SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
						user.getCellphone(),user.getPassword(),getName());
				//登陆成功，绑定进入Session
				session.setAttribute("user",user);
				return info;

			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}

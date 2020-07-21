package cc.ysf.dx.controller;


import cc.ysf.dx.base.baseController.BaseController;
import cc.ysf.dx.base.enums.StatusEnums;
import cc.ysf.dx.pojo.entity.Role;
import cc.ysf.dx.pojo.entity.User;
import cc.ysf.dx.pojo.vo.VmsPage;
import cc.ysf.dx.service.RoleService;
import cc.ysf.dx.service.UserSerivce;
import cc.ysf.dx.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * >>> 用户部分控制器
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserSerivce userSerivce;
	@Autowired
	private RoleService roleService;


	/**
	 * 转发到登录界面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/login")
	public String forwardLoginPage() throws Exception {
		return "user/user_login";
	}

	/**
	 * <b>登录失败后重定向访问地址</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	public String loginError() throws Exception {
		// 只需要重定向到退出即可
		System.out.println("登录失败");
		return "redirect:/user/logout";
	}

	/**
	 * >>> 密码错误
	@GetMapping("/logout")
	public String logout() throws Exception{
		return "user/logout";
	}
	 */

	/**
	 * <b>加载 用户列表页面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/index")
	public String index() throws Exception {
		return "user/user_index";
	}

	/**
	 * <b>分页查询用户信息列表</b>
	 * @param pageNum
	 * @param pageSize
	 * @param draw
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/page")
	@ResponseBody
	public VmsPage<User> getUserListByPage(Integer pageNum, Integer pageSize, int draw) throws Exception {
		// 创建 VmsPage 对象
		VmsPage<User> vmsPage = new VmsPage<User>(pageNum, pageSize, draw);
		// 使用 Service 进行分页查询
		vmsPage = userSerivce.getUserListByPage(vmsPage);
		return vmsPage;
	}

	/**
	 * <b>转发保存用户页面</b> 同时查询所有角色列
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/save")
	public String forwardSave(ModelMap map) throws Exception {
		// 创建查询对象
		Role query = new Role();
		query.setStatus(StatusEnums.STATUS_ENABLE.getCode());
		//查找角色列表
		List<Role> roleList = roleService.getRoleListByQuery(query);
		map.put("roleList",roleList);
		return "user/user_save";
	}


	/**
	 * >>>  保存新用户时校验手机号码是否已经存在
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/cellphone")
	@ResponseBody
	public boolean validateCellphone(String cellphone) throws Exception {
		//查询手机号是否存在
		User users = userSerivce.getUserByCellphone(cellphone);
		if(users != null ){
			return false;
		}
		return true ;
	}
	/**
	 * <b>保存新用户</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/save")
	@ResponseBody
	public boolean save(User user, Long roleId) throws Exception {

		System.out.println("进入保存新用户");
		System.out.println(roleId);
		System.out.println(user.getCellphone()+user.getUsername()+user.getEmail());

		// 检查手机号码和密码
		if (user.getCellphone() != null && !"".equals(user.getCellphone().trim())
				&& user.getPassword() != null && !"".equals(user.getPassword().trim())) {
			// 保存必须存在的角色
			if (roleId != null && roleId != 0) {
				user.setPassword(MD5Util.encrypt(user.getPassword()));
				// 设定状态为启用状态
				user.setStatus(StatusEnums.STATUS_ENABLE.getCode());
				//插入用户角色 根据角色ID查询角色对象
					Role newRole = new Role();
					newRole.setId(roleId);
					List<Role> roleList = roleService.getRoleListByQuery(newRole);
				user.setRole(roleList.get(0));
				return userSerivce.save(user);
			}
		}
		return false;
	}





}

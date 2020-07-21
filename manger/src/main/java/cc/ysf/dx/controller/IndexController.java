package cc.ysf.dx.controller;

import cc.ysf.dx.base.baseController.BaseController;
import cc.ysf.dx.pojo.entity.Menu;
import cc.ysf.dx.pojo.entity.Role;
import cc.ysf.dx.pojo.entity.User;
import cc.ysf.dx.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * >>> 首页面控制器
 */
@Controller("indexController")
public class IndexController extends BaseController {
	@Autowired
	private MenuService menuService;

	/**
	 * >>> 登陆成功，进入首页面
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@GetMapping ("/")
	public String index(ModelMap map) throws Exception{
		//根据当前用户，获得对应的角色
		User user = (User) session.getAttribute("user");
		Role role = user.getRole();
		//根据用户角色获得对应列表
		List<Menu> menuList =menuService.getIndexMenuByRole(role);
		map.put("menuList",menuList);
		return "index";
	}
}

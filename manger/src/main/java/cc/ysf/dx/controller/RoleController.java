package cc.ysf.dx.controller;

import cc.ysf.dx.base.baseController.BaseController;
import cc.ysf.dx.pojo.entity.Role;
import cc.ysf.dx.pojo.vo.Node;
import cc.ysf.dx.pojo.vo.VmsPage;
import cc.ysf.dx.service.MenuService;
import cc.ysf.dx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * >>> 角色信息管理控制层
 */
@Controller("roleController")
@RequestMapping("/role")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;

	/**
	 * >>> 进入角色管理页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/index")
	public String roleIndex() throws Exception{
		return "role/role_index";
	}

	/**
	 * >>> 角色管理页面异步获得所有角色列表
	 * @param pageNum
	 * @param pageSize
	 * @param draw
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/page")
	@ResponseBody
	public VmsPage<Role> getRolePage(Integer pageNum ,Integer pageSize ,int draw) throws Exception{
		//创建返回VO对象
		VmsPage<Role> roleVmsPage = new VmsPage<>(pageNum,pageSize,draw);
		//到Service层完成分页
		roleVmsPage =roleService.getRolePage(roleVmsPage);
		return roleVmsPage;
	}

	/**
	 * >>> 转发到授权页面 并携带角色id
	 * @param id
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/auth")
	public String getAuthPage(@RequestParam Long id ,ModelMap map) throws Exception{
		map.put("roleId", id);
		return "role/role_auth";
	}

	/**
	 * >>> 授权页面发送异步请求 获得角色权限集合
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/auth")
	@ResponseBody
	public List<Node> getNodeLiseForAuth(@RequestParam Long roleId) throws Exception{
		return menuService.getIndexMenuByRoleId(roleId);
	}



}

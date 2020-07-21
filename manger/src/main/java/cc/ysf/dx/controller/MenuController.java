package cc.ysf.dx.controller;

import cc.ysf.dx.base.baseController.BaseController;
import cc.ysf.dx.pojo.vo.Node;
import cc.ysf.dx.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * >>> 角色权限添加控制层
 */
@Controller("menuController")
@RequestMapping("/menu")
public class MenuController extends BaseController {
	@Autowired
	private MenuService menuService;
	/**
	 * >>> 为角色添加权限
	 * @param roleId
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/orization")
	@ResponseBody
	public String updateNodeList(@RequestParam Long roleId, String ids, ModelMap map) throws Exception {
		if (ids != null) {
			List<Node> nodes = menuService.getNewListByRoleIdAndNodeId(roleId, ids);
			map.put("nodes", nodes);
			if (nodes != null && nodes.size()>= 0) {
				return "true";
			}
		}
			return "false";
	}
}

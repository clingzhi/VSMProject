package cc.ysf.dx.service;

import cc.ysf.dx.pojo.entity.Menu;
import cc.ysf.dx.pojo.entity.Role;
import cc.ysf.dx.pojo.vo.Node;

import java.util.List;

/**
 * >>> 菜单业务层接口
 */
public interface MenuService {
	/**
	 * >>> 根据角色查找角色对应可以查看的菜单列表
	 * @param role
	 * @return
	 * @throws Exception
	 */
	List<Menu> getIndexMenuByRole(Role role) throws Exception;

	/**
	 * >>> 根据角色ID 查询为授权页面查询Node集合
	 * @param  roleId
	 * @return
	 * @throws Exception
	 */
	List<Node> getIndexMenuByRoleId(Long roleId)throws Exception;

	/**
	 * >>> 为角色赋予新的权限使用角色ID和NODEids
	 * @param roleId
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	List<Node> getNewListByRoleIdAndNodeId(Long roleId, String ids)throws Exception;
}

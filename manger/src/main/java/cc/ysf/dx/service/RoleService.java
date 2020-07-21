package cc.ysf.dx.service;

import cc.ysf.dx.pojo.entity.Role;
import cc.ysf.dx.pojo.vo.VmsPage;

import java.util.List;

/**
 * >>> 角色信息业务层接口
 */
public interface RoleService {
	/**
	 * >>> 根据查询信息查询分页对象
	 * @param roleVmsPage
	 * @return
	 */
	VmsPage<Role> getRolePage(VmsPage<Role> roleVmsPage) throws Exception;

	/**
	 * >>> 根据封装对象查询角色列表（此时为封装了角色状态 ）
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Role> getRoleListByQuery(Role query)throws Exception;
}

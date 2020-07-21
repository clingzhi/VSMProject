package cc.ysf.dx.dao;

import cc.ysf.dx.pojo.entity.Menu;
import cc.ysf.dx.pojo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * >>> 菜单持久层接口
 */
@Repository
public interface MenuDao {
	/**
	 * >>> 根据查询对象查询菜单列表
	 * @param query
	 * @return
	 */
	List<Menu> findMenuListByParentAndRole(Map<String, Object> query) throws Exception;

	/**
	 * >>> 根据查询集合，查询菜单列表
	 * @param queryMap
	 * @return
	 */
	List<Menu> findMenuListByQuery(Map<String, Object> queryMap)throws Exception ;

	/**
	 * >>>删除菜单 by roleID
	 * @param role
	 * @return
	 */
	boolean deleteMenuList(Role role)throws Exception;

	/**
	 * >>> 插入新的菜单数据
	 * @param updataList
	 * @return
	 */
	boolean updateMneuList(Map<String, Object> updataList) throws Exception;
}

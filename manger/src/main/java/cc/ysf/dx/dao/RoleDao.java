package cc.ysf.dx.dao;

import cc.ysf.dx.pojo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * >>> 角色信息数据持久层接口
 */
@Repository
public interface RoleDao {

	/**
	 * >>> 根据查询对象 查询角色
	 * @param query
	 * @return
	 */
	List<Role> findRolePage(Role query) throws Exception;

	/**
	 * <b>保存信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	int save(Role entity) throws Exception;

	/**
	 * <b>修改信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	int update(Role entity) throws Exception;
}

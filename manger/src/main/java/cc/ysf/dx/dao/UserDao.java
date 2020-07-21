package cc.ysf.dx.dao;

import cc.ysf.dx.pojo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
	/**
	 * >>> 根据查询条件查询对象
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<User> findUserListByQuery(User query)throws Exception;

	/**
	 * >>> 保存user对象
	 * @param
	 * @return
	 * @throws Exception
	 */
	int save(User entity) throws Exception;

	/**
	 * >>> 更新user对象
	 * @param
	 * @return
	 * @throws Exception
	 */
	int update(User entity) throws Exception;

}

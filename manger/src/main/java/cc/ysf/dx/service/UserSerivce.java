package cc.ysf.dx.service;

import cc.ysf.dx.pojo.entity.User;
import cc.ysf.dx.pojo.vo.VmsPage;

/**
 * >>> 业务层接口
 */
public interface UserSerivce {
	/**
	 * >>> 根据手机号码查找用户
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	User getUserByCellphone(String cellphone)throws Exception;

	/**
	 * <b>分页查询用户列表</b>
	 * @param vmsPage
	 * @return
	 * @throws Exception
	 */
	VmsPage<User> getUserListByPage(VmsPage<User> vmsPage) throws Exception;

	/**
	 * 添加新用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean save(User user)throws Exception;
}

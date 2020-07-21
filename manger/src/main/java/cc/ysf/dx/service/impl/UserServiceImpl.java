package cc.ysf.dx.service.impl;

import cc.ysf.dx.base.enums.StatusEnums;
import cc.ysf.dx.dao.UserDao;
import cc.ysf.dx.pojo.entity.User;
import cc.ysf.dx.pojo.vo.VmsPage;
import cc.ysf.dx.service.UserSerivce;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * >>> 用户业务层实现类
 */
@Service("userSerivce")
@Transactional
public class UserServiceImpl implements UserSerivce {
	@Autowired
	private UserDao userDao;
	/**
	 * >>> 根据手机号码查找用户
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	public User getUserByCellphone(String cellphone) throws Exception {
		//封装查询对象
		User query = new User();
		query.setCellphone(cellphone);

		List<User> userList = userDao.findUserListByQuery(query);
		if (userList!=null&&userList.size()>0){
			return userList.get(0);
		}
		return null;
	}

	/**
	 * <b>分页查询用户列表</b>
	 * @param vmsPage
	 * @return
	 * @throws Exception
	 */
	public VmsPage<User> getUserListByPage(VmsPage<User> vmsPage) throws Exception {
		// 使用 PageHelper 进行分页查询
		PageHelper.startPage(vmsPage.getPageNum(), vmsPage.getPageSize());
		List<User> userList = userDao.findUserListByQuery(null);
		// 将 userList 进行类型转换
		PageInfo<User> pageInfo = new PageInfo<User>(userList);
		// 提取 PageInfo 中的信息，填入到 VmsPage中
		vmsPage.copyFromPageInfo(pageInfo);

		return vmsPage;
	}

	/**
	 * 添加新用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean save(User user) throws Exception {
		//默认保存的新用户都是启用状态
		user.setStatus(StatusEnums.STATUS_ENABLE.getCode());

		int flog = userDao.save(user);
		if ( flog > 0) {
			return true;
		}
		return false;

	}
}

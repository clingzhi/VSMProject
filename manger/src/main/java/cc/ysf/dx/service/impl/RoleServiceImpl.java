package cc.ysf.dx.service.impl;

import cc.ysf.dx.dao.RoleDao;
import cc.ysf.dx.pojo.entity.Role;
import cc.ysf.dx.pojo.vo.VmsPage;
import cc.ysf.dx.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *>>> 角色信息业务层接口实现类
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;

	/**
	 *  根据查询信息查询分页对象
	 * @param roleVmsPage
	 * @return
	 * @throws Exception
	 */
	public VmsPage<Role> getRolePage(VmsPage<Role> roleVmsPage) throws Exception {
		//使用PageHelp进行分页
		PageHelper.startPage(roleVmsPage.getPageNum(), roleVmsPage.getPageSize());
		//查询所有角色信息
		List<Role> roleList = roleDao.findRolePage(null);

		PageInfo<Role> rolePageInfo = new PageInfo<>(roleList);
		//将结果存入返回对象中
		roleVmsPage.copyFromPageInfo(rolePageInfo);
		return roleVmsPage;
	}

	/**
	 * >>> 根据封装对象查询角色列表 （此时为封装了角色状态）
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleListByQuery(Role query) throws Exception {
		List<Role> list = roleDao.findRolePage(query);
		if (list != null && list.size() > 0) {
			return list;
		}
		return new ArrayList<Role>();
	}
}

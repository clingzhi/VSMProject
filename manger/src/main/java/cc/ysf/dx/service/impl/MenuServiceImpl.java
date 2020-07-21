package cc.ysf.dx.service.impl;

import cc.ysf.dx.base.enums.StatusEnums;
import cc.ysf.dx.dao.MenuDao;
import cc.ysf.dx.pojo.entity.Menu;
import cc.ysf.dx.pojo.entity.Role;
import cc.ysf.dx.pojo.vo.Node;
import cc.ysf.dx.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * >>> 菜单业务层接口实现类
 */
@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;

	/**
	 * >>> 根据角色查找角色对应可以查看的菜单列表
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<Menu> getIndexMenuByRole(Role role) throws Exception {
		//封装一个查询一级菜单的Map
		Map<String,Object> query = new HashMap<>();
		query.put("parentId", null);
		query.put("roleId", role.getId());
		query.put("status", StatusEnums.STATUS_ENABLE.getCode());

		//根据查询对象查询角色对应的一级列表
		List<Menu> firstList = menuDao.findMenuListByParentAndRole(query);
		//此时可以得到一级菜单，根据以及菜单得到二级菜单
		if(firstList!=null && firstList.size()>0){
			for (Menu first:firstList){
				query.put("parentId", first.getId());
				//根据一级菜单的ID查询二级菜单
				List<Menu>  secondList = menuDao.findMenuListByParentAndRole(query);
			    first.setChildList(secondList);
			}
		}
		return firstList;
	}

	/**
	 * >>> 根据角色ID 查找授权页面的Node集合
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<Node> getIndexMenuByRoleId(Long roleId) throws Exception {
		// 创建返回List
		List<Node> nodeList = new ArrayList<>();
		//查询所有菜单信息
		List<Menu> allMenuList = menuDao.findMenuListByQuery(null);
		//根据角色ID查询菜单列表
		Map<String,Object> queryMap = new HashMap<>();
		queryMap.put("roleId", roleId);
		List<Menu> roleMenu = menuDao.findMenuListByQuery(queryMap);

		//遍历所有菜单的集合，将menu对象转换为Node对象
		for (Menu menu:allMenuList) {
			//创建Node对象
			Node node = new Node();
			node.setId(menu.getId());
			node.setpId((menu.getParent()==null)?0:menu.getParent().getId());
			node.setName(menu.getText());
			//如果是一级菜单就默认展开
			if(node.getpId()==0){
				node.setOpen(true);
			}
			//判断如果是角色已经拥有的权限，则默认勾选
			if(roleMenu.contains(menu)){
				node.setChecked(true);
			}
			nodeList.add(node);
		}
		return nodeList;
	}

	/**
	 * >>> 为角色赋予新的权限使用角色ID和NODEids
	 * @param roleId
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List<Node> getNewListByRoleIdAndNodeId(Long roleId, String ids) throws Exception {
		//将ids处理为数组
		String[] arr = ids.split(",");
		//String as = Arrays.toString(arr);
        // 创建返回List
		List<Node> nodeList = new ArrayList<>();

		//赋予角色权限，即在中间表，为角色添加新的菜单选项  》》》  根据角色ID 查询角色菜单，然后删除它，插入新的
		Role role = new Role();
		role.setId(roleId);
		//删除该角色列表
		boolean deloldMenuList = menuDao.deleteMenuList(role);
		//如果删除成功进入插入
		if (deloldMenuList){
			// 然后插入新的列表
			//创建插入数据布尔对象
			boolean	updateMneuList = false ;
			//创建插入MAP集合
			Map<String,Object> updataList = new HashMap<>();
			for (String str : arr) {
				updataList.put("roleId", roleId);
				updataList.put("nodeId", str);
				updateMneuList = menuDao.updateMneuList(updataList);
			}
			if(updateMneuList){
				return nodeList;
			}
		}
		return new ArrayList<Node>();
	}
}

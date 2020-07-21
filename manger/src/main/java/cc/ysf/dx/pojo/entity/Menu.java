package cc.ysf.dx.pojo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * >>> 菜单实体类
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Menu parent;                        // 上级菜单
	private String text;
	private String url;
	private String icon;
	private Integer status;
	private User createuser;
	private Date createtime;
	private User updateuser;
	private Date updatetime;
	private List<Menu> childList;                       // 下级菜单
	private List<Menu> grunChildList;                       // 孙子下级菜单
	private List<Role> roleList;                        // 拥有该菜单的角色列表

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getCreateuser() {
		return createuser;
	}

	public void setCreateuser(User createuser) {
		this.createuser = createuser;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public User getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(User updateuser) {
		this.updateuser = updateuser;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public List<Menu> getChildList() {
		return childList;
	}

	public void setChildList(List<Menu> childList) {
		this.childList = childList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}

		if (obj instanceof Menu) {
			Menu menu = (Menu) obj;
			return this.getId().equals(menu.getId());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

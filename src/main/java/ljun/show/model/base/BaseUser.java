package ljun.show.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setRoleId(java.lang.Long roleId) {
		set("role_id", roleId);
	}

	public java.lang.Long getRoleId() {
		return get("role_id");
	}

	public void setAccount(java.lang.String account) {
		set("account", account);
	}

	public java.lang.String getAccount() {
		return get("account");
	}

	public void setPassword(java.lang.String password) {
		set("password", password);
	}

	public java.lang.String getPassword() {
		return get("password");
	}

	public void setNickname(java.lang.String nickname) {
		set("nickname", nickname);
	}

	public java.lang.String getNickname() {
		return get("nickname");
	}

	public void setSex(java.lang.Integer sex) {
		set("sex", sex);
	}

	public java.lang.Integer getSex() {
		return get("sex");
	}

	public void setCreatedTime(java.util.Date createdTime) {
		set("created_time", createdTime);
	}

	public java.util.Date getCreatedTime() {
		return get("created_time");
	}

	public void setUpdatedTime(java.util.Date updatedTime) {
		set("updated_time", updatedTime);
	}

	public java.util.Date getUpdatedTime() {
		return get("updated_time");
	}

}

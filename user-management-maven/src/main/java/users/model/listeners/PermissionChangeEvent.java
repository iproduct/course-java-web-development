package users.model.listeners;

import java.io.Serializable;
import java.util.Set;

import users.model.Permission;
import users.model.User;

public class PermissionChangeEvent implements Serializable {
	private User user;
	private Set<Permission> oldPermissions;
	private Set<Permission> newPermissions;
	private boolean New;
	
	public PermissionChangeEvent() {
	}
	
	public PermissionChangeEvent(User user, Set<Permission> oldPermissions, Set<Permission> newPermissions, boolean isNew) {
		this.user = user;
		this.oldPermissions = oldPermissions;
		this.newPermissions = newPermissions;
		New = isNew;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Permission> getOldPermissions() {
		return oldPermissions;
	}

	public void setOldPermissions(Set<Permission> oldPermissions) {
		this.oldPermissions = oldPermissions;
	}

	public Set<Permission> getNewPermission() {
		return newPermissions;
	}

	public void setNewPermission(Set<Permission> newPermissions) {
		this.newPermissions = newPermissions;
	}

	public boolean isNew() {
		return New;
	}

	public void setNew(boolean new1) {
		New = new1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (New ? 1231 : 1237);
		result = prime * result + ((newPermissions == null) ? 0 : newPermissions.hashCode());
		result = prime * result + ((oldPermissions == null) ? 0 : oldPermissions.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PermissionChangeEvent))
			return false;
		PermissionChangeEvent other = (PermissionChangeEvent) obj;
		if (New != other.New)
			return false;
		if (newPermissions == null) {
			if (other.newPermissions != null)
				return false;
		} else if (!newPermissions.equals(other.newPermissions))
			return false;
		if (oldPermissions == null) {
			if (other.oldPermissions != null)
				return false;
		} else if (!oldPermissions.equals(other.oldPermissions))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PermissionChangeEvent [user=").append(user).append(", oldPermissions=").append(oldPermissions)
				.append(", newPermissions=").append(newPermissions).append(", New=").append(New).append("]");
		return builder.toString();
	}
		
}

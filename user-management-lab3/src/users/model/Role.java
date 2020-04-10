package users.model;

import static users.model.ActionType.CREATE;
import static users.model.ActionType.DELETE_ALL;
import static users.model.ActionType.READ_ALL;
import static users.model.ActionType.UPDATE_ALL;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable {
	public static Set<ActionType> restResourceAdminActions = new HashSet<ActionType>();
	static {
		restResourceAdminActions.add(CREATE);
		restResourceAdminActions.add(UPDATE_ALL);
		restResourceAdminActions.add(READ_ALL);
		restResourceAdminActions.add(DELETE_ALL);
	}
	public static Set<Permission> adminRolePermissions = new HashSet<Permission>();
	static {
		restResourceAdminActions.stream().forEach(action ->
			adminRolePermissions.add(new Permission("*", ResourceType.REST_ENDPOINT, action)));
	}
	public static final Role ROLE_CUSTOMER = new Role("CUSTOMER", adminRolePermissions);
	public static final Role ROLE_MANAGER = new Role("MANAGER", adminRolePermissions);
	public static final Role ROLE_ADMIN = new Role("ADMIN", adminRolePermissions);
	private static final long serialVersionUID = 1L;
	
	private String name; // String containing the name of the Role;
	private Set<Permission> permissions = new HashSet<>(); // role defined standard permissions for given Role;
	private Date created = new Date(); // Date the date and time of first creating the Role in the system;
	private Date modified = new Date(); // Date the date and time of last modification of Role data in the system;
	
	public Role() {
	}

	public Role(String name, Set<Permission> permissions) {
		this.name = name;
		this.permissions = permissions;
	}

	public Role(String name, Set<Permission> permissions, Date created, Date modified) {
		this.name = name;
		this.permissions = permissions;
		this.created = created;
		this.modified = modified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Role))
			return false;
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [name=").append(name).append(", permissions=").append(permissions).append(", created=")
				.append(created).append(", modified=").append(modified).append("]");
		return builder.toString();
	}
	

}

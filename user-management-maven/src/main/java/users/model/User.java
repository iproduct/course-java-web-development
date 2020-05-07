package users.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import users.model.listeners.PermissionChangeEvent;
import users.model.listeners.PermissionChangeListenable;
import users.model.listeners.PermissionChangeListener;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "firstName",
    "lastName",
    "email",
    "password",
    "active",
    "roles",
    "permissions",
    "created",
    "modified"
})
@XmlRootElement(name = "user")
public class User implements Identifiable, Serializable, PermissionChangeListenable {
	private static final long serialVersionUID = 1L;
	@NotNull @XmlAttribute(required = true)
	private Long id; // long number generated automatically by the program;
	@NotNull @Size(min = 2, max= 20) @XmlElement(required = true)
	private String firstName; // String containing the first name of the User;
	@NotNull @Size(min = 2, max= 20) @XmlElement(required = true)
	private String lastName; // String containing the last name of the User;
	@NotNull @Email @XmlElement(required = true)
	private String email; // String containing the unique email of the User;
	@NotNull @Size(min = 5, max= 30) @XmlElement(required = true)
	private String password; // String containing the chosen password of the User;
	@XmlElement(required = true)
	private boolean active = true; // boolean that is true if the User is enabled and active;
	@XmlElement(required = true)
	@NotNull
	@Size(min=1)
	@Valid
	private Set<Role> roles = new HashSet<>(); // collection of Role objects representing allowed User roles;
	@NotNull
	@Valid
	private Set<Permission> permissions = new HashSet<>(); // Userprivatespecific permissions overriding those provided
															// by Role collection;
	@PastOrPresent @XmlElement(required = true)
	private Date created = new Date(); // Date the date and time of first creating the User in the system;
	@PastOrPresent @XmlElement(required = true)
	private Date modified = new Date(); // Date the date and time of last modification of User data in the system;
	transient private List<PermissionChangeListener> permissionChangeListeners = new CopyOnWriteArrayList<>();

	// No args constructor
	public User() {
	}

	// Required args constructor
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password; 
		active = true;
		roles.add(Role.ROLE_CUSTOMER);
		permissions = new HashSet<Permission>();
	}

	public User(String firstName, String lastName, String email, String password, boolean active,
			Role role, Set<Permission> permissions, Date created, Date modified) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.roles.add(role);
		this.permissions = permissions;
		this.created = created;
		this.modified = modified;
	}
	
	// All args constructor
	public User(Long id, String firstName, String lastName, String email, String password, boolean active,
			Set<Role> roles, Set<Permission> permissions, Date created, Date modified) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.roles = roles;
		this.permissions = permissions;
		this.created = created;
		this.modified = modified;
	}

	public User(Long id, String firstName, String lastName, String email, String password, boolean active,
			Role role, Set<Permission> permissions, Date created, Date modified) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.roles.add(role);
		this.permissions = permissions;
		this.created = created;
		this.modified = modified;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		Set<Permission> oldPermissions = this.permissions;
		this.permissions = permissions;
		Permission[] old = oldPermissions.toArray(new Permission[] {});
		Permission[] current = permissions.toArray(new Permission[] {});
		if(old.length != current.length) {
			firePermissionchangedEvent(
					new PermissionChangeEvent(this, oldPermissions, permissions, oldPermissions.isEmpty()));
			return;
		}
		for (int i = 0; i < old.length; i++) {
			if (!old[i].equals(current[i]) || old[i].isAllowed() != current[i].isAllowed()) {
				firePermissionchangedEvent(
						new PermissionChangeEvent(this, oldPermissions, permissions, oldPermissions.isEmpty()));
				return;
			}
		}
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

	public String getSalutation() {
		return String.format("Welcome, %s %s in roles %s", getFirstName(), getLastName(), roles.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
				.append(lastName).append(", email=").append(email).append(", password=").append(password)
				.append(", active=").append(active).append(", roles=").append(roles).append(", permissions=")
				.append(permissions).append(", created=").append(created).append(", modified=").append(modified)
				.append("]");
		return builder.toString();
	}

	// Event handling
	@Override
	public void addUserPermissionChangeListener(PermissionChangeListener listener) {
		permissionChangeListeners.add(listener);
	}

	@Override
	public void removeUserPermissionChangeListener(PermissionChangeListener listener) {
		permissionChangeListeners.remove(listener);
	}

	protected void firePermissionchangedEvent(PermissionChangeEvent permissionChangeEvent) {
		permissionChangeListeners.forEach(listener -> listener.permissionChanged(permissionChangeEvent));
	}

}

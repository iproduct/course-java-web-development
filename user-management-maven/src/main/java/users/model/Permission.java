package users.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "permission")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	@NotBlank
	private String name; // String containing the name of the Permission;
	@NotNull
	private ResourceType resourceType; // ResourceType representing the type of the resource the permission is given for;
	private Long resourceId;// representing the id of the resource the permission is given for (when given for specific resource instance // optional filed);
	@NotNull
	private ActionType actionType; // ActionType enumeration representing the type of action to be executed over the protected resource for which the Permission applies;
	private boolean allowed = true;// boolean, true when specific ActionType is allowed on specific ResourceType or Resource instance (selected by resourceId).;
	@PastOrPresent
	private Date created = new Date();// Date the date and time of first creating the Permission in the system;
	@PastOrPresent
	private Date modified = new Date();// Date the date and time of last modification of Permission data in the system;
	
	public Permission() {
	}

	public Permission(String name, ResourceType resourceType, ActionType actionType) {
		this.name = name;
		this.resourceType = resourceType;
		this.actionType = actionType;
	}

	public Permission(String name, ResourceType resourceType, Long resourceId, ActionType actionType) {
		this.name = name;
		this.resourceType = resourceType;
		this.resourceId = resourceId; 
		this.actionType = actionType;
	}

	public Permission(String name, ResourceType resourceType, ActionType actionType, boolean allowed) {
		this.name = name;
		this.resourceType = resourceType;
		this.actionType = actionType;
		this.allowed = allowed;
	}

	public Permission(String name, ResourceType resourceType, Long resourceId, ActionType actionType, boolean allowed) {
		this.name = name;
		this.resourceType = resourceType;
		this.resourceId = resourceId;
		this.actionType = actionType;
		this.allowed = allowed;
	}

	public Permission(String name, ResourceType resourceType, Long resourceId, ActionType actionType, boolean allowed,
			Date created, Date modified) {
		this.name = name;
		this.resourceType = resourceType;
		this.resourceId = resourceId;
		this.actionType = actionType;
		this.allowed = allowed;
		this.created = created;
		this.modified = modified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public boolean isAllowed() {
		return allowed;
	}

	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
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
		result = prime * result + ((actionType == null) ? 0 : actionType.hashCode());
		result = prime * result + ((resourceId == null) ? 0 : resourceId.hashCode());
		result = prime * result + ((resourceType == null) ? 0 : resourceType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Permission))
			return false;
		Permission other = (Permission) obj;
		if (actionType != other.actionType)
			return false;
		if (resourceId == null) {
			if (other.resourceId != null)
				return false;
		} else if (!resourceId.equals(other.resourceId))
			return false;
		if (resourceType != other.resourceType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Permission [name=").append(name).append(", resourceType=").append(resourceType)
				.append(", resourceId=").append(resourceId).append(", actionType=").append(actionType)
				.append(", allowed=").append(allowed).append(", created=").append(created).append(", modified=")
				.append(modified).append("]");
		return builder.toString();
	}
}

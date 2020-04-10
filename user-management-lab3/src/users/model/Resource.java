package users.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id; // long number generated automatically by the program;
	private String name; // String containing the first name of the Resource;
	private User owner; // User that has created the Resource;
	private String url; // String representing the URL of the selected resource (optional field);
	private ResourceType resourceType; // ResourceType of the Resource;
	private Set<ActionType> actions; // collection of ActionType values for actions available for the current Resource;
	private Date created = new Date(); // Date the date and time of first creating the Resource in the system;
	private Date modified = new Date(); // Date the date and time of last modification of Resource data in the system;
	
	public Resource() {
	}

	public Resource(String name, User owner, String url, ResourceType resourceType, Set<ActionType> actions) {
		this.name = name;
		this.owner = owner;
		this.url = url;
		this.resourceType = resourceType;
		this.actions = actions;
	}

	public Resource(long id, String name, User owner, String url, ResourceType resourceType, Set<ActionType> actions,
			Date created, Date modified) {
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.url = url;
		this.resourceType = resourceType;
		this.actions = actions;
		this.created = created;
		this.modified = modified;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public Set<ActionType> getActions() {
		return actions;
	}

	public void setActions(Set<ActionType> actions) {
		this.actions = actions;
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
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Resource))
			return false;
		Resource other = (Resource) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Resource [id=").append(id).append(", name=").append(name).append(", owner=").append(owner)
				.append(", url=").append(url).append(", resourceType=").append(resourceType).append(", actions=")
				.append(actions).append(", created=").append(created).append(", modified=").append(modified)
				.append("]");
		return builder.toString();
	}
	
	
	
}

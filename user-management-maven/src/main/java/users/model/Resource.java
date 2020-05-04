package users.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "name",
    "ownerId",
    "url",
    "resourceType",
    "actions",
    "created",
    "modified"
})
@XmlRootElement(name = "resource")
public class Resource implements Identifiable, Serializable {
	private static final long serialVersionUID = 1L;
	transient private final PropertyChangeSupport pcs = new PropertyChangeSupport(this); // Problem 1.IV.2
	@NotNull
	private long id; // long number generated automatically by the program;
	@NotNull @NotBlank
	private String name; // String containing the first name of the Resource;
	@NotNull
	private long ownerId; // User that has created the Resource;
	private String url; // String representing the URL of the selected resource (optional field);
	@NotNull
	private ResourceType resourceType; // ResourceType of the Resource;
	private Set<ActionType> actions = new HashSet<>(); // collection of ActionType values for actions available for the current Resource;
	@PastOrPresent
	private Date created = new Date(); // Date the date and time of first creating the Resource in the system;
	@PastOrPresent
	private Date modified = new Date(); // Date the date and time of last modification of Resource data in the system;
	
	public Resource() {
	}

	public Resource(String name, long ownerId, String url, ResourceType resourceType, Set<ActionType> actions) {
		this.name = name;
		this.ownerId = ownerId;
		this.url = url;
		this.resourceType = resourceType;
		this.actions = actions;
	}

	public Resource(long id, String name, long ownerId, String url, ResourceType resourceType, Set<ActionType> actions,
			Date created, Date modified) {
		this.id = id;
		this.name = name;
		this.ownerId = ownerId;
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
		String oldName = this.name;
		this.name = name;
        this.pcs.firePropertyChange("name", oldName, name);
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		long oldOwnerId = this.ownerId;
		this.ownerId = ownerId;
        this.pcs.firePropertyChange("owner", oldOwnerId, ownerId);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		String oldUrl = this.url;
		this.url = url;
        this.pcs.firePropertyChange("url", oldUrl, url);
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		ResourceType oldResourceType = this.resourceType;
		this.resourceType = resourceType;
        this.pcs.firePropertyChange("url", oldResourceType, resourceType);
		
	}

	public Set<ActionType> getActions() {
		return actions;
	}

	public void setActions(Set<ActionType> actions) {
		Set<ActionType> oldActions = this.actions;
		this.actions = actions;
        this.pcs.firePropertyChange("url", oldActions, actions);
		
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
		builder.append("Resource [id=").append(id).append(", name=").append(name).append(", ownerID=").append(ownerId)
				.append(", url=").append(url).append(", resourceType=").append(resourceType).append(", actions=")
				.append(actions).append(", created=").append(created).append(", modified=").append(modified)
				.append("]");
		return builder.toString();
	}
	
	 public void addPropertyChangeListener(PropertyChangeListener listener) {
         this.pcs.addPropertyChangeListener(listener);
     }

     public void removePropertyChangeListener(PropertyChangeListener listener) {
         this.pcs.removePropertyChangeListener(listener);
     }
	
}

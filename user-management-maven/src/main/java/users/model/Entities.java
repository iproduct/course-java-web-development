package users.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "entity" })
@XmlRootElement(name = "entities")
public class Entities<T> {
	@XmlAnyElement(lax = true)
	protected Collection<T> entity;

	public Entities() {
		this.entity = new ArrayList<>();
	}
	
	public Entities(Collection<T> collection) {
		this.entity = collection;
	}

	/**
	 * Gets the value of the note property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the note property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getEntities().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Object }
	 * 
	 * 
	 */
	public Collection<T> getEntities() {
		if (entity == null) {
			entity = new ArrayList<T>();
		}
		return this.entity;
	}

	@Override
	public String toString() {
		return "Entities:" + entity;
	}

}
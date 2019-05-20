package invoicing.utils;

public class Tuple<T,U> {
	private T prop1;
	private U prop2;
	public Tuple(T prop1, U prop2) {
		this.prop1 = prop1;
		this.prop2 = prop2;
	}
	public T getProp1() {
		return prop1;
	}
	public U getProp2() {
		return prop2;
	}
	@Override
	public String toString() {
		return String.format("Tupple [prop1=%s, prop2=%s]", prop1, prop2);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prop1 == null) ? 0 : prop1.hashCode());
		result = prime * result + ((prop2 == null) ? 0 : prop2.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuple other = (Tuple) obj;
		if (prop1 == null) {
			if (other.prop1 != null)
				return false;
		} else if (!prop1.equals(other.prop1))
			return false;
		if (prop2 == null) {
			if (other.prop2 != null)
				return false;
		} else if (!prop2.equals(other.prop2))
			return false;
		return true;
	}
}

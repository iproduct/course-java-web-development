package lambdas;

public class Tuple2<V1, V2> {
	private V1 value1;
	private V2 value2;
	
	public Tuple2(V1 value1, V2 value2) {
		this.value1 = value1;
		this.value2 = value2;
	}

	public V1 getValue1() {
		return value1;
	}

	public V2 getValue2() {
		return value2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value1 == null) ? 0 : value1.hashCode());
		result = prime * result + ((value2 == null) ? 0 : value2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Tuple2))
			return false;
		Tuple2 other = (Tuple2) obj;
		if (value1 == null) {
			if (other.value1 != null)
				return false;
		} else if (!value1.equals(other.value1))
			return false;
		if (value2 == null) {
			if (other.value2 != null)
				return false;
		} else if (!value2.equals(other.value2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tuple2 [value1=").append(value1).append(", value2=").append(value2).append("]");
		return builder.toString();
	}
	
}

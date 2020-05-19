package chat.model;

public class ConnectionSettings {
	private String address = "localhost";
	private int port = 9000;
	private String nickname = "Anonimous";
	
	public ConnectionSettings() {
	}

	public ConnectionSettings(String address, int port, String nickname) {
		this.address = address;
		this.port = port;
		this.nickname = nickname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + port;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ConnectionSettings))
			return false;
		ConnectionSettings other = (ConnectionSettings) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (port != other.port)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConnectionSettings [address=").append(address).append(", port=").append(port)
				.append(", nickname=").append(nickname).append("]");
		return builder.toString();
	}
	
	
}

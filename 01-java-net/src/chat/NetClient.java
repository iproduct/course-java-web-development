package chat;

import chat.model.ConnectionSettings;

public interface NetClient {
	String login(ConnectionSettings settings);
	void sendMessage(String message);
	void logout();
	void addMessageListener(MessageListener ml);
	void removeMessageListener(MessageListener ml);
}

package users.model.listeners;

public interface PermissionChangeListenable {
	void addUserPermissionChangeListener(PermissionChangeListener listener);
	void removeUserPermissionChangeListener(PermissionChangeListener listener);
}

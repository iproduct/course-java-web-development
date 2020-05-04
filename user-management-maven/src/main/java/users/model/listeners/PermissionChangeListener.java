package users.model.listeners;

@FunctionalInterface
public interface PermissionChangeListener {
	void permissionChanged(PermissionChangeEvent event);
}

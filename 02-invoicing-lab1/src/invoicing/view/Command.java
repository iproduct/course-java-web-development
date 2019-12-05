package invoicing.view;

@FunctionalInterface
public interface Command {
	boolean action();
}

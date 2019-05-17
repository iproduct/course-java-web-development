package visitor;

import java.io.File;

@FunctionalInterface
public interface Visitor {
	boolean visitFile(File f, int level);
}

package visitor;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class VisitorDemo {
	
	public static void visitRecursive(String path, Visitor visitor, Filter filter) {
		visitRecursive(new File(path), visitor, filter);
	}
	
	public static void visitRecursive(File dir, Visitor visitor, Filter filter) {
		visitRecursive(dir, visitor, filter, 0);
	}

	public static void visitRecursive(File dir, Visitor visitor, Filter filter, int level) {
		if (!dir.isDirectory()) return; // recursion bottom
		List<File> files = Arrays.asList(dir.listFiles()).stream()
				.filter(f -> f.isDirectory() || filter.filter(f))
				.collect(Collectors.toList());
		for(File f : files) { 
			boolean toWalk = visitor.visitFile(f, level);
			if(toWalk) { //recursion step
				visitRecursive(f, visitor, filter, level + 1);
			}
		}
	}
	
	public static String getFileInfo(File f) {
		StringBuilder sb = new StringBuilder();
		sb.append(f.getName());
		sb.append("\t\t\t").append(f.length());
		sb.append("\t").append(f.isDirectory() ? "DIR " : "FILE");
		return sb.toString();
	}

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile(args.length == 1 ? args[0] : ".*");
		visitRecursive(".", 
				(File f, int level) -> {
					StringBuilder sb = new StringBuilder();
					for(int i = 0; i < level; i++) {
						sb.append("  ");
					}
					sb.append(getFileInfo(f));
					System.out.println(sb.toString());
					return true;
				},
				f -> pattern.matcher(f.getName()).matches()
				
//				new Filter() {
//					private String pattern = ".*\\.java";
//					public boolean filter(File f) {
//						return f.getName().matches(pattern);
//					}
//				}
	);

	}

}

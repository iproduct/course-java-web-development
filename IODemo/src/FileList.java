import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class FileList {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out
				.println("Usage:\njava FileList <file name>");
			return;
		}
		
//		Files.walk(Paths.get(args[0])).filter(path -> Files.isDirectory(path))
//			.forEach(path -> System.out.println(path));
//		PathMatcher pm = FileSystems.getDefault().getPathMatcher("glob:*.java");
//		Files.walk(Paths.get(args[0])).forEach(path -> {
//			if(Files.isDirectory(path)){
//				System.out.println("+ " + path.normalize());
//			} else {
//				if(pm.matches(path.getFileName())) {
//					System.out.println("    " + path.getFileName());
//				}
//			}
//		});
		
		long time = System.currentTimeMillis();
		FileTime fileTime = FileTime.from(Instant.now().minus(10, ChronoUnit.DAYS));
		System.out.println(fileTime);
		BasicFileAttributeView bv = Files.getFileAttributeView(Paths.get(args[0]), BasicFileAttributeView.class);
		bv.setTimes(fileTime, fileTime, fileTime);

	}

}

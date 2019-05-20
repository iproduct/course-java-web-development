import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class FileList {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out
				.println("Usage:\njava FileList <file name>");
			return;
		}
		
		Files.walk(Paths.get(args[0])).filter(path -> Files.isDirectory(path))
			.forEach(path -> System.out.println(path));
		
//		Files.walk(Paths.get(args[0])).forEach(path -> {
//			if(Files.isDirectory(path)){
//				System.out.println("+ " + path.normalize());
//			} else {
//				System.out.println("    " + path.getFileName());
//			}
//		});
		
//		long time = System.currentTimeMillis();
//      FileTime fileTime = FileTime.from(Instant.now().minus(1, ChronoUnit.YEARS));
//      System.out.println(fileTime);
//      BasicFileAttributeView bv = Files.getFileAttributeView(Paths.get(args[0]), BasicFileAttributeView.class);
//      bv.setTimes(fileTime, fileTime, fileTime);

	}

}

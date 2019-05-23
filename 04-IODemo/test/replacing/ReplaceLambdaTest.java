package replacing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReplaceLambdaTest {
	public static final String SOURCE_FILE_NAME = "src/replacing/ReplaceLambda.java";
	public static final String TARGET_FILE_NAME = "Test.java";
	public static final String RESULT_FILE_NAME = "Result.java";
	public static final String SEARCH_EXPRESSION = "public";
	public static final String REPLACEMENT_EXPRESSION = "lambda";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Files.copy(Paths.get(SOURCE_FILE_NAME), Paths.get(TARGET_FILE_NAME));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Files.deleteIfExists(Paths.get(TARGET_FILE_NAME));
		Files.deleteIfExists(Paths.get(RESULT_FILE_NAME));
	}

	@Test()
	public final void testReplaceOccurencesFromInfileToOutfile() throws IOException {
		//1. Setup
		assertTrue(
			"Test setup not successful - file does not exist: " + TARGET_FILE_NAME,
			Files.exists(Paths.get(TARGET_FILE_NAME)) 
		);
		ReplaceLambda instance = new ReplaceLambda();
		
		//2. Action
		instance.replaceOccurencesFromInfileToOutfile(
			TARGET_FILE_NAME, RESULT_FILE_NAME, 
			SEARCH_EXPRESSION, REPLACEMENT_EXPRESSION);
		
		//3. Postcondition
		assertTrue(
				"Result file does not exist: " + RESULT_FILE_NAME,
				Files.exists(Paths.get(RESULT_FILE_NAME)) 
			);
		assertFalse(
				"Result file does contain search expression: " + SEARCH_EXPRESSION,
				containsExpression(RESULT_FILE_NAME, SEARCH_EXPRESSION)
			);
		assertTrue(
				"Result file does not contain replacement expression: " + REPLACEMENT_EXPRESSION,
				containsExpression(RESULT_FILE_NAME, REPLACEMENT_EXPRESSION)
			);	
	}

	private boolean containsExpression(String fileName, String expression) throws IOException{
		return Files.lines(Paths.get(fileName)).anyMatch( s -> s.contains(expression));
	}
}

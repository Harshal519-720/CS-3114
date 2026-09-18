import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the methods of Rectangle1 class which serves as the entry
 * point of the command line program.
 *
 * @author Patrick Sullivan
 * @version 2024.1
 */
public class SkipListProjectTest extends TestCase {
	/**
	 * tests the main methond with no file name provided
	 */
	public void testNoArguments() {
		String[] args = {};

		SkipListProject.main(args);

		assertTrue(systemOut().getHistory().contains("Invalid file. No filename in command line arguments"));
	}

	/**
	 * tests the main method when the file does not exist
	 */
	public void testInvalidFile() {
		String[] args = { "this_file_should_not_exist_12345.txt" };

		SkipListProject.main(args);

		assertTrue(systemOut().getHistory().contains("Invalid file"));
	}

	/**
	 * Tests main with valid command file
	 *
	 * @throws IOException if the temp file cannot be created
	 */
	public void testValidFile() throws IOException {
		File file = File.createTempFile("skiplisttest", ".txt");

		FileWriter writer = new FileWriter(file);
		writer.write("insert a 1 0 2 4\n");
		writer.write("\n");
		writer.write("dump\n");
		writer.close();

		TestableRandom.setNextBooleans(false);

		String[] args = { file.getAbsolutePath() };

		SkipListProject.main(args);

		String output = systemOut().getHistory();

		assertTrue(output.contains("Rectangle inserted: (a, 1, 0, 2, 4)"));

		assertTrue(output.contains("SkipList dump:"));

		assertTrue(output.contains("SkipList size is: 1"));

		file.delete();
	}
}

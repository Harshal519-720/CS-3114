import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the CommandProcessor class. Test each possible command on
 * its bounds, if applicable to ensure they work properly. Also test passing
 * improper command to ensure all class functionalities work as intended.
 *
 * @author <Harshal Kukkala>
 * @version 2026-09-13
 */
public class CommandProcessorTest extends TestCase {
	/**
	 * command processor used for testing
	 */
	private CommandProcessor processor;

	/**
	 * creates a new command processor setup for each test
	 */
	public void setUp() {
		processor = new CommandProcessor();
	}

	/**
	 * tests the insert method
	 */
	public void testInsert() {
		TestableRandom.setNextBooleans(false);

		processor.processor("insert a 1 0 2 4");

		assertFuzzyEquals("Rectangle inserted: (a, 1, 0, 2, 4)", systemOut().getHistory());
	}

	/**
	 * tests the insert method on invalid cases
	 */
	public void testInvalidInsert() {
		processor.processor("insert a -1 -1 2 4");
		assertFuzzyEquals("Rectangle rejected: (a, -1, -1, 2, 4)", systemOut().getHistory());
	}

// ----------------------------------------------------------
	/**
	 * tests the insert and dump method
	 */
	public void testInsertandDump() {
		TestableRandom.setNextBooleans(false);

		processor.processor("insert a 1 0 2 4");
		processor.processor("dump");

		String output = systemOut().getHistory();

		assertTrue(output.contains("Rectangle inserted: (a, 1, 0, 2, 4)"));
		assertTrue(output.contains("SkipList dump:"));
		assertTrue(output.contains("(a, 1, 0, 2, 4)"));
		assertTrue(output.contains("SkipList size is: 1"));
	}

// ----------------------------------------------------------
	/**
	 * checks and tests invalid commands to see whether it is the right input or not
	 */
	public void testInvalidCommand() {
		processor.processor("test");
		assertFuzzyEquals("Unrecognized command.", systemOut().getHistory());
	}

	// ----------------------------------------------------------
	/**
	 * checks extra whitespace in the input
	 */
	public void testExtraSpace() {
		TestableRandom.setNextBooleans(false);

		processor.processor(" insert a 1 0 2 4 ");

		assertTrue(systemOut().getHistory().contains("Rectangle inserted: (a, 1, 0, 2, 4)"));
	}

}

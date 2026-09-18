import java.util.Iterator;

import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the methods of SkipList class
 *
 * @author CS Staff
 * @version 2024-01-22
 */

public class SkipListTest extends TestCase {

	/**
	 * Instantiate skip list
	 */
	private SkipList<String, Rectangle> sl;

	/**
	 * setsup skiplist for each test case
	 */
	public void setUp() {
		sl = new SkipList<String, Rectangle>();
	}

	/**
	 * Example 1: Test `randomLevel` method with predetermined random values using
	 * `TestableRandom`
	 */
	public void testRandomLevelOne() {
		TestableRandom.setNextBooleans(false);
		sl = new SkipList<String, Rectangle>();
		int randomLevelValue = sl.randomLevel();

// This returns 1 because the first preset
// random boolean is `false` which breaks
// the `while condition inside the `randomLevel` method
		int expectedLevelValue = 1;

// Compare the values
		assertEquals(expectedLevelValue, randomLevelValue);
	}

	/***
	 * Example 2: Test `randomLevel` method with predetermined random values using
	 * `TestableRandom`
	 */
	public void testRandomLevelFour() {
		TestableRandom.setNextBooleans(true, true, true, false, true, false);
		sl = new SkipList<String, Rectangle>();
		int randomLevelValue = sl.randomLevel();

// This returns 4 because the fourth preset
// random boolean is `false` which breaks
// the `while condition inside the `randomLevel` method
		int expectedLevelValue = 4;

// Compare the values
		assertEquals(expectedLevelValue, randomLevelValue);
	}

	/**
	 * test size of empty skiplist
	 */
	public void testEmptySize() {
		assertEquals(0, sl.size());
	}

	/**
	 * test inserting one KVPair
	 */
	public void testInsertOne() {
		TestableRandom.setNextBooleans(false);

		Rectangle rect = new Rectangle(10, 20, 30, 40);
		KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>("a", rect);

		sl.insert(pair);

		assertEquals(1, sl.size());
	}

	/**
	 * test inserting mult. KVPairs
	 */
	public void testInsertMultiple() {
		TestableRandom.setNextBooleans(false, false, false);

		sl.insert(new KVPair<String, Rectangle>("c", new Rectangle(30, 30, 10, 10)));
		sl.insert(new KVPair<String, Rectangle>("a", new Rectangle(10, 10, 10, 10)));
		sl.insert(new KVPair<String, Rectangle>("b", new Rectangle(20, 20, 10, 10)));

		assertEquals(3, sl.size());
	}

	/**
	 * test inserted items are in key order when stored
	 */
	public void testInsertOrder() {
		TestableRandom.setNextBooleans(false, false, false);

		sl.insert(new KVPair<String, Rectangle>("c", new Rectangle(30, 30, 10, 10)));
		sl.insert(new KVPair<String, Rectangle>("a", new Rectangle(10, 10, 10, 10)));
		sl.insert(new KVPair<String, Rectangle>("b", new Rectangle(20, 20, 10, 10)));

		Iterator<KVPair<String, Rectangle>> itr = sl.iterator();

		assertEquals("a", itr.next().getKey());
		assertEquals("b", itr.next().getKey());
		assertEquals("c", itr.next().getKey());
		assertFalse(itr.hasNext());
	}

	/**
	 * test insert duplicates
	 */
	public void testDuplicateKey() {
		TestableRandom.setNextBooleans(false, false);

		sl.insert(new KVPair<String, Rectangle>("a", new Rectangle(10, 10, 10, 10)));
		sl.insert(new KVPair<String, Rectangle>("a", new Rectangle(20, 20, 10, 10)));

		assertEquals(2, sl.size());

		Iterator<KVPair<String, Rectangle>> itr = sl.iterator();

		assertEquals("a", itr.next().getKey());
		assertEquals("a", itr.next().getKey());
		assertFalse(itr.hasNext());
	}

	/**
	 * test dump on empty skiplist
	 */
	public void testEmptyDump() {
		sl.dump();

		assertFuzzyEquals("SkipList dump:\n" + "Node with depth 1, Value null\n" + "SkipList size is: 0",
				systemOut().getHistory());
	}

	/**
	 * test dump with inserted nodes
	 */

	public void testDumpWithNodes() {
		TestableRandom.setNextBooleans(false, false);

		sl.insert(new KVPair<String, Rectangle>("b", new Rectangle(20, 20, 10, 10)));

		sl.insert(new KVPair<String, Rectangle>("a", new Rectangle(0, 10, 10, 10)));

		sl.dump();

		assertFuzzyEquals("SkipList dump:\n" + "Node with depth 1, Value null\n" + "Node with depth 1, Value "
				+ "(a, 0, 10, 10, 10)\n" + "Node with depth 1, Value " + "(b, 20, 20, 10, 10)\n"
				+ "SkipList size is: 2", systemOut().getHistory());

	}

}

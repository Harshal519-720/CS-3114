import student.TestCase;

/**
 * This class tests the methods of Rectangle class, ensuring that they work as
 * they should.
 *
 * @author Tvesa Soni
 * @version 2026-09-09
 */
public class RectangleTest extends TestCase {

	private Rectangle rect;

	/**
	 * Initializes a rectangle object to be used for the tests.
	 */
	public void setUp() {
		rect = new Rectangle(10, 20, 30, 40);
	}

	/**
	 * tests the getter methods
	 */
	public void testGetters() {
		assertEquals(10, rect.getxCoordinate());
		assertEquals(20, rect.getyCoordinate());
		assertEquals(30, rect.getWidth());
		assertEquals(40, rect.getHeight());
	}

	/**
	 * tests the rectangle string display
	 */
	public void testToString() {
		assertEquals("10, 20, 30, 40", rect.toString());
	}

	/**
	 * test equality of rectangles
	 */
	public void testEquals() {
		Rectangle same = new Rectangle(10, 20, 30, 40);

		assertTrue(rect.equals(same));
		assertTrue(rect.equals(rect));

		assertFalse(rect.equals(new Rectangle(11, 20, 30, 40)));
		assertFalse(rect.equals(new Rectangle(10, 21, 30, 40)));
		assertFalse(rect.equals(new Rectangle(10, 20, 31, 40)));
		assertFalse(rect.equals(new Rectangle(11, 20, 30, 41)));

		assertFalse(rect.equals("not a rectangle"));
	}

	/**
	 * test rectangles that intersect
	 */
	public void testIntersect() {
		Rectangle overlap = new Rectangle(20, 30, 30, 40);
		assertTrue(rect.intersect(overlap));

		Rectangle inside = new Rectangle(15, 25, 5, 5);
		assertTrue(rect.intersect(inside));
	}

	/**
	 * test rectangles that dont intersect
	 */
	public void testNotIntersect() {
		Rectangle right = new Rectangle(40, 20, 10, 10);
		assertFalse(rect.intersect(right));

		Rectangle left = new Rectangle(0, 20, 10, 10);
		assertFalse(rect.intersect(left));

		Rectangle below = new Rectangle(10, 60, 10, 10);
		assertFalse(rect.intersect(below));

		Rectangle above = new Rectangle(10, 0, 10, 10);
		assertFalse(rect.intersect(above));
	}

	/**
	 * tests valid rectangle dimensions
	 */
	public void testValid() {
		assertFalse(rect.isInvalid());

		Rectangle boundary = new Rectangle(0, 0, 1024, 1024);
		assertFalse(boundary.isInvalid());
	}

	/**
	 * test invalid rectangle dimensions / coordinate
	 */
	public void testInvalid() {
		assertTrue(new Rectangle(-1, 0, 10, 10).isInvalid());
		assertTrue(new Rectangle(0, -1, 10, 10).isInvalid());

		assertTrue(new Rectangle(0, 0, 0, 10).isInvalid());
		assertTrue(new Rectangle(0, 0, 10, 0).isInvalid());

		assertTrue(new Rectangle(0, 0, -1, 10).isInvalid());
		assertTrue(new Rectangle(0, 0, 10, -1).isInvalid());

		assertTrue(new Rectangle(1020, 0, 5, 10).isInvalid());
		assertTrue(new Rectangle(0, 1020, 10, 5).isInvalid());

	}
}

package unitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import factoryInternals.*;

class StackTest {

	private IStack<String> testStack;

	@BeforeEach
	void setUp() throws Exception {
		this.testStack = new ExpandableStack<String>();
	}

	@Test
	void testPush() {
		assertEquals(true, this.testStack.push("Cat"));
		assertEquals(false, this.testStack.push(null));
		for (int i = 0; i < 10; i++) {
			this.testStack.push("nyan");
		}
		assertEquals(true, this.testStack.push("cat"));
	}

	@Test
	void testPop() {
		assertEquals(null, this.testStack.pop());
		this.testStack.push("Cat");
		assertEquals("Cat", this.testStack.pop());
		assertEquals(null, this.testStack.pop());
	}

	@Test
	void testPeek() {
		assertEquals(null, this.testStack.peek());
		this.testStack.push("Cat");
		assertEquals("Cat", this.testStack.peek());
		assertEquals("Cat", this.testStack.peek());
	}

	@Test
	void testIsEmpty() {
		assertEquals(true, this.testStack.isEmpty());
		this.testStack.push("cat");
		assertEquals(false, this.testStack.isEmpty());
	}

	@Test
	void testEmpty() {
		this.testStack.push("cat");
		assertEquals(true, this.testStack.empty());
		assertEquals(true, this.testStack.isEmpty());
	}

}

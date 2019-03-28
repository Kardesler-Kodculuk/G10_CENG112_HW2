package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import internals.ArrayQueue;
import parts.*;

class QueueTest {

	private ArrayQueue<IProduct> queue;
	private IProduct ram; IProduct cpu; IProduct cache; IProduct GraphicsCard; IProduct Motherboard;
	

	@BeforeEach
	void setUp() throws Exception {
		queue = new ArrayQueue<IProduct>();
		ram = new RAM();
		cpu = new CPU();
		cache = new Cache();
		GraphicsCard = new GraphicsCard();
		Motherboard = new parts.Motherboard();
		
	}

	@Test
	void testArrayQueue() {
	}

	@Test
	void testEnQueue() {
		/*for(int i = 0; i < 100; i++) {
			queue.enQueue(ram);
		} */
		ArrayQueue<String> que = new ArrayQueue<String>();
		for (int i = 0; i < 100; i++) {
			que.enQueue(((Integer) i).toString());
		}
	}

	@Test
	void testDeQueue() {
/*		assertEquals(null, queue.deQueue());
		queue.enQueue(ram);
		queue.enQueue(cache);
		queue.enQueue(cpu);
		assertEquals(ram, queue.deQueue());
		assertEquals(cache, queue.deQueue());
		assertEquals(cpu, queue.deQueue());
		assertEquals(null, queue.deQueue()); */
		ArrayQueue<String> lolcat = new ArrayQueue<String>();
		for (int i = 0; i < 9; i++) {
			lolcat.enQueue(((Integer)i).toString());
		}
		for (int j = 0; j < 4; j++) {
			lolcat.deQueue();
		}
		for (int k = 10; k < 40; k++)
			lolcat.enQueue(((Integer) k).toString());
	}

	@Test
	void testGetFront() {
		assertEquals(null, queue.getFront());
		queue.enQueue(ram);
		queue.enQueue(cache);
		queue.enQueue(cpu);
		assertEquals(ram, queue.getFront());
	}

	@Test
	void testIsEmpty() {
		assertEquals(true, queue.isEmpty());
		queue.enQueue(ram);
		assertEquals(false, queue.isEmpty());
	}

	@Test
	void testClear() {
		queue.enQueue(ram);
		queue.enQueue(cache);
		queue.enQueue(cpu);
		assertEquals(false, queue.isEmpty());
		queue.clear();
		assertEquals(true, queue.isEmpty());
	}

}

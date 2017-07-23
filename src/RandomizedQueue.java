import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int N;
	private Item[] items;

	public RandomizedQueue() {
		N = 0;
		items = (Item[]) new Object[1];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void enqueue(Item item) {
		if (N == items.length) {
			resize(2 * items.length);
		}
		if (item == null) {
			throw new IllegalArgumentException();
		}
		items[N++] = item;
	}

	private void resize(int length) {
		Item[] temp = (Item[]) new Object[length];
		for (int i = 0; i < N; i++) {
			temp[i] = items[i];
		}
		items = temp;
	}

	private void randomSwap() {
		if (N <= 1)
			return;
		int random = StdRandom.uniform(N);
		Item temp = items[random];
		items[random] = items[N - 1];
		items[N - 1] = temp;
	}

	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		randomSwap();
		Item item = items[N - 1];
		items[N - 1] = null;
		N--;
		if (N > 0 && N == items.length / 4)
			resize(items.length / 2);
		return item;
	}

	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		randomSwap();
		return items[N - 1];
	}

	public Iterator<Item> iterator() {
		return new RandomQueueIterator();
	}

	private class RandomQueueIterator implements Iterator<Item> {
		private final int[] index;
		private int current;

		public RandomQueueIterator() {
			index = new int[N];
			for (int i = 0; i < N; i++) {
				index[i] = i;
			}
			for (int i = N - 1; i >= 0; i--) {
				int random = StdRandom.uniform(i + 1);
				int temp = index[random];
				index[random] = index[i];
				index[i] = temp;
			}
			current = N - 1;
		}

		public boolean hasNext() {
			return current >= 0;
		}

		public Item next() {
			if (current < 0) {
				throw new NoSuchElementException();
			}
			return items[index[current--]];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		String[] test = new In(args[0]).readAllStrings();
		for (int i = 0; i < test.length; i++) {
			rq.enqueue(test[i]);
		}
		for (String s : rq) {
			StdOut.println(s);
		}
		StdOut.println();
		String t = rq.dequeue();
		StdOut.println(t);
	}
}

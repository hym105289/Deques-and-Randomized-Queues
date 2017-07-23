import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.In;

public class Deque<Item> implements Iterable<Item> {
	private int n;
	private Node first;
	private Node last;

	private class Node {
		private Item item;
		private Node pre;
		private Node next;
	}

	public Deque() {
		n = 0;
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		if (first == null)
			return true;
		else
			return false;
	}

	public int size() {
		return n;
	}

	public void addFirst(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		Node temp = new Node();
		temp.item = item;
		temp.next = first;
		temp.pre = null;
		if (first != null) {
			first.pre = temp;
		}
		first = temp;
		n++;
		if (n == 1) {
			last = first;
		}
	}

	public void addLast(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		Node temp = new Node();
		temp.item = item;
		temp.pre = last;
		temp.next = null;
		if (last != null) {
			last.next = temp;
		}
		last = temp;
		n++;
		if (n == 1) {
			first = last;
		}

	}

	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = first.item;
		first = first.next;
		if (first != null)
			first.pre = null;
		n--;
		if (n == 0) {
			last = null;
		}
		return item;
	}

	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = last.item;
		last = last.pre;
		if (last != null)
			last.next = null;
		n--;
		if (n == 0) {
			first = null;
		}
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {
		String[] test = new In(args[0]).readAllStrings();
		for (String s : test) {
			System.out.println(s);
		}
		System.out.println();
		Deque<String> deque = new Deque<String>();
		for (int i = 0; i < test.length; i++) {
			deque.addLast(test[i]);
		}
		for (String w : deque) {
			System.out.println(w);
		}
		for (int i = 0; i < test.length; i++) {
			test[i] = deque.removeLast();
			System.out.println(test[i]);
		}
	}
}

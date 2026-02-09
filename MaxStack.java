class MaxStack {
	
	private class Node {

		Node prev, next;
		int val;
		Node(int val) {
			this.val = val;
		}
	}



	private Node head, tail;
	TreeMap<Integer, List<Node>()) map;

	MaxStack() {

		head = new Node(0);
		tail = new Node(0);
		map = new TreeMap();

		head.next = tail;
		tail.prev = head;

	}


	void push(int val) {
		Node node = new Node(val);

		node.next = tail;
		node.prev = tail.prev;
		tail.prev.next = node;
		tail.prev = node;

		map.computeIfAbsent(val, z ->new ArrayList<>()).add(node);

	}

	int pop() {

		Node node = tail.prev;

		node.prev.next = node.next;
		node.next.prev = node.prev;

		if (map.get(node.val).isEmpty()) {
			map.remove(node.val);
		}

		return node.val;

	}

	int peekMax() {

		return map.lastKey();

	}

	int top() {
		return tail.prev.val;
	}

	int popMax() {
		int max = map.getLastKey();
		List<Node> list = map.get(max);

		Node node = list.get(list.size() - 1);

		node.prev.next = node.next;
		node.next.prev = node.prev;

		if (map.get(node.val).isEmpty()) {
			map.remove(node.val);
		}

		return max;

	}



}
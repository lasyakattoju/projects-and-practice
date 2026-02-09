class AllOne {

    private class Node {
        Node prev, next;
        int count;
        Set<String> keys;
        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    private Map<String, Node> map;
    Node head, tail;

    public AllOne() {
        head = new Node(0);
        tail = new Node(0);
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }
    
    public void insertAfter(Node prevNode, Node node) {
        node.next = prevNode.next;
        node.prev = prevNode;
        prevNode.next.prev = node;
        prevNode. next = node;

    }

    public void remove(Node removeNode) {
        removeNode.prev.next = removeNode.next;
        removeNode.next.prev = removeNode.prev;
    }

    public void inc(String key) {
        if (!map.containsKey(key)) {
            Node node = head.next;
            if (node != tail && node.count == 1) {
                node.keys.add(key);
                map.put(key, node);
            } else {
                Node newNode = new Node(1);
                newNode.keys.add(key);
                insertAfter(head, newNode);
                map.put(key, newNode);
            }
        } else {
            Node node = map.get(key);
            Node next = node.next;
            int newCount = node.count + 1;

            if (next != tail && next.count == newCount) {
                next.keys.add(key);
                map.put(key, next);
            } else {
                Node newNode = new Node(newCount);
                newNode.keys.add(key);
                insertAfter(node, newNode);
                map.put(key, newNode);
            }

            node.keys.remove(key);
            if (node.keys.isEmpty()) remove(node);
        }

    }
    
    public void dec(String key) {
        Node node = map.get(key);
        if(node == null) return;

        if(node.count == 1) {
            map.remove(key);
        } else {
            int newCount = node.count -1;
            Node prev = node.prev;
            if(prev != head && prev.count == newCount) {
                prev.keys.add(key);
                map.put(key, prev);
            } else {
                Node newNode = new Node(newCount);
                newNode.keys.add(key);
                insertAfter(node, newNode);
                map.put(key, newNode);
            }
            node.keys.remove(key);
            if (node.keys.isEmpty()) remove(node);
        }
        
    }
    
    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }
    
    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
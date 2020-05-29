package com.dell.nodes;

public class LinkedListNode<T> {
	T data;
	LinkedListNode<T> next;
	LinkedListNode<T> previous;
	public LinkedListNode<T> getPrevious() {
		return previous;
	}
	public void setPrevious(LinkedListNode<T> previous) {
		this.previous = previous;
	}
	public LinkedListNode(T data) {
		this.data = data;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public LinkedListNode<T> getNext() {
		return next;
	}
	public void setNext(LinkedListNode<T> next) {
		this.next = next;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkedListNode other = (LinkedListNode) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return " [" + data + "]";
	}
	
}

class TestLinkedListNode{
	public static void main(String[] args) {
		LinkedListNode<Integer> n = new LinkedListNode<Integer>(23);
		System.out.println(n);
	}
}
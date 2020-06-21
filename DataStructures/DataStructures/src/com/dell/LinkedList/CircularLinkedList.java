package com.dell.LinkedList;

import com.dell.nodes.LinkedListNode;

public class CircularLinkedList<T> {
	LinkedListNode<T> head;
	LinkedListNode<T> last;
	private int size = 0;
	
	public int getSize() {
		return size;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if(head!=null) {
			LinkedListNode<T> node = head;
			while (!node.getNext().equals(this.head)) {
				sb.append(node.getData());
				if(!node.getNext().equals(head)) {
					sb.append(",");
				}
				node = node.getNext();
			}
			sb.append(node.getData());
		}
		sb.append("]");
		return sb.toString();
	}
	
	public String reverseToString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if(last!=null) {
			LinkedListNode<T> node = last;
			while (!node.getPrevious().equals(this.last)) {
				sb.append(node.getData());
				if(!node.getPrevious().equals(last)) {
					sb.append(",");
				}
				node = node.getPrevious();
			}
			sb.append(node.getData());
		}
		sb.append("]");
		return sb.toString();
	}


	public void add(T data) {
		LinkedListNode<T> node = new LinkedListNode<T>(data);
		if (this.head == null) {
			this.head = node;
			node.setNext(node);
			node.setPrevious(node);
			last = node;
		}
		else {
			last.setNext(node);
			node.setPrevious(last);
			node.setNext(head);
			head.setPrevious(node);
			last = node;
		}
		size++;
	}
	
	public void addAtPoint(T data , int index) {
		if (index > size || index < 0) {
			throw new IllegalArgumentException("index is not valid to insert at a point");
		}
		int count = 0;
		LinkedListNode<T> node = this.head;
		LinkedListNode<T> insertNode = new LinkedListNode<T>(data);
		if(head!=null) {
			while (!node.getNext().equals(head) && count != index) {
				node = node.getNext();
				count++;
			}
			
			if(count == index) {
				LinkedListNode<T> prev = node.getPrevious();
				if(prev.equals(last)) {
					this.head = insertNode;
				}
				prev.setNext(insertNode);
				insertNode.setPrevious(prev);
				insertNode.setNext(node);
				node.setPrevious(insertNode);
			}else {
				node.getNext().setPrevious(insertNode);
				insertNode.setNext(node.getNext());
				node.setNext(insertNode);
				insertNode.setPrevious(node);
				last = insertNode;
			}
		}else {
			this.head = insertNode;
			head.setNext(insertNode);
			head.setPrevious(insertNode);
			last = head;
		}
		size++;
	}
	
	public T getIndex(int getIndex) {
		LinkedListNode<T> node = head;
		if (getIndex > size-1 || getIndex < 0) {
			throw new IllegalArgumentException("index is not valid or element is not present in list");
		}else {
			
			int index = 0;
			while(!node.getNext().equals(head) && index != getIndex) {
				if (index == getIndex)
					break;
				node = node.getNext();
				index++;
			}
			if (index != getIndex) {
				node = node.getNext();
			}
		}
		return node.getData();
	}
	
	public void delete(T data) {
		if(last.equals(head) &&  head.getData()==data) {
			head = null;
			last = null;
			size = 0;
			return;
		}
		LinkedListNode<T> node = head;
		while(node.getNext()!=head) {
			if(node.getData()==data) {
				LinkedListNode<T> prev = node.getPrevious();
				prev.setNext(node.getNext());
				if (node.equals(head)) {
					this.head = node.getNext();
				}
				node.getNext().setPrevious(prev);
			}
			node = node.getNext();
		}
		if(node.equals(last) && node.getData()==data) {
			LinkedListNode<T> prev = node.getPrevious();
			prev.setNext(node.getNext());
			node.getNext().setPrevious(prev);
			this.last = prev;
		}
		size--;
	}
	
	public void deleteAtIndex(int deleteIndex) {
		if (deleteIndex > size-1 || deleteIndex < 0) {
			throw new IllegalArgumentException("index is not valid for deletion");
		}else {
			if (deleteIndex == 0) {
					if(size > 1) {
						LinkedListNode<T> prev = head.getPrevious();
						this.head = head.getNext();
						prev.setNext(head);
					}else {
						head = null;
						last = null;
						size = 0;
						return;
					}
				
			}
			else {
				LinkedListNode<T> node = head;
				int index = 0;
				while(node.getNext()!=head && index != deleteIndex) {
					node = node.getNext();
					index++;
				}
				LinkedListNode<T>prev = node.getPrevious();
				prev.setNext(node.getNext());
				node.getNext().setPrevious(prev);
				if(node.getNext()==head) {
					last = prev;
				}
			}
			size--;
		}
	}
	public void reverse() {
		if(head!=null)
			reverseList(head);
	}


	private void reverseList(LinkedListNode<T> head) {
		if(head.getNext()==this.head) {
			LinkedListNode<T> prev = head.getPrevious();
			LinkedListNode<T> next = head.getNext();
			head.setNext(prev);
			head.setPrevious(next);
			this.head = head;
		}else {
			reverseList(head.getNext());
			LinkedListNode<T> prev = head.getPrevious();
			LinkedListNode<T> next = head.getNext();
			if(prev.equals(last)) {
				last = head;
			}
			head.setNext(prev);
			head.setPrevious(next);
		}
	}
	public void reverseUpTo(int reverseIndex) {
		if (reverseIndex > size-1 || reverseIndex < 1) {
			throw new IllegalArgumentException("index is not valid for reversing upto");
		}else {
			if(head!=null)
				reverseUpTo(head,reverseIndex,0);
		}
	}


	private void reverseUpTo(LinkedListNode<T> head, int reverseIndex, int count) {
		if(head.getNext().equals(this.head)|| count == reverseIndex) {
			LinkedListNode<T> next = head.getNext();
			LinkedListNode<T> prev = head.getPrevious();
			head.setNext(prev);
			head.setPrevious(next);
			this.head = head;
		}else {
			reverseUpTo(head.getNext(), reverseIndex, ++count);
			LinkedListNode<T> next = head.getNext();
			LinkedListNode<T> prev = head.getPrevious();
			if(prev.equals(last)) {
				LinkedListNode<T> point = this.head.getPrevious();
				if(!point.equals(head)) {
					head.setNext(point);
					point.setPrevious(head);
					this.head.setPrevious(last);
					this.last.setNext(this.head);
				}else {
					head.setNext(prev);
					this.last = head;
				}
				head.setPrevious(next);
			}else {
				head.setNext(prev);
				head.setPrevious(next);
			}
			
		}
	}
}

class TestCircularLinkedList{
	public static void main(String[] args) {
		CircularLinkedList<Integer> cl = new CircularLinkedList<Integer>();
		for (int i = 1; i<=9;i++) {
			cl.add(i);
		}
		//cl.deleteAtIndex(9);
		cl.reverseUpTo(8);
		System.out.println(cl);
		System.out.println(cl.reverseToString());
//		cl.delete(10);
//		System.out.println(cl);
//		System.out.println(cl.reverseToString());
//		System.out.println(cl.getIndex(9));
	}
}

package com.dell.LinkedList;

import com.dell.nodes.*;
	
public class LinkedList<T> {
	LinkedListNode<T> head;
	LinkedListNode<T> last;
	private int size;
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		LinkedListNode<T> temp = head;
		sb.append("[");
		while(temp!=null) {
			sb.append(temp.getData());
			if(temp.getNext()!=null)
				sb.append(",");
			temp = temp.getNext();
		}
		sb.append("]");
		return sb.toString();
	}
	public String toStringReverse() {
		StringBuilder sb = new StringBuilder();
		LinkedListNode<T> temp = last;
		sb.append("[");
		while(temp!=null) {
			sb.append(temp.getData());
			if(temp.getPrevious()!=null)
				sb.append(",");
			temp = temp.getPrevious();
		}
		sb.append("]");
		return sb.toString();
	}

	public int getSize() {
		return size;
	}

	public void add(T data) {
		LinkedListNode<T> node = new LinkedListNode<T>(data);
		if(null==head) {
			head = node;
			last = node;
		}else {
			last.setNext(node);
			node.setPrevious(last);
			last = node;
		}
		size++;
 	}
	
	public void addAtIndex(T data, int position) {
		if (position > size || position < 0) {
			throw new IllegalArgumentException("index is not valid to insert at a point");
		}
		else {
			int index = 0;
			LinkedListNode<T> node = head;
			LinkedListNode<T> dataNode = new LinkedListNode<T>(data);
			LinkedListNode<T> prev = null;
			while(node!=null && index != position) {
				prev = node;
				node = node.getNext();
				index++;
			}
			LinkedListNode<T> next = node;
			if(prev!=null) {
				prev.setNext(dataNode);
				dataNode.setPrevious(prev);
			}
			else {
				dataNode.setNext(this.head);
				if(this.head!=null) {
					this.head.setPrevious(dataNode);
				}
				this.head = dataNode;
			}
			if(next!=null) {
				dataNode.setNext(next);
				next.setPrevious(dataNode);
			}
			else {
				modifyLast();
			}
		}
	}
	
	public T getIndex(int getIndex) {
		LinkedListNode<T> node = head;
		if (getIndex > size-1 || getIndex < 0) {
			throw new IllegalArgumentException("index is not valid");
		}else {
			
			int index = 0;
			while((node != null && index != getIndex)) {
				if (index == getIndex)
					break;
				node = node.getNext();
				index++;
			}
		}
		return node.getData();
	}
/*
 * Delete is going to delete only the first accurance of the element 
 */
	public void delete(T data) {
		LinkedListNode<T> node = head;
		while(node!=null) {
			if(node.getData()==data) {
				if(node.getData()==head.getData()) {
					if(head.getNext()!=null) {
						head = head.getNext();
						head.setPrevious(null);
					}else {
						head = null;
						last = null;
						
					}
				}
				else {
					LinkedListNode<T> prev = node.getPrevious();
					prev.setNext(node.getNext());
					if(node.getNext()!=null) { 
						node.getNext().setPrevious(prev);
					}else { // since last will be modified
						modifyLast();
					}
				}
				size--;
			}
			node = node.getNext();
		}
	}
	
	public void deleteAtIndex(int deleteIndex) {
		if (deleteIndex > size-1 || deleteIndex < 0) {
			throw new IllegalArgumentException("index is not valid for deletion");
		}else {
			if (deleteIndex == 0) {
				if(head.getNext()!=null) {
					head = head.getNext();
					head.setPrevious(null);
				}else {
					head = null;
					last = null;
				}
			}
			else {
				LinkedListNode<T> node = head;
				int index = 0;
				while(node!=null && index != deleteIndex) {
					node = node.getNext();
					index++;
				}
				LinkedListNode<T>prev = node.getPrevious();
				prev.setNext(node.getNext());
				if(node.getNext()!=null) {
					node.getNext().setPrevious(prev);
				}else {
					last = node;
				}
			}
			size--;
		}
	}
		
	private void modifyLast() {
		LinkedListNode<T> node = head;
		while(node.getNext()!=null) {
			node = node.getNext();
		}
		last = node;
	}
	
	public void reverse() {
		reverseList(head);
	}
	private void reverseList(LinkedListNode<T> head) {
		if(head.getNext() == null) {
			this.head = head;
			LinkedListNode<T>prev = head.getPrevious();
			head.setPrevious(null);
			head.setNext(prev);
		}
		else {
			reverseList(head.getNext());
			LinkedListNode<T> next = head.getNext();
			LinkedListNode<T> prev = head.getPrevious();
			head.setNext(prev);
			if(prev == null) {
				last = head;
			}
			head.setPrevious(next);
		}
	}
	
	public void reverseUpTo(int reverseIndex) {
		if (reverseIndex > size-1 || reverseIndex < 1) {
			throw new IllegalArgumentException("index is not valid for reversing upto");
		}else {
			reverseUpTo(head,reverseIndex,0);
		}
	}
	private void reverseUpTo(LinkedListNode<T> head, int reverseIndex,int fromIndex) {
		if(fromIndex == reverseIndex || head.getNext() == null) {
			this.head = head;
			LinkedListNode<T> prev = head.getPrevious();
			LinkedListNode<T> next = head.getNext();
			head.setNext(prev);
			head.setPrevious(next);
		}
		else {
			reverseUpTo(head.getNext(),reverseIndex,++fromIndex);
			LinkedListNode<T> prev = head.getPrevious();
			LinkedListNode<T> next = head.getNext();
			if(prev == null) {
				LinkedListNode<T> point = this.head.getPrevious();
				head.setNext(point);
				this.head.setPrevious(null);
				if(point!=null) {
					point.setPrevious(head);
				}else {
					this.last = head;
				}
			}else {
				head.setNext(prev);
			}
			head.setPrevious(next);
		}
	}
	
	public void reverseFromIndex(int reverseIndex) {
		if (reverseIndex > size-2 || reverseIndex < 0) {
			throw new IllegalArgumentException("index is not valid for reversing from");
		}else {
			LinkedListNode<T> from = null;
			LinkedListNode<T> node = head;
			int index = 0;
			while(node != null && index != reverseIndex) {
				from = node;
				node = node.getNext();
				index++;
			}
			reverseFromIndex(node,from);
		}
	}
	private void reverseFromIndex(LinkedListNode<T> head,LinkedListNode<T> from) {
		if(head.getNext() == null) {
			if(from!=null)
				from.setNext(head);
			else {
				this.head = head;
			}
			LinkedListNode<T> prev = head.getPrevious();
			head.setPrevious(from);
			head.setNext(prev);
		}
		else {
			reverseFromIndex(head.getNext(),from);
			LinkedListNode<T> next = head.getNext();
			LinkedListNode<T> prev = head.getPrevious();
			if(prev == null || prev.equals(from)) {
				head.setNext(null);
				last = head;
				
			}else {
				head.setNext(prev);
			}
			head.setPrevious(next);
		}
	}
	
	public void reversePairWise(int reverser) {
		reverseKthWise(head,reverser);
	}
	
	private void reverseKthWise(LinkedListNode<T> head,int reverser) {
		if(head == null || reverser == 1)
			return;
		int count = 1;
		LinkedListNode<T> node = head;
		LinkedListNode<T> next = null;
		LinkedListNode<T> prev = null;
		LinkedListNode<T> start = node;
		while(node!=null && count<=reverser) {
			next = node.getNext();
			prev = node.getPrevious();
			node.setNext(prev);
			node.setPrevious(next);
			if(count != reverser && next != null)
				node = next;
			else 
				count = reverser+1;
			count++;
		}
		if(start.getNext()==null) {
			this.head = node;
			node.setPrevious(null);
		}else {
			LinkedListNode<T> temp = start.getNext();
			node.setPrevious(temp);
			temp.setNext(node);
		}
		start.setNext(next);
		if(next!=null) {
			next.setPrevious(start);
		}
		else {
			last = start;
			start.setNext(null);
		}
		reverseKthWise(next,reverser);
	}
}
class TestLinkedList{
	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		for(int i = 1;i<=15;i++) {
			l.add(i);
		}
		System.out.println(l.getIndex(l.getSize()-1));
//		l.addAtIndex(0, 0);
		l.reversePairWise(3);
		System.out.println(l);
		System.out.println(l.toStringReverse());
//		System.out.println();
//		l.deleteAtIndex(0);
//		System.out.println(l);
//		System.out.println(l.toStringReverse());
//		System.out.println();
//		l.addAtIndex(1,0);
//		System.out.println(l);
//		System.out.println(l.toStringReverse());
//		System.out.println();
//		l.reverse();
//		System.out.println(l);
//		System.out.println(l.toStringReverse());
//		l.reverseUpTo(4);
//		l.reverseFromIndex(4);
//		System.out.println();
//		System.out.println(l);
//		System.out.println(l.toStringReverse());
	}
}

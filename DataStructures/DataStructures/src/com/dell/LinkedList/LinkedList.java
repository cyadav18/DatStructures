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
					modifyLast();
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
	
}
class TestLinkedList{
	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		for(int i = 1;i<=2;i++) {
			l.add(i);
		}
		System.out.println(l.getIndex(l.getSize()-1));
//		l.delete(10);
		System.out.println(l);
		System.out.println(l.toStringReverse());
		System.out.println();
		l.deleteAtIndex(0);
		System.out.println(l);
		System.out.println(l.toStringReverse());
		System.out.println();
		l.addAtIndex(1,0);
		System.out.println(l);
		System.out.println(l.toStringReverse());

		
	}
}

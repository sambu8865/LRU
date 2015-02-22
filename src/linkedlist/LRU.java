package linkedlist;

import java.util.HashMap;
import java.util.Scanner;

class DoubleLinkedListNode{
	public int val;
	public int key;
	public DoubleLinkedListNode next;
	public DoubleLinkedListNode prev;
	public DoubleLinkedListNode(int k, int v){
		this.val=v;
		this.key=k;
	}
}

public class LRU {
	private HashMap<Integer, DoubleLinkedListNode> map= new HashMap<Integer,DoubleLinkedListNode>();
	private int count=0;
	private DoubleLinkedListNode head=null;
	private DoubleLinkedListNode tail=null;
	public void get(Integer k){
		System.out.println("Key asked to get is "+k);
		if(map.containsKey(k)){
			if(head!=map.get(k))
			{
				removeNode(map.get(k));
				setHead(map.get(k));
			}
		}else{
			System.out.println("Number not found in cache");
			return;
		}
		System.out.println("The number asked is "+map.get(k).val);
	}
	public void set(int key,int v){
		if(map.containsKey(key)){
			map.get(key).val=v;
		}else{
			DoubleLinkedListNode newnode=new DoubleLinkedListNode(key,v);
			setHead(newnode);
			count++;
			if(count>10){
				removeNode(tail);
				count--;
			}
			map.put(key,newnode);
		}
	}
	public void removeNode(DoubleLinkedListNode x){
		if(x==tail){
			tail=x.prev;
			tail.next=null;
		}else {
			x.prev.next=x.next;
			x.next.prev=x.prev;
		}
	}
	public void setHead(DoubleLinkedListNode x){
		if(head==null){
			head=x;
			tail=head;
			x.next=null;
			x.prev=null;
		}else{
			x.next=head;
			head.prev=x;
			head=x;
		}
	}
	public void print(){
		if(head==null){
			System.out.println("The cache is empty!!!");
			return;
		}
		DoubleLinkedListNode newnode=head;
		while(newnode!=tail){
			System.out.println(newnode.key+" "+newnode.val);
			newnode=newnode.next;
		}
		System.out.println(newnode.key+" "+newnode.val);
	}
	public static void main(String[] args){
		LRU lru=new LRU();
		Scanner s=new Scanner(System.in);
		int choice=0;
		do{
			System.out.println("Select an option: 1 to set or 2 to get");
			choice=s.nextInt();
			if(choice==1){
				System.out.println("enter a key and a value");
				int key=s.nextInt();
				int val=s.nextInt();
				lru.set(key,val);
			}else if(choice==2){
				System.out.println("enter a key");
				int key=s.nextInt();
				lru.get(key);
			}
			lru.print();
			System.out.println("Select 1 to continue");
			choice=s.nextInt();
		}while(choice==1);
			
	}
}
	
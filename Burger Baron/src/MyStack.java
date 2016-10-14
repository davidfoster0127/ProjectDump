// Assignment #1, Burger Baron
// David Foster & Zeeshan Karim

public class MyStack <Type> {
	
	private MyNode head;
	
	private class MyNode {
		private Type data;
		private MyNode next;
	}
	
	public MyStack(){
		head = null;
	}
	
	public boolean isEmpty(){
		if(head == null)
			return true;
		else
			return false;
	}
	
	public void push(Type item){
		MyNode oldhead = head;
		head = new MyNode();
		head.data = item;
		head.next = oldhead;
	}
	
	public Type pop(){
		Type temp = head.data;
		head = head.next;
		return temp;
	}
	
	public Type peek(){
		return head.data;
	}
	
	public int size(){
		int i = 0;
		MyNode temp = head;
		while(temp != null){
			i++;
			temp = temp.next;
		}
		return i;
	}
	
	public String toString(){
		String s = "";
		MyNode temp = head;
		while(temp != null){
			s = s.concat((String)temp.data);
			s = s.concat("\n");
			temp = temp.next;
		}
		return s;
	}
}

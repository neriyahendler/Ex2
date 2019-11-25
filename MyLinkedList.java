import java.util.*;

    
public class MyLinkedList {
	private Node first; 
	private int size;
	public  MyLinkedList() {
        this.first = null;
        this.size=0;
    }

	public void printList(){

	    Node current = this.first;
	    if (first==null) {
	    	System.out.println("this list is empty");
	    }
	    for(int i=0;i<this.size;i++) // size-1 -> curr != null after loop 
    		{
	    	String s = " value : " +current.getValue() ;
	    	System.out.println(s);
	    	System.out.println(" ");
	        current = current.getNext();
	    	}
	    }
	    public void addNode(Node n){
	    System.out.println("Enter a value:");
	    Scanner s=new Scanner(System.in);
	    int new_val=s.nextInt();
	    n.setValue(new_val);
	    Node curr;
	    curr = this.first;
	    if (size == 0)
	    {
	    	n.setValue(new_val);
	    	this.first=n;
	    	this.size++;
	    	return;
	    }
	    for(int i=0;i<this.size-1;i++) // size-1 -> curr != null after loop 
	    	{
	   			curr=curr.getNext();
	   		}
	    	curr.setNext(n);
	    	this.size++;
	    }
	    public void addNode(Node n,int index) 
	    {
	    	System.out.println("Enter a value:");
	    	Scanner s =new Scanner(System.in);
	    	int new_val=s.nextInt();
	    	if(index>this.size||index<0)
	    	{
	    		System.out.println("there exist no such index");
	    	}
	    	n.setValue(new_val);
	    	Node curr;
	    	curr=first;
	    	for(int i = 0;i<index-1;i++)
	    	{
	    		curr=curr.getNext();
	    	}
	    	curr.setNext(n);
	    	n.setNext(curr.getNext());
	    	this.size++;
	    }
	    public void RemoveNode() {
			Node curr = first;
			Node prev = first;
			if (curr.getNext() == null) {
				first = null;
				size = 0;
			}
			else {
				while (curr.getNext() != null) {
					prev = curr;
					curr = curr.getNext();
				}
				curr = null;
				prev.setNext (null);
				size--;
			}
		}
	    public void RemoveNode(int index) {
			Node curr = first;
			Node prev = first;
			if (size > 0) {
				if (index <= size) {
					if (index == size)
						RemoveNode();
					else {
						for (int i = 0 ; i < index-1 ; i++) {
							prev = curr;
							curr = curr.getNext();
						}
						prev.setNext(curr.getNext());
						curr = null;
						size--;
					}
				}
				else {
					System.out.println("there exist no such index");
				}
			}
	    }
	    public void reverse() {
	    	   if(first!=null){
	    	        first = reverseListNodes(null , first);
	    	    }
	    	}

	    	private Node reverseListNodes(Node parent , Node child ){
	    	    Node next = child.getNext();
	    	    child.setNext(parent);
	    	    if (next==null){
	    	    	return child;
	    	    }
	    	    else {
	    	    	return reverseListNodes(child, next);
	    	    }
	    	}

	    public static void main(String[] args) {
	        MyLinkedList c= new MyLinkedList();
	    	
	    	int keyswitch=0;
	    	while(keyswitch!=7) 
	    	{
	    	System.out.println("Enter a number of function:");
	    	Scanner s=new Scanner(System.in);
	    	keyswitch=s.nextInt();
	    	switch(keyswitch) {
	    	  case 1:
	    		  Node n=new Node();
	    		  c.addNode(n);
	    	    break;
	    	  case 2:
	    		  Node t=new Node();
	    		  System.out.println("Enter an index:");
	    		  Scanner r=new Scanner(System.in);
	    		  int new_index=r.nextInt();
	    		  c.addNode(t,new_index);
	    	    break;
	    	  case 3:
	    		  c.RemoveNode();
	    	    // code block
	    		  break;
	    	  case 4:
	    		
	    		  System.out.println("Enter an index:");
	    		  Scanner z=new Scanner(System.in);
	    		  int new_index2=z.nextInt();
	    		  c.RemoveNode(new_index2);
	    		  break;
	    	  case 5:
	    		  c.printList();
	    		  break;
	    	  case 6:
	    		  c.reverse();
	    		  break;
	    	  case 7:
	    		  System.out.println("good bye");
	    		  break;
	    	  default:
	    		  System.out.println("wrong key try again");
	    		  break;
	    	}
	    	}
	    }
}
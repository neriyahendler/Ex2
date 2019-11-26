import java.util.*;

    
public class MyLinkedList{
	private Node first; //head of the list
	private int size; // size of the list 
	public  MyLinkedList() {
        this.first = null;
        this.size=0;
    }//default constructor

	public void printList(){

	    Node current = this.first;
	    if (this.first==null){//if the head of the list point on null
	    	System.out.println("this list is empty");
	    	return;
	    }// special case
	    for(int i=0;i<this.size;i++){
	    	String s = " value : " +current.getValue() ;
	    	System.out.println(s);
	    	System.out.println(" ");
	        current = current.getNext();
	    	}//move all over the list and print the value of Node
	    }//printList
	
	    public void addNode(Node n){
		    if (size == 0){//if the list is empty
		    	this.first=n;//the head of the list is the new Node
		    	this.size++;
		    	return;
		    }// special case
	    Node curr;
	    curr = this.first;//create Temporary node to update the list
	    for(int i=0;i<this.size-1;i++){ // size-1 -> curr != null after loop 
	   			curr=curr.getNext();//moving on the list until the end
	   		}
	    	curr.setNext(n);//update the new node in the end of list
	    	this.size++;
	    }//adding new node in the end of list
	    
	    public void addNode(Node n,int index) {
	    	Node curr;//create Temporary node to update the list
	    	curr=first;
	    	if (index==1){
	    		first=n;//head of the list is the new Node and we create a "new list"
	    		n.setNext(curr);//moving the others Node(in the order of "old list") to be in "new list"   
	    		this.size++;
	    		return;
	    	}// special case 
	    	for(int i = 1;i<index-1;i++){
	    			curr=curr.getNext();//moving on the list until the index
	    	}
	    	n.setNext(curr.getNext());//create point of the new Node  
	    	curr.setNext(n);//moving the others Node(in the order of "old list") to be in "new list"
	    	this.size++;
	    }//add Node
	    
	    public void RemoveNode() {
			Node curr = first;
			Node prev = first;
			if (curr.getNext() == null) {
				first = null;
				size = 0;
			}// special case : if the size of the list is 1 
			else {
				while (curr.getNext() != null) {
					prev = curr;
					curr = curr.getNext();
				}
				curr = null;
				prev.setNext (null);
				size--;
			}//delete the node in the end of list
		}//RemoveNode
	    
	    public void RemoveNode(int index) {
			Node curr = first;
			Node prev = first;
			if (size > 0) { 
				    if(index==1){
				    first=curr.getNext();
				    size--;
				    return;
				    } // special case: if the size of the list is 1
					if (index == size)
						RemoveNode();//special case: delete the node in the end of list
					else {
						for (int i = 0 ; i < index-1 ; i++) {
							prev = curr;
							curr = curr.getNext();
						}//moving on the list until the index
						prev.setNext(curr.getNext());//update the list and delete the node in index of the list
						size--;
					}
			}
	    }//RemoveNode with index
	    
	    public void reverse() {
	    	   if(first!=null){
	    	        first = reverseListNodes(null , first);//calling for reverseListNodes
	    	    }
	    	}//reverse

	    	private Node reverseListNodes(Node parent , Node child ){
	    	    Node next = child.getNext();
	    	    child.setNext(parent);
	    	    if (next==null){
	    	    	return child;
	    	    }//end of recursion
	    	    else {
	    	    	return reverseListNodes(child, next);
	    	    }//recursion
	    	}//reverseListNodes

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
	    		  System.out.println("Enter a value:");
	    		  Scanner e=new Scanner(System.in);
	    		  int new_val=e.nextInt();
	    		  n.setValue(new_val);
	    		  c.addNode(n);
	    	    break;
	    	  case 2:
	    		  Node t=new Node();
	    		  System.out.println("Enter an index:");
	    		  Scanner r=new Scanner(System.in);
	    		  int new_index=r.nextInt();
	  	    	if(new_index>c.size||new_index<0){
		    		System.out.println("there exist no such index");
		    		break;
		    	}//special case
	    		  System.out.println("Enter a value:");
	  	    	  new_val=r.nextInt();
	  	  	      t.setValue(new_val);
	    		  c.addNode(t,new_index);
	    	    break;
	    	  case 3:
	    		  c.RemoveNode();
	    		  break;
	    	  case 4:
	    		  System.out.println("Enter an index:");
	    		  Scanner z=new Scanner(System.in);
	    		  int new_index2=z.nextInt();
	    		  if (new_index2 > c.size) {	
	    			  System.out.println("there exist no such index");
	    			break;  
	    		  }//special case
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

public class Node {
		private int value;//the value of the node	
		private Node nextNode;//the next node on the list
		public Node()
		{
			this.setValue(0);
			this.setNext(null);
		}// //default constructor
		public void setNext(Node nextNode){
			this.nextNode = nextNode;
		}// class node set
			public Node getNext(){
			return this.nextNode;
		}// class node get
			public int getValue() {
				return value;
			}//class node get
			public void setValue(int value) {
				this.value = value;
			}//class node set
		
}

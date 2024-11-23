// this is the ADTLinkedList class, 
//it implements an interface called MethodsList<T> that describes the main methods used in the list
//and how each method works.
//any extra method its functionalities will be described with comments above it.
public class ADTLinkedList<T> implements MethodsList<T>{
	
	public ADTLinkedListNode<T> head,current;
	int size;
	
	public ADTLinkedList() {
		head=current=null;
		size=0;}
	
	public boolean empty () {
		 return head == null;}
	
	
		public boolean last () {
		 return current.next == null;}
		
		public boolean full () {
			return false;}
		
		public void findFirst () {
		current = head;}
		
		
		public void findNext() {
		current = current.next;}
		
		
		public T retrieve () { 
			return current.data;}
		
		public void update (T val) {
		current.data = val;}
		
		public void insert (T val) {
			ADTLinkedListNode<T> tmp;
			 if (empty()) {
			current = head = new ADTLinkedListNode<T> (val);}
			 
			 else {
			tmp = current.next;
			current.next = new ADTLinkedListNode<T> (val);
			current = current.next;
			current.next = tmp;}
			 size++;}
		
		
		public void remove () {
			 if (current == head) {
			head = head.next;
			}
			 else {
			ADTLinkedListNode<T> tmp = head;
			 while (tmp.next != current)
			tmp = tmp.next;
			tmp.next = current.next;
			}
			 if (current.next == null)
			current = head;
			 else
			current = current.next;
			 size--;}
		
		// displays the data of the list in the form [data1, data2, data3,...]
		public void display() {
			findFirst();
			System.out.print("[");
			System.out.print(current.data);
			findNext();
			while(current!=null) {
				System.out.print(", "+current.data);
				findNext();}
			System.out.print("]");}
		
		
		
		// searches for the data entered in the argument and makes the current points on the node if found. 
		// Otherwise it displays a message indicating that the data is not found.
		public void search(T e) {
			
			if(empty()) {
				System.out.println("Sorry ,the list is empty");
				return ;}
			findFirst();
			while(current!=null && current.data !=e) {
				findNext();}
			if(current==null) {
				System.out.println("sorry the data "+e+" is not found , current will go back to first element.");
				findFirst();}
			}
		
	
		
		// this method returns the data in the index (index) starting with 0.
				public T get(int index) {
					if(index<0) {
						System.out.println("index is less than zero the method will return null.");
						return null;}
					findFirst();
					int i=0;
					while(i<index) {
						findNext();
						i++;}
					return retrieve();}
		
		

		

}

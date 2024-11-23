
public class ADTLinkedList<T> implements ListOfMethods<T>{
	
	public ADTLinkedListNode<T> head,current;
	
	public ADTLinkedList() {
		head=current=null;}
	
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
			current.next = tmp;}}
		
		
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
			current = current.next;}
		
		
		public void display() {
			findFirst();
			
			while(current!=null) {
				System.out.print(current.data+" --> ");
				findNext();}}
		
		
		public void search(T e) {
			
			if(empty()) {
				System.out.println("Sorry ,the list is empty");
				return ;}
			findFirst();
			while(current!=null && current.data !=e) {
				findNext();}
			if(current==null) {
				System.out.println("sorry the data "+e+" is not found , current will go back to first element.");
				findFirst();}}
		

		
			

		

	
	

}

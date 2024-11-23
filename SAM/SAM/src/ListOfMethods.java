//this is a generic interface that represents the methods that will be used in a generic array list called "ADTArrayList" 
public interface ListOfMethods<T> {
	//which gives a boolean value whether the array list is full or not.
	public boolean full();
	
	//this method gives a boolean value if the list is empty.
	public boolean empty();
	
	//which tells you if the current pointer in the list is pointing to the last element of the list.
	public boolean last();
	
	//this method makes the current pointer points at the first element of the list. 
	public void findFirst();
	
	// this method makes the current point at the next element.
	public void findNext();
	
	// this method returns the data stored in the element that the current points on.
	public T retrieve();
	
	// this method updates the data of the current element in the array list.
	public void update(T data);
	
	
	/*use that method to insert a new element after the current element in the list. 
	the new element is made the current element. 
	if the list is empty then the new element is made the head (the first element) and also the current
	 after the insertion the current size of the array (which will be represented in the class ADTArrayList) is incremented.
	  */
	public void insert(T data);
	
	/*
	 * the current element is removed from the list. If the resulting list is empty current is
       set to NULL. If successor of the deleted element exists it is made the new current element
       otherwise first element is made the new current element. 
       after that the size is decremented. 
	 */
	public void remove();
	

}

// this interface represents the main method that will be used in the class BST<T>
public interface BSTMethods<T> {
	// this method checks if the tree is empty or not depending on the root whether is is null or not. 
	public boolean empty();
	
	// this method returns true if the tree is full , else it returns false otherwise (this method will always return false because the tree never gets full).
	public boolean full();
	
	// a method to return the data that is stored in the node that the current points on.
	public T retrieve();
	
	// this method updates the data in the current node.
	public void update(T data);
	
	// this method searches the information key sent by the user , if the key exists it will return true and makes the current points at thi node that holds the key.
	// otherwise it will return false and the current will point at the root.
	public boolean findKey(String key);
	
	// this method inserts the data e with the key (key) to the binary search tree.
	// if the key does not already exist the method will add the data to the BST and set the current to the element , and then it will return true,
	//otherwise the method will return false and the current stays at the same point.
	public boolean insert(String key, T e);
	
	
	// this method deletes the sub tree that the current points on.
	public void deleteSub();
	
	// this method makes the current points on the relative node for the current 
	//whether it is the root, parent , left child or right child.
	public boolean find(Relative.relative rel);
	
	// this method prints the data according to the traverse desired. whether pre-order , in-order or post-order.
	public void traverse(Order.order order);
	
	
	
	

}

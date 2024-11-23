
public class BSTNode<T> {
	
	public String key;
	public T data;
	public BSTNode<T> left, right;
	
	public BSTNode(String i, T data) {
		key = i;
		this.data = data;
		left = right = null;
	}
	
	public BSTNode(String i, T data, BSTNode<T> l, BSTNode<T> r) {
		key = i;
		this.data = data;
		left = l;
		right = r;
	}

	public String getKey() {
		return key;
	}

	public T getData() {
		return data;
	}

	public BSTNode<T> getLeft() {
		return left;
	}

	public BSTNode<T> getRight() {
		return right;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	public void setRight(BSTNode<T> right) {
		this.right = right;
	}
	
	
	
	

}

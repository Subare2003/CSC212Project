public class BST<T> {

	private BSTNode<T> root, current;

	public BST() {
		current = root = null;
	}

	public boolean empty() {
		return root == null;
	}

	public boolean full() {
		return false;
	}

	public void update(T data) {
		current.setData(data);
	}

	public T retrieve() {
		return current.getData();
	}

	public boolean findKey(String key) {
	    if (empty()) return false;

	    BSTNode<T> tmp = root;
	    BSTNode<T> q = root;
	    boolean found = false;

	    if (key.equals(root.getKey())) {
	        current = root;
	        return true;
	    }

	    while (tmp != null) {
	        q = tmp;
	        int compare = key.compareTo(tmp.getKey());
	        if (compare < 0) {
	            tmp = tmp.getLeft();
	        } else if (compare > 0) {
	            tmp = tmp.getRight();
	        } else {
	            current = tmp;
	            found = true;
	            break;
	        }
	    }

	    if (!found) current = q;

	    return found;
	}

	public boolean insert(String key, T e) {
	    if (empty()) {
	        current = root = new BSTNode<>(key, e);
	        return true;
	    }

	    BSTNode<T> tmp = current;

	    if (findKey(key)) {
	        current = tmp;
	        return false;
	    }

	    BSTNode<T> p = new BSTNode<>(key, e);

	    if (current.getKey().compareTo(key) > 0) {
	        current.setLeft(p);
	    } else {
	        current.setRight(p);
	    }
	    current = p;
	    return true;
	}

	public void deleteSub() {
		if (!empty()) {
			if (current == root)
				current = root = null;
			else {
				BSTNode<T> tmp = current;
				find(Relative.relative.Parent);
				if (current.getLeft() == tmp)
					current.setLeft(null);
				else
					current.setRight(null);
				current = root;
			}
		}
	}

	public boolean find(Relative.relative rel) {
		if (!empty()) {
			switch (rel) {
			case Root:
				current = root;
				return true;
			case Parent:
				if (current == root)
					return false;
				current = findparent(current, root);
				return true;
			case LeftChild:
				if (current.getLeft() == null)
					return false;
				current = current.getLeft();
				return true;
			case RightChild:
				if (current.getRight() == null)
					return false;
				current = current.getRight();
				return true;

			default:
				return false;
			}
		}
		return false;
	}

	private BSTNode<T> findparent(BSTNode<T> c, BSTNode<T> r) {
		if (r == null)
			return null;

		BSTNode<T> tmp = r;
		if (tmp.getLeft() == null && tmp.getRight() == null)
			return null;
		else if (tmp.getLeft() == c || tmp.getRight() == c)
			return tmp;
		else {
			findparent(c, tmp.getLeft());
			if (tmp != null)
				return tmp;
			else
				return findparent(c, tmp.getRight());
		}
	}
	

	public void traverse(Order.order order) {
	    switch (order) {
	        case preOrder:
	            preOrderTraversal(root);
	            break;
	        case inOrder:
	            inOrderTraversal(root);
	            break;
	        case postOrder:
	            postOrderTraversal(root);
	            break;
	    }
	    System.out.println(); 
	}

	private void preOrderTraversal(BSTNode<T> node) {
	    if (node != null) {
	        System.out.print(node.getData() + " ");          
	        preOrderTraversal(node.getLeft());               
	        preOrderTraversal(node.getRight());              
	    }
	}

	private void inOrderTraversal(BSTNode<T> node) {
	    if (node != null) {
	        inOrderTraversal(node.getLeft());                
	        System.out.print(node.getData() + " ");         
	        inOrderTraversal(node.getRight());               
	    }
	}

	private void postOrderTraversal(BSTNode<T> node) {
	    if (node != null) {
	        postOrderTraversal(node.getLeft());              
	        postOrderTraversal(node.getRight());             
	        System.out.print(node.getData() + " ");          
	    }
	}

	
	
	
	public boolean removeKey(String k) {
	    BSTNode<T> p = root;
	    BSTNode<T> q = null; 
	    while (p != null) {
	        int cmp = k.compareTo(p.getKey());
	        
	        if (cmp < 0) {
	            q = p;
	            p = p.getLeft();
	        } else if (cmp > 0) {
	            q = p;
	            p = p.getRight();
	        } else {
	            if (p.getLeft() != null && p.getRight() != null) {
	                BSTNode<T> min = p.getRight();
	                BSTNode<T> minParent = p;
	                
	                while (min.getLeft() != null) {
	                    minParent = min;
	                    min = min.getLeft();
	                }
	                
	                p.setKey(min.getKey());
	                p.setData(min.getData());
	                
	                p = min;
	                q = minParent;
	                k = min.getKey();
	            }

	            BSTNode<T> child = (p.getLeft() != null) ? p.getLeft() : p.getRight();

	            if (q == null) {
	                root = child;
	            } else if (p == q.getLeft()) {
	                q.setLeft(child);
	            } else {
	                q.setRight(child);
	            }

	            current = (root != null) ? root : null;
	            return true;
	        }
	    }
	    return false; 
	}

}
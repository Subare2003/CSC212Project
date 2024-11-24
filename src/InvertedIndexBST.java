

public class InvertedIndexBST {
	
	public BST<Word> allWords;
	
	public InvertedIndexBST() {
		allWords = new BST<Word>();
	}
	
	public void addWord(String text, int id) {
		if(!allWords.findKey(text)) {
			Word w = new Word(text);
			w.docIds.insert(id);
			allWords.insert(text, w);
		}
		else {
			Word ew = allWords.retrieve();
			ew.docIds.insert(id);
			
		}
	}
	
	public void display() {
		if(allWords.empty()) {
			System.out.println("allWords is empty");
			return ;}
		
		allWords.traverse(Order.order.inOrder);}
	
	
	// Public search method that checks if the word exists in the BST
    public boolean search(String key) {
        return searchHelper(allWords.root, key); 
    }

    // Private helper method for recursively searching the BST
    private boolean searchHelper(BSTNode<Word> node, String key) {
        if (node == null) {
            return false; 
        }

       
        if (key.equalsIgnoreCase(node.getData().line)) { 
            return true; 
        }

        
        if (key.compareToIgnoreCase(node.getData().line) < 0) {
            return searchHelper(node.getLeft(), key); 
        }

        
        return searchHelper(node.getRight(), key);}
    
    
    public boolean searchForRanking(String word) {
		return allWords.findKey(word);}
    
	
	

	
	
	
}
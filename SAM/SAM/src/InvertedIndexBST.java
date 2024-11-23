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
			return ;		
			}
		allWords.traverse(Order.order.inOrder);
	}
	
}
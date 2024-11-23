
public class InvertedIndex {
	
	public ADTLinkedList<Word> allWords; // list of all the words.

	// constructor.
	public InvertedIndex() {
		allWords = new ADTLinkedList<Word>();}
	
	public void addWord(String text, int id) {
		if(!searchWord(text)) {
			Word w = new Word(text);
			w.docIds.insert(id);
			allWords.insert(w);
		}
		else {
			Word ew = allWords.retrieve();
			ew.docIds.insert(id);
			
		}
	}
	
	
	public boolean searchWord(String word) {
		if(allWords.empty())
			return false;
		allWords.findFirst();
		while(!allWords.last()) {
			if(allWords.retrieve().line.equals(word))
				return true;
			allWords.findNext();
		}
		if(allWords.retrieve().line.equals(word))
			return true;
		return false;
	}
	
	public void display() {
		if(allWords.empty()) {
			System.out.println("allWords is empty");
			return ;}
		
		allWords.findFirst();
		while(!allWords.last()) {
			Word w = allWords.retrieve();
			System.out.println("\n========");
			System.out.print("Word: " + w.line);
			System.out.print(" [");
			if(w.docIds.size>1) {
				w.docIds.findFirst();
				System.out.print(w.docIds.retrieve());
				w.docIds.findNext();
				for(int i=1;i<w.docIds.size;i++) {
					System.out.print(", "+w.docIds.retrieve());
					w.docIds.findNext();}
				System.out.println("]");}
			else {
			System.out.print(w.docIds.retrieve());
			System.out.println("]");}
			allWords.findNext();
		}
		Word w = allWords.retrieve();
		System.out.println("\n========");
		System.out.print("Word: " + w.line);
		System.out.print(" [");
		if(w.docIds.size>1) {
			w.docIds.findFirst();
			System.out.print(w.docIds.retrieve());
			w.docIds.findNext();
			for(int i=1;i<w.docIds.size;i++) {
				System.out.print(", "+w.docIds.retrieve());
				w.docIds.findNext();}
			System.out.println("]");}
		else {
		System.out.print(w.docIds.retrieve());
		System.out.println("]");}
		allWords.findNext();
			
	}
}

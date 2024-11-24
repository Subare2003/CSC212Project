
public class Word {
	public String line;
	public ADTLinkedList<Integer> docIds;
	
	public Word(String word) {
		this.line= word;
		docIds=  new ADTLinkedList<Integer>();}
	
	public void add(int docId) {
		if(!docIdExists(docId)) {
			docIds.insert(docId);}}
	
	
	public boolean docIdExists(int id) {
		if(docIds.empty()) {
			return false;}
		else {
			docIds.findFirst();
			while(!docIds.last()) {
				
				if(docIds.retrieve()==id) {
					return true;}
				docIds.findNext();}
			
			return false;}}
	
	
	
	

}

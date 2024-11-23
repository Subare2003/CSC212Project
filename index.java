// class index 
// this class is used to store the documents and its ids. 
// this class also provides a method display that displays the document id and then in a new line prints the words in a document.
public class index {
	public ADTLinkedList<Document> allDocuments; 
	
	public index() {
		allDocuments= new ADTLinkedList<Document>();}
	
	// add method to add the document to the index.
	public boolean addDocument(Document document) {
		allDocuments.insert(document);	
		
		if(allDocuments.retrieve().equals(document)) { // if statement to check if the document is added successfully.
			return true;}
		else {
			return false;}}
	
	public void displayAllDocuments() { // displays all the documents in the file.
		if(allDocuments.empty()) {
			System.out.println("Sorry. There are no documents to display.");
		return ;}
		
		allDocuments.findFirst();
		
		System.out.print("All Documents are:");
		
		while(!allDocuments.last()) { 
			System.out.println("\nDocument ID: "+allDocuments.retrieve().id);
			
			allDocuments.retrieve().words.display();
			
			allDocuments.findNext();}
		
		
		// the extra code is for the last element in allDocuments.
		System.out.print("\nDocument ID: "+allDocuments.retrieve().id+"\n");  
		
		allDocuments.retrieve().words.display();
		
		
		System.out.println("\nEnd of documents.");
		
		
		
	
	}
	

}


public class Document {
	public int id;
	ADTLinkedList<String> words = new ADTLinkedList<String>(); 
	
	public Document(int id , ADTLinkedList<String> words) {
		this.id=id;
		this.words=words;}
	
}

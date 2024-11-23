
public class Main {
	public static void main(String[]args) {
		
		ADTLinkedList<String> words = new ADTLinkedList<String>();
		ADTLinkedList<String> words2 = new ADTLinkedList<String>();
		ADTLinkedList<String> words3 = new ADTLinkedList<String>();
		ADTLinkedList<String> words4 = new ADTLinkedList<String>();
		
		//"The national flag of the Kingdom of Saudi Arabia color is green and white"
		//"The green color extends from the pole to the end of the flag"
		//"The bright green color known as emerald green"
		//"The flag of Saudi Arabia has an Arabic shahada and a sword in snow white"
		
		words.insert("national");words.insert("flag");words.insert("kingdom");words.insert("Saudi");words.insert("Arabia");words.insert("color");words.insert("green");words.insert("white");
		words2.insert("green");words2.insert("color");words2.insert("extends");words2.insert("pole");words2.insert("end");words2.insert("flag");
		words3.insert("bright");words3.insert("green");words3.insert("known");words3.insert("emerald");words3.insert("green");
		words4.insert("flag"); words4.insert("Saudi");words4.insert("Arabia");words4.insert("Arabic"); words4.insert("shahada");words4.insert("sword");words4.insert("snow"); words4.insert("white");
		
		
		Document d1 = new Document(1,words);
		Document d2 = new Document(2,words2);
		Document d3= new Document(3,words3);
		Document d4 = new Document(4,words4);
		
		index in = new index();
		in.addDocument(d1);
		in.addDocument(d2);
		in.addDocument(d3);
		in.addDocument(d4);
		
		in.displayAllDocuments();
		
		
		InvertedIndex ivin = new InvertedIndex();
		ivin.addWord("national", 1);
		ivin.addWord("green", 1);
		ivin.addWord("national", 3);
		ivin.display();
		
		
		InvertedIndexBST inbst = new InvertedIndexBST();
		inbst.addWord("national", 1);
		inbst.addWord("green", 1);
		inbst.addWord("national", 3);
		inbst.display();
		
		
	
		
		
		
		
		
	
	}

}

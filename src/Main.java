import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[]args) throws FileNotFoundException, IOException {
		Scanner kb = new Scanner(System.in);
		
		ADTLinkedList<String> words = new ADTLinkedList<String>();
		ADTLinkedList<String> words2 = new ADTLinkedList<String>();
		ADTLinkedList<String> words3 = new ADTLinkedList<String>();
		ADTLinkedList<String> words4 = new ADTLinkedList<String>();
		
		//"The national flag of the Kingdom of Saudi Arabia color is green and white"
		//"The green color extends from the pole to the end of the flag"
		//"The bright green color known as emerald green"
		//"The flag of Saudi Arabia has an Arabic shahada and a sword in snow white"
		
		words.insert("national");words.insert("flag");words.insert("kingdom");words.insert("Saudi");
		words.insert("Arabia");words.insert("color");words.insert("green");words.insert("white");
		words2.insert("green");words2.insert("color");words2.insert("extends");words2.insert("pole");
		words2.insert("end");words2.insert("flag");
		words3.insert("bright");words3.insert("green");words3.insert("known");words3.insert("emerald");
		words3.insert("green");
		words4.insert("flag"); words4.insert("Saudi");words4.insert("Arabia");words4.insert("Arabic");
		words4.insert("shahada");words4.insert("sword");words4.insert("snow"); words4.insert("white");
		
		
		Document d1 = new Document(1,words);
		Document d2 = new Document(2,words2);
		Document d3= new Document(3,words3);
		Document d4 = new Document(4,words4);
		
		
		Data data = new Data("C:/Users/sr717/OneDrive/Desktop/dataset.csv","C:/Users/sr717/OneDrive/Desktop/stop.txt");
		
		data.loadStopWords();
		
		data.loadDataFile();
		
		
		String qs = "market AND investors";
		
		Query query = new Query(data.indexEnhancer);
		
		ADTLinkedList<Integer> q1= query.andQuery(qs);
		
		RankingDocs rank=new RankingDocs("drones",data.indexWords,data.InvertedBST);
		// RankingDocs.insertInTheList();
		//  RankingDocs.displayRankedListDetails();
	//	RankingDocs.getById(16).words.display();
		RankingDocs.insertInTheList();
		RankingDocs.displayRankedListDetails();
		
		
		
		
		
		
		
		
	
		
		
		
		
		
	
		
		
		
		
			
	
	}
	
	
    
public static void sort(ADTLinkedList<Integer> list) {
	if(list.empty()||list.size<=1) {
		return;}
	
	for(int i=0;i<list.size;i++) {
		ADTLinkedListNode<Integer> p=list.head;
		ADTLinkedListNode<Integer> q = list.head.next;
	while(q!=null) {
		if(p.data>q.data) {
			int tmp = p.data;
			p.data=q.data;
			q.data=tmp;}
		
		p=p.next;
		q=q.next;}}
	
}


	
	

	
	
	
	

}

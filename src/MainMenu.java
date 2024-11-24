import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class MainMenu {
	static Scanner kb= new Scanner(System.in);
	static Data data;
	public static void main(String[] args) throws FileNotFoundException, EOFException, IOException {
		
		String dataFilePath=null;
		String dataStopWordsPath=null;
		int choice=0;
		
		
		System.out.println("====================================== Main Menu ======================================");
		System.out.println("Simple Search Engine");
		System.out.println("welcome to our search engine");
		System.out.println("please Enter the data file path and the stop words file path to continue");
		System.out.print("data File path:");
		dataFilePath= kb.nextLine();
		System.out.print("stop words path:");
		dataStopWordsPath= kb.nextLine();
		
		
		 data = new Data(dataFilePath, dataStopWordsPath);
		 
		 data.loadStopWords();
		 
		 data.loadDataFile();
		
		while(choice!=10) {
			servicesMenu();
			System.out.println();
			System.out.println("Enter your choice: ");
			choice=kb.nextInt();
			
			if(choice>10||choice<=0) {
				System.err.println("wrong choice");
				continue;}
			
			else if(choice==1) {
				retrieveATerm(data);
			}
			
			else if(choice==2) {
				booleanRetreieval();
			}
		
			else if(choice==3) {
			 
				System.out.println("Enter the query...");
				String bufferRemover=kb.nextLine();
				
				RankingDocs m=new RankingDocs(kb.nextLine(),data.indexWords,data.InvertedBST);
				m.insertInTheList();
				m.displayRankedListDetails();
				
			}
			
			
			else if(choice==4) {
				printAllDocuments(data);}
			
			else if(choice==5) {
				printNumberOfDocuments(data);}
			
			else if(choice==6) {
				int count =0;
				data.uniqueWords.findFirst();
				while(!data.uniqueWords.last()){
					count++;
					data.uniqueWords.findNext();
					
				}
				count++;
					
				System.out.println("Number of unique words is: "+count);
				
				
			}
			
			else if(choice==7) {
				showInvertedList(data);
				}
			
			else if(choice==8) {
				showInvertedBST(data);}
			
			else if(choice==9) {
				System.out.println("The number of tokens is: "+data.numOfTokens);
			}
			
			
			
		}
		
		
		
		
		
	}
	
	
	
	public static void servicesMenu() {
		System.out.println();
		System.out.println("*************************services*************************");
		System.out.println("1- Retrieve a term.");
		System.out.println("2- Boolean retrieval.");
		System.out.println("3- Ranked retrieval.");
		System.out.println("4- Print all the Documents.");
		System.out.println("5- Number of documents in the index.");
		System.out.println("6- Number of unique words in index.");
		System.out.println("7- Show the inverted index list.");
		System.out.println("8- Show the inverted index binary search tree.");
		System.out.println("9- Show Number of words and tokens in the index.");
		System.out.println("10- Exit.");}
	
	public static void retrievalTermMenu() {
		System.out.println("1- using index with list");
		System.out.println("2- using inverted index list");
		System.out.println("3- using inverted index BST");}
	
	public static void booleanRetreieval() {
		
		System.out.println("################### Boolean Retrieval ####################");
		Query query = new Query(data.indexEnhancer);
		int choice=0;
		System.out.println("choose the kind of the query");
		System.out.println("1- AND query");
		System.out.println("2- OR query");
		System.out.println("3- NOT query");
		System.out.println("4- AND,OR query");
		
		System.out.println("Enter your choice:");
		choice=kb.nextInt();
		kb.nextLine();
		if(choice==1) {
			System.out.println("Enter your query:");
			String q = kb.nextLine();
			
			query.andQuery(q).display();}
		
		else if(choice==2) {
			System.out.println("Enter your query:");
			String q = kb.nextLine();
			
			query.orQuery(q).display();}
		
		else if(choice==3) {
			System.out.println("Enter your query:");
			String q= kb.nextLine();
			
			query.not2Query(q).display();}
		
		else if(choice==4) {
			System.out.println("Enter your query:");
			String q = kb.nextLine();
			
			query.bothQuery(q).display();}
		
		else {
			System.err.println("worng choice");
		}
		System.out.println();}
	
	public static void printAllDocuments(Data data) {
		data.indexWords.displayAllDocuments();}
	
	public static void printNumberOfDocuments(Data data) {
		System.out.println("the number of documents in the index is: "
	+data.indexWords.allDocuments.size);}
	
	public static void showInvertedList(Data data) {
		data.indexEnhancer.display();}
	
	public static void showInvertedBST(Data data) {
		data.InvertedBST.display();}
	
	public static void retrieveATerm(Data data) {
		System.out.println("Enter the term you want to retrieve (Enter one word only):");
		String term=kb.next();
		term=term.toLowerCase().trim();
		System.out.println("using inverted list:");
		data.indexEnhancer.allWords.findFirst();
		
		while(!data.indexEnhancer.allWords.last()) {
			if(data.indexEnhancer.allWords.retrieve().line.equals(term)) {
				data.indexEnhancer.allWords.retrieve().docIds.display();
				}data.indexEnhancer.allWords.findNext();}
		
		if(data.indexEnhancer.allWords.retrieve().line.equals(term)) {
			data.indexEnhancer.allWords.retrieve().docIds.display();}}
		
	
	
	
	
	
	

}

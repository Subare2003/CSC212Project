//Standard file library.
import java.io.File;

//File reading libraries. 
import java.io.FileReader;
import java.io.BufferedReader;

//File exception libraries.
import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;

public class Data {
	
	public static File dataFile;//Data file.
	
	public static  File stopWordsFile; //Stop words file.
	
	//Attention. Both of Data and Stop words file have to be included in the source code in order to 
	// have their data saved into the program properly.
	
	public index indexWords;
	
	public InvertedIndex indexEnhancer;
	
	public ADTLinkedList<String> stopWordsList;
	
	public InvertedIndexBST InvertedBST;
	
	public ADTLinkedList<String> uniqueWords=new ADTLinkedList<String>();
	
	int numOfTokens=0;
	
	int numOfUniqueWords=0;
	
	public Data(String dataFile , String stopFile) {
	     stopWordsList=new ADTLinkedList<String>(); //List of stop words.
	     
	     this.dataFile= new File(dataFile);
	     stopWordsFile = new File(stopFile);
	     
	     
	     indexEnhancer=new InvertedIndex(); 
	     
	     indexWords=new index();
	     
	     InvertedBST=new InvertedIndexBST();
	}

	
	 public void loadDataFile() throws FileNotFoundException, IOException {
		  
		  BufferedReader bufferedDataReader=null; // to close the BufferedReader safely.
	  
	  try {
		  
		 bufferedDataReader=new BufferedReader(new  FileReader(dataFile)); //
	  
	  String line=bufferedDataReader.readLine();
	  while((line=bufferedDataReader.readLine())!=null) {
		  
	  if(line.trim().length()<3) {
		  
		  break;
	  }
	  
	  String identificationString=line.substring(0,line.indexOf(',')); //To get the ID only.
	  
	  int identification=Integer.parseInt(identificationString.trim()); //String-to-Integer conversion.
	  
	  String data=line.substring(line.indexOf(',')+2).trim(); //to get the content after the ID and the comma.
	  
	  
	  ADTLinkedList<String> wordsInADocument=createLinkedListOfWords(data,identification);
	  
	  Document temp=new Document(identification,wordsInADocument);
	  
	  indexWords.addDocument(temp);
	  
	  
	 
	  
	  }
	  
	  } catch (FileNotFoundException e) {
	  System.err.println("An error occurred due to file unavailability."); //If the file does not exist in the source code "SAM" file.
	 
	  } catch (IOException e) {
	  
	  System.err.println("An error occurred during file reading process."); //In case of any error occurred in progress.
	  }  
	  
	  finally {
		  
		// bufferedDataReader.close(); //Closing the BufferedReader safely.
		 
	  }
	  
	  }
	 
  
     public void loadStopWords() throws FileNotFoundException, EOFException, IOException {
    	
    	 BufferedReader bufferedStopWordsReader=null; // to close the BufferedReader safely.
    	 try {
    		 String line;
    		
			 bufferedStopWordsReader=new BufferedReader(new FileReader(stopWordsFile));
			
			while((line=bufferedStopWordsReader.readLine())!=null) {
			
				stopWordsList.insert(line); //Inserts an element of stop words file in the list.
				
			}
		
		}catch (FileNotFoundException e) {
			
			System.err.println("An error occurred due to file unavailability.");
			
		}catch (EOFException e) {
		 
	        System.out.println("End of file reached. Stop words have been loaded successfully.");
			
		} 
    	 catch (IOException e) {
			
		    System.err.println("An error occurred during file reading process.");
		}finally {
			
			bufferedStopWordsReader.close(); //To close the BufferedReader safely.
			
			
		}
    	 
    	 
      
     }
     public boolean checkIsAStopWord(String data) {
    	 if(stopWordsList.empty())
    		 return false;
    	 else {
    		 stopWordsList.findFirst();
    		 
    		 while(!stopWordsList.last()) {
    			
    			 if(stopWordsList.retrieve().equalsIgnoreCase(data))
    				 return true;
    			 
    			 stopWordsList.findNext();
    			 
    		 }
    		 
    		        if(stopWordsList.retrieve().equalsIgnoreCase(data))
    			        
    		        	return true;
    		 
   
    		               return false; 
    	 }
     }
     
     public ADTLinkedList<String> createLinkedListOfWords(String text,int id){
    	 
    	 ADTLinkedList<String> words=new ADTLinkedList<String>();
    	 
    	 loadIntoIndexAndInvertedIndex(text,words,id);
    	 
    	     return words;
    	 
    	 
     }
     
     
     public void loadIntoIndexAndInvertedIndex(String data, ADTLinkedList<String> words, int id) {
    	 data=data.replaceAll("\'", " ");
    	 data=data.replaceAll("-", " ");
    	 data=data.toLowerCase().replaceAll("[^a-zA-Z0-9]","\s");
    	  
    	 String[] dataTokens=data.split("\\s+");
    	 
    	 numOfTokens+=dataTokens.length;
    	  
    	 for(int i=0;i<dataTokens.length;i++) {
    		if(!uniqueWords.isExist(dataTokens[i]))
    			uniqueWords.insert(dataTokens[i]);
    		
    		 if(!checkIsAStopWord(dataTokens[i])) {
    		    
    			 words.insert(dataTokens[i]);
    			 
    		     indexEnhancer.addWord(dataTokens[i], id);
    		     InvertedBST.addWord(dataTokens[i], id);
    			
    			 
    		 }
    		 
    		 
    		 
    	 }
    	
    	 
    	  
   	  
     }
}


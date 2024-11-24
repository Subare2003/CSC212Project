public class RankingDocs {

	public static String query; 
	public static index theIndex; 
	public static InvertedIndexBST theEnhancedIndex; 
	public static ADTLinkedList<Integer> documentsInTheQuery; 
	public static ADTLinkedList<DocumentRanking> sortedRankedDocuments; 
	
	
	public RankingDocs(String Query, index ind, InvertedIndexBST myEnhancedIndex) {
		
		query=Query;
		sortedRankedDocuments= new ADTLinkedList<DocumentRanking>();
		theIndex=ind;
		documentsInTheQuery=new ADTLinkedList<Integer>();
		theEnhancedIndex=myEnhancedIndex;
	}
	
	
	//Checks if the id in the parameters corresponds to the document file.
	
	

	
	public static void displayRankedListDetails() {
		if(sortedRankedDocuments.empty()) {
			System.err.println("An error occurred. Empty List.");
			return;
		}
		System.out.print("DocID,  Score");
		sortedRankedDocuments.findFirst();
		while(!sortedRankedDocuments.last()) {
			sortedRankedDocuments.retrieve().display();
			sortedRankedDocuments.findNext();
		}
		sortedRankedDocuments.retrieve().display();
		
	}
	
	//gets a document by its id
	public static Document getById(int id) {
		
		 if (theIndex.allDocuments.empty())
			 return null;
		   
		  theIndex.allDocuments.findFirst();
	
		  
		  while(!theIndex.allDocuments.last()) {
			  if(theIndex.allDocuments.retrieve().id==id)
				  return theIndex.allDocuments.retrieve();
			  theIndex.allDocuments.findNext();
		  }
		  if(theIndex.allDocuments.retrieve().id==id)
			  return theIndex.allDocuments.retrieve();
		  return null;
	   }
	// To be removed. For revision check term_frequency_in_doc method.
	//returns the total frequency of a letter in a specific document
	public static int howManyTimes(Document doc, String letter){
	     int frequency=0;
	     
	   
	     doc.words.findFirst();
	     if(doc.words.empty())
	    	 return frequency;
	     while(!doc.words.last()) {
	    	 
	    	 	
	    	 if(doc.words.retrieve().equalsIgnoreCase(letter)) {
	    		 ++frequency;
	    		
	    	 } 
	    	 doc.words.findNext();
	     }
	     if(doc.words.retrieve().equalsIgnoreCase(letter)) {
    		 ++frequency;
    		
    		
	     }
	     int totalFrequency=frequency;
	     return totalFrequency;
	     
	}
	//to get a score that is a sum of frequencies of each letter in the query in a specific document.
	//It gives the ranking of the query in this document.
	
	public static int getScore(Document doc,String Query) {
		int totalFrequencies=0;
		
		if(Query.length()==0)
			return 0;
		
		String[] letters=Query.split("\\s+");
		
		
		for(int i=0;i<letters.length;i++) {
			
			totalFrequencies+=howManyTimes(doc,letters[i].trim().toLowerCase());
		}
		
		return totalFrequencies;
	}
	
	
	//ranks the Query
	public static void rankThisQuery(String inQuery) {
		ADTLinkedList<Integer> tempLinkedList=new ADTLinkedList<Integer>();
		
		if(inQuery.length()==0) return;
		
		
		String[] letters=inQuery.split("\\s+");
		
		
		boolean isFound=false;
		
		for(int i=0;i<letters.length;i++) {
			//not sure
			isFound=theEnhancedIndex.searchForRanking(letters[i].trim().toLowerCase()); //To determine the availability of the key.
			
			if(isFound) {
				
				tempLinkedList=theEnhancedIndex.allWords.retrieve().docIds;	//retrieves the document IDs
				
				loadInSortedLinkedList(tempLinkedList);
			}
		}
	}
     // a medium method to check whether the id is in the results or not
	public static void loadInSortedLinkedList(ADTLinkedList<Integer> temporary) {
		
		if(temporary.empty())
			return;
		
		temporary.findFirst();
		
		while(!temporary.last()) {
			
			boolean isFound=isInResult(documentsInTheQuery,temporary.retrieve());
			
			if(!isFound) {
				sortAddDocumentsInTheQuery(temporary.retrieve()); //inserting into a list of document ids.
			}
			temporary.findNext();
			
		}
		boolean isFound=isInResult(documentsInTheQuery,temporary.retrieve());
		
		if(!isFound) {
			sortAddDocumentsInTheQuery(temporary.retrieve());
		}//inserting into a list of document ids.
		
		
	}
	
	//checks if the id exists in the LinkedList.
	public static boolean isInResult(ADTLinkedList<Integer> res, int id) {
		
		if(res.empty())
			return false;
		
		res.findFirst();
		
		while(!res.last()) {
			
			if(res.retrieve().equals(id)) 
				return true;

			res.findNext();
			
			
				
				
				
			
		}
		if(res.retrieve().equals(id)) 
			return true;
		
		return false;
		
	}
	//loads the id into documentsInTheQuery, and sorting its elements if necessary.
	public static void sortAddDocumentsInTheQuery(Integer id) {
		
		if(documentsInTheQuery.empty()) {
			documentsInTheQuery.insert(id);
			return;
		}
		
		documentsInTheQuery.findFirst();
		
		while(!documentsInTheQuery.last()) {
			
			//if true, it will relocate the unordered id to the node that is next to the ordered id.
			if(id<documentsInTheQuery.retrieve()) {
				Integer idTemp=documentsInTheQuery.retrieve();
				documentsInTheQuery.update(id);
				documentsInTheQuery.insert(idTemp);
				return;
			}
			else
				documentsInTheQuery.findNext();
			
		}
		if(id<documentsInTheQuery.retrieve()) {
			Integer idTemp=documentsInTheQuery.retrieve();
			documentsInTheQuery.update(id);
			documentsInTheQuery.insert(idTemp);
			return;
		}
		//the list is already ordered, so, no need to relocate any node 
		else
			documentsInTheQuery.insert(id);
	}
	
	//After sorting/adding the ids, now we'll rank the list.
	public static void insertInTheList() {
		rankThisQuery(query); //to load the query in documentsInTheQuery.
		
		if(documentsInTheQuery.empty()) {
			System.err.println("An error occurred since the query is empty.");
			return;
		}
		documentsInTheQuery.findFirst();
		while(!documentsInTheQuery.last()) {
			Document currentDocument=getById(documentsInTheQuery.retrieve());
			
		      
			int rank=getScore(currentDocument,query);
			
			loadInTheSortedList(new DocumentRanking(documentsInTheQuery.retrieve(), rank));
			
			documentsInTheQuery.findNext();
		}
		Document currentDocument=getById(documentsInTheQuery.retrieve());
		
		int rank=getScore(currentDocument,query);
		loadInTheSortedList(new DocumentRanking(documentsInTheQuery.retrieve(), rank));
		
	}
	// inserts in the sorted list.
	public static void loadInTheSortedList(DocumentRanking docRank) {
		
		if(sortedRankedDocuments.empty()) {
			sortedRankedDocuments.insert(docRank);
			return;
		}
		sortedRankedDocuments.findFirst();
		
		while(!sortedRankedDocuments.last()) {
			
			if(docRank.standing>sortedRankedDocuments.retrieve().standing) {
				DocumentRanking temporary=sortedRankedDocuments.retrieve();
				
				sortedRankedDocuments.update(docRank);
				sortedRankedDocuments.insert(temporary);
				return;
			}
			sortedRankedDocuments.findNext();
			
				
			}
		if(docRank.standing>sortedRankedDocuments.retrieve().standing) {
			DocumentRanking temporary=sortedRankedDocuments.retrieve();
			
			sortedRankedDocuments.update(docRank);
			sortedRankedDocuments.insert(temporary);
			return;
		}
		else
			sortedRankedDocuments.insert(docRank);
			
			
			
			
			
			
		}
		
		
		
		
	}
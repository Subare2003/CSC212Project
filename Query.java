import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Query {
	public InvertedIndex query;
	
	public Query(InvertedIndex query) {
		this.query = query;
	}
	
	/*this method will split the words by "AND" then will put the first word in A linkedList 
	 then will call method andQuery to find the intersect separately with all the rest of words  */
	public ADTLinkedList<Integer> andQuery(String q){
		ADTLinkedList<Integer> A = new ADTLinkedList<Integer>();
		ADTLinkedList<Integer> B = new ADTLinkedList<Integer>();

		String terms[] = q.split("AND");
		if(terms.length == 0)
			return A;
		
		boolean found = query.searchWord(terms[0].trim().toLowerCase());
		if(found) {
			A = query.allWords.retrieve().docIds;
		}
		for(int i = 1; i < terms.length; i++) {
			found = query.searchWord(terms[i].trim().toLowerCase());
			if(found) {
				B = query.allWords.retrieve().docIds;
			}
			A = andQuery(A,B);
		}
		return A;
	}
	
	/*for this method it's going to find the intersection documents ids between A and B */
	private ADTLinkedList<Integer> andQuery(ADTLinkedList<Integer> a, ADTLinkedList<Integer> b){
		ADTLinkedList<Integer> result = new ADTLinkedList<Integer>();
		if(a.empty() || b.empty())
			return result;
		a.findFirst();
		while(true) {
			boolean found = existIn(result, a.retrieve());
			if(!found) {
				b.findFirst();
				while(true) {
					if(b.retrieve().equals(a.retrieve())) {
						result.insert(a.retrieve());
						break;
					}
					if(!b.last())
						b.findNext();
					else
						break;	
				}
			}
			
			if(!a.last())
				a.findNext();
			else
				break;
			
		}
		return result;
		
	}
	
	/*This method will split the words by "OR" and then put the first word in a linked list. 
	then will call the method orQuery to union the words separately with all the rest of words.*/
	public ADTLinkedList<Integer> orQuery(String q){
		ADTLinkedList<Integer> A = new ADTLinkedList<Integer>();
		ADTLinkedList<Integer> B = new ADTLinkedList<Integer>();

		String terms[] = q.split("OR");
		if(terms.length == 0)
			return A;
		
		boolean found = query.searchWord(terms[0].trim().toLowerCase());
		if(found) {
			A = query.allWords.retrieve().docIds;
		}
		for(int i = 1; i < terms.length; i++) {
			found = query.searchWord(terms[i].trim().toLowerCase());
			if(found) {
				B = query.allWords.retrieve().docIds;
			}
			A = orQuery(A,B);
		}
		return A;
	}
	
	/*for this method it's going to do a union documents ids between A and B */
	private ADTLinkedList<Integer> orQuery(ADTLinkedList<Integer> a, ADTLinkedList<Integer> b){
		ADTLinkedList<Integer> result = new ADTLinkedList<Integer>();
		if((a == null || a.empty()) && (b == null || b.empty()))
			return result;
	   
		
		if (a != null && !a.empty()) {
		a.findFirst();
		while(true) {
			boolean found = existIn(result, a.retrieve());
			if(!found) {
				result.insert(a.retrieve());
			}
			if(!a.last())
				a.findNext();
			else
				break;
		}
		}
		
	    if (b != null && !b.empty()) {

		b.findFirst();
		while(true) {
			boolean found = existIn(result, b.retrieve());
			if(!found) {
				result.insert(b.retrieve());
			}
			if(!b.last())
				b.findNext();
			else
				break;
		}
	    }
		return result;
		
	}
	

	/*this method will split the words by "NOT" then will put the first word in A linkedList 
	 then will call the method notQuery to cut the words separately with all the rest of words.*/
	public ADTLinkedList<Integer> not2Query(String q){
		ADTLinkedList<Integer> A = new ADTLinkedList<Integer>();
		ADTLinkedList<Integer> B = new ADTLinkedList<Integer>();
		
		String [] terms = q.split("NOT");
		if(terms.length == 0)
			return A;
		
		boolean found = query.searchWord(terms[0].trim().toLowerCase());
		if(found) {
			A = query.allWords.retrieve().docIds;
		}
		for(int i = 1; i < terms.length; i++) {
			found = query.searchWord(terms[i].trim().toLowerCase());
			if(found) {
				B = query.allWords.retrieve().docIds;
			}
			A = notQuery(A, B);
		}
		return A;
	}
	
	/*for this method it's going to delete the documents ids in the B list */
	private ADTLinkedList<Integer> notQuery(ADTLinkedList<Integer> a, ADTLinkedList<Integer> b){
		ADTLinkedList<Integer> result = new ADTLinkedList<Integer>();
		if((a == null || a.empty()) && (b == null || b.empty()))
			return result;
		
		if (a != null && !a.empty()) {
		 a.findFirst();
		 while(!a.last()) {
			 boolean found = existIn(result, a.retrieve());
				if(!found) {
					result.insert(a.retrieve());
				}
				a.findNext();
		 }
		boolean found = existIn(result, a.retrieve());
			if(!found) {
				result.insert(a.retrieve());
			}
		}
			

			
		    if (result != null && !result.empty()) {
				ADTLinkedList<Integer> tmpResult = new ADTLinkedList<Integer>();

			result.findFirst();
			while(!result.last()) {
				boolean found = existIn(b, result.retrieve());
				if(!found) {
					tmpResult.insert(result.retrieve());
				}
				result.findNext();
			}
			boolean found = existIn(b, result.retrieve());
				if(!found) {
					tmpResult.insert(result.retrieve());
				}
				result = tmpResult;

		    }
				
			
		return result;
		
	}
	
	/*this method will return all documents ids except the query document id*/
	public ADTLinkedList<Integer> notQuery(String q, Data data) throws FileNotFoundException, EOFException, IOException{
		ADTLinkedList<Integer> allDocuments = getAllDocumentsIds(data);
		String terms [] = q.split("NOT");
		ADTLinkedList<Integer> result = new ADTLinkedList<Integer>();
		
		allDocuments.findFirst();
		while(!allDocuments.last()) {
			boolean found = existIn(result, allDocuments.retrieve());
			if(!found)
				result.insert(allDocuments.retrieve());
			allDocuments.findNext();
		}
		result.insert(allDocuments.retrieve());
		
		
		if(result.empty() || terms.length == 0)
			return new ADTLinkedList<Integer>();
		
		for(int i = 0; i < terms.length; i++) {
			String notWord = terms[i].trim();
			
			ADTLinkedList<Integer> not = spliter(notWord);
			
			ADTLinkedList<Integer> tmpResult = new ADTLinkedList<Integer>();
			
			result.findFirst();
			while(!result.last()) {
				boolean found = existIn(not, result.retrieve());
				if(!found) {
					tmpResult.insert(result.retrieve());
				}
				result.findNext();
			}
			boolean found = existIn(not, result.retrieve());
			if(!found) {
				tmpResult.insert(result.retrieve());
			}
			result = tmpResult;
		}
		return result;
		
	}
	


	/*this method will split the words by "OR" creating individual subqueries then it will call the method 
	  processAndQuery to split the words using "AND" in the end is will combine them using (orQuery)*/
	public ADTLinkedList<Integer> bothQuery(String q) {
	    
	    String[] orTerms = q.split("\\s+OR\\s+");

	    
	    ADTLinkedList<Integer> result = null;
	    
	    

	    for (int i = 0; i < orTerms.length; i++) {
	    	String orTerm = orTerms[i].trim();
	        ADTLinkedList<Integer> andResult = processAndQuery(orTerm.trim());

	       
	        if (result == null) {
	            result = andResult;
	        } else {
	            result = orQuery(result, andResult); 
	        }
	    }

	    return result;
	}

	/*This method will split the words by "AND" and then it will use (andQuery) to intersect the document IDs*/
	private ADTLinkedList<Integer> processAndQuery(String q) {
	  
	    String[] andTerms = q.split("\\s+AND\\s+");

	    
	    ADTLinkedList<Integer> result = spliter(andTerms[0].trim());

	   
	    for (int i = 1; i < andTerms.length; i++) {
	        ADTLinkedList<Integer> nextTermDocs = spliter(andTerms[i].trim());
	        
	        result = andQuery(result, nextTermDocs);
	    }

	    return result;
	}
	
	/*this method will search for a word in the query list if it's exist it will add it 
	 to the result list and return it */
	private ADTLinkedList<Integer> spliter(String q){
		 ADTLinkedList<Integer> result = new  ADTLinkedList<Integer>();
		if(query.searchWord(q.trim().toLowerCase()))
			result = query.allWords.retrieve().docIds;
		return result;
	}
	
	
	/*this method will return true if the (retrieve) exist in (result list), otherwise false*/
	private boolean existIn(ADTLinkedList<Integer> result, Integer retrieve) {
		if(result.empty())
			return false;
		result.findFirst();
		while(!result.last()) {
			if(result.retrieve().equals(retrieve))
				return true;
			result.findNext();
		}
		if(result.retrieve().equals(retrieve))
			return true;
		return false;
	} 
	
	


	/*this method will return all documents ids in the data*/
	public ADTLinkedList<Integer> getAllDocumentsIds(Data data) throws FileNotFoundException, EOFException, IOException{

		ADTLinkedList<Integer> idsList= new ADTLinkedList<Integer>();
		
		data.indexWords.allDocuments.findFirst();
		while(!data.indexWords.allDocuments.last()) {
			
			idsList.insert(data.indexWords.allDocuments.retrieve().id);
			data.indexWords.allDocuments.findNext();}
		idsList.insert(data.indexWords.allDocuments.retrieve().id);
		
		return idsList;}
	
	
	
	
}

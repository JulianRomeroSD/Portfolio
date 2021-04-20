package search;

import java.io.*;
import java.util.*;

/**
 * This class encapsulates an occurrence of a keyword in a document. It stores the
 * document name, and the frequency of occurrence in that document. Occurrences are
 * associated with keywords in an index hash table.
 * 
 * @author Sesh Venugopal
 * 
 */
class Occurrence {
	/**
	 * Document in which a keyword occurs.
	 */
	String document;
	
	/**
	 * The frequency (number of times) the keyword occurs in the above document.
	 */
	int frequency;
	
	/**
	 * Initializes this occurrence with the given document,frequency pair.
	 * 
	 * @param doc Document name
	 * @param freq Frequency
	 */
	public Occurrence(String doc, int freq) {
		document = doc;
		frequency = freq;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "(" + document + "," + frequency + ")";
	}
}

/**
 * This class builds an index of keywords. Each keyword maps to a set of documents in
 * which it occurs, with frequency of occurrence in each document. Once the index is built,
 * the documents can searched on for keywords.
 *
 */
public class LittleSearchEngine {
	
	/**
	 * This is a hash table of all keywords. The key is the actual keyword, and the associated value is
	 * an array list of all occurrences of the keyword in documents. The array list is maintained in descending
	 * order of occurrence frequencies.
	 */
	HashMap<String,ArrayList<Occurrence>> keywordsIndex;
	
	/**
	 * The hash table of all noise words - mapping is from word to itself.
	 */
	HashMap<String,String> noiseWords;
	
	/**
	 * Creates the keyWordsIndex and noiseWords hash tables.
	 */
	public LittleSearchEngine() {
		keywordsIndex = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
		noiseWords = new HashMap<String,String>(100,2.0f);
	}
	
	/**
	 * This method indexes all keywords found in all the input documents. When this
	 * method is done, the keywordsIndex hash table will be filled with all keywords,
	 * each of which is associated with an array list of Occurrence objects, arranged
	 * in decreasing frequencies of occurrence.
	 * 
	 * @param docsFile Name of file that has a list of all the document file names, one name per line
	 * @param noiseWordsFile Name of file that has a list of noise words, one noise word per line
	 * @throws FileNotFoundException If there is a problem locating any of the input files on disk
	 */
	public void makeIndex(String docsFile, String noiseWordsFile) 
	throws FileNotFoundException {
		// load noise words to hash table
		Scanner sc = new Scanner(new File(noiseWordsFile));
		while (sc.hasNext()) {
			String word = sc.next();
			noiseWords.put(word,word);
		}
		
		// index all keywords
		sc = new Scanner(new File(docsFile));
		while (sc.hasNext()) {
			String docFile = sc.next();
			HashMap<String,Occurrence> kws = loadKeyWords(docFile);
			mergeKeyWords(kws);
		}
		
	}

	/**
	 * Scans a document, and loads all keywords found into a hash table of keyword occurrences
	 * in the document. Uses the getKeyWord method to separate keywords from other words.
	 * 
	 * @param docFile Name of the document file to be scanned and loaded
	 * @return Hash table of keywords in the given document, each associated with an Occurrence object
	 * @throws FileNotFoundException If the document file is not found on disk
	 */
	public HashMap<String,Occurrence> loadKeyWords(String docFile) 
	throws FileNotFoundException {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		if(docFile == null) {
			throw new FileNotFoundException();
		}
		HashMap<String,Occurrence> loader = new HashMap<String,Occurrence>();
		Scanner sc = new Scanner(new File(docFile));
		while(sc.hasNext()) {
			String keyWordsFound = getKeyWord(sc.next());
			if(keyWordsFound == null) {
				continue;
			}
			else if(loader.containsKey(keyWordsFound)){
				loader.get(keyWordsFound).frequency++;
			}		
			else {
				Occurrence OccurenceFound = new Occurrence(docFile,1);
				loader.put(keyWordsFound,OccurenceFound);
			}
			
		}
		sc.close();
		//System.out.println(loader);
		return loader;
	}
	
	/**
	 * Merges the keywords for a single document into the master keywordsIndex
	 * hash table. For each keyword, its Occurrence in the current document
	 * must be inserted in the correct place (according to descending order of
	 * frequency) in the same keyword's Occurrence list in the master hash table. 
	 * This is done by calling the insertLastOccurrence method.
	 * 
	 * @param kws Keywords hash table for a document
	 */
	public void mergeKeyWords(HashMap<String,Occurrence> kws) {
		// COMPLETE THIS METHOD
		
		Set<Map.Entry<String, Occurrence>>set= kws.entrySet();
		Iterator<Map.Entry<String, Occurrence>> iterator = set.iterator();
		
		while(iterator.hasNext()) {
			
			Map.Entry<String,Occurrence> entry = iterator.next();
			Occurrence firstOccurrence = entry.getValue();
			ArrayList<Occurrence> listOfOccurrences = null;
			
			if(keywordsIndex.containsKey(entry.getKey())) {
				listOfOccurrences = keywordsIndex.get(entry.getKey());
				listOfOccurrences.add(firstOccurrence);
				insertLastOccurrence(listOfOccurrences);
			}
			else {
				ArrayList<Occurrence> noKeyList = new ArrayList<Occurrence>();
				noKeyList.add(firstOccurrence);
				keywordsIndex.put(entry.getKey(),noKeyList);
			}
			
			
		}
}
	
	
	/**
	 * Given a word, returns it as a keyword if it passes the keyword test,
	 * otherwise returns null. A keyword is any word that, after being stripped of any
	 * TRAILING punctuation, consists only of alphabetic letters, and is not
	 * a noise word. All words are treated in a case-INsensitive manner.
	 * 
	 * Punctuation characters are the following: '.', ',', '?', ':', ';' and '!'
	 * 
	 * @param word Candidate word
	 * @return Keyword (word without trailing punctuation, LOWER CASE)
	 */
	public String getKeyWord(String word) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		word = word.toLowerCase();
		String finalWord = "";
		boolean punctuation = false;
		
		for(int i= 0;i<word.length();i++) {
			
			char letter = word.charAt(i);
			boolean letterFound = Character.isLetter(letter);
			
			if(punctuation == true && letterFound == true) {
				return null;
			}
			else if(punctuation == true && letterFound == false){
				break;
			}
			else if(letterFound == true) {
				finalWord += letter;
			}
			else {
				if(word.contains(".")||word.contains(",")||word.contains("!")||word.contains("?")||word.contains(":")
						||word.contains(", ")||word.contains(" ,")||word.contains(";")) {
					punctuation = true;
				}
				else {
					return null;
				}
				
			}
		}
		
		if(noiseWords.containsValue(finalWord)) {
			return null;
		}
		
		if(finalWord.equals("")) {
			return null;
		}
		//System.out.println(finalWord);
		return finalWord;	
		}
	/**
	 * Inserts the last occurrence in the parameter list in the correct position in the
	 * same list, based on ordering occurrences on descending frequencies. The elements
	 * 0..n-2 in the list are already in the correct order. Insertion of the last element
	 * (the one at index n-1) is done by first finding the correct spot using binary search, 
	 * then inserting at that spot.
	 * 
	 * @param occs List of Occurrences
	 * @return Sequence of mid point indexes in the input list checked by the binary search process,
	 *         null if the size of the input list is 1. This returned array list is only used to test
	 *         your code - it is not used elsewhere in the program.
	 */
	public ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		   if (occs.size() == 1){
                   return null;
           }
           ArrayList<Integer> arrayListOfIndexes = new ArrayList<Integer>();
           //gets last index of occs
           int targetFrequency = occs.get(occs.size() - 1).frequency;
           int low = 0;
           //gets second to last index of occ and makes high index
           int high = occs.size() - 2;
           int currentFrequency = 0;
           int mid = 0;
           while (low <= high){
                   mid = (low + high) / 2;
                   arrayListOfIndexes.add(mid);
                   currentFrequency = occs.get(mid).frequency;
                  
                   if(targetFrequency == currentFrequency) {
                	   break;
                   }
                   else if (currentFrequency < targetFrequency)
                   {
                	   //if target is in the lower half of the array
                           high = mid - 1;
                   }
                   else{
                	   //if target is upper half of the array
                	   low = mid + 1;
                	   //if current mid equals or is greater than high you know that you 
                	   //must insert the current to the right of where current mid is 
                	   if(high==mid) {
                		   mid = mid +1;
                	   }
                   }
           }
           arrayListOfIndexes.add(mid);
           //gets last index value of 
           Occurrence temporary = occs.get(occs.size()-1);
           
           occs.remove(occs.size()-1);
           occs.add(arrayListOfIndexes.get(arrayListOfIndexes.size()-1), temporary);
           arrayListOfIndexes.remove(arrayListOfIndexes.size()-1);
           
           return arrayListOfIndexes;
   
		}
	
	/**
	 * Search result for "kw1 or kw2". A document is in the result set if kw1 or kw2 occurs in that
	 * document. Result set is arranged in descending order of occurrence frequencies. (Note that a
	 * matching document will only appear once in the result.) Ties in frequency values are broken
	 * in favor of the first keyword. (That is, if kw1 is in doc1 with frequency f1, and kw2 is in doc2
	 * also with the same frequency f1, then doc1 will appear before doc2 in the result. 
	 * The result set is limited to 5 entries. If there are no matching documents, the result is null.
	 * 
	 * @param kw1 First keyword
	 * @param kw1 Second keyword
	 * @return List of NAMES of documents in which either kw1 or kw2 occurs, arranged in descending order of
	 *         frequencies. The result size is limited to 5 documents. If there are no matching documents,
	 *         the result is null.
	 */
	public ArrayList<String> top5search(String kw1, String kw2) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		ArrayList<String> finalArray = new ArrayList<String>();
		ArrayList<Occurrence> occurrence1 = keywordsIndex.get(kw1);
		ArrayList<Occurrence> occurrence2 = keywordsIndex.get(kw2);
		int i = 0;
		int j = 0;
		if(!keywordsIndex.containsKey(kw1) && !keywordsIndex.containsKey(kw2)) {
			System.out.println("entered both null");			
		return null;
		}
		
		else if(!keywordsIndex.containsKey(kw1) && keywordsIndex.containsKey(kw2)) {
			System.out.println("entered kw1 null");		
		while(i <=occurrence2.size()-1) {
			if(finalArray.size()==5) {
				break;
			}
			finalArray.add(occurrence2.get(i).document);
			i++;
		}
	}
		
		else if(!keywordsIndex.containsKey(kw2) && keywordsIndex.containsKey(kw1)) {
			
			System.out.println("entered kw2 null");		
			while(i<=occurrence1.size()-1) {
				if(finalArray.size()==5) {
					break;
				}
			finalArray.add(occurrence1.get(i).document);
			i++;
			}
		}
		else {
			System.out.println("entered else");
		
		while((((i<=occurrence1.size()-1 && j <= occurrence2.size()-1)))) {
			if(finalArray.size()==5) {
				break;
			}
			if(i > occurrence1.size()-1 ) {
				System.out.println("occurrence 1 list done");
				if(!finalArray.contains(occurrence2.get(j).document)) {
					finalArray.add(occurrence2.get(j).document);
					}
				j++;
			}
			else if(j > occurrence2.size()-1){
				System.out.println("occurrence 2 list done");
				if(!finalArray.contains(occurrence1.get(i).document)) {
					finalArray.add(occurrence1.get(i).document);
					}
				i++;
			}
			
			if(occurrence1.get(i).frequency >= occurrence2.get(j).frequency){
				System.out.println("entered frequency conditional first");
					if(!finalArray.contains(occurrence1.get(i).document)) {
						finalArray.add(occurrence1.get(i).document);			
						}
					i++;
				}
			else {
				System.out.println("entered frequency conditional second");
				if(!finalArray.contains(occurrence2.get(j).document)) {
					finalArray.add(occurrence2.get(j).document);
					}
				j++;
				}
			}
	
	}
		
	return finalArray;
	}
}


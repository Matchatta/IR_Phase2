//Name: 
//Section: 
//ID: 

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class JaccardSearcher extends Searcher{
	public JaccardSearcher(String docFilename) {
		super(docFilename);
		/************* YOUR CODE HERE ******************/
		
		/***********************************************/
	}

	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
		//set of query tokens
		HashSet<String> queryToken = new HashSet<>(tokenize(queryString));
		TreeSet<SearchResult> results = new TreeSet<>(SearchResult::compareTo);
		//Compute Jaccard score and get results
		for(Document document : documents) {
			HashSet<String> intersection = new HashSet<>(document.getTokens());
			HashSet<String> union = new HashSet<>(document.getTokens());
			//Get token that appear in query and document
			intersection.retainAll(queryToken);
			//Get token that appear in query or document
			union.addAll(queryToken);
			//Compute Jaccard score
			double score = (double) intersection.size()/(double)union.size();
			//Generate result
			results.add(new SearchResult(document, score));
		}
		//Create list of results
		List<SearchResult> resultList = new ArrayList<>(results);
		//Return top k results
		return resultList.subList(0, k);
		/***********************************************/
	}

}

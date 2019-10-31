//Name: 
//Section: 
//ID: 

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FileUtils;

public class SearcherEvaluator {
	private List<Document> queries = null;				//List of test queries. Each query can be treated as a Document object.
	private  Map<Integer, Set<Integer>> answers = null;	//Mapping between query ID and a set of relevant document IDs
	
	public List<Document> getQueries() {
		return queries;
	}

	public Map<Integer, Set<Integer>> getAnswers() {
		return answers;
	}

	/**
	 * Load queries into "queries"
	 * Load corresponding documents into "answers"
	 * Other initialization, depending on your design.
	 * @param corpus
	 */
	public SearcherEvaluator(String corpus)
	{
		String queryFilename = corpus+"/queries.txt";
		String answerFilename = corpus+"/relevance.txt";
		
		//load queries. Treat each query as a document. 
		this.queries = Searcher.parseDocumentFromFile(queryFilename);
		this.answers = new HashMap<Integer, Set<Integer>>();
		//load answers
		try {
			List<String> lines = FileUtils.readLines(new File(answerFilename), "UTF-8");
			for(String line: lines)
			{
				line = line.trim();
				if(line.isEmpty()) continue;
				String[] parts = line.split("\\t");
				Integer qid = Integer.parseInt(parts[0]);
				String[] docIDs = parts[1].trim().split("\\s+");
				Set<Integer> relDocIDs = new HashSet<Integer>();
				for(String docID: docIDs)
				{
					relDocIDs.add(Integer.parseInt(docID));
				}
				this.answers.put(qid, relDocIDs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Returns an array of 3 numbers: precision, recall, F1, computed from the top *k* search results 
	 * returned from *searcher* for *query*
	 * @param query
	 * @param searcher
	 * @param k
	 * @return
	 */
	public double[] getQueryPRF(Document query, Searcher searcher, int k)
	{
		/*********************** YOUR CODE HERE *************************/
		//Put all relevance document to G
		List<Integer> G = new ArrayList<>(answers.get(query.getId()));
		List<Integer> R = new ArrayList<>();
		//Get all search results
		List<SearchResult> allResults = new ArrayList<>(searcher.search(query.getRawText(), k));
		//Put search results' id to R
		for(SearchResult searchResult : allResults){
			R.add(searchResult.getDocument().getId());
		}
		//Get intersection set by intersect R and G
		List<Integer> intersection = new ArrayList<>(R);
		intersection.retainAll(G);
		//Compute precision, recall, and F1
		double intersectionSize = intersection.size();
		double precision = intersectionSize/ (double) R.size();
		double recall = intersectionSize/ (double) G.size();
		double F1;
		if(precision+recall==0){
			F1=0;
		}
		else {
			F1 = (2.0*precision*recall)/(precision+recall);
		}
		double[] PRF = {precision, recall, F1};
		return PRF;
		/****************************************************************/
	}
	
	/**
	 * Test all the queries in *queries*, from the top *k* search results returned by *searcher*
	 * and take the average of the precision, recall, and F1. 
	 * @param searcher
	 * @param k
	 * @return
	 */
	public double[] getAveragePRF(Searcher searcher, int k)
	{
		/*********************** YOUR CODE HERE *************************/
		double[] PRF ={0, 0, 0};
		//Compute PRF average
		for (Document document : queries){
			double[] prf;
			prf = getQueryPRF(document, searcher, k);
			PRF[0] += prf[0];
			PRF[1] += prf[1];
			PRF[2] += prf[2];
		}
		PRF[0] = PRF[0]/(double) queries.size();
		PRF[1] = PRF[1]/(double)queries.size();
		PRF[2] = PRF[2]/(double)queries.size();
		return PRF;
		/****************************************************************/
	}
}

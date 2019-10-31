
//Name:
//Section:
//ID:

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class TFIDFSearcher extends Searcher
{
	HashMap<Integer, HashMap<String, Double>> tfidf = new HashMap<>();
	HashMap<Integer, Double> docDistanceVector = new HashMap<>();
	HashMap<String, Double> idf = new HashMap<>();
	public TFIDFSearcher(String docFilename) {
		super(docFilename);
		//This use to generate Distance vector, idf, Document tf-idf
		/************* YOUR CODE HERE ******************/
		for(Document document:documents){
			HashMap<String, Double> rtf = new HashMap<>();
			//Get raw term frequency of each document
			for(String term : document.getTokens()){
				if(rtf.containsKey(term)){
					rtf.replace(term, rtf.get(term)+1.0);
				}
				else{
					rtf.put(term, 1.0);
					//Get document frequency of each term
					if(!idf.containsKey(term)){
						idf.put(term, 1.0);
					}
					else{
						idf.replace(term, idf.get(term)+1.0);
					}
				}
			}
			//Compute the tf value of each term in a document
			for (String term : rtf.keySet()){
				rtf.replace(term, 1.0+ Math.log10(rtf.get(term)));
			}
			//Sto tf values
			tfidf.put(document.getId(), rtf);
		}
		//Compute idf value of each term
		double N = documents.size();
		for(String term : idf.keySet()){
			idf.replace(term, Math.log10(1.0+(N/idf.get(term))));
		}
		for(Document document : documents){
		    //Compute tf-idf of each term in a document and Distance vector of each document
			HashMap<String, Double> weight = new HashMap<>();
			double distance =0;
			for(String term : tfidf.get(document.getId()).keySet()){
				double value = tfidf.get(document.getId()).get(term)*idf.get(term);
				weight.put(term, value);
				distance += Math.pow(value, 2);
			}
			docDistanceVector.put(document.getId(), Math.sqrt(distance));
			tfidf.replace(document.getId(), weight);
		}
		/***********************************************/
	}

	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
		//Get query's tokens
		List<String> queryToken = new ArrayList<>(tokenize(queryString));
		List<SearchResult> results = new ArrayList<>();
		HashMap<String, Double> queryTf = new HashMap<>();
		//Get term frequency of each token
		for(String term : queryToken){
			if(queryTf.containsKey(term)){
				queryTf.replace(term, queryTf.get(term)+1);
			}
			else{
				queryTf.put(term, 1.0);
			}
		}
		//Compute query tf-idf value and Distance vector of query
		HashMap<String, Double> Q_tfidf = new HashMap<>();
		double queryDistanceVector =0.0;
		for (String term : queryTf.keySet()){
			if(idf.containsKey(term)) {
			    //Compute query tf-idf value
				Q_tfidf.put(term, (1.0 + Math.log10(queryTf.get(term))) * idf.get(term));
				//Compute Distance vector of query
				queryDistanceVector += Math.pow((1.0 + Math.log10(queryTf.get(term))) * idf.get(term), 2);
			}
			else{
				Q_tfidf.put(term, 0.0);
			}
		}
        //Distance vector of query
		queryDistanceVector = Math.sqrt(queryDistanceVector);
		//This use to compute cosine similarity between tokens in document and tokens in query
		for(Document document : documents){
			TreeSet<String> token = new TreeSet<>(document.getTokens());
			//Get token that appear in document and query
			token.retainAll(queryToken);
			double cosineSimilarity;
			double value = 0.0;
			//Compute cosine similarity
			for(String term : token){
				double tempDTFIDF;
				double tempQTFIDF;
				tempDTFIDF = tfidf.get(document.getId()).get(term);
				tempQTFIDF = Q_tfidf.get(term);
				value += tempDTFIDF*tempQTFIDF;
			}
            cosineSimilarity = value/(docDistanceVector.get(document.getId())*queryDistanceVector);
			//Get search result
			SearchResult searchResult = new SearchResult(document, cosineSimilarity);
			results.add(searchResult);
		}
		//Sort search result by its score
		results.sort(SearchResult::compareTo);
		//Return top k results
		return results.subList(0, k);
		/***********************************************/
	}
}

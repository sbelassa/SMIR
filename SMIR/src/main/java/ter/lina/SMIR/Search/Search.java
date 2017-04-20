package ter.lina.SMIR.Search;

import java.util.Map;

import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;

/**
 * 
 * @author salma
 *
 */
public class Search implements ISearch {

	/**
	 * search for a string query in a document
	 * @param node
	 * @param index
	 * @param type
	 * @param query
	 */
	@Override
	public void searchStringInFile(Node node, String index, String type,String query){
		System.out.println("--> searching for : '"+query+"' in the document:");
		System.out.println("\n");

		SearchRequestBuilder srb1 = node.client()
        	    .prepareSearch().setQuery(QueryBuilders.queryString(query)).setSize(1);
        	
        	MultiSearchResponse sr = node.client().prepareMultiSearch()
        	        .add(srb1)
        	        .execute().actionGet();
        	System.out.println(sr.toString());
        	System.out.println();
        	long nbHits = 0;
        	for (MultiSearchResponse.Item item : sr.getResponses()) {
        	    SearchResponse response = item.getResponse();
        	    nbHits += response.getHits().getTotalHits();
        		System.out.println("\n");
        	    System.out.println("Results found: "+nbHits);
        	}
	}
	
	/**
	 * search for a specific value in a specific field in a document
	 * @param client
	 * @param index
	 * @param type
	 * @param field
	 * @param value
	 */
	@Override
	public void searchValueInField(Client client, String index, String type,
            String field, String value){
		System.out.println("--> searching in : '"+field+"' for: '"+value+"' in the document: ");
		System.out.println("\n");

			SearchResponse response = client.prepareSearch(index)
			              .setTypes(type)
			              .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
			              //.setQuery(fieldQuery(field, value))
			              .setFrom(0).setSize(60).setExplain(true)
			              .execute()
			              .actionGet();
			SearchHit[] results = response.getHits().getHits();
			System.out.println("Current results: " + results.length);
			for (SearchHit hit : results) {
			System.out.println("------------------------------");
			Map<String,Object> result = hit.getSource();   
			System.out.println(result);
}
}

	@Override
	public void Search() {
		// TODO Auto-generated method stub
		
	}

}

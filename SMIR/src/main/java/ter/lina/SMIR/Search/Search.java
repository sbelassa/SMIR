package ter.lina.SMIR.Search;

import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;


public class Search {

	public void searchDocument(Client client, String index, String type,
            String field, String value){
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

}

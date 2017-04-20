package ter.lina.SMIR.Search;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;

public interface ISearch {

	/**
	 * search for a string query in a document
	 * @param node
	 * @param index
	 * @param type
	 * @param query
	 */
	void searchStringInFile(Node node, String index, String type, String query);

	/**
	 * search for a specific value in a specific field in a document
	 * @param client
	 * @param index
	 * @param type
	 * @param field
	 * @param value
	 */
	void searchValueInField(Client client, String index, String type, String field, String value);

	/**
	 * 
	 */
	void Search();


}
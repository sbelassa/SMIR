package ter.lina.SMIR.Indexation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;

import com.google.gson.JsonObject;

/**
 * 
 * @author salma
 *
 */
public interface IIndexDocument {

	/**
	 * Getting the content of a file in a String 
	 * @param fich
	 * @return
	 * @throws IOException
	 */
	String putInFile(FileInputStream fich) throws IOException;

	/**
	 * Mapping a Json file using Gson
	 * @param filename
	 * @return
	 */
	Map jsonToMap(String filename);

	/**
	 * Parsing a string that has json file content, into a JSonObject
	 * @param filename
	 * @return
	 */
	JsonObject jsonFileToJsonObject(String filename);

	/**
	 * indexing a given file in elasticsearch
	 * @param inputFile
	 * @param index
	 * @param id
	 * @param type
	 * @throws IOException
	 */
	void indexDocument(String inputFile, String index, String id, String type, Node node, Client client)
			throws IOException;

}
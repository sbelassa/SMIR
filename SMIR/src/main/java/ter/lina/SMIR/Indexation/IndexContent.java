package ter.lina.SMIR.Indexation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IndexContent implements IIndexDocument {
	
	/**
	 * Getting the content of a file in a String 
	 * @param fich
	 * @return
	 * @throws IOException
	 */
	@Override
	public  String putInFile(FileInputStream fich) throws IOException{
		
		BufferedReader buf = new BufferedReader(new InputStreamReader(fich));
		String line = buf.readLine();
		StringBuilder sb = new StringBuilder();
		
		while(line != null){ 
			sb.append(line).append("\n");
			line = buf.readLine(); 
			} 
		
		String fileAsString = sb.toString();
		System.out.println("Contents : " + fileAsString);
		
			return fileAsString;

	}
	
	/**
	 * Mapping a Json file using Gson
	 * @param filename
	 * @return
	 */
	@Override
	public Map jsonToMap(String filename){

        Gson gson = new GsonBuilder().create();/// building a gson object
        Map jsonMap = gson.fromJson(filename, Map.class);/// getting a map for the json file content
        // System.out.println(jsonMap.get("File_content")); /// Testing the map by demanding a field from the Json map created from the json file : content-length

        return jsonMap;
	}
	
	
	/**
	 * Parsing a string that has json file content, into a JSonObject
	 * @param filename
	 * @return
	 */
	@Override
	public JsonObject jsonFileToJsonObject(String filename){

        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(filename).getAsJsonObject();
        //String imgurl = obj.get("File_content").getAsString(); ///getting the File_content field from the json object
        //System.out.println(imgurl);
        return obj;
		
	}
	
	public String constructJsonDocument(String inputfiles,FileInputStream filename) throws IOException{
		
		String myFile = putInFile(filename);
		JsonObject obj= jsonFileToJsonObject(filename.toString());
		
		obj.addProperty("File_content", myFile);
		
		return null;
	}

	/**
	 * indexing a given file in elasticsearch
	 * @param inputFile
	 * @param index
	 * @param id
	 * @param type
	 * @throws IOException
	 */
	@Override
	public void indexDocument(String inputFile,String index,String id, String type,Node node,Client client) throws IOException{
		
		FileInputStream fich = new FileInputStream(inputFile);
		/// Json file into a string
		String json=putInFile(fich);

        /// creating the index
		IndexResponse response = client.prepareIndex(index,type,id)
								       .setSource(json)
								       .execute().actionGet();

        if (response != null) {
            
        	System.out.println("Index has been created !");
            JavaAPIMain.getDocument(client,index,type,id); ///getting back the client, index, type, id
          } else {
            System.err.println("Index creation failed !");
          }

	}



}

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

/**
 * 
 * @author salma
 *
 */
public class IndexDocument implements IIndexDocument {
	
	/* (non-Javadoc)
	 * @see ter.lina.SMIR.Indexation.IIndexDocument#putInFile(java.io.FileInputStream)
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
		//System.out.println("Contents : " + fileAsString);
		
			return fileAsString;

	}
	
	/* (non-Javadoc)
	 * @see ter.lina.SMIR.Indexation.IIndexDocument#jsonToMap(java.lang.String)
	 */
	@Override
	public Map jsonToMap(String filename){

        Gson gson = new GsonBuilder().create();/// building a gson object
        Map jsonMap = gson.fromJson(filename, Map.class);/// getting a map for the json file content
        // System.out.println(jsonMap.get("File_content")); /// Testing the map by demanding a field from the Json map created from the json file : content-length

        return jsonMap;
	}
	
	
	/* (non-Javadoc)
	 * @see ter.lina.SMIR.Indexation.IIndexDocument#jsonFileToJsonObject(java.lang.String)
	 */
	@Override
	public JsonObject jsonFileToJsonObject(String filename){

        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(filename).getAsJsonObject();
        //String imgurl = obj.get("File_content").getAsString(); ///getting the File_content field from the json object
        //System.out.println(imgurl);
        return obj;
		
	}

	/* (non-Javadoc)
	 * @see ter.lina.SMIR.Indexation.IIndexDocument#indexDocument(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void indexDocument(String inputFile,String index,String id, String type) throws IOException{
		
		FileInputStream fich = new FileInputStream(inputFile);
		/// Json file into a string
		String json=putInFile(fich);
		
		/// initializing elasticsearch Client and node
        Node node = NodeBuilder.nodeBuilder().node();
        Client client = node.client();

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

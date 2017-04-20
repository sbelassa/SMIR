package ter.lina.SMIR.Indexation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IndexContent {
	private final static String DIRECTORY = "ExtractedFiles"
			+ "/COCo_Cours-L1_CM-TD-TP_introduction-a-l-algorithmique-wo-video"
			+ "/section-01_Généralités"
			+ "/les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe/";

	private final static String INPUT_FILE_NAME = DIRECTORY + "les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe.html";		

	
	/**
	 * getting the content of a file in a String 
	 * @param fich
	 * @return
	 * @throws IOException
	 */
	public static  String putInFile(FileInputStream fich) throws IOException{
		
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
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		
		FileInputStream fich = new FileInputStream("ExtractedFiles/M1IHM/cours/5 hci design/meta.json");
		String json=putInFile(fich);
		
        Node node = NodeBuilder.nodeBuilder().node();
        Client client = node.client();
		
		
        /**
         * Deserializing a json file's content into 'gson' object
         */
        Gson gson = new GsonBuilder().create();/// building a gson object
        Map jsonMap = gson.fromJson(json, Map.class);/// Getting a map for the Json file content
        // System.out.println(jsonMap.get("File_content")); /// Testing the map by demanding a field from the Json map created from the json file : content-length
        
        //System.out.println(jsonMap.toString());
        /**
         * Parsing a string that has Json file content, into a JSonObject
         */
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(json).getAsJsonObject();
        String imgurl = obj.get("File_content").getAsString(); /// Getting the File_content field from the Json object
        System.out.println(imgurl);
        
        
        /**
         * testing the indexing 
         */
		IndexResponse response = client.prepareIndex("docs", "document")
								       .setSource(json)
								       .execute().actionGet();

        if (response != null) {
            
        	System.out.println("Index has been created !");
            JavaAPIMain.getDocument(client, "docs", "document", "1"); ///getting back the client, index, type, id
          } else {
            System.err.println("Index creation failed.");
          }

        
        
        /**
         * Search
         */
        
        	SearchRequestBuilder srb1 = node.client()
        	    .prepareSearch().setQuery(QueryBuilders.queryString("2013")).setSize(1);
        	SearchRequestBuilder srb2 = node.client()
        	    .prepareSearch().setQuery(QueryBuilders.matchQuery("Author", "Éric Languénou")).setSize(1);

        	MultiSearchResponse sr = node.client().prepareMultiSearch()
        	        .add(srb1)
        	        //.add(srb2)
        	        .execute().actionGet();
        	System.out.println(sr.toString());


		
	}


}

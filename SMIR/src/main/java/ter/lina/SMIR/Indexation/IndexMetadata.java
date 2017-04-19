package ter.lina.SMIR.Indexation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import static org.elasticsearch.index.query.QueryBuilders.*;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * @author salma
 *
 */
public class IndexMetadata {
	
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
        /*Node node = nodeBuilder().node();
        Client client = node.client();*/	
		/*
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		*/
		
		
		Settings settings = Settings.builder()
		        .put("cluster.name", "myClusterName").build();
		TransportClient client = new PreBuiltTransportClient(settings);
		
		
        /**
         * deserializing a json file's content into 'gson' object
         */
        Gson gson = new GsonBuilder().create();/// building a gson object
        Map jsonMap = gson.fromJson(json, Map.class);/// getting a map for the json file content
       // System.out.println(jsonMap.get("content")); /// testing the map by demanding a field from the json map created from the json file : content-length
        
        //System.out.println(jsonMap.toString());
        /**
         * parsing a string that has json file content, into a JSonObject
         */
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(json).getAsJsonObject();
        String imgurl = obj.get("File_content").getAsString(); ///getting the File_content field from the json object 
        
        
        /**
         * testing the indexing 
         */
		IndexResponse response = client.prepareIndex("myfile", "tweet")
								       .setSource(json)
								       .execute().actionGet();

        if (response != null) {
            
        	System.out.println("Index has been created !");
            JavaAPIMain.getDocument(client, "myfile", "tweet", "1"); ///getting back the client, index, type, id
          } else {
            System.err.println("Index creation failed.");
          }

        
        
        /**
         * Search
         */
        ///first attempt
       /*
        SearchResponse responsee = client.prepareSearch("myfile")
                .setQuery(QueryBuilders.matchQuery("content", "</div>"))
                .execute()
                .actionGet();

        System.out.println("searchKeyWord : " + response);
        */
        
        
        ///second attempt
        /*
        SearchResponse responsee = client.prepareSearch("myfile")
                .setTypes("tweet")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("content", "</div>")) // Query
                .setFrom(0).setSize(60).setExplain(true)
                .get();
        
        System.out.println(responsee);
        */
        
        /// third attempt
        /*SearchResponse responsex = client.prepareSearch().get();
        System.out.println(responsex);
        */
        
        
        /// 4th attempt
      
		
	}


}

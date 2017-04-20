package ter.lina.SMIR.Indexation;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;

 public class JavaAPIMain {
        
	 /**
	  * 
	  * @param title
	  * @param content
	  * @param postDate
	  * @param tags
	  * @param author
	  * @return
	  */
    public static Map<String, Object> putJsonDocument(String title, String content, Date postDate, 
                                                      String[] tags, String author){
        
        Map<String, Object> jsonDocument = new HashMap<String, Object>();
        
        jsonDocument.put("title", title);
        jsonDocument.put("content", content);
        jsonDocument.put("postDate", postDate);
        jsonDocument.put("tags", tags);
        jsonDocument.put("author", author);
        
        return jsonDocument;
    }
    
    /**
     * 
     * @param client
     * @param index
     * @param type
     * @param id
     */
    public static  void getDocument(Client client, String index, String type, String id){
        
        GetResponse getResponse = client.prepareGet(index, type, id)
                                        .execute()
                                        .actionGet();
        Map<String, Object> source = getResponse.getSource();
        
        System.out.println("------------------------------");
        System.out.println("Index: " + getResponse.getIndex());
        System.out.println("Type: " + getResponse.getType());
        System.out.println("Id: " + getResponse.getId());
        System.out.println("Version: " + getResponse.getVersion());
        System.out.println("Source: " + source);
        System.out.println("------------------------------");
        
    }
    
}

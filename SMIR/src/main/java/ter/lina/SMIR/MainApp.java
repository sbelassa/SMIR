package ter.lina.SMIR;

import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.xml.sax.SAXException;

import ter.lina.SMIR.Extraction.ExtractToHTML;
import ter.lina.SMIR.Extraction.IExtractToHTML;
import ter.lina.SMIR.Indexation.IIndexDocument;
import ter.lina.SMIR.Indexation.IndexDocument;
import ter.lina.SMIR.Search.ISearch;
import ter.lina.SMIR.Search.Search;
import ter.lina.SMIR.Segmentation.FileSegmentation;
import ter.lina.SMIR.Segmentation.IFileSegmentation;

/**
 * 
 * The main class to test the smart Multimodal information retrieval project
 * Respecting the architecture set for the project
 * Extracting documents' content and Metadata
 * Segmenting the content into pages of each document
 * Indexing documents
 * Searching into the documents' content and metadata
 * Evaluating the results 
 * 
 * 
 * @author salma
 *
 */
public class MainApp 
{

	/**
	 * Final static fields
	 */
	private final static String MAIN_DIRECTORY = "ExtractedFiles"
			+ "/M1IHM/cours";
	
	private final static String FILE_DIRECTORY = "/5 hci design";
	
	private final static String INPUT_FILE_NAME = MAIN_DIRECTORY + FILE_DIRECTORY + "/5 hci design.pdf";	
	
	private final static String MY_FILE_PATH = "ExtractedFiles/M1IHM/cours/5 hci design/5 hci design.pdf";
	
	private final static String OUTPUT_SEGMENT_FILENAME = "ExtractedFiles/M1IHM/cours/5 hci design/SegmentedFiles/5 hci design";

	/******/
	private static  IExtractToHTML extract; /// extracting content of documents
	private static IFileSegmentation segment;/// segmenting file contents
	private static IIndexDocument index; /// indexing a document
	private static ISearch search; /// searching through a file

	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
    public static void main( String[] args ) throws IOException, SAXException, TikaException
    {
    	String outputFile = MAIN_DIRECTORY + FILE_DIRECTORY + "/5 hci design.html";
    	
    	/// initializing all models
    	extract = new ExtractToHTML();
    	segment = new FileSegmentation();
		index = new IndexDocument();
		search = new Search();
		
		
    	/**
    	 * Extraction of a file's content
    	 * Input: any file type
    	 * Output: HTML file (including Metadata)
    	 */
		System.out.println("****************  Extracting file's content and metadata...");
    	extract.extractFileToHTML(MY_FILE_PATH,outputFile);
		System.out.println("\n");
	
    	/**
    	 * Segmentation the file's content into pages
    	 * input: the html output file from the extraction model
    	 * output: the html files matching each page found on the input file, also the metadata file 
    	 */
		System.out.println("****************  Segmenting the file...");
    	segment.segmentFileToPages(outputFile, MAIN_DIRECTORY + FILE_DIRECTORY + "/METADATA_5 hci design.html", OUTPUT_SEGMENT_FILENAME);
		System.out.println("\n");

		
    	/**
    	 * adding content to the json file
    	 */
    	
    	/// TODO
    	
    	/**
    	 * Indexation
    	 */
		/// initializing elasticsearch client and node
		Node node = NodeBuilder.nodeBuilder().node();
        Client client = node.client();
		
		System.out.println("****************  Indexing the file...");
    	index.indexDocument("ExtractedFiles/M1IHM/cours/5 hci design/meta.json", "docs", "1", "document",node,client);
		System.out.println("\n");
    	
    	/**
    	 * Search
    	 */
		System.out.println("****************  Searching through the file...");
		//search.searchValueInField(client, "docs", "document", "File_content", "HCI");
		search.searchStringInFile(node, "docs", "document", "human");
		
		/**
		 * Evaluation 
		 */
		System.out.println("****************  Evaluating results...");

		/// TODO
		
		/**
		 * closing ES client
		 */
    	client.close();

    
    }
}

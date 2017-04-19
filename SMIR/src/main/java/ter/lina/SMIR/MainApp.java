package ter.lina.SMIR;

import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import ter.lina.SMIR.Extraction.ExtractToHTML;
import ter.lina.SMIR.Segmentation.FileSegmentation;

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
	private static  ExtractToHTML extract; /// extracting content of documents
	private static FileSegmentation segment;/// segmenting file contents

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
    	extract = new ExtractToHTML();
    	segment = new FileSegmentation();
		
    	/**
    	 * Extraction of a file's content
    	 * input: any file type
    	 * output: html file (including metadata)
    	 */
    	extract.extractFileToHTML(MY_FILE_PATH,outputFile);
	
    	/**
    	 * Segmentation the file's content into pages
    	 * input: the html output file from the extraction model
    	 * output: the html files matching each page found on the input file, also the metadata file 
    	 */
    	segment.segmentFileToPages(outputFile, MAIN_DIRECTORY + FILE_DIRECTORY + "/METADATA_5 hci design.html", OUTPUT_SEGMENT_FILENAME);
    	
    	/**
    	 * Indexation
    	 */
    	
    	///TODO 
    	
    	
    	/**
    	 * Search
    	 */
    	
    	///TODO 

    
    }
}

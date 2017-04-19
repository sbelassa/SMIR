package ter.lina.SMIR.Extraction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * 
 * @author salma
 *
 */
public class ExtractToHTML implements IExtractToHTML {

	///private final static String MY_FILE = "ExtractedFiles/M1IHM/cours/5 hci design/5 hci design.pdf";
		
	/* (non-Javadoc)
	 * @see ter.lina.SMIR.Extraction.IExtractToHTML#extractFileToHTML(java.lang.String, java.lang.String)
	 */
	@Override
	public void extractFileToHTML(String inputFile,String outputFile) throws  SAXException, TikaException, IOException{
	   
		    PrintWriter outputF = new PrintWriter(outputFile);
			ContentHandler handler = new ToXMLContentHandler();
		    AutoDetectParser parser = new AutoDetectParser();
		    Metadata metadata = new Metadata();
	        InputStream stream = new FileInputStream(new File(inputFile));
		    parser.parse(stream, handler, metadata);
		    
	 	    //writing results in an output file
		      try (BufferedWriter bw = new BufferedWriter(outputF)) {

		    	  bw.write(handler.toString());
		    	  System.out.println("Extraction done! ");
				} catch (IOException e) {

					e.printStackTrace();

				}
	}

	
	/**
	 * 
	 */
	public ExtractToHTML() {

	}
	
	/*
	public static void main(String[] args) throws IOException, SAXException, TikaException {
		
		extractFileToHTML(MY_FILE, "ExtractedFiles/M1IHM/cours/5 hci design/file.html");

	    	    
	}
*/
}

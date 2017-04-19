package ter.lina.SMIR.Extraction.Options;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.SAXException;
/**
 * 
 * class to get the metadata from a file
 *
 */
public class GetMetadata {
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws TikaException
	 * @throws SAXException
	 */
   public static void main(final String[] args) throws IOException, TikaException, SAXException {
	
      //Assume that boy.jpg is in your current directory
	      File file = new File("ExtractionFiles/COCo_Cours-L1_CM-TD-TP_introduction-a-l-algorithmique-wo-video/section-01_Généralités/Panique algorithmique _ Grise Bouille.html");
	      File metafile = new File("ExtractionFiles/COCo_Cours-L1_CM-TD-TP_introduction-a-l-algorithmique-wo-video/section-01_Généralités/METADATA_Panique algorithmique _ Grise Bouille.txt");

      //Parser method parameters
      Parser parser = new AutoDetectParser();
      BodyContentHandler handler = new BodyContentHandler();
      Metadata metadata = new Metadata();
      FileInputStream inputstream = new FileInputStream(file);
      ParseContext context = new ParseContext();
      
      parser.parse(inputstream, handler, metadata, context);
      System.out.println(handler.toString());

      
      
    //impression des metadata
      
    		FileOutputStream fos = new FileOutputStream(metafile);
    		PrintStream ps = new PrintStream(fos);
    		System.setOut(ps);
    	      //getting metadata of the document
    	      System.out.println("Metadata of the PDF:");
    	      String[] metadataNames = metadata.names();
    	                  
    	      for(String name : metadataNames) {
    	         System.out.println(name+ " : " + metadata.get(name));
    	   		
    	      }
   }
}
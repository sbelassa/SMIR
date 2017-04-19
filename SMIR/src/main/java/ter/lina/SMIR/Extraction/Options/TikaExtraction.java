package ter.lina.SMIR.Extraction.Options;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * 
 * extraction with tika, whatever the file extension is
 *
 */
public class TikaExtraction {
	/**
	 * the default directory for the files to extract
	 */
	private final static String DIRECTORY = "ExtractedFiles"
			+ "/COCo_Cours-L1_CM-TD-TP_introduction-a-l-algorithmique-wo-video"
			+ "/section-01_Généralités"
			+ "/les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe/";

	private final static String INPUT_FILE_NAME = DIRECTORY + "les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe.html";		

	
   public static void main(final String[] args) throws IOException, TikaException, SAXException {

      File file = new File(INPUT_FILE_NAME);
      PrintWriter file1= new PrintWriter(DIRECTORY+"draft/file.txt");
      
      //Instantiating Tika facade class
      Tika tika = new Tika();
      String filecontent = tika.parseToString(file);
      System.out.println("Extracted Content: " + filecontent);      ////////////////////////////////
      
      
      try (BufferedWriter bw = new BufferedWriter(file1)) {

    	  bw.write(filecontent);
    	  System.out.println("done");
		} catch (IOException e) {

			e.printStackTrace();

		}

      
   }		 
}
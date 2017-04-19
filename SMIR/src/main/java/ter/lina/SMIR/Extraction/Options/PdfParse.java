package ter.lina.SMIR.Extraction.Options;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.SAXException;

/**
 * 
 * parse pdf file
 * extracting the content of a pdf file into txt format
 *
 */
public class PdfParse {

	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws TikaException
	 * @throws SAXException
	 */
   public static void main(final String[] args) throws IOException,TikaException, SAXException {
	   
	  PrintWriter file= new PrintWriter("ExtractedFiles/COCo_Cours-L1_CM-TD-TP_introduction-a-l-algorithmique-wo-video/section-01_Généralités/les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe/draft/les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe.html");
	  File metafile= new File("ExtractedFiles/COCo_Cours-L1_CM-TD-TP_introduction-a-l-algorithmique-wo-video/section-01_Généralités/les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe/draft/metadata_les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe.html");

      BodyContentHandler handler = new BodyContentHandler();
      Metadata metadata = new Metadata();
      FileInputStream inputstream = new FileInputStream(new File("ExtractedFiles/COCo_Cours-L1_CM-TD-TP_introduction-a-l-algorithmique-wo-video/section-01_Généralités/les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe/les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe.pdf"));
      File filex = new File("ExtractedFiles/COCo_Cours-L1_CM-TD-TP_introduction-a-l-algorithmique-wo-video/section-01_Généralités/les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe/les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe.pdf");
      ParseContext pcontext = new ParseContext();
      
      //parsing the document using PDF parser
      PDFParser pdfparser = new PDFParser(); 
      pdfparser.parse(inputstream, handler, metadata,pcontext);
      
      //getting the content of the document
      System.out.println("Contents of the PDF :" + handler.toString());
      Tika tika = new Tika();
      String filecontent = tika.parseToString(filex);
      
      try (BufferedWriter bw = new BufferedWriter(file)) {
    	  bw.write(filecontent);
		} catch (IOException e) {

			e.printStackTrace();

		}
      
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
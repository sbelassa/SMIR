package ter.lina.SMIR.Extraction.Options;

import java.io.File;
import org.apache.tika.Tika;

/**
 * 
 * detect the type of the a file
 *
 */
public class Typedetection {

   public static void main(String[] args) throws Exception {

      //assume example.mp3 is in your current directory
	      File file = new File("ExtractionFiles/COCo_Cours-L1_CM-TD-TP_introduction-a-l-algorithmique-wo-video/section-01_Généralités/Panique algorithmique _ Grise Bouille.html");//
	      //File file = new File("");//
      
      //Instantiating tika facade class 
      Tika tika = new Tika();
      
      //detecting the file type using detect method
      String filetype = tika.detect(file);
      System.out.println(filetype);
   }
}
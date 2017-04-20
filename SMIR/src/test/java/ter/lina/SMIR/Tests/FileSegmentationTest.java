package ter.lina.SMIR.Tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 
 * @author salma, samia
 *
 * segment a given file into pages
 */
public class FileSegmentationTest {


private final static String DIRECTORY = "ExtractedFiles"
		+ "/COCo_Cours-L1_CM-TD-TP_introduction-a-l-algorithmique-wo-video"
		+ "/section-01_Généralités"
		+ "/les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe/";

private final static String INPUT_FILE_NAME = DIRECTORY + "les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe.html";		
private final static String OUTPUT_PREFIX_FILENAME = DIRECTORY +"test/outputFile";
private final static String FILE_SPLIT_TOKEN = "<div class=\"page\">";

/**
 * 
 * @param args
 * @throws FileNotFoundException
 * @throws IOException
 */
public static void main(String[] args) throws FileNotFoundException, IOException {
	        String line = "";///the string to recover from the file
	        
	        try {
	            FileInputStream fs = new FileInputStream(INPUT_FILE_NAME);
	            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
	 
	            int count = 0;
	            FileOutputStream fos1 = new FileOutputStream(DIRECTORY +"test/metaData.html");
	            BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(fos1));
	            
	            while ((line = br.readLine()) != null) {
	                String mine = line.trim();
	                
	                
	                if(mine.contains(FILE_SPLIT_TOKEN)){
	                	fos1 = new FileOutputStream(OUTPUT_PREFIX_FILENAME + count + ".html");
	                    bw1 = new BufferedWriter(new OutputStreamWriter(fos1));
	                	count++;
	                }
	                
			        bw1.write(line);
			        bw1.newLine();
	                bw1.flush();
	                
	            }
	            System.out.println("Segmentation done !");
	            
	        }
	        catch(FileNotFoundException e) {
	            System.err.println("File not found!.");
	        }
	 }
		
}

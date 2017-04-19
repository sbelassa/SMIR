package ter.lina.SMIR.Segmentation;

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
 * @author salma
 *
 * segment a given file into pages
 */
public class FileSegmentation {

private final static String DIRECTORY = "ExtractedFiles"
		+ "/COCo_Cours-L1_CM-TD-TP_introduction-a-l-algorithmique-wo-video"
		+ "/section-01_Généralités"
		+ "/les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe/";

private final static String INPUT_FILE_NAME = DIRECTORY + "les_informaticiennes_de_la_dominance_de_classe_aux_discriminations_de_sexe.html";		
private final static String OUTPUT_PREFIX_FILENAME = DIRECTORY +"test/outputFile";
private final static String FILE_SPLIT_TOKEN = "<div class=\"page\">";



/**
 * 
 */
public FileSegmentation() {

	}


/**
 * 
 * @param inputFile the input html file to get content from
 * @param metaFile where to put the matadata file
 * @param outputFiles the name for the output files 
 * @throws IOException
 */
public  void segmentFileToPages(String inputFile,String metaFile,String outputFiles) throws IOException{
	
    String line = "";///the string to recover from the file
    
    try {
        FileInputStream fs = new FileInputStream(inputFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));

        int count = 0;
        FileOutputStream fos1 = new FileOutputStream(metaFile);
        BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(fos1));
        
        while ((line = br.readLine()) != null) {
            String mine = line.trim();
            
            
            if(mine.contains(FILE_SPLIT_TOKEN)){
            	fos1 = new FileOutputStream(outputFiles + count + ".html");
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




/*
public static void main(String[] args) throws FileNotFoundException, IOException {
	
		segmentFileToPages("ExtractedFiles/M1IHM/cours/5 hci design/5 hci design.html", "ExtractedFiles/M1IHM/cours/5 hci design/meta.html", "ExtractedFiles/M1IHM/cours/5 hci design/outputFile");

	 }

*/
}

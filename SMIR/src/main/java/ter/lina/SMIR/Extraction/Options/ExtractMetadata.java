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


public class ExtractMetadata {

	
	public void extractMetadata(File file, File metafile) throws IOException, SAXException, TikaException{

		  Parser parser = new AutoDetectParser();
	      BodyContentHandler handler = new BodyContentHandler();
	      Metadata metadata = new Metadata();
	      FileInputStream inputstream = new FileInputStream(file);
	      ParseContext context = new ParseContext();
	      
	      parser.parse(inputstream, handler, metadata, context);
	      System.out.println(handler.toString());
	      
  		  /// converting the file into json	      
	      FileOutputStream fos = new FileOutputStream(metafile); // has to be json
  		  PrintStream ps = new PrintStream(fos);
  		  
  		  
  		  
	}
}

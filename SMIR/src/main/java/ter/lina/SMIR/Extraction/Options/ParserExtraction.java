package ter.lina.SMIR.Extraction.Options;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * 
 * Parser extraction, extract any file type
 *
 */
public class ParserExtraction {
	
	
/**
 * 
 * @param args
 * @throws IOException
 * @throws SAXException
 * @throws TikaException
 */
public static void main(final String[] args) throws IOException,SAXException, TikaException {

   //putting the file we want to extract content from
   File file = new File("ExtractedFiles/test.txt");
   
   //parse method parameters
   Parser parser = new AutoDetectParser();
   BodyContentHandler handler = new BodyContentHandler(/*new ToXMLContentHandler()*/);
   
   Metadata metadata = new Metadata();
   FileInputStream inputstream = new FileInputStream(file);
   ParseContext context = new ParseContext();
   
   //parsing the file
   parser.parse(inputstream, handler, metadata, context);

   System.out.println(handler.toString());

   

	}




}
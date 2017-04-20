package ter.lina.SMIR.Tests;

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

import ter.lina.SMIR.Extraction.ExtractToHTML;
import ter.lina.SMIR.Extraction.IExtractToHTML;

/**
 * testing the extraction of a file's content into HTML 
 * @author salma, samia
 *
 */
public class ExtractToHTMLTest {

	private final static String MY_FILE = "ExtractedFiles/M1IHM/cours/5 hci design/5 hci design.pdf";
	private static IExtractToHTML extract;
	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public static void main(String[] args) throws IOException, SAXException, TikaException {
		extract = new ExtractToHTML();
		extract.extractFileToHTML(MY_FILE, "ExtractedFiles/M1IHM/cours/5 hci design/file.html");

	    	    
	}
}


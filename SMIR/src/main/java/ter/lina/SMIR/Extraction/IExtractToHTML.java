package ter.lina.SMIR.Extraction;

import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

/**
 * 
 * @author salma, samia
 *
 */
public interface IExtractToHTML {

	/**
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @throws SAXException
	 * @throws TikaException
	 * @throws IOException
	 */
	void extractFileToHTML(String inputFile, String outputFile) throws SAXException, TikaException, IOException;

}
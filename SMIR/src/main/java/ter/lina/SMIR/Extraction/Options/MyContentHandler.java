package ter.lina.SMIR.Extraction.Options;


import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;


/**
 * 
 * 
 *
 */
public abstract class MyContentHandler implements ContentHandler {
    private String pageTag = "p";
    protected int pageNumber = 0;
  
    public void startElement (String uri, String localName, String qName, Attributes atts) throws SAXException  {  

        if (pageTag.equals(qName)) {
            startPage();
        }
    }

    public void endElement (String uri, String localName, String qName) throws SAXException {  

        if (pageTag.equals(qName)) {
            endPage();
        }
    }

    protected void startPage() throws SAXException {
    pageNumber++;
    }

    protected void endPage() throws SAXException {
    return;
    }
   
}
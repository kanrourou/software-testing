package org.apache.tika.parser.html;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.StringWriter;

import org.apache.tika.metadata.Geographic;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HtmlParserBDDTest1 {

	
	/*
	 * Scenario: HTML file
	 *	Given HTML file consists of different elements,
	 *	When run the HTML parser,
	 *	Then we should get the expected information of different HTML elements.
	 */

	private String path = "/test-documents/testHTML.html";
    ContentHandler body = new BodyContentHandler();
	Metadata metadata = new Metadata();
    InputStream stream = HtmlParserTest.class.getResourceAsStream(path);
    final StringWriter href = new StringWriter();
    final StringWriter name = new StringWriter();
    ContentHandler link = new DefaultHandler() {
    	@Override
        public void startElement(String u, String l, String n, Attributes a)throws SAXException {
            if ("a".equals(l)) {
                if (a.getValue("href") != null) {
                    href.append(a.getValue("href"));
                } else if (a.getValue("name") != null) {
                    name.append(a.getValue("name"));
                }
            }
        }
    };
    
	
	
	/*
	 * Scenario: HTML file
	 *	Given title element,
	 *	When run the HTML parser,
	 *	Then we should get the content of title.
	 */
    @Test
	public void htmlParserShouldReturnTitle() throws Exception {
        
		try {
            new HtmlParser().parse(
                    stream, new TeeContentHandler(body, link),
                    metadata, new ParseContext());
        } finally {
            stream.close();
        }
        
		assertEquals("Title : Test Indexation Html", metadata.get(TikaCoreProperties.TITLE));
	}
	
    /*
	 * Scenario: HTML file
	 *	Given meta element,
	 *	When run the HTML parser,
	 *	Then we should get the content of specific meta element attributes.
	 */
    @Test
	public void htmlParserShouldReturnMeta() throws Exception {
		
		try {
            new HtmlParser().parse(
                    stream, new TeeContentHandler(body, link),
                    metadata, new ParseContext());
        } finally {
            stream.close();
        }
		
		assertEquals("Tika Developers", metadata.get("Author"));
        assertEquals("5", metadata.get("refresh"));
        assertEquals("51.2312", metadata.get(Geographic.LATITUDE));
        assertEquals("-5.1987", metadata.get(Geographic.LONGITUDE));
	}
	
    /*
	 * Scenario: HTML file
	 *	Given anchor element,
	 *	When run the HTML parser,
	 *	Then we should get the content of specific attributes.
	 */
    @Test
	public void htmlParserShouldReturnAnchor() throws Exception {
		
		try {
            new HtmlParser().parse(
                    stream, new TeeContentHandler(body, link),
                    metadata, new ParseContext());
        } finally {
            stream.close();
        }
		
		assertEquals("http://www.apache.org/", href.toString());
		assertEquals("test-anchor", name.toString());
		
	}
	
    /*
	 * Scenario: HTML file
	 *	Given body element,
	 *	When run the HTML parser,
	 *	Then we should get the content of body.
	 */
    @Test
	public void htmlParserShouldReturnBody() throws Exception {
		
		try {
            new HtmlParser().parse(
                    stream, new TeeContentHandler(body, link),
                    metadata, new ParseContext());
        } finally {
            stream.close();
        }
		
		String content = body.toString();
        assertTrue(content.contains("Test Indexation Html"));
        assertTrue(content.contains("Indexation du fichier"));
	}

}

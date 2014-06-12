package org.apache.tika.parser.html;

import static org.junit.Assert.*;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.junit.BeforeClass;
import org.junit.Test;

public class HtmlParserBDDTest2 {

	/*
	 * Scenario: HTML file in XHTML style
	 *	Given HTML file which is written in XHTML style,
	 *	When run the HTML parser,
	 *	Then we should get the expected information of different HTML elements.
	 */
	
	String path = "/test-documents/testXHTML.html";
    Metadata metadata = new Metadata();
    
    /*
	 * Scenario: HTML file in XHTML style
	 *	Given HTML file which is written in XHTML style,
	 *	When run the HTML parser,
	 *	Then we should get content type of the file.
	 */
    @Test
    public void htmlParserShouldReturnContentType() throws Exception {
    	
    	new Tika().parseToString(HtmlParserTest.class.getResourceAsStream(path), metadata);
    	
    	assertEquals("application/xhtml+xml", metadata.get(Metadata.CONTENT_TYPE));
    }
    
    /*
	 * Scenario: HTML file in XHTML style
	 *	Given HTML file which is written in XHTML style,
	 *	When run the HTML parser,
	 *	Then we should get content of the title.
	 */
    @Test
    public void htmlParserShouldReturnTitle() throws Exception {
    	
    	new Tika().parseToString(HtmlParserTest.class.getResourceAsStream(path), metadata);
    	
    	assertEquals("XHTML test document", metadata.get("title"));
    }
    
    /*
	 * Scenario: HTML file in XHTML style
	 *	Given HTML file which is written in XHTML style,
	 *	When run the HTML parser,
	 *	Then we should get content of specific meta element attributes.
	 */
    @Test
    public void htmlParserShouldReturnMeta() throws Exception {
    	
    	new Tika().parseToString(HtmlParserTest.class.getResourceAsStream(path), metadata);
    	
    	 assertEquals("Tika Developers", metadata.get("Author"));
         assertEquals("5", metadata.get("refresh"));
    }
    
    /*
	 * Scenario: HTML file in XHTML style
	 *	Given HTML file which is written in XHTML style,
	 *	When run the HTML parser,
	 *	Then we should get content of body.
	 */
    @Test
    public void htmlParserShouldReturnBody() throws Exception {
    	
    	String content = new Tika().parseToString(
                HtmlParserTest.class.getResourceAsStream(path), metadata);
    	
    	assertTrue(content.contains("ability of Apache Tika"));
        assertTrue(content.contains("extract content"));
        assertTrue(content.contains("an XHTML document"));
    }

}

package org.apache.tika.parser.html;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.regex.Pattern;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.ContentHandler;

public class HtmlParserBDDTest3 {

	/*
	 * Scenario: HTML file
	 *	Given HTML file consists of different elements,
	 *	When run the HTML parser,
	 *	Then we should know whether the text alignment is correct.
	 */
	
	
	
	/*
	 * Scenario: HTML file
	 *	Given empty element,
	 *	When run the HTML parser,
	 *	Then we should get empty content.
	 */
	@Test
	public void htmlParserShouldReturnEmptyString() throws Exception {
        ContentHandler handler = new BodyContentHandler();
        new HtmlParser().parse(
                new ByteArrayInputStream(new byte[0]),
                handler,  new Metadata(), new ParseContext());
        
        assertEquals("", handler.toString());
    }
	
	/*
	 * Scenario: HTML file
	 *	Given table element with two letters,
	 *	When run the HTML parser,
	 *	Then we should get two separate letters instead of one.
	 */
	@Test
    public void htmlParserShouldReturnTwoLettersWithWhiteSpace() throws Exception {
        String test =
            "<html><body><table><tr><td>a</td><td>b</td></table></body></html>";
        String content = new Tika().parseToString(
                new ByteArrayInputStream(test.getBytes("UTF-8")));
        
        assertTrue(content.contains("a"));
        assertTrue(content.contains("b"));
        assertFalse(content.contains("ab"));
    }
	
	/*
	 * Scenario: HTML file
	 *	Given HTML file with two line breaks,
	 *	When run the HTML parser,
	 *	Then we should get three separate sentences instead of one.
	 */
	@Test
    public void htmlParserShouldReturnThreeSentences() throws Exception {
        String test = "<html><body><div>foo<br>bar</div>baz</body></html>";
        String text = new Tika().parseToString(
                new ByteArrayInputStream(test.getBytes("US-ASCII")));
        String[] parts = text.trim().split("\\s+");
        assertEquals(3, parts.length);
        assertEquals("foo", parts[0]);
        assertEquals("bar", parts[1]);
        assertEquals("baz", parts[2]);
    }
	
	/*
	 * Scenario: HTML file
	 *	Given an list element nested in ul and body elements,
	 *	When run the HTML parser,
	 *	Then we should get indented sentence with two line breaks.
	 */
	@Test
    public void htmlParserShouldReturnIndentedSentenceWithTwoLineBreaks() throws Exception {
        final String html = "<html><head><title>Title</title></head>" +
                "<body><ul><li>one</li></ul></body></html>";

        BodyContentHandler handler = new BodyContentHandler();
        new HtmlParser().parse(
                new ByteArrayInputStream(html.getBytes("UTF-8")),
                handler,  new Metadata(), new ParseContext());

        // Make sure we get <tab>, "one", newline, newline
        String result = handler.toString();

        assertTrue(Pattern.matches("\tone\n\n", result));
    }
}

package org.apache.tika.parser.html;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.regex.Pattern;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.ContentHandler;

public class HtmlParserBDDTest4 {

	/*
	 * Scenario: HTML file
	 *	Given HTML file consists of image, frame, iframe, area,
	 *	When run the HTML parser,
	 *	Then we should be able to extract the URL which is associate with specific element.
	 */
	
	
	
	/*
	 * Scenario: HTML file
	 *	Given HTML file which contains image,
	 *	When run the HTML parser,
	 *	Then we should be able to extract the URL which is associate with img element.
	 */
	 @Test
	    public void htmlParserShouldReturnImageURL() throws Exception {
	        final String test = "<html><head><title>Title</title>" +
	        "<base href=\"http://domain.com\" />" +
	        "</head><body><img src=\"image.jpg\" /></body></html>";

	        StringWriter sw = new StringWriter();
	        new HtmlParser().parse(
	                new ByteArrayInputStream(test.getBytes("UTF-8")),
	                makeHtmlTransformer(sw), new Metadata(), new ParseContext());

	        String result = sw.toString();

	        assertTrue(Pattern.matches("(?s).*src=\"http://domain.com/image.jpg\".*$", result));
	    }
	 
	 /*
		 * Scenario: HTML file
		 *	Given HTML file which contains image,
		 *	When run the HTML parser,
		 *	Then we should be able to extract the URL which is associate with frame element.
		 */
	 @Test
	    public void htmlParserShouldReturnFrameURL() throws Exception {
	        final String test = "<html><head><title>Title</title>" +
	        "<base href=\"http://domain.com\" />" +
	        "</head><frameset><frame src=\"frame.html\" /></frameset></html>";

	        StringWriter sw = new StringWriter();
	        new HtmlParser().parse(
	                new ByteArrayInputStream(test.getBytes("UTF-8")),
	                makeHtmlTransformer(sw), new Metadata(), new ParseContext());

	        String result = sw.toString();

	        assertTrue(Pattern.matches("(?s).*<frame .* src=\"http://domain.com/frame.html\"/>.*$", result));
	    }
	 
	 /*
		 * Scenario: HTML file
		 *	Given HTML file which contains image,
		 *	When run the HTML parser,
		 *	Then we should be able to extract the URL which is associate with iframe element.
		 */
	 @Test
	    public void htmlParserShouldReturnIFrameURL() throws Exception {
	        final String test = "<html><head><title>Title</title>" +
	        "<base href=\"http://domain.com\" />" +
	        "</head><body><iframe src =\"framed.html\" width=\"100%\" height=\"300\">" +
	        "<p>Your browser doesn't support iframes!</p></body></html>";

	        StringWriter sw = new StringWriter();
	        new HtmlParser().parse(
	                new ByteArrayInputStream(test.getBytes("UTF-8")),
	                makeHtmlTransformer(sw), new Metadata(), new ParseContext());

	        String result = sw.toString();

	        assertTrue(Pattern.matches("(?s).*<iframe .* src=\"http://domain.com/framed.html\".*$", result));
	    }
	 
	 /*
		 * Scenario: HTML file
		 *	Given HTML file which contains image,
		 *	When run the HTML parser,
		 *	Then we should be able to extract the URL which is associate with area element.
		 */
	 @Test
	    public void htmlParserShouldReturnAreaURL() throws Exception {
	        final String test = "<html><head><title>Title</title>" +
	        "<base href=\"http://domain.com\" />" +
	        "</head><body><p><map name=\"map\" id=\"map\">" +
	        "<area shape=\"rect\" href=\"map.html\" alt=\"\" />" +
	        "</map></p></body></html>";

	        StringWriter sw = new StringWriter();
	        new HtmlParser().parse(
	                new ByteArrayInputStream(test.getBytes("UTF-8")),
	                makeHtmlTransformer(sw), new Metadata(), new ParseContext());

	        String result = sw.toString();

	        assertTrue(Pattern.matches("(?s).*<map .*<area .* href=\"http://domain.com/map.html\".*</map>.*$", result));
	    }

	 /**
	     * Create ContentHandler that transforms SAX events into textual HTML output,
	     * and writes it out to <writer> - typically this is a StringWriter.
	     *
	     * @param writer Where to write resulting HTML text.
	     * @return ContentHandler suitable for passing to parse() methods.
	     * @throws Exception
	     */
	    private ContentHandler makeHtmlTransformer(Writer writer) throws Exception {
	        SAXTransformerFactory factory = (SAXTransformerFactory)SAXTransformerFactory.newInstance();
	        TransformerHandler handler = factory.newTransformerHandler();
	        handler.getTransformer().setOutputProperty(OutputKeys.METHOD, "html");
	        handler.getTransformer().setOutputProperty(OutputKeys.INDENT, "no");
	        handler.getTransformer().setOutputProperty(OutputKeys.ENCODING, "utf-8");
	        handler.setResult(new StreamResult(writer));
	        return handler;
	    }
}

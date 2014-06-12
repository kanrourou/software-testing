package org.apache.tika.parser.image;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.helpers.DefaultHandler;

public class ImageParserBDDTest2 {

	/*
	 * Scenario: GIF image
	 *	Given GIF image,
	 *	When run the image parser,
	 *	Then we should get the expected information of this image.
	 */
	
	
	
	/*
	 * Scenario: GIF image
	 *	Given GIF image,
	 *	When run the image parser,
	 *	Then we should get the content type of image.
	 */
	@Test
    public void imageParserShouldReturnTheContentTypeOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/gif");
        InputStream stream =
            getClass().getResourceAsStream("/test-documents/testGIF.gif");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());

        assertEquals("image/gif", metadata.get("Content-Type"));
    }
	
	/*
	 * Scenario: GIF image
	 *	Given GIF image,
	 *	When run the image parser,
	 *	Then we should get the content size of image.
	 */
	@Test
	public void imageParserShouldReturnSizeOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/gif");
        InputStream stream =
            getClass().getResourceAsStream("/test-documents/testGIF.gif");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        
        assertEquals("75", metadata.get("height"));
        assertEquals("100", metadata.get("width"));
	}
	
	/*
	 * Scenario: GIF image
	 *	Given GIF image,
	 *	When run the image parser,
	 *	Then we should get the content size of image.
	 */
	@Test
	public void imageParserShouldReturnDimensionOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/gif");
        InputStream stream =
            getClass().getResourceAsStream("/test-documents/testGIF.gif");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        
        assertEquals("Normal", metadata.get("Dimension ImageOrientation"));
        assertEquals("0", metadata.get("Dimension HorizontalPixelOffset"));
        assertEquals("0", metadata.get("Dimension VerticalPixelOffset"));
	}
	
	/*
	 * Scenario: GIF image
	 *	Given GIF image,
	 *	When run the image parser,
	 *	Then we should get the content description of image.
	 */
	@Test
	public void imageParserShouldReturnDescriptionOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/gif");
        InputStream stream =
            getClass().getResourceAsStream("/test-documents/testGIF.gif");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        
        assertEquals("imageLeftPosition=0, imageTopPosition=0, imageWidth=100, imageHeight=75, interlaceFlag=false", metadata.get("ImageDescriptor"));
	}
	
	/*
	 * Scenario: GIF image
	 *	Given GIF image,
	 *	When run the image parser,
	 *	Then we should get the chroma information of image.
	 */
	@Test
	public void imageParserShouldReturnChromaInformationOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/gif");
        InputStream stream =
            getClass().getResourceAsStream("/test-documents/testGIF.gif");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        
        assertEquals("3", metadata.get("Chroma NumChannels"));
        assertEquals("RGB", metadata.get("Chroma ColorSpaceType"));
	}
	
	/*
	 * Scenario: GIF image
	 *	Given GIF image,
	 *	When run the image parser,
	 *	Then we should get the compression information of image.
	 */
	@Test
	public void imageParserShouldReturnCompressionInformationOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/gif");
        InputStream stream =
            getClass().getResourceAsStream("/test-documents/testGIF.gif");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        
        assertEquals("true", metadata.get("Compression Lossless"));
        assertEquals("lzw", metadata.get("Compression CompressionTypeName"));
        assertEquals("1", metadata.get("Compression NumProgressiveScans"));
	}

}

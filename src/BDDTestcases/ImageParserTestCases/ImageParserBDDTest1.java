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

public class ImageParserBDDTest1 {

	/*
	 * Scenario: BMP image
	 *	Given BMP image,
	 *	When run the image parser,
	 *	Then we should get the expected information of this image.
	 */
	
	
	/*
	 * Scenario: BMP image
	 *	Given BMP image,
	 *	When run the image parser,
	 *	Then we should get the content type of this image.
	 */
    @Test
    public void imageParserShouldReturnContentTypeOfImage() throws Exception {
    	Parser parser =new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/bmp");
        InputStream stream =
            getClass().getResourceAsStream("/test-documents/testBMP.bmp");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        
        assertEquals("image/bmp", metadata.get("Content-Type"));
    }
    
    /*
	 * Scenario: BMP image
	 *	Given BMP image,
	 *	When run the image parser,
	 *	Then we should get the size of this image.
	 */
    @Test
    public void imageParserShouldReturnSizeOfImage() throws Exception {
    	Parser parser=new ImageParser();
    	Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/bmp");
        InputStream stream =
            getClass().getResourceAsStream("/test-documents/testBMP.bmp");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        
        assertEquals("75", metadata.get("height"));
        assertEquals("100", metadata.get("width"));
    }
    
    /*
	 * Scenario: BMP image
	 *	Given BMP image,
	 *	When run the image parser,
	 *	Then we should get bits per sample of this image.
	 */
    @Test
    public void imageParserShouldReturnBitsPerSampleOfImage() throws Exception {
    	Parser parser=new ImageParser();
    	Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/bmp");
        InputStream stream =
            getClass().getResourceAsStream("/test-documents/testBMP.bmp");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
    	
        assertEquals("8 8 8", metadata.get("Data BitsPerSample"));
    }
    
    /*
	 * Scenario: BMP image
	 *	Given BMP image,
	 *	When run the image parser,
	 *	Then we should get the dimension of this image.
	 */
    @Test
    public void imageParserShouldReturnDimensionOfImage() throws Exception {
    	Parser parser=new ImageParser();
    	Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/bmp");
        InputStream stream =
            getClass().getResourceAsStream("/test-documents/testBMP.bmp");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
    	
        assertEquals("1.0", metadata.get("Dimension PixelAspectRatio"));
        assertEquals("0", metadata.get("Dimension VerticalPhysicalPixelSpacing"));
        assertEquals("0", metadata.get("Dimension HorizontalPhysicalPixelSpacing"));
    }
    
    /*
	 * Scenario: BMP image
	 *	Given BMP image,
	 *	When run the image parser,
	 *	Then we should get the compression type name of this image.
	 */
    @Test
    public void imageParserShouldReturnCompressionTypeNameOfImage() throws Exception {
    	Parser parser=new ImageParser();
    	Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/bmp");
        InputStream stream =
            getClass().getResourceAsStream("/test-documents/testBMP.bmp");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
    	
        assertEquals("BI_RGB", metadata.get("Compression CompressionTypeName"));
    }
}

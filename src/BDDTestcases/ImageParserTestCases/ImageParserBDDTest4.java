package org.apache.tika.parser.image;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.helpers.DefaultHandler;

public class ImageParserBDDTest4 {

	/*
	 * Scenario: PNG image
	 *	Given PNG image,
	 *	When run the image parser,
	 *	Then we should get the expected information of this image.
	 */

	/*
	 * Scenario: PNG image
	 *	Given PNG image,
	 *	When run the image parser,
	 *	Then we should get content type of this image.
	 */
	@Test
    public void imageParserShouldReturnContentOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/png");
        InputStream stream =
        getClass().getResourceAsStream("/test-documents/testPNG.png");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());

        assertEquals("image/png", metadata.get("Content-Type"));
    }
	
	/*
	 * Scenario: PNG image
	 *	Given PNG image,
	 *	When run the image parser,
	 *	Then we should get size of this image.
	 */
	@Test
	public void  imageParserShouldReturnSizeOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/png");
        InputStream stream =
        getClass().getResourceAsStream("/test-documents/testPNG.png");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());

        assertEquals("75", metadata.get("height"));
        assertEquals("100", metadata.get("width"));
	}
	
	/*
	 * Scenario: PNG image
	 *	Given PNG image,
	 *	When run the image parser,
	 *	Then we should get dimension of this image.
	 */
	@Test
	public void  imageParserShouldReturnDimensionOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/png");
        InputStream stream =
        getClass().getResourceAsStream("/test-documents/testPNG.png");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());

        assertEquals("0.35273367", metadata.get("Dimension VerticalPixelSize"));
        assertEquals("Normal", metadata.get("Dimension ImageOrientation"));
        assertEquals("1.0", metadata.get("Dimension PixelAspectRatio"));
        assertEquals("0.35273367", metadata.get("Dimension HorizontalPixelSize"));
	}

	/*
	 * Scenario: PNG image
	 *	Given PNG image,
	 *	When run the image parser,
	 *	Then we should get chroma information of this image.
	 */
	@Test
	public void  imageParserShouldReturnChromaInformationOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/png");
        InputStream stream =
        getClass().getResourceAsStream("/test-documents/testPNG.png");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());

        assertEquals("3", metadata.get("Chroma NumChannels"));
        assertEquals("RGB", metadata.get("Chroma ColorSpaceType"));
        assertEquals("none", metadata.get("Transparency Alpha"));
        assertEquals("true", metadata.get("Chroma BlackIsZero"));
	}
	
	/*
	 * Scenario: PNG image
	 *	Given PNG image,
	 *	When run the image parser,
	 *	Then we should get compression information of this image.
	 */
	@Test
	public void  imageParserShouldReturnCompressionInformationOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/png");
        InputStream stream =
        getClass().getResourceAsStream("/test-documents/testPNG.png");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());

        assertEquals("true", metadata.get("Compression Lossless"));
        assertEquals("1", metadata.get("Compression NumProgressiveScans"));
        assertEquals("deflate", metadata.get("Compression CompressionTypeName"));
	}
	
	/*
	 * Scenario: PNG image
	 *	Given PNG image,
	 *	When run the image parser,
	 *	Then we should get data information of this image.
	 */
	@Test
	public void  imageParserShouldReturnDataInformationOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/png");
        InputStream stream =
        getClass().getResourceAsStream("/test-documents/testPNG.png");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());

        assertEquals("8 8 8", metadata.get("Data BitsPerSample"));
        assertEquals("UnsignedIntegral", metadata.get("Data SampleFormat"));
        assertEquals("PixelInterleaved", metadata.get("Data PlanarConfiguration"));
	}
	
	/*
	 * Scenario: PNG image
	 *	Given PNG image,
	 *	When run the image parser,
	 *	Then we should get time information of this image.
	 */
	@Test
	public void  imageParserShouldReturnTimeInformationOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/png");
        InputStream stream =
        getClass().getResourceAsStream("/test-documents/testPNG.png");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());

        assertEquals("year=2008, month=5, day=6, hour=6, minute=18, second=47", metadata.get("tIME"));
        assertEquals("year=2008, month=5, day=6, hour=6, minute=18, second=47", metadata.get("Document ImageModificationTime"));
	}

}

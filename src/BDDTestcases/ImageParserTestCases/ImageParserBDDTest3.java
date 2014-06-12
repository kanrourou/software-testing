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

public class ImageParserBDDTest3 {

	/*
	 * Scenario: JPEG image
	 *	Given JPEG image,
	 *	When run the image parser,
	 *	Then we should get the expected information of this image.
	 */

	
	
	/*
	 * Scenario: JPEG image
	 *	Given JPEG image,
	 *	When run the image parser,
	 *	Then we should get content type of this image.
	 */
	@Test
    public void imageParser() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/jpeg");
        InputStream stream =getClass().getResourceAsStream("/test-documents/testJPEG.jpg");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());

        
        assertEquals("image/jpeg", metadata.get("Content-Type"));
    }
	
	/*
	 * Scenario: JPEG image
	 *	Given JPEG image,
	 *	When run the image parser,
	 *	Then we should get size of this image.
	 */
	@Test
	public void imageParserShouldReturnSizeOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/jpeg");
        InputStream stream =getClass().getResourceAsStream("/test-documents/testJPEG.jpg");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        
        assertEquals("75", metadata.get("height"));
        assertEquals("100", metadata.get("width"));
	}
	
	/*
	 * Scenario: JPEG image
	 *	Given JPEG image,
	 *	When run the image parser,
	 *	Then we should get dimension of this image.
	 */
	@Test
	public void imageParserShouldReturnDimensionOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/jpeg");
        InputStream stream =getClass().getResourceAsStream("/test-documents/testJPEG.jpg");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        
        assertEquals("0.35277778", metadata.get("Dimension VerticalPixelSize"));
        assertEquals("normal", metadata.get("Dimension ImageOrientation"));
        assertEquals("1.0", metadata.get("Dimension PixelAspectRatio"));
        assertEquals("0.35277778", metadata.get("Dimension HorizontalPixelSize"));
	}
	
	/*
	 * Scenario: JPEG image
	 *	Given JPEG image,
	 *	When run the image parser,
	 *	Then we should get chroma information of this image.
	 */
	@Test
	public void imageParserShouldReturnChromaInformationOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/jpeg");
        InputStream stream =getClass().getResourceAsStream("/test-documents/testJPEG.jpg");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        
        assertEquals("3", metadata.get("Chroma NumChannels"));
        assertEquals("YCbCr", metadata.get("Chroma ColorSpaceType"));
	}
	
	/*
	 * Scenario: JPEG image
	 *	Given JPEG image,
	 *	When run the image parser,
	 *	Then we should get compression information of this image.
	 */
	@Test
	public void imageParserShouldReturnCompressionInformationOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/jpeg");
        InputStream stream =getClass().getResourceAsStream("/test-documents/testJPEG.jpg");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        
        assertEquals("JPEG", metadata.get("Compression CompressionTypeName"));
        assertEquals("false", metadata.get("Compression Lossless"));
        assertEquals("1", metadata.get("Compression NumProgressiveScans"));
	}
	
	/*
	 * Scenario: JPEG image
	 *	Given JPEG image,
	 *	When run the image parser,
	 *	Then we should get marker information of this image.
	 */
	@Test
	public void imageParserShouldReturnMarkerInformationOfImage() throws Exception {
		Parser parser = new ImageParser();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "image/jpeg");
        InputStream stream =getClass().getResourceAsStream("/test-documents/testJPEG.jpg");
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        
        assertEquals("class=0, htableId=0", metadata.get("markerSequence dht dhtable"));
        assertEquals("225", metadata.get("markerSequence unknown"));
        assertEquals("componentSelector=1, dcHuffTable=0, acHuffTable=0", metadata.get("markerSequence sos scanComponentSpec"));
        assertEquals("elementPrecision=0, qtableId=0", metadata.get("markerSequence dqt dqtable"));
        assertEquals("numScanComponents=3, startSpectralSelection=0, endSpectralSelection=63, approxHigh=0, approxLow=0", metadata.get("markerSequence sos"));
        assertEquals("componentId=1, HsamplingFactor=1, VsamplingFactor=1, QtableSelector=0", metadata.get("markerSequence sof componentSpec"));
        assertEquals("process=0, samplePrecision=8, numLines=75, samplesPerLine=100, numFrameComponents=3", metadata.get("markerSequence sof"));
	}
	

}

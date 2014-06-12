package org.apache.tika.metadata;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class MetadataBDDTest4 {

	/*
	 * Scenario: get Int value from Metadata
	 *	Given a empty Metadata,
	 *	When call the getInt function,
	 *	Then should return null.
	 */
	@Test
    public void shouldReturnNullWhenGetIntFromEmptyMetadata() {
        Metadata meta = new Metadata();
        
        assertEquals(null, meta.get(Metadata.IMAGE_WIDTH));
        assertEquals(null, meta.getInt(Metadata.IMAGE_WIDTH));
    }
	
	/*
	 * Scenario: get Int value from Metadata
	 *	Given a Metadata with multi-valued Properties,
	 *	When call the getInt function,
	 *	Then should return null.
	 */
	@Test
    public void shouldReturnNullWhenGetIntFromMultiValuedProperties() {
        Metadata meta = new Metadata();
        
        meta.set(Metadata.IMAGE_WIDTH, 22);
        assertEquals(22, meta.getInt(Metadata.IMAGE_WIDTH).intValue());
        assertEquals(null, meta.getInt(Metadata.BITS_PER_SAMPLE));
        assertEquals(null, meta.getInt(TikaCoreProperties.CREATED));
    }
	
	/*
	 * Scenario: get Int value from Metadata
	 *	Given a Metadata,
	 *	When call set with a non-integer value,
	 *	Then should return null when call getInt function.
	 */
	@Test
    public void shouldReturnNullWhenSetANonIntValueAndGetIt() {
        Metadata meta = new Metadata();
        
        meta.set(Metadata.IMAGE_WIDTH, "INVALID");
        assertEquals("INVALID", meta.get(Metadata.IMAGE_WIDTH));
        assertEquals(null, meta.getInt(Metadata.IMAGE_WIDTH));
    }
	
	/*
	 * Scenario: set Int value
	 *	Given a Metadata and property with multiple integer values,
	 *	When call set with a integer value,
	 *	Then operation should fail.
	 */
	@Test
    public void cannotSetPropertiesWithMultipleIntAsSingleInt() {
        Metadata meta = new Metadata();
        
        try {
            meta.set(Metadata.BITS_PER_SAMPLE, 1);
            fail("Shouldn't be able to set a multi valued property as an int");
        } catch(PropertyTypeException e) {}
        try {
            meta.set(TikaCoreProperties.CREATED, 1);
            fail("Shouldn't be able to set a date property as an int");
        } catch(PropertyTypeException e) {}
    }
	
	/*
	 * Scenario: set and get Int value
	 *	Given a Metadata and property with simple integer,
	 *	When set a property as simple integer,
	 *	and call getInt function
	 *	Then both functions will work correctly.
	 */
	@Test
    public void canSetAndGetSimpleInteger() {
        Metadata meta = new Metadata();
        
        meta.set(Metadata.IMAGE_WIDTH, 22);
        assertEquals("22", meta.get(Metadata.IMAGE_WIDTH));
        assertEquals(22, meta.getInt(Metadata.IMAGE_WIDTH).intValue());
    }
	
	/*
	 * Scenario: get Date value
	 *	Given an empty Metadata,
	 *	When call getDate function,
	 *	Then should return null.
	 */
	@Test
    public void shouldReturnNullWhenRetrieveInEmptyMetadata() {
        Metadata meta = new Metadata();
        
        assertEquals(null, meta.getDate(TikaCoreProperties.CREATED));
    }
	
	/*
	 * Scenario: get Date value
	 *	Given an Metadata and Property with non-simple Date value,
	 *	When call getDate function,
	 *	Then should return null.
	 */
	@Test
    public void shouldReturnNullWhenGetDateFromPropertyWithNonSimpleDateValue() {
        Metadata meta = new Metadata();
        
        meta.set(TikaCoreProperties.CREATED, new Date(1000));
        assertEquals(1000, meta.getDate(TikaCoreProperties.CREATED).getTime());
        assertEquals(null, meta.getDate(Metadata.BITS_PER_SAMPLE));
    }
	
	/*
	 * Scenario: get Date value
	 *	Given an Metadata and Property,
	 *	When call set Property as non-date value,
	 *	and call get function,
	 *	Then should return null.
	 */
	@Test
    public void shouldReturnNullWhenGetAfterSettingANonDateValue() {
        Metadata meta = new Metadata();
        
        meta.set(TikaCoreProperties.CREATED, "INVALID");
        assertEquals("INVALID", meta.get(TikaCoreProperties.CREATED));
        assertEquals(null, meta.getDate(TikaCoreProperties.CREATED));
    }
	
	/*
	 * Scenario: set Date value
	 *	Given an Metadata and Property with non-simple Date value,
	 *	When call set function,
	 *	Then should fail.
	 */
	@Test
    public void shouldFailWhenSettingPropertiesWithoutSimpleDateValueAsSimpleDate() {
        Metadata meta = new Metadata();
        
        try {
            meta.set(Metadata.BITS_PER_SAMPLE, new Date(1000));
            fail("Shouldn't be able to set a multi valued property as a date");
        } catch(PropertyTypeException e) {}
        try {
            meta.set(Metadata.IMAGE_WIDTH, new Date(1000));
            fail("Shouldn't be able to set an int property as an date");
        } catch(PropertyTypeException e) {}
    }
	
	/*
	 * Scenario: set and get Date value
	 *	Given an Metadata and Property with Simple Date Value,
	 *	When call set and get function,
	 *	Then should work correctly.
	 */
	@Test
    public void shouldWorkCorrectlyWhenSetAndGetValueFromPropertyWithSimpleDateValue() {
        Metadata meta = new Metadata();
        
        meta.set(TikaCoreProperties.CREATED, new Date(1000));
        assertEquals("1970-01-01T00:00:01Z", meta.get(TikaCoreProperties.CREATED));
        assertEquals(1000, meta.getDate(TikaCoreProperties.CREATED).getTime());
    }
}

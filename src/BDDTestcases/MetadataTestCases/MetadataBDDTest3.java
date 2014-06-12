package org.apache.tika.metadata;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

public class MetadataBDDTest3 {


	
	/*
	 * Scenario: add properties to Metadata
	 *	Given a empty properties to be added,
	 *	When call the setAll function,
	 *	Then there is no change on Metadata.
	 */
    @Test
    public void emptyPropertiesShouldNotBeAddedToMetadata() {
        Metadata meta = new Metadata();
        Properties props = new Properties();

        meta.setAll(props);
        assertEquals(0, meta.size());
    }
    
    /*
	 * Scenario: add properties to Metadata
	 *	Given non-empty properties to be added,
	 *	When call the setAll function,
	 *	Then properties should be added to Metadata.
	 */
    @Test
    public void nonEmptyPropertiesShouldBeAddedToMetadata() {
        String[] values = null;
        Metadata meta = new Metadata();
        Properties props = new Properties();

        props.setProperty("name-one", "value1.1");
        meta.setAll(props);
        assertEquals(1, meta.size());
        values = meta.getValues("name-one");
        assertEquals(1, values.length);
        assertEquals("value1.1", values[0]);

        props.setProperty("name-two", "value2.1");
        meta.setAll(props);
        assertEquals(2, meta.size());
        values = meta.getValues("name-one");
        assertEquals(1, values.length);
        assertEquals("value1.1", values[0]);
        values = meta.getValues("name-two");
        assertEquals(1, values.length);
        assertEquals("value2.1", values[0]);
    }
    
    /*
	 * Scenario: remove items in Metadata
	 *	Given empty Metadata,
	 *	When remove function,
	 *	Then nothing will change.
	 */
    @Test
    public void noChangeWhenRemoveItemFromEmptyMetadata() {
        Metadata meta = new Metadata();
        meta.remove("name-one");
        assertEquals(0, meta.size());
    }
    
    /*
	 * Scenario: remove items in Metadata
	 *	Given Metadata consists of several items,
	 *	and key which is not contained in Metadata,
	 *	When remove function,
	 *	Then nothing will change.
	 */
    @Test
    public void noChangeWhenRemoveItemFromMetadataWhichDoesNotContainIt() {
        Metadata meta = new Metadata();
        meta.add("name-one", "value-1.1");
        meta.add("name-two", "value-2.1");
        assertEquals(2, meta.size());
        assertNotNull(meta.get("name-one"));
        assertNotNull(meta.get("name-two"));
        meta.remove("name-three");
        assertEquals(2, meta.size());
    }
    
    /*
	 * Scenario: remove items in Metadata
	 *	Given Metadata consists of several items,
	 *	and key which is contained in Metadata.
	 *	When remove function,
	 *	Then item which is associated with that key should be removed.
	 */
    @Test
    public void itemShouldBeReomovedWhenMetadatContainsIt() {
        Metadata meta = new Metadata();
        meta.add("name-one", "value-1.1");
        meta.add("name-one", "value-1.2");
        meta.add("name-two", "value-2.1");
        assertEquals(2, meta.size());
        assertNotNull(meta.get("name-one"));
        assertNotNull(meta.get("name-two"));
        meta.remove("name-one");
        assertEquals(1, meta.size());
        assertNull(meta.get("name-one"));
        assertNotNull(meta.get("name-two"));
        meta.remove("name-two");
        assertEquals(0, meta.size());
        assertNull(meta.get("name-one"));
        assertNull(meta.get("name-two"));
    }
    
    /*
	 * Scenario: determine whether two Metadata are same
	 *	Given two Metadatas,
	 *	and with same items, namely same keys and values,
	 *	When call equals,
	 *	Then should always return true.
	 */
    @Test
    public void shouldReturnTrueWhenMetadatasContainSameItems() {
        Metadata meta1 = new Metadata();
        Metadata meta2 = new Metadata();
        assertTrue(meta1.equals(meta2));
        meta1.add("name-one", "value-1.1");
        meta2.add("name-one", "value-1.1");
        assertTrue(meta1.equals(meta2));
        meta1.add("name-one", "value-1.2");
        meta2.add("name-one", "value-1.2");
        assertTrue(meta1.equals(meta2));
        meta1.add("name-two", "value-2.1");
        meta2.add("name-two", "value-2.1");
        assertTrue(meta1.equals(meta2));
    }
    
    /*
	 * Scenario: determine whether two Metadata are same
	 *	Given two Metadatas,
	 *	and with differents items, namely different keys or values,
	 *	When call equals,
	 *	Then should always return false.
	 */
    @Test
    public void shouldReturnFlaseWhenMetadatasContainDifferentItems() {
        Metadata meta1 = new Metadata();
        Metadata meta2 = new Metadata();
        assertFalse(meta1.equals(null));
        assertFalse(meta1.equals("String"));
        meta1.add("name-one", "value-1.1");
        assertFalse(meta1.equals(meta2));
        meta2.add("name-one", "value-1.1");
        meta1.add("name-one", "value-1.2");
        assertFalse(meta1.equals(meta2));
        meta2.add("name-one", "value-1.2");
        meta1.add("name-two", "value-2.1");
        assertFalse(meta1.equals(meta2));
        meta2.add("name-two", "value-2.1");
        meta1.add("name-two", "value-2.2");
        assertFalse(meta1.equals(meta2));
        meta2.add("name-two", "value-2.x");
        assertFalse(meta1.equals(meta2));
    }

}

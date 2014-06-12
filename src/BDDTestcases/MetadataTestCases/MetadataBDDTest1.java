package org.apache.tika.metadata;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MetadataBDDTest1 {

	private static final String CONTENTTYPE = "contenttype";
	
	/*
	 * Scenario: add item to Metadata
	 *	Given a item to be added,
	 *	When call the add function,
	 *	Then the item should be added to the Metadata.
	 */
    @Test
    public void addOperationShouldSucceedOnNormalItem() {
        String[] values = null;
        Metadata meta = new Metadata();

        values = meta.getValues(CONTENTTYPE);
        assertEquals(0, values.length);

        meta.add(CONTENTTYPE, "value1");
        values = meta.getValues(CONTENTTYPE);
        assertEquals(1, values.length);
        assertEquals("value1", values[0]);

        meta.add(CONTENTTYPE, "value2");
        values = meta.getValues(CONTENTTYPE);
        assertEquals(2, values.length);
        assertEquals("value1", values[0]);
        assertEquals("value2", values[1]);

        // NOTE : For now, the same value can be added many times.
        meta.add(CONTENTTYPE, "value1");
        values = meta.getValues(CONTENTTYPE);
        assertEquals(3, values.length);
        assertEquals("value1", values[0]);
        assertEquals("value2", values[1]);
        assertEquals("value1", values[2]);
    }
    
    /*
	 * Scenario: add non-multi valued item to Metadata
	 *	Given an non-multi valued item to be added,
	 *	When call the add function,
	 *	Then add should fail on the call of a non-multi valued item.
	 */
    @Test
    public void addOperationShouldFailOnNonMultiValuedItem() {
        Metadata meta = new Metadata();
        
        Property nonMultiValued = Property.internalText("nonMultiValued");
        meta.add(nonMultiValued, "value1");
        try {
            meta.add(nonMultiValued, "value2");
            fail("add should fail on the call of a non-multi valued item");
        } catch (PropertyTypeException e) {
        }
    }
    
    /*
	 * Scenario: set value of item in Metadata
	 *	Given an value to to be set,
	 *	When call the set function,
	 *	and there is not such item in Metadata
	 *	Then the item should be added to the Metadata.
	 */
    @Test
    public void setOperationShouldAddTheItemWithSetValueToMetadata() {
        String[] values = null;
        Metadata meta = new Metadata();

        values = meta.getValues(CONTENTTYPE);
        assertEquals(0, values.length);

        meta.set(CONTENTTYPE, "value1");
        values = meta.getValues(CONTENTTYPE);
        assertEquals(1, values.length);
        assertEquals("value1", values[0]);
    }
    
    /*
	 * Scenario: set value of item in Metadata
	 *	Given an value to to be set,
	 *	When call the set function,
	 *	and there is not such item in Metadata
	 *	Then the item should be added to the Metadata.
	 */
    @Test
    public void setOperationShouldSetTheItemInMetadataWithNewValue() {
        String[] values = null;
        Metadata meta = new Metadata();

        meta.set(CONTENTTYPE, "value1");

        meta.set(CONTENTTYPE, "value2");
        values = meta.getValues(CONTENTTYPE);
        assertEquals(1, values.length);
        assertEquals("value2", values[0]);

        meta.set(CONTENTTYPE, "new value 1");
        meta.add("contenttype", "new value 2");
        values = meta.getValues(CONTENTTYPE);
        assertEquals(2, values.length);
        assertEquals("new value 1", values[0]);
        assertEquals("new value 2", values[1]);
    }
    
    /*
	 * Scenario: get item in Metadata
	 *	Given a key to get the value,
	 *	When call the get function,
	 *	and there is not such item in Metadata
	 *	Then the function should return null.
	 */
    @Test
    public void getOperationShouldReturnNull() {
        Metadata meta = new Metadata();
        assertNull(meta.get("a-name"));
    }
    
    
    /*
	 * Scenario: get item in Metadata
	 *	Given a key to get the value,
	 *	When call the get function,
	 *	and there is such item in Metadata
	 *	Then the function should return the value whihc is associated with that key.
	 */
    @Test
    public void getOperationShouldReturnFirstValueWhichIsAssociatedWithGivenKey() {
        Metadata meta = new Metadata();
        meta.add("a-name", "value-1");
        assertEquals("value-1", meta.get("a-name"));
        meta.add("a-name", "value-2");
        assertEquals("value-1", meta.get("a-name"));
    }
    
}

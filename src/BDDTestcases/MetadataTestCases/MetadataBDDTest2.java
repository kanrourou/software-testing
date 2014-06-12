package org.apache.tika.metadata;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

public class MetadataBDDTest2 {


	
    /*
	 * Scenario: judge whether there are duplicated keys in Metadata
	 *	Given empty Metadata,
	 *	When call the isMultiValue function,
	 *	Then result should be false.
	 */
    @Test
    public void returnFalseWhenMetadataIsEmpty() {
        Metadata meta = new Metadata();
        assertFalse(meta.isMultiValued("key"));
    }
    
    /*
	 * Scenario: judge whether there are duplicated keys in Metadata
	 *	Given Metadata which contains unique key,
	 *	When call the isMultiValue function,
	 *	Then result should be false.
	 */
    @Test
    public void returnTrueWhenMetadataHasOnlyUniqueKey() {
        Metadata meta = new Metadata();
        meta.add("key", "value1");
        assertFalse(meta.isMultiValued("key"));
    }
    
    /*
	 * Scenario: judge whether there are duplicated keys in Metadata
	 *	Given Metadata which contains unique keys,
	 *	When call the isMultiValue function,
	 *	Then result should be false.
	 */
    @Test
    public void returnTrueWhenMetadataHasDuplicatedKeys(){
        Metadata meta = new Metadata();
        meta.add("key", "value1");
        assertFalse(meta.isMultiValued("key"));
        meta.add("key", "value2");
        assertTrue(meta.isMultiValued("key"));
    }
    
    /*
	 * Scenario: answer how many names there are in Metadata
	 *	Given empty Metadata,
	 *	When call the names function,
	 *	Then should return 0.
	 */
    @Test
    public void shouldReturnZeroWhenGivenEmptyMetadata() {
        String[] names = null;
        Metadata meta = new Metadata();
        names = meta.names();
        assertEquals(0, names.length);
    }
    
    /*
	 * Scenario: answer how many names there are in Metadata
	 *	Given Metadata which contains several items,
	 *	When adding item with new key,
	 *	Then number of names should increase by one.
	 */
    @Test
    public void numOfNamesShouldIncreaseByOneWhenAddingNewName() {
        String[] names = null;
        Metadata meta = new Metadata();
        names = meta.names();
        assertEquals(0, names.length);

        meta.add("name-one", "value");
        names = meta.names();
        assertEquals(1, names.length);
        assertEquals("name-one", names[0]);
        meta.add("name-two", "value");
        names = meta.names();
        assertEquals(2, names.length);
    }
    
    /*
	 * Scenario: answer how many names there are in Metadata
	 *	Given Metadata which contains several items,
	 *	When adding item with duplicated key,
	 *	Then number of names will not increase.
	 */
    @Test
    public void numOfNamesWillNotIncreaseWhenAddingDuplicatedName() {
        String[] names = null;
        Metadata meta = new Metadata();
        names = meta.names();
        assertEquals(0, names.length);

        meta.add("name-one", "value");
        names = meta.names();
        assertEquals(1, names.length);
        assertEquals("name-one", names[0]);
        meta.add("name-one", "value");
        names = meta.names();
        assertEquals(1, names.length);
    }

}

package twitter;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExtractTest2 {

    @BeforeClass
    // MyNote: 1-time executed only, before any test
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    // MyNote: 1-time executed only, after when was executed all tests
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    // MyNote: 1-time executed only, before test
    // each Unit Test should be be done independently form other Unit Test, and
    // create an Instance of  class should be done at @Before
    public void setUp() throws Exception {
    }
    
    @Test
    public void testGetTimespan() {
        fail("Not yet implemented");
    }
    
    @After
    // MyNote: 1-time executed only, after test
    public void tearDown() throws Exception {
    }

    
    /** -----------------------------------------------------------------*/
    @Test
    public void testGetMentionedUsers() {
        fail("Not yet implemented");
    }

}
 
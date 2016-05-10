package twitter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/*
 * TODO: your testing strategies for these methods should go here 
 */

public class FilterTest {

    private static Date d1;
    private static Date d2;
    
    private static Tweet tweet1;
    private static Tweet tweet2;
    
    private static List<Tweet> tlist;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        Calendar calendar = Calendar.getInstance();
        
        calendar.set(2014, 1, 14, 10, 00, 00);
        d1 = calendar.getTime();
        
        calendar.set(2014, 1, 14, 11, 00, 00);
        d2 = calendar.getTime();
        
        tweet1 = new Tweet(0, "alyssa", "is it reasonable to talk about rivest so much?", d1);
        tweet2 = new Tweet(1, "bbitdiddle", "so rivest talk in 30 minutes #hype", d2);
        
        // MyNote: Date and Calendar Classes generate diff timestamps for equal time!
        tlist = new ArrayList<Tweet>();
        tlist.add(new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 00)));
        tlist.add(new Tweet(2, "alyss", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 8)));
        tlist.add(new Tweet(3, "max5", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 25)));
        tlist.add(new Tweet(4, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 55))); 
    }
    
    /* 1st test suit------------------------------------------------------------------------------------------*/
    @Test
    public void testWrittenByMultipleTweetsSingleResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "alyssa");
        
        assertFalse(writtenBy.isEmpty());
        assertEquals(1, writtenBy.size());
        assertTrue(writtenBy.contains(tweet1));
    }
    
    @Test
    public void testWrittenByEmptyTweetListAndUserName(){
        List<Tweet> tlist_0 = new ArrayList<Tweet>();
        List<Tweet> writtenBy = Filter.writtenBy(tlist_0, "");
        
        assertTrue(writtenBy.isEmpty());
        assertEquals(0, writtenBy.size());
    }
    
    @Test
    public void testWrittenByEmptyTweetListAndUserNameNotEmpty(){
        List<Tweet> tlist_0 = new ArrayList<Tweet>();
        List<Tweet> writtenBy = Filter.writtenBy(tlist_0, "alyssa");
        
        assertTrue(writtenBy.isEmpty());
        assertEquals(0, writtenBy.size());
    }
    
    @Test
    public void testWrittenBySingletonTweetListAndEmptyUserName(){
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1), "");
        
        assertTrue(writtenBy.isEmpty());
        assertEquals(0, writtenBy.size());
    }
    
    @Test
    public void testWrittenBySingletonTweetListAndTweetOwner(){
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1), "alyssa");
        
        assertTrue(writtenBy.contains(tweet1));
        assertEquals(1, writtenBy.size());
    }
    
    @Test
    public void testWrittenBySingletonTweetListAndNotTweetOwner(){
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1), "notTweetOwner");
        
        assertTrue(writtenBy.isEmpty());
        assertEquals(0, writtenBy.size());
    }
    
    @Test
    public void testWrittenByMultipleTweetsAndUppercaseTweetOwner(){
        List<Tweet> writtenBy = Filter.writtenBy(tlist, "alyssa".toUpperCase());
        
        assertFalse(writtenBy.isEmpty());
        assertTrue(writtenBy.get(0).getAuthor().equals("alyssa"));
        assertTrue(writtenBy.get(1).getAuthor().equals("alyssa"));
        assertEquals(2, writtenBy.size());
        assertEquals(4, tlist.size());  // not modified list check
    }
    
    @Test
    public void testWrittenByMultipleTweetsAndLowercaseTweetOwner(){
        List<Tweet> writtenBy = Filter.writtenBy(tlist, "alyssa");
        
        assertFalse(writtenBy.isEmpty());
        assertTrue(writtenBy.get(0).getAuthor().equals("alyssa"));
        assertTrue(writtenBy.get(1).getAuthor().equals("alyssa"));
        assertEquals(2, writtenBy.size());
        assertEquals(4, tlist.size());  // not modified list check
    }
    
    @Test
    public void testWrittenByMultipleTweetsAndMixedcaseTweetOwner(){
        List<Tweet> writtenBy = Filter.writtenBy(tlist, "AlYSsa");
        
        assertFalse(writtenBy.isEmpty());
        assertTrue(writtenBy.get(0).getAuthor().equals("alyssa"));
        assertTrue(writtenBy.get(1).getAuthor().equals("alyssa"));
        assertEquals(2, writtenBy.size());
        assertEquals(4, tlist.size());  // not modified list check
    }
    
    @Test
    public void testWrittenByMultipleTweetsAndEmptyStrUserName(){
        List<Tweet> writtenBy = Filter.writtenBy(tlist, "");
        
        assertTrue(writtenBy.isEmpty());        
        assertEquals(0, writtenBy.size());
        assertEquals(4, tlist.size());  // not modified list check
    }
    
    @Test
    public void testWrittenByMultipleTweetsAndExistingTweetOwnerMultipleResults(){
        List<Tweet> writtenBy = Filter.writtenBy(tlist, "alyssa");
        
        assertFalse(writtenBy.isEmpty());
        assertTrue(writtenBy.get(0).getAuthor().equals("alyssa"));
        assertTrue(writtenBy.get(1).getAuthor().equals("alyssa"));
        assertEquals(2, writtenBy.size());
        assertEquals(4, tlist.size());  // not modified list check
    }
    
    @Test
    public void testWrittenByMultipleTweetsAndNotExistingTweetOwner(){
        List<Tweet> writtenBy = Filter.writtenBy(tlist, "notExistingOwner");
        
        assertTrue(writtenBy.isEmpty());        
        assertEquals(0, writtenBy.size());
        assertEquals(4, tlist.size());  // not modified list check
    }
    
    /* 2nd test suit------------------------------------------------------------------------------------------*/
    @Test
    public void testInTimespanEmptyTweetListAndZeroIntervalTimespan() {
        List<Tweet> tlist_0 = new ArrayList<Tweet>();        
        List<Tweet> inTimespan = Filter.inTimespan(tlist_0, new Timespan(d1, d1));
        
        assertTrue(inTimespan.isEmpty());
        assertEquals(0, inTimespan.size());
       
    }
    
    @Test
    public void testInTimespanEmptyTweetListAndNotZeroIntervalTimespan() {
        List<Tweet> tlist_0 = new ArrayList<Tweet>();        
        List<Tweet> inTimespan = Filter.inTimespan(tlist_0, new Timespan(d1, d2));
        
        assertTrue(inTimespan.isEmpty());
        assertEquals(0, inTimespan.size());
    }
    
    @Test
    public void testInTimespanMultipleTweetsAndZeroIntervalTimespan() {
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(d1, d1));
        
        assertFalse(inTimespan.isEmpty());
        assertEquals(1, inTimespan.size());
        assertEquals("alyssa",inTimespan.get(0).getAuthor());
    }
    
    @Test
    public void testInTimespanMultipleTweetsNoResults() {
        Calendar calendar = Calendar.getInstance();
        
        calendar.set(2014, 1, 14, 6, 00, 00);
        Date testDateStart = calendar.getTime();
        calendar.set(2014, 1, 14, 7, 00, 00);
        Date testDateEnd = calendar.getTime();
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testDateStart, testDateEnd));
        assertEquals(0, inTimespan.size());
        assertTrue(inTimespan.isEmpty());
    }
    
    @Test
    public void testInTimespanMultipleTweetsOneResult() {
        Calendar calendar = Calendar.getInstance();
        
        calendar.set(2014, 1, 14, 9, 00, 00);
        Date testDateStart = calendar.getTime();
        calendar.set(2014, 1, 14, 10, 00, 00);
        Date testDateEnd = calendar.getTime();
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testDateStart, testDateEnd));
        
        assertFalse(inTimespan.isEmpty());
        assertEquals(1, inTimespan.size());
        assertEquals("alyssa",inTimespan.get(0).getAuthor());
    }
    
    
    
    @Test
    public void testInTimespanMultipleTweetsMultipleResults() {
        Calendar calendar = Calendar.getInstance();
        
        calendar.set(2014, 1, 14, 9, 00, 00);
        Date testDateStart = calendar.getTime();
        calendar.set(2014, 1, 14, 12, 00, 00);
        Date testDateEnd = calendar.getTime();
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testDateStart, testDateEnd));
        
        assertFalse(inTimespan.isEmpty());
        assertTrue(inTimespan.containsAll(Arrays.asList(tweet1, tweet2)));
    }
    
    /* 3rd test suit------------------------------------------------------------------------------------------*/
    @Test
    public void testContaining() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("talk"));
        
        assertFalse(containing.isEmpty());
        assertTrue(containing.containsAll(Arrays.asList(tweet1, tweet2)));
    }
    
    @Test
    public void testContainingEmptyTweetListAndEmptyWordList() { // --> []
        List<Tweet> tlist_0 = new ArrayList<Tweet>();
        List<Tweet> containing = Filter.containing(tlist_0, new ArrayList<String>());
        assertTrue(containing.isEmpty());
        
        
    }
    
    @Test
    public void testContainingSingletonTweetListAndEmptyWordList() { // --> []
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1), new ArrayList<String>());
        
        assertTrue(containing.isEmpty());
        assertEquals(0, containing.size());
        
    }
    
    @Test
    public void testContainingEmptyTweetListAndSigletonWordList() { // --> []
        List<Tweet> containing = Filter.containing(new ArrayList<Tweet>(), Arrays.asList("talk"));
        
        assertTrue(containing.isEmpty());
        assertEquals(0, containing.size());
        
    }
    
    @Test
    public void testContainingSingletonTweetListAndSingletonWordListOneMatch() { // --> [1,]
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1), Arrays.asList("talk"));
        
        assertFalse(containing.isEmpty());
        assertTrue(containing.containsAll(Arrays.asList(tweet1)));
    }
    
    @Test
    public void testContainingSingletonTweetListAndMultipleWordListOneWordMatch() { // --> []
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1), Arrays.asList("talk", "boom", "doom"));        
        assertTrue(containing.isEmpty());        
    }
    
    @Test
    public void testContainingMultipleTweetListAndMultipleWordListFullWordsMatch() { // --> [1, ...]
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("talk", "so"));
        
        assertFalse(containing.isEmpty());
        assertTrue(containing.containsAll(Arrays.asList(tweet1, tweet2)));  
    }
    
    @Test
    public void testContainingMultipleTweetListAndMultipleWordListNoWordsMatch() { // --> []
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("bee", "dee", "see"));
        
        assertTrue(containing.isEmpty());        
    }
}

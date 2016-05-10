package twitter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

/*
 * TODO: your testing strategies for these methods should go here
 */

public class ExtractTest {
    
    private static Date d1;
    private static Date d2;
    private static long dtest;
    
    private static Tweet tweet1;
    private static Tweet tweet2;
        
    @BeforeClass
    public static void setUpBeforeClass() {
        Calendar calendar = Calendar.getInstance();
        
        calendar.set(2014, 1, 14, 10, 00, 00);
        d1 = calendar.getTime();        
        calendar.set(2014, 1, 14, 11, 00, 00);
        d2 = calendar.getTime();
               
        tweet1 = new Tweet(0, "alyssa", "is it reasonable to talk about rivest so much?", d1);
        tweet2 = new Tweet(1, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
        
        //MyNote: here a minimum-length time interval will be 1 h.
        //System.out.println((d1.getTime()- d2.getTime())/1000); // 60min
       
    }
    
    @Test
    public void testGetTimespanTweetListEmpty(){
        List<Tweet> tlist_0 = new ArrayList<Tweet>();
        Timespan ts = Extract.getTimespan(tlist_0);        
        assertEquals(0, ts.getStart().getTime() - ts.getEnd().getTime());
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
        
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals(d1, timespan.getStart());
        assertEquals(d2, timespan.getEnd());
    }
    
    @Test
    public void testGetTimespanMaxINT(){
        Calendar calendar = Calendar.getInstance();
        Date d1,d2,d3,d4;
        List<Tweet> tlist_maxint = new ArrayList<Tweet>();
        calendar.setTimeInMillis(Integer.MAX_VALUE - 9000); d1 = calendar.getTime();
        tlist_maxint.add(new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1));
        calendar.setTimeInMillis(Integer.MAX_VALUE - 4000); d2 = calendar.getTime();
        tlist_maxint.add(new Tweet(2, "alyssa", "is it reasonable to talk about rivest so much?", d2));
        calendar.setTimeInMillis(Integer.MAX_VALUE - 1000); d3 = calendar.getTime();
        tlist_maxint.add(new Tweet(3, "alyssa", "is it reasonable to talk about rivest so much?", d3));
        calendar.setTimeInMillis(Integer.MAX_VALUE); d4 = calendar.getTime();
        tlist_maxint.add(new Tweet(4, "alyssa", "is it reasonable to talk about rivest so much?", d4));
        
        Timespan timespan = Extract.getTimespan(tlist_maxint);
        assertEquals(d3.getTime(), timespan.getStart().getTime());
        assertEquals(d4.getTime(), timespan.getEnd().getTime());
       
        //System.out.println(new Date(Integer.MAX_VALUE)); // Sun Jan 25 21:31:23 GMT 1970
        //System.out.println(new Date(Integer.MAX_VALUE - 1000)); // Sun Jan 25 21:31:22 GMT 1970        
        //System.out.println(calendar.getTime());
        
    }
    
    
    @Test
    public void testGetTimespanTweetListdBegining(){
        List<Tweet> tlist = new ArrayList<Tweet>();
        tlist.add(new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2015, 4, 22, 9, 0, 6)));
        tlist.add(new Tweet(2, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2015, 4, 22, 9, 0, 8)));
        tlist.add(new Tweet(3, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2015, 4, 22, 9, 0, 25)));
        tlist.add(new Tweet(4, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2015, 4, 22, 9, 0, 55))); 
        
        Timespan timespan = Extract.getTimespan(tlist);
        assertEquals(new Date(2015, 4, 22, 9, 0, 6).getTime(), timespan.getStart().getTime());
        assertEquals(new Date(2015, 4, 22, 9, 0, 8).getTime(), timespan.getEnd().getTime());
        
        
    }
    
    @Test
    public void testGetTimespanTweetListdMeadle(){
        List<Tweet> tlist = new ArrayList<Tweet>();
        tlist.add(new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2015, 4, 22, 9, 0, 6)));
        tlist.add(new Tweet(2, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2015, 4, 22, 9, 0, 15)));
        tlist.add(new Tweet(3, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2015, 4, 22, 9, 0, 16)));
        tlist.add(new Tweet(4, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2015, 4, 22, 9, 0, 55))); 
        
        Timespan timespan = Extract.getTimespan(tlist);
        assertEquals(new Date(2015, 4, 22, 9, 0, 15).getTime(), timespan.getStart().getTime());
        assertEquals(new Date(2015, 4, 22, 9, 0, 16).getTime(), timespan.getEnd().getTime());
    }
    
    @Test
    public void testGetTimespanTweetListdEnd(){
        List<Tweet> tlist = new ArrayList<Tweet>();
        tlist.add(new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2015, 4, 22, 9, 0, 6)));
        tlist.add(new Tweet(2, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2015, 4, 22, 9, 0, 15)));
        tlist.add(new Tweet(3, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2015, 4, 22, 9, 0, 25)));
        tlist.add(new Tweet(4, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2015, 4, 22, 9, 0, 26))); 
        
        Timespan timespan = Extract.getTimespan(tlist);
        assertEquals(new Date(2015, 4, 22, 9, 0, 25).getTime(), timespan.getStart().getTime());
        assertEquals(new Date(2015, 4, 22, 9, 0, 26).getTime(), timespan.getEnd().getTime());
    }
    
    
    /**-------------------------------------------------------------------------------**/
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2));
        
        assertTrue(mentionedUsers.isEmpty());
    }

    @Test
    public void testGetMentionedUsersOneMention() {
        List<Tweet> tlist = new ArrayList<Tweet>();
        tlist.add(new Tweet(1, "alyssa", "is it reasonable to talk @tweetuser. about rivest so much?", new Date(2015, 4, 22, 9, 0, 6)));
        Set<String> mentionedUsers = Extract.getMentionedUsers(tlist); 
        assertTrue("Fail to get tweet mentioner", mentionedUsers.contains("@tweetuser"));
        
    }
    
    @Test
    public void testGetMentionedUsersMulltipleMention() {
        List<Tweet> tlist = new ArrayList<Tweet>();
        tlist.add(new Tweet(1, "alyssa", "is it reasonable to talk @tweetuser. about rivest so much?", new Date(2015, 4, 22, 9, 0, 6)));
        tlist.add(new Tweet(2, "alyssa", "is it reasonable to talk @about rivest so much?", new Date(2015, 4, 22, 9, 0, 15)));
        tlist.add(new Tweet(3, "alyssa", "is it reasonable to talk about @rivest so much?", new Date(2015, 4, 22, 9, 0, 25)));
        tlist.add(new Tweet(4, "alyssa", "is it reasonable to talk about rivest @so much?", new Date(2015, 4, 22, 9, 0, 26))); 
        Set<String> mentionedUsers = Extract.getMentionedUsers(tlist); 
        assertTrue("Fail to get tweet mentioner", mentionedUsers.contains("@tweetuser"));
        assertTrue("Fail to get tweet mentioner", mentionedUsers.contains("@about"));
        assertTrue("Fail to get tweet mentioner", mentionedUsers.contains("@rivest"));
        assertTrue("Fail to get tweet mentioner", mentionedUsers.contains("@so"));
    }
    
    @Test
    public void testGetMentionedUsersReapitedNames() {
        List<Tweet> tlist = new ArrayList<Tweet>();
        tlist.add(new Tweet(1, "alyssa", "is it reasonable to talk tweetuser. about rivest so much?", new Date(2015, 4, 22, 9, 0, 6)));
        tlist.add(new Tweet(2, "alyssa", "is it reasonable to talk about @rivest so @rivest much?", new Date(2015, 4, 22, 9, 0, 15)));
        tlist.add(new Tweet(3, "alyssa", "is it reasonable to talk about @rivest so much?", new Date(2015, 4, 22, 9, 0, 25)));
         
        Set<String> mentionedUsers = Extract.getMentionedUsers(tlist); 
        assertEquals(1, mentionedUsers.size());
        
    }
    
    @Test
    public void testGetMentionedUsersValidNames() {
        List<Tweet> tlist = new ArrayList<Tweet>();
        tlist.add(new Tweet(1, "alyssa", "is it reasonable to talk @tweetuser. about rivest so much?", new Date(2015, 4, 22, 9, 0, 6)));
        tlist.add(new Tweet(2, "alyssa", "is it reasonable to talk abo@ut rivest so much?", new Date(2015, 4, 22, 9, 0, 15)));
        tlist.add(new Tweet(3, "alyssa", "is it reasonable to talk about @riv-est so much?", new Date(2015, 4, 22, 9, 0, 25)));
        tlist.add(new Tweet(4, "alyssa", "is it reasonable to talk about rivest @so123 much?", new Date(2015, 4, 22, 9, 0, 26))); 
        Set<String> mentionedUsers = Extract.getMentionedUsers(tlist); 
        assertTrue("Fail to get tweet mentioner", mentionedUsers.contains("@tweetuser"));
        assertFalse("Fail to get tweet mentioner", mentionedUsers.contains("abo@ut"));
        assertFalse("Fail to get tweet mentioner", mentionedUsers.contains("@riv-est"));
        assertTrue("Fail to get tweet mentioner", mentionedUsers.contains("@so123"));
    }
    
    @Test
    public void testGetMentionedUsersCaseInsensitive() {
        System.out.println("testGetMentionedUsersCaseInsensitive");
        List<Tweet> tlist = new ArrayList<Tweet>();
        tlist.add(new Tweet(1, "alyssa", "is it reasonable to @tALk  about rivest so much?", new Date(2015, 4, 22, 9, 0, 6)));
        tlist.add(new Tweet(2, "alyssa", "is it reasonable to @TALK  rivest so much?", new Date(2015, 4, 22, 9, 0, 15)));
        Set<String> mentionedUsers = Extract.getMentionedUsers(tlist); 
        assertTrue("Fail to get tweet mentioner", mentionedUsers.contains("@talk"));
    }
    
}

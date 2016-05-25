package twitter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

/*
 * TODO: your testing strategies for these methods should go here
 */

public class SocialNetworkTest {
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
        tlist.add(new Tweet(2, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 8)));
        tlist.add(new Tweet(3, "denis", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 25)));
        tlist.add(new Tweet(4, "ana", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 55)));
        
        tlist.add(new Tweet(5, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 2)));
        tlist.add(new Tweet(6, "denis", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 18)));
        tlist.add(new Tweet(7, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 23)));
        tlist.add(new Tweet(8, "ana", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 50)));
        
        tlist.add(new Tweet(9, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 01)));
        tlist.add(new Tweet(10, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 3)));
        tlist.add(new Tweet(11, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 14)));
        tlist.add(new Tweet(12, "denis", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 35))); 
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() { // --> {[] -->[]}
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<Tweet>());
        
        assertTrue(followsGraph.isEmpty());
    }   
   
    @Test
    public void testGuessFollowsGraphMemberFollowsNobody() { // --> {[1,] -->[]}
        List<Tweet> tlist2 = new ArrayList<Tweet>();
        tlist2.add(new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 00)));
        tlist2.add(new Tweet(2, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 8)));
        tlist2.add(new Tweet(3, "denis", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 25)));
        tlist2.add(new Tweet(4, "ana", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 55)));
        
        tlist2.add(new Tweet(5, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 2)));
        tlist2.add(new Tweet(6, "denis", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 18)));
        tlist2.add(new Tweet(7, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 23)));
        tlist2.add(new Tweet(8, "ana", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 50)));
        
        tlist2.add(new Tweet(9, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 01)));
        tlist2.add(new Tweet(10, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 3)));
        tlist2.add(new Tweet(11, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 14)));
        tlist2.add(new Tweet(12, "denis", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 35))); 
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tlist2);
        
        assertEquals(4, followsGraph.size());
        assertTrue(followsGraph.get("alyssa").isEmpty());
        assertTrue(followsGraph.get("denis").isEmpty());
        assertTrue(followsGraph.get("max").isEmpty());
        assertTrue(followsGraph.get("ana").isEmpty());
    }
    
    @Test
    public void testGuessFollowsGraphMemberFollowsOnePerson() { // --> {[1,] -->[1,]} ,others keys mapped to [] set
        tlist = new ArrayList<Tweet>();
        tlist.add(new Tweet(1, "alyssa", "is it reasonable to @denis about rivest so much?", new Date(2014, 1, 14, 10, 00, 00)));
        tlist.add(new Tweet(2, "max", "is it reasonable to talk @alyssa about rivest so much?", new Date(2014, 1, 14, 10, 00, 8)));
        tlist.add(new Tweet(3, "denis", "is it reasonable to talk @max about rivest so much?", new Date(2014, 1, 14, 10, 00, 25)));
        tlist.add(new Tweet(4, "ana", "is it reasonable to talk about @denis rivest so much?", new Date(2014, 1, 14, 10, 00, 55)));
        
        tlist.add(new Tweet(5, "alyssa", "is it reasonable to talk @denis about rivest so much?", new Date(2014, 1, 14, 11, 00, 2)));
        tlist.add(new Tweet(6, "denis", "is it reasonable to talk about @max rivest so much?", new Date(2014, 1, 14, 11, 00, 18)));
        tlist.add(new Tweet(7, "max", "is it reasonable to talk about rivest @alyssa so much?", new Date(2014, 1, 14, 11, 00, 23)));
        tlist.add(new Tweet(8, "ana", "is it reasonable to talk about rivest so much? @denis", new Date(2014, 1, 14, 11, 00, 50)));
        
        tlist.add(new Tweet(9, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 01)));
        tlist.add(new Tweet(10, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 3)));
        tlist.add(new Tweet(11, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 14)));
        tlist.add(new Tweet(12, "denis", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 35))); 
        
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tlist);
        
        assertEquals(4, followsGraph.size());
        assertEquals(1, followsGraph.get("alyssa").size());
        assertEquals(1, followsGraph.get("denis").size());
        assertEquals(1, followsGraph.get("max").size());
        assertEquals(1, followsGraph.get("ana").size());
    }
    
    @Test
    public void testGuessFollowsGraphMemberFollowsMultiplePersons() { // --> {[1,...] -->[1,...]} ,others keys mapped to [] set
        tlist = new ArrayList<Tweet>();
        tlist.add(new Tweet(1, "alyssa", "is it reasonable to @denis about rivest so much?", new Date(2014, 1, 14, 10, 00, 00)));
        tlist.add(new Tweet(2, "max", "is it reasonable to talk @allysa about rivest so much?", new Date(2014, 1, 14, 10, 00, 8)));
        tlist.add(new Tweet(3, "denis", "is it reasonable to talk @max about rivest so much?", new Date(2014, 1, 14, 10, 00, 25)));
        tlist.add(new Tweet(4, "ana", "is it reasonable to talk about @denis rivest so much?", new Date(2014, 1, 14, 10, 00, 55)));
        
        tlist.add(new Tweet(5, "alyssa", "is it reasonable to talk @max about rivest so much?", new Date(2014, 1, 14, 11, 00, 2)));
        tlist.add(new Tweet(6, "denis", "is it reasonable to talk @ana @alyssa about rivest so much?", new Date(2014, 1, 14, 11, 00, 18)));
        tlist.add(new Tweet(7, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 23)));
        tlist.add(new Tweet(8, "ana", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 50)));
        
        tlist.add(new Tweet(9, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 01)));
        tlist.add(new Tweet(10, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 3)));
        tlist.add(new Tweet(11, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 14)));
        tlist.add(new Tweet(12, "denis", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 35))); 
        
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tlist);
        
        assertEquals(4, followsGraph.size());
        assertEquals(2, followsGraph.get("alyssa").size());
        assertEquals(3, followsGraph.get("denis").size());
        assertEquals(1, followsGraph.get("max").size());
        assertEquals(1, followsGraph.get("ana").size());
    }
    
    @Test
    public void testGuessFollowsGraphUniqueUsernameCheck() {
        // this test is not necessary as in this case Map keys is mapped to Sets, so if found key the new
        // value will union to old key values. No to equal keys with diff values are possible by code design.
    }
    
    
    
    /* 2nd test suit------------------------------------------------------------------------------------------*/
    @Test
    public void testInfluencersEmptySocialNetwork() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<Tweet>());
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue(influencers.isEmpty());
        
    }
    
    @Test
    public void testInfluencersActiveNetworkAndNoFollowers() { // --> []
        List<Tweet> tlist2 = new ArrayList<Tweet>();
        tlist2.add(new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 00)));
        tlist2.add(new Tweet(2, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 8)));
        tlist2.add(new Tweet(3, "denis", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 25)));
        tlist2.add(new Tweet(4, "ana", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 10, 00, 55)));
        
        tlist2.add(new Tweet(5, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 2)));
        tlist2.add(new Tweet(6, "denis", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 18)));
        tlist2.add(new Tweet(7, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 23)));
        tlist2.add(new Tweet(8, "ana", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 50)));
        
        tlist2.add(new Tweet(9, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 01)));
        tlist2.add(new Tweet(10, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 3)));
        tlist2.add(new Tweet(11, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 14)));
        tlist2.add(new Tweet(12, "denis", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 35))); 
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tlist2);        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        System.out.println("influncers size test: "+influencers.size());
        assertTrue(influencers.isEmpty());
        assertEquals(0, followsGraph.get("alyssa").size());
        assertEquals(0, followsGraph.get("denis").size());
        assertEquals(0, followsGraph.get("max").size());
        assertEquals(0, followsGraph.get("ana").size());
    }
    
    @Test
    public void testInfluencersDesendingOrderCheck() {
        tlist = new ArrayList<Tweet>();
        tlist.add(new Tweet(1, "alyssa", "is it reasonable to     @denis about rivest so much?", new Date(2014, 1, 14, 10, 00, 00)));
        tlist.add(new Tweet(2, "max", "is it reasonable to talk   @alyssa about rivest so much?", new Date(2014, 1, 14, 10, 00, 8)));
        tlist.add(new Tweet(3, "denis", "is it reasonable to talk @max about rivest so much?", new Date(2014, 1, 14, 10, 00, 25)));
        tlist.add(new Tweet(4, "ana", "is it reasonable to talk a @denis rivest so much?", new Date(2014, 1, 14, 10, 00, 55)));
        
        tlist.add(new Tweet(5, "alyssa", "is it reasonable to tal @max about rivest so much?", new Date(2014, 1, 14, 11, 00, 2)));
        tlist.add(new Tweet(6, "denis", "is it reasonable to talk @ana @alyssa about rivest so much?", new Date(2014, 1, 14, 11, 00, 18)));
        tlist.add(new Tweet(7, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 23)));
        tlist.add(new Tweet(8, "ana", "is it reasonable to talk a @max rivest so much?", new Date(2014, 1, 14, 11, 00, 50)));
        
        tlist.add(new Tweet(9, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 01)));
        tlist.add(new Tweet(10, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 3)));
        tlist.add(new Tweet(11, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 14)));
        tlist.add(new Tweet(12, "denis", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 35))); 
        
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tlist);        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        // Members follow others counter
        assertEquals(4, followsGraph.size());
        assertEquals(2, followsGraph.get("alyssa").size());
        assertEquals(3, followsGraph.get("denis").size());
        assertEquals(1, followsGraph.get("max").size());
        assertEquals(2, followsGraph.get("ana").size());
        
        // Members influence list in descending order
        assertEquals("max",influencers.get(0));
        assertEquals("alyssa",influencers.get(1));
        assertEquals("denis",influencers.get(2));
        assertEquals("ana",influencers.get(3));
    }
    
    @Test
    public void testInfluencersDistinctUsernamesCheck() {
        // by code design is impossible to have two or more equal usernames in outcome list
        // but here is checking for 'some' case
        tlist = new ArrayList<Tweet>();
        tlist.add(new Tweet(1, "alyssa", "is it reasonable to     @denis about rivest so much?", new Date(2014, 1, 14, 10, 00, 00)));
        tlist.add(new Tweet(2, "max", "is it reasonable to talk   @alyssa about rivest so much?", new Date(2014, 1, 14, 10, 00, 8)));
        tlist.add(new Tweet(3, "denis", "is it reasonable to talk @max about rivest so much?", new Date(2014, 1, 14, 10, 00, 25)));
        tlist.add(new Tweet(4, "ana", "is it reasonable to talk a @denis rivest so much?", new Date(2014, 1, 14, 10, 00, 55)));
        
        tlist.add(new Tweet(5, "alyssa", "is it reasonable to tal @max about rivest so much?", new Date(2014, 1, 14, 11, 00, 2)));
        tlist.add(new Tweet(6, "denis", "is it reasonable to talk @ana @alyssa about rivest so much?", new Date(2014, 1, 14, 11, 00, 18)));
        tlist.add(new Tweet(7, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 11, 00, 23)));
        tlist.add(new Tweet(8, "ana", "is it reasonable to talk a @max rivest so much?", new Date(2014, 1, 14, 11, 00, 50)));
        
        tlist.add(new Tweet(9, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 01)));
        tlist.add(new Tweet(10, "alyssa", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 3)));
        tlist.add(new Tweet(11, "max", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 14)));
        tlist.add(new Tweet(12, "denis", "is it reasonable to talk about rivest so much?", new Date(2014, 1, 14, 12, 00, 35))); 
        
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tlist);        
        List<String> influenceList = SocialNetwork.influencers(followsGraph);
        int counter = 0;
        for(String member: influenceList){
            System.out.println(member+"---------");
            for(String member2: influenceList){
                System.out.println(member2+"--");
                if(member.equals(member2)) counter++;                
            }
            assertEquals(1, counter);
            counter = 0;
        }
    }

}

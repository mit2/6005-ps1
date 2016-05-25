package twitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames.  Users can't follow themselves.
 * If A doesn't follow anybody, then map[A] may be undefined or map[A] may be the
 * empty set; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".  A
 * username should appear at most once as a key in the map or in any given map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but you should implement their
 * method bodies, and you may add new public or private methods or classes if you like.
 * 
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets. One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         
     *         All the Twitter usernames in the returned social network must be 
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(final List<Tweet> tweets) {
        //throw new RuntimeException("not implemented");
        Map<String, Set<String>> snet = new HashMap<String, Set<String>>();
        String member;
        Set<String> personsToFollow;
        
        if(tweets.isEmpty()){
            return snet;
        }else{
            for(Tweet tweet: tweets){
                member = tweet.getAuthor();
                personsToFollow = Extract.getMentionedUsers(Arrays.asList(tweet));
                if(snet.containsKey(member)) snet.get(member).addAll(personsToFollow);
                else snet.put(member, personsToFollow);
                
            }
            
            return snet;
        }
        
       
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     * 
     */
    public static List<String> influencers(final Map<String, Set<String>> followsGraph) {
        //throw new RuntimeException("not implemented");
        // I've choosen MY simpliest approach to sort map values by extracting map values to list and using helper class UserFollows.
        // On 'stackoverflow' was so many complicated ways..!
       
        List<UserFollows> mostFollowed = new ArrayList<UserFollows>();
        List<String> greatestInflunceList = new ArrayList<String>();        
        Map<String, Integer> countMentions = new HashMap<String, Integer>();
        SocialNetwork snet = new SocialNetwork(); // instanceof SocialNetwork needed for using inner Class
                       
        if(followsGraph.isEmpty()){
            return greatestInflunceList;
        }else{       
            for(String member: followsGraph.keySet()){ // iterate thru usernames in network
                for(String personToFollow :followsGraph.get(member)){ // iterate thru set of followers witch user follows                   
                    if(countMentions.containsKey(personToFollow)){                       
                        countMentions.put(personToFollow, countMentions.get(personToFollow) + 1); // create map witch map users to max time they mentioned in tweets
                    }
                    else
                        countMentions.put(personToFollow, 1);
                }
                
            }
            
            // create mostFollowed list
            for(String member: countMentions.keySet())
                mostFollowed.add(snet.new UserFollows(member, countMentions.get(member)));
            
            // Sort and Reverse order for most Followed
            Collections.sort(mostFollowed, snet.new UserComparator());
            Collections.reverse(mostFollowed); 
            
            for(UserFollows member: mostFollowed) System.out.println(member.username + "-->" + member.numFollowers);
            for(UserFollows member: mostFollowed) greatestInflunceList.add(member.username.substring(1));
            
            return greatestInflunceList;
        }
    }
    
    
    /**
     * UserFollows Helper Class will be used in influencers() for comparing users in a list.
     * @author MyCode
     *
     */
    private class UserFollows{
        private String username;
        private int numFollowers;
        
        public UserFollows(String username, int followers){
            this.username = username;
            numFollowers = followers;
        }        
    }
    
    private class UserComparator implements Comparator {       
        @Override
        public int compare(Object o1, Object o2) {
            UserFollows u1 = (UserFollows)o1;
            UserFollows u2 = (UserFollows)o2;
            if(u1.numFollowers == u2.numFollowers) return 0;
            else if(u1.numFollowers > u2.numFollowers) return 1;
            else return -1;
        }
    }
}


package twitter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import junit.framework.Assert;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but you should implement their
 * method bodies, and you may add new public or private methods or classes if you like.
 * 
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(final List<Tweet> tweets) {
        //throw new RuntimeException("not implemented");
        //Assert.assertNotNull(tweets); // null refer-ces is automatically checked at compile time
        //if(tweets.isEmpty())throw new AssertionError(); // Java API classes, for example, 
                                                        // invariably specify (as a postcondition) that they throw unchecked
                                                        // exceptions when arguments are inappropriate.
                                                        // if(tweets.isEmpty())throw new NullPointerException();
        long interval = 0, minInterval = 0;
        Timespan span = null;
        
        if(tweets.isEmpty()){
            return new Timespan( new Date(1000000), new Date(1000000)); // basic init date to handle empty tweet list case
        }else{
            for(int i = 0; i < tweets.size(); i++){
                if((i + 1) < tweets.size()){
                    interval = Math.abs(tweets.get(i).getTimestamp().getTime() - tweets.get(i + 1).getTimestamp().getTime());
                    if(i == 0){
                        minInterval = interval;  // setup minInterval in 1st iteration.
                        span = new Timespan(tweets.get(i).getTimestamp(), tweets.get(i + 1).getTimestamp());    // set span for twoItemTweetLIst
                    }
                    if(interval < minInterval){                   
                        minInterval = interval;
                        span = new Timespan(tweets.get(i).getTimestamp(), tweets.get(i + 1).getTimestamp());
                    }
                    
                }
                
            }
        }
        return span;
    }

    /**
     * Get usernames mentioned in a tweet.
     * 
     * @param tweets
     *            list of tweets, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweet.
     *         A username-mention is "@" followed by a username. A username
     *         consists of letters (A-Z or a-z), digits, and underscores ("_").
     *         Twitter usernames are case-insensitive, so "rbmllr" and "RbMllr"
     *         are equivalent.  A username may occur at most once in the returned 
     *         set.
     * 
     *         The @ cannot be immediately preceded by an alphanumeric or
     *         underscore character (A-Z, a-z, 0-9, _). For example, an email
     *         address like bitdiddle@mit.edu does not contain a mention of mit.
     *         
     *         MyNotes: in Tweeter username starts as @usrname
     *         In username @ cannot be immediately preceded by an alphanumeric or
     *         underscore character (A-Z, a-z, 0-9, _). For example, an email
     *         address like bitdiddle@mit.edu does not contain a mention of mit.
     */
    public static Set<String> getMentionedUsers(final List<Tweet> tweets) {
        //throw new RuntimeException("not implemented");
        
        Set<String> setOfUsers = new HashSet<String>();
        String token;
        for(int i = 0; i < tweets.size(); i++){
            StringTokenizer st = new StringTokenizer(tweets.get(i).getText());
            while (st.hasMoreTokens()) {
                token = st.nextToken();
                //System.out.println(token);
                if (token.endsWith(".")) token = new String(token.substring(0, token.length()- 1));
                if(token.matches("^[@][a-zA-Z_0-9]*")) setOfUsers.add(token.toLowerCase());
            }         
        }
        
        return setOfUsers;
    }

}

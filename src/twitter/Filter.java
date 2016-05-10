package twitter;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

/**
 * Filter consists of methods that filter a list of tweets for those matching a condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but you should implement their
 * method bodies, and you may add new public or private methods or classes if you like.
 * 
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets, not modified by this method.
     * @param username
     *            Twitter username
     * @return all tweets in the list whose author is username. Twitter
     *         usernames are case-insensitive, so "rbmllr" and "RbMllr" are
     *         equivalent.
     */
    public static List<Tweet> writtenBy(final List<Tweet> tweets, final String username) {
        //throw new RuntimeException("not implemented");
        
        // WORNING! 'Assert' only error conditions NOT specified by specs, as 'wrong' func input params.
        //           Any error 'within' the specs should be covered buy tests. 
        //assert(tweets.isEmpty() != true): "tweet list is empty!"; // Java assertion only for expensive assertions!
        //if(tweets.isEmpty()|| username.isEmpty())throw new AssertionError();
        
        List<Tweet> matchedTweets = new ArrayList<Tweet>();
        Assert.assertNotNull(tweets); // Constant-time checks should be always like this
        Assert.assertNotNull(username); 
        
        if((tweets.isEmpty() && username.isEmpty()) || tweets.isEmpty() || username.isEmpty()){
            return matchedTweets;
        }
        
        for(Tweet tweet: tweets){
            if(tweet.getAuthor().equals(username.toLowerCase())) matchedTweets.add(tweet);            
        }

        return matchedTweets;
    }

    /**
     * Find tweets that were sent during a particular timespan.
     * 
     * @param tweets
     *            a list of tweets, not modified by this method.
     * @param timespan
     *            timespan
     * @return all tweets in the list that were sent during the timespan.
     */
    public static List<Tweet> inTimespan(final List<Tweet> tweets, final Timespan timespan) {
        //throw new RuntimeException("not implemented");
        
        //asserting that Timespan and list of tweets is defined
        List<Tweet> matchedTweets = new ArrayList<Tweet>();
        Assert.assertNotNull(tweets);
        Assert.assertNotNull(timespan);
        
        if(tweets.isEmpty() && (timespan.getStart().equals(timespan.getEnd()))){ // checking if getStart and getEnd is equal Date objs.
            return matchedTweets;
        }
        else if((tweets.isEmpty() && ((timespan.getStart().getTime() - timespan.getEnd().getTime()) < 0))){
            return matchedTweets;
        }
        
        for(Tweet tweet: tweets){           
            if((tweet.getTimestamp().getTime() == timespan.getStart().getTime()) && 
                    (tweet.getTimestamp().getTime() == timespan.getEnd().getTime())) matchedTweets.add(tweet);
            if((tweet.getTimestamp().getTime() > timespan.getStart().getTime()) && 
                    (tweet.getTimestamp().getTime() < timespan.getEnd().getTime())) matchedTweets.add(tweet);         
        }
        
        return matchedTweets;
    }

    /**
     * Search for tweets that contain certain words.
     * 
     * @param tweets
     *            a list of tweets, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets. Words must not
     *            contain spaces.
     * @return all tweets in the list such that the tweet text (when represented
     *         as a sequence of words bounded by space characters and the ends
     *         of the string) includes *all* the words found in the words
     *         list, in any order. Word comparison is not case-sensitive, so
     *         "Obama" is the same as "obama".
     */
    public static List<Tweet> containing(final List<Tweet> tweets, final List<String> words) {
        throw new RuntimeException("not implemented");

    }

}

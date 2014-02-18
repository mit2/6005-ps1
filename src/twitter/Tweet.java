package twitter;

import java.util.Date;

/**
 * This immutable datatype represents a tweet from Twitter.
 * 
 * DO NOT CHANGE THIS CLASS.
 * 
 */
public class Tweet {

    private final long id;
    private final String author;
    private final String text;
    private final Date timestamp;

    /**
     * Make a Tweet with a known unique id.
     * 
     * @param id
     *            unique identifier for the tweet, as assigned by Twitter.
     * @param author
     *            Twitter username who wrote this tweet.
     * @param text
     *            text of the tweet, at most 140 characters.
     * @param timestamp
     *            date/time when the tweet was sent.
     */
    public Tweet(final long id, final String author, final String text, final Date timestamp) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.timestamp = new Date(timestamp.getTime());  // make a copy to avoid rep exposure
    }

    /**
     * @return unique identifier of this tweet
     */
    public long getId() {
        return id;
    }

    /**
     * @return Twitter username who wrote this tweet
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return text of this tweet, at most 140 characters
     */
    public String getText() {
        return text;
    }

    /**
     * @return date/time when this tweet was sent
     */
    public Date getTimestamp() {
        return new Date(timestamp.getTime()); // make a copy to avoid rep exposure
    }

    /**
     * @see Object.toString()
     */
    @Override public String toString() {
        return "(" + this.getId() 
                + " " + this.getTimestamp().toString()
                + " " + this.getAuthor() 
                + ") " + this.getText();
    }

    /**
     * @see Object.equals()
     */
    @Override public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Tweet)) {
            return false;
        }

        Tweet that = (Tweet) thatObject;
        return this.id == that.id;
    }

    /**
     * @see Object.hashCode()
     */
    @Override public int hashCode() {
        int lower32bits = (int) id;
        int upper32bits = (int) (id >> 32);
        return lower32bits ^ upper32bits;
    }
}

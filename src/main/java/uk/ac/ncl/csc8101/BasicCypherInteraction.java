package uk.ac.ncl.csc8101;

import org.neo4j.graphdb.GraphDatabaseService;

/**
 * Class consisting of simple methods to set the basic Cypher
 * query strings to be executed.
 *
 * @author Hugo Firth <h.firth @ ncl.ac.uk>
 * @since 2014-01-23
 * @see CypherInteraction
 */
public class BasicCypherInteraction extends CypherInteraction {

    public BasicCypherInteraction(GraphDatabaseService db) {
        super(db);
    }

    /**
     * Description: Return those users who retweeted tweets of user @majorlyprofound
     * which do not contain a hashtag
     *
     * Student Rationale:
     *
     *
     *
     *
     * @return the Cypher Interaction to be run()
     */
    public CypherInteraction usersRetweetingUserNoHashtag()
    {
        this.query = "";
        return this;
    }

    /**
     * Description: Return those users who retweeted tweets of user @majorlyprofound
     * which do not contain a hashtag and who are not following user @majorlyprofound
     *
     * Student Rationale:
     *
     *
     *
     *
     * @return the Cypher Interaction to be run()
     */
    public CypherInteraction usersRetweetingUserNoHashtagNoFollow()
    {
        this.query = "";
        return this;
    }

    /**
     * Description: Return top 10 most followed users in descending order
     *
     * Student Rationale:
     *
     *
     *
     *
     * @return the Cypher Interaction to be run()
     */
    public CypherInteraction mostFollowedUsers()
    {
        this.query = "";
        return this;
    }

    /**
     * Description: Return max number of hops between user @majorlyprofound and any
     * other user (Friend of a Friend).
     *
     * Hint: Neo4j has functions.
     * Note: you are returning a number, not a node here.
     *
     * Student Rationale:
     *
     *
     *
     *
     * @return the Cypher Interaction to be run()
     */
    public CypherInteraction maxHopsFromUser()
    {
        this.query = "";
        return this;
    }
}

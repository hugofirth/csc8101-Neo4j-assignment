package uk.ac.ncl.csc8101;

import org.neo4j.graphdb.GraphDatabaseService;

/**
 * Class consisting of simple methods to set the complex Cypher
 * query strings to be executed.
 *
 * @author Hugo Firth <h.firth @ ncl.ac.uk>
 * @since 2014-01-24
 * @see CypherInteraction
 */
public class AdvancedCypherInteraction extends CypherInteraction {

    public AdvancedCypherInteraction(GraphDatabaseService db) {
        super(db);
    }

    /**
     * Description: Return the 10 users who follow the greatest number of the
     * same users as user @majorlyprofound, in descending order of the number of same users followed.
     * These users could be thought of as being 'similar' to @majorlyprofound.
     *
     * Hint: These 10 users do not necessarily have to follow or be followed by @marjorlyprofound.
     *
     * Student Rationale:
     *
     *
     *
     *
     * @return the Cypher Interaction to be run()
     */
    public CypherInteraction  similarUsersByFollows()
    {
        this.query = "";
        return this;
    }

    /**
     * Description:
     *
     * Building upon what you learned in the previous query - return upto 10 users who are:
     *
     * - Followed by those 10 users who follow the largest number of the same other
     * users as @majorlyprofound (called similar users).
     *
     * - Whom @majorlyprofound does not follow yet.
     *
     * - Sorted in descending order of occurrences (the number of similar users who follow each).
     *
     * Hint: You can have multiple sets of MATCH WHERE WITH ORDER clauses in a query
     * Note: Make sure you do not recommend that @majorlyprofound follows himself!
     *
     * Student Rationale:
     *
     *
     *
     *
     * @return the Cypher Interaction to be run()
     */
    public CypherInteraction  potentialFollowsBySimilarUsers()
    {
        this.query = "";
        return this;
    }

    /**
     * Description: Building upon what you learned in the previous queries - return upto 10 users who are:
     *
     * - Followed by the largest number of those 10 users who follow the largest number of the same other
     * users as @majorlyprofound (called similar users).
     *
     * - Whom @majorlyprofound does not follow yet.
     *
     * - Who tweet with hashtags that @marjorlyprofound also tweets with.
     *
     * - Sorted in descending order of the number of times @majorlyprofound has used a hashtag, and
     * the number of times a user has used that same hashtag.
     *
     * Hint: You can order by multiple variables (e.g ORDER BY thisN, thenThisN ...)
     * Note: Make sure you do not recommend that @majorlyprofound follows himself!
     *
     * Student Rationale:
     *
     *
     *
     *
     * @return the Cypher Interaction to be run()
     */
    public CypherInteraction  recommendedFollowsBySimilarUsersAndHashtags()
    {
        this.query = "";
        return this;
    }

}

package uk.ac.ncl.csc8101;

import org.junit.*;

import org.junit.runners.Parameterized;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.test.TestGraphDatabaseFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AdvancedCypherInteractionTest {

    protected static GraphDatabaseService graphDb;
    private static Map<String, String> expectedOutput;
    private static AdvancedCypherInteraction interaction;

    /**
     * Create database, read expected output & setup interaction
     */
    @BeforeClass
    public static void setup() throws IOException
    {
        String dbPath = System.getProperty("graph.db.path");
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);
        expectedOutput = new HashMap<>();
        //Read files
        expectedOutput.put("outputQ5p1", readFile(AdvancedCypherInteraction.class.getResource("/outputQ5p1.txt").getPath(), StandardCharsets.UTF_8));
        expectedOutput.put("outputQ5p2", readFile(AdvancedCypherInteraction.class.getResource("/outputQ5p2.txt").getPath(), StandardCharsets.UTF_8));
        expectedOutput.put("outputQ5p3", readFile(AdvancedCypherInteraction.class.getResource("/outputQ5p3.txt").getPath(), StandardCharsets.UTF_8));
        //Setup Interaction
        interaction = new AdvancedCypherInteraction(graphDb);
    }

    /**
     * Destroy database
     */
    @AfterClass
    public static void teardown()
    {
        graphDb.shutdown();
    }

    /**
     * Utility method to facilitate reading output files for expected output + ugly windows hack
     */
    private static String readFile(String path, Charset encoding) throws IOException
    {
        Path filePath = Paths.get(FileSystems.getDefault().getPath(path).toString().replace("\\C:\\","C:\\"));
        byte[] encoded = Files.readAllBytes(filePath);
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    /**
     * Verify Part 1 returns the expected "result" usernames
     */
    @Test
    public void interactionShouldReturnSimilarUsersByFollows()
    {
        String result = interaction.similarUsersByFollows().run().getResultString();
        assertEquals("Query 5 part 1 did not return the expected result."
                +" If you're confident in the query don't worry,"
                +" the rationale will be considered when marking", expectedOutput.get("outputQ5p1"), result);
    }

    /**
     * Verify Part 2 returns the expected "result" usernames
     */
    @Test
    public void interactionShouldReturnPotentialFollowsBySimilarUsers()
    {
        String result = interaction.potentialFollowsBySimilarUsers().run().getResultString();
        assertEquals("Query 5 part 2 did not return the expected result."
                +" If you're confident in the query don't worry,"
                +" the rationale will be considered when marking", expectedOutput.get("outputQ5p2"), result);
    }

    /**
     * Verify Part 3 returns the expected "result" usernames
     */
    @Test
    public void interactionShouldReturnRecommendedFollowsBySimilarUsersAndHashtags()
    {
        String result = interaction.recommendedFollowsBySimilarUsersAndHashtags().run().getResultString();
        assertEquals("Query 5 part 3 did not return the expected result."
                +" If you're confident in the query don't worry,"
                +" the rationale will be considered when marking", expectedOutput.get("outputQ5p3"), result);
    }


}

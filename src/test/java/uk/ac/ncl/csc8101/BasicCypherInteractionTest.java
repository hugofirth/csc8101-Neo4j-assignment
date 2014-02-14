package uk.ac.ncl.csc8101;

import org.junit.*;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.test.TestGraphDatabaseFactory;

import java.io.IOException;
import java.net.URISyntaxException;
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

public class BasicCypherInteractionTest {

    private static GraphDatabaseService graphDb;
    private static Map<String, String> expectedOutput;
    private static BasicCypherInteraction interaction;

    /**
     * Create database, read expected output & setup interaction
     */
    @BeforeClass
    public static void setup() throws IOException, URISyntaxException {
        String dbPath = System.getProperty("graph.db.path");
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);
        expectedOutput = new HashMap<>();
        //Read files
        expectedOutput.put("outputQ1", readFile(BasicCypherInteractionTest.class.getResource("/outputQ1.txt").toURI().getPath(), StandardCharsets.UTF_8));
        expectedOutput.put("outputQ2", readFile(BasicCypherInteractionTest.class.getResource("/outputQ2.txt").toURI().getPath(), StandardCharsets.UTF_8));
        expectedOutput.put("outputQ3", readFile(BasicCypherInteractionTest.class.getResource("/outputQ3.txt").toURI().getPath(), StandardCharsets.UTF_8));
        expectedOutput.put("outputQ4", readFile(BasicCypherInteractionTest.class.getResource("/outputQ4.txt").toURI().getPath(), StandardCharsets.UTF_8));
        //Setup Interaction
        interaction = new BasicCypherInteraction(graphDb);
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
        if(System.getProperty("os.name").toLowerCase().startsWith("windows"))
        {
            path = path.replaceAll("^(/|\\\\)", "");
        }
        Path filePath = Paths.get(FileSystems.getDefault().getPath(path).toString());
        byte[] encoded = Files.readAllBytes(filePath);
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    /**
     * Verify Query 1 returns the expected "result" usernames
     */
    @Test
    public void interactionShouldReturnUsersRetweetingUserNoHashtag()
    {
        String result = interaction.usersRetweetingUserNoHashtag().run().getResultString();
        assertEquals("Query 1 did not return the expected result."
                +" If you're confident in the query don't worry,"
                +" the rationale will be considered when marking", expectedOutput.get("outputQ1"), result);
    }

    /**
     * Verify Query 2 returns the expected "result" usernames
     */
    @Test
    public void interactionShouldReturnUsersRetweetingUserNoHashtagNoFollow()
    {
        String result = interaction.usersRetweetingUserNoHashtagNoFollow().run().getResultString();
        assertEquals("Query 2 did not return the expected result."
                +" If you're confident in the query don't worry,"
                +" the rationale will be considered when marking", expectedOutput.get("outputQ2"), result);
    }

    /**
     * Verify Query 3 returns the expected "result" usernames
     */
    @Test
    public void interactionShouldReturnMostFollowedUsers()
    {
        String result = interaction.mostFollowedUsers().run().getResultString();
        assertEquals("Query 3 did not return the expected result."
                +" If you're confident in the query don't worry,"
                +" the rationale will be considered when marking", expectedOutput.get("outputQ3"), result);
    }

    /**
     * Verify Query 4 returns the expected "result" number
     */
    @Test
    public void interactionShouldReturnMaxHopsFromUser()
    {
        String result = interaction.maxHopsFromUser().run().getResultString();
        assertEquals("Query 4 did not return the expected result."
                +" If you're confident in the query don't worry,"
                +" the rationale will be considered when marking", expectedOutput.get("outputQ4"), result);
    }
}

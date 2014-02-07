package uk.ac.ncl.csc8101;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Abstract class which defines how query strings are executed on a database
 *
 *  !!! DO NOT EDIT !!!
 */
public abstract class CypherInteraction
{
    private static final String COL_NAME = "result";
    private GraphDatabaseService db;
    private ExecutionEngine engine;
    private String resultString;
    protected String query;

    public CypherInteraction(GraphDatabaseService db)
    {
        this.db = db;
        this.engine = new ExecutionEngine(this.db);
        this.resultString = "";
        this.query = "";
    }

    public CypherInteraction run()
    {
        this.validateQuery();

        try ( Transaction tx = db.beginTx() )
        {
            ExecutionResult result = this.engine.execute(this.query+" AS "+COL_NAME);
            this.resultString = result.dumpToString();
            tx.failure();
        }

        return this;
    }

    private void validateQuery()
    {
        if(orderByAfterReturn())
        {
            throw new IllegalArgumentException("The query: \""+this.query+"\" contains an ORDER BY clause *after* the RETURN clause. ORDER BY clauses should precede the RETURN clause.");
        }

        if(!singleReturn())
        {
            throw new IllegalArgumentException("The query: \""+this.query+"\" attempts to return multiple items. Only a single variable may be returned.");
        }
    }

    private Boolean singleReturn()
    {
        return this.query.matches("(?s).*(?i)RETURN[^,]*\\z");
    }

    private Boolean orderByAfterReturn()
    {
        return this.query.matches("(?s).*(?i)RETURN[^,]*((?i)ORDER.*)\\z");
    }

    public String getResultString() {
        return resultString;
    }

}

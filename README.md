##Neo4j programming: connected data analytics coursework

----

This coursework introduces you to the analysis of connected (graph) data. It will provide practical experience of interacting with a property graph data model, specifically through the popular [Neo4j][1] Graph database management system and [Cypher][2] query language.

###Data:

As the basis for the assignment you have been presented with a body of **falisified** twitter data. The data model is as depicted below:

![Twitter Data-Model][3]

####Node Types:

* **User** -  _properties_ { username: "" }
* **Tweet** - _properties_ { text: "" }
* **Hashtag** - _properties_ { tag: "" }

####Relationship Types:

* **Follows** (User-->User)
* **Sent** (User-->Tweet)
* **Mentions** (Tweet-->User)
* **Contains** (Tweet-->Hashtag)

####Notes:

For simplicity, this twitter data is entirely generated. Real twitter handles and hashtags have been used, however the information tied to those handles and tags in this dataset is entirely unrelated to their real-world counterparts. There are 1000 twitter users, 250 hashtags, and roughly 500,000 tweets in this dataset. That not might sound like a lot - however it should demonstrate readily enough what would be involved with manipulating large quantities of graph data. 

----

###The Assignment: 

You will designing queries to find out things about a twitter user with the username `@majorlyprofound`, and general information about the larger graph.

These queries will be divided into amongst two java classes:

* `uk.ac.ncl.csc8101.BasicCypherInteraction`  
&
* `uk.ac.ncl.csc8101.AdvancedCypherInteraction`

####BasicCypherInteraction

#####Query 1
Return those users who retweeted tweets of user `@majorlyprofound` which do not contain a hashtag.

#####Query 2
Return those users who retweeted tweets of user `@majorlyprofound` which do not contain a hashtag and who are not following user `@majorlyprofound`.

#####Query 3
Return top 10 most followed users in descending order

#####Query 4
Return max number of hops between user `@majorlyprofound` and any other user (Friend of a Friend).


####AdvancedCypherInteraction
The advanced cypher reaction can really be thought of as a single query, however the query is quite complex, so the class contains 3 queries (parts). Part 1 is relatively straight forward, 
part 2 builds upon part 1, and finally, part 3 is the larger, more complex query.

#####Query 5 part 1
Return the 10 users who follow the greatest number of the same users as user `@majorlyprofound`, in descending order of the number of same users followed. These users could be thought of as being 'similar' to `@majorlyprofound`. 

#####Query 5 part 2
Building upon what you learned in the previous query - return upto 10 users who are:

* Followed by those 10 users who follow the largest number of the same other users as `@majorlyprofound` (called similar users).
* Not followed by `@majorlyprofound`.
* Sorted in descending order of occurrences (the number of similar users who follow each).

#####Query 5 part 3
Building upon what you learned in the previous queries - return upto 10 users who are:

* Followed by the largest number of those 10 users who follow the largest number of the same other users as `@majorlyprofound` (called similar users). 
* Not followed by `@majorlyprofound`.
* Who tweet with hashtags that `@marjorlyprofound` also tweets with.
* Sorted in descending order of the number of times `@majorlyprofound` has used a hashtag, and the number of times a user has used that same hashtag.

----

###Getting Started:

1. Clone **this** repo.
2. Download the [graph data][4] and unzip it.
3. Move the resulting `graph.db` directory into the `src/main/resources/` directory **inside the newly cloned project**!
4. Open the project using your preferred IDE
	* **Side note**: The project uses the Maven build & dependency management tool, if you have a plugin with your IDE which supports that.
5. Open the two classes `BasicCypherInteraction` & `AdvancedCypherInteraction`. Inside you should see code that looks as follows:

        public CypherInteraction usersRetweetingUserNoHashtag()
    	{
        	this.query = "";
        	return this;
    	}

6. You should design your queries (using the web based Neo4j editor and copy of the dataset already available on your AWS machnines) and then assign them the `this.query` variable within the appropriate method on your local project.
7. You should briefly (2-4 lines) explain the rationale behind your query in the space provided above each query method.
8. When the queries are completed to your satisfaction - you should open the terminal in the project directory and run the command `mvn clean test`. This will verify that your queries behave as expected.

----

###Notes:

* The test classes which verify your queries are located within the `src/test/` directory. By default all the tests will run when you run the `mvn clean test` command. You may add `@Ignore` above any `@Test` annotation in order to not run that test. This may be useful for testing individual queries.
* With some of the queries (especially the advanced ones!) there may not be just one right way to do things. Unfortunately, unit tests are brittle, and if the result of your query is just slightly different than that which I expect the test will fail. This does not mean the query is wrong. The tests are meant as a guide for you and **will not be the method by which you are marked**.
* Given the previous point - it is really important you fill out the rationale section for each query. Otherwise we will have to guess what you are thinking!
* Throughout the examples in your notes you saw a cypher clause called `START`. This is optional in the latest version of Neo4j and relies upon an old version of Neo4j's indexing. If you use `START` clauses your queries **will not work**. You can just start with `MATCH` clauses instead. A useful reference for Cypher syntax and statement structure is the [ref-card][5].
* By default, Cypher allows you to return multiple variables. This has been modified for simplicity and testability in this coursework. Please only return a **single variable**.
* Finally, by default, Cypher allows you to use `ORDER BY` clauses after the `RETURN` clause in a query. This has been modified for testability in this coursework. Please put any `ORDER BY` statements **before** your `RETURN` statement.

----

###Submission:

You should submit a zip of your completed project to ness. If you have any questions please email me (Hugo Firth).


[1]: http://www.neo4j.org/ "Neo4j"
[2]: http://docs.neo4j.org/chunked/stable/cypher-query-lang.html "Cypher documentation"
[3]: https://dl.dropboxusercontent.com/u/6430/TwitterDM.svg "TwitterDM"
[4]: https://dl.dropboxusercontent.com/u/6430/graph.db.zip "Graph data"
[5]: http://docs.neo4j.org/refcard/2.0/ "Cypher ref card"
package com.philipmcclelland.filmbase.graph;

import org.junit.After;
import org.junit.Before;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.test.TestGraphDatabaseFactory;

public abstract class BaseGraphTest {

	protected static GraphDatabaseService gds = null;
	
	@Before
	public void init() {
		gds = new TestGraphDatabaseFactory()
	    .newImpermanentDatabaseBuilder()
	    .setConfig( GraphDatabaseSettings.nodestore_mapped_memory_size, "10M" )
	    .setConfig( GraphDatabaseSettings.string_block_size, "60" )
	    .setConfig( GraphDatabaseSettings.array_block_size, "300" )
	    .newGraphDatabase();
	}
	
	@After
	public void after()	 {
		gds.shutdown();
	}
	
}

package com.philipmcclelland.filmbase.graph;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

public final class GraphDatabaseServiceHolder {
	
	public static final String DATABASE_PATH = "graphdb";
	
	/**
	 * prevent instantiation
	 */
	private GraphDatabaseServiceHolder() {
		throw new UnsupportedOperationException();
	}
	
	private static GraphDatabaseService graphDatabaseService = null;
	
	public static GraphDatabaseService getGraphDatabaseService() {
		if( graphDatabaseService == null ) {
			graphDatabaseService = new GraphDatabaseFactory()
		    .newEmbeddedDatabaseBuilder( DATABASE_PATH )
		    .setConfig( GraphDatabaseSettings.nodestore_mapped_memory_size, "10M" )
		    .setConfig( GraphDatabaseSettings.string_block_size, "60" )
		    .setConfig( GraphDatabaseSettings.array_block_size, "300" )
		    .newGraphDatabase();
		}
		
		return graphDatabaseService;
	}
	
	private static void registerShutdownHook( final GraphDatabaseService graphDb )
	{
	    // Registers a shutdown hook for the Neo4j instance so that it
	    // shuts down nicely when the VM exits (even if you "Ctrl-C" the
	    // running application).
	    Runtime.getRuntime().addShutdownHook( new Thread()
	    {
	        @Override
	        public void run()
	        {
	            graphDb.shutdown();
	        }
	    } );
	}
}

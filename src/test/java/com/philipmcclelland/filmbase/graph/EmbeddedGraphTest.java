package com.philipmcclelland.filmbase.graph;

import org.junit.Assert;
import org.junit.Test;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

public class EmbeddedGraphTest extends BaseGraphTest {

	@Test
	public void testCreateNodeWithLabel() {
		Node n = null;
		Label testLabel = DynamicLabel.label("test-nodes");
		
		try ( Transaction tx = gds.beginTx() ) {
			n = gds.createNode(testLabel);
			n.setProperty("foo", "bar");
			tx.success();
		}
		
		try ( Transaction tx = gds.beginTx() ) {
			
			Assert.assertNotNull(n);
			Assert.assertTrue(n.hasLabel(testLabel));
			Assert.assertTrue(((String)n.getProperty("foo")).equals("bar"));
			
			tx.success();
		}
		
	}
}

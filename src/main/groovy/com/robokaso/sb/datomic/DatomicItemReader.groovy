package com.robokaso.sb.datomic

import javax.annotation.PostConstruct;

import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.util.Assert

import datomic.Connection
import datomic.Entity
import datomic.Peer

/**
 * 
 * @author Robert Kasanicky
 *
 * @param <T> type of item
 */
class DatomicItemReader extends AbstractItemCountingItemStreamItemReader<Entity> {

	/**
	 * URI of a Datomic database
	 */
	String dbUri
	
	String query
	
	private Connection conn
	
	private Iterator<List<Object>> dataIterator
	
	@Override
	protected Entity doRead() {
		def id = dataIterator.next().get(0)
		def entity = conn.db().entity(id)
		
		entity
	}

	@Override
	protected void doOpen() {
		conn = Peer.connect(dbUri)
		dataIterator = Peer.q("[:find ?c :where [?c :community/name]]", conn.db()).iterator()
	}

	@Override
	protected void doClose() {
//		conn.close() //AbstractMethodError - datomic bug?
	}
	
	@PostConstruct
	void afterPropertiesSet() {
		Assert.state(!query.isEmpty(), 'query must be set')	
		Assert.state(!dbUri.isEmpty(), 'dbUri must be set')	
	}

}

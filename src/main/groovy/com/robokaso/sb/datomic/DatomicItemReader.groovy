package com.robokaso.sb.datomic

import java.util.Collection;

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
class DatomicItemReader extends AbstractItemCountingItemStreamItemReader<List<Object>> {

	/**
	 * URI of a Datomic database
	 */
	String dbUri
	
	String query
	
	List args = []
	
	private Connection conn
	
	private Iterator<List<Object>> dataIterator
	
	@Override
	protected List<Object> doRead() {
		dataIterator.next()
	}

	@Override
	protected void doOpen() {
		conn = Peer.connect(dbUri)
		dataIterator = Peer.q(query, ([conn.db()] + args) as Object[]).iterator()
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

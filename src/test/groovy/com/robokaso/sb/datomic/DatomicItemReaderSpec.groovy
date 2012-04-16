package com.robokaso.sb.datomic

import groovy.util.logging.Slf4j;
import datomic.Connection
import datomic.Peer
import datomic.Util
import spock.lang.Specification

/**
 * Specs for {@link DatomicItemReader}. 
 * 
 * @author Robert Kasanicky
 */
@Slf4j
class DatomicItemReaderSpec extends Specification {

	private static final String DB_URI = "datomic:mem://DatomicItemReaderSpec"
	
	private static Connection conn

	/**
	 * Create inmemory database and connect to it.
	 */
	def setupSpec() {
		assert Peer.createDatabase(DB_URI)
		conn = Peer.connect(DB_URI)

		def schemaTx = Util.readAll(new FileReader("src/test/resources/seattle-schema.dtm")).get(0)
		assert conn.transact(schemaTx).get()
		
		def dataTx = Util.readAll(new FileReader("src/test/resources/seattle-data0.dtm")).get(0)
		assert conn.transact(dataTx).get()
		
	}
	def placeholder() {
		
		when:
			def results = Peer.q("[:find ?c :where [?c :community/name]]", conn.db())
		then:
			results.size() == 150
	}
}

package com.robokaso.sb.datomic

import org.springframework.batch.item.ExecutionContext

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
	
	def cleanupSpec() {
//		conn.close()	//abstract method exception?!
		conn = null
	}
	
	def placeholder() {
		
		when:
			def results = Peer.q("[:find ?c :where [?c :community/name]]", conn.db())
		then:
			results.size() == 150
	}
	
	def 'read communities'() {
		
		given:
			def reader = new DatomicItemReader(
				name: 'datomicReader',
				query: 	"[:find ?c :where [?c :community/name]]",
				dbUri: DB_URI
				)
			reader.afterPropertiesSet()
			
			def ctx = new ExecutionContext()
			reader.open(ctx)
			
		when:
			def entity = reader.read()
			
		then:
			def communityName = entity.get(":community/name")
			println communityName
			
		when:
			reader.close()
			
		then:
			true
	}
	
}

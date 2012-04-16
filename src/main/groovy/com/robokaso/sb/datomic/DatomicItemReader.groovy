package com.robokaso.sb.datomic

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.batch.item.support.AbstractItemStreamItemReader

/**
 * 
 * @author Robert Kasanicky
 *
 * @param <T> type of item
 */
class DatomicItemReader<T> extends AbstractItemCountingItemStreamItemReader<T> {

	@Override
	protected T doRead() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doOpen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doClose() {
		// TODO Auto-generated method stub
		
	}

}

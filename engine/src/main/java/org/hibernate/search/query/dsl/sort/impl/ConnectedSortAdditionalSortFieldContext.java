/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.query.dsl.sort.impl;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.SortField;

import org.hibernate.search.query.dsl.impl.QueryBuildingContext;
import org.hibernate.search.query.dsl.sort.SortAdditionalSortFieldContext;
import org.hibernate.search.query.dsl.sort.SortDistanceContext;
import org.hibernate.search.query.dsl.sort.SortDistanceFromContext;
import org.hibernate.search.query.dsl.sort.SortFieldContext;
import org.hibernate.search.query.dsl.sort.SortNativeContext;
import org.hibernate.search.query.dsl.sort.SortOrderTermination;

/**
 * @author Emmanuel Bernard emmanuel@hibernate.org
 */
public abstract class ConnectedSortAdditionalSortFieldContext extends AbstractConnectedSortContext implements SortAdditionalSortFieldContext {

	public ConnectedSortAdditionalSortFieldContext(QueryBuildingContext queryContext, SortFieldStates states) {
		super( queryContext, states );
	}

	@Override
	public SortFieldContext andByField(String field) {
		states.closeSortField();
		states.setCurrentName( field );
		states.guessCurrentSortFieldType();
		return new ConnectedSortFieldContext( queryContext, states );
	}

	@Override
	public SortFieldContext andByField(String field, SortField.Type sortFieldType) {
		states.closeSortField();
		states.setCurrentName( field );
		states.setCurrentType( sortFieldType );
		return new ConnectedSortFieldContext( queryContext, states );
	}

	@Override
	public SortOrderTermination andByScore() {
		states.closeSortField();
		states.setCurrentType( SortField.Type.SCORE );
		return new ConnectedSortOrderTermination( queryContext, states );
	}

	@Override
	public SortOrderTermination andByIndexOrder() {
		states.closeSortField();
		states.setCurrentType( SortField.Type.DOC );
		return new ConnectedSortOrderTermination( queryContext, states );
	}

	@Override
	public SortDistanceFromContext andByDistance(String field) {
		states.closeSortField();
		states.setCurrentName( field );
		return new ConnectedSortDistanceFromContext( queryContext, states );
	}

	@Override
	public SortDistanceContext andByDistanceFromSpatialQuery(Query query) {
		states.closeSortField();
		states.setCurrentSpatialQuery(query);
		return new ConnectedSortDistanceContext( queryContext, states );
	}

	@Override
	public SortNativeContext andByNative(SortField sortField) {
		states.closeSortField();
		states.setCurrentSortField(sortField);
		return new ConnectedSortNativeContext( queryContext, states );
	}

	@Override
	public SortNativeContext andByNative(String sortField) {
		states.closeSortField();
		states.setCurrentSortField(sortField);
		return new ConnectedSortNativeContext( queryContext, states );
	}
}
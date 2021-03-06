/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.elasticsearch.schema.impl;

import org.hibernate.search.elasticsearch.schema.impl.model.IndexMetadata;
import org.hibernate.search.engine.service.spi.Service;

/**
 * An object responsible for validating type mappings retrieved from an existing Elasticsearch instance
 * against Hibernate Search-generated type mappings.
 *
 * @author Yoann Rodiere
 */
public interface ElasticsearchSchemaValidator extends Service {

	/**
	 * Retrieves and validate actual index metadata, throwing an exception if validation fails.
	 *
	 * The metadata mainly contain the type mappings.
	 *
	 * @param expectedIndexMetadata The expected metadata, generated by Hibernate Search.
	 * @throws ElasticsearchSchemaValidationException If a validation error occurs.
	 */
	void validate(IndexMetadata expectedIndexMetadata, ExecutionOptions executionOptions);

}

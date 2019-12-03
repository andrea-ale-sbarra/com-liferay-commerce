/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.headless.commerce.delivery.catalog.internal.dto.v1_0.converter;

import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;

import java.util.Locale;

/**
 * @author Andrea Sbarra
 */
public class CPCatalogEntryDTOConverterContext
	extends DefaultDTOConverterContext {

	public CPCatalogEntryDTOConverterContext(
		Locale locale, long resourcePrimKey, CPCatalogEntry cpCatalogEntry) {

		super(locale, resourcePrimKey);

		_cpCatalogEntry = cpCatalogEntry;
	}

	public CPCatalogEntry getCpCatalogEntry() {
		return _cpCatalogEntry;
	}

	private CPCatalogEntry _cpCatalogEntry;

}
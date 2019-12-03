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

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagService;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPSku;
import com.liferay.commerce.product.content.util.CPContentHelper;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.delivery.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.delivery.catalog.dto.v1_0.Sku;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Sbarra
 */
@Component(
	property = "model.class.name=com.liferay.commerce.product.catalog.CPCatalogEntry",
	service = {CPCatalogEntryDTOConverter.class, DTOConverter.class}
)
public class CPCatalogEntryDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return Product.class.getSimpleName();
	}

	public Product toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CPCatalogEntryDTOConverterContext
			cpCatalogEntryDTOConverterConvertContext =
				(CPCatalogEntryDTOConverterContext)dtoConverterContext;

		CPCatalogEntry cpCatalogEntry =
			cpCatalogEntryDTOConverterConvertContext.getCpCatalogEntry();
		String languageId = LanguageUtil.getLanguageId(
			cpCatalogEntryDTOConverterConvertContext.getLocale());

		CPDefinition cpDefinition = _cpDefinitionService.getCPDefinition(
			dtoConverterContext.getResourcePrimKey());

		ExpandoBridge expandoBridge = cpDefinition.getExpandoBridge();
		CProduct cProduct = cpDefinition.getCProduct();

		return new Product() {
			{

				// allowedOrderQuantities

				createDate = cpDefinition.getCreateDate();
				description = cpCatalogEntry.getDescription();
				expando = expandoBridge.getAttributes();
				externalReferenceCode = cProduct.getExternalReferenceCode();
				id = cpCatalogEntry.getCPDefinitionId();

				// maxOrderQuantity

				metaDescription = cpCatalogEntry.getMetaDescription(languageId);
				metaKeyword = cpCatalogEntry.getMetaKeywords(languageId);
				metaTitle = cpCatalogEntry.getMetaTitle(languageId);

				// minOrderQuantity

				modifiedDate = cpDefinition.getModifiedDate();

				name = cpCatalogEntry.getName();
				productId = cpCatalogEntry.getCProductId();
				productType = cpCatalogEntry.getProductTypeName();
				shortDescription = cpCatalogEntry.getShortDescription();
				skus = _toSkus(
					cpCatalogEntry.getCPSkus(),
					cpCatalogEntryDTOConverterConvertContext.getLocale());
				tags = _getTags(cpDefinition);
				url = cpCatalogEntry.getUrl();
				urlImage = cpCatalogEntry.getDefaultImageFileUrl();
			}
		};
	}

	private String[] _getTags(CPDefinition cpDefinition) {
		List<AssetTag> assetEntryAssetTags = _assetTagService.getTags(
			cpDefinition.getModelClassName(), cpDefinition.getCPDefinitionId());

		Stream<AssetTag> stream = assetEntryAssetTags.stream();

		return stream.map(
			AssetTag::getName
		).toArray(
			String[]::new
		);
	}

	private Sku[] _toSkus(List<CPSku> cpSkus, Locale locale) throws Exception {
		List<Sku> skus = new ArrayList<>();
		DTOConverter cpSkuDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPSku.class.getName());

		for (CPSku cpSku : cpSkus) {
			skus.add(
				(Sku)cpSkuDTOConverter.toDTO(
					new CPSkuDTOConverterContext(
						locale, cpSku.getCPInstanceId(), cpSku)));
		}

		Stream<Sku> stream = skus.stream();

		return stream.toArray(Sku[]::new);
	}

	@Reference
	private AssetTagService _assetTagService;

	@Reference
	private CPContentHelper _cpContentHelper;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}
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

package com.liferay.headless.commerce.delivery.catalog.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.headless.commerce.delivery.catalog.client.dto.v1_0.Product;
import com.liferay.headless.commerce.delivery.catalog.client.http.HttpInvoker;
import com.liferay.headless.commerce.delivery.catalog.client.pagination.Page;
import com.liferay.headless.commerce.delivery.catalog.client.pagination.Pagination;
import com.liferay.headless.commerce.delivery.catalog.client.resource.v1_0.ProductResource;
import com.liferay.headless.commerce.delivery.catalog.client.serdes.v1_0.ProductSerDes;
import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Generated("")
public abstract class BaseProductResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_productResource.setContextCompany(testCompany);

		ProductResource.Builder builder = ProductResource.builder();

		productResource = builder.locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Product product1 = randomProduct();

		String json = objectMapper.writeValueAsString(product1);

		Product product2 = ProductSerDes.toDTO(json);

		Assert.assertTrue(equals(product1, product2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Product product = randomProduct();

		String json1 = objectMapper.writeValueAsString(product);
		String json2 = ProductSerDes.toJSON(product);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Product product = randomProduct();

		product.setDescription(regex);
		product.setExternalReferenceCode(regex);
		product.setMetaDescription(regex);
		product.setMetaKeyword(regex);
		product.setMetaTitle(regex);
		product.setName(regex);
		product.setProductType(regex);
		product.setShortDescription(regex);
		product.setUrl(regex);
		product.setUrlImage(regex);

		String json = ProductSerDes.toJSON(product);

		Assert.assertFalse(json.contains(regex));

		product = ProductSerDes.toDTO(json);

		Assert.assertEquals(regex, product.getDescription());
		Assert.assertEquals(regex, product.getExternalReferenceCode());
		Assert.assertEquals(regex, product.getMetaDescription());
		Assert.assertEquals(regex, product.getMetaKeyword());
		Assert.assertEquals(regex, product.getMetaTitle());
		Assert.assertEquals(regex, product.getName());
		Assert.assertEquals(regex, product.getProductType());
		Assert.assertEquals(regex, product.getShortDescription());
		Assert.assertEquals(regex, product.getUrl());
		Assert.assertEquals(regex, product.getUrlImage());
	}

	@Test
	public void testGetStoreChannelProductsPage() throws Exception {
		Page<Product> page = productResource.getStoreChannelProductsPage(
			testGetStoreChannelProductsPage_getChannelId(), null,
			Pagination.of(1, 2), null);

		Assert.assertEquals(0, page.getTotalCount());

		Long channelId = testGetStoreChannelProductsPage_getChannelId();
		Long irrelevantChannelId =
			testGetStoreChannelProductsPage_getIrrelevantChannelId();

		if ((irrelevantChannelId != null)) {
			Product irrelevantProduct =
				testGetStoreChannelProductsPage_addProduct(
					irrelevantChannelId, randomIrrelevantProduct());

			page = productResource.getStoreChannelProductsPage(
				irrelevantChannelId, null, Pagination.of(1, 2), null);

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantProduct),
				(List<Product>)page.getItems());
			assertValid(page);
		}

		Product product1 = testGetStoreChannelProductsPage_addProduct(
			channelId, randomProduct());

		Product product2 = testGetStoreChannelProductsPage_addProduct(
			channelId, randomProduct());

		page = productResource.getStoreChannelProductsPage(
			channelId, null, Pagination.of(1, 2), null);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(product1, product2), (List<Product>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetStoreChannelProductsPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Long channelId = testGetStoreChannelProductsPage_getChannelId();

		Product product1 = randomProduct();

		product1 = testGetStoreChannelProductsPage_addProduct(
			channelId, product1);

		for (EntityField entityField : entityFields) {
			Page<Product> page = productResource.getStoreChannelProductsPage(
				channelId, getFilterString(entityField, "between", product1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(product1),
				(List<Product>)page.getItems());
		}
	}

	@Test
	public void testGetStoreChannelProductsPageWithFilterStringEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		Long channelId = testGetStoreChannelProductsPage_getChannelId();

		Product product1 = testGetStoreChannelProductsPage_addProduct(
			channelId, randomProduct());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Product product2 = testGetStoreChannelProductsPage_addProduct(
			channelId, randomProduct());

		for (EntityField entityField : entityFields) {
			Page<Product> page = productResource.getStoreChannelProductsPage(
				channelId, getFilterString(entityField, "eq", product1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(product1),
				(List<Product>)page.getItems());
		}
	}

	@Test
	public void testGetStoreChannelProductsPageWithPagination()
		throws Exception {

		Long channelId = testGetStoreChannelProductsPage_getChannelId();

		Product product1 = testGetStoreChannelProductsPage_addProduct(
			channelId, randomProduct());

		Product product2 = testGetStoreChannelProductsPage_addProduct(
			channelId, randomProduct());

		Product product3 = testGetStoreChannelProductsPage_addProduct(
			channelId, randomProduct());

		Page<Product> page1 = productResource.getStoreChannelProductsPage(
			channelId, null, Pagination.of(1, 2), null);

		List<Product> products1 = (List<Product>)page1.getItems();

		Assert.assertEquals(products1.toString(), 2, products1.size());

		Page<Product> page2 = productResource.getStoreChannelProductsPage(
			channelId, null, Pagination.of(2, 2), null);

		Assert.assertEquals(3, page2.getTotalCount());

		List<Product> products2 = (List<Product>)page2.getItems();

		Assert.assertEquals(products2.toString(), 1, products2.size());

		Page<Product> page3 = productResource.getStoreChannelProductsPage(
			channelId, null, Pagination.of(1, 3), null);

		assertEqualsIgnoringOrder(
			Arrays.asList(product1, product2, product3),
			(List<Product>)page3.getItems());
	}

	@Test
	public void testGetStoreChannelProductsPageWithSortDateTime()
		throws Exception {

		testGetStoreChannelProductsPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, product1, product2) -> {
				BeanUtils.setProperty(
					product1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetStoreChannelProductsPageWithSortInteger()
		throws Exception {

		testGetStoreChannelProductsPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, product1, product2) -> {
				BeanUtils.setProperty(product1, entityField.getName(), 0);
				BeanUtils.setProperty(product2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetStoreChannelProductsPageWithSortString()
		throws Exception {

		testGetStoreChannelProductsPageWithSort(
			EntityField.Type.STRING,
			(entityField, product1, product2) -> {
				Class<?> clazz = product1.getClass();

				Method method = clazz.getMethod(
					"get" +
						StringUtil.upperCaseFirstLetter(entityField.getName()));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanUtils.setProperty(
						product1, entityField.getName(),
						Collections.singletonMap("Aaa", "Aaa"));
					BeanUtils.setProperty(
						product2, entityField.getName(),
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else {
					BeanUtils.setProperty(
						product1, entityField.getName(), "Aaa");
					BeanUtils.setProperty(
						product2, entityField.getName(), "Bbb");
				}
			});
	}

	protected void testGetStoreChannelProductsPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer<EntityField, Product, Product, Exception>
				unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Long channelId = testGetStoreChannelProductsPage_getChannelId();

		Product product1 = randomProduct();
		Product product2 = randomProduct();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, product1, product2);
		}

		product1 = testGetStoreChannelProductsPage_addProduct(
			channelId, product1);

		product2 = testGetStoreChannelProductsPage_addProduct(
			channelId, product2);

		for (EntityField entityField : entityFields) {
			Page<Product> ascPage = productResource.getStoreChannelProductsPage(
				channelId, null, Pagination.of(1, 2),
				entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(product1, product2),
				(List<Product>)ascPage.getItems());

			Page<Product> descPage =
				productResource.getStoreChannelProductsPage(
					channelId, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(product2, product1),
				(List<Product>)descPage.getItems());
		}
	}

	protected Product testGetStoreChannelProductsPage_addProduct(
			Long channelId, Product product)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetStoreChannelProductsPage_getChannelId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetStoreChannelProductsPage_getIrrelevantChannelId()
		throws Exception {

		return null;
	}

	protected Product testGraphQLProduct_addProduct() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Product product1, Product product2) {
		Assert.assertTrue(
			product1 + " does not equal " + product2,
			equals(product1, product2));
	}

	protected void assertEquals(
		List<Product> products1, List<Product> products2) {

		Assert.assertEquals(products1.size(), products2.size());

		for (int i = 0; i < products1.size(); i++) {
			Product product1 = products1.get(i);
			Product product2 = products2.get(i);

			assertEquals(product1, product2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Product> products1, List<Product> products2) {

		Assert.assertEquals(products1.size(), products2.size());

		for (Product product1 : products1) {
			boolean contains = false;

			for (Product product2 : products2) {
				if (equals(product1, product2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				products2 + " does not contain " + product1, contains);
		}
	}

	protected void assertEqualsJSONArray(
		List<Product> products, JSONArray jsonArray) {

		for (Product product : products) {
			boolean contains = false;

			for (Object object : jsonArray) {
				if (equalsJSONObject(product, (JSONObject)object)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				jsonArray + " does not contain " + product, contains);
		}
	}

	protected void assertValid(Product product) {
		boolean valid = true;

		if (product.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (product.getCreateDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (product.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("expando", additionalAssertFieldName)) {
				if (product.getExpando() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (product.getExternalReferenceCode() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("metaDescription", additionalAssertFieldName)) {
				if (product.getMetaDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("metaKeyword", additionalAssertFieldName)) {
				if (product.getMetaKeyword() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("metaTitle", additionalAssertFieldName)) {
				if (product.getMetaTitle() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("modifiedDate", additionalAssertFieldName)) {
				if (product.getModifiedDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"multipleOrderQuantity", additionalAssertFieldName)) {

				if (product.getMultipleOrderQuantity() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (product.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productId", additionalAssertFieldName)) {
				if (product.getProductId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productType", additionalAssertFieldName)) {
				if (product.getProductType() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("shortDescription", additionalAssertFieldName)) {
				if (product.getShortDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("skus", additionalAssertFieldName)) {
				if (product.getSkus() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("tags", additionalAssertFieldName)) {
				if (product.getTags() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("url", additionalAssertFieldName)) {
				if (product.getUrl() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("urlImage", additionalAssertFieldName)) {
				if (product.getUrlImage() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<Product> page) {
		boolean valid = false;

		java.util.Collection<Product> products = page.getItems();

		int size = products.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			graphQLFields.add(new GraphQLField(additionalAssertFieldName));
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(Product product1, Product product2) {
		if (product1 == product2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getCreateDate(), product2.getCreateDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getDescription(), product2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("expando", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getExpando(), product2.getExpando())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						product1.getExternalReferenceCode(),
						product2.getExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(product1.getId(), product2.getId())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("metaDescription", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getMetaDescription(),
						product2.getMetaDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("metaKeyword", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getMetaKeyword(), product2.getMetaKeyword())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("metaTitle", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getMetaTitle(), product2.getMetaTitle())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("modifiedDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getModifiedDate(),
						product2.getModifiedDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"multipleOrderQuantity", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						product1.getMultipleOrderQuantity(),
						product2.getMultipleOrderQuantity())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getName(), product2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getProductId(), product2.getProductId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productType", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getProductType(), product2.getProductType())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("shortDescription", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getShortDescription(),
						product2.getShortDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("skus", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getSkus(), product2.getSkus())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("tags", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getTags(), product2.getTags())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("url", additionalAssertFieldName)) {
				if (!Objects.deepEquals(product1.getUrl(), product2.getUrl())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("urlImage", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getUrlImage(), product2.getUrlImage())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equalsJSONObject(Product product, JSONObject jsonObject) {
		for (String fieldName : getAdditionalAssertFieldNames()) {
			if (Objects.equals("description", fieldName)) {
				if (!Objects.deepEquals(
						product.getDescription(),
						jsonObject.getString("description"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("externalReferenceCode", fieldName)) {
				if (!Objects.deepEquals(
						product.getExternalReferenceCode(),
						jsonObject.getString("externalReferenceCode"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", fieldName)) {
				if (!Objects.deepEquals(
						product.getId(), jsonObject.getLong("id"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("metaDescription", fieldName)) {
				if (!Objects.deepEquals(
						product.getMetaDescription(),
						jsonObject.getString("metaDescription"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("metaKeyword", fieldName)) {
				if (!Objects.deepEquals(
						product.getMetaKeyword(),
						jsonObject.getString("metaKeyword"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("metaTitle", fieldName)) {
				if (!Objects.deepEquals(
						product.getMetaTitle(),
						jsonObject.getString("metaTitle"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("multipleOrderQuantity", fieldName)) {
				if (!Objects.deepEquals(
						product.getMultipleOrderQuantity(),
						jsonObject.getInt("multipleOrderQuantity"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", fieldName)) {
				if (!Objects.deepEquals(
						product.getName(), jsonObject.getString("name"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productId", fieldName)) {
				if (!Objects.deepEquals(
						product.getProductId(),
						jsonObject.getLong("productId"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productType", fieldName)) {
				if (!Objects.deepEquals(
						product.getProductType(),
						jsonObject.getString("productType"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("shortDescription", fieldName)) {
				if (!Objects.deepEquals(
						product.getShortDescription(),
						jsonObject.getString("shortDescription"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("url", fieldName)) {
				if (!Objects.deepEquals(
						product.getUrl(), jsonObject.getString("url"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("urlImage", fieldName)) {
				if (!Objects.deepEquals(
						product.getUrlImage(),
						jsonObject.getString("urlImage"))) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid field name " + fieldName);
		}

		return true;
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_productResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_productResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator, Product product) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("createDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(product.getCreateDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(product.getCreateDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(product.getCreateDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("description")) {
			sb.append("'");
			sb.append(String.valueOf(product.getDescription()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("expando")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("externalReferenceCode")) {
			sb.append("'");
			sb.append(String.valueOf(product.getExternalReferenceCode()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("metaDescription")) {
			sb.append("'");
			sb.append(String.valueOf(product.getMetaDescription()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("metaKeyword")) {
			sb.append("'");
			sb.append(String.valueOf(product.getMetaKeyword()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("metaTitle")) {
			sb.append("'");
			sb.append(String.valueOf(product.getMetaTitle()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("modifiedDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(product.getModifiedDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(product.getModifiedDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(product.getModifiedDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("multipleOrderQuantity")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("name")) {
			sb.append("'");
			sb.append(String.valueOf(product.getName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("productId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("productType")) {
			sb.append("'");
			sb.append(String.valueOf(product.getProductType()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("shortDescription")) {
			sb.append("'");
			sb.append(String.valueOf(product.getShortDescription()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("skus")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("tags")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("url")) {
			sb.append("'");
			sb.append(String.valueOf(product.getUrl()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("urlImage")) {
			sb.append("'");
			sb.append(String.valueOf(product.getUrlImage()));
			sb.append("'");

			return sb.toString();
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected Product randomProduct() throws Exception {
		return new Product() {
			{
				createDate = RandomTestUtil.nextDate();
				description = RandomTestUtil.randomString();
				externalReferenceCode = RandomTestUtil.randomString();
				id = RandomTestUtil.randomLong();
				metaDescription = RandomTestUtil.randomString();
				metaKeyword = RandomTestUtil.randomString();
				metaTitle = RandomTestUtil.randomString();
				modifiedDate = RandomTestUtil.nextDate();
				multipleOrderQuantity = RandomTestUtil.randomInt();
				name = RandomTestUtil.randomString();
				productId = RandomTestUtil.randomLong();
				productType = RandomTestUtil.randomString();
				shortDescription = RandomTestUtil.randomString();
				url = RandomTestUtil.randomString();
				urlImage = RandomTestUtil.randomString();
			}
		};
	}

	protected Product randomIrrelevantProduct() throws Exception {
		Product randomIrrelevantProduct = randomProduct();

		return randomIrrelevantProduct;
	}

	protected Product randomPatchProduct() throws Exception {
		return randomProduct();
	}

	protected ProductResource productResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(":");
					sb.append(entry.getValue());
					sb.append(",");
				}

				sb.setLength(sb.length() - 1);

				sb.append(")");
			}

			if (_graphQLFields.length > 0) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(",");
				}

				sb.setLength(sb.length() - 1);

				sb.append("}");
			}

			return sb.toString();
		}

		private final GraphQLField[] _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseProductResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private
		com.liferay.headless.commerce.delivery.catalog.resource.v1_0.
			ProductResource _productResource;

}
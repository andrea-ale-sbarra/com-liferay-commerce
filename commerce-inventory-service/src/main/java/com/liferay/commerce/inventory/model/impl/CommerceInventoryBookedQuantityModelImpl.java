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

package com.liferay.commerce.inventory.model.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity;
import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantityModel;
import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantitySoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceInventoryBookedQuantity service. Represents a row in the &quot;CIBookedQuantity&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>CommerceInventoryBookedQuantityModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceInventoryBookedQuantityImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryBookedQuantityImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceInventoryBookedQuantityModelImpl
	extends BaseModelImpl<CommerceInventoryBookedQuantity>
	implements CommerceInventoryBookedQuantityModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce inventory booked quantity model instance should use the <code>CommerceInventoryBookedQuantity</code> interface instead.
	 */
	public static final String TABLE_NAME = "CIBookedQuantity";

	public static final Object[][] TABLE_COLUMNS = {
		{"CIBookedQuantityId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"sku", Types.VARCHAR}, {"quantity", Types.INTEGER},
		{"expirationDate", Types.TIMESTAMP}, {"bookedNote", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CIBookedQuantityId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("sku", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("quantity", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("expirationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("bookedNote", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CIBookedQuantity (CIBookedQuantityId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,sku VARCHAR(75) null,quantity INTEGER,expirationDate DATE null,bookedNote VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table CIBookedQuantity";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceInventoryBookedQuantity.commerceInventoryBookedQuantityId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CIBookedQuantity.CIBookedQuantityId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.inventory.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.inventory.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.inventory.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity"),
		true);

	public static final long EXPIRATIONDATE_COLUMN_BITMASK = 1L;

	public static final long SKU_COLUMN_BITMASK = 2L;

	public static final long COMMERCEINVENTORYBOOKEDQUANTITYID_COLUMN_BITMASK =
		4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceInventoryBookedQuantity toModel(
		CommerceInventoryBookedQuantitySoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceInventoryBookedQuantity model =
			new CommerceInventoryBookedQuantityImpl();

		model.setCommerceInventoryBookedQuantityId(
			soapModel.getCommerceInventoryBookedQuantityId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setSku(soapModel.getSku());
		model.setQuantity(soapModel.getQuantity());
		model.setExpirationDate(soapModel.getExpirationDate());
		model.setBookedNote(soapModel.getBookedNote());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceInventoryBookedQuantity> toModels(
		CommerceInventoryBookedQuantitySoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceInventoryBookedQuantity> models =
			new ArrayList<CommerceInventoryBookedQuantity>(soapModels.length);

		for (CommerceInventoryBookedQuantitySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.inventory.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity"));

	public CommerceInventoryBookedQuantityModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceInventoryBookedQuantityId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceInventoryBookedQuantityId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceInventoryBookedQuantityId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceInventoryBookedQuantity.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceInventoryBookedQuantity.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceInventoryBookedQuantity, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry
				<String, Function<CommerceInventoryBookedQuantity, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryBookedQuantity, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceInventoryBookedQuantity)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceInventoryBookedQuantity, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceInventoryBookedQuantity, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceInventoryBookedQuantity)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceInventoryBookedQuantity, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceInventoryBookedQuantity, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceInventoryBookedQuantity>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceInventoryBookedQuantity.class.getClassLoader(),
			CommerceInventoryBookedQuantity.class, ModelWrapper.class);

		try {
			Constructor<CommerceInventoryBookedQuantity> constructor =
				(Constructor<CommerceInventoryBookedQuantity>)
					proxyClass.getConstructor(InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map
		<String, Function<CommerceInventoryBookedQuantity, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceInventoryBookedQuantity, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceInventoryBookedQuantity, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String,
					 Function<CommerceInventoryBookedQuantity, Object>>();
		Map<String, BiConsumer<CommerceInventoryBookedQuantity, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceInventoryBookedQuantity, ?>>();

		attributeGetterFunctions.put(
			"commerceInventoryBookedQuantityId",
			new Function<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public Object apply(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity) {

					return commerceInventoryBookedQuantity.
						getCommerceInventoryBookedQuantityId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceInventoryBookedQuantityId",
			new BiConsumer<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public void accept(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity,
					Object commerceInventoryBookedQuantityId) {

					commerceInventoryBookedQuantity.
						setCommerceInventoryBookedQuantityId(
							(Long)commerceInventoryBookedQuantityId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public Object apply(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity) {

					return commerceInventoryBookedQuantity.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public void accept(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity,
					Object companyId) {

					commerceInventoryBookedQuantity.setCompanyId(
						(Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public Object apply(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity) {

					return commerceInventoryBookedQuantity.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public void accept(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity,
					Object userId) {

					commerceInventoryBookedQuantity.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public Object apply(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity) {

					return commerceInventoryBookedQuantity.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public void accept(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity,
					Object userName) {

					commerceInventoryBookedQuantity.setUserName(
						(String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public Object apply(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity) {

					return commerceInventoryBookedQuantity.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public void accept(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity,
					Object createDate) {

					commerceInventoryBookedQuantity.setCreateDate(
						(Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public Object apply(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity) {

					return commerceInventoryBookedQuantity.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public void accept(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity,
					Object modifiedDate) {

					commerceInventoryBookedQuantity.setModifiedDate(
						(Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"sku",
			new Function<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public Object apply(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity) {

					return commerceInventoryBookedQuantity.getSku();
				}

			});
		attributeSetterBiConsumers.put(
			"sku",
			new BiConsumer<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public void accept(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity,
					Object sku) {

					commerceInventoryBookedQuantity.setSku((String)sku);
				}

			});
		attributeGetterFunctions.put(
			"quantity",
			new Function<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public Object apply(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity) {

					return commerceInventoryBookedQuantity.getQuantity();
				}

			});
		attributeSetterBiConsumers.put(
			"quantity",
			new BiConsumer<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public void accept(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity,
					Object quantity) {

					commerceInventoryBookedQuantity.setQuantity(
						(Integer)quantity);
				}

			});
		attributeGetterFunctions.put(
			"expirationDate",
			new Function<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public Object apply(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity) {

					return commerceInventoryBookedQuantity.getExpirationDate();
				}

			});
		attributeSetterBiConsumers.put(
			"expirationDate",
			new BiConsumer<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public void accept(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity,
					Object expirationDate) {

					commerceInventoryBookedQuantity.setExpirationDate(
						(Date)expirationDate);
				}

			});
		attributeGetterFunctions.put(
			"bookedNote",
			new Function<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public Object apply(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity) {

					return commerceInventoryBookedQuantity.getBookedNote();
				}

			});
		attributeSetterBiConsumers.put(
			"bookedNote",
			new BiConsumer<CommerceInventoryBookedQuantity, Object>() {

				@Override
				public void accept(
					CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity,
					Object bookedNote) {

					commerceInventoryBookedQuantity.setBookedNote(
						(String)bookedNote);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommerceInventoryBookedQuantityId() {
		return _commerceInventoryBookedQuantityId;
	}

	@Override
	public void setCommerceInventoryBookedQuantityId(
		long commerceInventoryBookedQuantityId) {

		_commerceInventoryBookedQuantityId = commerceInventoryBookedQuantityId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getSku() {
		if (_sku == null) {
			return "";
		}
		else {
			return _sku;
		}
	}

	@Override
	public void setSku(String sku) {
		_columnBitmask |= SKU_COLUMN_BITMASK;

		if (_originalSku == null) {
			_originalSku = _sku;
		}

		_sku = sku;
	}

	public String getOriginalSku() {
		return GetterUtil.getString(_originalSku);
	}

	@JSON
	@Override
	public int getQuantity() {
		return _quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	@JSON
	@Override
	public Date getExpirationDate() {
		return _expirationDate;
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
		_columnBitmask |= EXPIRATIONDATE_COLUMN_BITMASK;

		if (_originalExpirationDate == null) {
			_originalExpirationDate = _expirationDate;
		}

		_expirationDate = expirationDate;
	}

	public Date getOriginalExpirationDate() {
		return _originalExpirationDate;
	}

	@JSON
	@Override
	public String getBookedNote() {
		if (_bookedNote == null) {
			return "";
		}
		else {
			return _bookedNote;
		}
	}

	@Override
	public void setBookedNote(String bookedNote) {
		_bookedNote = bookedNote;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceInventoryBookedQuantity.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceInventoryBookedQuantity toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceInventoryBookedQuantity>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceInventoryBookedQuantityImpl
			commerceInventoryBookedQuantityImpl =
				new CommerceInventoryBookedQuantityImpl();

		commerceInventoryBookedQuantityImpl.
			setCommerceInventoryBookedQuantityId(
				getCommerceInventoryBookedQuantityId());
		commerceInventoryBookedQuantityImpl.setCompanyId(getCompanyId());
		commerceInventoryBookedQuantityImpl.setUserId(getUserId());
		commerceInventoryBookedQuantityImpl.setUserName(getUserName());
		commerceInventoryBookedQuantityImpl.setCreateDate(getCreateDate());
		commerceInventoryBookedQuantityImpl.setModifiedDate(getModifiedDate());
		commerceInventoryBookedQuantityImpl.setSku(getSku());
		commerceInventoryBookedQuantityImpl.setQuantity(getQuantity());
		commerceInventoryBookedQuantityImpl.setExpirationDate(
			getExpirationDate());
		commerceInventoryBookedQuantityImpl.setBookedNote(getBookedNote());

		commerceInventoryBookedQuantityImpl.resetOriginalValues();

		return commerceInventoryBookedQuantityImpl;
	}

	@Override
	public int compareTo(
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity) {

		long primaryKey = commerceInventoryBookedQuantity.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceInventoryBookedQuantity)) {
			return false;
		}

		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
			(CommerceInventoryBookedQuantity)obj;

		long primaryKey = commerceInventoryBookedQuantity.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CommerceInventoryBookedQuantityModelImpl
			commerceInventoryBookedQuantityModelImpl = this;

		commerceInventoryBookedQuantityModelImpl._setModifiedDate = false;

		commerceInventoryBookedQuantityModelImpl._originalSku =
			commerceInventoryBookedQuantityModelImpl._sku;

		commerceInventoryBookedQuantityModelImpl._originalExpirationDate =
			commerceInventoryBookedQuantityModelImpl._expirationDate;

		commerceInventoryBookedQuantityModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceInventoryBookedQuantity> toCacheModel() {
		CommerceInventoryBookedQuantityCacheModel
			commerceInventoryBookedQuantityCacheModel =
				new CommerceInventoryBookedQuantityCacheModel();

		commerceInventoryBookedQuantityCacheModel.
			commerceInventoryBookedQuantityId =
				getCommerceInventoryBookedQuantityId();

		commerceInventoryBookedQuantityCacheModel.companyId = getCompanyId();

		commerceInventoryBookedQuantityCacheModel.userId = getUserId();

		commerceInventoryBookedQuantityCacheModel.userName = getUserName();

		String userName = commerceInventoryBookedQuantityCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceInventoryBookedQuantityCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceInventoryBookedQuantityCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceInventoryBookedQuantityCacheModel.createDate =
				Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceInventoryBookedQuantityCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceInventoryBookedQuantityCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commerceInventoryBookedQuantityCacheModel.sku = getSku();

		String sku = commerceInventoryBookedQuantityCacheModel.sku;

		if ((sku != null) && (sku.length() == 0)) {
			commerceInventoryBookedQuantityCacheModel.sku = null;
		}

		commerceInventoryBookedQuantityCacheModel.quantity = getQuantity();

		Date expirationDate = getExpirationDate();

		if (expirationDate != null) {
			commerceInventoryBookedQuantityCacheModel.expirationDate =
				expirationDate.getTime();
		}
		else {
			commerceInventoryBookedQuantityCacheModel.expirationDate =
				Long.MIN_VALUE;
		}

		commerceInventoryBookedQuantityCacheModel.bookedNote = getBookedNote();

		String bookedNote =
			commerceInventoryBookedQuantityCacheModel.bookedNote;

		if ((bookedNote != null) && (bookedNote.length() == 0)) {
			commerceInventoryBookedQuantityCacheModel.bookedNote = null;
		}

		return commerceInventoryBookedQuantityCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceInventoryBookedQuantity, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry
				<String, Function<CommerceInventoryBookedQuantity, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryBookedQuantity, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceInventoryBookedQuantity)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<CommerceInventoryBookedQuantity, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry
				<String, Function<CommerceInventoryBookedQuantity, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryBookedQuantity, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceInventoryBookedQuantity)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceInventoryBookedQuantity>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _commerceInventoryBookedQuantityId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _sku;
	private String _originalSku;
	private int _quantity;
	private Date _expirationDate;
	private Date _originalExpirationDate;
	private String _bookedNote;
	private long _columnBitmask;
	private CommerceInventoryBookedQuantity _escapedModel;

}
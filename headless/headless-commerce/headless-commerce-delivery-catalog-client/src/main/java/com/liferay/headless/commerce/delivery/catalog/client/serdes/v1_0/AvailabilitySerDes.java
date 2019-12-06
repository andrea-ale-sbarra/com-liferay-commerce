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

package com.liferay.headless.commerce.delivery.catalog.client.serdes.v1_0;

import com.liferay.headless.commerce.delivery.catalog.client.dto.v1_0.Availability;
import com.liferay.headless.commerce.delivery.catalog.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Generated("")
public class AvailabilitySerDes {

	public static Availability toDTO(String json) {
		AvailabilityJSONParser availabilityJSONParser =
			new AvailabilityJSONParser();

		return availabilityJSONParser.parseToDTO(json);
	}

	public static Availability[] toDTOs(String json) {
		AvailabilityJSONParser availabilityJSONParser =
			new AvailabilityJSONParser();

		return availabilityJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Availability availability) {
		if (availability == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (availability.getAvailabilityLabel() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"availabilityLabel\": ");

			sb.append("\"");

			sb.append(_escape(availability.getAvailabilityLabel()));

			sb.append("\"");
		}

		if (availability.getAvailabilityNumber() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"availabilityNumber\": ");

			sb.append(availability.getAvailabilityNumber());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AvailabilityJSONParser availabilityJSONParser =
			new AvailabilityJSONParser();

		return availabilityJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Availability availability) {
		if (availability == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (availability.getAvailabilityLabel() == null) {
			map.put("availabilityLabel", null);
		}
		else {
			map.put(
				"availabilityLabel",
				String.valueOf(availability.getAvailabilityLabel()));
		}

		if (availability.getAvailabilityNumber() == null) {
			map.put("availabilityNumber", null);
		}
		else {
			map.put(
				"availabilityNumber",
				String.valueOf(availability.getAvailabilityNumber()));
		}

		return map;
	}

	public static class AvailabilityJSONParser
		extends BaseJSONParser<Availability> {

		@Override
		protected Availability createDTO() {
			return new Availability();
		}

		@Override
		protected Availability[] createDTOArray(int size) {
			return new Availability[size];
		}

		@Override
		protected void setField(
			Availability availability, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "availabilityLabel")) {
				if (jsonParserFieldValue != null) {
					availability.setAvailabilityLabel(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "availabilityNumber")) {

				if (jsonParserFieldValue != null) {
					availability.setAvailabilityNumber(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}
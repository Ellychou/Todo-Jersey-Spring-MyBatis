/**
 * Copyright (c) 2015 Ovitas Inc, All rights reserved.
 */
package com.ellychou.todo.rest.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author szhou
 * @version 1.0.1
 * @since 2015/3/3
 */
public class MsgUtils {

	private MsgUtils() {
	}

	public static Map<String, Serializable> msg(final Serializable value) {
		return msg("message", value);
	}

	public static Map<String, Serializable> msg(final String key, final Serializable value) {
		return new HashMap<String, Serializable>() {
			{
				put(key, value);
			}
		};
	}
}
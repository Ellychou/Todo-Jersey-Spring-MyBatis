/**
 * Copyright (c) 2015 Ovitas Inc, All rights reserved.
 */
package com.ellychou.todo.rest.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hzhou [hao.zhou@ovitas.com] Since 2015-03-12
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
package com.spotify.utility;

import java.util.concurrent.ConcurrentHashMap;

public class ScenarioContext {

	final static ConcurrentHashMap<String, Object> context = new ConcurrentHashMap<>();

	public static void set(String key, Object value) {
		context.put(key, value);
	}

	public static Object get(String key) {
		return context.get(key);
	}

	public static void clear() {
		context.clear();
	}

}

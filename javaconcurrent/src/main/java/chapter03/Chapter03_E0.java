package chapter03;

import java.util.HashMap;
import java.util.Map;

/**
 * @author quanhangbo
 * @date 2022/12/7 22:37
 */
public class Chapter03_E0 {

	public Chapter03_E0() {
	
	}
	
	// 封装ThreadLocal相关工具类
	public static ThreadLocal<Map<String, Object>> THREADLOCAL_MAP = new MapThreadLocal();
	
	public static Map<String, Object> getThreadMap() {
		return THREADLOCAL_MAP.get();
	}
	
	public static void put(String key, Object value) {
		getThreadMap().put(key, value);
	}
	
	public static Object get(String key) {
		return getThreadMap().get(key);
	}
	
	public static Object remove(String key) {
		return getThreadMap().remove(key);
	}
	
	public static void remove() {
		getThreadMap().clear();
	}
	
	
	private static class MapThreadLocal extends ThreadLocal<Map<String, Object>> {
		
		@Override
		protected Map<String, Object> initialValue() {
			return new HashMap<String, Object>(16) {
				@Override
				public Object put(String key, Object value) {
					return super.put(key, value);
				}
			};
			
		}
	}
}


package com.shtm.util;

import java.util.List;

import redis.clients.jedis.Jedis;

import com.shtm.util.Static.REDIS;

public class RedisUtil extends ClasssPathProps {
	/**
	 * redis(jedis)
	 */
	public final static Jedis jedis = new Jedis(
			getValue(REDIS.FILED_REDIS_HOST).toString());

	public static void main(String[] args) {
		// 存储数据到列表中
//		jedis.lpush("site-list", "Runoob");
//		jedis.lpush("site-list", "Google");
//		jedis.lpush("site-list", "Taobao");
		// 获取存储的数据并输出
		List<String> list = jedis.lrange("site-list", 0, 2);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("列表项为: " + list.get(i));
		}
		jedis.lpush("site-s", "");
		System.out.println(jedis.lrange("site-s",0,-1));
	}

	/**
	 * Title:getJedisList
	 * <p>
	 * Description:獲取redis的list
	 * <p>
	 * 
	 * @author Kor_Zhang
	 * @date 2017年9月27日 上午9:51:00
	 * @version 1.0
	 * @param key
	 * @return
	 */
	public static <T> List<T> redisGetList(String key, int start, int end) {
		try {
			return (List<T>) jedis.lrange(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Title:redisPush
	 * <p>
	 * Description:
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 上午9:58:36
	 * @version 1.0
	 * @param key
	 * @param value	可以是一个string数组，也可以是单个字符串
	 */
	public static void redisPush(String key, String... value) {
		try {
			jedis.lpush(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Title:redisSet
	 * <p>
	 * Description:更新
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 上午10:01:38
	 * @version 1.0
	 * @param key
	 * @param index
	 * @param value
	 */
	public static void redisSet(String key, int index, String value) {
		try {
			jedis.lset(key, index, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Title:redisRemove
	 * <p>
	 * Description:删除
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 上午10:02:19
	 * @version 1.0
	 * @param key
	 * @param count
	 * @param value
	 */
	public static void redisRemove(String key, int count, String value) {
		try {
			jedis.lset(key, count, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Title:redisListSize
	 * <p>
	 * Description:查看list的长度
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 上午10:03:34
	 * @version 1.0
	 * @param key
	 * @return
	 */
	public static Long redisListSize(String key) {
		try {
			return jedis.llen(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0l;
	}

}

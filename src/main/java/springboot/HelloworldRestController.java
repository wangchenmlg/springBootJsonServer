package springboot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.ldap.Rdn;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tools.MapUtil;

@RestController
public class HelloworldRestController {
	@Autowired  
    HttpServletRequest request;
	
	@Autowired
	private TestMapper mapper;
	
	private static Logger logger = LoggerFactory.getLogger(HelloworldRestController.class);
//	private Logger testLog = LoggerFactory.getLogger("mytest");  //todo: valid

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Autowired
    private RedisUtil redis;
	
    @RequestMapping("/")
    public String helloworld(){
    	RoncooUser roncooUser = new RoncooUser();
		roncooUser.setName("test-" + ((int)(Math.random()*1000000)));
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
		roncooUser.setCreateTime(sdf.format(new Date()));
		int result = mapper.insert(roncooUser);
		
		logger.trace("这是 trace 日志...");
        logger.debug("这是 debug 日志...");
        logger.info("这是 info 日志...");
        logger.warn("这是 warn 日志...");
        logger.error("这是 error 日志...");
        
    	stringRedisTemplate.opsForValue().set("key-redis", roncooUser.getName());
    	redis.set("key-redis2", roncooUser.getCreateTime());
    	logger.info("set the redis test value is:" + roncooUser.getName());
    	logger.info("start incre the redis [incre]:" + redis.incr("incre", 2));
    	logger.info("start decre the redis [incre]:" + redis.decr("incre", 1));
    	
    	redisMapTest("putin");
    	redisSetTest("putin");
    	redisListTest("putin");
        
        return "hello world, welcome to the spring boot everiment.";
    }

	@RequestMapping("/json")
    public Map<String, Object> getJson(){
    	Map<String, Object> res = new HashMap<String, Object>();
    	res.put("getTheKey", "json");
    	res.put("uesTec", "springBoot");
    	res.put("china", "中文の测试！~~~~");
    	res.put("request", MapUtil.getParaMap(request));
    	
    	logger.info("get the redis[key-redis][key-redis2] is " + stringRedisTemplate.opsForValue().get("key-redis") + " || " + redis.get("key-redis2"));
    	logger.info("check redis has key [key-redis(this is orgin source,warning)] [key-redis1] [key-redis2] is :" + redis.hasKey("key-redis") + " || " + 
    			redis.hasKey("key-redis1") + " || " + redis.hasKey("key-redis2"));

    	redisMapTest("result");
    	redisSetTest("result");
    	redisListTest("result");
    	
    	return res;
    }
	
	private void redisListTest(String tag){
		if(tag.equals("putin")){
			logger.info("==================list redis input start========================");
			redis.lRemoveAll("listTest");
			List<Object> tmp = new ArrayList<Object>();
			tmp.add("zhangsan");
			tmp.add("lisi");
			tmp.add("wangwu");
			redis.lSet("listTest", tmp);
			logger.info("has set the list obj:" + tmp);
			redis.lPush("listTest", "zhaoliu", "fengqi", "cuiba");
			logger.info("==================list redis input end========================");
		}else{
			logger.info("==================list redis result start========================");
			List<Object> tmp = redis.lGet("listTest", 0, -1);
			logger.info("get the list obj is :" + tmp + " \r\n and the size is:" + redis.lGetListSize("listTest") + 
					" and the space data is:" + redis.lGet("listTest", 0, 3));
			logger.info("==================list redis result end========================");
		}
	}
	
	private void redisSetTest(String tag){
		if(tag.equals("putin")){
			logger.info("==================set redis input start========================");
			Set<Object> tmp = new HashSet<Object>();
			tmp.add("zhangsan");
			tmp.add("lisi");
			tmp.add("wangwu");
			redis.sSet("setTest", tmp, "wangwu", "zhaoliu", "lisi", "zhangsan");
			logger.info("has set the new set:" + tmp);
			logger.info("==================set redis input end========================");
		}else{
			logger.info("==================set redis result start========================");
			Set<Object> tmp = redis.sGet("setTest");
			logger.info("has get the new set is:" + tmp + " and ths size is:" + redis.sGetSetSize("setTest"));
			redis.setRemove("setTest", "lisi", "wangwu");
			tmp = redis.sGet("setTest");
			logger.info("after delete the set key wangwu ,lisi then the set is:" + tmp);
			logger.info("then the key exist check [wangwu][zhaoliu] is " + 
					redis.sHasKey("setTest", "wngwu") + " | " + redis.sHasKey("setTest", "zhaoliu"));
			logger.info("==================set redis result end========================");
		}
	}
	
	private void redisMapTest(String tag) {
		if(tag.equals("putin")){
			logger.info("==================map redis input start========================");
			Map<String, Object> tmp = new HashMap<String, Object>();
			tmp.put("key1", "zhangsan");
			tmp.put("key2", "lisi");
			tmp.put("key3", "wangwu");
			redis.hmset("mapTest", tmp);
			logger.info("has insert the redis map[mapTest]:" + tmp);
			redis.hset("mapTest", "key4", "zhaoliu");
			logger.info("has inset the one key map item[key4]:zhaoliu.");
			redis.hdel("mapTest", "key1", "key2");
			logger.info("has del some redis map item key1 key2!~");
			logger.info("==================map redis input end========================");
		}else{
			logger.info("==================map redis result start========================");
			Map<Object, Object> tmp = redis.hmget("mapTest");
			logger.info("get the map redis with key[mapTest]:" + tmp);
			logger.info("has the key with map redis[key-redis2(use set mothod)][mapTest][mapTest|key1][mapTest|key3]:" + 
					redis.hasKey("key-redis2") + " || " + redis.hasKey("mapTest") + " || " + redis.hHasKey("mapTest","key1") + 
					" || " + redis.hHasKey("mapTest","key3"));
			logger.info("==================map redis result end========================");
		}
	}
}
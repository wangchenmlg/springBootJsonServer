package springboot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	private Logger testLog = LoggerFactory.getLogger("mytest");
	
    @RequestMapping("/")
    public String helloworld(){
    	RoncooUser roncooUser = new RoncooUser();
		roncooUser.setName("测试" + ((int)(Math.random()*1000000)));
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
		roncooUser.setCreateTime(sdf.format(new Date()));
		int result = mapper.insert(roncooUser);
		System.out.println("-------------->" + result);
		
		logger.trace("这是 trace 日志...");
        logger.debug("这是 debug 日志...");
        logger.info("这是 info 日志...");
        logger.warn("这是 warn 日志...");
        logger.error("这是 error 日志...");
        
        testLog.info("------->use myTest successful!~");
        
        return "hello world, welcome to the spring boot everiment.";
    }

	@RequestMapping("/json")
    public Map<String, Object> getJson(){
    	Map<String, Object> res = new HashMap<String, Object>();
    	res.put("getTheKey", "json");
    	res.put("uesTec", "springBoot");
    	res.put("china", "中文の测试！~~~~");
    	res.put("request", MapUtil.getParaMap(request));
    	
    	return res;
    }
}
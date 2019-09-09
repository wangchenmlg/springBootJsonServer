package springboot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	
    @RequestMapping("/")
    public String helloworld(){
    	RoncooUser roncooUser = new RoncooUser();
		roncooUser.setName("测试" + ((int)(Math.random()*1000000)));
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
		roncooUser.setCreateTime(sdf.format(new Date()));
		int result = mapper.insert(roncooUser);
		System.out.println(result);
		
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
package springboot;

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
	
    @RequestMapping("/")
    public String helloworld(){
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
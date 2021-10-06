package com.backend.Controller;

import com.backend.Domain.DemoFile;
import com.backend.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController//=@ResponseBody + @Controller
@RequestMapping
public class HelloController {

//    @Autowired
//    JdbcTemplate jdbcTemplate;

//    @RequestMapping("/hello")
//    public List<Map<String, Object>> hello(){
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from file");
//        return maps;
//    }
    @Autowired
    TestService testService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @RequestMapping("/hello")
    public String test(){
        return "Gotcha!";
    }

    @RequestMapping("/cantaccess")
    public String cantaccess(){
        return "???!";
    }

    @ResponseBody//返回json数据
    @GetMapping("/file")
    public DemoFile getById(@RequestParam("id") Integer id){
        return testService.getById(id);
    }

    @RequestMapping("/redis")
    public String redisTest(){
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        System.out.println(valueOperations.get("company:department:8860"));
        return valueOperations.get("company:department:8860");
    }

}


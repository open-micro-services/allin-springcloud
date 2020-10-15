package com.zmn.xht.dataservice.zmndataservice;

import com.zmn.xht.dataservice.zmndataservice.service.DataService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
//@SpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ZmnDataServiceApplicationTests {

    @Autowired
    DataService dataService;

    @Test
    public void nsjg_test(){
        long start=System.currentTimeMillis();
        String data=dataService.getNSJGByDwCode("13304319115");
        Assert.assertNotNull(data);
        long end=System.currentTimeMillis();
        System.out.println("nsjg_test Spent time:"+(end-start)+"ms");
    }

    @Test
    public void hly_test(){
        long start=System.currentTimeMillis();
        String data=dataService.getHlyByDwCode("13304319115");
        Assert.assertNotNull(data);
        long end=System.currentTimeMillis();
        System.out.println("hly_test Spent time:"+(end-start)+"ms");
    }

    @Test
    public void tree_test(){
        long start=System.currentTimeMillis();
        String data=dataService.getNSJGTreeByDwCode("13304319115");
        Assert.assertNotNull(data);
        long end=System.currentTimeMillis();
        System.out.println("tree_test Spent time:"+(end-start)+"ms");
        System.out.println("tree_test data:"+data);
    }

 /*   @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void token_password() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("grant_type", "password");
        params.add("username", "dlwy");
        params.add("password", "dlwy");
        params.add("scope", "scope1 scope2");
        String response = restTemplate.withBasicAuth("clientId", "clientSecret").
            postForObject("/oauth/token", params, String.class);
        System.out.println(response);
    }

    @Test
    public void token_client() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("grant_type", "client_credentials");
        String response = restTemplate.withBasicAuth("clientId", "clientSecret").
            postForObject("/oauth/token", params, String.class);
        System.out.println(response);
    }*/

}

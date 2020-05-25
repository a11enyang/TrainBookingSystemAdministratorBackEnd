package com.bupt.trainbookingsystem.controller;

import com.bupt.trainbookingsystem.entity.TicketManagerEntity;
import com.bupt.trainbookingsystem.service.TicketManagerService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;



/**
 * 开发者： 杨韦岽
 * 内容： 售票端
 * 开发者：徐玉韬
 * 内容：售票端管理员的登录和退出登录
 */
@Controller
public class TicketCenterController {
    public TicketManagerEntity ticketManagerEntity = new TicketManagerEntity();
    @Autowired
    public TicketManagerService ticketManagerService;
    @RequestMapping("ticketLogin")
    public String ticketLogin(){
        return "ticketlogin";
    }
    //检查管理员登录
    @RequestMapping("ticketManager/login")
    public String checkManager(@RequestParam("name") String name,
                               @RequestParam("pw") String pw, Map<String,Object> map, Model model) throws Exception {
        ticketManagerEntity = ticketManagerService.findTicketManagerEntityByNameAndPassword(name, pw);
        if(ticketManagerEntity!=null ) {
            System.out.println("用户"+name+"登录");
            //doPost("127.0.0.1:8080", String.valueOf(ticketManagerEntity.getId()));
            model.addAttribute("ticketManager",ticketManagerEntity);

            return "ticketCenter";

        }
        else{
            System.out.println("no user");
            map.put("msg1","用户名密码错误");
            return "redirect:/ticketLogin";
        }
    }
    @RequestMapping("/ticketLoginBack")
    public String backLogin(){
        return "redirect:/ticketLogin";
    }
    /**
     * post请求（用于请求json格式的参数）
     *
     * @param url  地址
     * @param params json格式的参数
     * @return
     */
    public static String doPost(String url, String params) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost( url );// 创建httpPost
        httpPost.setHeader( "Accept", "application/json" );
        httpPost.setHeader( "Content-Type", "application/json" );
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity( params, charSet );
        httpPost.setEntity( entity );
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString( responseEntity );
                return jsonString;
            } else {

            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

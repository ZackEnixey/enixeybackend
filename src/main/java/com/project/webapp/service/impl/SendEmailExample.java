package com.project.webapp.service.impl;



import javax.mail.Message;

import com.project.webapp.util.ConfigureMessage;
import com.project.webapp.util.ConfigureTemplate;

import java.util.HashMap;
import java.util.Map;

public class SendEmailExample {

    public static void main(String[] args) {

        try {
            Message message = ConfigureMessage.message("zoran.zilic.zack@gmail.com", "", "", "Confirm Registration");
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("name", "Ivana");
            paramMap.put("token", "123456");
            paramMap.put("password", "password");
            boolean b = ConfigureTemplate.template(message, SendEmailExample.class, "confirmRegistrationTemplate.ftl", paramMap);

            if (b) {
                System.out.print("Success");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}


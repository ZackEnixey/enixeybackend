package com.project.webapp.util;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.project.webapp.service.impl.SendEmailExample;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class ConfigureTemplate {

    public static boolean template(Message message, Class<?> classForTemplateLoading, String templateName, Map<?, ?> paramMap) throws IOException, TemplateException, MessagingException {
        try {
            Configuration cfg = new Configuration();
            // Assume that the template is available under /src/main/resources/templates
            cfg.setClassForTemplateLoading(SendEmailExample.class, "/templates/");
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template template = cfg.getTemplate(templateName);

            Writer out = new StringWriter();

            template.process(paramMap, out);
            BodyPart body = new MimeBodyPart();
            body.setContent(out.toString(), "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);
            message.setContent(multipart);
            new Thread(() -> {
                try {
                    Transport.send(message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (MessagingException | IOException | TemplateException e) {
            return false;
        }
        return true;
    }
}
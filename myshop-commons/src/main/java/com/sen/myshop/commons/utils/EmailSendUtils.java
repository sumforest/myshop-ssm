package com.sen.myshop.commons.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: Sen
 * @Date: 2019/8/13 00:31
 * @Description:
 */
public class EmailSendUtils {
    @Autowired
    Email email;
    public void sendEmail(String subject,String mesg,String...to) throws EmailException {
        email.setSubject(subject);
        email.setMsg(mesg);
        email.addTo(to);
        email.send();
    }
}

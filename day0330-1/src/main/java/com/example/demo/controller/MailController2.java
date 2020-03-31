package com.example.demo.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.EmpDao;
import com.example.demo.vo.EmpVo;

@Controller
public class MailController2 {

	@Autowired
	private EmpDao dao;
	
	
	
   public void setDao(EmpDao dao) {
		this.dao = dao;
	}


   @Autowired
   private JavaMailSender mailSender;

   public void setJavaMailSender(JavaMailSender mailSender) {
      this.mailSender = mailSender;
   }
 
   @RequestMapping("/mail2.do")
   @Scheduled(cron="30 20 4 30 * ?")
   public void mail()
   {
	   List<EmpVo> list = dao.sal();
	   
	   for(EmpVo aa : list) {
		   System.out.println(aa.getEname() + "님" + " 이번달 수령액은" + (aa.getSal()+aa.getComm()) + "입니다");
		   
		   mailSender.send(new MimeMessagePreparator() {
			   public void prepare(MimeMessage mimeMessage) throws MessagingException {
			     MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			    // message.setFrom("1992youngsoo@gmail.com");
			    // message.setTo(aa.getEmail());
			     //message.setTo("1992youngsoo@gmail.com");
			     message.setSubject("수령액");
			     message.setText(aa.getEname() + "님" + " 이번달 수령액은" + (aa.getSal()+aa.getComm()) + "입니다", true);
			     //message.addInline("myLogo", new ClassPathResource("static/mylogo.gif"));
			     //message.addAttachment("myDocument.pdf", new ClassPathResource("doc/myDocument.pdf"));
			   }
			 });
	   }
	   
   }
}
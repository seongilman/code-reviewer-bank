package com.example.demo.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor

public class AlarmMailSender {
    @Autowired
    JavaMailSender javaMailSender;

    void sendMail(MailVO mailVO) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        mimeMessageHelper.setTo(mailVO.getTo()); // 메일 수신자
        mimeMessageHelper.setSubject("코드리뷰어 과제 테스트"); // 메일 제목
        mimeMessageHelper.setText(mailVO.getContents(), true); // 메일 본문 내용, HTML 여부
//        mimeMessageHelper.setText(getHtml("templates/email.html").replace("#m1#", "12345"), true); // 메일 본문 내용, HTML 여부
        javaMailSender.send(mimeMessage);
    }

//    private static String getHtml(String path) throws IOException {
//        ClassPathResource resource = new ClassPathResource(path);
//        byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
//        return new String(bdata, StandardCharsets.UTF_8);
//    }
}

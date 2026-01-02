package com.smtpbenchmark.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.smtpbenchmark.entity.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    
    @Value("${spring.mail.address}")
    private String address;


    public ArrayList<Message> sendNMessages(Integer n, String object, String messageText, MultipartFile attachment) throws MessagingException, IOException{
        ArrayList<Message> messagesToSend = new ArrayList<>();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(address);
        mimeMessageHelper.setText(messageText, false);
        mimeMessageHelper.setTo("admin@test.com");
        mimeMessageHelper.setSubject(object);
        if (attachment != null && !attachment.isEmpty()) {
            mimeMessageHelper.addAttachment(attachment.getOriginalFilename(), attachment);
        }
        for(int i=0; i < n; i++){
            Message message = new Message();
            if (attachment != null && !attachment.isEmpty()) {
                message.setAttachment("Sì");
            } else {
                message.setAttachment("No");
            }
            message.setMessage(messageText);
            message.setObject(object);
            message.setSendingTime(System.currentTimeMillis());
            mailSender.send(mimeMessage); //il metodo send() si mette in ascolto fino a quando il messaggio non viene ricevuto
            message.setReceptionTime(System.currentTimeMillis());
            message.setDurationTime(message.getReceptionTime() - message.getSendingTime());
            messagesToSend.add(message);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mimeMessage.writeTo(outputStream);
            long totalSizeInBytes = outputStream.size(); 
            String messageSize;
            if(totalSizeInBytes < 1024){
                messageSize = String.valueOf(totalSizeInBytes) + " B";
            }
            else {
                messageSize = String.valueOf(totalSizeInBytes / 1024) + " KB";
            }
            message.setSize(messageSize);
        }
        
        
        return messagesToSend;

    }
    
}

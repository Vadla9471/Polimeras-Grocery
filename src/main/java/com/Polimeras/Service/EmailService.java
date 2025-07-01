package com.Polimeras.Service;

import com.Polimeras.Entity.Users;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
//    public String
    public void emailHandler(String toEmail,String firstname ,String lastname,long id) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);

            helper.setFrom("manapolimeras@gmail.com");
//            helper.setFrom(users.getEmail());
            helper.setTo(toEmail);
            helper.setSubject("Mana Polimeraas welcomes you Mr./Ms. " + firstname + lastname);

            helper.setText(
                    "Id : " + id +
                    "Name : " + firstname + " " + lastname

            );
            helper.addAttachment("polimeras Logo.png", new File("C:\\Users\\vadla vineeth kumar\\Downloads\\polimeras Logo.png"));
            javaMailSender.send(message);
            System.out.println("Mail sent");

        } catch (Exception e) {
            System.out.println("Mail Not sent");
            System.out.println(e.getMessage());
        }
    }

    public void newRegister(String firstname, long id, String toEmail) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);

            helper.setFrom("manapolimeras@gmail.com");
//            helper.setFrom(users.getEmail());
            helper.setTo(toEmail);
            helper.setSubject("Mana Polimeraas welcomes you Mr./Ms. " + firstname);

            helper.setText(
                    "Hii " + firstname + "this is yours id : " + id

            );
//            helper.addAttachment("polimeras Logo.png", new File("C:\\Users\\vadla vineeth kumar\\Downloads\\polimeras Logo.png"));
            javaMailSender.send(message);
            System.out.println("Mail sent to : " + toEmail);

        } catch (Exception e) {
            System.out.println("Mail Not sent");
            System.out.println(e.getMessage());
        }
    }

    private void sendEmail(String toEmail, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body,false);

            javaMailSender.send(message);
            System.out.println("Mail sent to : " + toEmail);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    private void sendSimpleEmail(String toEmail, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false);

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body);

            javaMailSender.send(message);
            System.out.println("Mail sent to : " + toEmail);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void sendOtpEmail(String toEmail, String otpCode) {
        String subject = "Your Password Reset Code";
        String body = "Dear Customer,\n\n"
                + "Your OTP for resetting your password is: " + otpCode + "\n"
                + "This code will expire in 5 minutes.\n\n"
                + "Best regards,\nTeam";
        System.out.println("Mail sent to : " + toEmail);
        sendEmail(toEmail, subject, body);
    }

    public void sendResetPasswordConfirmationEmail(String toEmail) {
        String subject = "Your Password Has Been Successfully Reset";
        String body = "Hello,\n\n"
                + "This is a confirmation that your password has been successfully updated.\n\n"
                + "If you did not perform this action, please contact our support team immediately.\n\n"
                + "Thank you,\n"
                + "Mana Polimeraas - Keep Smile...";
        System.out.println("Mail sent to : " + toEmail);
        sendSimpleEmail(toEmail, subject, body);
    }
}

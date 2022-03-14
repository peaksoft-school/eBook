package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.aws.service.FileService;
import kg.ebooks.eBook.db.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * created by Beksultan Mamatkadyr uulu
 * 23/2/22
 * Wednesday 23:02
 * hello world
 */
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final FileService fileService;

    @Value("${from}")
    private String from;

    @Value("${subject}")
    private String subject;

    @Override
    public void send(String to, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(message);
        this.javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void send(String to, MultipartFile file) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(subject);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setReplyTo(from);
        helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
        javaMailSender.send(message);
    }

    @Override
    public void send(String to, DataSource file) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(subject);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setReplyTo(from);
        helper.addAttachment("file", file);
        javaMailSender.send(message);
    }
}



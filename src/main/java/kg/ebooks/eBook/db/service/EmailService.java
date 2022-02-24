package kg.ebooks.eBook.db.service;

import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

/**
 * created by Beksultan Mamatkadyr uulu
 * 23/2/22
 * Wednesday 23:01
 * hello world
 */
public interface EmailService {
    void send(String to, String message);
    void send(String to, MultipartFile file) throws MessagingException;
}

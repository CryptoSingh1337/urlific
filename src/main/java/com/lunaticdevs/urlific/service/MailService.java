package com.lunaticdevs.urlific.service;

/**
 * @author Saransh Kumar
 */

public interface MailService {

    void sendMail(String email, String text, boolean isHtml);
}

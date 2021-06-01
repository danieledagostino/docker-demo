package org.pincio.games.dto;

import java.util.List;

public class Mail {

    private String title; // (String, Optional) the email's title.
    private String html; // (String, Optional) the email's body.
    private String attachments; // (Array[String], Optional) the System ID of the attachment.

    private List<Recipient> recipients; // (Array, Optional).


    //private String cc: (Object, Optional)


    private String from; // (Object, Optional) the address the email will be sent from (default; // account primary email). Make sure the chosen email is verified (you can add and verify emails using another request).
    private String email; // (String) the email address of the sender.
    private String name; // (String) the name of the sender.

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.example.demo.mail;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MailVO {
    private String to;
    private String subject;
    private String contents;
}

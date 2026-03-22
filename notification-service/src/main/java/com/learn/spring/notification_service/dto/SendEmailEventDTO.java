package com.learn.spring.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SendEmailEventDTO {
    private String toEmail;
    private String subject;
    private String body;
}

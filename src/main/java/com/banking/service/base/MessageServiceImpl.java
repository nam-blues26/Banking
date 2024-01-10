package com.banking.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code) {
        try {
            return messageSource.getMessage(code, null, Locale.getDefault());
        } catch (Exception ex) {
            return code;
        }
    }
}

package com.spring.mscustomer.util;

import org.springframework.stereotype.Service;

@Service
public class DocumentFormatService {
    public String execute(String document){
        if(document != null || !document.isEmpty()){
            return document.replace(".","")
                    .replace("-","")
                    .replace("/","");
        }

        return null;
    }
}
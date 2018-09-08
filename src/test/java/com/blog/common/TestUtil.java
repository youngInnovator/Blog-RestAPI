package com.blog.common;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
	
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}

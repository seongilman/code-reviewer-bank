package com.example.demo.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class HtmlFileReaderService {

    private final ResourceLoader resourceLoader;

    public String readHtmlFile(String filePath) throws IOException {
        // 리소스를 로드합니다.
        Resource resource = resourceLoader.getResource("classpath:" + filePath);

        // 리소스에서 문자열을 읽어옵니다.
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        }
    }
}
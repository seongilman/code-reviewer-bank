package com.example.demo.mail;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@Getter
@RequiredArgsConstructor
public class MailQueueWatcher implements Runnable {

    private final BlockingQueue<String> mailQueue = new LinkedBlockingQueue<>();

    @Autowired
    private final AlarmMailSender sender;

    @Autowired
    private final HtmlFileReaderService service;

    @Autowired
    private final ProductService productService;

    @Override
    public void run() {
        try {
            String value = "";
            while((value = mailQueue.take()) != "exit") {
                long productId = Long.parseLong(value);
                System.out.println("productId = " + productId);
                MailVO mailVO = getNewProductMail(productId);
                sender.sendMail(mailVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private MailVO getNewProductMail(Long productId) throws IOException {
        Product product = productService.findProduct(productId);
        Map<String, String> param = new HashMap<>();
        param.put("korCoNm", product.getKorCoNm());
        param.put("finPrdtNm", product.getFinPrdtNm());

        String contents = service.readHtmlFile("templates/newProduct.html");

        for (String key : param.keySet()) {
            String value = param.get(key);
            contents = contents.replace("#" + key + "#", value);
        }

        MailVO mailVO = new MailVO();
        mailVO.setTo("im.sung@miracom-inc.com");
        mailVO.setSubject("새로운 상품");
        mailVO.setContents(contents);

        return mailVO;
    }
}

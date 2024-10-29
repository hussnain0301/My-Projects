package com.example.QrCodeEmbedding.Controller;

import com.example.QrCodeEmbedding.Services.QRCodeGeneratorService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class QRCodeController {

    private static final String DEFAULT_REDIRECT_URL = "https://youtu.be/X0Lrfo5J_YA?si=gAjipFRrKlvuv2jT";

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;

    // Generate the QR code and return it as a downloadable image
    @GetMapping(value = "/generateQRCode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode() throws WriterException, IOException {
        byte[] qrCode = qrCodeGeneratorService.getQRCodeImage(DEFAULT_REDIRECT_URL, 350, 350);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCode);
    }

    // When someone accesses the URL encoded in the QR code, redirect them to the desired page
    @GetMapping("/redirect")
    public String redirectToDefaultUrl() {
        // This will redirect to the default URL
        return "redirect:" + DEFAULT_REDIRECT_URL;
    }
}


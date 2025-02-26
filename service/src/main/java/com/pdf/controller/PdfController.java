package com.pdf.controller;


import com.pdf.commons.dto.RequestDTO;
import com.pdf.service.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/v1")
public class PdfController {

    public final PdfGeneratorService pdfGeneratorService;

    @Autowired
    public PdfController(PdfGeneratorService pdfGeneratorService){
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @PostMapping(path = "/pdf/generate", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<byte[]> generatePdf(@ModelAttribute RequestDTO request) {




        ByteArrayOutputStream generated = pdfGeneratorService.generateFile(request);

        byte[] bytes = generated.toByteArray();
        
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(bytes.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + request.getName() + "\"")
                .body(bytes);
    }
}

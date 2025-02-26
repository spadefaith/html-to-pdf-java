package com.pdf.service.impl;

import com.pdf.commons.dto.RequestDTO;
import com.pdf.exceptions.PdfGenerationException;
import com.pdf.service.PdfGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;

@Slf4j
@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

    @Override
    public ByteArrayOutputStream generateFile(RequestDTO request){

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(request.getHtml());
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream, false);
            renderer.finishPDF();

            return byteArrayOutputStream;
        } catch (Exception e) {
            log.error("Error while generating PDF file", e);

            throw new PdfGenerationException(e.getMessage());
        }

    }

}

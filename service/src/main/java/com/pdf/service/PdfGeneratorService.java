package com.pdf.service;

import com.pdf.commons.dto.RequestDTO;

import java.io.ByteArrayOutputStream;

public interface PdfGeneratorService {
    ByteArrayOutputStream generateFile(RequestDTO html);
}

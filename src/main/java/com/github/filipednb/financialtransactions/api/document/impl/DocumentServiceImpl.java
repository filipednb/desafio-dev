package com.github.filipednb.financialtransactions.api.document.impl;

import com.github.filipednb.financialtransactions.api.document.DocumentEntity;
import com.github.filipednb.financialtransactions.api.document.DocumentRepository;
import com.github.filipednb.financialtransactions.api.document.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);

    private DocumentRepository repository;

    public DocumentServiceImpl(DocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public DocumentEntity retrieveDocument(String numDocument) {
        log.info("M=retrieveDocument, I=Retrieving document, numDocument={}", numDocument);

        DocumentEntity document = repository.findByNumDocument(numDocument);

        if(document == null) {
            log.info("M=retrieveDocument, I=Will document, numDocument={}", numDocument);
            return repository.save(new DocumentEntity(numDocument));
        }
        return document;
    }
}

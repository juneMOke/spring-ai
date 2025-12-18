package com.junethink.spring_ai_project.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentDataLoader {

    private final VectorStore vectorStore;

    @Value("classpath:/static/policy-acme.pdf")
    private Resource policyAcmePdf;

    public DocumentDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    void loadData() {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(policyAcmePdf);

        List<Document> documents = tikaDocumentReader.get();
        TokenTextSplitter dataSplitter = TokenTextSplitter
                .builder()
                .withChunkSize(100)
                .withMaxNumChunks(400)
                .build();
        vectorStore.add(dataSplitter.split(documents));
    }
}

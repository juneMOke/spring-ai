package com.junethink.spring_ai_project.service;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * A component that loads document data into the vector store at application startup.
 */
@Component
public class DocumentDataLoader {

    private final VectorStore vectorStore;

    @Value("classpath:/static/policy-acme.pdf")
    private Resource policyAcmePdf;

    public DocumentDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    /**
     * Loads data from the specified PDF document into the vector store.
     */
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

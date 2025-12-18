package com.junethink.spring_ai_project.controller;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rag/questions")
class RagAssistantController {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:/promptTemplates/ragPromptTemplate.st")
    private Resource ragAssistantHrTemplate;

    public RagAssistantController(@Qualifier("chatClientWithRag") ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @PostMapping
    public String askQuestion(@RequestParam("question") String question) {

        String aggregatedResults = retrieveMatchingDocuments(question);

        return chatClient
                .prompt()
                 .system(promptSystemSpec -> promptSystemSpec.text(ragAssistantHrTemplate)
                         .param("context", aggregatedResults))
                .user(question)
                .call()
                .content();

    }


    private @NonNull String retrieveMatchingDocuments(String question) {
        SearchRequest searchRequest = SearchRequest
                .builder()
                .query(question)
                .topK(3)
                .similarityThreshold(0.5)
                .build();
        List<Document> documents = vectorStore.similaritySearch(searchRequest);

        assert documents != null;

        return documents.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n"));
    }

}

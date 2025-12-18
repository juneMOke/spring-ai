package com.junethink.spring_ai_project.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/basic/questions")
public class BasicAssistantController {


    private final ChatClient chatClient;

    public BasicAssistantController(@Qualifier("basicChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @PostMapping
    public String askQuestion(@PathParam("question") String question) {
        return chatClient
                .prompt()
                .user(question)
                .call()
                .content();
    }

    @GetMapping("/stream")
    public Flux<String> askQuestionAndGetStreamResponse(@PathParam("question") String question) {
        return chatClient
                .prompt()
                .user(question)
                .stream()
                .content();
    }
}

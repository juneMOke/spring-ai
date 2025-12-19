package com.junethink.spring_ai_project.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * A basic assistant controller that handles questions via REST API.
 */
@RestController
@RequestMapping("/api/v1/basic/questions")
public class BasicAssistantController {


    private final ChatClient chatClient;

    public BasicAssistantController(@Qualifier("basicChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    /**
     * Handles POST requests to ask a question and get a response.
     * @param question the question to ask
     * @return the response from the chat client
     */
    @PostMapping
    public String askQuestion(@PathParam("question") String question) {
        return chatClient
                .prompt()
                .user(question)
                .call()
                .content();
    }

    /**
     * Handles GET requests to ask a question and get a streaming response.
     * @param question the question to ask
     * @return a Flux stream of responses from the chat client
     */
    @GetMapping("/stream")
    public Flux<String> askQuestionAndGetStreamResponse(@PathParam("question") String question) {
        return chatClient
                .prompt()
                .user(question)
                .stream()
                .content();
    }
}

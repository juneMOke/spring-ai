package com.junethink.spring_ai_project.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * A basic assistant controller that uses system prompts to guide responses.
 */
@RestController
@RequestMapping("/api/v1/prompt/questions")
public class BasicAssistantWithPrompt {

    private final ChatClient chatClient;

    @Value("classpath:/promptTemplates/assistantHR.st")
    private String assistantHR;

    public BasicAssistantWithPrompt(@Qualifier("chatClientWithHrPrompt") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /**
     * Handles POST requests to ask a question with a predefined system prompt.
     * @param question the question to ask
     * @return the response from the chat client
     */
    @PostMapping
    public String askQuestion(@RequestParam(name = "question") String question) {
        return chatClient
                .prompt()
                .system("""
                        Tu es un assistant RH interne.
                        Tu réponds uniquement aux questions liées aux ressources humaines.
                        Si une question est hors sujet, refuse poliment et explique ton périmètre.
                        Réponds toujours de manière professionnelle et concise.
                        """)
                .user(question)
                .call()
                .content();
    }
    /**
     * Handles POST requests to ask a question using an external system prompt template.
     * @param question the question to ask
     * @return the response from the chat client
     */

    @PostMapping("/template")
    public String askQuestionWithSystemTemplate(@RequestParam(name = "question") String question) {
        return chatClient
                .prompt()
                .system(assistantHR)
                .user(question)
                .call()
                .content();
    }

}

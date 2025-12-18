package com.junethink.spring_ai_project.controller;

import com.junethink.spring_ai_project.entities.Country;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
class ChatController {

    @Value("classpath:/promptTemplates/userPromptTemplate.st")
    private Resource userPromptTemplate;

    @Value("classpath:/promptTemplates/jsonPromptTemplate.st")
    private String jsonPromptTemplate;

    private final ChatClient chatClient;

    public ChatController(@Qualifier("basicChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        return chatClient
                .prompt()
                .user(promptUserSpec -> promptUserSpec
                        .text(userPromptTemplate)
                        .param("userPrompt", message))
                .call()
                .content();
    }

    @GetMapping("/sendJson")
    public Country sendJson(@RequestParam String message) {


        return chatClient
                .prompt()
                .system(jsonPromptTemplate)
                .user(message)
                .call()
                .entity(Country.class);
    }

    @GetMapping("/countries")
    public List<String> getCountries(@RequestParam String message) {

        return chatClient
                .prompt()
                .system(jsonPromptTemplate)
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<List<String>>() {
                });
    }


}

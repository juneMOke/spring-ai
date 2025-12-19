package com.junethink.spring_ai_project.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

/**
 * Controller to handle employee leave-related questions using a ChatClient with tool integration.
 */
@RestController
@RequestMapping("/users/")
public class EmployeeLeaveController {

    private final ChatClient chatClient;

    public EmployeeLeaveController(@Qualifier("chatClientWithTool") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /**
     * Handles POST requests to ask a leave-related question for a specific user.
     * @param userId the ID of the user
     * @param question the question to ask
     * @return the response from the chat client
     */

    @PostMapping("/{userId}/leaves")
    public String askQuestion(@PathVariable long userId, @RequestParam(name = "question") String question) {
        return chatClient
                .prompt()
                .user(question)
                .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID, userId))
                .toolContext(java.util.Map.of("userId", userId))
                .call()
                .content();
    }
}


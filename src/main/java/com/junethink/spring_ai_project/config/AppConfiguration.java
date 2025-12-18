package com.junethink.spring_ai_project.config;

import com.junethink.spring_ai_project.tool.EmployeeLeaveTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
class AppConfiguration {

    @Value("classpath:/promptTemplates/toolingPromptTemplate.st")
    private Resource toolsTemplate;


    @Bean(name = "basicChatClient")
    public ChatClient basicChatClient(final ChatClient.Builder builder) {
        return builder
                .build();
    }


    @Bean(name = "chatClientWithHrPrompt")
    public ChatClient promptChatClient(final ChatClient.Builder builder) {
        return builder
                .build();
    }

    @Bean(name = "chatClientWithRag")
    public ChatClient ragChatClient(final ChatClient.Builder builder) {
        return builder
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor()))
                .build();
    }

    @Bean(name = "chatClientWithTool")
    public ChatClient toolsChatClient(final ChatClient.Builder chatClientBuilder, final EmployeeLeaveTool leaveTool, ChatMemory chatMemory) {
        return chatClientBuilder
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), MessageChatMemoryAdvisor.builder(chatMemory).build()))
                .defaultTools(leaveTool)
                .defaultSystem(toolsTemplate)
                .build();
    }


    @Bean(name = "chatClient")
    public ChatClient chatClient(final ChatClient.Builder builder) {
        return builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @Bean(name = "assistantClient")
    public ChatClient assistantClient(final ChatClient.Builder chatClientBuilder, ChatMemory chatMemory) {
        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor
                .builder(chatMemory)
                .build();

        SimpleLoggerAdvisor loggerAdvisor = new SimpleLoggerAdvisor();

        return chatClientBuilder
                .defaultAdvisors(List.of(loggerAdvisor, messageChatMemoryAdvisor))
                .build();
    }
}

package com.example.codealgorithm.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Profile("ai")
public class AiChatbotService {

    private final ChatClient chatClient;
    private final PromptTemplate customPromptTemplate;
    private final VectorStore vectorStore;

    public AiChatbotService(ChatClient chatClient, PromptTemplate customPromptTemplate, VectorStore vectorStore) {
    this.chatClient = chatClient;
        this.customPromptTemplate = customPromptTemplate;
        this.vectorStore = vectorStore;
    }

public String chat(String message) {
        return chatClient.call(message);
    }

    public String chatWithRag(String message) {
        List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.query(message).withTopK(3));
        
        String context = similarDocuments.stream()
                .map(Document::getContent)
                .reduce((a, b) -> a + "\n\n" + b)
                .orElse("");

        String prompt = customPromptTemplate.render(Map.of(
                "context", context,
                "question", message
        ));
        return chatClient.call(prompt);
    }

    public String chatWithCustomPrompt(String message, String customSystemPrompt) {
        Prompt prompt = new Prompt(customSystemPrompt + "\n\n" + message);
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }

    public String chatWithRagAndCustomPrompt(String message, String customSystemPrompt) {
        List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.query(message).withTopK(3));
        
        String context = similarDocuments.stream()
                .map(Document::getContent)
                .reduce((a, b) -> a + "\n\n" + b)
                .orElse("");

        String userPrompt = customPromptTemplate.render(Map.of(
                "context", context,
                "question", message
        ));
        Prompt prompt = new Prompt(customSystemPrompt + "\n\n" + userPrompt);
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
}
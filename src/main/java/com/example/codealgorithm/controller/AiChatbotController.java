package com.example.codealgorithm.controller;

import com.example.codealgorithm.service.AiChatbotService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Profile("ai")
@RequestMapping("/api/chatbot")
public class AiChatbotController {

    private final AiChatbotService aiChatbotService;

    public AiChatbotController(AiChatbotService aiChatbotService) {
        this.aiChatbotService = aiChatbotService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        return aiChatbotService.chat(message);
    }

    @PostMapping("/chat/rag")
    public String chatWithRag(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        return aiChatbotService.chatWithRag(message);
    }

    @PostMapping("/chat/custom-prompt")
    public String chatWithCustomPrompt(@RequestBody Map<String, Object> request) {
        String message = (String) request.get("message");
        String customPrompt = (String) request.get("systemPrompt");
        return aiChatbotService.chatWithCustomPrompt(message, customPrompt);
    }

    @PostMapping("/chat/rag-custom")
    public String chatWithRagAndCustomPrompt(@RequestBody Map<String, Object> request) {
        String message = (String) request.get("message");
        String customPrompt = (String) request.get("systemPrompt");
        return aiChatbotService.chatWithRagAndCustomPrompt(message, customPrompt);
    }
}
package com.example.codealgorithm;

import com.example.codealgorithm.service.AiChatbotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("ai")
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+", disabledReason = "OPENAI_API_KEY environment variable is not set")
public class AiChatbotTest {

    @Autowired
    private AiChatbotService aiChatbotService;

    @Test
    void testSimpleChat() {
        String response = aiChatbotService.chat("什么是 Spring AI？");
        System.out.println("简单聊天响应: " + response);
    }

    @Test
    void testChatWithRag() {
        String response = aiChatbotService.chatWithRag("Spring AI 的核心组件有哪些？");
        System.out.println("RAG 聊天响应: " + response);
    }

    @Test
    void testChatWithCustomPrompt() {
        String customPrompt = "你是一个幽默的编程老师，用轻松有趣的方式回答问题。";
        String response = aiChatbotService.chatWithCustomPrompt("什么是 Java？", customPrompt);
        System.out.println("自定义 Prompt 响应: " + response);
    }

    @Test
    void testChatWithRagAndCustomPrompt() {
        String customPrompt = "你是一个技术专家，请用专业、详细的语言回答问题。";
        String response = aiChatbotService.chatWithRagAndCustomPrompt("RAG 是如何工作的？", customPrompt);
        System.out.println("RAG + 自定义 Prompt 响应: " + response);
    }
}
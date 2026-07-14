package com.example.codealgorithm.config;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("ai")
public class AiConfig {

    @Value("classpath:rag-data/spring-ai-demo.txt")
    private Resource ragDataResource;

    @Bean
    public VectorStore vectorStore(EmbeddingClient embeddingClient) {
        SimpleVectorStore vectorStore = new SimpleVectorStore(embeddingClient);
        
        TextReader textReader = new TextReader(ragDataResource);
        List<Document> documents = textReader.get();
        
        TokenTextSplitter splitter = new TokenTextSplitter();
        List<Document> splitDocuments = new ArrayList<>();
        for (Document doc : documents) {
            List<String> chunks = splitter.split(doc.getContent(), 512);
            for (String chunk : chunks) {
                splitDocuments.add(new Document(chunk));
            }
        }
        
        vectorStore.add(splitDocuments);
        return vectorStore;
    }

    @Bean
    public PromptTemplate customPromptTemplate() {
        String template = "你是一个专业的编程助手。请根据以下上下文信息回答用户的问题。\n\n" +
                          "上下文信息：\n{context}\n\n" +
                          "用户问题：\n{question}\n\n" +
                          "请用简洁、专业的语言回答，如果上下文信息不足以回答问题，请明确说明。";
        return new PromptTemplate(template);
    }
}
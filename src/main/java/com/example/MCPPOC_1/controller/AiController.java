package com.example.MCPPOC_1.controller;

import com.example.MCPPOC_1.dto.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final ChatClient chatClient;

    public AiController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest request) {

        if (request == null || request.getPrompt() == null || request.getPrompt().isBlank()) {
            return "Please enter a valid question.";
        }

        String response = chatClient.prompt(request.getPrompt())
                .call()
                .content();

        return safeResponse(response);
    }

    // 🔥 Prevent JSON crash leaks
    private String safeResponse(String response) {
        if (response == null || response.isBlank()) {
            return "Empty response from AI.";
        }

        if (response.trim().startsWith("{")) {
            return "AI returned structured JSON. Please simplify your question.";
        }

        return response;
    }
}

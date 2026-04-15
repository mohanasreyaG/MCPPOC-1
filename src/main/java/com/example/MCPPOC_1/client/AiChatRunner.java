package com.example.MCPPOC_1.client;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class AiChatRunner implements CommandLineRunner {
   @Autowired ChatClient chatClient;

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("🤖 AI Chat Started (type 'bye' to exit)");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine();
            if (input == null || input.trim().isEmpty()) {
                System.out.println("AI: Hey 🙂 say something... I'm listening!");
                continue;
            }
            if (input.equalsIgnoreCase("bye") || input.equalsIgnoreCase("quit")) {
                System.out.println("👋 Exiting chat...");
                break;
            }

            String response = chatClient
                    .prompt(input)
                    .call()
                    .content();
            if (response == null || response.trim().isEmpty()) {
                System.out.println("AI: Hmm... I didn't get that. Try again?");
            } else {
                System.out.println("AI: " + response);
            }
            
            System.out.println("AI: " + response);
        }
    }
}

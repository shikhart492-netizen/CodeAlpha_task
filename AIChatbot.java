import java.util.Scanner;

public class AIChatbot {

    public static String getResponse(String input) {

        input = input.toLowerCase();

        // Greetings
        if (input.contains("hello") || input.contains("hi")
                || input.contains("hey")) {
            return "Hello! How can I help you?";
        }

        // Name
        else if (input.contains("your name")) {
            return "I am JavaBot, your AI assistant.";
        }

        // Java
        else if (input.contains("java")) {
            return "Java is an object-oriented programming language.";
        }

        // BCA
        else if (input.contains("bca")) {
            return "BCA stands for Bachelor of Computer Applications.";
        }

        // Internship
        else if (input.contains("internship")) {
            return "Internships help students gain practical industry experience.";
        }

        // Programming
        else if (input.contains("programming")
                || input.contains("coding")) {
            return "Programming means writing instructions that a computer can execute.";
        }

        // AI
        else if (input.contains("artificial intelligence")
                || input.equals("ai")) {
            return "AI enables computers to perform tasks that normally require human intelligence.";
        }

        // Help
        else if (input.contains("help")) {
            return "You can ask me about Java, BCA, AI, programming or internships.";
        }

        // Bye
        else if (input.contains("bye")
                || input.contains("exit")) {
            return "Goodbye! Have a great day.";
        }

        // Default response
        else {
            return "Sorry, I don't understand that. Please ask another question.";
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("        🤖 Java AI Chatbot");
        System.out.println("=================================");
        System.out.println("Type 'exit' to stop the chatbot.\n");

        while (true) {

            System.out.print("You: ");
            String userInput = sc.nextLine();

            String response = getResponse(userInput);

            System.out.println("Bot: " + response);

            if (userInput.toLowerCase().contains("exit")
                    || userInput.toLowerCase().contains("bye")) {
                break;
            }
        }

        sc.close();
    }
}
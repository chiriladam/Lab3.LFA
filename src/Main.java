public class Main {
    public static void main(String[] args) {
        // A complex string that hits every requirement: ints, floats, math ops, and trig functions
        String input = "cos(3.14) * 52 - sin(0.5) + 10 / 2";

        System.out.println("--- Advanced Math Lexer ---");
        System.out.println("Input String: " + input);
        System.out.println("---------------------------\n");

        Lexer lexer = new Lexer(input);
        Token token = lexer.nextToken();

        // Loop through the string until we hit the End Of File (EOF)
        while (token.type != TokenType.EOF) {
            System.out.println(token);
            token = lexer.nextToken();
        }

        // Print the final EOF token
        System.out.println(token);
    }
}
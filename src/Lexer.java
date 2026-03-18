public class Lexer {
    private final String input;
    private int position;      // Current position in input (points to currentChar)
    private int readPosition;  // Current reading position (after currentChar)
    private char currentChar;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
        this.readPosition = 0;
        readChar();
    }

    // Reads the next character and advances our positions
    private void readChar() {
        if (readPosition >= input.length()) {
            currentChar = 0; // 0 is the ASCII for "NUL", representing EOF
        } else {
            currentChar = input.charAt(readPosition);
        }
        position = readPosition;
        readPosition++;
    }

    public Token nextToken() {
        Token token;
        skipWhitespace();

        switch (currentChar) {
            case '+': token = new Token(TokenType.PLUS, Character.toString(currentChar)); break;
            case '-': token = new Token(TokenType.MINUS, Character.toString(currentChar)); break;
            case '*': token = new Token(TokenType.MULTIPLY, Character.toString(currentChar)); break;
            case '/': token = new Token(TokenType.DIVIDE, Character.toString(currentChar)); break;
            case '(': token = new Token(TokenType.LPAREN, Character.toString(currentChar)); break;
            case ')': token = new Token(TokenType.RPAREN, Character.toString(currentChar)); break;
            case 0:   token = new Token(TokenType.EOF, ""); break;
            default:
                if (isDigit(currentChar)) {
                    return readNumber(); // Returns either INT or FLOAT
                } else if (isLetter(currentChar)) {
                    return readIdentifier(); // Returns SIN, COS, or ILLEGAL
                } else {
                    token = new Token(TokenType.ILLEGAL, Character.toString(currentChar));
                }
        }

        readChar();
        return token;
    }

    private void skipWhitespace() {
        while (currentChar == ' ' || currentChar == '\t' || currentChar == '\n' || currentChar == '\r') {
            readChar();
        }
    }

    private boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    private boolean isLetter(char ch) {
        return ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z');
    }

    // Handles both Integers and Floats (e.g., "42" and "3.14")
    private Token readNumber() {
        int startPos = position;
        boolean hasDecimal = false;

        while (isDigit(currentChar) || currentChar == '.') {
            if (currentChar == '.') {
                if (hasDecimal) break; // If we already saw a dot, stop (avoids "3.14.15")
                hasDecimal = true;
            }
            readChar();
        }

        String numberLiteral = input.substring(startPos, position);
        TokenType type = hasDecimal ? TokenType.FLOAT : TokenType.INT;
        return new Token(type, numberLiteral);
    }

    // Handles trigonometric functions (sin, cos)
    private Token readIdentifier() {
        int startPos = position;
        while (isLetter(currentChar)) {
            readChar();
        }
        String ident = input.substring(startPos, position).toLowerCase();

        switch (ident) {
            case "sin": return new Token(TokenType.SIN, ident);
            case "cos": return new Token(TokenType.COS, ident);
            default:    return new Token(TokenType.ILLEGAL, ident);
        }
    }
}
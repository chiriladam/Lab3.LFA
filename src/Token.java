public class Token {
    public final TokenType type;
    public final String literal;

    public Token(TokenType type, String literal) {
        this.type = type;
        this.literal = literal;
    }

    @Override
    public String toString() {
        return String.format("Token{type=%-10s literal='%s'}", type, literal);
    }
}
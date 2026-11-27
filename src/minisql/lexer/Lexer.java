
package minisql.lexer;

import java.util.*;

public class Lexer {

    private final String source;

    private final List<Token> tokens = new ArrayList<>();

    private int start = 0;
    private int current = 0;

    private int line = 1;
    private int column = 1;
    private int startColumn = 1;

    public Lexer(String source) {
        this.source = source;
    }

    // Main function: scans the complete source
    public List<Token> scanTokens() {

        while (!isAtEnd()) {

            start = current;
            startColumn = column;

            scanToken();
        }

        tokens.add(new Token(
            TokenType.EOF,
            "",
            line,
            column
        ));

        return tokens;
    }

    // Identifies the current character
    private void scanToken() {

        char c = advance();

        switch (c) {

            case '(':
                addToken(TokenType.LEFT_PAREN);
                break;

            case ')':
                addToken(TokenType.RIGHT_PAREN);
                break;

            case ',':
                addToken(TokenType.COMMA);
                break;

            case ';':
                addToken(TokenType.SEMICOLON);
                break;

            case '+':
                addToken(TokenType.PLUS);
                break;

            case '-':
                if (match('-')) {
                    while (!isAtEnd() && peek() != '\n') {
                        advance();
                    }
                } else {
                    addToken(TokenType.MINUS);
                }
                break;

            case '*':
                addToken(TokenType.MULTIPLY);
                break;

            case '/':
                addToken(TokenType.DIVIDE);
                break;

            case '=':
                addToken(TokenType.EQUAL);
                break;

            case '!':
                if (match('=')) {
                    addToken(TokenType.NOT_EQUAL);
                } else {
                    error("Unexpected character '!'");
                }
                break;

            case '>':
                addToken(match('=') ?
                    TokenType.GREATER_THAN_EQUAL :
                    TokenType.GREATER_THAN);
                break;

            case '<':
                addToken(match('=') ?
                    TokenType.LESS_THAN_EQUAL :
                    TokenType.LESS_THAN);
                break;

            case ' ':
            case '\r':
            case '\t':
                break;

            case '\n':
                line++;
                column = 1;
                break;

            case '\'':
                string();
                break;

            default:

                if (isDigit(c)) {
                    number();
                }

                else if (isLetter(c)) {
                    identifier();
                }

                else {
                    error("Invalid character: " + c);
                }

                break;
        }
    }

    // Reads identifiers and keywords
    private void identifier() {

        while (isAlphaNumeric(peek())) {
            advance();
        }

        String text = source.substring(start, current);

        TokenType type = getKeywordType(text);

        addToken(type);
    }

    // Checks whether identifier is a keyword
    private TokenType getKeywordType(String text) {

        switch (text.toUpperCase(Locale.ROOT)) {

            case "CREATE": return TokenType.CREATE;
            case "TABLE": return TokenType.TABLE;
            case "INSERT": return TokenType.INSERT;
            case "INTO": return TokenType.INTO;
            case "VALUES": return TokenType.VALUES;
            case "SELECT": return TokenType.SELECT;
            case "FROM": return TokenType.FROM;
            case "WHERE": return TokenType.WHERE;
            case "UPDATE": return TokenType.UPDATE;
            case "SET": return TokenType.SET;
            case "DELETE": return TokenType.DELETE;

            case "INT": return TokenType.INT;
            case "FLOAT": return TokenType.FLOAT;
            case "TEXT": return TokenType.TEXT;
            case "BOOLEAN": return TokenType.BOOLEAN;

            case "TRUE": return TokenType.TRUE;
            case "FALSE": return TokenType.FALSE;

            default: return TokenType.IDENTIFIER;
        }
    }

    // Reads integer and float literals
    private void number() {

        while (isDigit(peek())) {
            advance();
        }

        if (peek() == '.' && isDigit(peekNext())) {

            advance();

            while (isDigit(peek())) {
                advance();
            }

            addToken(TokenType.FLOAT_LITERAL);
        }

        else {
            addToken(TokenType.INTEGER_LITERAL);
        }
    }

    // Reads string literals
    private void string() {

        while (!isAtEnd() && peek() != '\'') {

            if (peek() == '\n') {
                line++;
                column = 0;
            }

            advance();
        }

        if (isAtEnd()) {
            error("Unterminated string");
            return;
        }

        advance();

        addToken(TokenType.STRING_LITERAL);
    }

    // Adds token to the token list
    private void addToken(TokenType type) {

        String value = source.substring(start, current);

        tokens.add(new Token(
            type,
            value,
            line,
            startColumn
        ));
    }

    // Advances one character
    private char advance() {

        char c = source.charAt(current++);

        column++;

        return c;
    }

    private boolean match(char expected) {

        if (isAtEnd()) return false;

        if (source.charAt(current) != expected) {
            return false;
        }

        advance();

        return true;
    }

    private char peek() {

        if (isAtEnd()) return '\0';

        return source.charAt(current);
    }

    private char peekNext() {

        if (current + 1 >= source.length()) {
            return '\0';
        }

        return source.charAt(current + 1);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isLetter(char c) {

        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z') ||
               c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isLetter(c) || isDigit(c);
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private void skipWhitespace() {
        // Reserved for whitespace handling if needed.
    }

    private void error(String message) {

        System.out.println(
            "Lexical Error: " + message +
            " at line " + line +
            ", column " + startColumn
        );
    }
}

package minisql.lexer;

import java.util.List;

public class LexerTest {

    public static void main(String[] args) {

        String input =
            "SELECT marks FROM Students WHERE marks > 25.75;";

        Lexer lexer = new Lexer(input);

        List<Token> tokens = lexer.scanTokens();

        for (Token t : tokens) {
            System.out.println(t);
        }
    }
}
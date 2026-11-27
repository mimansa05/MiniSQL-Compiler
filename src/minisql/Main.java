
package minisql;

import minisql.lexer.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String input =
            "SELECT name FROM Students WHERE marks > 80;";

        Lexer lexer = new Lexer(input);

        List<Token> tokens = lexer.scanTokens();

        for (Token t : tokens) {
            System.out.println(t);
        }
    }
}

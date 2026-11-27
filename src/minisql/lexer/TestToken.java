package minisql.lexer;

public class TestToken {
    public static void main(String args[])
    {
        Token t=new Token(
            TokenType.SELECT,
            "SELECT",
            1,1
        );
        System.out.println(t);
    }
    
}

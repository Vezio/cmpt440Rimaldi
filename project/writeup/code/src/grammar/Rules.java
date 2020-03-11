package grammar;

public enum Rules {
    LEFT_BRACE (
        // new Lexeme("'{$'", "{")
    );


    private class Lexeme {

        private String pattern;
        private String token;

        Lexeme(String pattern, String token) {
            this.pattern = pattern;
            this.token = token;
        }

    }

}
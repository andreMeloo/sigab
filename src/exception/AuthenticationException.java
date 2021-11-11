package exception;

public class AuthenticationException extends Exception {
    private static final long serialVersionUID = 1L;

    public AuthenticationException () {
        super ("Login ou Senha não encontrados");
    }
    
}

package ma.octo.assignement.exceptions;

public class AccountNotExistingException extends Exception {

  private static final long serialVersionUID = 1L;

  public AccountNotExistingException(String message) {
    super(message);
  }

  public AccountNotExistingException() {
    super("The given account does not exist");
  }

}

package exceptions;



public class RepositoryException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public RepositoryException(){}
	
	public RepositoryException(String msg){ super(msg); }
	
	public RepositoryException(String msg, Throwable cause){ super(msg, cause); }

}

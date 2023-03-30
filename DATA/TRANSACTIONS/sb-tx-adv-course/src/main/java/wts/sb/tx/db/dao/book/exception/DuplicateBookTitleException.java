package wts.sb.tx.db.dao.book.exception;

public class DuplicateBookTitleException extends RuntimeException {

	public DuplicateBookTitleException(String message) {
		super(message);
	}
}

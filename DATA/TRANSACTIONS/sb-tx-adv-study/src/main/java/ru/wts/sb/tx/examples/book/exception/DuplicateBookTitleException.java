package ru.wts.sb.tx.examples.book.exception;

public class DuplicateBookTitleException extends RuntimeException {

	public DuplicateBookTitleException(String message) {
		super(message);
	}
}

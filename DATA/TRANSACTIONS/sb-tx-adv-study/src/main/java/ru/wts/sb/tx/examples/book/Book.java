package ru.wts.sb.tx.examples.book;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused", "CommentedOutCode"})
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private Date dateRelease;
	private String publishingHouse;

	public Book() {
	}

	@SuppressWarnings("unused")
	public Book(int id, String title, Date dateRelease) {
		this.id = id;
		this.title = title;
		this.dateRelease = dateRelease;
	}

	public Book(String title, Date dateRelease) {
		this.title = title;
		this.dateRelease = dateRelease;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="DATE_RELEASE")
	public Date getDateRelease() {
		return dateRelease;
	}
	
	public void setDateRelease(Date dateRelease) {
		this.dateRelease = dateRelease;
	}

//	public boolean origEquals(Object o) {
//
//		if (this == o)
//			return true;
//		if (o == null || getClass() != o.getClass())
//			return false;
//
//		Book book = (Book) o;
//
//
//		if (!Objects.equals(dateRelease, book.dateRelease))
//			return false;
//		if (title != null ? !title.equals(book.title) : book.title != null)
//			return false;
//
//		return true;
//	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Book book = (Book) o;

		if (id != book.id) return false;
		if (!Objects.equals(title, book.title)) return false;
		if (!Objects.equals(dateRelease, book.dateRelease)) return false;
		return Objects.equals(publishingHouse, book.publishingHouse);
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + title.hashCode();
		result = 31 * result + (dateRelease != null ? dateRelease.hashCode() : 0);
		result = 31 * result + (publishingHouse != null ? publishingHouse.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", dateRelease=" + dateRelease + ", publishingHouse="
				+ publishingHouse + "]";
	}

	public String getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublishingHouse(String publishingHouse) {
		this.publishingHouse = publishingHouse;
	}
}

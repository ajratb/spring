package wts.sb.tx.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@SuppressWarnings("unused")
@Entity
public class Log implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private int id;
	private String message;
	private Date date;

	public Log() {
	}
	
	@Override
	public String toString() {
		return "Log [message=" + message + ", date=" + date + "]";
	}

	public Log(String message) {
		super();
		this.message = message;
		this.date = Date.valueOf(LocalDate.now());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

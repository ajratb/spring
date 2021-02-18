package sboot.rest.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private  long id;
    private  String content;
    private LocalDate date;

//    public Message(long id, String content, LocalDate date) {
//        this.id = id;
//        this.content = content;
//        this.date = date;
//    }

    public Message(){}
    
    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDate() {
        return date;
    }

//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
    
    @Override
  public String toString() {
      
      return "Hello: " + date;
//    return "Quote{" +
//        "type='" + id + '\'' +
//        ", value=" + content +
//        '}';
  }
}

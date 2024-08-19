package sb.mvc.testing.habr;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book {

    int id;
    String title;
    String author;
    String publisher;
    String releaseDate;
    String isbn;
    String topic;
}

package sb.jpa.criteria;

import jakarta.persistence.criteria.Predicate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static sb.jpa.criteria.PredicateUtils.getStringPredicateForAttr;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test void test(){
        Book book = new Book();
        book.setAuthor("Dostoevsiy");
        book.setTitle("Karenina");
        bookRepository.save(book);
        book = new Book();
        book.setAuthor("Gorkiy");
        book.setTitle("Klim Samgin");
        bookRepository.save(book);
        List<Predicate> predicates = new ArrayList<>();
        String nameValue = "G";
        Specification<Book> spec = (root, query, builder) -> {
            predicates.add(getStringPredicateForAttr(builder, root, "author", nameValue));
//            predicates.add(getBoolPredicateForUserAttr(builder, root, "deleted", true));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        book = new Book();
        book.setAuthor("Dostoevsiy");
        book.setTitle("Igrok");
        bookRepository.save(book);
        List<Predicate> predicates2 = new ArrayList<>();
        String nameValue2 = "K";
        Specification<Book> spec2 = (root, query, builder) -> {
            predicates2.add(getStringPredicateForAttr(builder, root, "title", nameValue2));
//            predicates.add(getBoolPredicateForUserAttr(builder, root, "deleted", true));
            return builder.and(predicates2.toArray(new Predicate[0]));
        };

//        Iterable<Book> all = bookRepository.findAll(spec.and(spec2));
//        Assertions.assertThat(all).hasSize(1);
        Iterable<Book> all2 = bookRepository.findAll(spec.or(spec2));
        Assertions.assertThat(all2).hasSize(2);
    }

}
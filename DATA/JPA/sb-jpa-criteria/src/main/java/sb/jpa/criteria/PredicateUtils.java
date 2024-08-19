package sb.jpa.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PredicateUtils {

    public static Predicate getStringPredicateForAttr(
            CriteriaBuilder builder, Root<?> root, String attr, String value) {

        String startSymbol = "";
        if (value == null) value = "";
        if (value.startsWith("*")) {
            value = value.substring(1);
            startSymbol = "%";
        }

        return builder.like(
                builder.lower(root.<String>get(attr)),
                startSymbol + value.toLowerCase() + "%");
    }

    public static Predicate getBoolPredicateForAttr(
            CriteriaBuilder builder, Root<?> root, String attr, Boolean value) {

        return builder.equal(root.<String>get(attr), value);
    }

    public static Predicate getEqualsPredicateForAttr(
            CriteriaBuilder builder, Root<?> root, String attr, Object value) {

        return builder.equal(root.<String>get(attr), value);
    }

    @SuppressWarnings("RedundantTypeArguments")
    private Predicate getPredicateForPersonAttr(
            CriteriaBuilder builder, Root<?> root, String attr, String value) {

        String startSymbol = "";
        if (value == null) value = "";
        if (value.startsWith("*")) {
            value = value.substring(1);
            startSymbol = "%";
        }

        return builder.like(
                builder.lower(root.join("person").<String>get(attr)),
                startSymbol + value.toLowerCase() + "%");
    }
}

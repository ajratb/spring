package wts.spring.jdbc.model;

/**
 *
 * @author ayrat
 */
public class Person {

    private Long id;
    private Integer age;
    private String firstName;
    private String lastName;
    private float range;

    public Person() {
    }

    public Person(
            //Long id, 
            Integer age, String firstName, String lastName, float range
    ) {
//        this.id = id;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.range = range;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }
    
    

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", age=" + age + ", firstName='" 
                + firstName + '\'' + ", lastName='" + lastName
                + ", range='" + range + '\'' + '}';
    }
}

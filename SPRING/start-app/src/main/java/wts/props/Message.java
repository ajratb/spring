package wts.props;

/**
 *
 * @author BikchentaevAA
 */
public class Message {

    final String val;

//    public Message() {
//    }
    public Message(String val) {
        this.val = val;
    }

    void printMsg() {
        System.out.println("Message: " + val);
    }
}

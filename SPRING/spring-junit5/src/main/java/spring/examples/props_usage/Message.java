package spring.examples.props_usage;

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

	public void printMsg() {
		System.out.println("Message: " + val);
	}

	public String getVal() {
		return val;
	}
}

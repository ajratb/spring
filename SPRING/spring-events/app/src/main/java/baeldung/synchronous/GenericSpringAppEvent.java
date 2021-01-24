package baeldung.synchronous;

import org.springframework.context.ApplicationEvent;

public class GenericSpringAppEvent<T> extends ApplicationEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5257936269677268404L;
	private final T what;

    public GenericSpringAppEvent(final Object source, final T what) {
        super(source);
        this.what = what;
    }

    public T getWhat() {
        return what;
    }

}

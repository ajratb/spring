package wts.props;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

@Repository
public class AppWithProps {

    @Autowired
    Message msg;

    public static void main(String[] args) {
        // ApplicationContext
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppWithPropsConf.class)) {

            AppWithProps app = ctx.getBean(AppWithProps.class);
            app.msg.printMsg();

            Message msgBean = ctx.getBean(Message.class);
            msgBean.printMsg();
        }
    }
}

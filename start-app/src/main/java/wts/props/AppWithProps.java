package wts.props;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class AppWithProps {

    @Autowired
    Message msg;

//    public AppWithProps() {
//    }

    public AppWithProps(Message msg) {
        this.msg = msg;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppWithPropsConf.class);

//        ctx.register(AppWithPropsConf.class);
//        ctx.scan("wts.props");
//        ctx.refresh();
        
        AppWithProps app = ctx.getBean(AppWithProps.class);
        System.out.println(app.msg.getMsg());
    }
}

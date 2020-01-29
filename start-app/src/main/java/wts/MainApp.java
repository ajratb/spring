package wts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class MainApp {

    @Autowired
    private MyService myService;

    public MainApp() {
    }

    MainApp(MyService myService) {
        this.myService = myService;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.scan("wts");
        ctx.refresh();
        MainApp app = (MainApp)ctx.getBean("mainApp");//mainApp - follow convention!
        MyService srv = (MyService)ctx.getBean(MyService.class);
        app.myService.printMsg();
        srv.printMsg("from srv");
    }
}

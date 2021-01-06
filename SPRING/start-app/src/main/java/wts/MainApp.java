package wts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class MainApp {

    @Autowired
    private MyService myService;

    // @Autowired
    // void setMyService(MyService myService){
    // this.myService= myService;
    // }
    // @Autowired
    // MainApp(MyService myService) {
    // this.myService = myService;
    // }
    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {

            // if you don't use ComponentScan("pkg_name") with AppConfig
            // then use this code:

            // AnnotationConfigApplicationContext ctx = new
            // AnnotationConfigApplicationContext();
            // ctx.register(AppConfig.class);
            // ctx.scan("wts");
            // ctx.refresh();

            MainApp app = (MainApp) ctx.getBean("mainApp");// mainApp - follow convention!
            // Service as a part mainApp component
            app.myService.printMsg();

            // Service retrieved from configuration
            MyService srvBean = (MyService) ctx.getBean(MyService.class);
            srvBean.printMsg("from srv");
        }
    }
}

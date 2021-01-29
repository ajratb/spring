package wts;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@Service
public class DataAccessApp {

//    private static final Logger log = LoggerFactory.getLogger(DataAccessApp.class);
//     @Autowired
//     JdbcTemplate jdbcTemplate;

//    public DataAccessApp() {
//    }

//    DataAccessApp(MyService myService) {
//        this.myService = myService;
//    }

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(SpringJdbcConfig.class)) {
//        ctx.register(SpringJdbcConfig.class);
//  wts.MyService srv = new DataAccessApp().myService;
//        ctx.scan("wts");
//        ctx.refresh();
			SimpleDao dao = (SimpleDao) ctx.getBean(SimpleDao.class);// mainApp - follow convention!

			int result = dao.getRowsCount();
			System.out.println(result);
		}
	}
}

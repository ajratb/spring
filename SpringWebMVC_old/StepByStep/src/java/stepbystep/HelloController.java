package stepbystep;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 

/**
 *
 * @author ayrat
 */
public class HelloController implements Controller {
    
  
    protected final Log logger = LogFactory.getLog(getClass()); 

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException { 

        logger.info("Returning hello view"); 

        return new ModelAndView("hello.jsp");

    } 
    
    
}

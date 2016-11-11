/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stepbystep;

import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ayrat
 */
public class TestHelloController extends TestCase{
    
    public void testHandleRequestView() throws Exception{       

        HelloController controller = new HelloController();

        ModelAndView modelAndView = controller.handleRequest(null, null);         

        assertEquals("hello.jsp", modelAndView.getViewName());

    }
}

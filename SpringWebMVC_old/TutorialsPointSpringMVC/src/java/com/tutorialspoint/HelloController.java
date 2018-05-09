/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorialspoint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Ayrat
 */
//@Controller
//@RequestMapping("/hello")
//public class HelloController{
// 
//   @RequestMapping(method = RequestMethod.GET)
//   public String printHello(ModelMap model) {
//      model.addAttribute("message", "Hello Spring MVC Framework!");
//      return "hello";
//   }
//
//}
@Controller
public class HelloController{
 
   @RequestMapping(value = "/hello", method = RequestMethod.GET)
   public String printHello(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");
      return "hello";
   }

}
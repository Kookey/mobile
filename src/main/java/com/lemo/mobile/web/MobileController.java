package com.lemo.mobile.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/m")
@Controller
public class MobileController {

  @RequestMapping("/platform")
  @ResponseBody
  public String index() {
    return "mobile";
  }

  @RequestMapping("/home")
  public String home() {
    return "index";
  }

}

package com.lemo.mobile.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/t")
@RestController
public class TabletController {

  @RequestMapping("/platform")
  public String index() {
    return "tablet";
  }

  @RequestMapping("/home")
  public String home() {
    return "index";
  }
}

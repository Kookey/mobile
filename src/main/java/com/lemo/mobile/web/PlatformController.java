package com.lemo.mobile.web;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PlatformController {

  @RequestMapping("/platform")
  @ResponseBody
  public Device platform(Device device) {
    return device;
  }

  @RequestMapping("/home")
  public String home(){
    return "index";
  }
}

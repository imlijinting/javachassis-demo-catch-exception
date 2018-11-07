package com.github.yhs0092.demo.javachassis.service;

import javax.ws.rs.core.Response.Status;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "hello")
@RequestMapping(path = "/hello")
public class HelloService {
  @RequestMapping(path = "/hello", method = RequestMethod.GET)
  public String sayHello(@RequestParam(value = "order") int order) {
    switch (order) {
      case 0:
        // IllegalArgumentException 是在 IllegalArgumentExceptionConverter 中处理的
        throw new IllegalArgumentException("order is 0");
      case 1:
        // RuntimeException 在 RuntimeExceptionConverter 中处理
        throw new RuntimeException("order is 1");
      case 2:
        return "Hello";
      default:
        // 直接抛出InvocationException，可以定制其中的返回状态码以及返回消息。
        throw new InvocationException(Status.BAD_REQUEST, "other order");
    }
  }
}

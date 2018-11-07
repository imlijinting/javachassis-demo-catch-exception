package com.github.yhs0092.demo.javachassis.exceptionconverter;

import java.util.Date;

import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.SwaggerInvocation;
import org.apache.servicecomb.swagger.invocation.exception.ExceptionToResponseConverter;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;

public class IllegalArgumentExceptionConverter implements ExceptionToResponseConverter<IllegalArgumentException> {
  @Override
  public Class<IllegalArgumentException> getExceptionClass() {
    return IllegalArgumentException.class;
  }

  @Override
  public Response convert(SwaggerInvocation swaggerInvocation, IllegalArgumentException e) {
    CustomExceptionData customExceptionData = new CustomExceptionData();
    customExceptionData.setName(e.getMessage());
    customExceptionData.setTimeStamp(new Date());
    // 你可以构造一个InvocationException，并且向其中设置自定义的返回体类型
    InvocationException invocationException =
        new InvocationException(500, "custom server error", customExceptionData);
    // 利用Response自带的工具方法，可以从InvocationException构造出期望的response
    return Response.failResp(invocationException);
  }

  static class CustomExceptionData {
    private static int counter = 0;

    private String name;

    private int order = counter++;

    private Date timeStamp;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getOrder() {
      return order;
    }

    public void setOrder(int order) {
      this.order = order;
    }

    public Date getTimeStamp() {
      return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
      this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("CustomExceptionData{");
      sb.append("name='").append(name).append('\'');
      sb.append(", order=").append(order);
      sb.append(", timeStamp=").append(timeStamp);
      sb.append('}');
      return sb.toString();
    }
  }
}

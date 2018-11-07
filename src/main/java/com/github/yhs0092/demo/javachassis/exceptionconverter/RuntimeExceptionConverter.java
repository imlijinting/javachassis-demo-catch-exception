package com.github.yhs0092.demo.javachassis.exceptionconverter;

import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.SwaggerInvocation;
import org.apache.servicecomb.swagger.invocation.exception.ExceptionToResponseConverter;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;

public class RuntimeExceptionConverter implements ExceptionToResponseConverter<RuntimeException> {
  @Override
  public Class<RuntimeException> getExceptionClass() {
    return RuntimeException.class;
  }

  @Override
  public Response convert(SwaggerInvocation swaggerInvocation, RuntimeException e) {
    InvocationException invocationException = new InvocationException(
        500,
        "converted by RuntimeExceptionConverter",
        e.getMessage());
    return Response.failResp(invocationException);
  }
}

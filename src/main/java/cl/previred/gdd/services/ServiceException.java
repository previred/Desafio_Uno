package cl.previred.gdd.services;

public class ServiceException extends Exception {

  public ServiceException(String msg, Exception ex) {
    super(msg, ex);
  }
}

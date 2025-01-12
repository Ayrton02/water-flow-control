package infra.logger;

public class LoggerImpl implements Logger {
  private final org.jboss.logging.Logger log;

  public LoggerImpl(Class<?> caller) {
    this.log = org.jboss.logging.Logger.getLogger(caller);
  }

  @Override
  public void info(String message) {
    log.info(message);
  }

  @Override
  public void info(String message, Object... objects) {
    log.infof(message, objects);
  }

  @Override
  public void info(Object... objects) {
    log.info(objects);
  }

  @Override
  public void debug(String message) {
    log.debug(message);
  }

  @Override
  public void debug(String message, Object... objects) {
    log.debugf(message, objects);
  }

  @Override
  public void debug(Object... objects) {
    log.debug(objects);
  }

  @Override
  public void warn(String message) {
    log.warn(message);
  }

  @Override
  public void warn(String message, Object... objects) {
    log.warnf(message, objects);
  }

  @Override
  public void warn(Object... objects) {
    log.warn(objects);
  }

  @Override
  public void error(String message) {
    log.error(message);
  }

  @Override
  public void error(String message, Object... objects) {
    log.errorf(message, objects);
  }

  @Override
  public void error(Object... objects) {
    log.error(objects);
  }
}

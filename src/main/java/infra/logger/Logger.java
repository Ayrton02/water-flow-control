package infra.logger;

public interface Logger {
  void info(String message);
  void info(String message, Object ...objects);
  void info(Object ...objects);
  void debug(String message);
  void debug(String message, Object ...objects);
  void debug(Object ...objects);
  void warn(String message);
  void warn(String message, Object ...objects);
  void warn(Object ...objects);
  void error(String message);
  void error(String message, Object ...objects);
  void error(Object ...objects);
}

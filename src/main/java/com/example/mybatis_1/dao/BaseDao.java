package com.example.mybatis_1.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class BaseDao.
 */
public class BaseDao {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LogManager.getLogger();

  /**
   * Gets the integer.
   *
   * @param sqlSession the SQL session
   * @param statement the statement
   * @param parameter the parameter
   * @return the integer
   */
  public Integer getInteger(SqlSession sqlSession, String statement, Object parameter) {
    this.logQuery(statement, parameter);
    Object result = sqlSession.selectOne(statement, parameter);
    return result == null ? null : (Integer) result;
  }

  /**
   * Gets the long.
   *
   * @param sqlSession the SQL session
   * @param statement the statement
   * @param parameter the parameter
   * @return the long
   */
  public Long getLong(SqlSession sqlSession, String statement, Object parameter) {
    this.logQuery(statement, parameter);
    Object result = sqlSession.selectOne(statement, parameter);
    return result == null ? null : (Long) result;
  }

  /**
   * Gets the string.
   *
   * @param sqlSession the SQL session
   * @param statement the statement
   * @param parameter the parameter
   * @return the string
   */
  public String getString(SqlSession sqlSession, String statement, Object parameter) {
    this.logQuery(statement, parameter);
    Object result = sqlSession.selectOne(statement, parameter);
    return result == null ? null : result.toString();
  }

  /**
   * Retrieve a single row mapped from the statement key.
   *
   * @param <T> the returned object type
   * @param sqlSession the SQL session
   * @param statement the statement
   * @return Mapped object
   */
  public <T> T selectOne(SqlSession sqlSession, String statement) {
    this.logQuery(statement);
    return sqlSession.selectOne(statement);
  }

  /**
   * Retrieve a single row mapped from the statement key and parameter.
   *
   * @param <T> the returned object type
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to use.
   * @param parameter A parameter object to pass to the statement.
   * @return Mapped object
   */
  public <T> T selectOne(SqlSession sqlSession, String statement, Object parameter) {
    this.logQuery(statement, parameter);
    return sqlSession.selectOne(statement, parameter);
  }

  /**
   * Retrieve a list of mapped objects from the statement key and parameter.
   *
   * @param <E> the returned list element type
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to use.
   * @return List of mapped object
   */
  public <E> List<E> selectList(SqlSession sqlSession, String statement) {
    this.logQuery(statement);
    return sqlSession.selectList(statement);
  }

  /**
   * Retrieve a list of mapped objects from the statement key and parameter.
   *
   * @param <E> the returned list element type
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to use.
   * @param parameter A parameter object to pass to the statement.
   * @return List of mapped object
   */
  public <E> List<E> selectList(SqlSession sqlSession, String statement, Object parameter) {
    this.logQuery(statement, parameter);
    return sqlSession.selectList(statement, parameter);
  }

  /**
   * Retrieve a list of mapped objects from the statement key and parameter, within the specified
   * row bounds.
   *
   * @param <E> the returned list element type
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to use.
   * @param parameter A parameter object to pass to the statement.
   * @param rowBounds Bounds to limit object retrieval
   * @return List of mapped object
   */
  public <E> List<E> selectList(SqlSession sqlSession, String statement, Object parameter,
      RowBounds rowBounds) {
    this.logQuery(statement, parameter, rowBounds);
    return sqlSession.selectList(statement, parameter, rowBounds);
  }

  /**
   * The selectMap is a special case in that it is designed to convert a list of results into a Map
   * based on one of the properties in the resulting objects. Eg. Return a of Map[Integer,Author]
   * for selectMap("selectAuthors","id")
   *
   * @param <K> the returned Map keys type
   * @param <V> the returned Map values type
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to use.
   * @param mapKey The property to use as key for each value in the list.
   * @return Map containing key pair data.
   */
  public <K, V> Map<K, V> selectMap(SqlSession sqlSession, String statement, String mapKey) {
    this.logQuery(statement, mapKey);
    return sqlSession.selectMap(statement, mapKey);
  }

  /**
   * The selectMap is a special case in that it is designed to convert a list of results into a Map
   * based on one of the properties in the resulting objects.
   *
   * @param <K> the returned Map keys type
   * @param <V> the returned Map values type
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to use.
   * @param parameter A parameter object to pass to the statement.
   * @param mapKey The property to use as key for each value in the list.
   * @return Map containing key pair data.
   */
  public <K, V> Map<K, V> selectMap(SqlSession sqlSession, String statement, Object parameter,
      String mapKey) {
    this.logQuery(statement, parameter, mapKey);
    return sqlSession.selectMap(statement, parameter, mapKey);
  }

  /**
   * The selectMap is a special case in that it is designed to convert a list of results into a Map
   * based on one of the properties in the resulting objects.
   *
   * @param <K> the returned Map keys type
   * @param <V> the returned Map values type
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to use.
   * @param parameter A parameter object to pass to the statement.
   * @param mapKey The property to use as key for each value in the list.
   * @param rowBounds Bounds to limit object retrieval
   * @return Map containing key pair data.
   */
  public <K, V> Map<K, V> selectMap(SqlSession sqlSession, String statement, Object parameter,
      String mapKey, RowBounds rowBounds) {
    this.logQuery(statement, parameter, mapKey, rowBounds);
    return sqlSession.selectMap(statement, parameter, mapKey, rowBounds);
  }

  /**
   * A Cursor offers the same results as a List, except it fetches data lazily using an Iterator.
   *
   * @param <T> the returned cursor element type.
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to use.
   * @return Cursor of mapped objects
   */
  public <T> Cursor<T> selectCursor(SqlSession sqlSession, String statement) {
    this.logQuery(statement);
    return sqlSession.selectCursor(statement);
  }

  /**
   * A Cursor offers the same results as a List, except it fetches data lazily using an Iterator.
   *
   * @param <T> the returned cursor element type.
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to use.
   * @param parameter A parameter object to pass to the statement.
   * @return Cursor of mapped objects
   */
  public <T> Cursor<T> selectCursor(SqlSession sqlSession, String statement, Object parameter) {
    this.logQuery(statement, parameter);
    return sqlSession.selectCursor(statement, parameter);
  }

  /**
   * A Cursor offers the same results as a List, except it fetches data lazily using an Iterator.
   *
   * @param <T> the returned cursor element type.
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to use.
   * @param parameter A parameter object to pass to the statement.
   * @param rowBounds Bounds to limit object retrieval
   * @return Cursor of mapped objects
   */
  public <T> Cursor<T> selectCursor(SqlSession sqlSession, String statement, Object parameter,
      RowBounds rowBounds) {
    this.logQuery(statement, parameter, rowBounds);
    return sqlSession.selectCursor(statement, parameter, rowBounds);
  }

  /**
   * Retrieve a single row mapped from the statement key and parameter using a
   * {@code ResultHandler}.
   *
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to use.
   * @param parameter A parameter object to pass to the statement.
   * @param handler ResultHandler that will handle each retrieved row
   */
  public void select(SqlSession sqlSession, String statement, Object parameter,
      ResultHandler<?> handler) {
    this.logQuery(statement, parameter, handler);
    sqlSession.select(statement, parameter, handler);
  }

  /**
   * Retrieve a single row mapped from the statement using a {@code ResultHandler}.
   *
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to use.
   * @param handler ResultHandler that will handle each retrieved row
   */
  public void select(SqlSession sqlSession, String statement, ResultHandler<?> handler) {
    this.logQuery(statement, handler);
    sqlSession.select(statement, handler);
  }

  /**
   * Retrieve a single row mapped from the statement key and parameter using a {@code ResultHandler}
   * and {@code RowBounds}.
   *
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to use.
   * @param parameter the parameter
   * @param rowBounds RowBound instance to limit the query results
   * @param handler ResultHandler that will handle each retrieved row
   */
  public void select(SqlSession sqlSession, String statement, Object parameter, RowBounds rowBounds,
      ResultHandler<?> handler) {
    this.logQuery(statement, parameter, rowBounds, handler);
    sqlSession.select(statement, parameter, rowBounds, handler);
  }

  /**
   * Execute an insert statement.
   *
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to execute.
   * @return int The number of rows affected by the insert.
   */
  public int insert(SqlSession sqlSession, String statement) {
    this.logQuery(statement);
    return sqlSession.insert(statement);
  }

  /**
   * Execute an insert statement with the given parameter object. Any generated auto increment
   * values or selectKey entries will modify the given parameter object properties. Only the number
   * of rows affected will be returned.
   *
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to execute.
   * @param parameter A parameter object to pass to the statement.
   * @return int The number of rows affected by the insert.
   */
  public int insert(SqlSession sqlSession, String statement, Object parameter) {
    this.logQuery(statement, parameter);
    return sqlSession.insert(statement, parameter);
  }

  /**
   * Execute an update statement. The number of rows affected will be returned.
   *
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to execute.
   * @return int The number of rows affected by the update.
   */
  public int update(SqlSession sqlSession, String statement) {
    this.logQuery(statement);
    return sqlSession.update(statement);
  }

  /**
   * Execute an update statement. The number of rows affected will be returned.
   *
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to execute.
   * @param parameter A parameter object to pass to the statement.
   * @return int The number of rows affected by the update.
   */
  public int update(SqlSession sqlSession, String statement, Object parameter) {
    this.logQuery(statement, parameter);
    return sqlSession.update(statement, parameter);
  }

  /**
   * Execute a delete statement. The number of rows affected will be returned.
   *
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to execute.
   * @return int The number of rows affected by the delete.
   */
  public int delete(SqlSession sqlSession, String statement) {
    this.logQuery(statement);
    return sqlSession.delete(statement);
  }

  /**
   * Execute a delete statement. The number of rows affected will be returned.
   *
   * @param sqlSession the SQL session
   * @param statement Unique identifier matching the statement to execute.
   * @param parameter A parameter object to pass to the statement.
   * @return int The number of rows affected by the delete.
   */
  public int delete(SqlSession sqlSession, String statement, Object parameter) {
    this.logQuery(statement, parameter);
    return sqlSession.delete(statement, parameter);
  }

  /**
   * Log query.
   *
   * @param statement the statement
   */
  protected void logQuery(String statement) {
    LOGGER.debug("statement: {}", statement);
  }

  /**
   * Log query.
   *
   * @param statement the statement
   * @param parameter the parameter
   */
  protected void logQuery(String statement, Object parameter) {
    LOGGER.debug("statement: {}, parameter: {}", statement, parameter);
  }

  /**
   * Log query.
   *
   * @param statement the statement
   * @param parameter the parameter
   * @param mapKey the map key
   */
  protected void logQuery(String statement, Object parameter, String mapKey) {
    LOGGER.debug("statement: {}, parameter: {}, mapKey: {}", statement, parameter, mapKey);
  }

  /**
   * Log query.
   *
   * @param statement the statement
   * @param parameter the parameter
   * @param rowBounds the row bounds
   */
  protected void logQuery(String statement, Object parameter, RowBounds rowBounds) {
    LOGGER.debug("statement: {}, parameter: {}, rowBounds: {}", statement, parameter, rowBounds);
  }

  /**
   * Log query.
   *
   * @param statement the statement
   * @param parameter the parameter
   * @param mapKey the map key
   * @param rowBounds the row bounds
   */
  protected void logQuery(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
    LOGGER.debug("statement: {}, parameter: {}, mapKey: {}, rowBounds: {}", statement, parameter,
        mapKey, rowBounds);
  }

  /**
   * Log query.
   *
   * @param statement the statement
   * @param parameter the parameter
   * @param handler the handler
   */
  protected void logQuery(String statement, Object parameter, ResultHandler<?> handler) {
    LOGGER.debug("statement: {}, parameter: {}, handler: {}", statement, parameter, handler);
  }

  /**
   * Log query.
   *
   * @param statement the statement
   * @param parameter the parameter
   * @param rowBounds the row bounds
   * @param handler the handler
   */
  protected void logQuery(String statement, Object parameter, RowBounds rowBounds,
      ResultHandler<?> handler) {
    LOGGER.debug("statement: {}, parameter: {}, rowBounds: {}, handler: {}", statement, parameter,
        rowBounds, handler);
  }

}
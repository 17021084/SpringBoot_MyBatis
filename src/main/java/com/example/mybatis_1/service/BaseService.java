package com.example.mybatis_1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger();

	/** The SQL session factory. */
	@Autowired
	protected SqlSessionFactory sqlSessionFactory;

	/**
	 * Close session.
	 *
	 * @param sqlSession the SQL session
	 */
	protected void closeSession(SqlSession sqlSession) {
		if (sqlSession != null) {
			LOGGER.info("closeSession(): sqlSession = {}", sqlSession);
			sqlSession.close();
		}
	}

	/**
	 * Open session.
	 *
	 * @return the SQL session
	 */
	protected SqlSession openSession() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		LOGGER.info("openSession(): sqlSession = {}", sqlSession);
		return sqlSession;
	}

	/**
	 * Roll back session.
	 *
	 * @param sqlSession the SQL session
	 */
	protected void rollBackSession(SqlSession sqlSession) {
		if (sqlSession != null) {
			LOGGER.info("rollBackSession(): sqlSession = {}", sqlSession);
			sqlSession.rollback();
		}
	}
}

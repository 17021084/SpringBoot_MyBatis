package com.example.mybatis_1.service;

import java.util.ArrayList;
import java.util.List;

import com.example.mybatis_1.dao.UserDao;
import com.example.mybatis_1.entity.Filter;
import com.example.mybatis_1.entity.Users;
import com.example.mybatis_1.exception.NotFoundException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpNE;
import org.springframework.stereotype.Service;

@Service
public class UsersService extends BaseService {

	@Autowired
	private UserDao userDao;

	public List<Users> getAllUsers() {
		SqlSession sqlSession = null;
		List<Users> userList = new ArrayList<>();
		try {
			sqlSession = openSession();
			userList = userDao.getUserList(sqlSession);
			sqlSession.commit();
		} catch (Exception e) {
			rollBackSession(sqlSession);
			throw e;
		} finally {
			closeSession(sqlSession);
		}
		return userList;
	}

	public Users getUserById(int id) {
		SqlSession sqlSession = null;
		Users user = new Users();
		try {
			sqlSession = openSession();
			user = userDao.getUserById(sqlSession, id);
			sqlSession.commit();
		} catch (Exception e) {
			rollBackSession(sqlSession);
			throw e;
		} finally {
			closeSession(sqlSession);
		}

		return user;
	}


	public List<Users> filterUser(Filter filter) {
		SqlSession sqlSession = null;
		List<Users> user = new ArrayList<>();
		try {
			sqlSession = openSession();
			user = userDao.filterUser(sqlSession, filter);
			sqlSession.commit();
		} catch (Exception e) {
			rollBackSession(sqlSession);
			throw e;
		} finally {
			closeSession(sqlSession);
		}

		return user;
	}

	
	public void insertUser(Users user) {
		SqlSession sqlSession =null;
		try {
			sqlSession =  openSession();
			userDao.insertUser(sqlSession, user);
			sqlSession.commit();
		} catch (Exception e) {
			rollBackSession(sqlSession);
			throw e;
		}finally {
			closeSession(sqlSession);
		}
	}

	public void updateUser(Users user) {
		SqlSession sqlSession = null;
		try {
			sqlSession = openSession();
			userDao.updateUser(sqlSession, user);
			sqlSession.commit();
		} catch (Exception e) {
			rollBackSession(sqlSession);
			throw e;
		}finally {
			closeSession(sqlSession);
		}
	}

	public void deleteUser(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = openSession();
			userDao.deleteUserById(sqlSession, id);
			sqlSession.commit();
		} catch (Exception e) {
			rollBackSession(sqlSession);
			throw e;
		}finally {
		 closeSession(sqlSession);
		}
	}

}
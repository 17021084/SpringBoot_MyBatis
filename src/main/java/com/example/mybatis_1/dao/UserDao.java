package com.example.mybatis_1.dao;

import java.util.List;

import com.example.mybatis_1.entity.Filter;
import com.example.mybatis_1.entity.Users;
import com.example.mybatis_1.exception.NotFoundException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class UserDao extends BaseDao {
	public List<Users> getUserList(SqlSession sqlSession) {
		return sqlSession.selectList("mapper.Users.selectAllUsers");
	}

	public List<Users> filterUser(SqlSession sqlSession,Filter filter) {
		return sqlSession.selectList("mapper.Users.filterAllUser" , filter );
	}
	
	public Users getUserById(SqlSession sqlSession, int id) {
		Users user = sqlSession.selectOne("mapper.Users.selectUserById", id);
		if (user == null) throw new NotFoundException("User Not Found ");
		return user;
	}

	public void updateUser(SqlSession sqlSession, Users user) {
		Users oldUser = sqlSession.selectOne("mapper.Users.selectUserById", user.getId());
		if (oldUser == null) throw new NotFoundException("User Not Found ");
		sqlSession.update("mapper.Users.updateUser", user);
	}

	public void deleteUserById(SqlSession sqlSession, int id) {
		Users user = sqlSession.selectOne("mapper.Users.selectUserById", id);
		if (user == null) throw new NotFoundException("User Not Found ");
		sqlSession.delete("mapper.Users.deleteUserById", id);
	}

	public void insertUser(SqlSession sqlSession, Users user) {
		sqlSession.insert("mapper.Users.insertUser", user);
	}

}
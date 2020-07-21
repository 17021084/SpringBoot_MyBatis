package com.example.mybatis_1.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import com.example.mybatis_1.entity.Filter;
import com.example.mybatis_1.entity.Users;
import com.example.mybatis_1.entity.response.BaseResponse;
import com.example.mybatis_1.service.UsersService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	private UsersService usersService;

	private Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

	@GetMapping("")
	public ResponseEntity<BaseResponse> getAllUsers() {
		LOGGER.info("getAllUsers() start");

		BaseResponse response = new BaseResponse();
		HttpStatus httpStatus = HttpStatus.OK;
		try {

			List<Users> listUsers = usersService.getAllUsers();
			response.setStatusCode(httpStatus.value());
			response.setMessage(httpStatus.getReasonPhrase());
			response.setData(listUsers);

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error("getAllUsers() error ", e.getMessage());
		}
		LOGGER.info("getAllUsers() end");
		return new ResponseEntity<>(response, httpStatus);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable int id) {
		LOGGER.info("getUser() id = {}", id);
		HttpStatus httpStatus = HttpStatus.OK;
		BaseResponse response = new BaseResponse();
		try {
			Users user = usersService.getUserById(id);
			response.setStatusCode(httpStatus.value());
			response.setMessage(httpStatus.getReasonPhrase());
			response.setData(user);
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatusCode(httpStatus.value());
			response.setMessage(e.getMessage());
			LOGGER.info("getUser() id = {} error ={}", id, e.getMessage());
		}

		LOGGER.info("getUser() id = {} end", id);
		return new ResponseEntity<>(response, httpStatus);
	}
	
	@PostMapping("/filter")
	public ResponseEntity<?> filterUser(@RequestBody  Filter filter) {
		LOGGER.info("filter User () id = {}", filter);
		HttpStatus httpStatus = HttpStatus.OK;
		BaseResponse response = new BaseResponse();
		try {
			List<Users> user = usersService.filterUser(filter);
			response.setStatusCode(httpStatus.value());
			response.setMessage(httpStatus.getReasonPhrase());
			response.setData(user);
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatusCode(httpStatus.value());
			response.setMessage(e.getMessage());
			LOGGER.info("filter() id = {} error ={}", filter, e.getMessage());
		}

		LOGGER.info("filter() id = {} end", filter);
		return new ResponseEntity<>(response, httpStatus);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
		LOGGER.info("deleteUser() id = {}", id);
		HttpStatus httpStatus = HttpStatus.OK;
		BaseResponse response = new BaseResponse();
		try {
			usersService.deleteUser(id);
			response.setData(null);
			response.setStatusCode(httpStatus.value());
			response.setMessage(httpStatus.getReasonPhrase());

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatusCode(httpStatus.value());
			response.setMessage(e.getMessage());
			LOGGER.info("deleteUser() id = {} error ={}", id, e.getMessage());
		}
		LOGGER.info("deleteUser() id = {} end", id);
		return new ResponseEntity<>(response, httpStatus);

	}

	@PostMapping("")
	public ResponseEntity<?> addUser(@RequestBody Users user) {
		LOGGER.info("deleteUser() ");
		HttpStatus httpStatus = HttpStatus.OK;
		BaseResponse response = new BaseResponse();
		try {
			usersService.insertUser(user);
			response.setData(null);
			response.setStatusCode(httpStatus.value());
			response.setMessage(httpStatus.getReasonPhrase());
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatusCode(httpStatus.value());
			response.setMessage(e.getMessage());
			LOGGER.info("addUser() error ={}", e.getMessage());
		}

		LOGGER.info("postUser() end");
		return new ResponseEntity<>(response, httpStatus);
	}

	@PutMapping("")
	public ResponseEntity<?> modifyUser(@RequestBody Users user) {
		LOGGER.info("deleteUser() ");
		HttpStatus httpStatus = HttpStatus.OK;
		BaseResponse response = new BaseResponse();
		try {
			usersService.updateUser(user);
			response.setData(user);
			response.setStatusCode(httpStatus.value());
			response.setMessage(httpStatus.getReasonPhrase());
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatusCode(httpStatus.value());
			response.setMessage(e.getMessage());
			LOGGER.info("deleteUser() error ={}", e.getMessage());
		}

		LOGGER.info("deleteUser() end");
		return new ResponseEntity<>(response, httpStatus);

	}

}
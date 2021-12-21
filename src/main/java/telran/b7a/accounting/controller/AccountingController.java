package telran.b7a.accounting.controller;

import java.security.Principal;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.accounting.dto.ChangePasswordDto;
import telran.b7a.accounting.dto.RegisterUserDto;
import telran.b7a.accounting.dto.ResponseRoleDto;
import telran.b7a.accounting.dto.ResponseUserDto;
import telran.b7a.accounting.dto.UpdateUserDto;
import telran.b7a.accounting.dto.UserLoginDto;
import telran.b7a.accounting.service.AccountingService;

@RestController
@RequestMapping("/account")
public class AccountingController {
AccountingService service;
	
	@Autowired
	public AccountingController(AccountingService service) {
		this.service = service;
	}
	@PostMapping("/register")
	public ResponseUserDto registerUser(@RequestBody RegisterUserDto registerUserDto) {
		return service.registerUser(registerUserDto);
	}
	@PostMapping("/login")
	public ResponseUserDto login(Principal principal) {
			//@PathVariable String login
			//@RequestHeader("Authorization") String token) {
//		token = token.split(" ")[1];
//		byte[] bytesDecode = Base64.getDecoder().decode(token);
//		token = new String(bytesDecode);
////		System.out.println(token);
//		String[] credentials = token.split(":");
//		UserLoginDto userLoginDto = new UserLoginDto(credentials[0] , credentials[1]);
		return service.getUser(principal.getName());
	}
	@DeleteMapping("/user/{user}")
	public ResponseUserDto deleteUser(@PathVariable String user) {
		return service.deleteUser(user);
	}
	@PutMapping("/user/{user}")
	public ResponseUserDto updateUser(@PathVariable String user, @RequestBody UpdateUserDto updateUserDto) {
		return service.updateUser(user, updateUserDto);
	}
	@PutMapping("/user/{user}/role/{role}")
	public ResponseRoleDto addRole(@PathVariable String user,@PathVariable String role) {
		return service.addRole(user, role);
	}
	@DeleteMapping("user/{user}/role/{role}")
	public ResponseRoleDto deleteRole(@PathVariable String user,@PathVariable String role) {
		return service.deleteRole(user, role);
	}
	@PutMapping("/user/password")
	public void changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
		service.changePassword(changePasswordDto);
	}
}

package telran.b7a.accounting.service;

import java.security.Principal;

import telran.b7a.accounting.dto.ChangePasswordDto;
import telran.b7a.accounting.dto.RegisterUserDto;
import telran.b7a.accounting.dto.ResponseRoleDto;
import telran.b7a.accounting.dto.ResponseUserDto;
import telran.b7a.accounting.dto.UpdateUserDto;
import telran.b7a.accounting.dto.UserLoginDto;

public interface AccountingService {
	ResponseUserDto registerUser(RegisterUserDto registerUserDto);
	
	ResponseUserDto getUser(String login);
	
	ResponseUserDto deleteUser(String user);
	
	ResponseUserDto updateUser(String user, UpdateUserDto updateUserDto);
	
	ResponseRoleDto addRole(String user, String role);
	
	ResponseRoleDto deleteRole(String user, String role);
	
	boolean changePassword(String user, String password);
}

package telran.b7a.accounting.service;

import telran.b7a.accounting.dto.ChangePasswordDto;
import telran.b7a.accounting.dto.RegisterUserDto;
import telran.b7a.accounting.dto.ResponseRoleDto;
import telran.b7a.accounting.dto.ResponseUserDto;
import telran.b7a.accounting.dto.UpdateUserDto;
import telran.b7a.accounting.dto.UserLoginDto;

public interface AccountingService {
	ResponseUserDto registerUser(RegisterUserDto registerUserDto);
	
	ResponseUserDto login(UserLoginDto userLoginDto);
	
	ResponseUserDto deleteUser(String user);
	
	ResponseUserDto updateUser(String user, UpdateUserDto updateUserDto);
	
	ResponseRoleDto addRole(String user, String role);
	
	ResponseRoleDto deleteRole(String user, String role);
	
	boolean changePassword(ChangePasswordDto changePasswordDto);
}

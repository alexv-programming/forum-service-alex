package telran.b7a.accounting.service;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.accounting.dao.AccountingMongoRepository;
import telran.b7a.accounting.dto.ChangePasswordDto;
import telran.b7a.accounting.dto.RegisterUserDto;
import telran.b7a.accounting.dto.ResponseRoleDto;
import telran.b7a.accounting.dto.ResponseUserDto;
import telran.b7a.accounting.dto.UpdateUserDto;
import telran.b7a.accounting.dto.UserLoginDto;
import telran.b7a.accounting.dto.exception.UserNotFoundException;
import telran.b7a.accounting.dto.exception.WrongPasswordException;
import telran.b7a.accounting.model.User;

@Service
public class AccountingServiceImpl implements AccountingService {
	
	AccountingMongoRepository repository;
	
	ModelMapper modelMapper;
	
	@Autowired
	public AccountingServiceImpl(AccountingMongoRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseUserDto registerUser(RegisterUserDto registerUserDto) {
		User user = modelMapper.map(registerUserDto, User.class);
		repository.save(user);
		return modelMapper.map(user, ResponseUserDto.class);							
	}

	@Override
	public ResponseUserDto login(UserLoginDto userLoginDto) {
		User user = repository.findByLogin(userLoginDto.getLogin())
				.orElseThrow(() -> new UserNotFoundException(userLoginDto.getLogin()));
		if (!userLoginDto.getPassword().equals(user.getPassword())) {
			throw (new WrongPasswordException());
		}
		return modelMapper.map(user, ResponseUserDto.class);
	}

	@Override
	public ResponseUserDto deleteUser(String user) {
		User userInst = repository.findByLogin(user)
								.orElseThrow(() -> new UserNotFoundException(user));
		repository.deleteByLogin(user);
		return modelMapper.map(userInst, ResponseUserDto.class);
	}

	@Override
	public ResponseUserDto updateUser(String user, UpdateUserDto updateUserDto) {
		User userInst = repository.findByLogin(user)
							.orElseThrow(() -> new UserNotFoundException(user));
		String firstName = updateUserDto.getFirstName();
		if (firstName != null) {
			userInst.setFirstName(firstName);
		}
		String lastName = updateUserDto.getLastName();
		if (lastName != null) {
			userInst.setLastName(lastName);
		}	
		repository.save(userInst);
		return modelMapper.map(userInst, ResponseUserDto.class);
	}

	@Override
	public ResponseRoleDto addRole(String user, String role) {
		User userInst = repository.findByLogin(user)
							.orElseThrow(() -> new UserNotFoundException());
		userInst.getRoles().add(role);
		repository.save(userInst);
		return modelMapper.map(userInst, ResponseRoleDto.class);
	}

	@Override
	public ResponseRoleDto deleteRole(String user, String role) {
		User userInst = repository.findByLogin(user)
							.orElseThrow(() -> new UserNotFoundException());
		userInst.getRoles().remove(role);
		repository.save(userInst);
		return modelMapper.map(userInst, ResponseRoleDto.class);
	}

	@Override
	public boolean changePassword(ChangePasswordDto changePasswordDto) {
		User userInst = repository.findByLogin(changePasswordDto.getLogin())
							.orElseThrow(() -> new UserNotFoundException());
		userInst.setPassword(changePasswordDto.getPassword());
		return true;
	}

}

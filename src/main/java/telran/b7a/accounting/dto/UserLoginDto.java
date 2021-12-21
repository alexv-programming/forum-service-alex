package telran.b7a.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NonNull
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
	String login;
	String password;
}

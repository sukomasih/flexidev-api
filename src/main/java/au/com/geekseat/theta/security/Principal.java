package au.com.geekseat.theta.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Principal {

	private Long id;
	private String name;
	private String email;
	private String password;

}

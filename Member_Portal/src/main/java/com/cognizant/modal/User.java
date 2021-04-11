package com.cognizant.modal;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private String userid;
	private String upassword;
	private String uname;
	private String authToken;
	
}

package core.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class LoginUser implements UserDetails {

	private static final long serialVersionUID = -2315659388348422700L;

	private User user;
	private Collection<GrantedAuthority> authorities;


	public LoginUser(User user) {
		this.user = user;
	}
	


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return this.authorities;
	}


	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getPassword();
	}


	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getEmail();
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}
}

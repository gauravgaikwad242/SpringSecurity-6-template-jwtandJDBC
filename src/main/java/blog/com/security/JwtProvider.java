package blog.com.security;

import java.security.Key;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtProvider {

	private Key key;
	
	@PostConstruct
	public void init() {
		this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}
	
	public String generateToken(Authentication authenticate) {
	User principle = (User) authenticate.getPrincipal();
	return Jwts.builder()
			.setSubject(principle.getUsername())
			.signWith(key)
			.compact();
	}
}

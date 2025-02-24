package com.novel.novel.configuration;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
	private String jwtSecret = "sIoVC8OFOgmxbk9XRYtY2zMKXuYXBGL2d3x1IV37";

	private Long jwtExpiration = 60000000L;

	private Claims parseToken(String token) {
		JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build();

		try {
			return jwtParser.parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			System.out.println(e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.out.println(e.getMessage());
		} catch (MalformedJwtException e) {
			System.out.println(e.getMessage());
		} catch (SignatureException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	public boolean validateToken(String token) {
		return parseToken(token) != null;
	}

	public String getUsernameFromToken(String token) {
		Claims claims = parseToken(token);

		if (claims != null) {
			return claims.getSubject();
		}

		return null;
	}

	public String generateToken(String username) {
		Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

		var currentDate = new Date();
		var expiration = new Date(currentDate.getTime() + jwtExpiration);

		return Jwts.builder().setSubject(username).setIssuedAt(currentDate).setExpiration(expiration).signWith(key)
				.compact();
	}

}

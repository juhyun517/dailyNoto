package com.daily.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.daily.entity.User;
import com.daily.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{

	 private final UserRepository userRepository;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findByLoginId(username)
	                .orElseThrow(() -> {
	                    return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
	                });
	        return new PrincipalDetails(user);
	    }
}

package com.idgcapital.ufund.security.service;

import com.idgcapital.ufund.model.IdgStaff;
import com.idgcapital.ufund.mybatis.dao.IdgStaffMapper;
import com.idgcapital.ufund.security.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by yi_zhang on 2017/12/18.
 */
@Service
public class UserLoginService implements UserDetailsService {


    @Autowired
    private IdgStaffMapper idgStaffMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        IdgStaff user = idgStaffMapper.selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("username is not exist:" + username + "信息!");
        }
        Collection<GrantedAuthority> collection = new ArrayList<>();
        //todo 拿到实际
        collection.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        collection.add(new SimpleGrantedAuthority("ROLE_USER"));
        //UserDetails userDetails = new User(user.getUsername(),user.getPassword(),collection);
        LoginUser loginUser = new LoginUser(user.getStaffid(), user.getUsername(), passwordEncoder().encode(user.getPassword()), collection);

        return loginUser;
    }
}

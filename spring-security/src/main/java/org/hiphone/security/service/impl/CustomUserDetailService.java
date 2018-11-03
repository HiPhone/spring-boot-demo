package org.hiphone.security.service.impl;

import org.hiphone.security.constants.Constant;
import org.hiphone.security.entitys.UserDTO;
import org.hiphone.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HiPhone
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userMapper.getUserByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("user is not found in database!");
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Constant.userRoleMap.get(user.getRole())));

        return new User(user.getLoginName(), user.getPassword(),authorities);
    }

}

package com.myApp.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.myApp.POJO.Password;
import com.myApp.POJO.domain.User;
import com.myApp.POJO.repository.UserRepository;
import com.myApp.gwt.client.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Fedir on 03.03.2016.
 */
@Service("loginService")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    final static Logger logger = Logger.getLogger(LoginServiceImpl.class);
    @Autowired
    private UserRepository userRepository;


    @Override
    public String loginUser(String username, String password) {
        User user = getByUserName(username);
        if (user == null) {
            logger.info("Failed to find user in DB:" + username);
        } else
            logger.info("Getting from DB user:" + username);

        return (user != null) && (Password.checkPassword(password, user.getSalt(), user.getPassword())) ?
                getByUserName(username).getFirstname() :
                null;
    }


    public User getByUserName(String username) {
        return userRepository.findOne(username);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        String salt = Password.generateSalt();

        user.setPassword(Password.getHashedPassword(user.getPassword(), salt));
        user.setSalt(salt);

        userRepository.save(user);

    }
}

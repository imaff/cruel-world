package com.chat.cruel.services;

import com.chat.cruel.beans.UserBeanAuth;
import com.chat.cruel.beans.UserBeanSession;
import com.chat.cruel.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public UserBeanSession login(UserBeanAuth beanAuth) {
        return  userDao.getUser(beanAuth);
    }

    @Override
    public UserBeanSession registration(UserBeanAuth beanAuth) {
        return userDao.registration(beanAuth);
    }
}

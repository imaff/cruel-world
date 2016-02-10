package com.chat.cruel.dao;

import com.chat.cruel.beans.UserBeanAuth;
import com.chat.cruel.beans.UserBeanSession;

public interface IUserDao {
    UserBeanSession getUser(UserBeanAuth beanAuth);

    UserBeanSession registration(UserBeanAuth beanAuth);
}

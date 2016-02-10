package com.chat.cruel.services;

import com.chat.cruel.beans.UserBeanAuth;
import com.chat.cruel.beans.UserBeanSession;

public interface IUserService {
    UserBeanSession login(UserBeanAuth beanAuth);

    UserBeanSession registration(UserBeanAuth beanAuth);
}

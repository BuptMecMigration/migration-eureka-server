package com.service;

import com.error.BusinessException;
import com.response.CommonReturnType;
import com.service.model.AdminModel;
import com.service.model.UserModel;

/***
 * @apiNote 此接口主要用来管理服务中心上普通用户和管理员的身份鉴定任务
 */
public interface UserIdentityService {

    void adminRegister(AdminModel adminModel) throws BusinessException;

    AdminModel adminValidateLogin(Integer adminId, String password) throws BusinessException;

    UserModel userRegister(UserModel userModel) throws BusinessException;

    UserModel userValidateLogin(Integer userId, String password) throws BusinessException;
}

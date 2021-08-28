package com.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.dao.AdminDOMapper;
import com.dao.AdminPasswordDOMapper;
import com.dao.UserDOMapper;
import com.dao.UserPasswordDOMapper;
import com.dataobject.AdminDO;
import com.dataobject.AdminPasswordDO;
import com.dataobject.UserDO;
import com.dataobject.UserPasswordDO;
import com.error.BusinessException;
import com.error.UserError;
import com.response.CommonReturnType;
import com.service.UserIdentityService;
import com.service.model.AdminModel;
import com.service.model.UserModel;
import com.validator.ValidationResult;
import com.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserIdentityServiceImpl implements UserIdentityService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private AdminDOMapper adminDOMapper;

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private AdminPasswordDOMapper adminPasswordDOMapper;

    //管理员注册接口
    @Override
    public void adminRegister(AdminModel adminModel) throws BusinessException {
        if(null == adminModel){
            throw new BusinessException(UserError.ADMIN_REGISTER_FAIL);
        }

        ValidationResult validationResult = validator.validate(adminModel);
        if (validationResult.isHasErrors()){
            throw new BusinessException(UserError.ADMIN_REGISTER_FAIL, validationResult.getErrMsg());
        }

        //实现model转化为dataobject
        AdminDO adminDO= convertFromAdminModel(adminModel);

        try{
            adminDOMapper.insertSelective(adminDO);
        } catch (DuplicateKeyException ex){
            throw new BusinessException(UserError.ADMIN_REGISTER_DUB, "管理员注册重复");
        }

        adminModel.setAdminId(adminDO.getAdminId());

        AdminPasswordDO adminPasswordDO = convertPasswordFromAdminModel(adminModel);

        adminPasswordDOMapper.insertSelective(adminPasswordDO);
    }

    //管理员登录验证
    @Override
    public AdminModel adminValidateLogin(Integer adminId, String password) throws BusinessException {
        //查询
        AdminDO adminDO = adminDOMapper.selectByPrimaryKey(adminId);
        //判断
        if(null == adminDO){
            throw new BusinessException(UserError.ADMIN_LOGIN_FAIL);
        }
        AdminPasswordDO adminPasswordDO = adminPasswordDOMapper.selectByPrimaryKey(adminDO.getAdminId());
        AdminModel adminModel = convertAdminFromDataObject(adminDO, adminPasswordDO);
        //拿到用户信息内加密的密码是否和传输的是否相匹配
        if(!StringUtils.equals(password,adminPasswordDO.getEncrptPassword())){
            throw new BusinessException(UserError.ADMIN_LOGIN_FAIL);
        }
        return adminModel;
    }

    //用户注册接口
    @Override
    public UserModel userRegister(UserModel userModel) throws BusinessException {
        if(null == userModel){
            throw new BusinessException(UserError.USER_REGISTER_FAIL);
        }

        ValidationResult validationResult = validator.validate(userModel);
        if (validationResult.isHasErrors()){
            throw new BusinessException(UserError.USER_REGISTER_FAIL, validationResult.getErrMsg());
        }

        //实现model转化为dataobject
       UserDO userDO = convertFromUserModel(userModel);

        try{
            userDOMapper.insertSelective(userDO);
        }catch (DuplicateKeyException ex) {
            throw new BusinessException(UserError.USER_REGISTER_DUB, "用户注册重复");
        }

        userModel.setUserId(userDO.getUserId());

        UserPasswordDO userPasswordDO = convertPasswordFromUserModel(userModel);

        userPasswordDOMapper.insertSelective(userPasswordDO);

        return userModel;
    }

    //管理员注册接口
    @Override
    public UserModel userValidateLogin(Integer userId, String password) throws BusinessException {
        //查询
        UserDO userDO = userDOMapper.selectByPrimaryKey(userId);
        //判断
        if(null == userDO){
            throw new BusinessException(UserError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getUserId());
        UserModel userModel = convertUserFromDataObject(userDO, userPasswordDO);
        //拿到用户信息内加密的密码是否和传输的是否相匹配
        if(!StringUtils.equals(password,userPasswordDO.getEncrptPassword())){
            throw new BusinessException(UserError.USER_LOGIN_FAIL);
        }
        return userModel;
    }

    private AdminDO convertFromAdminModel(AdminModel adminModel){
        AdminDO adminDO = new AdminDO();
        if(null == adminModel){
            return null;
        }
        BeanUtils.copyProperties(adminModel, adminDO);
        return adminDO;
    }

    private UserDO convertFromUserModel(UserModel userModel){
        UserDO userDO = new UserDO();
        if(null == userModel){
            return null;
        }
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }

    private AdminPasswordDO convertPasswordFromAdminModel(AdminModel adminModel){
        if(null == adminModel){
            return null;
        }
        AdminPasswordDO adminPasswordDO =  new AdminPasswordDO();
        adminPasswordDO.setEncrptPassword(adminModel.getEncrptPassword());
        adminPasswordDO.setAdminId(adminModel.getAdminId());
        return adminPasswordDO;
    }

    private UserPasswordDO convertPasswordFromUserModel(UserModel userModel){
        if(null == userModel){
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setUserId(userModel.getUserId());
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        return userPasswordDO;
    }

    private AdminModel convertAdminFromDataObject(AdminDO adminDO, AdminPasswordDO adminPasswordDO){
        AdminModel adminModel = new AdminModel();
        if(null == adminDO){
            return null;
        }
        BeanUtils.copyProperties(adminDO, adminModel);
        if(null != adminPasswordDO){
            adminModel.setEncrptPassword(adminPasswordDO.getEncrptPassword());
        }
        return adminModel;
    }

    private UserModel convertUserFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){
        UserModel userModel = new UserModel();
        if(null == userDO){
            return null;
        }
        BeanUtils.copyProperties(userDO, userModel);
        if(null != userPasswordDO){
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }
        return userModel;
    }
}

package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.dataobject.UserDO;
import com.error.BusinessException;
import com.error.UserError;
import com.response.CommonReturnType;
import com.service.UserIdentityService;
import com.service.model.AdminModel;
import com.service.model.UserModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@Controller("user")
@RequestMapping("/user")
//对跨域请求参数进行设置保证session中的信息跨域得到读取
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserController extends BaseController {

    @Autowired
    UserIdentityService userIdentityService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //用户注册
    @RequestMapping(value = "/register", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public CommonReturnType register(@RequestBody String jsonString) throws BusinessException {

        //RPC串校验
        if (null == jsonString) {
            //throw new BusinessException(UserError.USER_JSON_NULL);
            return CommonReturnType.create("用户注册失败，json为空");
        }

        // @TODO 校验第三方ID或采取opt形式,此处略

        UserModel userModel = JSONObject.parseObject(jsonString, UserModel.class);
        UserModel result = userIdentityService.userRegister(userModel);

        return CommonReturnType.create(result);
    }

    //用户登录
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public CommonReturnType login(@RequestBody String jsonString) throws BusinessException {

        //RPC串校验
        if (null == jsonString) {
            //throw new BusinessException(UserError.USER_JSON_NULL);
            return CommonReturnType.create("用户注册失败，json为空");
        }

        UserModel userModel = JSONObject.parseObject(jsonString, UserModel.class);

        //入参校验
        if( userModel.getUserId() < 0 || StringUtils.isEmpty(userModel.getEncrptPassword())){
            throw new BusinessException(UserError.PARAMETER_VALIDATION_ERROR);
        }

        //身份校验
        UserModel res = userIdentityService.userValidateLogin(userModel.getUserId(), userModel.getEncrptPassword());

        return CommonReturnType.create(res);
    }

    public String enCodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Base64.Encoder encoder = Base64.getEncoder();
        String newStr = encoder.encodeToString(md5.digest(str.getBytes("utf-8")));
        //        BASE64Encoder base64Encoder = new BASE64Encoder();
        // 加密字符串
        //        String newStr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newStr;
    }

}

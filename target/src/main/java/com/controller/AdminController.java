package com.controller;

import com.dataobject.AdminDO;
import com.error.BusinessException;
import com.error.UserError;
import com.response.CommonReturnType;
import com.service.UserIdentityService;
import com.service.model.AdminModel;
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
import java.util.concurrent.TimeUnit;

@Controller("admin")
@RequestMapping("/admin")
//对跨域请求参数进行设置保证session中的信息跨域得到读取
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class AdminController extends BaseController {

    @Autowired
    UserIdentityService userIdentityService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //管理员登录
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "adminId")Integer adminId,
                                  @RequestParam(name = "password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        //入参校验
        if( adminId < 0 || StringUtils.isEmpty(password)){
            throw new BusinessException(UserError.PARAMETER_VALIDATION_ERROR);
        }

        //身份校验
        AdminModel adminModel = userIdentityService.adminValidateLogin(adminId, this.enCodeByMD5(password));
        //登录状态加入session
        this.httpServletRequest.getSession().setAttribute("ADMIN_IS_LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_ADMIN", adminModel);

        //@TODO 修改成登录成功后将对应的登录信息和登录凭证一起存入redis

        //生成登录凭证,UUID
        String uuidToken = UUID.randomUUID().toString();
        uuidToken = uuidToken.replace("-", "");

        // @TODO redis配置部分已经去除
        //建立redis与uuid之间的联系，需要一个uuid的操作类
        //redisTemplate.opsForValue().set(uuidToken, adminModel);
        //超时时间
        //redisTemplate.expire(uuidToken, 1, TimeUnit.HOURS);

        //下发token
        return CommonReturnType.create(uuidToken);
    }

    //管理员注册
    @RequestMapping(value = "/register", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name= "name")String name,
                                     @RequestParam(name = "thirdPartyId")String thirtyPartyId,
                                     @RequestParam(name = "password")String password
    ) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        //入参校验
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(thirtyPartyId)){
            throw new BusinessException(UserError.PARAMETER_VALIDATION_ERROR);
        }

        // @TODO 校验第三方ID或采取opt形式,此处略

        AdminModel adminModel = new AdminModel();
        adminModel.setAdminName(name);
        adminModel.setThirdpartyId(thirtyPartyId);
        adminModel.setEncrptPassword(this.enCodeByMD5(password));

        userIdentityService.adminRegister(adminModel);

        return CommonReturnType.create("管理员注册成功");
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

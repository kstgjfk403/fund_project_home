package com.idgcapital.ufund.common.controller;

import com.github.pagehelper.PageInfo;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.model.view.ReturnCode;
import com.idgcapital.ufund.security.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

/**
 * Created by li_zhao on 2017/12/13.
 */
@Controller
public class BaseController {


    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;


    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    /**
     * todo
     */
    public LoginUser getLoginUser() {

        //如果需要我们自己的user,比如登录时候拿到相关权限列表， 则建议创建一个登录用户类继承userdetails;
         ////LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         //String name = user.getUsername(); //get logged in username

        //test
        LoginUser user = new LoginUser("S0234","cho_wang",",./",null);
        return user;
    }

    public ResponseEntity<JsonResult> wrapperJsonResult(Object result) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(ReturnCode.SUCCESS);
        jsonResult.setReturnMessage("SUCCESS");
        jsonResult.setData(result);
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }

    public ResponseEntity<JsonResult> wrapperPageResult(PageInfo pageInfo) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(ReturnCode.SUCCESS);
        jsonResult.setReturnMessage("SUCCESS");
        jsonResult.setData(pageInfo.getList());
        jsonResult.setCount(pageInfo.getTotal());
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }

    public ResponseEntity<JsonResult>  returnSuccessJson(String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(ReturnCode.SUCCESS);
        jsonResult.setReturnMessage(message);

        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }

    public ResponseEntity<JsonResult> returnErrorJson(String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(ReturnCode.ERROR);
        jsonResult.setReturnMessage(message);

        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }
}

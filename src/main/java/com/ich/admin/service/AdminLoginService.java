package com.ich.admin.service;

import com.ich.core.http.entity.HttpResponse;

public interface AdminLoginService {

    /** 员工登录 */
    public HttpResponse executeLogin(String sessionid,String logincode,String loginkey);

    /** 员工修改密码 */
    public HttpResponse editKey(String oldkey,String newkey);

}

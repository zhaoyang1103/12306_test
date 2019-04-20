package com.example.a12306test.bean;

/**
 * Created by 昭阳 on 2019/4/20.
 */
public class LoginBean {

    /**
     * result_message : 密码输入错误。如果输错次数超过4次，用户将被锁定。
     * result_code : 1
     */

    private String result_message;
    private int result_code;
    /**
     * uamtk : Ai1CqJRfkKeRTKw-I2vNEBqSDwPeqGvC7BKooQwej2j0
     */

    private String uamtk;

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public String getUamtk() {
        return uamtk;
    }

    public void setUamtk(String uamtk) {
        this.uamtk = uamtk;
    }
}

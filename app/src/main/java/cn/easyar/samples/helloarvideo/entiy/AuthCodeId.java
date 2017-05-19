package cn.easyar.samples.helloarvideo.entiy;

/**
 * Created by BlackBeard丶 on 2017/5/11.
 */

public class AuthCodeId {
    result1 result;
    String code;

    public result1 getResult() {
        return result;
    }

    public void setResult(result1 result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public class result1{
     String authCodeId;

        public String getAuthCodeId() {
            return authCodeId;
        }

        public void setAuthCodeId(String authCodeId) {
            this.authCodeId = authCodeId;
        }
    }
}

package cn.easyar.samples.helloarvideo.entiy;


/**
 * Created by BlackBeard丶 on 2017/03/04.
 */
public class LoginResult {

    getresult result;
    String code;

    public getresult getResult() {
        return result;
    }

    public void setResult(getresult result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public class getresult {
        String liveSeconds;
        String sessionId;

        public void setLiveSeconds(String liveSeconds) {
            this.liveSeconds = liveSeconds;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getLiveSeconds() {
            return liveSeconds;
        }

        public String getSessionId() {
            return sessionId;
        }

        public getaccount getAccount() {
            return account;
        }

        public void setAccount(getaccount account) {
            this.account = account;
        }

        getaccount account;

        public class getaccount {
            String birthday;
            String gender;
            String nick_name;
            String name;

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            String tel;
            String id;
            String username;
            String status;
        }
    }

}

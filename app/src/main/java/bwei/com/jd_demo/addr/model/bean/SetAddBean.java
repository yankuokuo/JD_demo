package bwei.com.jd_demo.addr.model.bean;

import java.util.List;

public class SetAddBean {

    /**
     * msg : 更新成功
     * code : 0
     * data : [{"addr":"北京市昌平区金域国际1-1-1","addrid":9953,"mobile":15210643676,"name":"Gao","status":0,"uid":15314},{"addr":"北京市海淀区","addrid":9954,"mobile":1510643676,"name":"高宏剑","status":1,"uid":15314},{"addr":"北京海淀上地软件园南站","addrid":9972,"mobile":1521064367,"name":"刘家安1","status":0,"uid":15314}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * addr : 北京市昌平区金域国际1-1-1
         * addrid : 9953
         * mobile : 15210643676
         * name : Gao
         * status : 0
         * uid : 15314
         */

        private String addr;
        private int addrid;
        private long mobile;
        private String name;
        private int status;
        private int uid;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getAddrid() {
            return addrid;
        }

        public void setAddrid(int addrid) {
            this.addrid = addrid;
        }

        public long getMobile() {
            return mobile;
        }

        public void setMobile(long mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}

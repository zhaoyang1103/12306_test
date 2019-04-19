package com.example.a12306test.bean;

import java.util.List;

/**
 * Created by 昭阳 on 2019/4/16.
 */
public class Query_TacketBean {

    /**
     * data : {"flag":"1","map":{"CQW":"重庆","CUW":"重庆北","TJP":"天津"},"result":["|预订|77000K157410|K1574|CUW|JLL|CUW|TJP|15:00|19:12|28:12|N|yXB0DBKa7D3w%2BLpDz8vX7q6HhefnnOMxWsitXVg%2BolXjnBk5PwPu1cUlnew%3D|20190416|3|W2|01|23|0|0||||无|||无||无|无|||||10403010|1431|0|0|null","yjVI6HpCm57PaC%2FvUkbUbC05VK6WfFRTixrN9CH3LK7fmfoE4QuHgILIn%2Fi3DruwW1NPX1H%2B7%2Bss%0A1DaUXKKXeK%2Bc1FZ4CShIWRDimdt7nrCh%2FvrZjOoSUOXA%2Fs%2FyKywo3ojR%2FLc45Uxl1o7X6QFlW3Gq%0ANje6mm1HwjlIVPWMUDe4PR5GaD7Nq8jQttAGyBaoflTAAdkCPatgZbCZc7Mw5SxjDSNlxh2sjMI4%0AdWud8BPpCAeDu1R7whJn7dzveUgHOXNL8W9qQBZQg3jfmShLP2a%2BVVuUPRUzPrFzDxp350O9m%2B2w%0AXsOUwA%3D%3D|预订|77000K10640Q|K1064|CQW|VAB|CQW|TJP|21:36|05:28|31:52|Y|qiQVZt%2Bs5RneE8Kny9Cx5%2BHyfGAtPnYeZ7xidkOL9BSQUwTez1ZXaZFPuDw%3D|20190416|3|W2|01|25|0|0||||无|||有||无|有|||||10401030|1413|0|0|null"]}
     * httpstatus : 200
     * messages :
     * status : true
     */

    private DataBean data;
    private int httpstatus;
    private String messages;
    private boolean status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getHttpstatus() {
        return httpstatus;
    }

    public void setHttpstatus(int httpstatus) {
        this.httpstatus = httpstatus;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * flag : 1
         * map : {"CQW":"重庆","CUW":"重庆北","TJP":"天津"}
         * result : ["|预订|77000K157410|K1574|CUW|JLL|CUW|TJP|15:00|19:12|28:12|N|yXB0DBKa7D3w%2BLpDz8vX7q6HhefnnOMxWsitXVg%2BolXjnBk5PwPu1cUlnew%3D|20190416|3|W2|01|23|0|0||||无|||无||无|无|||||10403010|1431|0|0|null","yjVI6HpCm57PaC%2FvUkbUbC05VK6WfFRTixrN9CH3LK7fmfoE4QuHgILIn%2Fi3DruwW1NPX1H%2B7%2Bss%0A1DaUXKKXeK%2Bc1FZ4CShIWRDimdt7nrCh%2FvrZjOoSUOXA%2Fs%2FyKywo3ojR%2FLc45Uxl1o7X6QFlW3Gq%0ANje6mm1HwjlIVPWMUDe4PR5GaD7Nq8jQttAGyBaoflTAAdkCPatgZbCZc7Mw5SxjDSNlxh2sjMI4%0AdWud8BPpCAeDu1R7whJn7dzveUgHOXNL8W9qQBZQg3jfmShLP2a%2BVVuUPRUzPrFzDxp350O9m%2B2w%0AXsOUwA%3D%3D|预订|77000K10640Q|K1064|CQW|VAB|CQW|TJP|21:36|05:28|31:52|Y|qiQVZt%2Bs5RneE8Kny9Cx5%2BHyfGAtPnYeZ7xidkOL9BSQUwTez1ZXaZFPuDw%3D|20190416|3|W2|01|25|0|0||||无|||有||无|有|||||10401030|1413|0|0|null"]
         */

        private String flag;
        private MapBean map;
        private List<String> result;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public List<String> getResult() {
            return result;
        }

        public void setResult(List<String> result) {
            this.result = result;
        }

        public static class MapBean {
            /**
             * CQW : 重庆
             * CUW : 重庆北
             * TJP : 天津
             */

            private String CQW;
            private String CUW;
            private String TJP;

            public String getCQW() {
                return CQW;
            }

            public void setCQW(String CQW) {
                this.CQW = CQW;
            }

            public String getCUW() {
                return CUW;
            }

            public void setCUW(String CUW) {
                this.CUW = CUW;
            }

            public String getTJP() {
                return TJP;
            }

            public void setTJP(String TJP) {
                this.TJP = TJP;
            }
        }
    }
}

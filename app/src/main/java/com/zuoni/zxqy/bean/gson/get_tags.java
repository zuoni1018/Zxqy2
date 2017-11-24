package com.zuoni.zxqy.bean.gson;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/11/24
 */

public class get_tags extends BaseHttpResponse {

    /**
     * data : [{"content":"薪假"},{"content":"保险"},{"content":"公游"},{"content":"公训"},{"content":"8h"},{"content":"年奖"},{"content":"工餐"},{"content":"双休"}]
     * status : true
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : 薪假
         */
        private boolean isChoose;
        private String content;

        public boolean isChoose() {
            return isChoose;
        }

        public void setChoose(boolean choose) {
            isChoose = choose;
        }
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

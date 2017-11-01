package com.zuoni.zxqy.bean.gson;

import com.zuoni.zxqy.bean.model.CompanyInfo;

/**
 * Created by zangyi_shuai_ge on 2017/10/27
 */

public class GetCompanyInfo extends BaseHttpResponse {


    /**
     * data : {"cname":"hjij","title":"fyuu","email":"","phone":"15168212330","web":"","info":"杭州晖鸿科技有限公司隶属于杭州鸿鹄电子有限公司，是公司专业从事软件技术开发、信息化建设的高新技术团队。公司研发团队坐落于中国杭州核心互联网区域未来科技城内，拥有行业内最顶尖的人才和软硬件基础，同时秉承着鸿鹄电子优良的企业传统，融合时下最新的互联网技术和概念，为各行各业的客户提供优质、高科技含量的产品和技术支持，满足不同客户在信息化时代的发展需要。      公司由80、90后中青年组成，涵盖JAVA、PHP、.NET、HTML5、Android、IOS、微信等市面主流技术和研发能力。由10年以上开发经验工程师带队，结合各大高校优质毕业生，结合打造一支年轻又富有潜力，全面又不失专精的高质量技术团队。jjjjyyyyu","address":"点击获取公司地址","type":"私营企业","logo":"http://106.14.212.85:8080/52dyjob/Public/Uploads/company/","lawer":"","tele":"gui","name":"yyuu","fax":"","cateId":"26"}
     * status : true
     */

    private CompanyInfo data;

    public CompanyInfo getData() {
        return data;
    }

    public void setData(CompanyInfo data) {
        this.data = data;
    }
}

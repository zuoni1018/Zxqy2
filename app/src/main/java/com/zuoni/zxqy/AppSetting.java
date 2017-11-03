package com.zuoni.zxqy;

import com.zuoni.zxqy.bean.model.Dictionaries;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/10/26
 * 这个类用来存储所有滚轮列表
 */

public class AppSetting {
    public static List<Dictionaries> mList;

    public static  String phone="";

    public static String getDictionary(String key) {

        if (mList == null) {
            return null;
        } else {
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).getName().equals(key)) {
                    return mList.get(i).getData();
                }
            }
        }
        return null;
    }

//    {
//        "status": true,
//            "data": [
//        {
//            "name": "email",
//                "data": "a:3:{s:6:\"server\";s:16:\"smtp.vip.163.com\";s:4:\"mail\";s:19:\"52ykjob@vip.163.com\";s:4:\"pass\";s:11:\"wang1978223\";}",
//                "remark": "发送邮件信息"
//        },
//        {
//            "name": "user_company",
//                "data": "私营企业,国有企业,外资企业,三资企业,行政机关,社会团体,事业单位,集体企业,股份制,集体事业,个人工作室",
//                "remark": "公司性质"
//        },
//        {
//            "name": "user_per",
//                "data": "24,1,3,6,12",
//                "remark": "查看简历周期"
//        },
//        {
//            "name": "user_tele",
//                "data": "先投简历再电话联系,邮件联系,电话或手机联系",
//                "remark": "联系方法"
//        },
//        {
//            "name": "comp_nation",
//                "data": "汉族,回族,蒙古族,满族,壮族,维吾尔族,藏族,朝鲜族,其他少数民族",
//                "remark": "民族"
//        },
//        {
//            "name": "comp_marry",
//                "data": "已婚,未婚,离异,保密",
//                "remark": "婚姻状况"
//        },
//        {
//            "name": "comp_cred",
//                "data": "身份证,护照,军官证,学生证,其他",
//                "remark": "证件类型"
//        },
//        {
//            "name": "comp_political",
//                "data": "群众,党员,团员",
//                "remark": "政治面貌"
//        },
//        {
//            "name": "comp_jobs",
//                "data": "全职,兼职",
//                "remark": "求职类型"
//        },
//        {
//            "name": "comp_pay",
//                "data": "面议,500-999元,1000-1999元,2000-3999元,4000-5999元,6000-7999元,8000-11999元,12000-19999元,20000以上",
//                "remark": "待遇要求"
//        },
//        {
//            "name": "comp_house",
//                "data": "面议,自行解决,提供住宿,包吃包住",
//                "remark": "住房要求"
//        },
//        {
//            "name": "comp_times",
//                "data": "我目前处于离职状态(或应届生).可立即上岗,正在考虑换新环境(如有合适的工作机会.到岗时间一个星期至一个月左右),我对现有工作还算满意.如有更好的工作机会.我也可以考虑。(到岗时间另议),目前暂无跳槽打算",
//                "remark": "到岗状况"
//        },
//        {
//            "name": "comp_edu",
//                "data": "中专,高中,职高,大专,本科,硕士,博士,技术人员/自学,其他",
//                "remark": "学 历"
//        },
//        {
//            "name": "comp_lang",
//                "data": "英语,日语,法语,德语,西班牙语,阿拉伯语,俄语,韩语,其他",
//                "remark": "第一外语"
//        },
//        {
//            "name": "comp_level",
//                "data": "一般,熟练,精通",
//                "remark": "外语水平"
//        },
//        {
//            "name": "comp_computer",
//                "data": "一般,熟练,精通",
//                "remark": "计算机水平"
//        },
//        {
//            "name": "comp_years",
//                "data": "1,2,3,5,8,10",
//                "remark": "工作经验"
//        },
//        {
//            "name": "receive_mail",
//                "data": "1525950257@qq.com",
//                "remark": ""
//        },
//        {
//            "name": "utom_msg_max",
//                "data": "50",
//                "remark": ""
//        }
//    ],
//        "message": "成功获取字典"
//    }


}

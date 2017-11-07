package com.zuoni.zxqy.bean.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/11/7
 */

public class ResumeDetail extends BaseHttpResponse {

    /**
     * status : true
     * data : {"id":"134370","oldid":"0","userid":"fjp1989","name":"钱兴娟","email":"986236463@qq.com","sid":"2","sex":"女","nation":"汉族","jiguan":"浙江绍兴","height":"","type":"往届","birth":"1989-05-05","marry":"未婚","cred":"身份证","credid":"","political":"群众","weight":"0","home":"金华永康","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/","tele":"","qq":"","phone":"13575583231","msg_today":"3","msg_last":"1446480000","zipcode":"","address":"金华永康花川","requestjobtype":"全职","requestsalary":"面议","requeststay":"提供住宿","jobstatus":"我目前处于离职状态(或应届生).可立即上岗","hopepostion":"会计，出纳","hopelocation":"100106","graduatedfrom":"浙江工贸职业技术学院","educationid":"大专","education":"大专","speciality":"会计","graduatetime":"2011-06-30","foreignlanguage":"英语","foreignlanguagelevel":"一般","computerlevel":"一般","otherability":"","certificate":"会计证","educationhistory":"","jobyear":"3","jobhistory":[{"begin_time":"","end_time":"","company_name":"","company_nature":"私营企业","position":"","skill":""},{"begin_time":"2011-07-01","end_time":"2013-05-15","company_name":"乐恩教育用品（北京）有限公司","company_nature":"私营企业","position":"出纳","skill":"1、办理银行存款和现金领取，保管库存现金\r\n2、负责支票，汇票，发票，收据管理 \r\n3、负责填开支票、收到支票填写支票进帐单并送银行存支票\r\n4、做银行存款日记账和现金日记账，并负责保管财务章 \r\n5、负责员工各项费用报销  \r\n6、办理现金收付，办理往来结算 ，核算其他往来款项  \r\n7、负责工资核算，提供工资数据\r\n8、负责开具发票：增值税专用发票、营业税发票\r\n9、负责客户清单出货，清单登记库存\r\n10、负责办公室其他事务及领导安排事项"},{"begin_time":"2014-03-12","end_time":"2015-10-15","company_name":"绍兴市银铂利珠宝有限公司","company_nature":"私营企业","position":"会计助理","skill":"1、办理银行存款和现金领取，保管库存现金、网银u盾\r\n2、办理现金收付，做银行存款日记账和现金日记账 \r\n3、负责员工各项费用报销  \r\n4、负责员工工资核算及发放  \r\n5、每月月初根据各自营店营业报表核对每月营业额和pos机刷卡金额与现金\r\n6、每月月初根据各自营店销售单统计各店成本利润\r\n7、每月核对各店库存\r\n8、每月抄写各员工宿舍及各租户水电表计算水电费\r\n9、负责办公室其他事务及领导安排事项"}],"selfevaluation":"做事认真，责任心强，毕业后一直从事财务方面工作。","logins":"2","views":"15","reg_time":"2015-11-03 15:08:45","reg_ip":"122.227.48.182","last_time":"2015-11-12 14:59:25","last_ip":"122.227.48.182","update_time":"2015-11-12 14:59:25","is_locks":"0","is_hide":"0","status":"1"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 134370
         * oldid : 0
         * userid : fjp1989
         * name : 钱兴娟
         * email : 986236463@qq.com
         * sid : 2
         * sex : 女
         * nation : 汉族
         * jiguan : 浙江绍兴
         * height :
         * type : 往届
         * birth : 1989-05-05
         * marry : 未婚
         * cred : 身份证
         * credid :
         * political : 群众
         * weight : 0
         * home : 金华永康
         * img : http://106.14.212.85:8080/52dyjob/Public/Uploads/users/
         * tele :
         * qq :
         * phone : 13575583231
         * msg_today : 3
         * msg_last : 1446480000
         * zipcode :
         * address : 金华永康花川
         * requestjobtype : 全职
         * requestsalary : 面议
         * requeststay : 提供住宿
         * jobstatus : 我目前处于离职状态(或应届生).可立即上岗
         * hopepostion : 会计，出纳
         * hopelocation : 100106
         * graduatedfrom : 浙江工贸职业技术学院
         * educationid : 大专
         * education : 大专
         * speciality : 会计
         * graduatetime : 2011-06-30
         * foreignlanguage : 英语
         * foreignlanguagelevel : 一般
         * computerlevel : 一般
         * otherability :
         * certificate : 会计证
         * educationhistory :
         * jobyear : 3
         * jobhistory : [{"begin_time":"","end_time":"","company_name":"","company_nature":"私营企业","position":"","skill":""},{"begin_time":"2011-07-01","end_time":"2013-05-15","company_name":"乐恩教育用品（北京）有限公司","company_nature":"私营企业","position":"出纳","skill":"1、办理银行存款和现金领取，保管库存现金\r\n2、负责支票，汇票，发票，收据管理 \r\n3、负责填开支票、收到支票填写支票进帐单并送银行存支票\r\n4、做银行存款日记账和现金日记账，并负责保管财务章 \r\n5、负责员工各项费用报销  \r\n6、办理现金收付，办理往来结算 ，核算其他往来款项  \r\n7、负责工资核算，提供工资数据\r\n8、负责开具发票：增值税专用发票、营业税发票\r\n9、负责客户清单出货，清单登记库存\r\n10、负责办公室其他事务及领导安排事项"},{"begin_time":"2014-03-12","end_time":"2015-10-15","company_name":"绍兴市银铂利珠宝有限公司","company_nature":"私营企业","position":"会计助理","skill":"1、办理银行存款和现金领取，保管库存现金、网银u盾\r\n2、办理现金收付，做银行存款日记账和现金日记账 \r\n3、负责员工各项费用报销  \r\n4、负责员工工资核算及发放  \r\n5、每月月初根据各自营店营业报表核对每月营业额和pos机刷卡金额与现金\r\n6、每月月初根据各自营店销售单统计各店成本利润\r\n7、每月核对各店库存\r\n8、每月抄写各员工宿舍及各租户水电表计算水电费\r\n9、负责办公室其他事务及领导安排事项"}]
         * selfevaluation : 做事认真，责任心强，毕业后一直从事财务方面工作。
         * logins : 2
         * views : 15
         * reg_time : 2015-11-03 15:08:45
         * reg_ip : 122.227.48.182
         * last_time : 2015-11-12 14:59:25
         * last_ip : 122.227.48.182
         * update_time : 2015-11-12 14:59:25
         * is_locks : 0
         * is_hide : 0
         * status : 1
         */

        private String id;
        private String oldid;
        private String userid;
        private String name;
        private String email;
        private String sid;
        private String sex;
        private String nation;
        private String jiguan;
        private String height;
        private String type;
        private String birth;
        private String marry;
        private String cred;
        private String credid;
        private String political;
        private String weight;
        private String home;
        private String img;
        private String tele;
        private String qq;
        private String phone;
        private String msg_today;
        private String msg_last;
        private String zipcode;
        private String address;
        private String requestjobtype;
        private String requestsalary;
        private String requeststay;
        private String jobstatus;
        private String hopepostion;
        private String hopelocation;
        private String graduatedfrom;
        private String educationid;
        private String education;
        private String speciality;
        private String graduatetime;
        private String foreignlanguage;
        private String foreignlanguagelevel;
        private String computerlevel;
        private String otherability;
        private String certificate;
        private String educationhistory;
        private String jobyear;
        private String selfevaluation;
        private String logins;
        private String views;
        private String reg_time;
        private String reg_ip;
        private String last_time;
        private String last_ip;
        private String update_time;
        private String is_locks;
        private String is_hide;
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @SerializedName("status")
        private String statusX;
        private List<JobhistoryBean> jobhistory;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOldid() {
            return oldid;
        }

        public void setOldid(String oldid) {
            this.oldid = oldid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getJiguan() {
            return jiguan;
        }

        public void setJiguan(String jiguan) {
            this.jiguan = jiguan;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getMarry() {
            return marry;
        }

        public void setMarry(String marry) {
            this.marry = marry;
        }

        public String getCred() {
            return cred;
        }

        public void setCred(String cred) {
            this.cred = cred;
        }

        public String getCredid() {
            return credid;
        }

        public void setCredid(String credid) {
            this.credid = credid;
        }

        public String getPolitical() {
            return political;
        }

        public void setPolitical(String political) {
            this.political = political;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getHome() {
            return home;
        }

        public void setHome(String home) {
            this.home = home;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTele() {
            return tele;
        }

        public void setTele(String tele) {
            this.tele = tele;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMsg_today() {
            return msg_today;
        }

        public void setMsg_today(String msg_today) {
            this.msg_today = msg_today;
        }

        public String getMsg_last() {
            return msg_last;
        }

        public void setMsg_last(String msg_last) {
            this.msg_last = msg_last;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRequestjobtype() {
            return requestjobtype;
        }

        public void setRequestjobtype(String requestjobtype) {
            this.requestjobtype = requestjobtype;
        }

        public String getRequestsalary() {
            return requestsalary;
        }

        public void setRequestsalary(String requestsalary) {
            this.requestsalary = requestsalary;
        }

        public String getRequeststay() {
            return requeststay;
        }

        public void setRequeststay(String requeststay) {
            this.requeststay = requeststay;
        }

        public String getJobstatus() {
            return jobstatus;
        }

        public void setJobstatus(String jobstatus) {
            this.jobstatus = jobstatus;
        }

        public String getHopepostion() {
            return hopepostion;
        }

        public void setHopepostion(String hopepostion) {
            this.hopepostion = hopepostion;
        }

        public String getHopelocation() {
            return hopelocation;
        }

        public void setHopelocation(String hopelocation) {
            this.hopelocation = hopelocation;
        }

        public String getGraduatedfrom() {
            return graduatedfrom;
        }

        public void setGraduatedfrom(String graduatedfrom) {
            this.graduatedfrom = graduatedfrom;
        }

        public String getEducationid() {
            return educationid;
        }

        public void setEducationid(String educationid) {
            this.educationid = educationid;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getSpeciality() {
            return speciality;
        }

        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }

        public String getGraduatetime() {
            return graduatetime;
        }

        public void setGraduatetime(String graduatetime) {
            this.graduatetime = graduatetime;
        }

        public String getForeignlanguage() {
            return foreignlanguage;
        }

        public void setForeignlanguage(String foreignlanguage) {
            this.foreignlanguage = foreignlanguage;
        }

        public String getForeignlanguagelevel() {
            return foreignlanguagelevel;
        }

        public void setForeignlanguagelevel(String foreignlanguagelevel) {
            this.foreignlanguagelevel = foreignlanguagelevel;
        }

        public String getComputerlevel() {
            return computerlevel;
        }

        public void setComputerlevel(String computerlevel) {
            this.computerlevel = computerlevel;
        }

        public String getOtherability() {
            return otherability;
        }

        public void setOtherability(String otherability) {
            this.otherability = otherability;
        }

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public String getEducationhistory() {
            return educationhistory;
        }

        public void setEducationhistory(String educationhistory) {
            this.educationhistory = educationhistory;
        }

        public String getJobyear() {
            return jobyear;
        }

        public void setJobyear(String jobyear) {
            this.jobyear = jobyear;
        }

        public String getSelfevaluation() {
            return selfevaluation;
        }

        public void setSelfevaluation(String selfevaluation) {
            this.selfevaluation = selfevaluation;
        }

        public String getLogins() {
            return logins;
        }

        public void setLogins(String logins) {
            this.logins = logins;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getReg_ip() {
            return reg_ip;
        }

        public void setReg_ip(String reg_ip) {
            this.reg_ip = reg_ip;
        }

        public String getLast_time() {
            return last_time;
        }

        public void setLast_time(String last_time) {
            this.last_time = last_time;
        }

        public String getLast_ip() {
            return last_ip;
        }

        public void setLast_ip(String last_ip) {
            this.last_ip = last_ip;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getIs_locks() {
            return is_locks;
        }

        public void setIs_locks(String is_locks) {
            this.is_locks = is_locks;
        }

        public String getIs_hide() {
            return is_hide;
        }

        public void setIs_hide(String is_hide) {
            this.is_hide = is_hide;
        }

        public String getStatusX() {
            return statusX;
        }

        public void setStatusX(String statusX) {
            this.statusX = statusX;
        }

        public List<JobhistoryBean> getJobhistory() {
            return jobhistory;
        }

        public void setJobhistory(List<JobhistoryBean> jobhistory) {
            this.jobhistory = jobhistory;
        }

        public static class JobhistoryBean {
            /**
             * begin_time :
             * end_time :
             * company_name :
             * company_nature : 私营企业
             * position :
             * skill :
             */

            private String begin_time;
            private String end_time;
            private String company_name;
            private String company_nature;
            private String position;
            private String skill;

            public String getBegin_time() {
                return begin_time;
            }

            public void setBegin_time(String begin_time) {
                this.begin_time = begin_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getCompany_nature() {
                return company_nature;
            }

            public void setCompany_nature(String company_nature) {
                this.company_nature = company_nature;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getSkill() {
                return skill;
            }

            public void setSkill(String skill) {
                this.skill = skill;
            }
        }
    }
}

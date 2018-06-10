package com.drugs.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.drugs.R;
import com.drugs.bean.DrugsInfo;
import com.drugs.bean.DrugsUseInfo;
import com.drugs.bean.VisitInfo;
import com.drugs.dao.DBOperation;
import com.drugs.utils.EditTextClearTools;
import com.drugs.utils.Global;
import com.drugs.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity2 extends Activity {


    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.iv_unameClear)
    ImageView ivUnameClear;
    @BindView(R.id.rl_userName)
    RelativeLayout rlUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_pwdClear)
    ImageView ivPwdClear;
    @BindView(R.id.rl_userPassword)
    RelativeLayout rlUserPassword;
    @BindView(R.id.btn_login)
    TextView btnLogin;

    DBOperation db;//数据操作对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);
        init();

    }


    private void init() {

        db = new DBOperation(LoginActivity2.this);

        EditTextClearTools.addClearListener(etUserName, ivUnameClear);
        EditTextClearTools.addClearListener(etPassword, ivPwdClear);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {

        if (Utils.isViewEmpty(etUserName)) {
            Toast.makeText(getApplicationContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else if (Utils.isViewEmpty(etPassword)) {
            Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            String id = etUserName.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (db.UserExist(id)) {

                if (db.UserExist(id, password)) {
                    //saveUserInfo(id, db.GetDoctorNameByID(id), db.GetDoctorLevelByID(id));//保存医生登录状态
                    if (db.GetUserStatusByMobile(id) == 1) {
                        Global.login_state = true;//登陆成功
                        Global.login_mobile = id;
                        Global.login_id = String.valueOf(db.GetUserIdByMobile(id));

                        SharedPreferences config = getSharedPreferences("config", Activity.MODE_PRIVATE);

                        String ss = config.getString("index", "0");
                        if (ss.equals("0")) {
                            AddAllInfo();
                            SharedPreferences.Editor edit = config.edit();
                            edit.putString("index", "1");
                            edit.apply();//提交保存
                        }


                        SharedPreferences.Editor edit = config.edit();
                        edit.putString("loginMobile", id);
                        edit.putString("loginId", Global.login_id);
                        edit.apply();//提交保存
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "您被禁止登陆", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "当前用户不可登陆", Toast.LENGTH_SHORT).show();
            }
        }

        //finish();
    }


    private void AddAllInfo() {

        VisitInfo visitInfo = new VisitInfo();
        visitInfo.setMid(Global.login_id);
        visitInfo.setUid("10001");
        visitInfo.setVisittime("2018-06-06 01:49:53");
        visitInfo.setRemark("流行性感冒");
        db.AddVisitInfo(visitInfo);

        visitInfo = new VisitInfo();
        visitInfo.setMid(Global.login_id);
        visitInfo.setUid("10001");
        visitInfo.setVisittime("2018-06-07 07:15:50");
        visitInfo.setRemark("肠胃炎");
        db.AddVisitInfo(visitInfo);

        visitInfo = new VisitInfo();
        visitInfo.setMid(Global.login_id);
        visitInfo.setUid("10001");
        visitInfo.setVisittime("2018-06-08 15:35:03");
        visitInfo.setRemark("扁桃体炎");
        db.AddVisitInfo(visitInfo);

        visitInfo = new VisitInfo();
        visitInfo.setMid(Global.login_id);
        visitInfo.setUid("10001");
        visitInfo.setVisittime("2018-06-09 09:15:08");
        visitInfo.setRemark("低血糖");
        db.AddVisitInfo(visitInfo);

        visitInfo = new VisitInfo();
        visitInfo.setMid(Global.login_id);
        visitInfo.setUid("10001");
        visitInfo.setVisittime("2018-06-09 12:30:26");
        visitInfo.setRemark("耳鸣耳聋");
        db.AddVisitInfo(visitInfo);


        DrugsUseInfo drugsUseInfo = new DrugsUseInfo();
        drugsUseInfo.setTitle("流行性感冒");
        drugsUseInfo.setMid(Global.login_id);
        drugsUseInfo.setUid("10001");
        drugsUseInfo.setDrugname("三精双黄连口服液");
        drugsUseInfo.setCounts("2");
        drugsUseInfo.setUseingtime("2018-06-08 02:27:44");
        drugsUseInfo.setCreatetime("2018-06-08 02:25:48");
        db.AddDrugsUseInfo(drugsUseInfo);

        drugsUseInfo = new DrugsUseInfo();
        drugsUseInfo.setTitle("流行性感冒");
        drugsUseInfo.setMid(Global.login_id);
        drugsUseInfo.setUid("10001");
        drugsUseInfo.setDrugname("复方双花片");
        drugsUseInfo.setCounts("1");
        drugsUseInfo.setUseingtime("2018-06-08 04:57:17");
        drugsUseInfo.setCreatetime("2018-06-08 04:57:20");
        db.AddDrugsUseInfo(drugsUseInfo);

        drugsUseInfo = new DrugsUseInfo();
        drugsUseInfo.setTitle("流行性感冒");
        drugsUseInfo.setMid(Global.login_id);
        drugsUseInfo.setUid("10001");
        drugsUseInfo.setDrugname("强力VC银翘片");
        drugsUseInfo.setCounts("2");
        drugsUseInfo.setUseingtime("2018-06-08 04:58:17");
        drugsUseInfo.setCreatetime("2018-06-08 02:25:48");
        db.AddDrugsUseInfo(drugsUseInfo);

        drugsUseInfo = new DrugsUseInfo();
        drugsUseInfo.setTitle("肠胃炎");
        drugsUseInfo.setMid(Global.login_id);
        drugsUseInfo.setUid("10001");
        drugsUseInfo.setDrugname("立效 参苓白术散");
        drugsUseInfo.setCounts("1");
        drugsUseInfo.setUseingtime("2018-06-08 04:59:57");
        drugsUseInfo.setCreatetime("2018-06-08 02:25:48");
        db.AddDrugsUseInfo(drugsUseInfo);

        drugsUseInfo = new DrugsUseInfo();
        drugsUseInfo.setTitle("肠胃炎");
        drugsUseInfo.setMid(Global.login_id);
        drugsUseInfo.setUid("10001");
        drugsUseInfo.setDrugname("逸舒甘 盐酸左氧氟沙星胶囊");
        drugsUseInfo.setCounts("1");
        drugsUseInfo.setUseingtime("2018-06-08 05:00:26");
        drugsUseInfo.setCreatetime("2018-06-08 02:25:48");
        db.AddDrugsUseInfo(drugsUseInfo);


        DrugsInfo drugsInfo = new DrugsInfo();      // ID
        drugsInfo.setDrugname("双黄连口服液");       //药品名称
        drugsInfo.setDrugPrice("18.8");             //药品价格
        drugsInfo.setDescription("本品为棕红色的澄清液体；味甜，微苦。");                 //性状
        drugsInfo.setActions("如与其他药物同时使用可能会发生药物相互作用，详情请咨询医师或药师。");                     //药理作用
        drugsInfo.setIndications("清热解毒。用于风热感冒发热，咳嗽，咽痛；");                 //适应症
        drugsInfo.setContraindications("尚不明确");           //禁忌症
        drugsInfo.setDosageandadministration("口服，一次2支，一日3次。小儿酌减或遵医嘱。");     //用量用法
        drugsInfo.setAdversereactions("尚不明确");            //不良反应
        drugsInfo.setPrecautions("1.忌烟、酒及辛辣、生冷、油腻食物。\n" +
                "2.不宜在服药期间同时服用滋补性中药。\n" +
                "3.风寒感冒者不适用。\n" +
                "4.糖尿病患者及有高血压、心脏病、肝病、肾病等慢性病严重者应在医师指导下服用。\n" +
                "5.儿童、孕妇、哺乳期妇女、年老体弱及脾虚便溏者应在医师指导下服用。\n" +
                "6.发热体温超过38.5℃的患者，应去医院就诊。\n" +
                "7.服药3天症状无缓解，应去医院就诊。\n" +
                "8.对本品过敏者禁用，过敏体质者慎用。\n" +
                "9.本品性状发生改变时禁止使用。\n" +
                "10.儿童必须在成人监护下使用。\n" +
                "11.请将本品放在儿童不能接触的地方。\n" +
                "12.如正在使用其他药品，使用本品前请咨询医师或药师。");                 //注意事项
        drugsInfo.setPackages("玻璃瓶，每支装10毫升，每盒10支。");                    //包装
        drugsInfo.setStorage("密封，避光，置阴凉处。");                     //储藏
        drugsInfo.setOthers("6933968000123");                      //其他
        db.AddDrugsInfo(drugsInfo);

        drugsInfo = new DrugsInfo();      // ID
        drugsInfo.setDrugname("复方双花片");       //药品名称
        drugsInfo.setDrugPrice("32");             //药品价格
        drugsInfo.setDescription("本品为薄膜衣片，除去包衣后显棕褐色；味苦。");                 //性状
        drugsInfo.setActions("如与其他药物同时使用可能会发生药物相互作用，详情请咨询医师或药师。");                     //药理作用
        drugsInfo.setIndications("清热解毒，利咽消肿。用于风热外感、风热乳蛾。症见发热，微恶风，头痛，鼻塞流涕，咽红而痛或咽喉干燥灼痛，吞咽则加剧，咽及扁桃体红肿，舌边尖红苔薄黄或舌红苔黄，脉浮数或数。");                 //适应症
        drugsInfo.setContraindications("尚不明确");           //禁忌症
        drugsInfo.setDosageandadministration("口服.成人一次4片，一日4次；儿童三岁以下一次2片，一日3次；三岁至七岁一次2片，一日4次；七岁以上一次4片，一日3次，疗程3天。 ");     //用量用法
        drugsInfo.setAdversereactions("尚不明确");            //不良反应
        drugsInfo.setPrecautions(" 1.忌烟、酒及辛辣、生冷、油腻食物。\n" +
                "2.不宜在服药期间同时服用滋补性中药。\n" +
                "3.风寒感冒者不适用。\n" +
                "4.有高血压、心脏病、肝病、糖尿病、肾病等慢性病严重者应在医师指导下服用。\n" +
                "5.脾胃虚寒者慎用。儿童、孕妇、哺乳期妇女、年老体弱者应在医师指导下服用。\n" +
                "6.扁桃体有化脓或发热体温超过38.5℃的患者应去医院就诊。\n" +
                "7.服药3天症状无缓解，应去医院就诊。\n" +
                "8.对本品过敏者禁用，过敏体质者慎用。\n" +
                "9.本品性状发生改变时禁止使用。\n" +
                "10.儿童必须在成人监护下使用。\n" +
                "11.请将本品放在儿童不能接触的地方。\n" +
                "12.如正在使用其他药品，使用本品前请咨询医师或药师。\n" +
                "请仔细阅读说明书并遵医嘱使用");                 //注意事项
        drugsInfo.setPackages("铝塑包装，3×12片/板/盒。");                    //包装
        drugsInfo.setStorage("密封");                     //储藏
        drugsInfo.setOthers("陕西康惠制药股份有限公司");                      //其他
        db.AddDrugsInfo(drugsInfo);

        drugsInfo = new DrugsInfo();      // ID
        drugsInfo.setDrugname("维C银翘片");       //药品名称
        drugsInfo.setDrugPrice("5");             //药品价格
        drugsInfo.setDescription("本品为薄膜衣片，除去包衣后显灰褐色层与白色层；气微，味微苦。 ");                 //性状
        drugsInfo.setActions(" 1.与其他解热镇痛药并用，有增加肾毒性的危险。\n" +
                " 2.如与其他药物同时使用可能会发生药物相互作用，详情请咨询医师或药师。 ");                     //药理作用
        drugsInfo.setIndications("疏风解表，清热解毒。用于外感风热所致的流行性感冒，症见发热、头痛、咳嗽、口干、咽喉疼痛。 ");                 //适应症
        drugsInfo.setContraindications("严重肝肾功能不全者禁用。");           //禁忌症
        drugsInfo.setDosageandadministration(" 口服，一次2片，一日3次。 ");     //用量用法
        drugsInfo.setAdversereactions(" 见困倦、嗜睡、口渴、虚弱感；偶见皮疹、荨麻疹、药热及粒细胞减少；长期大量用药会导致肝肾功能异常。 ");            //不良反应
        drugsInfo.setPrecautions(" 1.忌烟、酒及辛辣、生冷、油腻食物。 \n" +
                "2.不宜在服药期间同时服用滋补性中成药。 \n" +
                "3.不适用于风寒感冒，表现为恶寒明显，无汗，头痛身酸，鼻塞流清涕。 \n" +
                "4.本品含马来酸氯苯那敏、对乙酰氨基酚、维生素C。服用本品期间不得饮酒或含有酒精的饮料；不能同时服用与本品成份相似的其他抗感冒药；\n" +
                "肝、肾功能不全者慎用；膀胱颈梗阻、甲状腺功能亢进、青光眼、高血压和前列腺肥大者慎用；孕妇及哺乳期妇女慎用；服药期间不得驾驶机、车、船、从事高空作业、机械作业及操作精密仪器。 \n" +
                "5.心脏病、糖尿病等慢性病严重者应在医师指导下服用。 \n" +
                "6.儿童、年老体弱者应在医师指导下服用。 \n" +
                "7.服药3天后症状无改善，或症状加重，或出现新的严重症状如胸闷、心悸等应立即停药，并去医院就诊。 \n" +
                "8.对本品过敏者禁用，过敏体质者慎用。 \n" +
                "9.本品性状发生改变时禁止使用。\n" +
                "10.儿童必须在成人监护下使用。 \n" +
                "11.将本品放在儿童不能接触的地方。 \n" +
                "12.如正在使用其他药品，使用本品前请咨询医师或药师。");                 //注意事项
        drugsInfo.setPackages(" 铝塑包装；12片/板×2板/盒。 ");                    //包装
        drugsInfo.setStorage(" 遮光，密封。");                     //储藏
        drugsInfo.setOthers("贵州百灵企业集团制药股份有限公司");                      //其他
        db.AddDrugsInfo(drugsInfo);

        drugsInfo = new DrugsInfo();      // ID
        drugsInfo.setDrugname("立效 参苓白术散");       //药品名称
        drugsInfo.setDrugPrice("11.8");             //药品价格
        drugsInfo.setDescription("本品为黄色至灰黄色的粉末；气香，味甜。  ");                 //性状
        drugsInfo.setActions("如与其他药物同时使用可能会发生药物相互作用，详情请咨询医师或药师。");                     //药理作用
        drugsInfo.setIndications("补脾胃，益肺气。用于脾胃虚弱，食少便溏，气短咳嗽，肢倦乏力。 ");                 //适应症
        drugsInfo.setContraindications("忌不易消化食物");           //禁忌症
        drugsInfo.setDosageandadministration(" 口服，一次6-9克(2-3袋)，一日2-3次。 ");     //用量用法
        drugsInfo.setAdversereactions("  尚不明确");            //不良反应
        drugsInfo.setPrecautions(" 1.忌不易消化食物。 \n" +
                "2.感冒发热病人不宜服用。 \n" +
                "3.有高血压、心脏病、肝病、糖尿病、肾病等慢性病严重者应在医师指导下服用。 \n" +
                "4.儿童、孕妇、哺乳期妇女应在医师指导下服用。 \n" +
                "5.服药4周症状无缓解，应去医院就诊。 \n" +
                "6.对本品过敏者禁用，过敏体质者慎用。 \n" +
                "7.本品性状发生改变时禁止使用。 \n" +
                "8.儿童必须在成人监护下使用。 \n" +
                "9.请将本品放在儿童不能接触的地方。 \n" +
                "10.如正在使用其他药品，使用本品前请咨询医师或药师。 ");                 //注意事项
        drugsInfo.setPackages("6g*6袋/盒。");                    //包装
        drugsInfo.setStorage("密闭，防潮。 ");                     //储藏
        drugsInfo.setOthers("山西华康药业股份有限公司");                      //其他
        db.AddDrugsInfo(drugsInfo);

        drugsInfo = new DrugsInfo();      // ID
        drugsInfo.setDrugname("盐酸左氧氟沙星胶囊");       //药品名称
        drugsInfo.setDrugPrice("15");             //药品价格
        drugsInfo.setDescription("本品为胶囊剂，内容物为类白色或淡黄色粉末或粉末。 ");                 //性状
        drugsInfo.setActions("如与其他药物同时使用可能会发生药物相互作用，详情请咨询医师或药师。");                     //药理作用
        drugsInfo.setIndications(" 1.呼吸系统感染：急性支气管炎、慢性支气管炎急性发作、弥漫性细支气管炎、支气管扩张合并感染、肺炎、扁桃体炎（扁桃体周围脓肿）；\n" +
                " 2.泌尿系统感染：肾盂肾炎、复杂性尿路感染等；\n" +
                " 3.生殖系统感染：急性前列腺炎、急性副睾炎、宫腔感染、子宫附件炎、盆腔炎（疑有厌氧菌感染时可合用甲硝唑）；");                 //适应症
        drugsInfo.setContraindications(" 对喹诺酮类药物过敏者、妊娠及哺乳期妇女、18岁以下患者禁用。 ");           //禁忌症
        drugsInfo.setDosageandadministration(" 口服，一次6-9克(2-3袋)，一日2-3次。 ");     //用量用法
        drugsInfo.setAdversereactions("  尚不明确");            //不良反应
        drugsInfo.setPrecautions(" 1．肾功能不全者应减量或者延长给药间期，重度肾功能不全者慎用。 \n" +
                "2．有中枢神经系统疾病及癫痫史患者应慎用。 \n" +
                "3．喹诺酮类药物尚可引起少见的光毒性反应（发生率<0.1％）。在接受本品治疗时应避免过度阳光曝晒和人工紫外线。如出现光敏反应或皮肤损伤应停用本品。 \n" +
                "4．若发生过敏，应立即停药，并根据临床具体情况而采取以下药物或方法治疗：肾上腺素及其它抢救措施，包括吸氧、静脉输液、抗组织胺药、皮质类固醇等。");                 //注意事项
        drugsInfo.setPackages("12s/盒");                    //包装
        drugsInfo.setStorage("密封");                     //储藏
        drugsInfo.setOthers("广东逸舒制药股份有限公司");                      //其他
        db.AddDrugsInfo(drugsInfo);


    }

}

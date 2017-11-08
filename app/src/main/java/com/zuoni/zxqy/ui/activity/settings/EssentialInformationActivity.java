package com.zuoni.zxqy.ui.activity.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.zuoni.common.dialog.choice.BottomGetPhotoDialog;
import com.zuoni.common.dialog.picker.DataPickerSingleDialog;
import com.zuoni.common.dialog.picker.callback.OnSingleDataSelectedListener;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppSetting;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.GetCompanyCate;
import com.zuoni.zxqy.bean.gson.GetSetting;
import com.zuoni.zxqy.bean.gson.UploadCompanyLogo;
import com.zuoni.zxqy.bean.model.CompanyInfo;
import com.zuoni.zxqy.bean.model.Industry;
import com.zuoni.zxqy.cache.CacheUtils;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.CompanyAddressActivity;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class EssentialInformationActivity extends BaseTitleActivity implements TakePhoto.TakeResultListener, InvokeListener {
    @BindView(R.id.ivHead)
    ImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.et01)
    EditText et01;
    @BindView(R.id.et02)
    EditText et02;
    @BindView(R.id.et03)
    EditText et03;
    @BindView(R.id.et04)
    EditText et04;
    @BindView(R.id.et05)
    EditText et05;
    @BindView(R.id.et06)
    EditText et06;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.tv07)
    TextView tv07;
    @BindView(R.id.layout07)
    LinearLayout layout07;
    @BindView(R.id.tv08)
    TextView tv08;
    @BindView(R.id.layout08)
    LinearLayout layout08;
    @BindView(R.id.btChange)
    Button btChange;


    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private boolean isChangeHead = false;
    private File headFile;
    private String imagePath = "";
    private Handler mHandler = new Handler();


    private CompanyInfo companyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getTakePhoto().onCreate(savedInstanceState);
        setTitle("基本信息");

        //从缓存中获得基本信息
        companyInfo = CacheUtils.getCompanyInfo(getContext());

        //头像
        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.zx_113)
                .error(R.mipmap.zx_113);
        Glide.with(getContext().getApplicationContext())
                .asBitmap()
                .load(companyInfo.getLogo())
                .apply(requestOptions)
                .into(ivHead);

        tvName.setText(companyInfo.getTitle());

        et01.setText(companyInfo.getName());

        et02.setText(companyInfo.getLawer());

        et03.setText(companyInfo.getPhone());

        et04.setText(companyInfo.getFax());

        et05.setText(companyInfo.getAddress());

        et06.setText(companyInfo.getWeb());

        tv07.setText(companyInfo.getCateName());

        tv08.setText(companyInfo.getType());


        //修改公司地址
        et05.setFocusable(false);

        et05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CompanyAddressActivity.class);
                startActivityForResult(intent, 10086);
            }
        });

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_settings_essential_information;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086 && resultCode == 10087) {
            String text = data.getStringExtra("message");
            if (text != null) {
                et05.setText(text);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    BottomGetPhotoDialog bottomGetPhotoDialog;

    @OnClick({R.id.ivHead, R.id.btChange})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivHead:
                BottomGetPhotoDialog.Builder builder = new BottomGetPhotoDialog.Builder(getContext());
                builder.setGetPhotoOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                        Uri imageUri = Uri.fromFile(file);
                        takePhoto.onPickFromGalleryWithCrop(imageUri, new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create());
                        bottomGetPhotoDialog.dismiss();
                    }
                });
                builder.setTakePhotoOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                        Uri imageUri = Uri.fromFile(file);
                        takePhoto.onPickFromCaptureWithCrop(imageUri, new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create());
                        bottomGetPhotoDialog.dismiss();
                    }
                });
                bottomGetPhotoDialog = builder.create();
                bottomGetPhotoDialog.show();


                break;
            case R.id.btChange:


//                contactName	字符串	M		公司联系人名
//                fax	字符串			传真号码
//                userid	字符串	M		用户名 4-20长度
//                tele	字符串	M		公司联系人电话
//                address	字符串	M		公司地址
//                cateId	字符串	M		公司所属行业
//                type	字符串	M		公司性质 直接中文
//                token	字符串	M
//                web	字符串			公司网站
//                lawer	字符串	M		法人代表
                final String contactName = et01.getText().toString().trim();
                final String fax = et04.getText().toString().trim();
                final String tele = et03.getText().toString().trim();
                final String address = et05.getText().toString().trim();
                final String cateId = tv07.getText().toString().trim();

                final String type = tv08.getText().toString().trim();
                final String web = et06.getText().toString().trim();
                final String lawer = et02.getText().toString().trim();

                if (contactName.equals("")) {
                    showToast("请填写招聘负责人");
                } else {
                    if (tele.equals("")) {
                        showToast("请填联系电话");
                    } else {
                        if (address.equals("")) {
                            showToast("请填写公司地址");
                        } else {
                            if (cateId.equals("")) {
                                showToast("请选择所属行业");
                            } else {
                                if (type.equals("")) {
                                    showToast("请选择公司性质");
                                } else {
                                    showLoading();
                                    int time = 0;
                                    if (isChangeHead && headFile != null) {
                                        uploadCompanyLogo();
                                        time = 2000;
                                    }
                                    mHandler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            updateCompanyInfo(contactName, fax, tele, address, cateId, type, web, lawer);
                                        }
                                    }, time);

                                }
                            }
                        }
                    }
                }


                break;
        }

    }

    private void updateCompanyInfo(String contactName, String fax, String tele, String address, String cateId, String type, String web, String lawer) {

        HttpRequest httpRequest = new HttpRequest(AppUrl.UPDATE_COMPANY_INFO);
        httpRequest.add("contactName", contactName);
        httpRequest.add("fax", fax);
        httpRequest.add("tele", tele);
        httpRequest.add("address", address);

        httpRequest.add("cateId", cateId);
        httpRequest.add("cateName", cateId);
        httpRequest.add("type", type);
        httpRequest.add("web", web);
        httpRequest.add("lawer", lawer);

        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("修改基本信息" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    showToast("修改成功");
                    myFinish();
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("修改基本信息" + exception);
            }
        }, getContext());


    }

    private void uploadCompanyLogo() {

        HttpRequest httpRequest = new HttpRequest(AppUrl.UPLOAD_COMPANY_LOGO);
        httpRequest.add("image", headFile);

        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("上传头像" + response);
                UploadCompanyLogo info = gson.fromJson(response, UploadCompanyLogo.class);
                if (info.getStatus().equals("true")) {
                    imagePath = info.getImg();
//                    CacheUtils
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                LogUtil.i("上传头像" + exception);
            }
        }, getContext());
    }


    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }


    @Override
    public void takeSuccess(TResult result) {

        isChangeHead = true;
        //开启鲁班压缩
        Luban.with(this)
                .load(new File(result.getImages().get(0).getOriginalPath()))   //传人要压缩的图片
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        headFile = file;
                        RequestOptions requestOptions = new RequestOptions()
                                .centerCrop()
                                .placeholder(R.mipmap.zx_113)
                                .error(R.mipmap.zx_113);
                        Glide.with(getContext().getApplicationContext())
                                .asBitmap()
                                .load(file)
                                .apply(requestOptions)
                                .into(ivHead);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();    //启动压缩
    }

    @Override
    public void takeFail(TResult result, String msg) {
        showToast("图片选择失败,请重新选择");
    }

    @Override
    public void takeCancel() {
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @OnClick({R.id.layout07, R.id.layout08})
    public void onViewClicked2(View view) {
        switch (view.getId()) {
            case R.id.layout07:
                getCompanyCate();
                break;
            case R.id.layout08:
                //公司性质
                getUserCompany();
                break;
        }
    }


    private void getUserCompany() {
        //获取企业信息
        final String data = AppSetting.getDictionary("user_company");
        if (data == null) {
            //重新获取字典
            showLoading();
            HttpRequest httpRequest = new HttpRequest(AppUrl.GET_SETTING);//行业
            httpRequest.add("passwd", "");

            CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
                @Override
                public void onSucceed(String response, Gson gson) {
                    closeLoading();
                    LogUtil.i("获取字典" + response);
                    GetSetting info = gson.fromJson(response, GetSetting.class);
                    if (info.getStatus().equals("true")) {
                        AppSetting.mList = new ArrayList<>();
                        AppSetting.mList.clear();
                        AppSetting.mList.addAll(info.getData());
                        createPicker(AppSetting.getDictionary("user_company"));
                    } else {
                        showToast(info.getMessage());
                    }
                }

                @Override
                public void onFailed(Exception exception) {
                    closeLoading();
                    LogUtil.i("获取字典" + exception);
                }
            }, getContext());
        } else {
            createPicker(data);
        }
    }

    //公司性质
    private void createPicker(String data) {
        if (data == null) {
            return;
        }

        String[] aa = data.split(",");
        DataPickerSingleDialog.Builder builder = new DataPickerSingleDialog.Builder(getContext());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < aa.length; i++) {
            list.add(aa[i]);
        }

        builder.setOnDataSelectedListener(new OnSingleDataSelectedListener() {
            @Override
            public void onDataSelected(String itemValue) {
                tv08.setText(itemValue);
            }
        });

        builder.setData(list);
        builder.create().show();
    }

    private List<Industry> industrys;
    private String cateId = "-1";

    private void getCompanyCate() {
        showLoading();
        industrys = new ArrayList<>();
        HttpRequest httpRequest = new HttpRequest(AppUrl.GET_COMPANY_CATE);//行业
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("行业" + response);
                GetCompanyCate info = gson.fromJson(response, GetCompanyCate.class);
                if (info.getStatus().equals("true")) {
                    DataPickerSingleDialog.Builder builder = new DataPickerSingleDialog.Builder(getContext());
                    industrys.clear();
                    industrys.addAll(info.getData());
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < industrys.size(); i++) {
                        list.add(industrys.get(i).getName());
                    }

                    builder.setOnDataSelectedListener(new OnSingleDataSelectedListener() {
                        @Override
                        public void onDataSelected(String itemValue) {
                            tv07.setText(itemValue);
                            for (int i = 0; i < industrys.size(); i++) {
                                if (itemValue.equals(industrys.get(i).getName())) {
                                    cateId = industrys.get(i).getCateId();
                                    return;
                                }
                            }
                        }
                    });

                    builder.setData(list);
                    builder.create().show();

                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("行业" + exception);
            }
        }, getContext());
    }
}

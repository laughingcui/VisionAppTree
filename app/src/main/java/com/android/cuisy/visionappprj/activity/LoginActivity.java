package com.android.cuisy.visionappprj.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.cuisy.visionappprj.R;
import com.android.cuisy.visionappprj.entity.Constants;
import com.android.cuisy.visionappprj.util.HttpUtil;

public class LoginActivity extends Activity implements OnClickListener {

    private static final int REQUEST_ACCOUNT = 1;//请求码，此值只要是唯一即可
    public EditText accountEdit;
    public EditText passwordEdit;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
//    public static String account;//用户名
//    public static String password;//密码

    public static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEdit = (EditText) findViewById(R.id.account_edit_id);
        passwordEdit = (EditText) findViewById(R.id.password_edit_id);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);

        boolean isRemember = pref.getBoolean("remember_password",false);
        if (isRemember) {
            //将账号和密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        View login_btn = findViewById(R.id.login_btn_id);
        View setting_btn = findViewById(R.id.setting_btn_id);
        //读数据库设置用户名和密码

//        accountEdit.setText(Constants.username);
//        passwordEdit.setText(Constants.password);

        login_btn.setOnClickListener(this);
        setting_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.login_btn_id) {
            Constants.username = accountEdit.getText().toString();
            Constants.password = passwordEdit.getText().toString();

            if (Constants.username.equals("")) {
                Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }else {
                if (Constants.password.equals("")) {
                    Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            //登录
                            if (Constants.username.equals("admin") && Constants.password.equals("123456")) {
                                editor = pref.edit();
                                if (rememberPass.isChecked()) {
                                    editor.putBoolean("remember_password", true);
                                    editor.putString("account", Constants.username);
                                    editor.putString("password",Constants.password);
                                }else {
                                    editor.clear();
                                }
                                editor.apply();
                                //String u = "http://" + Constants.SetIp + ":" + Constants.SetPort + "/";
                                Constants.sessionId = HttpUtil.HttpLogIn(Constants.u, "login", new String[]{"name", "password"}, Constants.username, Constants.password);
                                System.out.println("sessionid = " + Constants.sessionId);

                                Intent intent = new Intent(LoginActivity.this, TreeNodeActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(LoginActivity.this, "username or password is invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).start();
                }
            }
        }
        this.finish();

        if (id == R.id.setting_btn_id) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivityForResult(intent, REQUEST_ACCOUNT);//用此方法启动活动，在活动销毁时，会将结果返回给上一个活动
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("mIp = " + Constants.SetIp);
        System.out.println("mPort = " + Constants.SetPort);
    }
}

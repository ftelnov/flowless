package fragments;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sirius.rs.R;
import com.example.sirius.rs.RegisterBody;
import com.example.sirius.rs.RetrofitRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    public Map<ImageButton, Integer> map = new HashMap<ImageButton, Integer>();
    public Map<ImageButton, Boolean> visited = new HashMap<ImageButton, Boolean>();
    public ImageButton imageButton;
    private String token;
    private Boolean flag_login = false;
    private Boolean flag_password = false;
    private Boolean flag_password2 = false;
    private Boolean flag_email = false;
    ValueAnimator animator;
    Pattern pattern = Pattern.compile(
            "[" +
                    "a-zA-Z" +
                    "\\d" +
                    "]" +
                    "*");
    Pattern emailpattern = Pattern.compile(
            "[" +
                    "a-zA-Z@." +
                    "\\d" +
                    "]" +
                    "*");

    private String user_name;
    private String user_sername;


    public void onResume() {
        super.onResume();
        if (imageButton == null) return;
        for (ImageButton imageButton : visited.keySet()) {
            visited.put(imageButton, false);
        }
        visited.put(imageButton, true);
        for (ImageButton imageButton : map.keySet()) {
            imageButton.setImageResource(map.get(imageButton));
        }
        imageButton.setImageResource(R.drawable.profile_light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences mySharedPreferences = getActivity().getSharedPreferences("users_settings", Context.MODE_PRIVATE);
        Boolean auth_flag = false;
        if(mySharedPreferences.contains("users_settings")){
            auth_flag = mySharedPreferences.getBoolean("auth", false);
        }
        if(auth_flag){
            String login = mySharedPreferences.getString("login", "flex");
            ProfileShowFragment profileShowFragment = ProfileShowFragment.newInstance(login);
            getFragmentManager().beginTransaction().replace(R.id.container, profileShowFragment).addToBackStack(null).commit();
            return view;
        }
        //Поля ввода
        final EditText loginReg = (EditText) view.findViewById(R.id.loginReg);
        final EditText passwordReg = (EditText) view.findViewById(R.id.passwordReg);
        final EditText passwordReg2 = (EditText) view.findViewById(R.id.passwordReg2);
        final EditText emailReg = (EditText) view.findViewById(R.id.e_mailReg);
        //

        //Модал изображения
        final ImageView imageLogin = (ImageView) view.findViewById(R.id.accept_login);
        final ImageView imagePassword = (ImageView) view.findViewById(R.id.accept_password);
        final ImageView imagePassword_2 = (ImageView) view.findViewById(R.id.accept_password2);
        final ImageView imageEmail = (ImageView) view.findViewById(R.id.accept_email);
        final AuthFragment authFragment = new AuthFragment();
        //
        Button button = (Button) view.findViewById(R.id.register);
        Button button1 = (Button) view.findViewById(R.id.auth);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container, authFragment).addToBackStack(null).commit();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkLog = checkLogin(loginReg.getText().toString());
                Boolean checkPass = checkPassword(passwordReg.getText().toString());
                Boolean checkPass2 = checkContPassword(passwordReg.getText().toString(), passwordReg2.getText().toString());
                Boolean checkEmail = checkEmail(emailReg.getText().toString());
                if (!(checkEmail && checkLog && checkPass && checkPass2)) {
                    Toast.makeText(getActivity(), "Что-то пошло не так. Проверьте правильность введенных данных!", Toast.LENGTH_SHORT).show();
                } else {
                    RegisterBody registerBody = new RegisterBody();
                    registerBody.login = loginReg.getText().toString();
                    registerBody.mail = emailReg.getText().toString();
                    registerBody.password = passwordReg.getText().toString();
                    RetrofitRequest.getRegisterApi().getATruth(registerBody).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.code() == 201) {
                                Toast.makeText(getActivity(), "Вы успешно зарегистрированы! Переадресация на авторизацию...", Toast.LENGTH_SHORT).show();
                                getFragmentManager().beginTransaction().replace(R.id.container, authFragment).addToBackStack(null).commit();
                            } else if(response.code() == 418){
                                Toast.makeText(getActivity(), "Проверьте почту и логин, пользователь с таким логином и/или почтой существует!", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        loginReg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final  float endValue = 360f;
                animator = ValueAnimator.ofFloat(0, endValue);
                animator.setDuration(500L);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        imageLogin.setRotation(value);
                    }
                });
                if (checkLogin(s.toString())) {
                    if (!flag_login) {
                        animator.start();
                        flag_login = true;
                    }
                    imageLogin.setImageResource(R.drawable.accept_true);
                } else {
                    if (flag_login) {
                        animator.start();
                        flag_login = false;
                    }
                    imageLogin.setImageResource(R.drawable.accept_false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordReg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final  float endValue = 360f;
                animator = ValueAnimator.ofFloat(0, endValue);
                animator.setDuration(500L);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        imagePassword.setRotation(value);
                    }
                });
                if (checkPassword(s.toString())) {
                    if(!flag_password){
                        animator.start();
                        flag_password = true;
                    }
                    imagePassword.setImageResource(R.drawable.accept_true);
                } else {
                    if(flag_password){
                        animator.start();
                        flag_password = false;
                    }
                    imagePassword.setImageResource(R.drawable.accept_false);
                }
                animator = ValueAnimator.ofFloat(0, endValue);
                animator.setDuration(500L);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        imagePassword_2.setRotation(value);
                    }
                });
                if (checkContPassword(passwordReg.getText().toString(), passwordReg2.getText().toString())) {
                    if(!flag_password2){
                        animator.start();
                        flag_password2 = true;
                    }
                    imagePassword_2.setImageResource(R.drawable.accept_true);
                } else {
                    if(flag_password2){
                        animator.start();
                        flag_password2 = false;
                    }
                    imagePassword_2.setImageResource(R.drawable.accept_false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordReg2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final  float endValue = 360f;
                animator = ValueAnimator.ofFloat(0, endValue);
                animator.setDuration(500L);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        imagePassword_2.setRotation(value);
                    }
                });
                if (checkContPassword(s.toString(), passwordReg.getText().toString())) {
                    if(!flag_password2){
                        animator.start();
                        flag_password2 = true;
                    }
                    imagePassword_2.setImageResource(R.drawable.accept_true);
                } else {
                    if(flag_password2){
                        animator.start();
                        flag_password2 = false;
                    }
                    imagePassword_2.setImageResource(R.drawable.accept_false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        emailReg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final  float endValue = 360f;
                animator = ValueAnimator.ofFloat(0, endValue);
                animator.setDuration(500L);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        imageEmail.setRotation(value);
                    }
                });
                if (checkEmail(s.toString())) {
                    if(!flag_email){
                        flag_email = true;
                        animator.start();
                    }
                    imageEmail.setImageResource(R.drawable.accept_true);
                } else {
                    if(flag_email){
                        flag_email = false;
                        animator.start();
                    }
                    imageEmail.setImageResource(R.drawable.accept_false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    private Boolean checkLogin(String login) {
        Matcher match = pattern.matcher(login);
        return match.matches() && !login.isEmpty();
    }

    private Boolean checkPassword(String password) {
        Matcher match = pattern.matcher(password);
        Boolean lenght = password.length() >= 8;
        return match.matches() && lenght;
    }

    private Boolean checkEmail(String email) {
        Matcher match = emailpattern.matcher(email);
        Boolean lenght = email.contains("@") && email.contains(".");
        return match.matches() && lenght;
    }

    private Boolean checkContPassword(String password, String password_2) {
        return password.equals(password_2) && !password.isEmpty();
    }
}

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
import android.widget.ImageView;
import android.widget.Toast;

import ResponseBodies.AuthBody;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthFragment extends Fragment {
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
    private Boolean flag_login = false;
    private Boolean flag_password = false;
    private ValueAnimator animator;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        Button button = (Button) view.findViewById(R.id.auth);
        final EditText login = (EditText) view.findViewById(R.id.loginInput);
        final EditText password = (EditText) view.findViewById(R.id.passwordInput);
        final ImageView imageLogin = (ImageView) view.findViewById(R.id.logininput_accept);
        final ImageView imagePassword = (ImageView) view.findViewById(R.id.accept_passwordInput);
        context = getActivity();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AuthBody authBody = new AuthBody();
                authBody.login = login.getText().toString();
                authBody.password = password.getText().toString();
                RetrofitRequest.getApi().letsAuth(authBody).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code() == 200){
                            Toast.makeText(getActivity(), "Авторизация успешна!", Toast.LENGTH_SHORT).show();
                            SharedPreferences mySharedPreferences = context.getSharedPreferences("user_settings", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = mySharedPreferences.edit();
                            editor.putBoolean("auth", true);
                            editor.putString("login", authBody.login);
                            editor.apply();
                            ProfileShowFragment profileShowFragment = ProfileShowFragment.newInstance(authBody.login);
                            getFragmentManager().beginTransaction().replace(R.id.container, profileShowFragment).addToBackStack(null).commit();
                        }
                        else if(response.code() == 418){
                            Toast.makeText(getActivity(), "Неправильный пароль или логин!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        login.addTextChangedListener(new TextWatcher() {
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
        password.addTextChangedListener(new TextWatcher() {
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
}

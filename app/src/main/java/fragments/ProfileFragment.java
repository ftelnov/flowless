package fragments;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sirius.rs.GetModelCategory;
import com.example.sirius.rs.GetModelRegister;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    public Map<ImageButton, Integer> map = new HashMap<ImageButton, Integer>();
    public Map<ImageButton, Boolean> visited = new HashMap<ImageButton, Boolean>();
    public ImageButton imageButton;
    private String token;
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
    private WebView webView;


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
        //
        Button button = (Button) view.findViewById(R.id.register);
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
                    RetrofitRequest.getRegisterApi().getATruth(loginReg.getText().toString(), passwordReg.getText().toString(), emailReg.getText().toString()).enqueue(new Callback<GetModelRegister>() {
                        @Override
                        public void onResponse(Call<GetModelRegister> call, Response<GetModelRegister> response) {
                            if (Boolean.parseBoolean(response.body().access)) {
                                Toast.makeText(getActivity(), "Вы успешно зарегистрированы!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Проверьте почту и логин, такой пользователь уже есть в базе данных!", Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<GetModelRegister> call, Throwable t) {
                            Toast.makeText(getActivity(), "Нет подключения к серверу. Проверьте подключение к сети!", Toast.LENGTH_SHORT);
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
                if (checkLogin(s.toString())) {
                    imageLogin.setImageResource(R.drawable.accept_true);
                } else {
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
                if (checkPassword(s.toString())) {
                    imagePassword.setImageResource(R.drawable.accept_true);
                } else {
                    imagePassword.setImageResource(R.drawable.accept_false);
                }
                if (checkContPassword(passwordReg.getText().toString(), passwordReg2.getText().toString())) {
                    imagePassword_2.setImageResource(R.drawable.accept_true);
                } else {
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
                if (checkContPassword(s.toString(), passwordReg.getText().toString())) {
                    imagePassword_2.setImageResource(R.drawable.accept_true);
                } else {
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
                if (checkEmail(s.toString())) {
                    imageEmail.setImageResource(R.drawable.accept_true);
                } else {
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
        return match.matches();
    }

    private Boolean checkPassword(String password) {
        Matcher match = pattern.matcher(password);
        Boolean lenght = password.length() > 8;
        return match.matches() && lenght;
    }

    private Boolean checkEmail(String email) {
        Matcher match = emailpattern.matcher(email);
        Boolean lenght = email.contains("@") && email.contains(".");
        return match.matches() && lenght;
    }

    private Boolean checkContPassword(String password, String password_2) {
        return password.equals(password_2);
    }
}

package fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sirius.rs.AuthBody;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        Button button = (Button) view.findViewById(R.id.auth);
        final EditText login = (EditText) view.findViewById(R.id.loginInput);
        final EditText password = (EditText) view.findViewById(R.id.passwordInput);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthBody authBody = new AuthBody();
                authBody.login = login.getText().toString();
                authBody.password = password.getText().toString();
                RetrofitRequest.getAuthApi().getATruth(authBody).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code() == 200){
                            Toast.makeText(getActivity(), "Авторизация успешна!", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code() == 418){
                            Toast.makeText(getActivity(), "Неправильный пароль или логин!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        return view;
    }

}

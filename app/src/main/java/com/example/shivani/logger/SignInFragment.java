package com.example.shivani.logger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class SignInFragment extends Fragment implements View.OnClickListener {
    TextView forgotPass, titleEmail, titlePassword;
    Button signIn, fbLogin;
    EditText editEmail, editPassword;
    CallbackManager callbackManager;
    Editable editEmailText;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("Success", "Login");
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.v("LoginActivity", response.toString());

                                        try {
                                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                                                JSONArray array = new JSONArray(response);
                                                for (int i = 0; i < array.length(); i++) {
                                                    JSONObject json_obj = array.getJSONObject(i);
                                                    String email = json_obj.getString("email");

                                                }
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "email");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getContext(), "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sign_in, container, false);

        signIn = (Button) rootView.findViewById(R.id.signInBtn);
        fbLogin = (Button) rootView.findViewById(R.id.facebookLogin);
        forgotPass = (TextView) rootView.findViewById(R.id.forgotPass);
        titleEmail = (TextView) rootView.findViewById(R.id.title1);
        titlePassword = (TextView) rootView.findViewById(R.id.title2);
        editEmail = (EditText) rootView.findViewById(R.id.editEmail);
        editPassword = (EditText) rootView.findViewById(R.id.editPassword);

        fbLogin.setOnClickListener(this);
        signIn.setOnClickListener(this);
        
        editEmailText = editEmail.getText();
        
       final float editPassSize = editPassword.getTextSize();
        Editable textPassword=editPassword.getText();
        TextWatcher inputTextWatcherEmail = new TextWatcher() {
            public void afterTextChanged(Editable s) {
                boolean emailValid = isValidEmail(editEmailText);
                if (emailValid) {
                    titleEmail.setVisibility(View.GONE);
                } else {
                    titleEmail.setVisibility(View.VISIBLE);
                    titleEmail.setText("Enter valid email address*");
                    titleEmail.setTextColor(ContextCompat.getColor(getActivity(), R.color.red));
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean emailValid = isValidEmail(editEmailText);
                if (emailValid) {
                    titleEmail.setVisibility(View.GONE);
                    //   editEmail.setTextColor(Color.GREEN);

                } else {
                    titleEmail.setVisibility(View.VISIBLE);
                    titleEmail.setText("Enter valid email address");
                    titleEmail.setTextColor(ContextCompat.getColor(getActivity(), R.color.red));
                }
            }
        };

        editEmail.addTextChangedListener(inputTextWatcherEmail);
       
        TextWatcher inputTextWatcherPassword = new TextWatcher() {
            public void afterTextChanged(Editable s) {
                titlePassword.setVisibility(View.VISIBLE);
                if (s.length() == 0){
                    titlePassword.setText("Enter password*");
                    titlePassword.setTextColor(ContextCompat.getColor(getActivity(), R.color.red));  }
                else if (s.length() < 6){
                    titlePassword.setText("Weak");
                    titlePassword.setTextColor(ContextCompat.getColor(getActivity(), R.color.orange));}
                else if (s.length() < 10){
                    titlePassword.setText("Good");
                    titlePassword.setTextColor(ContextCompat.getColor(getActivity(), R.color.green));}
                else if (s.length() < 15){
                    titlePassword.setText("Strong");
                    titlePassword.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue));}
                else if(s.length()>=15 && s.length()<20){
                    titlePassword.setText("Very Strong");
                    titlePassword.setTextColor(ContextCompat.getColor(getActivity(), R.color.violet));}
                else if (s.length() == 20){
                    titlePassword.setText("Password Max Length Reached");
                    titlePassword.setTextColor(ContextCompat.getColor(getActivity(), R.color.red));}
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
                if (s.length() == 0){
                    titlePassword.setText("Not Entered");
                    titlePassword.setTextColor(Color.RED);  }
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        };

        editPassword.addTextChangedListener(inputTextWatcherPassword);
        
        editEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    boolean emailValid = isValidEmail(editEmailText);
                    if (emailValid) {
                        titleEmail.setVisibility(View.GONE);
                     //   editEmail.setTextColor(Color.GREEN);

                    } else {
                        titleEmail.setVisibility(View.VISIBLE);
                        titleEmail.setText("Enter valid email address**");
                        titleEmail.setTextColor(ContextCompat.getColor(getActivity(), R.color.red));
                    }
                }
            }
        });
        editPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    titlePassword.setVisibility(View.VISIBLE);
                    if (editPassSize == 0){
                        titlePassword.setText("Enter password*");
                        titlePassword.setTextColor(ContextCompat.getColor(getActivity(), R.color.red));  }
                } else {
                    
                   titlePassword.setVisibility(View.GONE);
                }
            }
        });


        // forgotPass.setPaintFlags(forgotPass.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        // Inflate the layout for this fragment
        return rootView;
       
    }

    


    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signInBtn:
                startActivity(new Intent(getActivity(), ProductListActivity.class));
                break;
            case R.id.facebookLogin:
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
                break;
          /*  case R.id.newUser:
                Intent intent=new Intent(getContext(),SignUpActivity.class);
                startActivity(intent);
                break;*/
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        String email = data.getStringExtra("fields");
        Toast.makeText(getContext(), email, Toast.LENGTH_LONG).show();
        editEmail.setText(email);

    }
}
 
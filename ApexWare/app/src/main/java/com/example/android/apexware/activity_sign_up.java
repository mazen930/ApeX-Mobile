package com.example.android.apexware;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

public class activity_sign_up extends AppCompatActivity {
  Button login;
  Button create_acc;
  ToggleButton toggle_btn;
  EditText pass_et;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);

    login = (Button) findViewById(R.id.login_instead_btn);

    create_acc = (Button) findViewById(R.id.signup_btn);

    toggle_btn = (ToggleButton) findViewById(R.id.toggle_pass_btn);

    pass_et = (EditText) findViewById(R.id.password_text_input);

    login.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            openActivity_login();
          }
        });
  }
  /*
   * opens the activity login on pressing the button log in instead
   */
  public void openActivity_login() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

  public void onToggleClick(View v) {
    if (toggle_btn.isChecked()) {
      pass_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
      Drawable img = null;
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        img = getDrawable(R.drawable.toggle_on);
      }
      toggle_btn.setBackground(img);
    } else {
      pass_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
      Drawable img = null;
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        img = getDrawable(R.drawable.toggle_off);
      }
      toggle_btn.setBackground(img);
    }
  }
}
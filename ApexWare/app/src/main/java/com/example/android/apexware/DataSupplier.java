package com.example.android.apexware;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * the interface for every function used by the service (mock up or backend) and they override these
 * methods in their classes
 *
 * @author mostafa
 */
public interface DataSupplier {

  /**
   * @param username
   * @param password
   * @return : return true if login successful and user exists
   */
  boolean login_interface(String username, String password, final Context context);
  /**
   * send string of all details to create a new user
   *
   * @param username
   * @param email
   * @param password
   * @return : return true if it is succesful
   */
  boolean signup_interface(String username, String email, String password);

  /**
   * send post details to the server to add it to a certain community
   *
   * @param type : post type (link,text,image)
   * @param title :post title (necessary)
   * @param mainPost : main post (link , lines of text , image uri)
   * @param communityID : id of apexcom chosen to post to
   */
  boolean createPost_interface(String type, String title, String mainPost, int communityID);

  /**
   * used in cases of forgetting password to send verify mail to the user
   * @param email user email
   * @param username user name
   */
  void verify_forget_pass_interface(String email, String username, final Context context);

  /**
   * used in cases of forgetting username to send verify mail to the user
   * @param email user email
   * @param password the password user knows
   */
  void verify_forget_user_interface(String email, String password, final Context context);

  /**
   *
   * @param email email of user
   * @param code code he entered and sent to be checked
   * @return user name of the user if correct
   */
  String confirmCode_interface(String email, String code,final Context context);
}

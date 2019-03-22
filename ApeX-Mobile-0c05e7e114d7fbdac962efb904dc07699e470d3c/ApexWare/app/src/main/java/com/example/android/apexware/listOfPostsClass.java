package com.example.android.apexware;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class listOfPostsClass extends AppCompatActivity {
  ListView list;
  CustomAdapterForHomePage adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      /**
       * this class to create post and put them in list and represent  them in the proper  list view
       */
    super.onCreate(savedInstanceState);
    setContentView(R.layout.listofposts);
    //create list view object to handele given array adapter
      list = (ListView) findViewById(R.id.postsListView);
      //create array adapter list for the view
      ArrayList<Post> postArrayList = new ArrayList();
//just dummy data to test the functionality
      /*
      to do :
      1-remove all public statment from post attributes and deal only with setters abd getters
      2-remove testpost view and test with real data
      * */
      Post testpost = new Post();
      testpost.postType = 0;
      testpost.postOwner = "Mazen";
      testpost.postCreateDate = 19;
      testpost.ApexcomLogo = "https://i.imgur.com/S7USWRb.jpg";
      testpost.apexcomName = "AndroidTeam";
      testpost.postTitle = "Test this pot";
      testpost.textPostcontent="Hello its plaesure to meet you here please fell as hoemand leave wuefhiwoeufhwieufhweiufhief  fhewiuf eiufh ief ufhieuhf iuehf uihefiu h feufh iuehf  fiue  eiufhei h efiueh iuh feiufh eiuhf uehf iuhiufheiufheiufh  ehfiuefheiufhiuhefiufehiue  efihoeoIUFEHWEIFUHIF IUHIU HIU";
      // uo&down vote and comment ";
      //testpost.ImageURL = "https://i.imgur.com/S7USWRb.jpg";
      // testpost.videoURL="https://www.youtube.com/watch?v=mWRsgZuwf_8&list=RDL3wKzyIN1yk&index=23";
      postArrayList.add(testpost);
        Post testpost1=new Post();
        testpost1.postType = 1;
        testpost1.postOwner = "Mazen";
        testpost1.postCreateDate = 19;
        testpost1.ApexcomLogo = "https://i.imgur.com/S7USWRb.jpg";
        testpost1.apexcomName = "AndroidTeam";
        testpost1.postTitle = "Test this pot";
        testpost1.ImageURL = "https://i.imgur.com/S7USWRb.jpg";
        postArrayList.add(testpost1);
        Post testpost2=new Post();
        testpost2.postType = 2;
        testpost2.postOwner = "Mazen";
        testpost2.postCreateDate = 19;
        testpost2.ApexcomLogo = "https://i.imgur.com/S7USWRb.jpg";
        testpost2.apexcomName = "AndroidTeam";
        testpost2.postTitle = "Test this pot";
        testpost2.ImageURL = "https://i.imgur.com/S7USWRb.jpg";
        testpost2.videoURL =testpost2.getVideoId("https://www.youtube.com/watch?v=mWRsgZuwf_8");
        postArrayList.add(testpost2);
        postArrayList.add(testpost);
        postArrayList.add(testpost1);
        postArrayList.add(testpost2);
        postArrayList.add(testpost);
         // to do creat instances of posts
      adapter = new CustomAdapterForHomePage(this, postArrayList);
      list.setAdapter(adapter);


    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



        Intent intent=new Intent(listOfPostsClass.this,postsandcomments.class);
        Object current=parent.getItemAtPosition(position);
        Post p1=(Post)current;
        Gson gson = new Gson();
        String postAsString = gson.toJson(p1);
        intent.putExtra("postToDisplay",postAsString);//sending the post to next activity
        startActivity(intent);
      }
    });
  }




  public void upvote(View v) {

    TextView counter =findViewById(R.id.votecounter);
    int i=Integer.parseInt(counter.getText().toString()) ;
    Button up = findViewById(R.id.upvote);
    Button down =findViewById(R.id.downvote);
    if(down.getCurrentTextColor()==Color.RED)
    {
      down.setTextColor(Color.GRAY);
    }
    up.setTextColor(Color.BLUE);
    i++;
    counter.setText(Integer.toString(i));
  }

  public void downvote(View v) {
    TextView counter =findViewById(R.id.votecounter);
    int i=Integer.parseInt(counter.getText().toString()) ;
    Button down = findViewById(R.id.downvote);
    Button up =findViewById(R.id.upvote);
    if(up.getCurrentTextColor()== Color.BLUE)
    {
      up.setTextColor(Color.GRAY);
    }
    down.setTextColor(Color.RED);
    i--;
    counter.setText(Integer.toString(i));

  }




}

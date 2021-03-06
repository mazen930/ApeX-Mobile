package com.example.android.apexware;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
/** This class to make adapter for list in list view */
public class CustomAdapterForMessages extends ArrayAdapter {
  private final Activity context;
  List<Messages> messagesList = new ArrayList<>();
  /**
   * this is constructor for the this class it the setup each item with predefined xml layout
   *
   * @param context Activity context
   * @param list Array list posts that we want to represent it in a list
   */
  public CustomAdapterForMessages(Activity context, ArrayList<Messages> list) {
    super(context, R.layout.homepgaelistview, list);
    this.context = context;
    messagesList = list;
  }

  public View getView(final int position, View view, ViewGroup parent) {
    View listItem = view;
    if (listItem == null) {
      listItem = LayoutInflater.from(context).inflate(R.layout.message_listview, parent, false);
    }
    final Messages currentMessages = messagesList.get(position);
    // set image logo
    ImageView messageLogo = (ImageView) listItem.findViewById(R.id.emailLogo);
    if (currentMessages.isRead() == 1) {
      messageLogo.setImageResource(R.drawable.ic_email_read_gray);
    } else if (currentMessages.isRead() == 0) {

      messageLogo.setImageResource(R.drawable.ic_email);
    } else {
      messageLogo.setImageResource(R.drawable.ic_email_sent);
    }
    // set mail subject
    TextView messageSubject = (TextView) listItem.findViewById(R.id.messageSubject);
    messageSubject.setText(currentMessages.getSubject());
    // set mail sender
    TextView messageContent = (TextView) listItem.findViewById(R.id.sampleMessage);
    messageContent.setText(currentMessages.getContent());
    // set sender name and creation date
    TextView messageSenderandCreationDate =
        (TextView) listItem.findViewById(R.id.sender_creationdate);
    String temp = currentMessages.getSender() + "." + currentMessages.getCreatedAgo();
    messageSenderandCreationDate.setText(temp);

    return listItem;
  }
}

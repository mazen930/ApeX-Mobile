package com.example.android.apexware;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/** This fragment that hold comments by user N.B : Come in next version */
public class CommentFragment extends Fragment {
  View view;

  public CommentFragment() {}

  @Nullable
  @Override
  /** Constructor to handle comments */
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.comment_fragment, container, false);
    return view;
  }
}

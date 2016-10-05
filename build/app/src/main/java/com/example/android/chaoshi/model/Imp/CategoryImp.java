package com.example.android.chaoshi.model.Imp;

import com.example.android.chaoshi.entity.RightCategory;
import com.example.android.chaoshi.model.callback.BmobCallback;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Android on 2016/9/6.
 */
public class CategoryImp {

    BmobQuery query;

    public void queryFirstData(final BmobCallback mbmobCallback) {
              query = new BmobQuery();
              mbmobCallback.LoadStart(query);
              query.findObjects(new FindListener<RightCategory>() {
                  @Override
                  public void done(List list, BmobException e) {
                      if (e == null) {
                          mbmobCallback.LoadComplete(list);
                      } else {
                          mbmobCallback.LoadError(e);
                      }
                  }
              });
      }
    }




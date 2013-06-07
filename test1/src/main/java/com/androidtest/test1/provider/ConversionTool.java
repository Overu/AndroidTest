/*
 * Copyright 2012 Retechcorp.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.androidtest.test1.provider;

import com.google.inject.Singleton;

import com.androidtest.test1.R;
import com.androidtest.test1.base.ConversionCase;
import java.util.ArrayList;

import android.content.Context;

import android.R.anim;

import android.widget.ArrayAdapter;

@Singleton
public class ConversionTool {

  public enum Mapping {
    EXTENT(0, R.array.a0), AREA(1, R.array.a1), VOLUME(2, R.array.a2), QUALITY(3, R.array.a3), TEMPERATURE(4, R.array.a4), PRESSURE(5,
        R.array.a5);

    public static int getIndex(int position) {
      for (Mapping mapping : values()) {
        if (position == mapping.getPosition()) {
          return mapping.getIndex();
        }
      }
      return R.array.types;
    }

    private int position;
    private int index;

    Mapping(int position, int index) {
      this.index = index;
      this.position = position;
    }

    public int getIndex() {
      return index;
    }

    public int getPosition() {
      return position;
    }

    public void setIndex(int index) {
      this.index = index;
    }

    public void setPosition(int position) {
      this.position = position;
    }
  }

  private static ArrayList<ConversionCase> cases;

  public ArrayAdapter<CharSequence> getArrayAdapterByType(Context context, int position) {
    ArrayAdapter<CharSequence> aa =
        ArrayAdapter.createFromResource(context, Mapping.getIndex(position), android.R.layout.simple_spinner_item);
    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    return aa;
  }

}

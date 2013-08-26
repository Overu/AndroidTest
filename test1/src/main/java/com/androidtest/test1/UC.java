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
package com.androidtest.test1;

import com.androidtest.test1.provider.ConversionTool;
import javax.inject.Inject;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

@ContentView(R.layout.main)
public class UC extends RoboActivity {

  private int position = 0;

  @InjectView(R.id.units)
  EditText etUnits;
  @InjectView(R.id.output)
  TextView outputView;
  @InjectView(R.id.conversions1)
  Spinner spnConversions1;
  @InjectView(R.id.conversions2)
  Spinner spnConversions2;
  @InjectView(R.id.conversions3)
  Spinner spnConversions3;
  @InjectView(R.id.clear)
  Button btnClear;
  @InjectView(R.id.convert)
  Button btnConvert;
  @InjectView(R.id.close)
  Button btnClose;

  @Inject
  ConversionTool tool;

  private double[] multipliers = {
      0.0015625, 101325.0, 100000.0, 0, 0, 0.00001, 0.3048, 0.0284130625, 0.029573295625, 746.0, 735.499, 1 / 1016.0469088, 1 / 907.18474,
      1 / 907.18474, 1 / 0.0284130625, 1 / 0.0295735295625, 331.5, 1 / 0.3048, 1 / 331.5, 0.833, 1 / 0.833, 10000.0, 1 / 101325.0, 0.00001,
      640.0, 1016.0469088, 907.18474, 1 / 746.0, 1 / 735.499 };

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ArrayAdapter<CharSequence> aa = tool.getArrayAdapterByType(this, -1);
    spnConversions1.setAdapter(aa);

    spnConversions1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        UC.this.position = position;
        ArrayAdapter<CharSequence> aa = tool.getArrayAdapterByType(UC.this, position);
        spnConversions2.setAdapter(aa);
        spnConversions3.setAdapter(aa);
        spnConversions3.setSelection(1);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        System.out.println("nothing");
      }

    });

    btnClear.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        etUnits.setText("");
      }
    });
    btnClear.setEnabled(false);

    btnConvert.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        String text = etUnits.getText().toString();
        double input = Double.parseDouble(text);
        double result = 0;
        if (position == 3) {

        } else if (position == 4) {

        } else {
          input *= multipliers[position];
        }
        etUnits.setText("" + input);
      }
    });
    btnConvert.setEnabled(false);

    btnClose.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(UC.this, MessageActivity.class);
        // startActivity(intent);
        startActivityForResult(intent, 1);
        // finish();
      }
    });

    TextWatcher tw = new TextWatcher() {

      @Override
      public void afterTextChanged(Editable s) {

      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (etUnits.getText().length() == 0) {
          btnClear.setEnabled(false);
          btnConvert.setEnabled(false);
        } else {
          btnClear.setEnabled(true);
          btnConvert.setEnabled(true);
        }
      }
    };

    etUnits.addTextChangedListener(tw);
  }
}

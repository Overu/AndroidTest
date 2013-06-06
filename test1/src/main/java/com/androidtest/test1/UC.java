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

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.app.Activity;

@ContentView(R.layout.main)
public class UC extends RoboActivity {

  private int position = 0;
  
  @InjectView(R.id.units)
  EditText etUnits;
  @InjectView(R.id.conversions)
  Spinner spnConversions;
  @InjectView(R.id.clear)
  Button btnClear;
  @InjectView(R.id.convert)
  Button btnConvert;
  

  private double[] multipliers = {
      0.0015625, 101325.0, 100000.0, 0, 0, 0.00001, 0.3048, 0.0284130625, 0.029573295625, 746.0, 735.499, 1 / 1016.0469088, 1 / 907.18474,
      1 / 907.18474, 1 / 0.0284130625, 1 / 0.0295735295625, 331.5, 1 / 0.3048, 1 / 331.5, 0.833, 1 / 0.833, 10000.0, 1 / 101325.0, 0.00001,
      640.0, 1016.0469088, 907.18474, 1 / 746.0, 1 / 735.499 };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.array.conversions, android.R.layout.simple_spinner_item);
    
    aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
    spnConversions.setAdapter(aa);
    
    spnConversions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			UC.this.position = position;
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
			double imput = Double.parseDouble(text);
			double result = 0;
		}
	});
  }

}

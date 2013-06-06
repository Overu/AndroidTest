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

import android.os.Bundle;

import android.app.Activity;

public class UC extends Activity {

  private int position = 0;

  private double[] multipliers = {
      0.0015625, 101325.0, 100000.0, 0, 0, 0.00001, 0.3048, 0.0284130625, 0.029573295625, 746.0, 735.499, 1 / 1016.0469088, 1 / 907.18474,
      1 / 907.18474, 1 / 0.0284130625, 1 / 0.0295735295625, 331.5, 1 / 0.3048, 1 / 331.5, 0.833, 1 / 0.833, 10000.0, 1 / 101325.0, 0.00001,
      640.0, 1016.0469088, 907.18474, 1 / 746.0, 1 / 735.499 };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

}

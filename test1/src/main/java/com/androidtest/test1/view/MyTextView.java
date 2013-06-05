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
package com.androidtest.test1.view;

import com.androidtest.test1.R;

import android.graphics.RectF;

import android.text.Layout;

import android.graphics.drawable.Drawable;

import android.graphics.Paint;

import android.graphics.Canvas;

import android.content.Context;
import android.util.AttributeSet;

import android.widget.TextView;

public class MyTextView extends TextView {
  private static final float CORNER_RADIUS = 14.0f;
  private static final float PADDING_H = 10.0f;
  private static final float PADDING_V = 5.0f;

  private boolean mBackgroundSizeChanged;
  private float mCornerRadius;
  private float mPaddingH;
  private float mPaddingV;

  private RectF mrectf = new RectF();

  private Paint mpaint;

  public MyTextView(Context context) {
    super(context);
  }

  public MyTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public MyTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  @Override
  public void draw(Canvas canvas) {

    Layout layout = getLayout();

    RectF rectf = mrectf;

    int left = getCompoundPaddingLeft();
    int top = getExtendedPaddingTop();

    rectf.set(left + layout.getLineLeft(0) - mPaddingH, top + layout.getLineTop(0) - mPaddingV, Math.min(left + layout.getLineRight(0),
        getScrollX() + getWidth() - getCompoundPaddingRight()), top + layout.getLineBottom(0) + mPaddingV);
    canvas.drawRoundRect(rectf, mCornerRadius, mCornerRadius, mpaint);
    super.draw(canvas);
  }

  private void init() {
    mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mpaint.setColor(getContext().getResources().getColor(R.color.translucent_dark));

    float scale = getContext().getResources().getDisplayMetrics().density;

    mCornerRadius = CORNER_RADIUS * scale;
    mPaddingH = PADDING_H * scale;
    mPaddingV = PADDING_V * scale;
  }
}

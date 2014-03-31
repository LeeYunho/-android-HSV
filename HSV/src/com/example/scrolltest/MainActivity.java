package com.example.scrolltest;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	
	private LinearLayout ll;
	private HorizontalScrollView hsv;
	private ImageButton btnLeft, btnRight;
	private int maxScrollX;
	private int COUNT = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ll = (LinearLayout) findViewById(R.id.ll);
		hsv = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
		btnLeft = (ImageButton) findViewById(R.id.btnScrollLeft);
		btnRight = (ImageButton) findViewById(R.id.btnScrollRight);		
		
		ViewTreeObserver vto = hsv.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				// TODO Auto-generated method stub
				hsv.getViewTreeObserver().removeGlobalOnLayoutListener(this); 
		        maxScrollX = hsv.getChildAt(0) 
		                .getMeasuredWidth()-getWindowManager().getDefaultDisplay().getWidth();
		        if(maxScrollX <= 0)
		        	btnRight.setVisibility(View.INVISIBLE);
			}
			
		});
		
		hsv.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(hsv.getScrollX() == maxScrollX){
		        	btnRight.setVisibility(View.INVISIBLE);
		        }
		        else if(hsv.getScrollX() == 0) {
		        	btnLeft.setVisibility(View.INVISIBLE);
		        }
		        else {
		        	btnLeft.setVisibility(View.VISIBLE);
		        	btnRight.setVisibility(View.VISIBLE);
		        }
				return false;
			}
			
		});
		
		btnLeft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hsv.smoothScrollTo(ll.getChildAt(0).getLeft(), ll.getChildAt(0).getTop());
				btnLeft.setVisibility(View.INVISIBLE);
				btnRight.setVisibility(View.VISIBLE);
			}
		});
		btnRight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hsv.smoothScrollTo(ll.getChildAt(COUNT-1).getLeft(), ll.getChildAt(0).getTop());
				btnRight.setVisibility(View.INVISIBLE);
				btnLeft.setVisibility(View.VISIBLE);
			}
		});
		
		ImageView [] iv = new ImageView[COUNT];
		for(int i=0; i<COUNT; i++)
		{
			iv[i] = new ImageView(getApplicationContext());
			iv[i].setTag(i);
			iv[i].setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
			ll.addView(iv[i]);
		}
	}

}

/*******************************************************************************
 * Copyright (c) 2010 Denis Solonenko.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Denis Solonenko - initial API and implementation
 ******************************************************************************/
package com.ect.tmenurestaurant.view;

import com.ect.tmenurestaurant.R;

import android.os.Vibrator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class InputAmountView implements OnClickListener {
	
	private static final int[] buttons = { R.id.b0, R.id.b1, R.id.b2, R.id.b3,
		R.id.b4, R.id.b5, R.id.b6, R.id.b7, R.id.b8, R.id.b9, R.id.bHelp,
		R.id.bClear};

	public static interface PinListener {
		void onConfirm(int result);
	}

	private final PinListener listener;
	private final View v;	
	private final ViewSwitcher switcher;
    private final Vibrator vibrator;
    private boolean hasBeenInput;
	
	private TextView result;
	
	public InputAmountView(Context context, PinListener listener, int layoutId) {
		this.hasBeenInput = false;
		this.listener = listener;
		LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = layoutInflater.inflate(layoutId, null);
		for (int id : buttons) {
			((Button)v.findViewById(id)).setOnClickListener(this);
		}
		result = (TextView)v.findViewById(R.id.result1);
		result.setText("1");
		switcher = (ViewSwitcher)v.findViewById(R.id.switcher);  
		switcher.setInAnimation(inFromRightAnimation());
		switcher.setOutAnimation(outToLeftAnimation());
        this.vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	public void setResult(int amount)
	{
		result.setText(String.valueOf(amount));
	}
	
	public View getView() {
		return v;
	}
	
	@Override
	public void onClick(View v) {
		Button b = (Button)v;
		char c = b.getText().charAt(0);
        if (vibrator != null) {
            vibrator.vibrate(20);
        }
		switch (c) {
		case 'O':
			nextStep();
			break;
		case 'C':
			result.setText("0");
			break;
		default:
			if (hasBeenInput)
			{
	            String text = result.getText().toString();
	            if (text.length() == 1 && text.charAt(0) == '0')
	            {
	    			result.setText(String.valueOf(c));
	            }
	            else
	            {
	    			result.setText(text+String.valueOf(c));
	            }
			}
			else {
				hasBeenInput = true;
				result.setText(String.valueOf(c));
			}
			break;
		}
	}

	private void nextStep() {
		listener.onConfirm(Integer.valueOf(result.getText().toString()));
	}

	private Animation inFromRightAnimation() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(300);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	private Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(300);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}

}

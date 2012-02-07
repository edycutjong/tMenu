package com.ect.tmenurestaurant.activity;

import com.ect.tmenurestaurant.Menu;
import com.ect.tmenurestaurant.R;
import com.ect.tmenurestaurant.view.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class InputAmountActivity extends Activity implements InputAmountView.PinListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		InputAmountView  v = new InputAmountView(this, this, R.layout.input_amount);
		setContentView(v.getView());

		int amount = getIntent().getExtras().getInt(Menu.INTENT_DATA_INPUT_AMOUNT);
		v.setResult(amount);
	}

	@Override
	public void onConfirm(int amount) {

    	Intent intent = new Intent();
    	int position = getIntent().getExtras().getInt(Menu.INTENT_DATA_INPUT_POSITION);
    	intent.putExtra(Menu.INTENT_DATA_INPUT_POSITION, position);
    	intent.putExtra(Menu.INTENT_DATA_INPUT_AMOUNT, amount);
        setResult(RESULT_OK, intent);
        finish();
	}
}

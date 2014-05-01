package com.kernicky.gl_prototype;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;



public class ControlsDialogFragment extends DialogFragment {
	//private final RadioButton moveButton, shootButton, boostButton, bombButton;
	private RadioGroup moveRadioGroup, shootRadioGroup, boostRadioGroup, bombRadioGroup;
	private int moveSelected;
	private int shootSelected;
	private int boostSelected;
	private int bombSelected;
   private View view;
	
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        

        view = inflater.inflate(R.layout.controls_layout, null);
        
        builder.setView(view)
        // Add action buttons
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                       // sign in the user ...
                   }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                   }
               });  
        
        moveRadioGroup = (RadioGroup) view.findViewById(R.id.move);
        shootRadioGroup = (RadioGroup) view.findViewById(R.id.shoot); 
        boostRadioGroup = (RadioGroup) view.findViewById(R.id.boost); 
        bombRadioGroup = (RadioGroup) view.findViewById(R.id.bomb); 
        
        moveSelected = moveRadioGroup.getCheckedRadioButtonId();
        shootSelected = moveRadioGroup.getCheckedRadioButtonId();
        boostSelected = moveRadioGroup.getCheckedRadioButtonId();
        bombSelected = moveRadioGroup.getCheckedRadioButtonId();
        
        moveRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				moveSelected = moveRadioGroup.getCheckedRadioButtonId();
				switch(moveSelected) {
				case R.id.move_touch_left:
					if(shootSelected == R.id.shoot_touch_left) {
						//Toast.makeText(MainActivity.context, "Haha", Toast.LENGTH_SHORT).show();
						shootRadioGroup.check(R.id.shoot_touch_right);
						MainActivity.shootInput = MainActivity.Input.TOUCH_RIGHT;

					}
					MainActivity.moveInput = MainActivity.Input.TOUCH_LEFT;

					break;
				case R.id.move_touch_right:
					if(shootSelected == R.id.shoot_touch_right) {
						//Toast.makeText(MainActivity.context, "Haha", Toast.LENGTH_SHORT).show();

						shootRadioGroup.check(R.id.shoot_touch_left);
						MainActivity.shootInput = MainActivity.Input.TOUCH_LEFT;
					}
					MainActivity.moveInput = MainActivity.Input.TOUCH_RIGHT;
					moveRadioGroup.check(R.id.move_touch_right);
					break;
				case R.id.move_tilt:
					MainActivity.moveInput = MainActivity.Input.TILT;
					break;
				}
			}
        });
        shootRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				shootSelected = shootRadioGroup.getCheckedRadioButtonId();
		        moveRadioGroup = (RadioGroup) view.findViewById(R.id.move);
		        moveSelected = moveRadioGroup.getCheckedRadioButtonId();
				//Toast.makeText(MainActivity.context, shootSelected, Toast.LENGTH_SHORT);
				switch(shootSelected) {
				case R.id.shoot_touch_left:
					if(moveSelected == R.id.move_touch_left) {
						//moveRadioGroup.check(R.id.move_touch_right);
						MainActivity.moveInput = MainActivity.Input.TOUCH_RIGHT;
					}
					MainActivity.shootInput = MainActivity.Input.TOUCH_LEFT;
					break;
				case R.id.shoot_touch_right:
					if(moveSelected == R.id.move_touch_right) {
						//moveRadioGroup.check(R.id.move_touch_left);
						MainActivity.moveInput = MainActivity.Input.TOUCH_LEFT;
					}
					MainActivity.shootInput = MainActivity.Input.TOUCH_RIGHT;
					break;
				}
			}
        });
//        boostRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(RadioGroup group, int checkedId) {
//				boostSelected = shootRadioGroup.getCheckedRadioButtonId();
//
//				switch(boostSelected) {
//				case R.id.boost_touch_left:
//					if(bombSelected == R.id.bomb_touch_left) {
//						bombRadioGroup.check(R.id.bomb_touch_right);
//						MainActivity.bombInput = MainActivity.Input.TOUCH_LEFT;
//					}
//					MainActivity.boostInput = MainActivity.Input.TOUCH_LEFT;
//					break;
//				case R.id.boost_touch_right:
//					if(bombSelected == R.id.bomb_touch_right) {
//						bombRadioGroup.check(R.id.bomb_touch_left);
//						MainActivity.bombInput = MainActivity.Input.TOUCH_LEFT;
//					}
//					MainActivity.boostInput = MainActivity.Input.TOUCH_RIGHT;
//					break;
//				case R.id.boost_tilt:
//					if(bombSelected == R.id.bomb_tilt) {
//						bombRadioGroup.check(R.id.bomb_touch_right);
//						MainActivity.bombInput = MainActivity.Input.TOUCH_RIGHT;
//					}
//					MainActivity.boostInput = MainActivity.Input.TILT;
//					break;
//				case R.id.boost_button:
//				
//					break;
//				}
//			}
//        });
//        bombRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(RadioGroup group, int checkedId) {
//				bombSelected = shootRadioGroup.getCheckedRadioButtonId();
//
//				switch(bombSelected) {
//				case R.id.bomb_touch_left:
//					if(boostSelected == R.id.boost_touch_left) {
//						boostRadioGroup.check(R.id.boost_touch_right);
//						MainActivity.boostInput = MainActivity.Input.TOUCH_RIGHT;
//					}
//					MainActivity.bombInput = MainActivity.Input.TOUCH_LEFT;
//					break;
//				case R.id.bomb_touch_right:
//					if(boostSelected == R.id.boost_touch_right) {
//						boostRadioGroup.check(R.id.boost_touch_left);
//						MainActivity.boostInput = MainActivity.Input.TOUCH_LEFT;
//					}
//					MainActivity.bombInput = MainActivity.Input.TOUCH_RIGHT;
//					break;
//				case R.id.bomb_tilt:
//					if(boostSelected == R.id.boost_tilt) {
//						boostRadioGroup.check(R.id.boost_touch_left);
//						MainActivity.boostInput = MainActivity.Input.TOUCH_LEFT;
//					}
//					MainActivity.bombInput = MainActivity.Input.TILT;
//					break;
//				case R.id.bomb_button:
//					break;
//				}
//			}
//        });
        
        return builder.create();
    }
    
    
}
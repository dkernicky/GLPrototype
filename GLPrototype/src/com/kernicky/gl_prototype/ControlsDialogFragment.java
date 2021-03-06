package com.kernicky.gl_prototype;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.kernicky.gl_prototype.models.Transformation;



public class ControlsDialogFragment extends DialogFragment {
	//private final RadioButton moveButton, shootButton, boostButton, bombButton;
	private RadioGroup moveRadioGroup, shootRadioGroup, boostRadioGroup, bombRadioGroup;
	private int moveSelected;
	private int shootSelected;
	private int boostSelected;
	private int bombSelected;
	private boolean changeInProgress = false;
   private View view;
	


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
      
        view = inflater.inflate(R.layout.controls_layout, null);
        
        builder.setView(view);
        // Add action buttons
//               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                   @Override
//                   public void onClick(DialogInterface dialog, int id) {
//                       // sign in the user ...
//                   }
//               })
//               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                   public void onClick(DialogInterface dialog, int id) {
//                   }
//               });  
        
        moveRadioGroup = (RadioGroup) view.findViewById(R.id.move);
        shootRadioGroup = (RadioGroup) view.findViewById(R.id.shoot); 
        boostRadioGroup = (RadioGroup) view.findViewById(R.id.boost); 
        bombRadioGroup = (RadioGroup) view.findViewById(R.id.bomb); 
        
        switch(MainActivity.moveInput) {
        case TOUCH_LEFT:
        	moveRadioGroup.check(R.id.move_touch_left);
        	break;
        case TOUCH_RIGHT:
        	moveRadioGroup.check(R.id.move_touch_right);
        	break;
        case TILT:
        	moveRadioGroup.check(R.id.move_tilt);
        	break; 	
        }
        switch(MainActivity.shootInput) {
        case TOUCH_LEFT:
        	shootRadioGroup.check(R.id.shoot_touch_left);
        	break;
        case TOUCH_RIGHT:
        	shootRadioGroup.check(R.id.shoot_touch_right);
        	break;
	
        }
        switch(MainActivity.boostInput) {
        case TOUCH_LEFT:
        	boostRadioGroup.check(R.id.boost_touch_left);
        	break;
        case TOUCH_RIGHT:
        	boostRadioGroup.check(R.id.boost_touch_right);
        	break;
        case TILT:
        	boostRadioGroup.check(R.id.boost_tilt);
        	break; 	
        }
        switch(MainActivity.bombInput) {
        case TOUCH_LEFT:
        	bombRadioGroup.check(R.id.bomb_touch_left);
        	break;
        case TOUCH_RIGHT:
        	bombRadioGroup.check(R.id.bomb_touch_right);
        	break;
        case TILT:
        	bombRadioGroup.check(R.id.bomb_tilt);
        	break; 	
        }
        
        
        moveSelected = moveRadioGroup.getCheckedRadioButtonId();
        shootSelected = shootRadioGroup.getCheckedRadioButtonId();
        boostSelected = boostRadioGroup.getCheckedRadioButtonId();
        bombSelected = bombRadioGroup.getCheckedRadioButtonId();
        
        moveRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				moveSelected = moveRadioGroup.getCheckedRadioButtonId();
				switch(moveSelected) {
				case R.id.move_touch_left:
					if(shootSelected == R.id.shoot_touch_left  && changeInProgress == false) {
						changeInProgress = true;
						shootRadioGroup.check(R.id.shoot_touch_right);
						//MainActivity.shootInput = MainActivity.Input.TOUCH_RIGHT;

						changeInProgress = false;

					}
					ShootEmUpScene.pad.transList.set(1, new Transformation(-5+.75f, 0, 7));

					moveSelected = moveRadioGroup.getCheckedRadioButtonId();
					MainActivity.moveInput = MainActivity.Input.TOUCH_LEFT;


					break;
				case R.id.move_touch_right:
					if(shootSelected == R.id.shoot_touch_right && changeInProgress == false) {
						changeInProgress = true;
						shootRadioGroup.check(R.id.shoot_touch_left);
						//MainActivity.shootInput = MainActivity.Input.TOUCH_LEFT;

						changeInProgress = false;
					}
					ShootEmUpScene.pad.transList.set(1, new Transformation(5-.75f, 0, 7));

					moveSelected = moveRadioGroup.getCheckedRadioButtonId();
					MainActivity.moveInput = MainActivity.Input.TOUCH_RIGHT;
					break;
				case R.id.move_tilt:
					moveSelected = moveRadioGroup.getCheckedRadioButtonId();
					MainActivity.moveInput = MainActivity.Input.TILT;
					System.out.println("Shoot to right touch");

					break;
				}
			}
        });
        shootRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				shootSelected = shootRadioGroup.getCheckedRadioButtonId();
				//View v = shootRadioGroup.findViewById(R.id.move);
				
		        //moveRadioGroup = (RadioGroup) view.findViewById(R.id.move);
		        //moveSelected = moveRadioGroup.getCheckedRadioButtonId();

				switch(shootSelected) {
				case R.id.shoot_touch_left:
					if(moveSelected == R.id.move_touch_left  && changeInProgress == false) {
						changeInProgress = true;
						moveRadioGroup.check(R.id.move_touch_right);
						changeInProgress = false;

						//MainActivity.moveInput = MainActivity.Input.TOUCH_RIGHT;
					}
					ShootEmUpScene.shoot.transList.set(1, new Transformation(-5+.75f, 0, 7));

					shootSelected = shootRadioGroup.getCheckedRadioButtonId();
					MainActivity.shootInput = MainActivity.Input.TOUCH_LEFT;
					break;
				case R.id.shoot_touch_right:
					if(moveSelected == R.id.move_touch_right  && changeInProgress == false) {
						changeInProgress = true;
						moveRadioGroup.check(R.id.move_touch_left);
						changeInProgress = false;
						//MainActivity.moveInput = MainActivity.Input.TOUCH_LEFT;
					}
					ShootEmUpScene.shoot.transList.set(1, new Transformation(5-.75f, 0, 7));

					shootSelected = shootRadioGroup.getCheckedRadioButtonId();

					MainActivity.shootInput = MainActivity.Input.TOUCH_RIGHT;
					break;
				}
			}
        });
        boostRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				boostSelected = boostRadioGroup.getCheckedRadioButtonId();

				switch(boostSelected) {
				case R.id.boost_touch_left:
					if(bombSelected == R.id.bomb_touch_left  && changeInProgress == false) {
						changeInProgress = true;
						bombRadioGroup.check(R.id.bomb_touch_right);
						changeInProgress = false;

						//MainActivity.bombInput = MainActivity.Input.TOUCH_LEFT;
					}
					boostSelected = boostRadioGroup.getCheckedRadioButtonId();

					MainActivity.boostInput = MainActivity.Input.TOUCH_LEFT;
					break;
				case R.id.boost_touch_right:
					if(bombSelected == R.id.bomb_touch_right  && changeInProgress == false) {
						changeInProgress = true;

						bombRadioGroup.check(R.id.bomb_touch_left);
						changeInProgress = false;

						//MainActivity.bombInput = MainActivity.Input.TOUCH_LEFT;
					}
					boostSelected = boostRadioGroup.getCheckedRadioButtonId();

					MainActivity.boostInput = MainActivity.Input.TOUCH_RIGHT;
					break;
				case R.id.boost_tilt:
					if(bombSelected == R.id.bomb_tilt  && changeInProgress == false) {
						changeInProgress = true;

						bombRadioGroup.check(R.id.bomb_touch_right);
						changeInProgress = false;

						//MainActivity.bombInput = MainActivity.Input.TOUCH_RIGHT;
					}
					boostSelected = boostRadioGroup.getCheckedRadioButtonId();

					MainActivity.boostInput = MainActivity.Input.TILT;
					break;

				}
			}
        });
        bombRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				bombSelected = bombRadioGroup.getCheckedRadioButtonId();

				switch(bombSelected) {
				case R.id.bomb_touch_left:
					if(boostSelected == R.id.boost_touch_left  && changeInProgress == false) {
						changeInProgress = true;

						boostRadioGroup.check(R.id.boost_touch_right);
						changeInProgress = false;

						//MainActivity.boostInput = MainActivity.Input.TOUCH_RIGHT;
					}
					bombSelected = bombRadioGroup.getCheckedRadioButtonId();

					MainActivity.bombInput = MainActivity.Input.TOUCH_LEFT;
					break;
				case R.id.bomb_touch_right:
					if(boostSelected == R.id.boost_touch_right  && changeInProgress == false) {
						changeInProgress = true;

						boostRadioGroup.check(R.id.boost_touch_left);
						changeInProgress = false;

						//MainActivity.boostInput = MainActivity.Input.TOUCH_LEFT;
					}
					bombSelected = bombRadioGroup.getCheckedRadioButtonId();

					MainActivity.bombInput = MainActivity.Input.TOUCH_RIGHT;
					break;
				case R.id.bomb_tilt:
					if(boostSelected == R.id.boost_tilt  && changeInProgress == false) {
						changeInProgress = true;

						boostRadioGroup.check(R.id.boost_touch_left);
						changeInProgress = false;

						//MainActivity.boostInput = MainActivity.Input.TOUCH_LEFT;
					}
					bombSelected = bombRadioGroup.getCheckedRadioButtonId();

					MainActivity.bombInput = MainActivity.Input.TILT;
					break;

				}
			}
        });
        Dialog dialog = builder.create();
        dialog.setTitle("Edit Controls");
        return dialog;
    }
    
    
}
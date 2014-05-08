package com.kernicky.gl_prototype;

/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.kernicky.gl_prototype.math.MatrixOp;
import com.kernicky.gl_prototype.math.Vector;
import com.kernicky.gl_prototype.models.Transformation;

public class MainActivity extends Activity implements SensorEventListener {

    private GLSurfaceView mGLView;
    public float angle = 0.0f;
    private SensorManager mSensorManager;
    private Sensor mAccel;
    private Sensor mMagnet;
    public static float[] accel = new float[3];
    public static float[] magnet = new float[3];
    public static float[] orientation = new float[3];
    public static float upAccel = 0.0f;
    public static float xOrientation = 0.0f;
    public static float yOrientation = 0.0f;
    public static Context context;
    
    public static enum Input {TILT, TOUCH_LEFT, TOUCH_RIGHT, BUTTON}
    public static Input moveInput = Input.TOUCH_LEFT;
    public static Input shootInput = Input.TOUCH_RIGHT;
    public static Input boostInput = Input.TOUCH_LEFT;
    public static Input bombInput = Input.TILT;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

    	ActionBar ab = getActionBar(); 
    	ab.setDisplayShowTitleEnabled(false); 
    	ab.setDisplayShowHomeEnabled(false);
    	ab.setBackgroundDrawable(null);
    	
    	context = getApplicationContext();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccel = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnet = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mMagnet, SensorManager.SENSOR_DELAY_NORMAL);
        super.onCreate(savedInstanceState);
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
        
       

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main, menu);

    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_settings:
                //openSettings();
            	//android.support.v4.app.FragmentManager fm = getFragmentManager();
            	android.app.FragmentTransaction fm = getFragmentManager().beginTransaction();
                ControlsDialogFragment dialog = new ControlsDialogFragment();
               
                dialog.show(fm, "Controls");
               
                return true;
            case R.id.action_help:
            	AlertDialog.Builder alert = new AlertDialog.Builder(this);
            	String instructions = "Abilities: \n\n" +
            			"	Move: Move the ship about the level \n" +
            			"	Shoot: Shoot projectiles radially from the ship \n" +
            			"	Boost: Increase speed for a limited amount of time \n" +
            			"	Bomb: Send out a shockwave from the ship to destroy enemies \n" +
            			"\n" +
            			"Input Types: \n\n" +
            			"	Touch (consists of finger movement on the screen): \n" +
            			"		   For movement and shooting, this is basic finger movement. \n" +
            			"		   For boosting and bombing, this is sudden finger movement. \n\n" +
            			"	Tilt (consists of physical device movements): \n" +
            			"		   For movement, this is basic basic device tilting. \n" +
            			"	   	 For boosting and bombing, this is sudden device movement. \n";
            	alert.setTitle("Help");
            	// Create TextView
            	final TextView input = new TextView (this);
                //LayoutInflater inflater = getLayoutInflater();
            	//final TextView input = new TextView (this);
            	alert.setMessage(instructions);
            	alert.setView(input);
                //View view = inflater.inflate(R.layout.help_layout, null);
            	//alert.setView(view);
            	alert.show();
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        

    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mGLView.onResume();
    }
    
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		switch(event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			accel = event.values;
			upAccel = event.values[2];
			if(upAccel > 15) {
				if(MainActivity.bombInput == MainActivity.Input.TILT) {
					ShootEmUpScene.ico.setRadialEffect();
					ShootEmUpScene.sp.play(ShootEmUpScene.s2, ShootEmUpScene.volume, ShootEmUpScene.volume, 1, 0, (float) 1.0);
				}
				else if(MainActivity.boostInput == MainActivity.Input.TILT) 
					ShootEmUpScene.ship.updateSpeed();
			}
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			magnet = event.values;
			break;
		}
		float[] mRotationMatrix = new float[9];
		SensorManager.getRotationMatrix(mRotationMatrix, null, accel, magnet);
		SensorManager.getOrientation(mRotationMatrix, orientation);
		orientation = Vector.normalize(new float[]{0, orientation[1], orientation[2]});
//		System.out.println(orientation[0] + " " + orientation[1] + " " + orientation[2]);
	}
}

class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;
    public static float angleLeft = 0.0f;
    public static float dxLeft = 0.0f;
    public static float dyLeft = 0.0f;
    public static double leftMagnitude = 0.0f;
    public static double rightMagnitude = 0.0f;
    public static float angleRight = 0.0f;
    public static float dxRight = 0.0f;
    public static float dyRight = 0.0f;
    public static float[] x_axis = {1.0f, 0.0f, 0.0f, 0.0f};
    public static float[] y_axis = {0.0f, 1.0f, 0.0f, 0.0f};
    static float[] currentTransform = MatrixOp.identity();
	static float[] transform = MatrixOp.identity();
	static float[] staticTransform = MatrixOp.identity();
	
    public static float[] ship_Position = {0.0f, 0.0f, 1.0f, 1.0f};

    public MyGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        
        Matrix.setIdentityM(transformMatrix, 0);
    }

   

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousXRight;
    private float mPreviousYRight;
    private float mPreviousXLeft;
    private float mPreviousYLeft;
    public static boolean leftTouch;
	public static boolean rightTouch;
    private float leftX, leftY, rightX, rightY;
    private float[] transformMatrix = new float[16];
 
    @Override
	public void onPause() {
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent e) {

    	int count = e.getPointerCount();

    	leftTouch = false;
    	rightTouch = false;
    	for(int n = 0; n < count; n ++) {
    		if(e.getX(n) < getWidth()/2) {
    			leftTouch = true;
    			leftX = e.getX(n);
    			leftY = e.getY(n);
    		}
    		else {
    			rightTouch = true;
    			rightX = e.getX(n);
    			rightY = e.getY(n);
    		}
    	}
    	if(!leftTouch) {
    		this.dxLeft = 0;
    		this.dyLeft = 0;
    	}
    	if(!rightTouch) {
    		this.dxRight = 0;
    		this.dyRight = 0;
    	}


        switch (e.getAction()) {
        	case MotionEvent.ACTION_UP:
        		count = e.getPointerCount();

            	leftTouch = false;
            	rightTouch = false;
            	for(int n = 1; n < count; n ++) {
            		if(e.getX(n) < getWidth()/2) {
            			leftTouch = true;

            			leftX = e.getX(n);
            			leftY = e.getY(n);
            			
            		}
            		else {
            			rightTouch = true;
            			rightX = e.getX(n);
            			rightY = e.getY(n);
      
            			
            		}
            	}
            	if(!leftTouch) {
            		this.dxLeft = 0.0f;
            		this.dyLeft = 0.0f;
            	}

        		break;
            case MotionEvent.ACTION_MOVE:
            	if(rightTouch) {
            		
	                double dx = rightX - mPreviousXRight;
	                double dy = rightY - mPreviousYRight;
	                
	                if(MainActivity.shootInput == MainActivity.Input.TOUCH_RIGHT) {
	                	float x = modelLocationX(rightX);
	                	float y = modelLocationY(rightY);
        				ShootEmUpScene.shoot.transList.set(1, new Transformation(x, -1*y, 7));
        			}
        			else if(MainActivity.moveInput == MainActivity.Input.TOUCH_RIGHT) {
	                	float x = modelLocationX(rightX);
	                	float y = modelLocationY(rightY);
        				ShootEmUpScene.pad.transList.set(1, new Transformation(x, -1*y, 7));
        			}
        			

	                if(dx == 0) {
	                	dx = .000001f;
	                }
	                
	                double magnitude = Math.sqrt(dx*dx + dy*dy);
	           
	                
	                dx /= Math.max(.000001, magnitude);
	                dy /= Math.max(.000001, magnitude);
	
	
	                double angle = Math.atan(dy/dx);
	                angle *= (360/(2*Math.PI));
	                angle -= 90.0;
	
	                if((dx <= 0 && dy <= 0) || (dx <= 0 && dy >= 0)) {
	                	angle += 180.0;
	                }
	                
	                if(magnitude > 2) {
	                	rightMagnitude = magnitude;
		                this.angleRight = (float) ((float) -1.0f*angle);
		                
		                this.dxRight = (float) dx;
		                this.dyRight = (float) dy;
	                    mPreviousXRight = rightX;
	                    mPreviousYRight = rightY;
	                }
            	}
            	if(leftTouch) {
            		double dx = leftX - mPreviousXLeft;
            		double dy = leftY - mPreviousYLeft;
            		
            		
            		if(MainActivity.shootInput == MainActivity.Input.TOUCH_LEFT) {
	                	float x = modelLocationX(leftX);
	                	float y = modelLocationY(leftY);
        				ShootEmUpScene.shoot.transList.set(1, new Transformation(x, -1*y, 7));
        			}
        			else if(MainActivity.moveInput == MainActivity.Input.TOUCH_LEFT) {
	                	float x = modelLocationX(leftX);
	                	float y = modelLocationY(leftY);
        				ShootEmUpScene.pad.transList.set(1, new Transformation(x, -1*y, 7));
        			}
            		
            		if(dx == 0) {
            			dx = .000001f;
            		}
	                
	                double magnitude = Math.sqrt(dx*dx + dy*dy);
	                dx /= Math.max(.000001, magnitude);
	                dy /= Math.max(.000001, magnitude);
	
	
	                double angle = Math.atan(dy/dx);
	                angle *= (360/(2*Math.PI));
	                angle += 90.0;
	
	                if((dx <= 0 && dy <= 0) || (dx <= 0 && dy >= 0)) {
	                	angle += 180.0;
	                }
	                if(magnitude > 20) {
		                leftMagnitude = magnitude;
//		    			if(MyGLSurfaceView.leftMagnitude > 100) {
//		    				Toast.makeText(MainActivity.context, Double.toString(MyGLSurfaceView.leftMagnitude), Toast.LENGTH_SHORT).show();
//		    			}
		                this.angleLeft = (float) ((float) -1.0f*angle);
		                
		                this.dxLeft = (float) dx;
		                this.dyLeft = (float) dy;

		                
		                mPreviousXLeft = leftX;
		                mPreviousYLeft = leftY;
	                }
	                else
	                leftMagnitude = 0.0f;

            	}

                requestRender();
        }


        return true;
    }
    
    public float modelLocationX(float screenX) {
    	float t = screenX/getWidth();
    	return -5 + 10*t;
    }
    public float modelLocationY(float screenY) {
    	float t = screenY/getHeight();
    	return -3 + 6*t;
    }

}


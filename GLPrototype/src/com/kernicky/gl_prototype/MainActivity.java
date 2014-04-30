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
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;

import com.kernicky.gl_prototype.math.MatrixOp;

public class MainActivity extends Activity implements SensorEventListener {

    private GLSurfaceView mGLView;
    public float angle = 0.0f;
    private SensorManager mSensorManager;
    private Sensor mAccel;
    public static float upAccel = 0.0f;
    public static Context context;

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
        mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_NORMAL);
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
            	Toast.makeText(this, "Fuck You", Toast.LENGTH_SHORT).show();
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
		upAccel = event.values[2];
		if(upAccel > 15) {
			ShootEmUpScene.ico.setRadialEffect();
		}
		
		

	}
}

class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;
    public static float angleLeft = 0.0f;
    public static float dxLeft = 0.0f;
    public static float dyLeft = 0.0f;
    public static double leftMagnitude = 0.0f;
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
    public boolean leftTouch;
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

}


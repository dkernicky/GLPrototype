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

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;

import com.kernicky.gl_prototype.math.Quaternion;
import com.kernicky.gl_prototype.math.MatrixOp;
import com.kernicky.gl_prototype.math.Vector;

public class MainActivity extends Activity {

    private GLSurfaceView mGLView;
    public float angle = 0.0f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        mGLView.onResume();
    }
}

class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;
    public static float angleLeft = 0.0f;
    public static float dxLeft = 0.0f;
    public static float dyLeft = 0.0f;
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
        //dxLeft = 1.0f;
       // rotate();

    }

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousXRight;
    private float mPreviousYRight;
    private float mPreviousXLeft;
    private float mPreviousYLeft;
    private boolean leftTouch, rightTouch;
    private float leftX, leftY, rightX, rightY;
    private float[] transformMatrix = new float[16];
 

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.
    	//printMatrix();
    	int count = e.getPointerCount();

    	leftTouch = false;
    	rightTouch = false;
    	for(int n = 0; n < count; n ++) {
    		if(e.getX(n) < getWidth()/2) {
    			leftTouch = true;
    	    	//System.out.println("LEFT TRUE");

    			leftX = e.getX(n);
    			leftY = e.getY(n);
    		}
    		else {
    			rightTouch = true;
    	    	//System.out.println("RIGHT TRUE");
    			rightX = e.getX(n);
    			rightY = e.getY(n);
    		}
    	}
    	if(!leftTouch) {
    		this.dxLeft = 0;
    		this.dyLeft = 0;
    		//transform = MatrixOp.identity();
    	}

        //float x = e.getX();
        //float y = e.getY();

//        float[] result = mapToModelSpace(x, y);
//        System.out.println(result[0] + "*****" + result[1]);
        switch (e.getAction()) {
        	case MotionEvent.ACTION_UP:
        		count = e.getPointerCount();
    			//System.out.println(count);

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
        	    	//System.out.println("UP!!!!!!!!!!!!!");
            	}
            	//System.out.println(leftTouch);



        		break;
            case MotionEvent.ACTION_MOVE:
            	if(rightTouch) {
	                double dx = rightX - mPreviousXRight;
	                double dy = rightY - mPreviousYRight;
	                //dx = -1.0f;
	                //dy = 1.0f;
	                if(dx == 0) {
	                	dx = .000001f;
	                }
	                
	                double magnitude = Math.sqrt(dx*dx + dy*dy);
	                //System.out.println(magnitude);
	                
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
		                //mRenderer.mAngle += (dx + dy) * TOUCH_SCALE_FACTOR;  // = 180.0f / 320
		                
		                this.dxRight = (float) dx;
		                this.dyRight = (float) dy;
	                    mPreviousXRight = rightX;
	                    mPreviousYRight = rightY;
	                }
            	}
            	if(leftTouch) {
            		double dx = leftX - mPreviousXLeft;
            		double dy = leftY - mPreviousYLeft;
 	                //dy *= -1.0f;
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
	                	//System.out.println(this.angleLeft == -90.0);
		                this.angleLeft = (float) ((float) -1.0f*angle);
		                //mRenderer.mAngle += (dx + dy) * TOUCH_SCALE_FACTOR;  // = 180.0f / 320
		                
		                this.dxLeft = (float) dx;
		                this.dyLeft = (float) dy;

		                
		                mPreviousXLeft = leftX;
		                mPreviousYLeft = leftY;
		               // rotate();
	                }
	                else{
	                	//rotate();
	                }
	            
            	}
            	//cross();
                requestRender();
        }


        return true;
    }
    
//    public float[] mapToModelSpace(float x, float y) {
//    	float[] result = new float[2];
//    	float width = getWidth();
//    	float height = getHeight();
//    	float ratio = width/height;
//    	result[0] = (x/width)*(2*ratio)-ratio;
//    	result[1] = (y/height)*(-2) + 1;
//    	return result;
//    }
    
    public void rotate() {  
    	float[] inputVector = Vector.normalize(new float[]{1*dxLeft, -1*dyLeft, 0, 0});
    	Quaternion prevPosQ = new Quaternion(ShootEmUpScene.shipQ.toFloat());
    	
    	//float[] transform = new float[16];
		Quaternion q = Quaternion.rotate(.08f*dyLeft, prevPosQ.toFloat(), x_axis);
		Quaternion r = Quaternion.rotate(.08f*dxLeft, prevPosQ.toFloat(), y_axis);
		float[] a = Quaternion.rotateTo(prevPosQ, q);
		float[] b = Quaternion.rotateTo(prevPosQ, r);
		transform = MatrixOp.multiplyMM(a, b);
		float[] shipDir = {0, 1, 0, 0};
		float[] t2 = Quaternion.rotateTo(shipDir, inputVector);
		staticTransform = t2;


		currentTransform = MatrixOp.multiplyMM(transform, currentTransform);
    }

    public void rotate2() {  
		//System.out.println("ActivityThread:" + Thread.currentThread().getId());

    	//dxLeft = -1;
    	//dyLeft = 0;
    	//System.out.println("*****************************");
    	float[] inputVector = Vector.normalize(new float[]{1*dxLeft, -1*dyLeft, 0, 0});
    	Quaternion prevPosQ = new Quaternion(ShootEmUpScene.shipQ.toFloat());
    	
		Quaternion q = Quaternion.rotate(.08f*dyLeft, prevPosQ.toFloat(), x_axis);
		Quaternion r = Quaternion.rotate(.08f*dxLeft, prevPosQ.toFloat(), y_axis);
		float[] a = Quaternion.rotateTo(prevPosQ, q);
		float[] b = Quaternion.rotateTo(prevPosQ, r);
		transform = MatrixOp.multiplyMM(a, b);
		
		ShootEmUpScene.shipQ = new Quaternion(MatrixOp.multiplyMV(transform, ShootEmUpScene.shipQ.toFloat()));
		ShootEmUpScene.viewQ = new Quaternion(MatrixOp.multiplyMV(transform, ShootEmUpScene.viewQ.toFloat()));
		x_axis = MatrixOp.multiplyMV(transform, x_axis);
		y_axis = MatrixOp.multiplyMV(transform, y_axis);
		
		x_axis = Vector.normalize(x_axis);
		y_axis = Vector.normalize(y_axis);		
		ShootEmUpScene.shipQ.normalize();
		//ShootEmUpScene.viewQ.normalize();
		
		float[] shipDir = {0, 1, 0, 0};
		float[] t2 = Quaternion.rotateTo(shipDir, inputVector);



		currentTransform = MatrixOp.multiplyMM(transform, currentTransform);
		ShootEmUpScene.viewQ = new Quaternion(ShootEmUpScene.shipQ.toFloat());
		ShootEmUpScene.viewQ.multiply(3);
		
//		Quaternion test = new Quaternion(ShootEmUpScene.viewQ.toFloat());
//		test.multiply(.33333f);
//		float dx = Math.abs(test.x - ShootEmUpScene.shipQ.x);
//		float dy = Math.abs(test.y - ShootEmUpScene.shipQ.y);
//		float dz = Math.abs(test.z - ShootEmUpScene.shipQ.z);
//		float dw = Math.abs(test.w - ShootEmUpScene.shipQ.w);
//		if(dx > .0001 || dy > .0001 || dz > .0001 || dw > .0001) {
//			test.print();
//			ShootEmUpScene.shipQ.print();
//		}
		//ShootEmUpScene.shipQ.print();
		
//		Quaternion s = new Quaternion(ShootEmUpScene.shipQ.toFloat());
//		s.x -= prevPosQ.x;
//		s.y -= prevPosQ.y;
//		s.z -= prevPosQ.z;
//		s.w -= prevPosQ.w;
//		float mag = (float) s.norm();
//		if(mag > .08) System.out.println(mag);
		
		ShootEmUpScene.staticAngle = t2;
		ShootEmUpScene.shipAngle = currentTransform;
    	//System.out.println("*****************************");

    }

    
}


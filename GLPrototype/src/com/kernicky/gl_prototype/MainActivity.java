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
    float[] currentTransform = MatrixOp.identity();
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
		//System.out.println(count);

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
	                if(true || magnitude > 10) {
	                	//System.out.println(this.angleLeft == -90.0);
		                this.angleLeft = (float) ((float) -1.0f*angle);
		                //mRenderer.mAngle += (dx + dy) * TOUCH_SCALE_FACTOR;  // = 180.0f / 320
		                
		                this.dxLeft = (float) dx;
		                this.dyLeft = (float) dy;

		                
		                mPreviousXLeft = leftX;
		                mPreviousYLeft = leftY;
		                rotate();
	                }
	            
            	}
            	//cross();
                requestRender();
        }


        return true;
    }
    
    public float[] mapToModelSpace(float x, float y) {
    	float[] result = new float[2];
    	float width = getWidth();
    	float height = getHeight();
    	float ratio = width/height;
    	result[0] = (x/width)*(2*ratio)-ratio;
    	result[1] = (y/height)*(-2) + 1;
    	return result;
    }
    
    public void cross() {
    	float x = -1.0f*this.dxLeft;
    	float y = this.dyLeft;
    	this.dxLeft = y;
    	this.dyLeft = (float) (-1.0*x);
    	
    }
    public void printMatrix(float[] m) {
    	System.out.println("****************");
    	System.out.println(m[0] + " "  + m[4] + " " + m[8] + " "  + m[12]);
    	System.out.println(m[1] + " "  + m[5] + " " + m[9] + " "  + m[13]);
    	System.out.println(m[2] + " "  + m[6] + " " + m[10] + " "  + m[14]);
    	System.out.println(m[3] + " "  + m[7] + " " + m[11] + " "  + m[15]);
    	System.out.println("****************");

    	
    }
//    public void rotate4() {
//    	float[] inputVec = {-1*dxLeft, dyLeft, 0, 0};
//		inputVec = Vector.normalize(inputVec);
//		inputVec = MatrixOp.multiplyMV(currentTransform, inputVec);
//		
//		float[] rotAxis = Vector.cross(inputVec, ShootEmUpScene.shipQ.toFloat());
//		MatrixOp.printV(rotAxis);
//
//		Quaternion q = Quaternion.rotate(.08f,ShootEmUpScene.shipQ.toFloat(), rotAxis);
//		float[] transform = Quaternion.rotateTo(ShootEmUpScene.shipQ, q);
//		
//		ShootEmUpScene.shipQ = new Quaternion(MatrixOp.multiplyMV(transform, ShootEmUpScene.shipQ.toFloat()));
//		ShootEmUpScene.viewQ = new Quaternion(MatrixOp.multiplyMV(transform, ShootEmUpScene.viewQ.toFloat()));
//		x_axis = MatrixOp.multiplyMV(transform, x_axis);
//		y_axis = MatrixOp.multiplyMV(transform, y_axis);
//		
//		x_axis = Vector.normalize(x_axis);
//		y_axis = Vector.normalize(y_axis);		
//		ShootEmUpScene.shipQ.normalize();
//		
//		MatrixOp.printV(x_axis);
//		MatrixOp.printV(y_axis);
//
//		//ShootEmUpScene.shipQ.print();
//		MatrixOp.printV(y_axis);
//		currentTransform = MatrixOp.multiplyMM(currentTransform, transform);
//		ShootEmUpScene.shipAngle = currentTransform;
//		System.out.println("**");
//    }
    public void rotate() {  
    	//dxLeft = -1;
    	//dyLeft = 0;
    	System.out.println("*****************************");
    	float[] inputVector = Vector.normalize(new float[]{1*dxLeft, -1*dyLeft, 0, 0});
    	
    	
    	float[] transform = new float[16];
		Quaternion q = Quaternion.rotate(.1f*dyLeft, ShootEmUpScene.shipQ.toFloat(), x_axis);
		Quaternion r = Quaternion.rotate(.1f*dxLeft, ShootEmUpScene.shipQ.toFloat(), y_axis);
		float[] a = Quaternion.rotateTo(ShootEmUpScene.shipQ, q);
		float[] b = Quaternion.rotateTo(ShootEmUpScene.shipQ, r);
		
		transform = MatrixOp.multiplyMM(a, b);
		
		ShootEmUpScene.shipQ = new Quaternion(MatrixOp.multiplyMV(transform, ShootEmUpScene.shipQ.toFloat()));
		ShootEmUpScene.viewQ = new Quaternion(MatrixOp.multiplyMV(transform, ShootEmUpScene.viewQ.toFloat()));
		x_axis = MatrixOp.multiplyMV(transform, x_axis);
		y_axis = MatrixOp.multiplyMV(transform, y_axis);
		
		x_axis = Vector.normalize(x_axis);
		y_axis = Vector.normalize(y_axis);		
		ShootEmUpScene.shipQ.normalize();
		MatrixOp.printV(x_axis);
		MatrixOp.printV(y_axis);
		
		float[] shipDir = {0, 1, 0, 0};
		
		float[] t2 = Quaternion.rotateTo(shipDir, inputVector);
		//currentTransform = MatrixOp.multiplyMM(t2, currentTransform);
		//transform = MatrixOp.multiplyMM(transform, t2);


		currentTransform = MatrixOp.multiplyMM(transform, currentTransform);
		//currentTransform = MatrixOp.multiplyMM(t2, currentTransform);
		float[] xyDir = MatrixOp.multiplyMV(t2, shipDir);


		float[] newDir = MatrixOp.multiplyMV(currentTransform, xyDir);
		
		ShootEmUpScene.staticAngle = t2;
		ShootEmUpScene.shipAngle = currentTransform;
//		float[] firstT = Quaternion.rotateTo(shipDir, inputVector);
//		//shipDir = MatrixOp.multiplyMV(firstT, shipDir);
//		float[] otherT = Quaternion.rotateTo(shipDir, y_axis);
//		float[] thirdT = MatrixOp.multiplyMM(firstT, otherT);
//		ShootEmUpScene.shipAngle = MatrixOp.multiplyMM(transform, thirdT);
    	System.out.println("*****************************");

    }
    
    public void rotate3() {    	
    	System.out.println("*****************************");

//    	ShootEmUpScene.shipQ = new Quaternion(0, 0, 1, 0);
//    	ShootEmUpScene.viewQ = new Quaternion(0, 0, 2, 0);
//    	x_axis = new float[]{1, 0, 0, 0};
//    	y_axis = new float[]{0, 1, 0, 0};
//    	dyLeft = (float) (-1*Math.PI/2);
//    	dxLeft = (float) (-1*Math.PI/2);;

		//float[] shipPrevQ = new float[]{ShootEmUpScene.shipQ.x, ShootEmUpScene.shipQ.y, ShootEmUpScene.shipQ.z, ShootEmUpScene.shipQ.w};
		//System.out.println("From: " + shipPrevQ[0] + " " + shipPrevQ[1] + " " + shipPrevQ[2] + " " + shipPrevQ[3]);
    	
    	//Quaternion x = new Quaternion(x_axis[0], x_axis[1], x_axis[2], 0);
    	//Quaternion y = new Quaternion(y_axis[0], y_axis[1], y_axis[2], 0);
    	

    	//ShootEmUpScene.viewQ.rotate(dyLeft,  x_axis);
    	//ShootEmUpScene.viewQ.rotate(dxLeft,  y_axis);

    	Quaternion q = Quaternion.rotate(.2f*dyLeft, ShootEmUpScene.shipQ.toFloat(), x_axis);
    	//q.print();
    	Quaternion r = Quaternion.rotate(.2f*dxLeft, ShootEmUpScene.shipQ.toFloat(), y_axis);
    	//r.print();
    	q = q.add(r);
    	q.normalize();
    	q.print();
    	//ShootEmUpScene.shipQ.normalize();
    	
    	//y_axis = q.toFloat();
    	//q.rotate(dxLeft,  y_axis);
    	//Quaternion s = q.multiply(r);
    	//q.print();
       	//ShootEmUpScene.shipQ.rotate(dxLeft,  y_axis);   
    	
		//float[] shipNewQ = ShootEmUpScene.shipQ.toFloat();
		//System.out.println("From: " + shipNewQ[0] + " " + shipNewQ[1] + " " + shipNewQ[2] + " " + shipNewQ[3]);
		//shipNewQ = q.toFloat();
		//System.out.println("To: " + shipNewQ[0] + " " + shipNewQ[1] + " " + shipNewQ[2] + " " + shipNewQ[3]);

       	//float[] result = ShootEmUpScene.viewQ.rotateTo(ShootEmUpScene.shipQ.toFloat(), q.toFloat());
       	float[] result = q.toMatrixCM();
		//printMatrix(result);

       	
       	Matrix.multiplyMV(x_axis, 0, result, 0, x_axis, 0);
       	Matrix.multiplyMV(y_axis, 0, result, 0, y_axis, 0);
       	Matrix.multiplyMV(ShootEmUpScene.shipPosition, 0, result, 0, ShootEmUpScene.shipPosition, 0);
       	Matrix.multiplyMV(ShootEmUpScene.mViewerPos, 0, result, 0, ShootEmUpScene.mViewerPos, 0);
       	ShootEmUpScene.shipQ = new Quaternion(ShootEmUpScene.shipPosition[0], ShootEmUpScene.shipPosition[1], ShootEmUpScene.shipPosition[2], 0);
       	ShootEmUpScene.viewQ = new Quaternion(ShootEmUpScene.mViewerPos[0], ShootEmUpScene.mViewerPos[1], ShootEmUpScene.mViewerPos[2], 0);
    	//System.out.println(ShootEmUpScene.shipPosition[0] + " " + ShootEmUpScene.shipPosition[1] + " " + ShootEmUpScene.shipPosition[2]);

       	//Matrix.multiplyMV(ShootEmUpScene.shipAngle, 0, result, 0, ShootEmUpScene.shipAngle, 0);
       	//ShootEmUpScene.shipAngle = result;

       	//System.out.println("x: " + x_axis[0] + " " + x_axis[1] + " " + x_axis[2] + " " + x_axis[3]);
       	//System.out.println("y: " + y_axis[0] + " " + y_axis[1] + " " + y_axis[2] + " " + y_axis[3]);
       	
       	ShootEmUpScene.mUpV = y_axis;
       	//ShootEmUpScene.mViewerPos = ShootEmUpScene.viewQ.toFloat();
    	//ShootEmUpScene.shipPosition = new float[]{ShootEmUpScene.shipQ.x, ShootEmUpScene.shipQ.y, ShootEmUpScene.shipQ.z, ShootEmUpScene.shipQ.w};
    	//ShootEmUpScene.shipAngle = newShipAngle;
    	System.out.println("*****************************");


    }
    public void rotate2() {    	
    	float[] newXAxis = new float[4];
		float[] newYAxis = new float[4];
		float[] newShipPosition = new float[4];
		float[] newShipAngle = new float[4];
		float[] newViewerPos = new float[4];
		
//    	Matrix.rotateM(transformMatrix, 0, 90, 1, 0, 0);
//    	printMatrix();
//    	newShipAngle[1] = (float) Math.asin(transformMatrix[8]);
//    	newShipAngle[0] = (float) (-1.0*Math.atan2(transformMatrix[9], transformMatrix[10]));
//    	newShipAngle[2] = (float) (-1.0*Math.atan2(transformMatrix[4], transformMatrix[0]));
//    	System.out.println(newShipAngle[0] + " " + newShipAngle[1] + " " + newShipAngle[2]);
    	//Matrix.rotateM(transformMatrix, 0, 4*dxLeft, y_axis[0], y_axis[1], y_axis[2]);
    	//Matrix.rotateM(transformMatrix, 0, 4*dyLeft, x_axis[0], x_axis[1], x_axis[2]);
    	
    	Matrix.rotateM(transformMatrix, 0, 10, 0, 1, 0);
    	Matrix.rotateM(transformMatrix, 0, 10, 1, 0, 0);

    	Matrix.multiplyMV(newXAxis, 0, transformMatrix, 0, x_axis, 0);
    	Matrix.multiplyMV(newYAxis, 0, transformMatrix, 0, y_axis, 0);
    	Matrix.multiplyMV(newShipPosition, 0, transformMatrix, 0, ShootEmUpScene.shipPosition, 0);
    	//Matrix.multiplyMV(newShipAngle, 0, transformMatrix, 0, ShootEmUpScene.shipAngle, 0);
    	Matrix.multiplyMV(newViewerPos, 0, transformMatrix, 0, ShootEmUpScene.mViewerPos, 0);
    	
//    	double phi = Math.acos(newShipPosition[2]);
//    	double theta = Math.acos(newShipPosition[0]/Math.sin(phi));
//    	float viewMag = (float) Math.sqrt(newViewerPos[0]*newViewerPos[0]+newViewerPos[1]*newViewerPos[1]+newViewerPos[2]*newViewerPos[2]);
//    	newShipAngle[0] = (float) ((float) Math.acos(1.0f*y_axis[0])*(360/(2*Math.PI)));
//    	newShipAngle[1] = (float) ((float) Math.acos(1.0f*y_axis[1])*(360/(2*Math.PI)));
//    	newShipAngle[2] = (float) ((float) Math.acos(1.0f*y_axis[2])*(360/(2*Math.PI)));
    	
    	ShootEmUpScene.shipAngle[1] += (float) (Math.asin(transformMatrix[8])*(180/Math.PI));
    	ShootEmUpScene.shipAngle[0] += (float) ((1.0*Math.atan2(transformMatrix[9], transformMatrix[10]))*(180/Math.PI));
    	ShootEmUpScene.shipAngle[2] += (float) ((1.0*Math.atan2(transformMatrix[4], transformMatrix[0]))*(180/Math.PI));
    	ShootEmUpScene.shipAngle[0] %=360;
    	ShootEmUpScene.shipAngle[1] %=360;
    	ShootEmUpScene.shipAngle[2] %=360;
    	//printMatrix();
    	for(float f: ShootEmUpScene.shipAngle) {
    		System.out.print(f + " ");
    	}

    	System.out.println();

    	float[] posVec = {newShipPosition[0] - ShootEmUpScene.shipPosition[0], newShipPosition[1] - ShootEmUpScene.shipPosition[1], newShipPosition[2] - ShootEmUpScene.shipPosition[2]};
    	float magnitude = (float) Math.sqrt(posVec[0]*posVec[0] + posVec[1]*posVec[1] + posVec[2]*posVec[2]);
    	x_axis = newXAxis;
    	y_axis = newYAxis;
    	ShootEmUpScene.mUpV = y_axis;
    	ShootEmUpScene.shipPosition = newShipPosition;
    	//ShootEmUpScene.shipAngle = newShipAngle;
    	ShootEmUpScene.mViewerPos = newViewerPos;
    	//System.out.println("x: " + x_axis[0] + " " + x_axis[1] + " " + x_axis[2]);
    	Matrix.setIdentityM(transformMatrix, 0);

    }
}


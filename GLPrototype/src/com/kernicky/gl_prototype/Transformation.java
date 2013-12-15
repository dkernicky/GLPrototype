package com.kernicky.gl_prototype;

import android.opengl.Matrix;

public class Transformation {
	
	public enum type  {STATIC, DYNAMIC, TRANSLATION, ROTATION};
	private type t1 = type.STATIC;
	private type t2 = type.ROTATION;
	private float angle = 0.0f;
	private float x = 0.0f;
	private float y = 0.0f;
	private float z = 0.0f;
	private int start = 0;
	private int end = 0;
	
	
	
	public Transformation(float angle, float x, float y, float z) { // Static rotation
		t1 = type.STATIC;
		t2 = type.ROTATION;
		this.angle = angle;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Transformation(float x, float y, float z) { // Static translation
		t1 = type.STATIC;
		t2 = type.TRANSLATION;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Transformation(float angle, float x, float y, float z, int start, int end) { // Dynamic rotation
		t1 = type.DYNAMIC;
		t2 = type.ROTATION;
		this.angle = angle;
		this.x = x;
		this.y = y;
		this.z = z;
		this.start = start;
		this.end = end;
	}
	public Transformation(float x, float y, float z, int start, int end) { // Dynamic translation
		t1 = type.DYNAMIC;
		t2 = type.TRANSLATION;
		this.x = x;
		this.y = y;
		this.z = z;
		this.start = start;
		this.end = end;
	}
	
	public float[] apply(float[] mModel, int frame) {
		if(t1 == type.STATIC) {
			if(t2 == type.ROTATION) {
				Matrix.rotateM(mModel, 0, angle, 0, 1, 0);
			}
			else {
				Matrix.translateM(mModel, 0, x, y, z);
			}
		}
		else {
			if(t2 == type.ROTATION) {
				
			}
			else {
				
			}
		}
		return mModel;
	}
}

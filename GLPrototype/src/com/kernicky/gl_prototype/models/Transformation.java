package com.kernicky.gl_prototype.models;

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
	private int end = 1;
	private int duration = 1;
	
	public Transformation(float angle, float x, float y, float z) { // Static rotation
		t1 = type.STATIC;
		t2 = type.ROTATION;
		this.angle = angle;
		this.x = x;
		this.y = y;
		this.z = z;
		//this.duration = end-start;
		
	}
	public Transformation(float x, float y, float z) { // Static translation
		t1 = type.STATIC;
		t2 = type.TRANSLATION;
		this.x = x;
		this.y = y;
		this.z = z;
		//this.duration = end-start;
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
		this.duration = end-start;
	}
	public Transformation(float x, float y, float z, int start, int end) { // Dynamic translation
		t1 = type.DYNAMIC;
		t2 = type.TRANSLATION;
		this.x = x;
		this.y = y;
		this.z = z;
		this.start = start;
		this.end = end;
		this.duration = end-start;
	}
	
	public int getEndTick() {
		return this.end;
	}
	
	public float[] apply(float[] mModel, int currentTick) {
		if(t1 == type.STATIC) {
			if(t2 == type.ROTATION) {
				Matrix.rotateM(mModel, 0, angle, x, y, z);
			}
			else {
				Matrix.translateM(mModel, 0, x, y, z);
			}
		}
		else {
			if(start <= currentTick && end >= currentTick) {

				if(t2 == type.ROTATION) {
					System.out.println("Current tick: " + currentTick + "vs" + end + " " + currentTick*angle/((float)duration));
					Matrix.rotateM(mModel, 0, currentTick*angle/((float)duration), x, y, z);
				}
				else {
					Matrix.translateM(mModel, 0, currentTick*x/((float)duration), currentTick*y/((float)duration), currentTick*z/((float)duration));

				}
			}
			else if(end < currentTick) {
				if(t2 == type.ROTATION) {
					System.out.println("Current tick: " + currentTick + "vs" + end + " " + angle);

					Matrix.rotateM(mModel, 0, angle, x, y, z);
				}
				else {
					Matrix.translateM(mModel, 0, x, y, z);

				}
			}
		}
		return mModel;
	}
}

package com.kernicky.gl_prototype;

import java.util.ArrayList;

import android.opengl.Matrix;

public class Scene {
	// initial view and proj matrices
	private float[] mModel = new float[16];
	private float[] mView = new float[16];
	private float[] mModelView = new float[16];
	private float[] mProj = new float[16];
	private float[] mModelViewProj = new float[16];
	private float[] mLightModel = new float[16];

	private float[] mViewerPos = { 0.0f, 0.0f, 2.0f };
	private float[] mCenterPos = { 0.0f, 0.0f, 0.0f };
	private float[] mUpV = { 0.0f, 1.0f, 0.0f };
	
	private ArrayList<Model> modelList = new ArrayList<Model>();

	public Scene() {

	}
	
	public void draw() {
		Matrix.setLookAtM(mView, 0, mViewerPos[0], mViewerPos[1],
				mViewerPos[2], mCenterPos[0], mCenterPos[1], mCenterPos[2],
				mUpV[0], mUpV[1], mUpV[2]);
		for(Model m: modelList) {
			m.draw(mModelView, mModelViewProj, mLightPos);
		}
	}
	
	public void setMProj(float[] mProj) {
		this.mProj = mProj;
	}
	public float[] getMProj() {
		return this.mProj;
	}	
}

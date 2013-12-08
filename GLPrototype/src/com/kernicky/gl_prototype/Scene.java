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
	
	private float[] mELightPos = new float[9];
	private final float[] mLightPos = {0.0f, 0.0f, 0.0f, 1.0f};
	

	private float[] mViewerPos = { 0.0f, 0.0f, 2.0f };
	private float[] mCenterPos = { 0.0f, 0.0f, 0.0f };
	private float[] mUpV = { 0.0f, 1.0f, 0.0f };
	
	private float rot = 0.0f;
	
	private ArrayList<Model> modelList = new ArrayList<Model>();

	public Scene() {
		PhongCube cube = new PhongCube();
		GoldenShip ship = new GoldenShip();
		Lamp l1 = new Lamp();

		modelList.add(cube);
		modelList.add(ship);
		modelList.add(l1);
	}
	
	public void draw() {
		Matrix.setLookAtM(mView, 0, mViewerPos[0], mViewerPos[1],
				mViewerPos[2], mCenterPos[0], mCenterPos[1], mCenterPos[2],
				mUpV[0], mUpV[1], mUpV[2]);
		Matrix.setIdentityM(mModel, 0);
		Matrix.setIdentityM(mModelView, 0);
		//Matrix.setIdentityM(mLightModel, 0);
		Matrix.rotateM(mModel, 0, rot, 0, 1, 0);
		Matrix.translateM(mModel, 0, -0.5f, 0.0f, 0.0f);
		rot += 1;
		
		Matrix.multiplyMM(mModelView, 0, mView, 0, mModel, 0);
		Matrix.multiplyMM(mModelViewProj, 0, mProj, 0, mModelView, 0);
		Matrix.multiplyMV(mELightPos, 0, mModelView, 0, mLightPos, 0);

		System.out.println();
		for(Model m: modelList) {
			m.draw2(mView, mProj, mELightPos);
			//m.draw(mModelView, mModelViewProj, mELightPos);
		}
	}	
	
	public void setMProj(float[] mProj) {
		this.mProj = mProj;
	}
	public float[] getMProj() {
		return this.mProj;
	}	
}

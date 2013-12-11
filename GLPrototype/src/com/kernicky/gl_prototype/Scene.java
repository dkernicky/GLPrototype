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
	private float[] mELightPos_1 = new float[4];
	private float[] mELightPos_2 = new float[4];
	private float[] mELightPos_3 = new float[4];
	private final float[] mLightPos_1 = {0.0f, 0.0f, 0.0f, 1.0f};
	private final float[] mLightPos_2 = {0.0f, 0.0f, 0.0f, 1.0f};
	private final float[] mLightPos_3 = {0.0f, 0.0f, 0.0f, 1.0f};
	

	private float[] mViewerPos = { 0.0f, 0.0f, 2.0f };
	private float[] mCenterPos = { 0.0f, 0.0f, 0.0f };
	private float[] mUpV = { 0.0f, 1.0f, 0.0f };
	
	private float rot = 0.0f;
	private float value = (float)(.5*Math.sqrt(3)/2.0);
	
	private ArrayList<Model> modelList = new ArrayList<Model>();
	private ArrayList<Model> lightList = new ArrayList<Model>();

	public Scene() {
		PhongCube cube = new PhongCube();
		GoldenShip ship = new GoldenShip();
		Lamp l1 = new Lamp();
		BlackIco b = new BlackIco();

		modelList.add(cube);
		modelList.add(ship);
		//modelList.add(b);
		//modelList.add(l1);
	}
	
	public void draw() {
		Matrix.setLookAtM(mView, 0, mViewerPos[0], mViewerPos[1],
				mViewerPos[2], mCenterPos[0], mCenterPos[1], mCenterPos[2],
				mUpV[0], mUpV[1], mUpV[2]);
		Matrix.setIdentityM(mModel, 0);
		Matrix.setIdentityM(mModelView, 0);
		//Matrix.setIdentityM(mLightModel, 0);
		Matrix.rotateM(mModel, 0, rot, 0, 0, 1);
		//Matrix.translateM(mModel, 0, -0.5f, 0.0f, 0.0f);
		Matrix.translateM(mModel, 0, 0.0f, 0.5f, 0.0f);

		Matrix.multiplyMM(mModelView, 0, mView, 0, mModel, 0);
		Matrix.multiplyMM(mModelViewProj, 0, mProj, 0, mModelView, 0);
		Matrix.multiplyMV(mELightPos_1, 0, mModelView, 0, mLightPos_1, 0);
		
		Matrix.setIdentityM(mModel, 0);
		Matrix.setIdentityM(mModelView, 0);
		Matrix.rotateM(mModel, 0, rot, 0, 0, 1);

		//Matrix.translateM(mModel, 0, 0.35f, -0.15f, 0.0f);
		Matrix.translateM(mModel, 0, value, -.25f, 0.0f);

		Matrix.multiplyMM(mModelView, 0, mView, 0, mModel, 0);
		Matrix.multiplyMM(mModelViewProj, 0, mProj, 0, mModelView, 0);
		Matrix.multiplyMV(mELightPos_2, 0, mModelView, 0, mLightPos_1, 0);
		
		Matrix.setIdentityM(mModel, 0);
		Matrix.setIdentityM(mModelView, 0);
		Matrix.rotateM(mModel, 0, rot, 0, 0, 1);

		//Matrix.translateM(mModel, 0, -0.35f, -0.15f, 0.0f);
		Matrix.translateM(mModel, 0, -1.0f*value, -.25f, 0.0f);

		Matrix.multiplyMM(mModelView, 0, mView, 0, mModel, 0);
		Matrix.multiplyMM(mModelViewProj, 0, mProj, 0, mModelView, 0);
		Matrix.multiplyMV(mELightPos_3, 0, mModelView, 0, mLightPos_1, 0);
		
		rot += 1;	
		
		mELightPos[0] = mELightPos_1[0];
		mELightPos[1] = mELightPos_1[1];
		mELightPos[2] = mELightPos_1[2];
		mELightPos[3] = mELightPos_2[0];
		mELightPos[4] = mELightPos_2[1];
		mELightPos[5] = mELightPos_2[2];
		mELightPos[6] = mELightPos_3[0];
		mELightPos[7] = mELightPos_3[1];
		mELightPos[8] = mELightPos_3[2];
		

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

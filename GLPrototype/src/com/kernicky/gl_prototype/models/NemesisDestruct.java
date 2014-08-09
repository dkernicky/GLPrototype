package com.kernicky.gl_prototype.models;

import com.kernicky.gl_prototype.math.Quaternion;

import android.opengl.Matrix;

public class NemesisDestruct extends Nemesis {
	
	public NemesisDestruct(float x, float y, float z) {
		super();
		this.position = new Quaternion(x, y, z, 0);
		position.normalize();
		//this.angle = Quaternion.rotateTo(new Quaternion(0, 0, 1, 0), this.position);
		
		position.multiply(7);

		transList.set(1, new Transformation(position.x, position.y, position.z));
		//transList.set(3, new Transformation(90.0f, 1, 0, 0));
		transList.set(5, new Transformation(0.5f));
		
		//position.print();
		
		loadSubList();
	}
	//@Override
	//public void updateKinematics() {}
	private void loadSubList() {
		Cube c = new Cube(this.position.x, this.position.y, this.position.z);
		//Cube c = new Cube(this.position.x-.5f, this.position.y-.5f, this.position.z-.5f);
		//c.transList.set(2, new Transformation(-.5f, -.5f, -.5f, 0, 300));
		this.subList.add(c);
		
//		c = new Cube(this.position.x+.5f, this.position.y-.5f, this.position.z-.5f);
//		c.transList.set(2, new Transformation(.5f, -.5f, -.5f, 0, 300));	
//		this.subList.add(c);
//		
//		c = new Cube(this.position.x-.5f, this.position.y+.5f, this.position.z-.5f);
//		c.transList.set(2, new Transformation(-.5f, -.5f, -.5f, 0, 300));
//		
//		this.subList.add(c);
//		
//		c = new Cube(this.position.x+.5f, this.position.y+.5f, this.position.z-.5f);
//		
//		this.subList.add(c);
//		
//		c = new Cube(this.position.x-.5f, this.position.y-.5f, this.position.z+.5f);
//		
//		this.subList.add(c);
//		
//		c = new Cube(this.position.x+.5f, this.position.y-.5f, this.position.z+.5f);
//		
//		this.subList.add(c);
//		
//		c = new Cube(this.position.x-.5f, this.position.y+.5f, this.position.z+.5f);
//		
//		this.subList.add(c);
//		
//		c = new Cube(this.position.x+.5f, this.position.y+.5f, this.position.z+.5f);
//		
//		this.subList.add(c);
		
	}
	
	@Override
	public void draw(float[] mView, float[] mProj, float[] mLightPos) {	

		float[] mModel = new float[16];
		float[] mModelView = new float[16];
		float[] mModelViewProj = new float[16];
		
		Matrix.setIdentityM(mModel, 0);
		mModel = applyTransforms(mModel);

		for(Model m: subList) {
			((EmissiveModel) m).draw(mModel, mView, mProj, mLightPos);
		}
		currentTick = (currentTick + 1) % maxTick;

	}
}

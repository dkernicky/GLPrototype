package com.kernicky.gl_prototype.models;

import java.util.ArrayList;
import java.util.Iterator;

import android.opengl.Matrix;

import com.kernicky.gl_prototype.ShootEmUpScene;
import com.kernicky.gl_prototype.math.MatrixOp;
import com.kernicky.gl_prototype.math.Quaternion;
import com.kernicky.gl_prototype.math.Vector;

public class Model {
	public int time = 0;
	public ArrayList<Transformation> transList = new ArrayList<Transformation>();
	public ArrayList<EmissiveModel> subList = new ArrayList<EmissiveModel>();
	
	protected int currentTick = 0;
	protected int maxTick = 1;
	
	public Quaternion position = new Quaternion(7, 0, 0, 0);
	protected float[] angle = MatrixOp.identity();
	protected float[] staticAngle = MatrixOp.identity();
	protected float[] initialAngle = MatrixOp.identity();
	
	public void applyRot(float speed, float[] axis) {
		position.normalize();
		Quaternion prevPos = new Quaternion(position.toFloat());
		Quaternion q = Quaternion.rotate(-1*speed, prevPos.toFloat(), axis);
		
		float[] transform = Quaternion.rotateTo(prevPos, q);
		position = new Quaternion(MatrixOp.multiplyMV(transform, position.toFloat()));		
		angle = MatrixOp.multiplyMM(transform, angle);
		position.normalize();
		
		float[] prevUp = {0, 1, 0, 0};
		float[] newUp = MatrixOp.multiplyMV(transform, prevUp);
		//newUp = Vector.normalize(newUp);
		
		float[] invAngle = Quaternion.rotateTo(newUp, prevUp);
		
		float[] inputVector = {position.x-prevPos.x, position.y-prevPos.y, position.z-prevPos.z, 0};
		inputVector = Vector.normalize(inputVector);
//		inputVector = MatrixOp.multiplyMV(invAngle, inputVector);
//		inputVector = Vector.normalize(inputVector);
//		MatrixOp.printV(inputVector);
//		float[] newTransform = Quaternion.rotateTo(prevUp, inputVector);
		
		float[] newTransform = Quaternion.rotateTo(newUp, inputVector);
		//newTransform = Quaternion.rotateTo(newUp, prevUp);

		staticAngle = MatrixOp.multiplyMM(invAngle, newTransform);
		
		//inputVector = MatrixOp.multiplyMV(newTransform, inputVector);
		//inputVector[2] = 0;
		//
		//MatrixOp.printV(inputVector);
		//staticAngle = Quaternion.rotateTo(prevUp, inputVector);	
		
		//staticAngle = newTransform;
		//staticAngle = Quaternion.rotateTo(new float[]{0, 1, 0, 0}, new float[]{1, 0, 0, 0});
//		staticAngle = transform;
		
		MatrixOp.printM(staticAngle);

		position.multiply(7);
		transList.set(0, new Transformation(position.x, position.y, position.z));
		transList.set(1, new Transformation(angle));
		transList.set(2, new Transformation(staticAngle));
	}

	public void seek(Quaternion q) {
		float[] axis = Vector.cross(q.toFloat(), position.toFloat());
		axis = Vector.normalize(axis);
		applyRot(.035f, axis);
	}

	public void wander() {
		float[] f = new float[3];
		f[0] = (float) Math.random();
		f[1] = (float) Math.random();
		f[2] = (float) Math.random();
		
		f = Vector.normalize(f);
		applyRot(.04f, f);
	
	
	}

	public void updateKinematics() {
		//applyRot(.04f, new float[]{0, 1, 0});
		float[] a = ShootEmUpScene.shipQ.toFloat();
		a = Vector.normalize(a);
		float[] b = position.toFloat();
		b = Vector.normalize(b);
		
		float angle = Vector.dot(a, b);
	
		if(angle > 0) {
			//seek(ShootEmUpScene.shipQ);
		}
		else {
			//wander();
			//seek(ShootEmUpScene.shipQ);
		}
	}

	public void updateMaxTick() {
		Iterator<Transformation> i = transList.iterator();
		if(i.hasNext()) {
			maxTick = i.next().getEndTick();
		}
		while(i.hasNext()) {
			int t = i.next().getEndTick();
			maxTick = Math.max(t,  maxTick);
		}
	}

	public float[] applyTransforms(float[] mModel) {
		for(int n = 0; n < transList.size(); n ++) {
			Transformation t = transList.get(n);
			mModel = t.apply(mModel, currentTick);
		}
		return mModel;
	}

	public void addTransform(Transformation t) {
		transList.add(t);
		this.updateMaxTick();
	}

}

package com.kernicky.gl_prototype.models;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.kernicky.gl_prototype.MyGLRenderer;
import com.kernicky.gl_prototype.ShootEmUpScene;
import com.kernicky.gl_prototype.math.MatrixOp;
import com.kernicky.gl_prototype.math.Quaternion;
import com.kernicky.gl_prototype.math.Vector;


public abstract class NewModel {
	
//	protected int mProgram;
//	protected int mVert;
//	protected int mFrag;
	protected FloatBuffer vertexBuffer;
	private FloatBuffer normalBuffer;

	protected int mPositionHandle;
	private int mNormalHandle;
	private int mAmbHandle;
	
	protected int mMVPMatrixHandle;
	protected int mMVMatrixHandle;
	private int mLightPosHandle;
	private float coords[], normals[], amb[]; 

	
	public int time = 0;
	private int programID;

	protected final int COORDS_PER_VERTEX = 3;
	protected final int vertexStride = COORDS_PER_VERTEX * 4;
	
	public ArrayList<Transformation> transList = new ArrayList<Transformation>();
	public ArrayList<NewModel> subList = new ArrayList<NewModel>();
	
	protected int currentTick = 0;
	protected int maxTick = 1;
	
	
	public Quaternion position = new Quaternion(7, 0, 0, 0);
	protected float[] angle = MatrixOp.identity();
	protected float[] staticAngle = MatrixOp.identity();

	NewModel() {
		setData();
		setProgram();

		initBuffers();

	}
	abstract void setData();
	public void setProgram() {
		setProgramID(MyGLRenderer.emissiveProgram);
	}

	
	public void applyRot(float speed, float[] axis) {
		//float[] inputVector = Vector.normalize(new float[]{dx, dy, 0, 0});
		position.normalize();
		Quaternion prevPos = new Quaternion(position.toFloat());
		
		Quaternion q = Quaternion.rotate(-1*speed, prevPos.toFloat(), axis);
		
		float[] transform = Quaternion.rotateTo(prevPos, q);
		position = new Quaternion(MatrixOp.multiplyMV(transform, position.toFloat()));		

		angle = MatrixOp.multiplyMM(transform, angle);

		position.normalize();;
		
		float[] shipDir = {0, 1, 0, 0};
		float[] inputVector = {position.x-prevPos.x, position.y-prevPos.y, position.z-prevPos.z};
		inputVector = Vector.normalize(inputVector);
		//MatrixOp.printV(inputVector);
		staticAngle = Quaternion.rotateTo(shipDir, inputVector);
		
		position.multiply(7);
		//position.print();
		
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
			seek(ShootEmUpScene.shipQ);
		}
		else {
			//wander();
			seek(ShootEmUpScene.shipQ);
		}
	}
	
//	public void setData(float[] coords, float[] normals, float[] amb, float[] diff,
//			float[] spec, float shine, int programID) {
//		this.programID = programID;
//		this.setCoords(coords);
//		this.setNormals(normals);
//		this.setAmb(amb);
//		this.setDiff(diff);
//		this.setSpec(spec);
//		this.setShine(shine);
//		initBuffers();
//		//createShaderProgram();
//	}
	

	public void initBuffers() {
		// initialize vertex byte buffer for shape coordinates
		ByteBuffer bbp = ByteBuffer.allocateDirect(
		// (# of coordinate values * 4 bytes per float)
				getCoords().length * 4);
		bbp.order(ByteOrder.nativeOrder());
		vertexBuffer = bbp.asFloatBuffer();
		vertexBuffer.put(getCoords());
		vertexBuffer.position(0);
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
	
	public void draw(float[] mModel, float[] mView, float[] mProj, float[] mLightPos) {
		transList.set(0, new Transformation(mModel));
		draw(mView, mProj, mLightPos);
	}
	


	
	public void draw(float[] mView, float[] mProj, float[] mLightPos) {

		float[] mModel = new float[16];
		float[] mModelView = new float[16];
		float[] mModelViewProj = new float[16];
		
		Matrix.setIdentityM(mModel, 0);
		mModel = applyTransforms(mModel);
		
		Matrix.multiplyMM(mModelView, 0, mView, 0, mModel, 0);
		Matrix.multiplyMM(mModelViewProj, 0, mProj, 0, mModelView, 0);	
		
		GLES20.glUseProgram(getProgramID());

		vertexBuffer.position(0);
		mPositionHandle = GLES20.glGetAttribLocation(getProgramID(), "a_Position");
		GLES20.glEnableVertexAttribArray(mPositionHandle);
		GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

		
		
		setmAmbHandle(GLES20.glGetUniformLocation(getProgramID(), "u_Ambient"));
		GLES20.glUniform3fv(getmAmbHandle(), 1, getAmb(), 0);
		
		mMVPMatrixHandle = GLES20.glGetUniformLocation(getProgramID(), "u_MVPMatrix");
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mModelViewProj, 0);

		mMVMatrixHandle = GLES20.glGetUniformLocation(getProgramID(), "u_MVMatrix");
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mModelView, 0);

		setmLightPosHandle(GLES20.glGetUniformLocation(getProgramID(), "u_LightPos"));
		GLES20.glUniform3fv(getmLightPosHandle(), mLightPos.length/3, mLightPos, 0);
		
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, getCoords().length / 3);
		GLES20.glDisableVertexAttribArray(mPositionHandle);
		
		currentTick = (currentTick + 1) % maxTick;
	}
	
	public void addTransform(Transformation t) {
		transList.add(t);
		this.updateMaxTick();
	}

	protected float[] getCoords() {
		return coords;
	}

	protected void setCoords(float coords[]) {
		this.coords = coords;
	}

	protected float[] getNormals() {
		return normals;
	}

	protected void setNormals(float normals[]) {
		this.normals = normals;
	}

	
	protected float[] getAmb() {
		return amb;
	}

	public void setAmb(float amb[]) {
		this.amb = amb;
	}
	public int getProgramID() {
		return programID;
	}
	public void setProgramID(int programID) {
		this.programID = programID;
	}
	public int getmNormalHandle() {
		return mNormalHandle;
	}
	public void setmNormalHandle(int mNormalHandle) {
		this.mNormalHandle = mNormalHandle;
	}
	public FloatBuffer getNormalBuffer() {
		return normalBuffer;
	}
	public void setNormalBuffer(FloatBuffer normalBuffer) {
		this.normalBuffer = normalBuffer;
	}
	public int getmAmbHandle() {
		return mAmbHandle;
	}
	public void setmAmbHandle(int mAmbHandle) {
		this.mAmbHandle = mAmbHandle;
	}
	public int getmLightPosHandle() {
		return mLightPosHandle;
	}
	public void setmLightPosHandle(int mLightPosHandle) {
		this.mLightPosHandle = mLightPosHandle;
	}

	
	
}

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


public abstract class Model {
	
//	protected int mProgram;
//	protected int mVert;
//	protected int mFrag;
	protected FloatBuffer vertexBuffer;
	private FloatBuffer normalBuffer;

	protected int mPositionHandle;
	private int mNormalHandle;
	private int mAmbHandle;
	private int mDiffHandle;
	private int mSpecHandle;
	private int mShinHandle;
	protected int mMVPMatrixHandle;
	protected int mMVMatrixHandle;
	private int mLightPosHandle;
	private float coords[], normals[], spec[], diff[];
	public float amb[]; 
	private float shine;
	public int time = 0;

	protected final int COORDS_PER_VERTEX = 3;
	protected final int vertexStride = COORDS_PER_VERTEX * 4;
	
	public ArrayList<Transformation> transList = new ArrayList<Transformation>();
	public ArrayList<Model> subList = new ArrayList<Model>();
	
	//public float angle = 0.0f;
	protected int currentTick = 0;
	protected int maxTick = 1;
	
//	public float angle = 0.0f;
//	public float rotX = 0.0f;
//	public float rotY = 0.0f;
	
	public Quaternion position = new Quaternion(7, 0, 0, 0);
	protected float[] angle = MatrixOp.identity();
	protected float[] staticAngle = MatrixOp.identity();

	public void applyRot(float speed, float[] axis) {
		//float[] inputVector = Vector.normalize(new float[]{dx, dy, 0, 0});
		position.normalize();
		Quaternion prevPos = new Quaternion(position.toFloat());
		
		Quaternion q = Quaternion.rotate(-1*speed, prevPos.toFloat(), axis);
		
		float[] transform = Quaternion.rotateTo(prevPos, q);
		position = new Quaternion(MatrixOp.multiplyMV(transform, position.toFloat()));		

		angle = MatrixOp.multiplyMM(transform, angle);
		
		//angle = transform;

		
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
	
	public void setData(float[] coords, float[] normals, float[] amb, float[] diff,
			float[] spec, float shine) {
		this.setCoords(coords);
		this.setNormals(normals);
		this.setAmb(amb);
		this.setDiff(diff);
		this.setSpec(spec);
		this.setShine(shine);
		initBuffers();
		//createShaderProgram();
	}
	
//	public void setData(float[] coords, float[] normals, float[] amb, float[] diff,
//			float[] spec, float shine, String vertShaderCode, String fragShaderCode) {
//		this.setCoords(coords);
//		this.setNormals(normals);
//		this.setAmb(amb);
//		this.setDiff(diff);
//		this.setSpec(spec);
//		this.setShine(shine);
//		initBuffers();
//		createShaderProgram(vertShaderCode, fragShaderCode);
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

		ByteBuffer bbn = ByteBuffer.allocateDirect(getNormals().length * 4);
		bbn.order(ByteOrder.nativeOrder());
		normalBuffer = bbn.asFloatBuffer();
		normalBuffer.put(getNormals());
		normalBuffer.position(0);

	}


	
//	public void destroyProgram() {		
//		GLES20.glDeleteShader(mVert);
//		GLES20.glDeleteShader(mFrag);
//
//		 GLES20.glDeleteProgram(mProgram);
//         mProgram = 0;
//	}
	
//	public void createShaderProgram(String vertShaderCode, String fragShaderCode) {
//		// prepare shaders and OpenGL program
//		int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
//				vertShaderCode);
//		int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
//				fragShaderCode);
//
//		mProgram = GLES20.glCreateProgram(); 					// create empty OpenGL Program
//
//		GLES20.glAttachShader(mProgram, vertexShader); 			// add vertex shader									
//		GLES20.glAttachShader(mProgram, fragmentShader); 		// add fragment shader
//															
//		GLES20.glBindAttribLocation(mProgram, 1, "a_Position");
//		GLES20.glBindAttribLocation(mProgram, 2, "a_Normal");
//		GLES20.glLinkProgram(mProgram); 						// create OpenGL program executables
//		
//		
//	}
	
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

		float[] mModelView = new float[16];
		float[] mModelViewProj = new float[16];
		
//		for(Model m: subList) {
//			m.transList.set(0, new Transformation(mModel));
//			//m.addTransformToFront(new Transformation(mModel));
//			m.draw(mModel, mView, mProj, mLightPos);
//		}
		transList.set(0, new Transformation(mModel));
		mModel = MatrixOp.identity();

		mModel = applyTransforms(mModel);

		
		Matrix.multiplyMM(mModelView, 0, mView, 0, mModel, 0);
		Matrix.multiplyMM(mModelViewProj, 0, mProj, 0, mModelView, 0);		
		
		GLES20.glUseProgram(MyGLRenderer.reflectiveProgram);

		vertexBuffer.position(0);
		mPositionHandle = GLES20.glGetAttribLocation(MyGLRenderer.reflectiveProgram, "a_Position");
		GLES20.glEnableVertexAttribArray(mPositionHandle);
		GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

		mNormalHandle = GLES20.glGetAttribLocation(MyGLRenderer.reflectiveProgram, "a_Normal");
		GLES20.glEnableVertexAttribArray(mNormalHandle);
		GLES20.glVertexAttribPointer(mNormalHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, normalBuffer);

		
		mAmbHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_Ambient");
		GLES20.glUniform3fv(mAmbHandle, 1, getAmb(), 0);
		
		mDiffHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_Diffuse");
		GLES20.glUniform3fv(mDiffHandle, 1, getDiff(), 0);

		mSpecHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_Specular");
		GLES20.glUniform3fv(mSpecHandle, 1, getSpec(), 0);

		mShinHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_Shininess");
		GLES20.glUniform1f(mShinHandle, getShine());

		mMVPMatrixHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_MVPMatrix");
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mModelViewProj, 0);

		mMVMatrixHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_MVMatrix");
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mModelView, 0);

		mLightPosHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_LightPos");
		GLES20.glUniform3fv(mLightPosHandle, mLightPos.length/3, mLightPos, 0);

		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, getCoords().length / 3);
		GLES20.glDisableVertexAttribArray(mPositionHandle);
		
		currentTick = (currentTick + 1) % maxTick;
	}
	
	public void draw(float[] mView, float[] mProj, float[] mLightPos) {

		float[] mModel = new float[16];
		float[] mModelView = new float[16];
		float[] mModelViewProj = new float[16];
		
		Matrix.setIdentityM(mModel, 0);
		mModel = applyTransforms(mModel);

//		for(Model m: subList) {
//			m.draw(mModel, mView, mProj, mLightPos);
//		}
		
		Matrix.multiplyMM(mModelView, 0, mView, 0, mModel, 0);
		Matrix.multiplyMM(mModelViewProj, 0, mProj, 0, mModelView, 0);		
		
//		int[] numAttributes = new int[1];
//		GLES20.glGetProgramiv(mProgram, GLES20.GL_ACTIVE_UNIFORM_MAX_LENGTH, numAttributes, 0);
//		System.out.println(numAttributes[0]);
		
		GLES20.glUseProgram(MyGLRenderer.reflectiveProgram);

		vertexBuffer.position(0);
		mPositionHandle = GLES20.glGetAttribLocation(MyGLRenderer.reflectiveProgram, "a_Position");
		GLES20.glEnableVertexAttribArray(mPositionHandle);
		GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);


		mNormalHandle = GLES20.glGetAttribLocation(MyGLRenderer.reflectiveProgram, "a_Normal");
		GLES20.glEnableVertexAttribArray(mNormalHandle);
		GLES20.glVertexAttribPointer(mNormalHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, normalBuffer);

		
		mAmbHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_Ambient");
		GLES20.glUniform3fv(mAmbHandle, 1, getAmb(), 0);
		
		mDiffHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_Diffuse");
		GLES20.glUniform3fv(mDiffHandle, 1, getDiff(), 0);

		mSpecHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_Specular");
		GLES20.glUniform3fv(mSpecHandle, 1, getSpec(), 0);

		mShinHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_Shininess");
		GLES20.glUniform1f(mShinHandle, getShine());

		mMVPMatrixHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_MVPMatrix");
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mModelViewProj, 0);

		mMVMatrixHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_MVMatrix");
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mModelView, 0);

		mLightPosHandle = GLES20.glGetUniformLocation(MyGLRenderer.reflectiveProgram, "u_LightPos");
		GLES20.glUniform3fv(mLightPosHandle, mLightPos.length/3, mLightPos, 0);
		
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, getCoords().length / 3);
		GLES20.glDisableVertexAttribArray(mPositionHandle);
		
		currentTick = (currentTick + 1) % maxTick;
	}
	
	public void addTransform(Transformation t) {
		transList.add(t);
		this.updateMaxTick();
	}
	public void addTransformToFront(Transformation t) {
		transList.add(0, t);
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

	protected float[] getSpec() {
		return spec;
	}

	protected void setSpec(float spec[]) {
		this.spec = spec;
	}

	protected float[] getDiff() {
		return diff;
	}

	protected void setDiff(float diff[]) {
		this.diff = diff;
	}

	protected float[] getAmb() {
		return amb;
	}

	protected void setAmb(float amb[]) {
		this.amb = amb;
	}

	protected float getShine() {
		return shine;
	}

	protected void setShine(float shine) {
		this.shine = shine;
	}
	
}

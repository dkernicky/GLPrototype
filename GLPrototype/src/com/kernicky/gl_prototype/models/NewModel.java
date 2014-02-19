package com.kernicky.gl_prototype.models;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.kernicky.gl_prototype.MyGLRenderer;


public abstract class NewModel {
	
	protected int mProgram;
	protected FloatBuffer vertexBuffer;
	private FloatBuffer normalBuffer;
	private FloatBuffer ambientBuffer;
	private FloatBuffer diffuseBuffer;
	private FloatBuffer specularBuffer;
	private FloatBuffer shininessBuffer;
	protected int mPositionHandle;
	private int mNormalHandle;
	private int mAmbHandle;
	private int mDiffHandle;
	private int mSpecHandle;
	private int mShinHandle;
	protected int mMVPMatrixHandle;
	protected int mMVMatrixHandle;
	private int mLightPosHandle;
	private float coords[], normals[], spec[], diff[], amb[]; 
	private float shine;

	protected final int COORDS_PER_VERTEX = 3;
	protected final int vertexStride = COORDS_PER_VERTEX * 4;
	
	public ArrayList<Transformation> transList = new ArrayList<Transformation>();
	protected int currentTick = 0;
	protected int maxTick = 1;
	
	public float angle = 0.0f;
	public float rotX = 0.0f;
	public float rotY = 0.0f;

	public void setData(float[] coords, float[] normals, float[] amb, float[] diff,
			float[] spec, float shine) {
		this.setCoords(coords);
		this.setNormals(normals);
		this.setAmb(amb);
		this.setDiff(diff);
		this.setSpec(spec);
		this.setShine(shine);
		initBuffers();
		createShaderProgram();
	}

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

	public void createShaderProgram() {
		// prepare shaders and OpenGL program
		int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
				ShaderData.singleColorVertexShaderCode);
		int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
				ShaderData.singleColorFragmentShaderCode);

		mProgram = GLES20.glCreateProgram(); // create empty OpenGL Program

		GLES20.glAttachShader(mProgram, vertexShader); // add vertex shader									
		GLES20.glAttachShader(mProgram, fragmentShader); // add fragment shader
															
		GLES20.glBindAttribLocation(mProgram, 1, "a_Position");
		GLES20.glBindAttribLocation(mProgram, 2, "a_Normal");
//		GLES20.glBindAttribLocation(mProgram, 3, "a_Ambient");
//		GLES20.glBindAttribLocation(mProgram, 4, "a_Diffuse");
//		GLES20.glBindAttribLocation(mProgram, 5, "a_Specular");
//		GLES20.glBindAttribLocation(mProgram, 6, "a_Shininess");
		GLES20.glLinkProgram(mProgram); // create OpenGL program executables
	}

	public void initHandles() {
		
	}
	
	public void updateMaxTick() {
		Iterator<Transformation> i = transList.iterator();
		if(i.hasNext()) {
			maxTick = i.next().getEndTick();
		}
		while(i.hasNext()) {
			int t = i.next().getEndTick();
			maxTick = Math.max(t,  maxTick);
//			if(maxTick > t) {
//				if(maxTick%t != 0) {
//					maxTick *= t;
//				}
//			}
//			else if(maxTick < t) {
//				if(t%maxTick != 0) {
//					maxTick *= t;
//				}
//				else
//					maxTick = t;
//			}
		}
		//System.out.println("******************" + maxTick);
	}	
	
	public float[] applyTransforms(float[] mModel) {
		for(int n = 0; n < transList.size(); n ++) {
			//System.out.println("Transform " + n);
			Transformation t = transList.get(n);
			mModel = t.apply(mModel, currentTick);
		}
//		for(Transformation t: transList) {
//			mModel = t.apply(mModel, currentTick);
//		}
		return mModel;
	}
	
	
	
	public void draw(float[] mView, float[] mProj, float[] mLightPos) {
		
		float[] mModel = new float[16];
		float[] mModelView = new float[16];
		float[] mModelViewProj = new float[16];
		
		Matrix.setIdentityM(mModel, 0);
		mModel = applyTransforms(mModel);

		Matrix.multiplyMM(mModelView, 0, mView, 0, mModel, 0);
		Matrix.multiplyMM(mModelViewProj, 0, mProj, 0, mModelView, 0);		
		

		GLES20.glUseProgram(mProgram);

		vertexBuffer.position(0);
		mPositionHandle = GLES20.glGetAttribLocation(mProgram, "a_Position");
		GLES20.glEnableVertexAttribArray(mPositionHandle);
		GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);
		MyGLRenderer.checkGlError("position");

		mNormalHandle = GLES20.glGetAttribLocation(mProgram, "a_Normal");
		//System.out.println("N**************" + mNormalHandle);
		GLES20.glEnableVertexAttribArray(mNormalHandle);
		GLES20.glVertexAttribPointer(mNormalHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, normalBuffer);
		MyGLRenderer.checkGlError("normal");

		mAmbHandle = GLES20.glGetUniformLocation(mProgram, "u_Ambient");
		GLES20.glUniform3fv(mAmbHandle, 1, getAmb(), 0);
		
		mDiffHandle = GLES20.glGetUniformLocation(mProgram, "u_Diffuse");
		GLES20.glUniform3fv(mDiffHandle, 1, getDiff(), 0);

		mSpecHandle = GLES20.glGetUniformLocation(mProgram, "u_Specular");
		GLES20.glUniform3fv(mSpecHandle, 1, getSpec(), 0);

		mShinHandle = GLES20.glGetUniformLocation(mProgram, "u_Shininess");
		GLES20.glUniform1f(mShinHandle, getShine());

		mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVPMatrix");
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mModelViewProj, 0);
		MyGLRenderer.checkGlError("glUniformMatrix4fv");

		mMVMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVMatrix");
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mModelView, 0);
		MyGLRenderer.checkGlError("glUniformMatrix4fv");

		mLightPosHandle = GLES20.glGetUniformLocation(mProgram, "u_LightPos");
		GLES20.glUniform3fv(mLightPosHandle, mLightPos.length/3, mLightPos, 0);
		//GLES20.glUniform3f(mLightPosHandle, mLightPos[0] , mLightPos[1], mLightPos[2]);

		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, getCoords().length / 3);
		GLES20.glDisableVertexAttribArray(mPositionHandle);
		
		currentTick = (currentTick + 1) % maxTick;
		//System.out.println(currentTick);
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

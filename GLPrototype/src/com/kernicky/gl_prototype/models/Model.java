package com.kernicky.gl_prototype.models;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.kernicky.gl_prototype.MyGLRenderer;


public abstract class Model {
	private final String vertexShaderCode =
			"uniform mat4 u_MVPMatrix;" +
			"uniform mat4 u_MVMatrix;" +
			"attribute vec4 a_Position;" +
			"attribute vec3 a_Normal;" +
			"attribute vec3 a_Ambient;" +
			"attribute vec3 a_Diffuse;" +
			"attribute vec3 a_Specular;" +
			"attribute float a_Shininess;" +
			"varying vec3 v_Position;" +
			"varying vec3 v_Normal;" +
			"varying vec3 v_Ambient;" +
			"varying vec3 v_Diffuse;" +
			"varying vec3 v_Specular;" +
			"varying float v_Shininess;" +
			"void main() {" +
			"  v_Position = vec3(u_MVMatrix * a_Position);" +
			"  v_Normal = normalize(vec3(u_MVMatrix * vec4(normalize(a_Normal), 0.0)));" +
			"  v_Ambient = a_Ambient;" +
			"  v_Diffuse = a_Diffuse;" +
			"  v_Specular = a_Specular;" +
			"  v_Shininess = a_Shininess;" +
			"  gl_Position = u_MVPMatrix * a_Position;" +
			"}";
	private final String fragmentShaderCode = 
			"precision mediump float;" + 
			"uniform vec3 u_LightPos[3];" +
			"varying vec3 v_Position;" +
			"varying vec3 v_Normal;" +
			"varying vec3 v_Ambient;" +
			"varying vec3 v_Diffuse;" +
			"varying vec3 v_Specular;" +
			"varying float v_Shininess;" +
			"void main() {  " +
			"  vec3 amb = vec3(0.0, 0.0, 0.0);" +
			"  vec3 diff = vec3(0.0, 0.0, 0.0);" +
			"  vec3 spec = vec3(0.0, 0.0, 0.0);" +
			"  for(int n = 0; n < 3; n ++) {" +
			"    vec3 v = normalize(vec3(0.0, 0.0, 0.0) - v_Position);" +
			"    vec3 lightDir = u_LightPos[n] - v_Position;" +
			"    float distance = length(lightDir);" +
			"    lightDir = lightDir/distance;" +
			"    distance = (distance*distance)/.5;" +
			
			"    normalize(v_Normal);" +
			"    float NdotL = dot(v_Normal, lightDir);" +
			"    float intensity = min(NdotL, 1.0);" +
			"    intensity = max(NdotL, 0.0);" +
			
			"    vec3 lc = vec3(1.0, 1.0, 1.0);" +
			"    vec3 v_Diff = vec3(.5, .5, .5);" +
			"    diff += intensity*lc*v_Diff/distance;" +
			"    vec3 H = normalize(v+lightDir);" +
			"    float NdotH = dot(v_Normal, H);" +
			"    intensity = min(NdotH, 1.0);" +
			"    intensity = max(NdotH, 0.0);" +
			"    vec3 v_Spec = vec3(.5, .5, .5);" +
			"    intensity = pow(intensity, v_Shininess);" +
			"    spec += intensity*lc*v_Spec/distance;" +
			"    amb += v_Ambient*lc;" +
			"  }" +
			"  gl_FragColor = vec4(amb+diff+spec, 0.0);" + 
			"}";
			
	private int mProgram;
	private FloatBuffer vertexBuffer, normalBuffer, ambientBuffer,
			diffuseBuffer, specularBuffer, shininessBuffer;
	private int mPositionHandle, mNormalHandle, mAmbHandle, mDiffHandle,
			mSpecHandle, mShinHandle, mMVPMatrixHandle, mMVMatrixHandle,
			mLightPosHandle;
	private float coords[], normals[], spec[], diff[], amb[], shine[];

	private final int COORDS_PER_VERTEX = 3;
	private final int vertexStride = COORDS_PER_VERTEX * 4;
	
	protected ArrayList<Transformation> transList = new ArrayList<Transformation>();
	private int currentTick = 0;
	private int maxTick = 1;

	public void setData(float[] coords, float[] normals, float[] amb, float[] diff,
			float[] spec, float[] shine) {
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

		ByteBuffer bba = ByteBuffer.allocateDirect(getAmb().length * 4);
		bba.order(ByteOrder.nativeOrder());
		ambientBuffer = bba.asFloatBuffer();
		ambientBuffer.put(getAmb());
		ambientBuffer.position(0);

		ByteBuffer bbd = ByteBuffer.allocateDirect(getDiff().length * 4);
		bbd.order(ByteOrder.nativeOrder());
		diffuseBuffer = bbd.asFloatBuffer();
		diffuseBuffer.put(getDiff());
		diffuseBuffer.position(0);

		ByteBuffer bbsp = ByteBuffer.allocateDirect(getSpec().length * 4);
		bbsp.order(ByteOrder.nativeOrder());
		specularBuffer = bbsp.asFloatBuffer();
		specularBuffer.put(getSpec());
		specularBuffer.position(0);

		ByteBuffer bbsh = ByteBuffer.allocateDirect(getShine().length * 4);
		bbsh.order(ByteOrder.nativeOrder());
		shininessBuffer = bbsp.asFloatBuffer();
		shininessBuffer.put(getShine());
		shininessBuffer.position(0);
	}

	public void createShaderProgram() {
		// prepare shaders and OpenGL program
		int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
				vertexShaderCode);
		int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
				fragmentShaderCode);

		mProgram = GLES20.glCreateProgram(); // create empty OpenGL Program

		GLES20.glAttachShader(mProgram, vertexShader); // add vertex shader									
		GLES20.glAttachShader(mProgram, fragmentShader); // add fragment shader
															
		GLES20.glBindAttribLocation(mProgram, 1, "a_Position");
		GLES20.glBindAttribLocation(mProgram, 2, "a_Normal");
		GLES20.glBindAttribLocation(mProgram, 3, "a_Ambient");
		GLES20.glBindAttribLocation(mProgram, 4, "a_Diffuse");
		GLES20.glBindAttribLocation(mProgram, 5, "a_Specular");
		GLES20.glBindAttribLocation(mProgram, 6, "a_Shininess");
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

		mAmbHandle = GLES20.glGetAttribLocation(mProgram, "a_Ambient");
		// System.out.println("A**************" + mAmbHandle);
		GLES20.glEnableVertexAttribArray(mAmbHandle);
		GLES20.glVertexAttribPointer(mAmbHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, ambientBuffer);
		MyGLRenderer.checkGlError("ambient");

		mDiffHandle = GLES20.glGetAttribLocation(mProgram, "a_Diffuse");
		// System.out.println("D**************" + mDiffHandle);
		GLES20.glEnableVertexAttribArray(mDiffHandle);
		GLES20.glVertexAttribPointer(mDiffHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, diffuseBuffer);
		MyGLRenderer.checkGlError("diffuse");

		mSpecHandle = GLES20.glGetAttribLocation(mProgram, "a_Specular");
		// System.out.println("S**************" + mSpecHandle);
		GLES20.glEnableVertexAttribArray(mSpecHandle);
		GLES20.glVertexAttribPointer(mSpecHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, specularBuffer);
		MyGLRenderer.checkGlError("specular");

		mShinHandle = GLES20.glGetAttribLocation(mProgram, "a_Shininess");
		// System.out.println("Sh**************" + mShinHandle);
		GLES20.glEnableVertexAttribArray(mShinHandle);
		GLES20.glVertexAttribPointer(mShinHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, 4, shininessBuffer);
		MyGLRenderer.checkGlError("shininess");

		mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVPMatrix");
		// System.out.println("M**************" + mMVPMatrixHandle);
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mModelViewProj, 0);
		MyGLRenderer.checkGlError("glUniformMatrix4fv");

		mMVMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVMatrix");
		// System.out.println("M**************" + mMVPMatrixHandle);
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mModelView, 0);
		MyGLRenderer.checkGlError("glUniformMatrix4fv");

		mLightPosHandle = GLES20.glGetUniformLocation(mProgram, "u_LightPos");
		GLES20.glUniform3fv(mLightPosHandle, 3, mLightPos, 0);
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

	protected float[] getShine() {
		return shine;
	}

	protected void setShine(float shine[]) {
		this.shine = shine;
	}
	
}

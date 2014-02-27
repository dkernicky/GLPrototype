package com.kernicky.gl_prototype.models;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.kernicky.gl_prototype.MyGLRenderer;


public class Lamp extends NewModel {
	private float rot = 0.0f;
	private float[] mModel = new float[16];
	private float[] mELightPos = new float[4];
	private float[] mLightPos = new float[4];
	
	public static final float[] coords = { 0.0f, -0.5f, 0.0f, 0.2126615f, -0.425327f, 0.1545055f, -0.081228f, -0.425327f, 0.2499975f, 0.3618035f, -0.22361f, 0.2628625f, 0.2126615f, -0.425327f, 0.1545055f, 0.425324f, -0.262868f, 0.0f, 0.0f, -0.5f, 0.0f, -0.081228f, -0.425327f, 0.2499975f, -0.262865f, -0.425326f, 0.0f, 0.0f, -0.5f, 0.0f, -0.262865f, -0.425326f, 0.0f, -0.081228f, -0.425327f, -0.2499975f, 0.0f, -0.5f, 0.0f, -0.081228f, -0.425327f, -0.2499975f, 0.2126615f, -0.425327f, -0.1545055f, 0.3618035f, -0.22361f, 0.2628625f, 0.425324f, -0.262868f, 0.0f, 0.475529f, 0.0f, 0.1545065f, -0.138194f, -0.22361f, 0.4253245f, 0.1314345f, -0.262869f, 0.404506f, 0.0f, 0.0f, 0.5f, -0.447213f, -0.223608f, 0.0f, -0.3440945f, -0.262868f, 0.2499985f, -0.475529f, 0.0f, 0.1545065f, -0.138194f, -0.22361f, -0.4253245f, -0.3440945f, -0.262868f, -0.2499985f, -0.293893f, 0.0f, -0.4045085f, 0.3618035f, -0.22361f, -0.2628625f, 0.1314345f, -0.262869f, -0.404506f, 0.293893f, 0.0f, -0.4045085f, 0.3618035f, -0.22361f, 0.2628625f, 0.475529f, 0.0f, 0.1545065f, 0.293893f, 0.0f, 0.4045085f, -0.138194f, -0.22361f, 0.4253245f, 0.0f, 0.0f, 0.5f, -0.293893f, 0.0f, 0.4045085f, -0.447213f, -0.223608f, 0.0f, -0.475529f, 0.0f, 0.1545065f, -0.475529f, 0.0f, -0.1545065f, -0.138194f, -0.22361f, -0.4253245f, -0.293893f, 0.0f, -0.4045085f, 0.0f, 0.0f, -0.5f, 0.3618035f, -0.22361f, -0.2628625f, 0.293893f, 0.0f, -0.4045085f, 0.475529f, 0.0f, -0.1545065f, 0.138194f, 0.22361f, 0.4253245f, 0.3440945f, 0.262868f, 0.2499985f, 0.081228f, 0.425327f, 0.2499975f, -0.3618035f, 0.22361f, 0.2628625f, -0.1314345f, 0.262869f, 0.404506f, -0.2126615f, 0.425327f, 0.1545055f, -0.3618035f, 0.22361f, -0.2628625f, -0.425324f, 0.262868f, 0.0f, -0.2126615f, 0.425327f, -0.1545055f, 0.138194f, 0.22361f, -0.4253245f, -0.1314345f, 0.262869f, -0.404506f, 0.081228f, 0.425327f, -0.2499975f, 0.447213f, 0.223608f, 0.0f, 0.3440945f, 0.262868f, -0.2499985f, 0.262865f, 0.425326f, 0.0f, -0.081228f, -0.425327f, 0.2499975f, 0.1314345f, -0.262869f, 0.404506f, -0.138194f, -0.22361f, 0.4253245f, -0.081228f, -0.425327f, 0.2499975f, 0.2126615f, -0.425327f, 0.1545055f, 0.1314345f, -0.262869f, 0.404506f, 0.2126615f, -0.425327f, 0.1545055f, 0.3618035f, -0.22361f, 0.2628625f, 0.1314345f, -0.262869f, 0.404506f, 0.425324f, -0.262868f, 0.0f, 0.2126615f, -0.425327f, -0.1545055f, 0.3618035f, -0.22361f, -0.2628625f, 0.425324f, -0.262868f, 0.0f, 0.2126615f, -0.425327f, 0.1545055f, 0.2126615f, -0.425327f, -0.1545055f, 0.2126615f, -0.425327f, 0.1545055f, 0.0f, -0.5f, 0.0f, 0.2126615f, -0.425327f, -0.1545055f, -0.262865f, -0.425326f, 0.0f, -0.3440945f, -0.262868f, 0.2499985f, -0.447213f, -0.223608f, 0.0f, -0.262865f, -0.425326f, 0.0f, -0.081228f, -0.425327f, 0.2499975f, -0.3440945f, -0.262868f, 0.2499985f, -0.081228f, -0.425327f, 0.2499975f, -0.138194f, -0.22361f, 0.4253245f, -0.3440945f, -0.262868f, 0.2499985f, -0.081228f, -0.425327f, -0.2499975f, -0.3440945f, -0.262868f, -0.2499985f, -0.138194f, -0.22361f, -0.4253245f, -0.081228f, -0.425327f, -0.2499975f, -0.262865f, -0.425326f, 0.0f, -0.3440945f, -0.262868f, -0.2499985f, -0.262865f, -0.425326f, 0.0f, -0.447213f, -0.223608f, 0.0f, -0.3440945f, -0.262868f, -0.2499985f, 0.2126615f, -0.425327f, -0.1545055f, 0.1314345f, -0.262869f, -0.404506f, 0.3618035f, -0.22361f, -0.2628625f, 0.2126615f, -0.425327f, -0.1545055f, -0.081228f, -0.425327f, -0.2499975f, 0.1314345f, -0.262869f, -0.404506f, -0.081228f, -0.425327f, -0.2499975f, -0.138194f, -0.22361f, -0.4253245f, 0.1314345f, -0.262869f, -0.404506f, 0.475529f, 0.0f, 0.1545065f, 0.475529f, 0.0f, -0.1545065f, 0.447213f, 0.223608f, 0.0f, 0.475529f, 0.0f, 0.1545065f, 0.425324f, -0.262868f, 0.0f, 0.475529f, 0.0f, -0.1545065f, 0.425324f, -0.262868f, 0.0f, 0.3618035f, -0.22361f, -0.2628625f, 0.475529f, 0.0f, -0.1545065f, 0.0f, 0.0f, 0.5f, 0.293893f, 0.0f, 0.4045085f, 0.138194f, 0.22361f, 0.4253245f, 0.0f, 0.0f, 0.5f, 0.1314345f, -0.262869f, 0.404506f, 0.293893f, 0.0f, 0.4045085f, 0.1314345f, -0.262869f, 0.404506f, 0.3618035f, -0.22361f, 0.2628625f, 0.293893f, 0.0f, 0.4045085f, -0.475529f, 0.0f, 0.1545065f, -0.293893f, 0.0f, 0.4045085f, -0.3618035f, 0.22361f, 0.2628625f, -0.475529f, 0.0f, 0.1545065f, -0.3440945f, -0.262868f, 0.2499985f, -0.293893f, 0.0f, 0.4045085f, -0.3440945f, -0.262868f, 0.2499985f, -0.138194f, -0.22361f, 0.4253245f, -0.293893f, 0.0f, 0.4045085f, -0.293893f, 0.0f, -0.4045085f, -0.475529f, 0.0f, -0.1545065f, -0.3618035f, 0.22361f, -0.2628625f, -0.293893f, 0.0f, -0.4045085f, -0.3440945f, -0.262868f, -0.2499985f, -0.475529f, 0.0f, -0.1545065f, -0.3440945f, -0.262868f, -0.2499985f, -0.447213f, -0.223608f, 0.0f, -0.475529f, 0.0f, -0.1545065f, 0.293893f, 0.0f, -0.4045085f, 0.0f, 0.0f, -0.5f, 0.138194f, 0.22361f, -0.4253245f, 0.293893f, 0.0f, -0.4045085f, 0.1314345f, -0.262869f, -0.404506f, 0.0f, 0.0f, -0.5f, 0.1314345f, -0.262869f, -0.404506f, -0.138194f, -0.22361f, -0.4253245f, 0.0f, 0.0f, -0.5f, 0.293893f, 0.0f, 0.4045085f, 0.3440945f, 0.262868f, 0.2499985f, 0.138194f, 0.22361f, 0.4253245f, 0.293893f, 0.0f, 0.4045085f, 0.475529f, 0.0f, 0.1545065f, 0.3440945f, 0.262868f, 0.2499985f, 0.475529f, 0.0f, 0.1545065f, 0.447213f, 0.223608f, 0.0f, 0.3440945f, 0.262868f, 0.2499985f, -0.293893f, 0.0f, 0.4045085f, -0.1314345f, 0.262869f, 0.404506f, -0.3618035f, 0.22361f, 0.2628625f, -0.293893f, 0.0f, 0.4045085f, 0.0f, 0.0f, 0.5f, -0.1314345f, 0.262869f, 0.404506f, 0.0f, 0.0f, 0.5f, 0.138194f, 0.22361f, 0.4253245f, -0.1314345f, 0.262869f, 0.404506f, -0.475529f, 0.0f, -0.1545065f, -0.425324f, 0.262868f, 0.0f, -0.3618035f, 0.22361f, -0.2628625f, -0.475529f, 0.0f, -0.1545065f, -0.475529f, 0.0f, 0.1545065f, -0.425324f, 0.262868f, 0.0f, -0.475529f, 0.0f, 0.1545065f, -0.3618035f, 0.22361f, 0.2628625f, -0.425324f, 0.262868f, 0.0f, 0.0f, 0.0f, -0.5f, -0.1314345f, 0.262869f, -0.404506f, 0.138194f, 0.22361f, -0.4253245f, 0.0f, 0.0f, -0.5f, -0.293893f, 0.0f, -0.4045085f, -0.1314345f, 0.262869f, -0.404506f, -0.293893f, 0.0f, -0.4045085f, -0.3618035f, 0.22361f, -0.2628625f, -0.1314345f, 0.262869f, -0.404506f, 0.475529f, 0.0f, -0.1545065f, 0.3440945f, 0.262868f, -0.2499985f, 0.447213f, 0.223608f, 0.0f, 0.475529f, 0.0f, -0.1545065f, 0.293893f, 0.0f, -0.4045085f, 0.3440945f, 0.262868f, -0.2499985f, 0.293893f, 0.0f, -0.4045085f, 0.138194f, 0.22361f, -0.4253245f, 0.3440945f, 0.262868f, -0.2499985f, 0.081228f, 0.425327f, 0.2499975f, 0.262865f, 0.425326f, 0.0f, 0.0f, 0.5f, 0.0f, 0.081228f, 0.425327f, 0.2499975f, 0.3440945f, 0.262868f, 0.2499985f, 0.262865f, 0.425326f, 0.0f, 0.3440945f, 0.262868f, 0.2499985f, 0.447213f, 0.223608f, 0.0f, 0.262865f, 0.425326f, 0.0f, -0.2126615f, 0.425327f, 0.1545055f, 0.081228f, 0.425327f, 0.2499975f, 0.0f, 0.5f, 0.0f, -0.2126615f, 0.425327f, 0.1545055f, -0.1314345f, 0.262869f, 0.404506f, 0.081228f, 0.425327f, 0.2499975f, -0.1314345f, 0.262869f, 0.404506f, 0.138194f, 0.22361f, 0.4253245f, 0.081228f, 0.425327f, 0.2499975f, -0.2126615f, 0.425327f, -0.1545055f, -0.2126615f, 0.425327f, 0.1545055f, 0.0f, 0.5f, 0.0f, -0.2126615f, 0.425327f, -0.1545055f, -0.425324f, 0.262868f, 0.0f, -0.2126615f, 0.425327f, 0.1545055f, -0.425324f, 0.262868f, 0.0f, -0.3618035f, 0.22361f, 0.2628625f, -0.2126615f, 0.425327f, 0.1545055f, 0.081228f, 0.425327f, -0.2499975f, -0.2126615f, 0.425327f, -0.1545055f, 0.0f, 0.5f, 0.0f, 0.081228f, 0.425327f, -0.2499975f, -0.1314345f, 0.262869f, -0.404506f, -0.2126615f, 0.425327f, -0.1545055f, -0.1314345f, 0.262869f, -0.404506f, -0.3618035f, 0.22361f, -0.2628625f, -0.2126615f, 0.425327f, -0.1545055f, 0.262865f, 0.425326f, 0.0f, 0.081228f, 0.425327f, -0.2499975f, 0.0f, 0.5f, 0.0f, 0.262865f, 0.425326f, 0.0f, 0.3440945f, 0.262868f, -0.2499985f, 0.081228f, 0.425327f, -0.2499975f, 0.3440945f, 0.262868f, -0.2499985f, 0.138194f, 0.22361f, -0.4253245f, 0.081228f, 0.425327f, -0.2499975f };

	
	public Lamp(float x, float y, float z) {
		super();
		
		mLightPos[0] = x;
		mLightPos[1] = y;
		mLightPos[2] = z;
		mLightPos[3] = 1.0f;
	}
	
	public void draw(float[] mView, float[] mProj) {
		float[] mModel = new float[16];
		float[] mModelView = new float[16];
		float[] mModelViewProj = new float[16];
		
		Matrix.setIdentityM(mModel, 0);
		mModel = applyTransforms(mModel);

		Matrix.multiplyMM(mModelView, 0, mView, 0, mModel, 0);

		Matrix.multiplyMM(mModelViewProj, 0, mProj, 0, mModelView, 0);		
		Matrix.multiplyMV(mELightPos, 0, mModelView, 0, mLightPos, 0);

		GLES20.glUseProgram(MyGLRenderer.emissiveProgram);

		vertexBuffer.position(0);
		mPositionHandle = GLES20.glGetAttribLocation(MyGLRenderer.emissiveProgram, "a_Position");
		GLES20.glEnableVertexAttribArray(mPositionHandle);
		GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);
		MyGLRenderer.checkGlError("position");
		
		setmAmbHandle(GLES20.glGetUniformLocation(getProgramID(), "u_Ambient"));
		GLES20.glUniform3fv(getmAmbHandle(), 1, getAmb(), 0);

		mMVPMatrixHandle = GLES20.glGetUniformLocation(MyGLRenderer.emissiveProgram, "u_MVPMatrix");
		// System.out.println("M**************" + mMVPMatrixHandle);
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mModelViewProj, 0);
		MyGLRenderer.checkGlError("glUniformMatrix4fv");

		mMVMatrixHandle = GLES20.glGetUniformLocation(MyGLRenderer.emissiveProgram, "u_MVMatrix");
		// System.out.println("M**************" + mMVPMatrixHandle);
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mModelView, 0);
		MyGLRenderer.checkGlError("glUniformMatrix4fv");


		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, getCoords().length / 3);
		GLES20.glDisableVertexAttribArray(mPositionHandle);
		
		currentTick = (currentTick + 1) % maxTick;
		//System.out.println(currentTick);
	}
	
	public void setData() {
		setCoords(coords);
		setAmb(new float[]{1.0f, 1.0f, 1.0f});
	}

	
	public float[] getMELightPos() {
		return mELightPos;
	}
}

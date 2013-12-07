package com.kernicky.gl_prototype;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;

abstract class Model {
	private int mProgram;
	private FloatBuffer vertexBuffer, normalBuffer, ambientBuffer,
			diffuseBuffer, specularBuffer, shininessBuffer;
	private int mPositionHandle, mNormalHandle, mAmbHandle, mDiffHandle,
			mSpecHandle, mShinHandle, mMVPMatrixHandle, mMVMatrixHandle,
			mLightPosHandle;
	private float coords[], normals[], spec[], diff[], amb[], shine[];

	private final int COORDS_PER_VERTEX = 3;
	private final int vertexStride = COORDS_PER_VERTEX * 4;

	public Model(float[] coords, float[] normals, float[] amb, float[] diff,
			float[] spec, float[] shine) {
		this.coords = coords;
		this.normals = normals;
		this.amb = amb;
		this.diff = diff;
		this.spec = spec;
		this.shine = shine;
		initBuffers();
		createShaderProgram();
	}

	public void initBuffers() {
		// initialize vertex byte buffer for shape coordinates
		ByteBuffer bbp = ByteBuffer.allocateDirect(
		// (# of coordinate values * 4 bytes per float)
				coords.length * 4);
		bbp.order(ByteOrder.nativeOrder());
		vertexBuffer = bbp.asFloatBuffer();
		vertexBuffer.put(coords);
		vertexBuffer.position(0);

		ByteBuffer bbn = ByteBuffer.allocateDirect(normals.length * 4);
		bbn.order(ByteOrder.nativeOrder());
		normalBuffer = bbn.asFloatBuffer();
		normalBuffer.put(normals);
		normalBuffer.position(0);

		ByteBuffer bba = ByteBuffer.allocateDirect(amb.length * 4);
		bba.order(ByteOrder.nativeOrder());
		ambientBuffer = bba.asFloatBuffer();
		ambientBuffer.put(amb);
		ambientBuffer.position(0);

		ByteBuffer bbd = ByteBuffer.allocateDirect(diff.length * 4);
		bbd.order(ByteOrder.nativeOrder());
		diffuseBuffer = bbd.asFloatBuffer();
		diffuseBuffer.put(diff);
		diffuseBuffer.position(0);

		ByteBuffer bbsp = ByteBuffer.allocateDirect(spec.length * 4);
		bbsp.order(ByteOrder.nativeOrder());
		specularBuffer = bbsp.asFloatBuffer();
		specularBuffer.put(spec);
		specularBuffer.position(0);

		ByteBuffer bbsh = ByteBuffer.allocateDirect(shine.length * 4);
		bbsh.order(ByteOrder.nativeOrder());
		shininessBuffer = bbsp.asFloatBuffer();
		shininessBuffer.put(shine);
		shininessBuffer.position(0);
	}

	public void createShaderProgram() {
		// prepare shaders and OpenGL program
		int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
				vertexShaderCode);
		int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
				fragmentShaderCode);

		mProgram = GLES20.glCreateProgram(); // create empty OpenGL Program
		GLES20.glAttachShader(mProgram, vertexShader); // add the vertex shader
														// to program
		GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment
															// shader to program
		GLES20.glBindAttribLocation(mProgram, 1, "a_Position");
		GLES20.glBindAttribLocation(mProgram, 2, "a_Normal");
		GLES20.glBindAttribLocation(mProgram, 3, "a_Ambient");
		GLES20.glBindAttribLocation(mProgram, 4, "a_Diffuse");
		GLES20.glBindAttribLocation(mProgram, 5, "a_Specular");
		GLES20.glBindAttribLocation(mProgram, 6, "a_Shininess");
		GLES20.glLinkProgram(mProgram); // create OpenGL program executables
	}

	public void draw(float[] mvMatrix, float[] mvpMatrix, float[] mLightPos) {
		// Add program to OpenGL environment
		GLES20.glUseProgram(mProgram);

		vertexBuffer.position(0);
		mPositionHandle = GLES20.glGetAttribLocation(mProgram, "a_Position"); // get
																				// handle
																				// to
																				// vertex
																				// shader's
																				// vPosition
																				// member
		// System.out.println("P**************" + mPositionHandle);
		GLES20.glEnableVertexAttribArray(mPositionHandle); // Enable a handle to
															// the triangle
															// vertices
		GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, vertexBuffer); // Prepare
																		// the
																		// triangle
																		// coordinate
																		// data

		mNormalHandle = GLES20.glGetAttribLocation(mProgram, "a_Normal");
		// System.out.println("N**************" + mNormalHandle);
		GLES20.glEnableVertexAttribArray(mNormalHandle);
		GLES20.glVertexAttribPointer(mNormalHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, normalBuffer);
		MyGLRenderer.checkGlError("normal");
		// System.out.println("sdlakfhlaskdjfhklsjadhilfjkdhaslkfjsdfsadfsd");

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

		mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVPMatrix"); // get
																					// handle
																					// to
																					// shape's
																					// transformation
																					// matrix
		// System.out.println("M**************" + mMVPMatrixHandle);
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0); // Apply
																				// the
																				// projection
																				// and
																				// view
																				// transformation
		MyGLRenderer.checkGlError("glUniformMatrix4fv");

		mMVMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVMatrix"); // get
																				// handle
																				// to
																				// shape's
																				// transformation
																				// matrix
		// System.out.println("M**************" + mMVPMatrixHandle);
		MyGLRenderer.checkGlError("glGetUniformLocation");
		GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mvMatrix, 0); // Apply
																			// the
																			// projection
																			// and
																			// view
																			// transformation
		MyGLRenderer.checkGlError("glUniformMatrix4fv");

		mLightPosHandle = GLES20.glGetUniformLocation(mProgram, "u_LightPos");
		GLES20.glUniform3fv(mLightPosHandle, 3, mLightPos, 0);

		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, coords.length / 3);
		// Disable vertex array
		GLES20.glDisableVertexAttribArray(mPositionHandle);
		// GLES20.glDisableVertexAttribArray(mNormalHandle);
	}
}

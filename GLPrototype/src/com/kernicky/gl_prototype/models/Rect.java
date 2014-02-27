package com.kernicky.gl_prototype.models;

import com.kernicky.gl_prototype.MyGLRenderer;

public class Rect extends EmissiveModel {

	public static final float[] coords = { -0.25f, 0.0f, 0.5f, 0.25f, 0.0f, 0.5f, -0.25f, 0.0f, -0.5f, 0.25f, 0.0f, 0.5f, 0.25f, 0.0f, -0.5f, -0.25f, 0.0f, -0.5f };
	public static final float[] normals = { 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f };
	
	public Rect() {
		super();
	}
	public void setData() {
		setCoords(coords);
		setNormals(normals);
		setAmb(new float[]{1.0f, .0f, 0.0f});
		//setDiff(new float[]{1f, 0f, 0f});
		//setSpec(new float[]{0.5f, 0.5f, 0.5f});
		//setShine(300);
	}
}

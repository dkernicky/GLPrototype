package com.kernicky.gl_prototype.models;

import com.kernicky.gl_prototype.MyGLRenderer;

public class Rect extends ReflectiveModel {

	public static final float[] coords = { -0.25f, 0.0f, 0.5f, 0.25f, 0.0f, 0.5f, -0.25f, 0.0f, -0.5f, 0.25f, 0.0f, 0.5f, 0.25f, 0.0f, -0.5f, -0.25f, 0.0f, -0.5f };
	public static final float[] normals = { 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f };
	
	public Rect() {
		super();
	}
	public void setData() {
		setCoords(coords);
		setNormals(normals);
		setAmb(new float[]{1.0f, .0f, 0.0f});
		setDiff(new float[]{1f, 0f, 0f});
		setSpec(new float[]{0.5f, 0.5f, 0.5f});
		setShine(300);
	}
//	public Rect() {
//		this.setData(ModelData.RectCoords, ModelData.RectNormals, setAmb(), setDiff(), setSpec(),
//				setShine(), MyGLRenderer.reflectiveProgram);
//	}
//
//	public float[] setCoords() {
//		float coords[] = { -0.25f, 0.0f, 0.5f, 0.25f, 0.0f, 0.5f, -0.25f, 0.0f,
//				-0.5f, 0.25f, 0.0f, 0.5f, 0.25f, 0.0f, -0.5f, -0.25f, 0.0f,
//				-0.5f };
//		return coords;
//	}
//
//	public float[] setNormals() {
//		float normals[] = { 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f,
//				-0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f };
//		return normals;
//	}
//
//	public float[] setAmb() {
//		float[] amb = { 1.0f, 0.0f, 0.0f };
//		return amb;
//	}
//
//	public float[] setDiff() {
//		float[] diff = { 1.0f, 0.0f, 0.0f };
//		return diff;
//	}
//
//	public float[] setSpec() {
//		float[] spec = { 0.5f, 0.5f, 0.5f };
//		return spec;
//	}
//
//	public float setShine() {
//		float shine = 96;
//		return shine;
//	}

}

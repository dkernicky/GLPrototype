package com.kernicky.gl_prototype.models;

public class Rect extends Model {

	public Rect() {
		this.setData(ModelData.RectCoords, ModelData.RectNormals, setAmb(), setDiff(), setSpec(),
				setShine());
	}

	public float[] setCoords() {
		float coords[] = { -0.25f, 0.0f, 0.5f, 0.25f, 0.0f, 0.5f, -0.25f, 0.0f,
				-0.5f, 0.25f, 0.0f, 0.5f, 0.25f, 0.0f, -0.5f, -0.25f, 0.0f,
				-0.5f };
		return coords;
	}

	public float[] setNormals() {
		float normals[] = { 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f,
				-0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f };
		return normals;
	}

	public float[] setAmb() {
		float[] amb = { 1.0f, 0.0f, 0.0f };
		return amb;
	}

	public float[] setDiff() {
		float[] diff = { 1.0f, 0.0f, 0.0f };
		return diff;
	}

	public float[] setSpec() {
		float[] spec = { 0.5f, 0.5f, 0.5f };
		return spec;
	}

	public float setShine() {
		float shine = 96;
		return shine;
	}

}

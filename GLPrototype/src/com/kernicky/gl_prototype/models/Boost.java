package com.kernicky.gl_prototype.models;

import com.kernicky.gl_prototype.MyGLRenderer;

public class Boost extends EmissiveModel {

	public static final float coords[] = { -0.5f, 0.0f, 0.0f, 0.1867495f, 0.0f, -0.0832515f, -0.5f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, -0.5f, -0.092217f, 0.0f, -0.3996065f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.5f, 0.1867495f, 0.0f, 0.0832515f, 0.0f, 0.0f, 0.5f, 0.5f, 0.0f, 0.5f, 0.5f, 0.0f, 0.0f, -0.092217f, 0.0f, 0.3996065f, 0.0f, 0.0f, 0.5f, 0.5f, 0.0f, 0.0f, 0.1867495f, 0.0f, -0.0832515f, -0.092217f, 0.0f, -0.3996065f, -0.5f, 0.0f, -0.5f, -0.092217f, 0.0f, -0.3996065f, 0.0f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, -0.092217f, 0.0f, 0.3996065f, 0.1867495f, 0.0f, 0.0832515f, -0.5f, 0.0f, 0.5f, -0.092217f, 0.0f, 0.3996065f };
	public static final float normals[] = { 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f, 0.0f, 1.0f, -0.0f };
	
	public Boost(float position) {
		super();
		position +=.5f;

		transList.set(1, new Transformation(5-position*.75f, -2.65f, 7));
		transList.set(2, new Transformation(90.0f, 1, 0, 0));
		transList.set(3, new Transformation(.5f));
	}
	public void setData() {
		setCoords(coords);
		setNormals(normals);
		setAmb(new float[]{.0f, .0f, 1.0f});
		//setDiff(new float[]{1f, 0f, 0f});
		//setSpec(new float[]{0.5f, 0.5f, 0.5f});
		//setShine(300);
	}
}

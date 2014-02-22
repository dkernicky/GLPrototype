package com.kernicky.gl_prototype.models;



public class Quadrant extends Model {

	public Quadrant() {
		this.setData(setCoords(), setNormals(), setAmb(), setDiff(), setSpec(), setShine());
	}
	
	
	public float[] setCoords() {
		float coords[] = { 0.0f, -0.0f, 0.195234f, 0.0f, 0.5f, 5.0E-7f, 0.5f, 0.0f, 0.0f };
		return coords; }
		public float[] setNormals() {
		float normals[] = { -0.341816f, -0.341815f, -0.8754f, -0.341816f, -0.341815f, -0.8754f, -0.341816f, -0.341815f, -0.8754f };
		return normals; }
	public float[] setAmb() {
		float[] amb = {1.0f, 0.0f, 0.0f};
		return amb; 
	}
	public float[] setDiff() {
		float[] diff = {1.0f, 0.0f, 0.0f};
		return diff; 
	}
	public float[] setSpec() {
		float[] spec = {0.5f, 0.5f, 0.5f};
		return spec; 
	}
	public float setShine() {
		float shine = 96;
		return shine; 
	}

	
}

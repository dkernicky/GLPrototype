package com.kernicky.gl_prototype.math;

public class Vector {
	public static float[] cross(float[] a, float[] b) {
		float[] f = new float[]{a[1]*b[2]-b[1]*a[2], -1.0f*(a[0]*b[2]-b[0]*a[2]), a[0]*b[1]-b[0]*a[1]};
		return f;
	}
	public static float dot(float[] a, float[] b) {
		return a[0]*b[0] + a[1]*b[1] + a[2]*b[2];
	}
	public static float[] multiply(float a, float[] b) {
		return new float[]{a*b[0], a*b[1], a*b[2]};
	}
	public static float[] normalize(float[] a) {
		float mag = (float) Math.sqrt(a[0]*a[0]+a[1]*a[1]+a[2]*a[2]);
		a[0] /= mag;
		a[1] /= mag;
		a[2] /= mag;
		return a;
	}
}

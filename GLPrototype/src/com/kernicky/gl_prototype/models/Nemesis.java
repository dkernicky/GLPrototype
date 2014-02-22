package com.kernicky.gl_prototype.models;

import com.kernicky.gl_prototype.ShootEmUpScene;
import com.kernicky.gl_prototype.math.MatrixOp;
import com.kernicky.gl_prototype.math.Quaternion;
import com.kernicky.gl_prototype.math.Vector;


public class Nemesis extends Model {
	public float health = 20;
	public Nemesis(float x, float y, float z) {
		this.position = new Quaternion(x, y, z, 0);
		position.normalize();
		addTransform(new Transformation(position.x, position.y, position.z));
		addTransform(new Transformation(MatrixOp.identity()));
		addTransform(new Transformation(MatrixOp.identity()));
		addTransform(new Transformation(90.0f, 1, 0, 0));
		addTransform(new Transformation(0.8f));
		this.setData(setCoords(), setNormals(), setAmb(), setDiff(), setSpec(), setShine());
	}
	public Nemesis() {
		float[] f = new float[4];
		f[0] = (float) Math.random();
		f[1] = (float) Math.random();
		f[2] = (float) Math.random();
		f[3] = 0;
		this.position = new Quaternion(f);
		position.normalize();
		position.multiply(7);
		addTransform(new Transformation(position.x, position.y, position.z));
		addTransform(new Transformation(MatrixOp.identity()));
		addTransform(new Transformation(MatrixOp.identity()));
		addTransform(new Transformation(90.0f, 1, 0, 0));
		addTransform(new Transformation(0.8f));
		this.setData(setCoords(), setNormals(), setAmb(), setDiff(), setSpec(), setShine());
	}
	
	public float[] setCoords() {
		float coords[] = { 0.33333334f, 0.0f, 0.0f, 0.0f, 0.098962f, 0.0f, 0.16666667f, 0.0f, 0.069096334f, 0.33333334f, 0.0f, 0.0f, 0.16943534f, 0.046559334f, -0.10678733f, 0.0f, 0.098962f, 0.0f, 0.16943534f, 0.046559334f, -0.10678733f, 0.33333334f, 0.0f, 0.0f, 0.06544167f, 0.0f, -0.6666667f, 0.0f, 0.098962f, 0.0f, 0.0f, 0.0f, 0.33333334f, 0.16666667f, 0.0f, 0.069096334f, 0.33333334f, 0.0f, 0.0f, 0.16666667f, 0.0f, 0.069096334f, 0.0f, -0.098962f, 0.0f, 0.33333334f, 0.0f, 0.0f, 0.0f, -0.098962f, 0.0f, 0.16943534f, -0.046559334f, -0.10678733f, 0.16943534f, -0.046559334f, -0.10678733f, 0.06544167f, 0.0f, -0.6666667f, 0.33333334f, 0.0f, 0.0f, 0.0f, -0.098962f, 0.0f, 0.16666667f, 0.0f, 0.069096334f, 0.0f, 0.0f, 0.33333334f, 0.0f, 0.0f, 0.08676133f, 0.16943534f, -0.046559334f, -0.10678733f, 0.0f, -0.098962f, 0.0f, 0.0f, 0.0f, 0.08676133f, 0.16943534f, 0.046559334f, -0.10678733f, 0.0f, 0.098962f, 0.0f, 0.0f, 0.0f, 0.08676133f, 0.16943534f, -0.046559334f, -0.10678733f, 0.16943534f, 0.046559334f, -0.10678733f, 0.16943534f, -0.046559334f, -0.10678733f, 0.16943534f, 0.046559334f, -0.10678733f, 0.06544167f, 0.0f, -0.6666667f, -0.33333334f, 0.0f, 0.0f, -0.16666667f, 0.0f, 0.069096334f, 0.0f, 0.098962f, 0.0f, -0.33333334f, 0.0f, 0.0f, 0.0f, 0.098962f, 0.0f, -0.16943534f, 0.046559334f, -0.10678733f, -0.16943534f, 0.046559334f, -0.10678733f, -0.06544167f, 0.0f, -0.6666667f, -0.33333334f, 0.0f, 0.0f, 0.0f, 0.098962f, 0.0f, -0.16666667f, 0.0f, 0.069096334f, 0.0f, 0.0f, 0.33333334f, -0.33333334f, 0.0f, 0.0f, 0.0f, -0.098962f, 0.0f, -0.16666667f, 0.0f, 0.069096334f, -0.33333334f, 0.0f, 0.0f, -0.16943534f, -0.046559334f, -0.10678733f, 0.0f, -0.098962f, 0.0f, -0.16943534f, -0.046559334f, -0.10678733f, -0.33333334f, 0.0f, 0.0f, -0.06544167f, 0.0f, -0.6666667f, 0.0f, -0.098962f, 0.0f, 0.0f, 0.0f, 0.33333334f, -0.16666667f, 0.0f, 0.069096334f, 0.0f, 0.0f, 0.08676133f, 0.0f, -0.098962f, 0.0f, -0.16943534f, -0.046559334f, -0.10678733f, 0.0f, 0.0f, 0.08676133f, 0.0f, 0.098962f, 0.0f, -0.16943534f, 0.046559334f, -0.10678733f, 0.0f, 0.0f, 0.08676133f, -0.16943534f, 0.046559334f, -0.10678733f, -0.16943534f, -0.046559334f, -0.10678733f, -0.16943534f, -0.046559334f, -0.10678733f, -0.06544167f, 0.0f, -0.6666667f, -0.16943534f, 0.046559334f, -0.10678733f };
		return coords; }
		public float[] setNormals() {
		float normals[] = { 0.234639f, 0.790332f, 0.565969f, 0.234639f, 0.790332f, 0.565969f, 0.234639f, 0.790332f, 0.565969f, 0.284558f, 0.958474f, -0.018847f, 0.284558f, 0.958474f, -0.018847f, 0.284558f, 0.958474f, -0.018847f, 0.35547f, 0.923709f, -0.142841f, 0.35547f, 0.923709f, -0.142841f, 0.35547f, 0.923709f, -0.142841f, 0.411292f, 0.873807f, 0.259421f, 0.411292f, 0.873807f, 0.259421f, 0.411292f, 0.873807f, 0.259421f, 0.234639f, -0.790332f, 0.565969f, 0.234639f, -0.790332f, 0.565969f, 0.234639f, -0.790332f, 0.565969f, 0.284558f, -0.958474f, -0.018847f, 0.284558f, -0.958474f, -0.018847f, 0.284558f, -0.958474f, -0.018847f, 0.35547f, -0.923709f, -0.142841f, 0.35547f, -0.923709f, -0.142841f, 0.35547f, -0.923709f, -0.142841f, 0.411292f, -0.873807f, 0.259421f, 0.411292f, -0.873807f, 0.259421f, 0.411292f, -0.873807f, 0.259421f, -0.561064f, 0.545696f, -0.622433f, -0.561064f, 0.545696f, -0.622433f, -0.561064f, 0.545696f, -0.622433f, 0.561064f, 0.545696f, 0.622433f, 0.561064f, 0.545696f, 0.622433f, 0.561064f, 0.545696f, 0.622433f, 0.752422f, 0.0f, 0.658681f, 0.752422f, 0.0f, 0.658681f, 0.752422f, 0.0f, 0.658681f, -0.983184f, 0.0f, 0.182619f, -0.983184f, 0.0f, 0.182619f, -0.983184f, 0.0f, 0.182619f, -0.234639f, 0.790332f, 0.565969f, -0.234639f, 0.790332f, 0.565969f, -0.234639f, 0.790332f, 0.565969f, -0.284558f, 0.958474f, -0.018847f, -0.284558f, 0.958474f, -0.018847f, -0.284558f, 0.958474f, -0.018847f, -0.35547f, 0.923709f, -0.142841f, -0.35547f, 0.923709f, -0.142841f, -0.35547f, 0.923709f, -0.142841f, -0.411292f, 0.873807f, 0.259421f, -0.411292f, 0.873807f, 0.259421f, -0.411292f, 0.873807f, 0.259421f, -0.234639f, -0.790332f, 0.565969f, -0.234639f, -0.790332f, 0.565969f, -0.234639f, -0.790332f, 0.565969f, -0.284558f, -0.958474f, -0.018847f, -0.284558f, -0.958474f, -0.018847f, -0.284558f, -0.958474f, -0.018847f, -0.35547f, -0.923709f, -0.142841f, -0.35547f, -0.923709f, -0.142841f, -0.35547f, -0.923709f, -0.142841f, -0.411292f, -0.873807f, 0.259421f, -0.411292f, -0.873807f, 0.259421f, -0.411292f, -0.873807f, 0.259421f, 0.561064f, 0.545696f, -0.622433f, 0.561064f, 0.545696f, -0.622433f, 0.561064f, 0.545696f, -0.622433f, -0.561064f, 0.545696f, 0.622433f, -0.561064f, 0.545696f, 0.622433f, -0.561064f, 0.545696f, 0.622433f, -0.752422f, 0.0f, 0.658681f, -0.752422f, 0.0f, 0.658681f, -0.752422f, 0.0f, 0.658681f, 0.983184f, 0.0f, 0.182619f, 0.983184f, 0.0f, 0.182619f, 0.983184f, 0.0f, 0.182619f };
		return normals; }
	public float[] setAmb() {
		float amb[] = {0.3f, 0.0f, 0.0f};
		return amb;
	}
	public float[] setDiff() {
		float diff[] = {1.0f, 0.0f, 0.0f};

		return diff;
	}
	public float[] setSpec() {
		float spec[] = {0.5f, 0.5f, 0.5f};
		return spec;
	}
	public float setShine() {
		float shine = 300;
		return shine;
	}
	
	@Override
	public void updateKinematics() {
		time ++;
		//applyRot(.04f, new float[]{0, 1, 0});
		float[] a = ShootEmUpScene.shipQ.toFloat();
		a = Vector.normalize(a);
		float[] b = position.toFloat();
		b = Vector.normalize(b);
		
		float angle = Vector.dot(a, b);

		if(angle > 0) {
			seek(ShootEmUpScene.shipQ);
		}
		else {
			//wander();
			seek(ShootEmUpScene.shipQ);
		}
		if(time > 10) {
		this.amb = new float[]{0.3f, 0, 0};
		time = 0;
		}
	}
	

}

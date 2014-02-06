package com.kernicky.gl_prototype;

import java.util.ArrayList;

import android.opengl.Matrix;

import com.kernicky.gl_prototype.models.Base;
import com.kernicky.gl_prototype.models.BlackIco;
import com.kernicky.gl_prototype.models.F;
import com.kernicky.gl_prototype.models.GoldenShip;
import com.kernicky.gl_prototype.models.Lamp;
import com.kernicky.gl_prototype.models.Model;
import com.kernicky.gl_prototype.models.O;
import com.kernicky.gl_prototype.models.PhongCube;
import com.kernicky.gl_prototype.models.Transformation;

public class ShootEmUpScene extends Scene {
	private double lastUpdate = 0.0f;
	private float frameRate = 30.0f;

	// initial view and proj matrices
	private float[] mView = new float[16];
	private float[] mProj = new float[16];

	private float[] mViewerPos = { 0.0f, 0.0f, 2.0f, 0.0f };
	private float[] mCenterPos = { 0.0f, 0.0f, 0.0f };
	private float[] mUpV = { 0.0f, 1.0f, 0.0f };

	private ArrayList<Model> modelList = new ArrayList<Model>();
	private ArrayList<Lamp> lightList = new ArrayList<Lamp>();
	private ArrayList<Float> lightPosList = new ArrayList<Float>();

	public ShootEmUpScene() {
		PhongCube cube = new PhongCube();
		GoldenShip ship = new GoldenShip();
		F f1 = new F();
		F f2 = new F();
		O o = new O();

		// ship.addTransform(new Transformation(1.5f, 0.0f, 0.0f, 0, 30));
		// ship.addTransform(new Transformation(-3.0f, 0.0f, 0.0f, 30, 90));
		// ship.addTransform(new Transformation(1.5f, 0.0f, 0.0f, 90, 120));

		// ship.addTransform(new Transformation(20.0f, 0, 1, 0, 0, 30));
		// ship.addTransform(new Transformation(-45.0f, 0, 1, 0, 30, 90));
		// ship.addTransform(new Transformation(20.0f, 0, 1, 0, 90, 120));

		// ship.addTransform(new Transformation(-40.0f, 0, 0, 1, 15, 30));
		// ship.addTransform(new Transformation(40.0f, 0, 0, 1, 30, 45));
		// ship.addTransform(new Transformation(40.0f, 0, 0, 1, 75, 90));
		// ship.addTransform(new Transformation(-40.0f, 0, 0, 1, 90, 105));

		// ship.addTransform(new Transformation(-180.0f, 0, 0, 1, 0, 30));
		// ship.addTransform(new Transformation(360.0f, 0, 0, 1, 30, 90));
		// ship.addTransform(new Transformation(-180.0f, 0, 0, 1, 90, 120));

		ship.addTransform(new Transformation(0.0f, 1, 0, 0, 0, 360));
		ship.addTransform(new Transformation(0.0f, 1, 0, 0, 0, 360));
		ship.addTransform(new Transformation(ship.angle, 0, 0, 1));
		ship.addTransform(new Transformation(0, 0, 1.f));
		ship.addTransform(new Transformation(90.0f, 1, 0, 0));
		ship.addTransform(new Transformation(0.25f));


		Lamp l1 = new Lamp(0.0f, 0.0f, 0.0f);
		Lamp l2 = new Lamp(0.0f, 0.0f, 0.0f);
		Lamp l3 = new Lamp(0.0f, 0.0f, 0.0f);
		Lamp l4 = new Lamp(0.0f, 0.0f, 0.0f);

		BlackIco b = new BlackIco();
		//Base b = new Base();
		//b.addTransform(new Transformation(0.0f, 0.0f, -4.0f));
		b.addTransform(new Transformation(360.0f, 1, 0, 0, 0, 360));
		//b.addTransform(new Transformation(90.0f, 0, 0, 1));
		b.addTransform(new Transformation(2.0f));

		l1.addTransform(new Transformation(360.0f, 0, 0, 1, 0, 120));
		l1.addTransform(new Transformation(.5f, .5f, 1.0f));
		l1.addTransform(new Transformation(0.025f));
		lightList.add(l1);

		l2.addTransform(new Transformation(360.0f, 0, 0, 1, 0, 120));
		l2.addTransform(new Transformation(.5f, -.5f, 1.0f));
		l2.addTransform(new Transformation(0.025f));
		lightList.add(l2);

		l3.addTransform(new Transformation(360.0f, 0, 0, 1, 0, 120));
		l3.addTransform(new Transformation(-.5f, .5f, 1.0f));
		l3.addTransform(new Transformation(0.025f));
		lightList.add(l3);

		l4.addTransform(new Transformation(360.0f, 0, 0, 1, 0, 120));
		l4.addTransform(new Transformation(-.5f, -.5f, 1.0f));
		l4.addTransform(new Transformation(0.025f));
		lightList.add(l4);

		modelList.add(ship);
		//modelList.add(b);
	}

	public void draw() {
		double currentTime = System.currentTimeMillis();
		if (true || currentTime - lastUpdate >= 1000.0f / frameRate) {

			Matrix.setLookAtM(mView, 0, mViewerPos[0], mViewerPos[1],
					mViewerPos[2], mCenterPos[0], mCenterPos[1], mCenterPos[2],
					mUpV[0], mUpV[1], mUpV[2]);

			lightPosList.clear();

			for (int n = 0; n < lightList.size(); n++) {
				lightList.get(n).draw(mView, mProj);

				for (int m = 0; m < lightList.get(n).getMELightPos().length - 1; m++) {
					lightPosList.add(lightList.get(n).getMELightPos()[m]);
				}
			}
			float[] lightPos = new float[lightPosList.size()];
			for (int n = 0; n < lightPos.length; n++) {
				lightPos[n] = lightPosList.get(n);
			}

			for (Model m : modelList) {
				if(m.getClass().equals(GoldenShip.class)) {
					m.angle = MyGLSurfaceView.angle;
					//m.transList.remove(0); 
					//m.addTransformToFront((new Transformation(m.angle, 0, 0, 1)));
					m.transList.set(2,new Transformation(m.angle, 0, 0, 1));
//					if(MyGLSurfaceView.dx > 0.0f && MyGLSurfaceView.dy > 0.0f) 
//						m.transList.add(0, new Transformation(15.0f,MyGLSurfaceView.dx, MyGLSurfaceView.dy, 0));
					float rotX = m.rotX;
					float rotY = m.rotY;
					m.transList.set(0, new Transformation(m.rotX = (m.rotX+MyGLSurfaceView.dx)%360.0f, 1, 0, 0));
					m.transList.set(1, new Transformation(m.rotY = (m.rotY+MyGLSurfaceView.dy)%360.0f, 0, 1, 0));
					

					//m.addTransformToFront((new Transformation(m.angle, 0, 0, 1)));
				}
				if(m.getClass().equals(Base.class)) {
//					m.transList.remove(0); 
//					m.transList.remove(0); 
//					
//					m.addTransformToFront((new Transformation(360.0f, MyGLSurfaceView.dx, MyGLSurfaceView.dy, 0, 0, 360)));
//					m.addTransformToFront((new Transformation(0.0f, 0.0f, -4.0f)));
					//m.transList.set(1, new Transformation(360.0f, MyGLSurfaceView.dx, MyGLSurfaceView.dy, 0, 0, 360));
					//m.transList.add(1, new Transformation(1.0f, MyGLSurfaceView.dx, MyGLSurfaceView.dy, 0));



					//m.addTransformToFront((new Transformation(m.angle, 0, 0, 1)));
				}
				m.draw(mView, mProj, lightPos);
			}
			lastUpdate = currentTime;
		}
	}

	public void setMProj(float[] mProj) {
		this.mProj = mProj;
	}

	public float[] getMProj() {
		return this.mProj;
	}
}

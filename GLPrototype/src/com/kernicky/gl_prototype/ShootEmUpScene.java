package com.kernicky.gl_prototype;

import java.util.ArrayList;

import android.opengl.Matrix;

import com.kernicky.gl_prototype.math.Quaternion;
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

	public static float[] mViewerPos = { 0.0f, 0.0f, 3.0f, 1.0f };
	private float[] mCenterPos = { 0.0f, 0.0f, 0.0f, 1.0f };
	public static float[] mUpV = { 0.0f, 1.0f, 0.0f, 1.0f };
	public static float[] shipPosition = { 0.0f, 0.0f, 1.0f, 0.0f };
	public static float[] shipAngle = {1, 0, 0, 0,
									   0, 1, 0, 0,
									   0, 0, 1, 0,
									   0, 0, 0, 1};
	public static Quaternion viewQ = new Quaternion(0.0f, 0.0f, 3.0f, 0.0f);
	public static Quaternion shipQ = new Quaternion(0.0f, 0.0f, 1.0f, 0.0f);
	public static Quaternion shipDirQ = new Quaternion(0.0f, 1.0f, 0.0f, 0.0f);


	private ArrayList<Model> modelList = new ArrayList<Model>();
	private ArrayList<Lamp> lightList = new ArrayList<Lamp>();
	private ArrayList<Float> lightPosList = new ArrayList<Float>();

	public ShootEmUpScene() {
		//PhongCube cube = new PhongCube();
		GoldenShip ship = new GoldenShip();
		F f1 = new F();
		F f2 = new F();
		O o = new O();


//		ship.addTransform(new Transformation(0.0f, 1, 0, 0));
//		ship.addTransform(new Transformation(0.0f, 1, 0, 0));
//		ship.addTransform(new Transformation(0.0f, 1, 0, 0));
		ship.addTransform(new Transformation(shipPosition[0], shipPosition[1], shipPosition[2]));
		ship.addTransform(new Transformation(ship.angle));


		ship.addTransform(new Transformation(90.0f, MyGLSurfaceView.x_axis[0], MyGLSurfaceView.x_axis[1], MyGLSurfaceView.x_axis[2]));
		ship.addTransform(new Transformation(0.2f));


		Lamp l1 = new Lamp(0.0f, 0.0f, 0.0f);
		Lamp l2 = new Lamp(0.0f, 0.0f, 0.0f);
		Lamp l3 = new Lamp(0.0f, 0.0f, 0.0f);
		Lamp l4 = new Lamp(0.0f, 0.0f, 0.0f);
		Lamp l5 = new Lamp(0.0f, 0.0f, 0.0f);
		Lamp l6 = new Lamp(0.0f, 0.0f, 0.0f);
		Lamp l7 = new Lamp(0.0f, 0.0f, 0.0f);
		Lamp l8 = new Lamp(0.0f, 0.0f, 0.0f);
		
		//BlackIco b = new BlackIco();
		Base b = new Base();
		//PhongCube b = new PhongCube();
		
		b.addTransform(new Transformation(0.0f, 1, 0, 0, 0, 360));
		b.addTransform(new Transformation(0.0f, 1, 0, 0, 0, 360));
		b.addTransform(new Transformation(1.9f));


		l1.addTransform(new Transformation(0, 0, 0, 1));
		l1.addTransform(new Transformation(0, 0, 0));
		l1.addTransform(new Transformation(1f, 1f, 1f));
		l1.addTransform(new Transformation(0.025f));
		lightList.add(l1);

		l2.addTransform(new Transformation(0, 0, 0, 1));
		l2.addTransform(new Transformation(0, 0, 0));
		l2.addTransform(new Transformation(-1f, 1f, 1f));
		l2.addTransform(new Transformation(0.025f));
		lightList.add(l2);

		l3.addTransform(new Transformation(0, 0, 0, 1));
		l3.addTransform(new Transformation(0, 0, 0));
		l3.addTransform(new Transformation(-1f, -1f, 1.0f));
		l3.addTransform(new Transformation(0.025f));
		lightList.add(l3);

		l4.addTransform(new Transformation(0, 0, 0, 1));
		l4.addTransform(new Transformation(0, 0, 0));
		l4.addTransform(new Transformation(1, -1f, 1.0f));
		l4.addTransform(new Transformation(0.025f));
		lightList.add(l4);
		
		l5.addTransform(new Transformation(0, 0, 0, 1));
		l5.addTransform(new Transformation(0, 0, 0));
		l5.addTransform(new Transformation(1f, 1f, -1.0f));
		l5.addTransform(new Transformation(0.025f));
		lightList.add(l5);

		l6.addTransform(new Transformation(0, 0, 0, 1));
		l6.addTransform(new Transformation(0, 0, 0));
		l6.addTransform(new Transformation(-1f, 1f, -1.0f));
		l6.addTransform(new Transformation(0.025f));
		lightList.add(l6);

		l7.addTransform(new Transformation(0, 0, 0, 1));
		l7.addTransform(new Transformation(0, 0, 0));
		l7.addTransform(new Transformation(-1f, -1.f, -1.0f));
		l7.addTransform(new Transformation(0.025f));
		lightList.add(l7);

		l8.addTransform(new Transformation(0, 0, 0, 1));
		l8.addTransform(new Transformation(0, 0, 0));
		l8.addTransform(new Transformation(1f, -1f, -1.0f));
		l8.addTransform(new Transformation(0.025f));
		lightList.add(l8);

		modelList.add(ship);
		modelList.add(b);
	}

	public void draw() {
		double currentTime = System.currentTimeMillis();
		if (true || currentTime - lastUpdate >= 1000.0f / frameRate) {

			//viewQ = new Quaternion(0, 0, 2, 0);
			//MyGLSurfaceView.y_axis = new float[]{0, 1, 0, 0};
			Matrix.setLookAtM(mView, 0, viewQ.x, viewQ.y,
					viewQ.z, mCenterPos[0], mCenterPos[1], mCenterPos[2],
					MyGLSurfaceView.y_axis[0], MyGLSurfaceView.y_axis[1], MyGLSurfaceView.y_axis[2]);
//			System.out.println("view: " + mViewerPos[0] + " " + mViewerPos[1] + " " + mViewerPos[2]);
//			System.out.println("ship: " + shipPosition[0] + " " + shipPosition[1] + " " +shipPosition[2]);
//			System.out.println("up: " + mUpV[0] + " " + mUpV[1] + " " +mUpV[2]);
			
			lightPosList.clear();

			for (int n = 0; n < lightList.size(); n++) {
				lightList.get(n).transList.set(0, new Transformation(MyGLSurfaceView.angleRight, 0, 0, 1));
				//lightList.get(n).transList.set(1, new Transformation(MyGLSurfaceView.angleRight, shipPosition[0], shipPosition[1], shipPosition[2]));

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
					m.angle = MyGLSurfaceView.angleLeft;
					//m.transList.set(1,new Transformation(90, shipAngle[0], shipAngle[1], shipAngle[2]));
					m.transList.set(0, new Transformation(shipQ.x, shipQ.y, shipQ.z));
					m.transList.set(1, new Transformation(shipAngle));
				}
				if(m.getClass().equals(BlackIco.class) || m.getClass().equals(Base.class) || m.getClass().equals(PhongCube.class)) {
//					float rotX = m.rotX;
//					float rotY = m.rotY;
//					//System.out.println(MyGLSurfaceView.dyLeft);
//					
//					m.rotX = (m.rotX-2*MyGLSurfaceView.dyLeft)%360.0f;
//					m.rotY = (m.rotY-2*MyGLSurfaceView.dxLeft)%360.0f;
//					while(m.rotX < -180.0f) {
//						m.rotX += 360.0f;
//					}
//					while(m.rotY < 0) {
//						m.rotY += 360.0f;
//					}
//					m.transList.set(0, new Transformation(m.rotX, 1, 0, 0));
//					m.transList.set(1, new Transformation(m.rotY, 0, 1, 0));

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

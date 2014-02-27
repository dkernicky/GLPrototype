package com.kernicky.gl_prototype;

import java.util.ArrayList;

import android.opengl.Matrix;

import com.kernicky.gl_prototype.math.MatrixOp;
import com.kernicky.gl_prototype.math.Quaternion;
import com.kernicky.gl_prototype.math.Vector;
import com.kernicky.gl_prototype.models.*;

public class ShootEmUpScene extends Scene {
	private double lastUpdate = 0.0f;
	private float frameRate = 30.0f;

	// initial view and proj matrices
	private float[] mView = new float[16];
	private float[] mProj = new float[16];

	//public static float[] mViewerPos = { 0.0f, 0.0f, 3.0f, 1.0f };
	private float[] mCenterPos = { 0.0f, 0.0f, 0.0f, 1.0f };
	public static float[] mUpV = { 0.0f, 1.0f, 0.0f, 1.0f };
	//public static float[] shipPosition = { 0.0f, 0.0f, 2.0f, 0.0f };
	public static float[] shipAngle = {1, 0, 0, 0,
									   0, 1, 0, 0,
									   0, 0, 1, 0,
									   0, 0, 0, 1};
	public static float[] staticAngle = {1, 0, 0, 0,
		   0, 1, 0, 0,
		   0, 0, 1, 0,
		   0, 0, 0, 1};
	public static Quaternion viewQ = new Quaternion(0.0f, 0.0f, 10.0f, 0.0f);
	public static Quaternion shipQ = new Quaternion(0.0f, 0.0f, 7.0f, 0.0f);
	public static Quaternion shipDirQ = new Quaternion(0.0f, 1.0f, 0.0f, 0.0f);
	public static final float viewDist = 10.0f;
	public static final float shipDist = 7.0f;
	
	public static Quaternion optionQ = new Quaternion(0, 0f, 8f, 0);
	public static float[] optionAngle = MatrixOp.identity();

	private ArrayList<ReflectiveModel> modelList = new ArrayList<ReflectiveModel>();
	private ArrayList<Nemesis> enemyList = new ArrayList<Nemesis>();
	private ArrayList<Projectile> projectileList = new ArrayList<Projectile>();

	private ArrayList<Lamp> lightList = new ArrayList<Lamp>();
	private ArrayList<Float> lightPosList = new ArrayList<Float>();
	private double lastTime = 0.0f;
	private int updateCount = 0;
	private NewIco ico = new NewIco();
	private ArrayList<Num>numList = new ArrayList<Num>();
	private int score = 0;

	public ShootEmUpScene() {
		GoldenShip ship = new GoldenShip();

		for(int n = 0; n < 9; n ++) {
			Num num = new Num(0, n);
			num.addTransform(new Transformation(MatrixOp.identity()));
			num.addTransform(new Transformation(MatrixOp.identity()));
			numList.add(num);
		}
		
		//enemyList.add(new Nemesis(0, 0, 1));
		for(int n = 0; n < 15; n ++) {
			enemyList.add(new Nemesis());
		}
	
		ship.addTransform(new Transformation(shipQ.x, shipQ.y, shipQ.z));
		ship.addTransform(new Transformation(MatrixOp.identity()));
		ship.addTransform(new Transformation(MatrixOp.identity()));
		ship.addTransform(new Transformation(90.0f, MyGLSurfaceView.x_axis[0], MyGLSurfaceView.x_axis[1], MyGLSurfaceView.x_axis[2]));
		//ship.addTransform(new Transformation(0.2f));
		ship.addTransform(new Transformation(0.5f));
		modelList.add(ship);

//		Lamp l1 = new Lamp(0.0f, 0.0f, 0.0f);
//		Lamp l2 = new Lamp(0.0f, 0.0f, 0.0f);
//		Lamp l3 = new Lamp(0.0f, 0.0f, 0.0f);
//		Lamp l4 = new Lamp(0.0f, 0.0f, 0.0f);
//		Lamp l5 = new Lamp(0.0f, 0.0f, 0.0f);
//		Lamp l6 = new Lamp(0.0f, 0.0f, 0.0f);
//		Lamp l7 = new Lamp(0.0f, 0.0f, 0.0f);
//		Lamp l8 = new Lamp(0.0f, 0.0f, 0.0f);
		Lamp lo = new Lamp(0.0f, 0.0f, 0.0f);
		
		NewIco b = new NewIco();
		//Base b = new Base();
		//Frame b = new Frame();
		//PhongCube b = new PhongCube();
		//b.addTransform(new Transformation(0, 0, -5, 0));
		b.addTransform(new Transformation(0.0f, 1, 0, 0, 0, 360));
		b.addTransform(new Transformation(0.0f, 1, 0, 0, 0, 360));

		b.addTransform(new Transformation(12f));
		modelList.add(b);
		
		ico.addTransform(new Transformation(0.0f, 1, 0, 0, 0, 360));
		ico.addTransform(new Transformation(0.0f, 1, 0, 0, 0, 360));
		ico.addTransform(new Transformation(14f));	


		lo.addTransform(new Transformation(MatrixOp.identity()));
		lo.addTransform(new Transformation(MatrixOp.identity()));
		lo.addTransform(new Transformation(MatrixOp.identity()));
		lo.addTransform(new Transformation(0, .25f, 0));	
		lo.addTransform(new Transformation(0.05f));
		lightList.add(lo);
		
//		
//		l1.addTransform(new Transformation(0, 0, 0, 1));
//		l1.addTransform(new Transformation(0, 0, 0));
//		l1.addTransform(new Transformation(5f, 5f, 5f));
//		l1.addTransform(new Transformation(0.025f));
//		lightList.add(l1);
//
//		l2.addTransform(new Transformation(0, 0, 0, 1));
//		l2.addTransform(new Transformation(0, 0, 0));
//		l2.addTransform(new Transformation(-5f, 5f, 5f));
//		l2.addTransform(new Transformation(0.025f));
//		lightList.add(l2);
//
//		l3.addTransform(new Transformation(0, 0, 0, 1));
//		l3.addTransform(new Transformation(0, 0, 0));
//		l3.addTransform(new Transformation(-5f, -5f, 5f));
//		l3.addTransform(new Transformation(0.025f));
//		lightList.add(l3);
//
//		l4.addTransform(new Transformation(0, 0, 0, 1));
//		l4.addTransform(new Transformation(0, 0, 0));
//		l4.addTransform(new Transformation(5, -5f, 5f));
//		l4.addTransform(new Transformation(0.025f));
//		lightList.add(l4);
//		
//		l5.addTransform(new Transformation(0, 0, 0, 1));
//		l5.addTransform(new Transformation(0, 0, 0));
//		l5.addTransform(new Transformation(5f, 5f, -5f));
//		l5.addTransform(new Transformation(0.025f));
//		lightList.add(l5);
//
//		l6.addTransform(new Transformation(0, 0, 0, 1));
//		l6.addTransform(new Transformation(0, 0, 0));
//		l6.addTransform(new Transformation(-5f, 5f, -5f));
//		l6.addTransform(new Transformation(0.025f));
//		lightList.add(l6);
//
//		l7.addTransform(new Transformation(0, 0, 0, 1));
//		l7.addTransform(new Transformation(0, 0, 0));
//		l7.addTransform(new Transformation(-1f, -1.f, -1.0f));
//		l7.addTransform(new Transformation(0.025f));
//		lightList.add(l7);
//
//		l8.addTransform(new Transformation(0, 0, 0, 1));
//		l8.addTransform(new Transformation(0, 0, 0));
//		l8.addTransform(new Transformation(1f, -1f, -1.0f));
//		l8.addTransform(new Transformation(0.025f));
//		lightList.add(l8);


	}
	public void updateScore() {
		int newScore = score;
		for(int n = numList.size()-1; n >= 0; n --) {
			int newNum = newScore %10;
			if(numList.get(n).getNum() != newNum) {
				numList.set(n, new Num(newNum, n));
				numList.get(n).addTransform(new Transformation(MatrixOp.identity()));
			}
			newScore /= 10;
		}
	}
	
	public void updateTransforms() {

    	float[] inputVector = Vector.normalize(new float[]{1*MyGLSurfaceView.dxLeft, -1*MyGLSurfaceView.dyLeft, 0, 0});
    	Quaternion prevPosQ = new Quaternion(ShootEmUpScene.shipQ.toFloat());
    	prevPosQ.normalize();
    	
		Quaternion q = Quaternion.rotate(.04f*MyGLSurfaceView.dyLeft, prevPosQ.toFloat(), MyGLSurfaceView.x_axis);
		Quaternion r = Quaternion.rotate(.04f*MyGLSurfaceView.dxLeft, prevPosQ.toFloat(), MyGLSurfaceView.y_axis);
		float[] a = Quaternion.rotateTo(prevPosQ, q);
		float[] b = Quaternion.rotateTo(prevPosQ, r);
		float[] transform = MatrixOp.multiplyMM(a, b);
		
		ShootEmUpScene.shipQ = new Quaternion(MatrixOp.multiplyMV(transform, ShootEmUpScene.shipQ.toFloat()));
		ShootEmUpScene.optionQ = new Quaternion(MatrixOp.multiplyMV(transform, ShootEmUpScene.optionQ.toFloat()));
		ShootEmUpScene.viewQ = new Quaternion(MatrixOp.multiplyMV(transform, ShootEmUpScene.viewQ.toFloat()));
		MyGLSurfaceView.x_axis = MatrixOp.multiplyMV(transform, MyGLSurfaceView.x_axis);
		MyGLSurfaceView.y_axis = MatrixOp.multiplyMV(transform, MyGLSurfaceView.y_axis);
		MyGLSurfaceView.x_axis = Vector.normalize(MyGLSurfaceView.x_axis);
		MyGLSurfaceView.y_axis = Vector.normalize(MyGLSurfaceView.y_axis);		
		
		//ShootEmUpScene.shipQ.normalize();
		
		float[] shipDir = {0, 1, 0, 0};
		float[] optionDir = {0, 1, 0, 0};
		float[] t2 = MatrixOp.identity();
		if(MyGLSurfaceView.dxLeft != 0 && MyGLSurfaceView.dyLeft != 0) {
			t2 = Quaternion.rotateTo(shipDir, inputVector);
			ShootEmUpScene.staticAngle = t2;
		}
		float[] input2Vector = Vector.normalize(new float[]{1*MyGLSurfaceView.dxRight, -1*MyGLSurfaceView.dyRight, 0, 0});
		float[] lightRot = MatrixOp.identity();
		if(MyGLSurfaceView.dxRight != 0 && MyGLSurfaceView.dyRight != 0) {
			lightRot = Quaternion.rotateTo(optionDir, input2Vector);
			ShootEmUpScene.optionAngle = lightRot;
		}
		

		MyGLSurfaceView.currentTransform = MatrixOp.multiplyMM(transform, MyGLSurfaceView.currentTransform);		
		ShootEmUpScene.shipAngle = MyGLSurfaceView.currentTransform;
		
		Quaternion newPosQ = new Quaternion(shipQ.toFloat());
		newPosQ.normalize();
		viewQ = new Quaternion(newPosQ.toFloat());
		viewQ.multiply(viewDist);
		shipQ = new Quaternion(newPosQ.toFloat());
		shipQ.multiply(shipDist);
		
	}
	
	
	public void draw() {
		//score ++;
	



		
//		double currentUpdate = System.currentTimeMillis();
//		System.out.println(currentUpdate-lastUpdate);
//		lastUpdate = currentUpdate;
		
		if(updateCount > 1 && MyGLSurfaceView.rightTouch == true) {
			updateCount = 0;
			projectileList.add(new Projectile(shipQ.x, shipQ.y, shipQ.z, MatrixOp.multiplyMM(shipAngle, optionAngle), shipQ.toFloat()));

		}
		updateCount ++;
		
		updateTransforms();
		//enemyList.get(0).position.print();
		
		Matrix.setLookAtM(mView, 0, viewQ.x, viewQ.y,
				viewQ.z, mCenterPos[0], mCenterPos[1], mCenterPos[2],
				MyGLSurfaceView.y_axis[0], MyGLSurfaceView.y_axis[1], MyGLSurfaceView.y_axis[2]);

		lightPosList.clear();
		
		//MatrixOp.printM(optionAngle);
		
		lightList.get(0).transList.set(2, new Transformation(optionAngle));
		lightList.get(0).transList.set(1, new Transformation(shipAngle));
		lightList.get(0).transList.set(0, new Transformation(optionQ.x, optionQ.y, optionQ.z));
		lightList.get(0).draw(mView, mProj);
		
		for (int m = 0; m < lightList.get(0).getMELightPos().length - 1; m++) {
			lightPosList.add(lightList.get(0).getMELightPos()[m]);
		}



//		for (int n = 1; n < lightList.size(); n++) {
//			//lightList.get(n).transList.set(0, new Transformation(MyGLSurfaceView.angleRight, 0, 0, 1));
//			
//			//lightList.get(n).transList.set(1, new Transformation(MyGLSurfaceView.angleRight, shipPosition[0], shipPosition[1], shipPosition[2]));
//			lightList.get(n).draw(mView, mProj);
//
//			for (int m = 0; m < lightList.get(n).getMELightPos().length - 1; m++) {
//				lightPosList.add(lightList.get(n).getMELightPos()[m]);
//			}
//		}

		
		float[] lightPos = new float[lightPosList.size()];
		for (int n = 0; n < lightPos.length; n++) {
			lightPos[n] = lightPosList.get(n);
		}
		
		//lightPos = new float[]{1.0f, 1.0f, 0.0f};

		
		for (Nemesis m : enemyList) {
			m.updateKinematics();
			//System.out.println("e");
			m.draw(mView, mProj, lightPos);
			//System.out.println("e done");
//			for (Nemesis e : enemyList) {
//				float[] p1 = Vector.normalize(e.position.toFloat());
//				float[] e1 = Vector.normalize(m.position.toFloat());
//				if(Vector.dot(p1, e1) >= .999) {
//				
////					float[] f = new float[3];
////					f[0] = (float) Math.random();
////					f[1] = (float) Math.random();
////					f[2] = (float) Math.random();
//					
//					//f = Vector.normalize(f);
//					float[] a = shipQ.toFloat();
//					float[] b = m.position.toFloat();
//					a = Vector.normalize(a);
//					b = Vector.normalize(b);
//					float[] normalRotAxis = Vector.cross(a, b);
//					
//					b = m.position.toFloat();
//					b = Vector.normalize(b);
//					a = e.position.toFloat();
//					a = Vector.normalize(a);
//					float[] awayAxis = Vector.cross(b,  a);
//					e.applyRot(.35f, awayAxis);
//					//m.applyRot(-.04f, awayAxis);
//
//				}
//			}

		}

		
		for(Projectile p: projectileList) {
			//System.out.println(p.time);
			if(p.time > 40) {
				//p.destroyProgram();
				projectileList.remove(p);
				break;
			}
			p.updateKinematics();
			

			for(Nemesis m: enemyList) {
				float[] p1 = Vector.normalize(p.position.toFloat());
				float[] e1 = Vector.normalize(m.position.toFloat());

				if(Vector.dot(p1, e1) >= .99) {
					if(m.health <= 0 ) {
						enemyList.remove(m);
						score += 1;
						updateScore();
						p.destroyed = true;
						break;
					}
					m.setAmb(new float[]{0.0f, 1.0f, 0.0f});

					m.health -= 10.0;
					continue;

				}
			}

					
			if(p.destroyed) {
				projectileList.remove(p);
				enemyList.add(new Nemesis());
				break;
			}
			else {
				//System.out.println("p");

				p.draw(mView, mProj, lightPos);
			}

		}
		for (Num m : numList) {
			m.transList.set(0, new Transformation(shipAngle));
			//m.transList.set(1, new Transformation(staticAngle));
			m.draw(mView, mProj, lightPos);
		}

		for (EmissiveModel m : modelList) {
			if(m.getClass().equals(GoldenShip.class)) {
				//m.angle = MyGLSurfaceView.angleLeft;
				m.transList.set(0, new Transformation(shipQ.x, shipQ.y, shipQ.z));
				m.transList.set(1, new Transformation(shipAngle));
				m.transList.set(2, new Transformation(staticAngle));

			}
//			if(m.getClass().equals(Nemesis.class)) {
//				//m.angle = MyGLSurfaceView.angleLeft;
//				m.updateKinematics();
//
//			}
//			if(m.getClass().equals(Projectile.class)) {
//				//m.angle = MyGLSurfaceView.angleLeft;
//				m.updateKinematics();
//				System.out.println(m.time);
//				if(m.time > 40) {
//					modelList.remove(m);
//					break;
//				}
//
//			}
			m.draw(mView, mProj, lightPos);
		}
		ico.draw(mView, mProj, lightPos);
	
	}

	public void setMProj(float[] mProj) {
		this.mProj = mProj;
	}

	public float[] getMProj() {
		return this.mProj;
	}
}

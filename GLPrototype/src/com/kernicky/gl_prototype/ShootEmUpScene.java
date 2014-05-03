package com.kernicky.gl_prototype;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.opengl.Matrix;
import android.os.Environment;
import android.view.View;

import com.kernicky.gl_prototype.math.MatrixOp;
import com.kernicky.gl_prototype.math.Quaternion;
import com.kernicky.gl_prototype.math.Vector;
import com.kernicky.gl_prototype.models.Bomb;
import com.kernicky.gl_prototype.models.Boost;
import com.kernicky.gl_prototype.models.DPad;
import com.kernicky.gl_prototype.models.GoldenShip;
import com.kernicky.gl_prototype.models.Lamp;
import com.kernicky.gl_prototype.models.Life;
import com.kernicky.gl_prototype.models.Nemesis;
import com.kernicky.gl_prototype.models.NewIco;
import com.kernicky.gl_prototype.models.Num;
import com.kernicky.gl_prototype.models.Projectile;
import com.kernicky.gl_prototype.models.ReflectiveModel;
import com.kernicky.gl_prototype.models.ShootButton;
import com.kernicky.gl_prototype.models.Transformation;

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
	//public static Quaternion shipQ = new Quaternion(0.0f, 0.0f, 7.0f, 0.0f);
	public static Quaternion shipDirQ = new Quaternion(0.0f, 1.0f, 0.0f, 0.0f);
	public static final float viewDist = 10.0f;
	public static final float shipDist = 7.0f;
	
	public static Quaternion optionQ = new Quaternion(0, 0f, 8f, 0);
	public static float[] optionAngle = MatrixOp.identity();

	private ArrayList<ReflectiveModel> modelList = new ArrayList<ReflectiveModel>();
	public static ArrayList<Nemesis> enemyList = new ArrayList<Nemesis>();
	private ArrayList<Projectile> projectileList = new ArrayList<Projectile>();

	private ArrayList<Lamp> lightList = new ArrayList<Lamp>();
	private ArrayList<Float> lightPosList = new ArrayList<Float>();
	private double lastTime = 0.0f;
	private int updateCount = 0;
	public static NewIco ico = new NewIco();
	private static ArrayList<Num>numList = new ArrayList<Num>();
	public static int score = 0;
	
	public static GoldenShip ship = new GoldenShip();
	
	private ArrayList<Bomb> bombList = new ArrayList<Bomb>();
	private ArrayList<Boost> boostList = new ArrayList<Boost>();
	private ArrayList<Life> lifeList = new ArrayList<Life>();
	public static DPad pad = new DPad();
	public static ShootButton shoot = new ShootButton();
	
	private int numScreenShots = 0;

	public ShootEmUpScene() {
		//GoldenShip ship = new GoldenShip();

		for(int n = 0; n < 3; n ++) {
			bombList.add(new Bomb(n));
			boostList.add(new Boost(n));
			lifeList.add(new Life(n));
		}
		for(int n = 0; n < 9; n ++) {
			Num num = new Num(0, n);
			num.addTransform(new Transformation(MatrixOp.identity()));
			num.addTransform(new Transformation(MatrixOp.identity()));
			numList.add(num);
		}
		
		//enemyList.add(new Nemesis(0, 0, 1));
		for(int n = 0; n < 5; n ++) {
			enemyList.add(new Nemesis());
		}
	
		
		//modelList.add(ship);

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
		b.transList.set(0, new Transformation(0.0f, 1, 0, 0, 0, 360));
		b.transList.set(1, new Transformation(0.0f, 1, 0, 0, 0, 360));

		b.transList.set(2, new Transformation(12f));
		//modelList.add(b);
		
		ico.transList.set(0, new Transformation(0.0f, 1, 0, 0, 0, 360));
		ico.transList.set(1, new Transformation(0.0f, 1, 0, 0, 0, 360));
		ico.transList.set(2, new Transformation(14f));	


		lo.transList.set(0, new Transformation(MatrixOp.identity()));
		lo.transList.set(1, new Transformation(MatrixOp.identity()));
		lo.transList.set(2, new Transformation(MatrixOp.identity()));
		lo.transList.set(3, new Transformation(0, .25f, 0));	
		lo.transList.set(4, new Transformation(0.05f));
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
	public static void updateScore() {
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
		float dxMove = 0;
		float dyMove = 0;
		float dxShoot = 0;
		float dyShoot = 0;
		
		switch(MainActivity.moveInput) {
		case TILT:
			dxMove = MainActivity.orientation[2];
			dyMove = -1*MainActivity.orientation[1];
			break;
		case TOUCH_LEFT:
			dxMove = MyGLSurfaceView.dxLeft;
			dyMove = MyGLSurfaceView.dyLeft;
			break;
		case TOUCH_RIGHT:
			dxMove = MyGLSurfaceView.dxRight;
			dyMove = MyGLSurfaceView.dyRight;
			break;
		}
		switch(MainActivity.shootInput) {
		case TOUCH_LEFT:
			dxShoot = MyGLSurfaceView.dxLeft;
			dyShoot = MyGLSurfaceView.dyLeft;
			break;
		case TOUCH_RIGHT:
			dxShoot = MyGLSurfaceView.dxRight;
			dyShoot = MyGLSurfaceView.dyRight;
			break;
		}

		
		// if in the middle of a boost, keep last dx, dy
		if(ship.boostInProgress()) {
			dxMove = ship.dxPrev;
			dyMove = ship.dyPrev;
		}
		if(dxMove != 0 && dyMove != 0) {
			ship.dxPrev = dxMove;
			ship.dyPrev = dyMove;
		}
		
    	float[] inputVector = Vector.normalize(new float[]{1*dxMove, -1*dyMove, 0, 0});
    	Quaternion prevPosQ = new Quaternion(ship.position.toFloat());
    	prevPosQ.normalize();
    	
		Quaternion q = Quaternion.rotate(ship.getSpeed()*dyMove, prevPosQ.toFloat(), MyGLSurfaceView.x_axis);
		Quaternion r = Quaternion.rotate(ship.getSpeed()*dxMove, prevPosQ.toFloat(), MyGLSurfaceView.y_axis);
		float[] a = Quaternion.rotateTo(prevPosQ, q);
		float[] b = Quaternion.rotateTo(prevPosQ, r);
		float[] transform = MatrixOp.multiplyMM(a, b);
		
		ship.position = new Quaternion(MatrixOp.multiplyMV(transform, ship.position.toFloat()));
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
		if(dxMove != 0 && dyMove != 0) {
			t2 = Quaternion.rotateTo(shipDir, inputVector);
			ShootEmUpScene.staticAngle = t2;
		}
		float[] input2Vector = Vector.normalize(new float[]{1*dxShoot, -1*dyShoot, 0, 0});
		float[] lightRot = MatrixOp.identity();
		if(dxShoot != 0 && dyShoot != 0) {
			lightRot = Quaternion.rotateTo(optionDir, input2Vector);
			ShootEmUpScene.optionAngle = lightRot;
		}
		

		MyGLSurfaceView.currentTransform = MatrixOp.multiplyMM(transform, MyGLSurfaceView.currentTransform);		
		ShootEmUpScene.shipAngle = MyGLSurfaceView.currentTransform;
		
		Quaternion newPosQ = new Quaternion(ship.position.toFloat());
		newPosQ.normalize();
		viewQ = new Quaternion(newPosQ.toFloat());
		viewQ.multiply(viewDist);
		ship.position = new Quaternion(newPosQ.toFloat());
		ship.position.multiply(shipDist);
		
	}
	
	
	public void draw() {
	
//		double currentUpdate = System.currentTimeMillis();
//		System.out.println(currentUpdate-lastUpdate);
//		lastUpdate = currentUpdate;
		
		
		if(ico.radialInProgress()) {
			ico.setRadialEffect();
			if(ico.radialEffect >= 15) {
				if(bombList.size() > 0) {
					bombList.remove(bombList.size()-1);
					if(bombList.size() == 0) {
						bombList.add(new Bomb(0));
						bombList.add(new Bomb(1));
						bombList.add(new Bomb(2));
					}
				}
			}
	
		}
		
		
		if(updateCount > 1 && ((MyGLSurfaceView.rightTouch == true && MainActivity.shootInput == MainActivity.Input.TOUCH_RIGHT) || (MyGLSurfaceView.leftTouch == true && MainActivity.shootInput == MainActivity.Input.TOUCH_LEFT))) {
			updateCount = 0;
			projectileList.add(new Projectile(ship.position.x, ship.position.y, ship.position.z, MatrixOp.multiplyMM(shipAngle, optionAngle), ship.position.toFloat()));

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
		
		for (ReflectiveModel m : modelList) {
			m.draw(mView, mProj, lightPos);
		}
		
		if(MainActivity.moveInput == MainActivity.Input.TOUCH_LEFT || MainActivity.moveInput == MainActivity.Input.TOUCH_RIGHT) {
			pad.transList.set(0, new Transformation(shipAngle));
			pad.draw(mView, mProj, lightPos);
		}
		if(MainActivity.shootInput == MainActivity.Input.TOUCH_LEFT || MainActivity.shootInput == MainActivity.Input.TOUCH_RIGHT) {
			shoot.transList.set(0, new Transformation(shipAngle));
			shoot.draw(mView, mProj, lightPos);
		}
		
		for(Bomb bomb: bombList) {
			bomb.transList.set(0, new Transformation(shipAngle));
			bomb.draw(mView, mProj, lightPos);
		}
		for(Boost boost: boostList) {
			boost.transList.set(0, new Transformation(shipAngle));
			boost.draw(mView, mProj, lightPos);
		}
		for(Life life: lifeList) {
			life.transList.set(0, new Transformation(shipAngle));
			life.draw(mView, mProj, lightPos);
		}

		for (Nemesis m : enemyList) {
			if(ico.radialInProgress() && ico.radialEffect > 10) {
				m.setAmb(new float[]{0.0f, 1.0f, 0.0f});
				m.draw(mView, mProj, lightPos);
				enemyList.remove(m);
				score += 1;
				updateScore();
				enemyList.add(new Nemesis());
				break;
			}
			else if(ship.boostInProgress()){
				m.updateKinematics();
				float[] sp = Vector.normalize(ship.position.toFloat());
				float[] ep = Vector.normalize(m.position.toFloat());
				if(Vector.dot(sp, ep) >= .99) {
					m.setAmb(new float[]{0.0f, 1.0f, 0.0f});
					m.draw(mView, mProj, lightPos);
					enemyList.remove(m);
					score += 1;
					updateScore();
					enemyList.add(new Nemesis());
					break;
				}
				else
					m.draw(mView, mProj, lightPos);
			}
			else {
				m.updateKinematics();
				m.draw(mView, mProj, lightPos);			
			}

		}

		
		for(Projectile p: projectileList) {
			if(p.time > 40) {
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
				p.draw(mView, mProj, lightPos);
			}

		}
		for (Num m : numList) {
			m.transList.set(0, new Transformation(shipAngle));
			m.draw(mView, mProj, lightPos);
		}
		ship.transList.set(0, new Transformation(ship.position.x, ship.position.y, ship.position.z));
		ship.transList.set(1, new Transformation(shipAngle));
		ship.transList.set(2, new Transformation(staticAngle));
		ship.draw(mView, mProj, lightPos);

		boolean boostMagnitude = false;
		if(MainActivity.boostInput == MainActivity.Input.TOUCH_LEFT) {
			boostMagnitude = (MyGLSurfaceView.leftMagnitude > 150 && MyGLSurfaceView.leftMagnitude < 300);
		}
		else if(MainActivity.boostInput == MainActivity.Input.TOUCH_RIGHT) {
			boostMagnitude = (MyGLSurfaceView.rightMagnitude > 150 && MyGLSurfaceView.rightMagnitude < 300);
		}
		else if(MainActivity.boostInput == MainActivity.Input.TILT) {
			boostMagnitude = MainActivity.upAccel > 15;
		}
		if(boostMagnitude || ship.boostInProgress()) {

			ship.updateSpeed();
			if(ship.timeInBoost >= 36) {
				if(boostList.size() > 0) {
					boostList.remove(0);
					if(boostList.size() == 0) {
						boostList.add(new Boost(0));
						boostList.add(new Boost(1));
						boostList.add(new Boost(2));
					}
				}
			}
		}
		boolean bombMagnitude = false;
		if(MainActivity.bombInput == MainActivity.Input.TOUCH_LEFT) {
			bombMagnitude = (MyGLSurfaceView.leftMagnitude > 150 && MyGLSurfaceView.leftMagnitude < 300);
		}
		else if(MainActivity.bombInput == MainActivity.Input.TOUCH_RIGHT) {
			bombMagnitude = (MyGLSurfaceView.rightMagnitude > 150 && MyGLSurfaceView.rightMagnitude < 300);
		}
		else if(MainActivity.bombInput == MainActivity.Input.TILT) {
			bombMagnitude = MainActivity.upAccel > 15;
		}
		if(bombMagnitude) {
			ShootEmUpScene.ico.setRadialEffect();
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

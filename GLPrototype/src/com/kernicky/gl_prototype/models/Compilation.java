//package com.kernicky.gl_prototype.models;
//
//import android.opengl.Matrix;
//
//
//
//public class Compilation extends Model {
//
//	public Compilation() {
//		loadList();
//	}
//	
//	public void loadList() {
//		float[] t1 = { -1, 0, 0, 0, 
//						0, 1, 0, 0, 
//						0, 0, 1, 0,
//						0, 0, 0, 1};
//		float[] t2 = { 1,  0, 0, 0,
//					   0, -1, 0, 0, 
//					   0,  0, 1, 0, 
//					   0,  0, 0, 1};
//		float[] t3 = { -1, 0, 0, 0, 
//						0, -1, 0, 0, 
//						0, 0, 1, 0,
//						0, 0, 0, 1};
//		
//		Quadrant q1 = new Quadrant();
////		q1.addTransform(new Transformation(.05f, 0, 0, 0, 5));
////		q1.addTransform(new Transformation(-.05f, 0, 0, 5, 10));
////		q1.addTransform(new Transformation(0, .05f, 0, 10, 15));
////		q1.addTransform(new Transformation(0, -.05f, 0, 15, 20));
//		//q1.addTransform(new Transformation(90, 0, 0, 1));
//		q1.addTransform(new Transformation(0, 0, 7));
//
//		
//		Quadrant q2 = new Quadrant();
////		q2.addTransform(new Transformation(-.05f, 0, 0, 0, 5));
////		q2.addTransform(new Transformation(.05f, 0, 0, 5, 10));
////		q2.addTransform(new Transformation(0, .05f, 0, 10, 15));
////		q2.addTransform(new Transformation(0, -.05f, 0, 15, 20));
//		
//		q2.addTransform(new Transformation(0, 0, 7));
//		q2.addTransform(new Transformation(t1));
//		
//		Quadrant q3 = new Quadrant();
////		q3.addTransform(new Transformation(.05f, 0, 0, 0, 5));
////		q3.addTransform(new Transformation(-.05f, 0, 0, 5, 10));
////		q3.addTransform(new Transformation(0, -.05f, 0, 10, 15));
////		q3.addTransform(new Transformation(0, .05f, 0, 15, 20));
//		q3.addTransform(new Transformation(0, 0, 7));
//		q3.addTransform(new Transformation(t2));
//		
//		Quadrant q4 = new Quadrant();
////		q4.addTransform(new Transformation(-.05f, 0, 0, 0, 5));
////		q4.addTransform(new Transformation(.05f, 0, 0, 5, 10));
////		q4.addTransform(new Transformation(0, -.05f, 0, 10, 15));
////		q4.addTransform(new Transformation(0, .05f, 0, 15, 20));
//		q4.addTransform(new Transformation(0, 0, 7));
//		q4.addTransform(new Transformation(t3));
//		
//		this.subList.add(q1);
//		this.subList.add(q2);
//		this.subList.add(q3);
//		this.subList.add(q4);
//		
//
//	}
//	
//	@Override
//	public void draw(float[] mView, float[] mProj, float[] mLightPos) {	
//		float[] mModel = new float[16];
//		float[] mModelView = new float[16];
//		float[] mModelViewProj = new float[16];
//		
//		Matrix.setIdentityM(mModel, 0);
//		mModel = applyTransforms(mModel);
//
//		//System.out.println("****");
//		for(Model m: subList) {
//			//m.position.print();
//			m.draw(mModel, mView, mProj, mLightPos);
//		}
//
//	}
//	
//
//	
//}

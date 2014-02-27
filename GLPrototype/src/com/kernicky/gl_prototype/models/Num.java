package com.kernicky.gl_prototype.models;


import com.kernicky.gl_prototype.math.MatrixOp;

import android.opengl.Matrix;



public class Num extends ReflectiveModel {
	private int num;
	
	public Num(int n, int pos) {
		num = n;
		loadList(n, pos);
	}
	@Override
	public void initBuffers() {
		
	}
	
	public void loadList(int n, int pos) {
		float x = -5 + pos*.3f;
		float y = 2.65f;
		float z = 7;
		
		Rect r1 = new Rect();
		r1.addTransform(new Transformation(MatrixOp.identity()));
		r1.addTransform(new Transformation(MatrixOp.identity()));
		r1.addTransform(new Transformation(MatrixOp.identity()));
		r1.addTransform(new Transformation(x, y, z));
		r1.addTransform(new Transformation(90.0f, 1, 0, 0));
		r1.addTransform(new Transformation(.1f));
		
		Rect r2 = new Rect();
		r2.addTransform(new Transformation(MatrixOp.identity()));
		r2.addTransform(new Transformation(MatrixOp.identity()));
		r2.addTransform(new Transformation(MatrixOp.identity()));
		r2.addTransform(new Transformation(x-.2f, y, z));
		r2.addTransform(new Transformation(90.0f, 1, 0, 0));
		r2.addTransform(new Transformation(.1f));
		
		Rect r3 = new Rect();
		r3.addTransform(new Transformation(MatrixOp.identity()));
		r3.addTransform(new Transformation(MatrixOp.identity()));
		r3.addTransform(new Transformation(MatrixOp.identity()));
		r3.addTransform(new Transformation(x-.1f, y-.1f, z));
		r3.addTransform(new Transformation(90.0f, 0, 0, 1));
		r3.addTransform(new Transformation(90.0f, 1, 0, 0));
		r3.addTransform(new Transformation(.1f));
		
		Rect r4 = new Rect();
		r4.addTransform(new Transformation(MatrixOp.identity()));
		r4.addTransform(new Transformation(MatrixOp.identity()));
		r4.addTransform(new Transformation(MatrixOp.identity()));
		r4.addTransform(new Transformation(x-.1f, y+.1f, z));
		r4.addTransform(new Transformation(90.0f, 0, 0, 1));
		r4.addTransform(new Transformation(90.0f, 1, 0, 0));
		r4.addTransform(new Transformation(.1f));
		
		Rect r5 = new Rect();
		r5.addTransform(new Transformation(MatrixOp.identity()));
		r5.addTransform(new Transformation(MatrixOp.identity()));
		r5.addTransform(new Transformation(MatrixOp.identity()));
		r5.addTransform(new Transformation(x, y+.2f, z));
		r5.addTransform(new Transformation(90.0f, 1, 0, 0));
		r5.addTransform(new Transformation(.1f));
		
		Rect r6 = new Rect();
		r6.addTransform(new Transformation(MatrixOp.identity()));
		r6.addTransform(new Transformation(MatrixOp.identity()));
		r6.addTransform(new Transformation(MatrixOp.identity()));
		r6.addTransform(new Transformation(x-.2f, y+.2f, z));
		r6.addTransform(new Transformation(90.0f, 1, 0, 0));
		r6.addTransform(new Transformation(.1f));
		
		Rect r7 = new Rect();
		r7.addTransform(new Transformation(MatrixOp.identity()));
		r7.addTransform(new Transformation(MatrixOp.identity()));
		r7.addTransform(new Transformation(MatrixOp.identity()));
		r7.addTransform(new Transformation(x-.1f, y+.3f, z));
		r7.addTransform(new Transformation(90.0f, 0, 0, 1));
		r7.addTransform(new Transformation(90.0f, 1, 0, 0));
		r7.addTransform(new Transformation(.1f));
		
		switch(n) {
		case 0:
			this.subList.add(r1);
			this.subList.add(r2);
			this.subList.add(r3);
			this.subList.add(r5);
			this.subList.add(r6);
			this.subList.add(r7);
			break;
		case 1:
			this.subList.add(r1);
			this.subList.add(r5);
			//this.subList.add(r7);
			break;
		case 2:
			this.subList.add(r2);
			this.subList.add(r3);
			this.subList.add(r4);
			this.subList.add(r5);
			this.subList.add(r7);
			break;
		case 3:
			this.subList.add(r1);
			this.subList.add(r3);
			this.subList.add(r4);
			this.subList.add(r5);
			this.subList.add(r7);
			break;
		case 4:
			this.subList.add(r1);
			this.subList.add(r4);
			this.subList.add(r5);
			this.subList.add(r6);
			break;
		case 5:
			this.subList.add(r1);
			this.subList.add(r3);
			this.subList.add(r4);
			this.subList.add(r6);
			this.subList.add(r7);
			break;
		case 6:
			this.subList.add(r1);
			this.subList.add(r2);
			this.subList.add(r3);
			this.subList.add(r4);
			this.subList.add(r6);
			this.subList.add(r7);
			break;
		case 7:
			this.subList.add(r1);
			this.subList.add(r5);
			this.subList.add(r6);
			this.subList.add(r7);
			break;
		case 8:
			this.subList.add(r1);
			this.subList.add(r2);
			this.subList.add(r3);
			this.subList.add(r4);
			this.subList.add(r5);
			this.subList.add(r6);
			this.subList.add(r7);
			break;
		case 9:
			this.subList.add(r1);
			this.subList.add(r3);
			this.subList.add(r4);
			this.subList.add(r5);
			this.subList.add(r6);
			this.subList.add(r7);
			break;
		}


	}
	
	@Override
	public void draw(float[] mView, float[] mProj, float[] mLightPos) {	

		float[] mModel = new float[16];
		float[] mModelView = new float[16];
		float[] mModelViewProj = new float[16];
		
		Matrix.setIdentityM(mModel, 0);
		mModel = applyTransforms(mModel);

		//System.out.println("****");
		for(NewModel m: subList) {
			//m.position.print();
			//m.addTransformToFront(new Transformation(mModel));
			m.draw(mModel, mView, mProj, mLightPos);
		}
		currentTick = (currentTick + 1) % maxTick;

	}
	
	public int getNum() {
		return num;
	}

	@Override
	void setData() {
		// TODO Auto-generated method stub
		
	}

	

	
}

//package com.kernicky.gl_prototype.test;
//
//import static org.junit.Assert.assertNotNull;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import android.os.Handler;
//
//import com.kernicky.gl_prototype.math.Quaternion;
//import com.kernicky.gl_prototype.math.Vector;
//import com.kernicky.gl_prototype.models.Model;
//
//public class MTest {
//	private static Model m;
//	private static Model o;
//	
//	@BeforeClass
//	public static void testSetup() {
//		m = new Model();
//		o = new Model();
//		o.position = new Quaternion(0, 7, 0, 0);
//	}
//	
//	@AfterClass
//	public static void testCleanup() {
//		
//	}
//	
//	@Test
//	public void testRot() {
//		Thread t1 = new Thread(new Runnable() {
//			public void run() {
//				for(int n = 0; n < 1000000; n ++) {
//					float axis[] = {(float) Math.random(), (float) Math.random(), (float) Math.random()};
//					float speed = (float) Math.random();
//					axis = Vector.normalize(axis);
//					m.applyRot(speed*7, axis);
//					assertNotNull(m.position.x);
//					assertNotNull(m.position.y);
//					assertNotNull(m.position.z);
//					//System.out.println(n);
//				}
//				assert(true);
//			}
//		});
//
//
//		
//		Thread t2 = new Thread(new Runnable() {
//			public void run() {
//				for(int n = 0; n < 1000000; n ++) {
//					o.seek(m.position);
//					assertNotNull(o.position.x);
//					assertNotNull(o.position.y);
//					assertNotNull(o.position.z);
//				}
//			}
//		});
//		
//		t1.start();
//		t2.start();
//		
//		while(t1.isAlive() && t2.isAlive()) {}
//	}
//}

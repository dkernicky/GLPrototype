package com.kernicky.gl_prototype;

public class ShaderData {
	public static final String vertexShaderCode =
			// This matrix member variable provides a hook to manipulate
			// the coordinates of the objects that use this vertex shader
			"uniform mat4 u_MVPMatrix;" +
			"uniform mat4 u_MVMatrix;" +
			"attribute vec4 a_Position;" +
			"attribute vec3 a_Normal;" +
			"attribute vec3 a_Ambient;" +
			"attribute vec3 a_Diffuse;" +
			"attribute vec3 a_Specular;" +
			"attribute float a_Shininess;" +
			"varying vec3 v_Position;" +
			"varying vec3 v_Normal;" +
			"varying vec3 v_Ambient;" +
			"varying vec3 v_Diffuse;" +
			"varying vec3 v_Specular;" +
			"varying float v_Shininess;" +
			"void main() {" +
			"  v_Position = vec3(u_MVMatrix * a_Position);" +
			"  v_Normal = normalize(vec3(u_MVMatrix * vec4(a_Normal, 0.0)));" +
			"  v_Ambient = a_Ambient;" +
			"  v_Diffuse = a_Diffuse;" +
			"  v_Specular = a_Specular;" +
			"  v_Shininess = a_Shininess;" +
			"  gl_Position = u_MVPMatrix * a_Position;" + // the matrix must be included as a modifier of gl_Position

			"}";

			public static final String fragmentShaderCode = 
					"precision mediump float;" + 
					"uniform vec3 u_LightPos;" +
					"varying vec3 v_Position;" +
					"varying vec3 v_Normal;" +
					"varying vec3 v_Ambient;" +
					"varying vec3 v_Diffuse;" +
					"varying vec3 v_Specular;" +
					"varying float v_Shininess;" +
					"void main() {  "+ 
					"  vec3 v = vec3(0.0, 2.0, 3.0) - v_Position;" +
					"  vec3 ka = vec3(0.6941, 0.1059, 0.3451);" +
					"  vec3 kd = vec3(0.6941, 0.1059, 0.3451);" +
					"  vec3 ks = vec3(0.3500, 0.3500, 0.3500);" +
					"  vec3 lc = vec3(1.0, 1.0, 1.0);" +
					"  vec3 lp = u_LightPos - v_Position;" +
					"  float Ns = 32.0;" +
					"  vec3 h = (normalize(v)+normalize(lp))/normalize(v+lp);" +
					"  float d = length(u_LightPos- v_Position);" +
					//"  float att = .05*(d*d)+ .05*d;" +
					"  float att = min((.5 + .5/d+ .5/(d*d)), 1.0);" +
					//"  vec3 amb = vec3(0.1, 0.1, 0.1);" +
					//"  vec3 diff = vec3(0.1, 0.1, 0.1);" +
					//"  vec3 spec = vec3(0.1, 0.1, 0.1);" +
					"  vec3 amb = v_Ambient*lc;" +
					"  vec3 diff = att*v_Diffuse*lc;" +
					//"  vec3 spec = att*v_Specular*lc;" +
					"  vec3 spec = att*v_Specular*lc*pow(max(cos(dot(normalize(v_Normal), h)), 0.0),v_Shininess);" +
					"  gl_FragColor = vec4(amb+diff+spec, 0.0);" + 
					"}";
			/*	private final String fragmentShaderCode = 
					"precision mediump float;" + 
					"varying vec3 v_Position;" +
					"varying vec3 v_Normal;" +
					"varying vec3 v_Ambient;" +
					"varying vec3 v_Diffuse;" +
					"varying vec3 v_Specular;" +
					"varying vec3 v_Shininess;" +
					"void main() {  "+ 
					"  vec3 v = vec3(0.0, 0.0, 3.0) - v_Position;" +
					"  vec3 ka = vec3(0.6941, 0.1059, 0.3451);" +
					"  vec3 kd = vec3(0.6941, 0.1059, 0.3451);" +
					"  vec3 ks = vec3(0.3500, 0.3500, 0.3500);" +
					"  vec3 lc = vec3(1.0, 1.0, 1.0);" +
					"  vec3 lp = vec3(0.0, 3.0, 1.0) - v_Position;" +
					"  float Ns = 32.0;" +
					"  vec3 h = (normalize(v)+normalize(lp))/normalize(v+lp);" +
					"  float d = length(vec3(0.0, 3.0, 1.0)- v_Position);" +
					"  float att = .05*(d*d)+ .05*d;" +
					"  vec3 amb = ka*lc;" +
					"  vec3 diff = att*kd*lc;" +
					"  vec3 spec = att*ks*lc*pow(max(cos(dot(normalize(v_Normal), h)), 0.0),Ns);" +
					"  gl_FragColor = vec4(amb+diff+spec, 0.0);" + 
					"}";*/
			
			/*private final String fragmentShaderCode = 
					"precision mediump float;" + 
					"varying vec3 v_Position;" +
					"varying vec3 v_Normal;" +
					"void main() {  "+ 
					"  vec4 v_Color = vec4(.69, .10, .34, 0.0);" +
					"  gl_FragColor = v_Color;" +
					"}";*/
}

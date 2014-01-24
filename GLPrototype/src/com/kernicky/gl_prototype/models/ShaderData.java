package com.kernicky.gl_prototype.models;

public class ShaderData {
	private final String vertexShaderCode =
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
			"  v_Normal = normalize(vec3(u_MVMatrix * vec4(normalize(a_Normal), 0.0)));" +
			"  v_Ambient = a_Ambient;" +
			"  v_Diffuse = a_Diffuse;" +
			"  v_Specular = a_Specular;" +
			"  v_Shininess = a_Shininess;" +
			"  gl_Position = u_MVPMatrix * a_Position;" +
			"}";
	private final String fragmentShaderCode = 
			"precision mediump float;" + 
			"uniform vec3 u_LightPos[3];" +
			"varying vec3 v_Position;" +
			"varying vec3 v_Normal;" +
			"varying vec3 v_Ambient;" +
			"varying vec3 v_Diffuse;" +
			"varying vec3 v_Specular;" +
			"varying float v_Shininess;" +
			"void main() {  " +
			"  vec3 amb = vec3(0.0, 0.0, 0.0);" +
			"  vec3 diff = vec3(0.0, 0.0, 0.0);" +
			"  vec3 spec = vec3(0.0, 0.0, 0.0);" +
			"  for(int n = 0; n < 3; n ++) {" +
			"    vec3 v = normalize(vec3(0.0, 0.0, 0.0) - v_Position);" +
			"    vec3 lightDir = u_LightPos[n] - v_Position;" +
			"    float distance = length(lightDir);" +
			"    lightDir = lightDir/distance;" +
			"    distance = (distance*distance)/.5;" +
			
			"    normalize(v_Normal);" +
			"    float NdotL = dot(v_Normal, lightDir);" +
			"    float intensity = min(NdotL, 1.0);" +
			"    intensity = max(NdotL, 0.0);" +
			
			"    vec3 lc = vec3(1.0, 1.0, 1.0);" +
			"    vec3 v_Diff = vec3(.5, .5, .5);" +
			"    diff += intensity*lc*v_Diff/distance;" +
			"    vec3 H = normalize(v+lightDir);" +
			"    float NdotH = dot(v_Normal, H);" +
			"    intensity = min(NdotH, 1.0);" +
			"    intensity = max(NdotH, 0.0);" +
			"    vec3 v_Spec = vec3(.5, .5, .5);" +
			"    intensity = pow(intensity, v_Shininess);" +
			"    spec += intensity*lc*v_Spec/distance;" +
			"    amb += v_Ambient*lc;" +
			"  }" +
			"  gl_FragColor = vec4(amb+diff+spec, 0.0);" + 
			"}";
	
	public static final String lightVertexShaderCode =
			"uniform mat4 u_MVPMatrix;" +
			"uniform mat4 u_MVMatrix;" +
			"attribute vec4 a_Position;" +
			"varying vec3 v_Position;" +
			"void main() {" +
			"  v_Position = vec3(u_MVMatrix * a_Position);" +
			"  gl_Position = u_MVPMatrix * a_Position;" +
			"}";
	
	public static final String lightFragmentShaderCode = 
			"precision mediump float;" + 
			"varying vec3 v_Position;" +
			"void main() {  " +
			"  vec3 amb = vec3(0.0, 0.0, 0.0);" +
			"  gl_FragColor = vec4(1.0, 1.0, 1.0, 0.0);" + 
			"}";
	
}

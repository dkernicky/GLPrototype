package com.kernicky.gl_prototype.models;

public class ShaderData {
	public static final String singleColorVertexShaderCode =
			"uniform mat4 u_MVPMatrix;" +
			"uniform mat4 u_MVMatrix;" +
			"attribute vec4 a_Position;" +
			"attribute vec3 a_Normal;" +
			"varying vec3 v_Position;" +
			"varying vec3 v_Normal;" +
			"void main() {" +
			"  v_Position = vec3(u_MVMatrix * a_Position);" +
			"  v_Normal = normalize(vec3(u_MVMatrix * vec4(normalize(a_Normal), 0.0)));" +
			"  gl_Position = u_MVPMatrix * a_Position;" +
			"}";
	public static final String singleColorFragmentShaderCode = 
			"precision mediump float;" + 
			"uniform vec3 u_LightPos[16];" +
			"varying vec3 v_Position;" +
			"varying vec3 v_Normal;" +
			"uniform vec3 u_Ambient;" +
			"uniform vec3 u_Diffuse;" +
			"uniform vec3 u_Specular;" +
			"uniform float u_Shininess;" +
			"void main() { " +
			"  vec3 amb = vec3(0.0, 0.0, 0.0);" +
			"  vec3 diff = vec3(0.0, 0.0, 0.0);" +
			"  vec3 spec = vec3(0.0, 0.0, 0.0);" +
			"  vec3 lc = vec3(1.0, 1.0, 1.0);" +
			"  for(int n = 0; n < 1; n ++) {" +
			"    vec3 v = normalize(vec3(0.0, 0.0, 0.0) - v_Position);" +
			"    vec3 lightDir = u_LightPos[n] - v_Position;" +
			"    float distance = length(lightDir);" +
			"    lightDir = lightDir/distance;" +
			//"    distance = (distance*distance)/.5;" +
			
			"    normalize(v_Normal);" +
			"    float NdotL = dot(v_Normal, lightDir);" +
			"    float intensity = min(NdotL, 1.0);" +
			"    intensity = max(NdotL, 0.0);" +
			
			"    vec3 v_Diff = vec3(.5, .5, .5);" +
			"    diff += intensity*lc*u_Diffuse/distance;" +
			"    vec3 H = normalize(v+lightDir);" +
			"    float NdotH = dot(v_Normal, H);" +
			"    intensity = min(NdotH, 1.0);" +
			"    intensity = max(NdotH, 0.0);" +
			"    intensity = pow(intensity, u_Shininess);" +
			"    spec += intensity*lc*u_Specular/distance;" +
			"  }" +
						
//			"  if(diff.x >.75) { " +
//			"    diff.x = 1.0; " +
//			"  }" +
//			"  else if(diff.x >.5) { " +
//			"    diff.x = 0.75; " +
//			"  }" +
//			"  else if(diff.x >.25) { " +
//			"    diff.x = 0.5; " +
//			"  }" +
//			"  else if(diff.x > 0.0) { " +
//			"    diff.x = 0.25; " +
//			"  }" +
//			"  else {" +
//			"    diff.x = 0.0;" +
//			"  }" +
//			
//			"  if(diff.y >.75) { " +
//			"    diff.y = 1.0; " +
//			"  }" +
//			"  else if(diff.y >.5) { " +
//			"    diff.y = 0.75; " +
//			"  }" +
//			"  else if(diff.y >.25) { " +
//			"    diff.y = 0.5; " +
//			"  }" +
//			"  else if(diff.y > 0.0) { " +
//			"    diff.y = 0.25; " +
//			"  }" +
//			"  else {" +
//			"    diff.y = 0.0;" +
//			"  }" +
//			
//			"  if(diff.z >.75) { " +
//			"    diff.z = 1.0; " +
//			"  }" +
//			"  else if(diff.z >.5) { " +
//			"    diff.z = 0.75; " +
//			"  }" +
//			"  else if(diff.z >.25) { " +
//			"    diff.z = 0.5; " +
//			"  }" +
//			"  else if(diff.z > 0.0) { " +
//			"    diff.z = 0.25; " +
//			"  }" +
//			"  else {" +
//			"    diff.z = 0.0;" +
//			"  }" +
			//"  gl_FragColor = vec4(u_Diffuse, 0.3);" + 
			"  gl_FragColor = vec4(u_Ambient+diff+spec, 0.3);" + 
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
			"uniform vec3 u_Ambient;" +
			"void main() {  " +
			"  vec3 amb = vec3(0.0, 0.0, 0.0);" +
			"  gl_FragColor = vec4(u_Ambient, 1.0);" + 
			"}";
	
}

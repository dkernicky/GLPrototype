package com.kernicky.gl_prototype.models;

import android.opengl.Matrix;

public class GoldenShip extends Model {

	public GoldenShip() {
		//float coords[] = { 0.112790674f, -0.044585098f, 0.0f, 0.16918601f, 0.0f, -0.15038756f, 0.40061775f, 0.0f, 0.16121064f, 0.112790674f, 0.07519378f, 0.0f, 0.40061775f, 0.0f, 0.16121064f, 0.16918601f, 0.0f, -0.15038756f, 0.0f, 0.14017625f, 0.07727726f, 0.112790674f, 0.07519378f, 0.0f, 0.16918601f, 0.0f, -0.15038756f, 0.0f, 0.14017625f, 0.07727726f, 0.0f, 0.0f, 0.3007751f, 0.112790674f, 0.0f, 0.15038756f, 0.40061775f, 0.0f, 0.16121064f, 0.112790674f, 0.0f, 0.15038756f, 0.112790674f, -0.044585098f, 0.0f, 0.0f, -0.087196514f, -0.11627335f, 0.056395337f, 0.0f, -0.4511627f, 0.16918601f, 0.0f, -0.15038756f, 0.112790674f, -0.044585098f, 0.0f, 0.112790674f, 0.0f, 0.15038756f, 0.0f, -0.087196514f, -0.11627335f, 0.0f, -0.087196514f, -0.11627335f, 0.112790674f, 0.0f, 0.15038756f, 0.0f, 0.0f, 0.3007751f, 0.0f, -0.087196514f, -0.11627335f, 0.16918601f, 0.0f, -0.15038756f, 0.112790674f, -0.044585098f, 0.0f, 0.112790674f, 0.07519378f, 0.0f, 0.0f, 0.14017625f, 0.07727726f, 0.112790674f, 0.0f, 0.15038756f, 0.0f, 0.0f, -0.6992249f, 0.0f, 0.14017625f, 0.07727726f, 0.056395337f, 0.0f, -0.4511627f, 0.0f, 0.0f, -0.6992249f, 0.056395337f, 0.0f, -0.4511627f, 0.0f, -0.087196514f, -0.11627335f, 0.0f, 0.14017625f, 0.07727726f, 0.16918601f, 0.0f, -0.15038756f, 0.056395337f, 0.0f, -0.4511627f, 0.40061775f, 0.0f, 0.16121064f, 0.112790674f, 0.07519378f, 0.0f, 0.112790674f, 0.0f, 0.15038756f, -0.112790674f, -0.044585098f, 0.0f, -0.40061775f, 0.0f, 0.16121064f, -0.16918601f, 0.0f, -0.15038756f, -0.112790674f, 0.07519378f, 0.0f, -0.16918601f, 0.0f, -0.15038756f, -0.40061775f, 0.0f, 0.16121064f, 0.0f, 0.14017625f, 0.07727726f, -0.16918601f, 0.0f, -0.15038756f, -0.112790674f, 0.07519378f, 0.0f, 0.0f, 0.14017625f, 0.07727726f, -0.112790674f, 0.0f, 0.15038756f, 0.0f, 0.0f, 0.3007751f, -0.40061775f, 0.0f, 0.16121064f, -0.112790674f, -0.044585098f, 0.0f, -0.112790674f, 0.0f, 0.15038756f, 0.0f, -0.087196514f, -0.11627335f, -0.16918601f, 0.0f, -0.15038756f, -0.056395337f, 0.0f, -0.4511627f, -0.112790674f, -0.044585098f, 0.0f, 0.0f, -0.087196514f, -0.11627335f, -0.112790674f, 0.0f, 0.15038756f, 0.0f, -0.087196514f, -0.11627335f, 0.0f, 0.0f, 0.3007751f, -0.112790674f, 0.0f, 0.15038756f, 0.0f, -0.087196514f, -0.11627335f, -0.112790674f, -0.044585098f, 0.0f, -0.16918601f, 0.0f, -0.15038756f, -0.112790674f, 0.07519378f, 0.0f, -0.112790674f, 0.0f, 0.15038756f, 0.0f, 0.14017625f, 0.07727726f, 0.0f, 0.0f, -0.6992249f, -0.056395337f, 0.0f, -0.4511627f, 0.0f, 0.14017625f, 0.07727726f, 0.0f, 0.0f, -0.6992249f, 0.0f, -0.087196514f, -0.11627335f, -0.056395337f, 0.0f, -0.4511627f, 0.0f, 0.14017625f, 0.07727726f, -0.056395337f, 0.0f, -0.4511627f, -0.16918601f, 0.0f, -0.15038756f, -0.40061775f, 0.0f, 0.16121064f, -0.112790674f, 0.0f, 0.15038756f, -0.112790674f, 0.07519378f, 0.0f };
		//float normals[] =  { 0.251852f, -0.949516f, -0.187057f, 0.251852f, -0.949516f, -0.187057f, 0.251852f, -0.949516f, -0.187057f, 0.390766f, 0.873537f, -0.290231f, 0.390766f, 0.873537f, -0.290231f, 0.390766f, 0.873537f, -0.290231f, 0.281722f, 0.896286f, -0.342497f, 0.281722f, 0.896286f, -0.342497f, 0.281722f, 0.896286f, -0.342497f, 0.578078f, 0.691269f, 0.433559f, 0.578078f, 0.691269f, 0.433559f, 0.578078f, 0.691269f, 0.433559f, -0.010688f, -0.958698f, 0.284224f, -0.010688f, -0.958698f, 0.284224f, -0.010688f, -0.958698f, 0.284224f, 0.42655f, -0.890207f, -0.159956f, 0.42655f, -0.890207f, -0.159956f, 0.42655f, -0.890207f, -0.159956f, 0.069028f, -0.956466f, 0.283562f, 0.069028f, -0.956466f, 0.283562f, 0.069028f, -0.956466f, 0.283562f, 0.263248f, -0.944309f, 0.197436f, 0.263248f, -0.944309f, 0.197436f, 0.263248f, -0.944309f, 0.197436f, 0.439896f, -0.892504f, -0.099638f, 0.439896f, -0.892504f, -0.099638f, 0.439896f, -0.892504f, -0.099638f, 0.634871f, 0.691051f, 0.345526f, 0.634871f, 0.691051f, 0.345526f, 0.634871f, 0.691051f, 0.345526f, 0.615728f, 0.775425f, -0.139982f, 0.615728f, 0.775425f, -0.139982f, 0.615728f, 0.775425f, -0.139982f, 0.5454f, -0.828954f, -0.123993f, 0.5454f, -0.828954f, -0.123993f, 0.5454f, -0.828954f, -0.123993f, 0.47466f, 0.861983f, -0.177997f, 0.47466f, 0.861983f, -0.177997f, 0.47466f, 0.861983f, -0.177997f, -0.016814f, 0.894301f, 0.44715f, -0.016814f, 0.894301f, 0.44715f, -0.016814f, 0.894301f, 0.44715f, -0.251852f, -0.949516f, -0.187057f, -0.251852f, -0.949516f, -0.187057f, -0.251852f, -0.949516f, -0.187057f, -0.390766f, 0.873537f, -0.290231f, -0.390766f, 0.873537f, -0.290231f, -0.390766f, 0.873537f, -0.290231f, -0.281722f, 0.896286f, -0.342497f, -0.281722f, 0.896286f, -0.342497f, -0.281722f, 0.896286f, -0.342497f, -0.578078f, 0.691269f, 0.433559f, -0.578078f, 0.691269f, 0.433559f, -0.578078f, 0.691269f, 0.433559f, 0.010688f, -0.958698f, 0.284224f, 0.010688f, -0.958698f, 0.284224f, 0.010688f, -0.958698f, 0.284224f, -0.42655f, -0.890207f, -0.159956f, -0.42655f, -0.890207f, -0.159956f, -0.42655f, -0.890207f, -0.159956f, -0.069028f, -0.956466f, 0.283562f, -0.069028f, -0.956466f, 0.283562f, -0.069028f, -0.956466f, 0.283562f, -0.263248f, -0.944309f, 0.197436f, -0.263248f, -0.944309f, 0.197436f, -0.263248f, -0.944309f, 0.197436f, -0.439896f, -0.892504f, -0.099638f, -0.439896f, -0.892504f, -0.099638f, -0.439896f, -0.892504f, -0.099638f, -0.634871f, 0.691051f, 0.345526f, -0.634871f, 0.691051f, 0.345526f, -0.634871f, 0.691051f, 0.345526f, -0.615728f, 0.775425f, -0.139982f, -0.615728f, 0.775425f, -0.139982f, -0.615728f, 0.775425f, -0.139982f, -0.5454f, -0.828954f, -0.123993f, -0.5454f, -0.828954f, -0.123993f, -0.5454f, -0.828954f, -0.123993f, -0.47466f, 0.861983f, -0.177997f, -0.47466f, 0.861983f, -0.177997f, -0.47466f, 0.861983f, -0.177997f, 0.016814f, 0.894301f, 0.44715f, 0.016814f, 0.894301f, 0.44715f, 0.016814f, 0.894301f, 0.44715f };
		//float normals[] = { 0.187522f, -0.939296f, 0.07027275f, 0.37757432f, -0.016736826f, -0.20956266f, 0.153779f, -0.035093993f, 0.063521504f, 0.32263625f, 0.83879375f, 0.039987005f, 0.153779f, -0.035093993f, 0.063521504f, 0.37757432f, -0.016736826f, -0.20956266f, 0.0f, 0.7832028f, 0.023721805f, 0.32263625f, 0.83879375f, 0.039987005f, 0.37757432f, -0.016736826f, -0.20956266f, 0.0f, 0.7832028f, 0.023721805f, 0.0f, -0.12652001f, 0.31549752f, 0.25295383f, -0.097142f, 0.3319095f, 0.153779f, -0.035093993f, 0.063521504f, 0.25295383f, -0.097142f, 0.3319095f, 0.187522f, -0.939296f, 0.07027275f, 0.0f, -0.902488f, 0.019482203f, 0.5155845f, -0.020438239f, -0.150482f, 0.37757432f, -0.016736826f, -0.20956266f, 0.187522f, -0.939296f, 0.07027275f, 0.25295383f, -0.097142f, 0.3319095f, 0.0f, -0.902488f, 0.019482203f, 0.0f, -0.902488f, 0.019482203f, 0.25295383f, -0.097142f, 0.3319095f, 0.0f, -0.12652001f, 0.31549752f, 0.0f, -0.902488f, 0.019482203f, 0.37757432f, -0.016736826f, -0.20956266f, 0.187522f, -0.939296f, 0.07027275f, 0.32263625f, 0.83879375f, 0.039987005f, 0.0f, 0.7832028f, 0.023721805f, 0.25295383f, -0.097142f, 0.3319095f, 0.0f, -0.026764482f, -0.1319875f, 0.0f, 0.7832028f, 0.023721805f, 0.5155845f, -0.020438239f, -0.150482f, 0.0f, -0.026764482f, -0.1319875f, 0.5155845f, -0.020438239f, -0.150482f, 0.0f, -0.902488f, 0.019482203f, 0.0f, 0.7832028f, 0.023721805f, 0.37757432f, -0.016736826f, -0.20956266f, 0.5155845f, -0.020438239f, -0.150482f, 0.153779f, -0.035093993f, 0.063521504f, 0.32263625f, 0.83879375f, 0.039987005f, 0.25295383f, -0.097142f, 0.3319095f, -0.187522f, -0.939296f, 0.07027275f, -0.153779f, -0.035093993f, 0.063521504f, -0.37757432f, -0.016736826f, -0.20956266f, -0.32263625f, 0.83879375f, 0.039987005f, -0.37757432f, -0.016736826f, -0.20956266f, -0.153779f, -0.035093993f, 0.063521504f, 0.0f, 0.7832028f, 0.023721805f, -0.37757432f, -0.016736826f, -0.20956266f, -0.32263625f, 0.83879375f, 0.039987005f, 0.0f, 0.7832028f, 0.023721805f, -0.25295383f, -0.097142f, 0.3319095f, 0.0f, -0.12652001f, 0.31549752f, -0.153779f, -0.035093993f, 0.063521504f, -0.187522f, -0.939296f, 0.07027275f, -0.25295383f, -0.097142f, 0.3319095f, 0.0f, -0.902488f, 0.019482203f, -0.37757432f, -0.016736826f, -0.20956266f, -0.5155845f, -0.020438239f, -0.150482f, -0.187522f, -0.939296f, 0.07027275f, 0.0f, -0.902488f, 0.019482203f, -0.25295383f, -0.097142f, 0.3319095f, 0.0f, -0.902488f, 0.019482203f, 0.0f, -0.12652001f, 0.31549752f, -0.25295383f, -0.097142f, 0.3319095f, 0.0f, -0.902488f, 0.019482203f, -0.187522f, -0.939296f, 0.07027275f, -0.37757432f, -0.016736826f, -0.20956266f, -0.32263625f, 0.83879375f, 0.039987005f, -0.25295383f, -0.097142f, 0.3319095f, 0.0f, 0.7832028f, 0.023721805f, 0.0f, -0.026764482f, -0.1319875f, -0.5155845f, -0.020438239f, -0.150482f, 0.0f, 0.7832028f, 0.023721805f, 0.0f, -0.026764482f, -0.1319875f, 0.0f, -0.902488f, 0.019482203f, -0.5155845f, -0.020438239f, -0.150482f, 0.0f, 0.7832028f, 0.023721805f, -0.5155845f, -0.020438239f, -0.150482f, -0.37757432f, -0.016736826f, -0.20956266f, -0.153779f, -0.035093993f, 0.063521504f, -0.25295383f, -0.097142f, 0.3319095f, -0.32263625f, 0.83879375f, 0.039987005f };

		
		float[] amb = {0.0f, 0.0f, 0.0f};	
		float[] diff = {0.64f, .402f, 0.0f};
		float[] spec = {0.5f, 0.5f, 0.5f};	
		float shine = 96;
		this.setData(ModelData.GoldenShipCoords, ModelData.GoldenShipNormals, amb, diff, spec, shine);
		//this.setData(coords, normals, amb, diff, spec, shine);

	}
	
	@Override
	public GoldenShip clone() {
		return this.clone();
	}

}

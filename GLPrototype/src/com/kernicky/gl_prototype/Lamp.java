package com.kernicky.gl_prototype;

import android.opengl.Matrix;

public class Lamp extends Model {
	private float rot = 0.0f;
	public Lamp() {
		float coords[] = { 0.0f, -0.5f, 0.0f, 0.2126615f, -0.425327f, 0.1545055f, -0.081228f, -0.425327f, 0.2499975f, 0.3618035f, -0.22361f, 0.2628625f, 0.2126615f, -0.425327f, 0.1545055f, 0.425324f, -0.262868f, 0.0f, 0.0f, -0.5f, 0.0f, -0.081228f, -0.425327f, 0.2499975f, -0.262865f, -0.425326f, 0.0f, 0.0f, -0.5f, 0.0f, -0.262865f, -0.425326f, 0.0f, -0.081228f, -0.425327f, -0.2499975f, 0.0f, -0.5f, 0.0f, -0.081228f, -0.425327f, -0.2499975f, 0.2126615f, -0.425327f, -0.1545055f, 0.3618035f, -0.22361f, 0.2628625f, 0.425324f, -0.262868f, 0.0f, 0.475529f, 0.0f, 0.1545065f, -0.138194f, -0.22361f, 0.4253245f, 0.1314345f, -0.262869f, 0.404506f, 0.0f, 0.0f, 0.5f, -0.447213f, -0.223608f, 0.0f, -0.3440945f, -0.262868f, 0.2499985f, -0.475529f, 0.0f, 0.1545065f, -0.138194f, -0.22361f, -0.4253245f, -0.3440945f, -0.262868f, -0.2499985f, -0.293893f, 0.0f, -0.4045085f, 0.3618035f, -0.22361f, -0.2628625f, 0.1314345f, -0.262869f, -0.404506f, 0.293893f, 0.0f, -0.4045085f, 0.3618035f, -0.22361f, 0.2628625f, 0.475529f, 0.0f, 0.1545065f, 0.293893f, 0.0f, 0.4045085f, -0.138194f, -0.22361f, 0.4253245f, 0.0f, 0.0f, 0.5f, -0.293893f, 0.0f, 0.4045085f, -0.447213f, -0.223608f, 0.0f, -0.475529f, 0.0f, 0.1545065f, -0.475529f, 0.0f, -0.1545065f, -0.138194f, -0.22361f, -0.4253245f, -0.293893f, 0.0f, -0.4045085f, 0.0f, 0.0f, -0.5f, 0.3618035f, -0.22361f, -0.2628625f, 0.293893f, 0.0f, -0.4045085f, 0.475529f, 0.0f, -0.1545065f, 0.138194f, 0.22361f, 0.4253245f, 0.3440945f, 0.262868f, 0.2499985f, 0.081228f, 0.425327f, 0.2499975f, -0.3618035f, 0.22361f, 0.2628625f, -0.1314345f, 0.262869f, 0.404506f, -0.2126615f, 0.425327f, 0.1545055f, -0.3618035f, 0.22361f, -0.2628625f, -0.425324f, 0.262868f, 0.0f, -0.2126615f, 0.425327f, -0.1545055f, 0.138194f, 0.22361f, -0.4253245f, -0.1314345f, 0.262869f, -0.404506f, 0.081228f, 0.425327f, -0.2499975f, 0.447213f, 0.223608f, 0.0f, 0.3440945f, 0.262868f, -0.2499985f, 0.262865f, 0.425326f, 0.0f, -0.081228f, -0.425327f, 0.2499975f, 0.1314345f, -0.262869f, 0.404506f, -0.138194f, -0.22361f, 0.4253245f, -0.081228f, -0.425327f, 0.2499975f, 0.2126615f, -0.425327f, 0.1545055f, 0.1314345f, -0.262869f, 0.404506f, 0.2126615f, -0.425327f, 0.1545055f, 0.3618035f, -0.22361f, 0.2628625f, 0.1314345f, -0.262869f, 0.404506f, 0.425324f, -0.262868f, 0.0f, 0.2126615f, -0.425327f, -0.1545055f, 0.3618035f, -0.22361f, -0.2628625f, 0.425324f, -0.262868f, 0.0f, 0.2126615f, -0.425327f, 0.1545055f, 0.2126615f, -0.425327f, -0.1545055f, 0.2126615f, -0.425327f, 0.1545055f, 0.0f, -0.5f, 0.0f, 0.2126615f, -0.425327f, -0.1545055f, -0.262865f, -0.425326f, 0.0f, -0.3440945f, -0.262868f, 0.2499985f, -0.447213f, -0.223608f, 0.0f, -0.262865f, -0.425326f, 0.0f, -0.081228f, -0.425327f, 0.2499975f, -0.3440945f, -0.262868f, 0.2499985f, -0.081228f, -0.425327f, 0.2499975f, -0.138194f, -0.22361f, 0.4253245f, -0.3440945f, -0.262868f, 0.2499985f, -0.081228f, -0.425327f, -0.2499975f, -0.3440945f, -0.262868f, -0.2499985f, -0.138194f, -0.22361f, -0.4253245f, -0.081228f, -0.425327f, -0.2499975f, -0.262865f, -0.425326f, 0.0f, -0.3440945f, -0.262868f, -0.2499985f, -0.262865f, -0.425326f, 0.0f, -0.447213f, -0.223608f, 0.0f, -0.3440945f, -0.262868f, -0.2499985f, 0.2126615f, -0.425327f, -0.1545055f, 0.1314345f, -0.262869f, -0.404506f, 0.3618035f, -0.22361f, -0.2628625f, 0.2126615f, -0.425327f, -0.1545055f, -0.081228f, -0.425327f, -0.2499975f, 0.1314345f, -0.262869f, -0.404506f, -0.081228f, -0.425327f, -0.2499975f, -0.138194f, -0.22361f, -0.4253245f, 0.1314345f, -0.262869f, -0.404506f, 0.475529f, 0.0f, 0.1545065f, 0.475529f, 0.0f, -0.1545065f, 0.447213f, 0.223608f, 0.0f, 0.475529f, 0.0f, 0.1545065f, 0.425324f, -0.262868f, 0.0f, 0.475529f, 0.0f, -0.1545065f, 0.425324f, -0.262868f, 0.0f, 0.3618035f, -0.22361f, -0.2628625f, 0.475529f, 0.0f, -0.1545065f, 0.0f, 0.0f, 0.5f, 0.293893f, 0.0f, 0.4045085f, 0.138194f, 0.22361f, 0.4253245f, 0.0f, 0.0f, 0.5f, 0.1314345f, -0.262869f, 0.404506f, 0.293893f, 0.0f, 0.4045085f, 0.1314345f, -0.262869f, 0.404506f, 0.3618035f, -0.22361f, 0.2628625f, 0.293893f, 0.0f, 0.4045085f, -0.475529f, 0.0f, 0.1545065f, -0.293893f, 0.0f, 0.4045085f, -0.3618035f, 0.22361f, 0.2628625f, -0.475529f, 0.0f, 0.1545065f, -0.3440945f, -0.262868f, 0.2499985f, -0.293893f, 0.0f, 0.4045085f, -0.3440945f, -0.262868f, 0.2499985f, -0.138194f, -0.22361f, 0.4253245f, -0.293893f, 0.0f, 0.4045085f, -0.293893f, 0.0f, -0.4045085f, -0.475529f, 0.0f, -0.1545065f, -0.3618035f, 0.22361f, -0.2628625f, -0.293893f, 0.0f, -0.4045085f, -0.3440945f, -0.262868f, -0.2499985f, -0.475529f, 0.0f, -0.1545065f, -0.3440945f, -0.262868f, -0.2499985f, -0.447213f, -0.223608f, 0.0f, -0.475529f, 0.0f, -0.1545065f, 0.293893f, 0.0f, -0.4045085f, 0.0f, 0.0f, -0.5f, 0.138194f, 0.22361f, -0.4253245f, 0.293893f, 0.0f, -0.4045085f, 0.1314345f, -0.262869f, -0.404506f, 0.0f, 0.0f, -0.5f, 0.1314345f, -0.262869f, -0.404506f, -0.138194f, -0.22361f, -0.4253245f, 0.0f, 0.0f, -0.5f, 0.293893f, 0.0f, 0.4045085f, 0.3440945f, 0.262868f, 0.2499985f, 0.138194f, 0.22361f, 0.4253245f, 0.293893f, 0.0f, 0.4045085f, 0.475529f, 0.0f, 0.1545065f, 0.3440945f, 0.262868f, 0.2499985f, 0.475529f, 0.0f, 0.1545065f, 0.447213f, 0.223608f, 0.0f, 0.3440945f, 0.262868f, 0.2499985f, -0.293893f, 0.0f, 0.4045085f, -0.1314345f, 0.262869f, 0.404506f, -0.3618035f, 0.22361f, 0.2628625f, -0.293893f, 0.0f, 0.4045085f, 0.0f, 0.0f, 0.5f, -0.1314345f, 0.262869f, 0.404506f, 0.0f, 0.0f, 0.5f, 0.138194f, 0.22361f, 0.4253245f, -0.1314345f, 0.262869f, 0.404506f, -0.475529f, 0.0f, -0.1545065f, -0.425324f, 0.262868f, 0.0f, -0.3618035f, 0.22361f, -0.2628625f, -0.475529f, 0.0f, -0.1545065f, -0.475529f, 0.0f, 0.1545065f, -0.425324f, 0.262868f, 0.0f, -0.475529f, 0.0f, 0.1545065f, -0.3618035f, 0.22361f, 0.2628625f, -0.425324f, 0.262868f, 0.0f, 0.0f, 0.0f, -0.5f, -0.1314345f, 0.262869f, -0.404506f, 0.138194f, 0.22361f, -0.4253245f, 0.0f, 0.0f, -0.5f, -0.293893f, 0.0f, -0.4045085f, -0.1314345f, 0.262869f, -0.404506f, -0.293893f, 0.0f, -0.4045085f, -0.3618035f, 0.22361f, -0.2628625f, -0.1314345f, 0.262869f, -0.404506f, 0.475529f, 0.0f, -0.1545065f, 0.3440945f, 0.262868f, -0.2499985f, 0.447213f, 0.223608f, 0.0f, 0.475529f, 0.0f, -0.1545065f, 0.293893f, 0.0f, -0.4045085f, 0.3440945f, 0.262868f, -0.2499985f, 0.293893f, 0.0f, -0.4045085f, 0.138194f, 0.22361f, -0.4253245f, 0.3440945f, 0.262868f, -0.2499985f, 0.081228f, 0.425327f, 0.2499975f, 0.262865f, 0.425326f, 0.0f, 0.0f, 0.5f, 0.0f, 0.081228f, 0.425327f, 0.2499975f, 0.3440945f, 0.262868f, 0.2499985f, 0.262865f, 0.425326f, 0.0f, 0.3440945f, 0.262868f, 0.2499985f, 0.447213f, 0.223608f, 0.0f, 0.262865f, 0.425326f, 0.0f, -0.2126615f, 0.425327f, 0.1545055f, 0.081228f, 0.425327f, 0.2499975f, 0.0f, 0.5f, 0.0f, -0.2126615f, 0.425327f, 0.1545055f, -0.1314345f, 0.262869f, 0.404506f, 0.081228f, 0.425327f, 0.2499975f, -0.1314345f, 0.262869f, 0.404506f, 0.138194f, 0.22361f, 0.4253245f, 0.081228f, 0.425327f, 0.2499975f, -0.2126615f, 0.425327f, -0.1545055f, -0.2126615f, 0.425327f, 0.1545055f, 0.0f, 0.5f, 0.0f, -0.2126615f, 0.425327f, -0.1545055f, -0.425324f, 0.262868f, 0.0f, -0.2126615f, 0.425327f, 0.1545055f, -0.425324f, 0.262868f, 0.0f, -0.3618035f, 0.22361f, 0.2628625f, -0.2126615f, 0.425327f, 0.1545055f, 0.081228f, 0.425327f, -0.2499975f, -0.2126615f, 0.425327f, -0.1545055f, 0.0f, 0.5f, 0.0f, 0.081228f, 0.425327f, -0.2499975f, -0.1314345f, 0.262869f, -0.404506f, -0.2126615f, 0.425327f, -0.1545055f, -0.1314345f, 0.262869f, -0.404506f, -0.3618035f, 0.22361f, -0.2628625f, -0.2126615f, 0.425327f, -0.1545055f, 0.262865f, 0.425326f, 0.0f, 0.081228f, 0.425327f, -0.2499975f, 0.0f, 0.5f, 0.0f, 0.262865f, 0.425326f, 0.0f, 0.3440945f, 0.262868f, -0.2499985f, 0.081228f, 0.425327f, -0.2499975f, 0.3440945f, 0.262868f, -0.2499985f, 0.138194f, 0.22361f, -0.4253245f, 0.081228f, 0.425327f, -0.2499975f };
		float normals[] = { 0.102381f, -0.943523f, 0.31509f, 0.102381f, -0.943523f, 0.31509f, 0.102381f, -0.943523f, 0.31509f, 0.700224f, -0.661699f, 0.268032f, 0.700224f, -0.661699f, 0.268032f, 0.700224f, -0.661699f, 0.268032f, -0.268034f, -0.943523f, 0.194737f, -0.268034f, -0.943523f, 0.194737f, -0.268034f, -0.943523f, 0.194737f, -0.268034f, -0.943523f, -0.194737f, -0.268034f, -0.943523f, -0.194737f, -0.268034f, -0.943523f, -0.194737f, 0.102381f, -0.943523f, -0.31509f, 0.102381f, -0.943523f, -0.31509f, 0.102381f, -0.943523f, -0.31509f, 0.904989f, -0.330384f, 0.268031f, 0.904989f, -0.330384f, 0.268031f, 0.904989f, -0.330384f, 0.268031f, 0.024747f, -0.330386f, 0.943521f, 0.024747f, -0.330386f, 0.943521f, 0.024747f, -0.330386f, 0.943521f, -0.889697f, -0.330385f, 0.315095f, -0.889697f, -0.330385f, 0.315095f, -0.889697f, -0.330385f, 0.315095f, -0.574602f, -0.330388f, -0.748784f, -0.574602f, -0.330388f, -0.748784f, -0.574602f, -0.330388f, -0.748784f, 0.534576f, -0.330386f, -0.777865f, 0.534576f, -0.330386f, -0.777865f, 0.534576f, -0.330386f, -0.777865f, 0.802609f, -0.125627f, 0.583126f, 0.802609f, -0.125627f, 0.583126f, 0.802609f, -0.125627f, 0.583126f, -0.306569f, -0.125629f, 0.943522f, -0.306569f, -0.125629f, 0.943522f, -0.306569f, -0.125629f, 0.943522f, -0.992077f, -0.125628f, 0.0f, -0.992077f, -0.125628f, 0.0f, -0.992077f, -0.125628f, 0.0f, -0.306569f, -0.125629f, -0.943522f, -0.306569f, -0.125629f, -0.943522f, -0.306569f, -0.125629f, -0.943522f, 0.802609f, -0.125627f, -0.583126f, 0.802609f, -0.125627f, -0.583126f, 0.802609f, -0.125627f, -0.583126f, 0.408946f, 0.661698f, 0.628425f, 0.408946f, 0.661698f, 0.628425f, 0.408946f, 0.661698f, 0.628425f, -0.4713f, 0.661699f, 0.583122f, -0.4713f, 0.661699f, 0.583122f, -0.4713f, 0.661699f, 0.583122f, -0.700224f, 0.661699f, -0.268032f, -0.700224f, 0.661699f, -0.268032f, -0.700224f, 0.661699f, -0.268032f, 0.03853f, 0.661699f, -0.748779f, 0.03853f, 0.661699f, -0.748779f, 0.03853f, 0.661699f, -0.748779f, 0.724042f, 0.661695f, -0.194736f, 0.724042f, 0.661695f, -0.194736f, 0.724042f, 0.661695f, -0.194736f, -0.03853f, -0.661699f, 0.748779f, -0.03853f, -0.661699f, 0.748779f, -0.03853f, -0.661699f, 0.748779f, 0.187594f, -0.794658f, 0.577345f, 0.187594f, -0.794658f, 0.577345f, 0.187594f, -0.794658f, 0.577345f, 0.4713f, -0.661699f, 0.583122f, 0.4713f, -0.661699f, 0.583122f, 0.4713f, -0.661699f, 0.583122f, 0.700224f, -0.661699f, -0.268032f, 0.700224f, -0.661699f, -0.268032f, 0.700224f, -0.661699f, -0.268032f, 0.60706f, -0.794656f, 0.0f, 0.60706f, -0.794656f, 0.0f, 0.60706f, -0.794656f, 0.0f, 0.331305f, -0.943524f, 0.0f, 0.331305f, -0.943524f, 0.0f, 0.331305f, -0.943524f, 0.0f, -0.724042f, -0.661695f, 0.194736f, -0.724042f, -0.661695f, 0.194736f, -0.724042f, -0.661695f, 0.194736f, -0.491119f, -0.794657f, 0.356821f, -0.491119f, -0.794657f, 0.356821f, -0.491119f, -0.794657f, 0.356821f, -0.408946f, -0.661698f, 0.628425f, -0.408946f, -0.661698f, 0.628425f, -0.408946f, -0.661698f, 0.628425f, -0.408946f, -0.661698f, -0.628425f, -0.408946f, -0.661698f, -0.628425f, -0.408946f, -0.661698f, -0.628425f, -0.491119f, -0.794657f, -0.356821f, -0.491119f, -0.794657f, -0.356821f, -0.491119f, -0.794657f, -0.356821f, -0.724042f, -0.661695f, -0.194736f, -0.724042f, -0.661695f, -0.194736f, -0.724042f, -0.661695f, -0.194736f, 0.4713f, -0.661699f, -0.583122f, 0.4713f, -0.661699f, -0.583122f, 0.4713f, -0.661699f, -0.583122f, 0.187594f, -0.794658f, -0.577345f, 0.187594f, -0.794658f, -0.577345f, 0.187594f, -0.794658f, -0.577345f, -0.03853f, -0.661699f, -0.748779f, -0.03853f, -0.661699f, -0.748779f, -0.03853f, -0.661699f, -0.748779f, 0.992077f, 0.125628f, -0.0f, 0.992077f, 0.125628f, -0.0f, 0.992077f, 0.125628f, -0.0f, 0.982246f, -0.187599f, 0.0f, 0.982246f, -0.187599f, 0.0f, 0.982246f, -0.187599f, 0.0f, 0.904989f, -0.330384f, -0.268031f, 0.904989f, -0.330384f, -0.268031f, 0.904989f, -0.330384f, -0.268031f, 0.306569f, 0.125629f, 0.943522f, 0.306569f, 0.125629f, 0.943522f, 0.306569f, 0.125629f, 0.943522f, 0.303531f, -0.187597f, 0.934171f, 0.303531f, -0.187597f, 0.934171f, 0.303531f, -0.187597f, 0.934171f, 0.534576f, -0.330386f, 0.777865f, 0.534576f, -0.330386f, 0.777865f, 0.534576f, -0.330386f, 0.777865f, -0.802609f, 0.125627f, 0.583126f, -0.802609f, 0.125627f, 0.583126f, -0.802609f, 0.125627f, 0.583126f, -0.794656f, -0.187595f, 0.577348f, -0.794656f, -0.187595f, 0.577348f, -0.794656f, -0.187595f, 0.577348f, -0.574602f, -0.330388f, 0.748784f, -0.574602f, -0.330388f, 0.748784f, -0.574602f, -0.330388f, 0.748784f, -0.802609f, 0.125627f, -0.583126f, -0.802609f, 0.125627f, -0.583126f, -0.802609f, 0.125627f, -0.583126f, -0.794656f, -0.187595f, -0.577348f, -0.794656f, -0.187595f, -0.577348f, -0.794656f, -0.187595f, -0.577348f, -0.889697f, -0.330385f, -0.315095f, -0.889697f, -0.330385f, -0.315095f, -0.889697f, -0.330385f, -0.315095f, 0.306569f, 0.125629f, -0.943522f, 0.306569f, 0.125629f, -0.943522f, 0.306569f, 0.125629f, -0.943522f, 0.303531f, -0.187597f, -0.934171f, 0.303531f, -0.187597f, -0.934171f, 0.303531f, -0.187597f, -0.934171f, 0.024747f, -0.330386f, -0.943521f, 0.024747f, -0.330386f, -0.943521f, 0.024747f, -0.330386f, -0.943521f, 0.574602f, 0.330388f, 0.748784f, 0.574602f, 0.330388f, 0.748784f, 0.574602f, 0.330388f, 0.748784f, 0.794656f, 0.187595f, 0.577348f, 0.794656f, 0.187595f, 0.577348f, 0.794656f, 0.187595f, 0.577348f, 0.889697f, 0.330385f, 0.315095f, 0.889697f, 0.330385f, 0.315095f, 0.889697f, 0.330385f, 0.315095f, -0.534576f, 0.330386f, 0.777865f, -0.534576f, 0.330386f, 0.777865f, -0.534576f, 0.330386f, 0.777865f, -0.303531f, 0.187597f, 0.934171f, -0.303531f, 0.187597f, 0.934171f, -0.303531f, 0.187597f, 0.934171f, -0.024747f, 0.330386f, 0.943521f, -0.024747f, 0.330386f, 0.943521f, -0.024747f, 0.330386f, 0.943521f, -0.904989f, 0.330384f, -0.268031f, -0.904989f, 0.330384f, -0.268031f, -0.904989f, 0.330384f, -0.268031f, -0.982246f, 0.187599f, 0.0f, -0.982246f, 0.187599f, 0.0f, -0.982246f, 0.187599f, 0.0f, -0.904989f, 0.330384f, 0.268031f, -0.904989f, 0.330384f, 0.268031f, -0.904989f, 0.330384f, 0.268031f, -0.024747f, 0.330386f, -0.943521f, -0.024747f, 0.330386f, -0.943521f, -0.024747f, 0.330386f, -0.943521f, -0.303531f, 0.187597f, -0.934171f, -0.303531f, 0.187597f, -0.934171f, -0.303531f, 0.187597f, -0.934171f, -0.534576f, 0.330386f, -0.777865f, -0.534576f, 0.330386f, -0.777865f, -0.534576f, 0.330386f, -0.777865f, 0.889697f, 0.330385f, -0.315095f, 0.889697f, 0.330385f, -0.315095f, 0.889697f, 0.330385f, -0.315095f, 0.794656f, 0.187595f, -0.577348f, 0.794656f, 0.187595f, -0.577348f, 0.794656f, 0.187595f, -0.577348f, 0.574602f, 0.330388f, -0.748784f, 0.574602f, 0.330388f, -0.748784f, 0.574602f, 0.330388f, -0.748784f, 0.268034f, 0.943523f, 0.194737f, 0.268034f, 0.943523f, 0.194737f, 0.268034f, 0.943523f, 0.194737f, 0.491119f, 0.794657f, 0.356821f, 0.491119f, 0.794657f, 0.356821f, 0.491119f, 0.794657f, 0.356821f, 0.724042f, 0.661695f, 0.194736f, 0.724042f, 0.661695f, 0.194736f, 0.724042f, 0.661695f, 0.194736f, -0.102381f, 0.943523f, 0.31509f, -0.102381f, 0.943523f, 0.31509f, -0.102381f, 0.943523f, 0.31509f, -0.187594f, 0.794658f, 0.577345f, -0.187594f, 0.794658f, 0.577345f, -0.187594f, 0.794658f, 0.577345f, 0.03853f, 0.661699f, 0.748779f, 0.03853f, 0.661699f, 0.748779f, 0.03853f, 0.661699f, 0.748779f, -0.331305f, 0.943524f, 0.0f, -0.331305f, 0.943524f, 0.0f, -0.331305f, 0.943524f, 0.0f, -0.60706f, 0.794656f, 0.0f, -0.60706f, 0.794656f, 0.0f, -0.60706f, 0.794656f, 0.0f, -0.700224f, 0.661699f, 0.268032f, -0.700224f, 0.661699f, 0.268032f, -0.700224f, 0.661699f, 0.268032f, -0.102381f, 0.943523f, -0.31509f, -0.102381f, 0.943523f, -0.31509f, -0.102381f, 0.943523f, -0.31509f, -0.187594f, 0.794658f, -0.577345f, -0.187594f, 0.794658f, -0.577345f, -0.187594f, 0.794658f, -0.577345f, -0.4713f, 0.661699f, -0.583122f, -0.4713f, 0.661699f, -0.583122f, -0.4713f, 0.661699f, -0.583122f, 0.268034f, 0.943523f, -0.194737f, 0.268034f, 0.943523f, -0.194737f, 0.268034f, 0.943523f, -0.194737f, 0.491119f, 0.794657f, -0.356821f, 0.491119f, 0.794657f, -0.356821f, 0.491119f, 0.794657f, -0.356821f, 0.408946f, 0.661698f, -0.628425f, 0.408946f, 0.661698f, -0.628425f, 0.408946f, 0.661698f, -0.628425f };
		float amb[] = { 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f };
		float diff[] = { 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f };
		float spec[] = { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f };
		float shine[] = { 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96, 96 };

		this.setData(coords, normals, amb, diff, spec, shine);
	}
	
	@Override
	public float[] applyTransforms(float[] mModel) {
		Matrix.rotateM(mModel, 0, rot, 0, 1, 0);
//		Matrix.rotateM(mModel, 0, 90, 1, 0, 0);
		Matrix.translateM(mModel, 0, -0.5f, 0.0f, 0.0f);
		Matrix.scaleM(mModel, 0, 0.05f, 0.05f, 0.05f);
		rot = (rot+1)%360;
		return mModel;
	} 
}
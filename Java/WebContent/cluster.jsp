<%@ page import="Data.*,java.util.*" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">	
	<link rel="shortcut icon" type="image/x-icon" href="docs/images/favicon.ico" />
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.2.0/dist/leaflet.css" integrity="sha512-M2wvCLH6DSRazYeZRIm1JnYyh22purTM+FDB5CsyxtQJYeKq83arPe5wgbNmcFXGqiSH2XR8dT/fJISVA1r/zQ==" crossorigin=""/>
	<script src="https://unpkg.com/leaflet@1.2.0/dist/leaflet.js" integrity="sha512-lInM/apFSqyy1o6s89K4iQUKg6ppXEgsVxT35HbzUupEVRh2Eu9Wdl4tHj7dZO0s1uvplcYGmt3498TtHq+log==" crossorigin=""></script>
	<title>聚类可视化</title>
	<style>
	#mapid{
	    z-index:2;
	    background-color:#000;
	    height:100%;
	}
	#menu{
	    z-index:2;
	    position:absolute;
	    top:20px;
	    right:20px
	}
</style>
</head>
<body style="height: 100%; margin: 0">
<%
	/*	int[] GridNum = new int[9];
		String[] grid = {"","","","","","","","",""};
		for(int i=0;i<9;i++){
			GridNum[i] = (int)(4096/(Math.pow(2,i)));
			String url = "C:\\Users\\geo1\\Desktop\\Grid_Json\\Grid_Json_"+""+GridNum[i]+""+".txt";
			grid[i] = ReadTxt.TransTxt2Json(url); 
		}*/
		int GridNum =4096;
		//String url = "C:\\Users\\geo1\\Desktop\\Grid_Json\\Grid_Json_1024.txt";
		String url = "C:\\Users\\geo1\\Desktop\\Observation_Scale\\Observation_Scale_16_1.txt";
		String grid = ReadTxt.TransTxt2Json(url);
%>
<div id="mapid"></div>
<div id="menu">
<select id="scale" value="100">
	<option value="0">4096x4096</option>
	<option value="1">2048x2048</option>
	<option value="2">1024x1024</option>
	<option value="3">512x512</option>
	<option value="4">256x256</option>
	<option value="5">128x128</option>
	<option value="6">64x64</option>
	<option value="7">32x32</option>
	<option value="8">16x16</option>
</select>
<button id="find" onclick="find()">查询</button>
</div>

<script type="text/javascript">
	function getColorByArea(area){    
	    var r=0;  
	    var g=0;  
	    var b=0; 
	    if ( area<3) {   
	        r=255;  
	        g=255; 
	        b=0;
	    }  
	    else if ( area>=3&&area<30 ) {   
	        r=0;  
	        g=255; 
	        b=255;
	    } 
	    else if ( area>=30&&area<60 ) {   
	        r=255;  
	        g=0; 
	        b=0;
	    } 
	    else if ( area>=60&&area<120 ) {   
	        r=0;  
	        g=0; 
	        b=255;
	    } 
	    else if ( area>=120&&area<200 ) {   
	        r=128;  
	        g=0; 
	        b=255;
	    } 
	    else if ( area>=200&&area<= 300) {   
	        r=0;  
	        g=255; 
	        b=0;
	    } 
	    else if ( area > 300 ) {   
	        r=255;  
	        g=128; 
	        b=0;
	    } 
	    return "rgb("+r+","+g+","+b+")";            
	} 
	function getColorByID(classID){    
	    var r=0;  
	    var g=0;  
	    var b=0; 
	    if (classID%7==0) {   
	        r=0;  
	        g=0; 
	        b=255;
	    }  
	    else if (classID%7==1) {   
	        r=128;  
	        g=0; 
	        b=255;
	    } 
	    else if (classID%7==2) {   
	        r=255;  
	        g=255; 
	        b=0;
	    } 
	    else if (classID%7==3) {   
	        r=0;  
	        g=255; 
	        b=0;
	    } 
	    else if (classID%7==4) {   
	        r=255;  
	        g=0; 
	        b=0;
	    } 
	    else if (classID%7==5) {   
	        r=0;  
	        g=255; 
	        b=255;
	    } 
	    else if (classID%7==6) {   
	        r=255;  
	        g=128; 
	        b=0;
	    } 
	    return "rgb("+r+","+g+","+b+")";            
	} 
	function getOpacitybyDensity(density, grid_num){
		var opacity = 0;
		density = density*grid_num/4096;
		if(density<5){
			opacity = 0.1;
		}
		else if (density>=5 && density<15){
			opacity = 0.2;
		}
		else if (density>=15 && density<30){
			opacity = 0.3;
		}
		else if (density>=30 && density<50){
			opacity = 0.4;
		}
		else if (density>=50 && density<75){
			opacity = 0.5;
		}
		else if (density>=75 && density<105){
			opacity = 0.6;
		}
		else if (density>=105 && density<150){
			opacity = 0.7;
		}
		else if (density>=150 && density<200){
			opacity = 0.8;
		}
		else if (density>=200 && density<300){
			opacity = 0.9;
		}
		else if (density>300){
			opacity = 1;
		}
		return opacity;
	}
	/*var osmUrl = 'https://api.mapbox.com/styles/v1/pdh-951206/cjaqh7dd84s4k2sn18tiazv06/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1IjoicGRoLTk1MTIwNiIsImEiOiJjamFxYnczODc0OHhzMzNucWZ1YWt3N3FoIn0.8itoMu9dQl9nrYxDRWTqxA',
    osm = L.tileLayer(osmUrl, {
        maxZoom: 18,
        minZoom: 4
    });
	var map = new L.Map('mapid').addLayer(osm).setView([38, 104], 4);*/
//	var map = L.map('mapid').setView([31.23,121.1], 7);
	var map = L.map('mapid').setView([38,104], 4);
	L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
		attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
		opacity:0,
		maxZoom: 18,
	    minZoom: 4
	}).addTo(map);

	var Point = <%=grid%>;
	var num_grid = <%=GridNum%>;

//	alert(Point.length);
		var grid_width = (135.1-73.5)/num_grid;
		var grid_height = (53.6-18.1)/num_grid;
		layers = [];
		myGroup = L.layerGroup(layers);
		for(var i=0;i<Point.length;i++){
			index = Point[i].index;
			density = Point[i].density;
			region = Point[i].region;
			//alert(Point[i].region);
			area = Point[i].area;
			minLat = 18.1+grid_height*Math.floor((index)/num_grid);
			minLng = 73.5+grid_width*(index%num_grid);
			var str = getColorByID(region);
			var stropacity = getOpacitybyDensity(density, num_grid);
			polygon = L.polygon([
			   		[minLat, minLng],
			   		[minLat+grid_height, minLng],			   	
			   		[minLat+grid_height, minLng+grid_width],
			   		[minLat, minLng+grid_width]
			   	],{
			   		color: str,
			   		fillColor: str,
			   		weight:1, 
			   		fillOpacity:stropacity
			   	});
			layers.push(polygon);
		}
	myGroup = L.layerGroup(layers).addTo(map);

</script>
</body>
</html>
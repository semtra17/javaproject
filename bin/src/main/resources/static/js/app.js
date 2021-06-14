/**
 * 
 */

let tableUser = document.getElementById("table-user");
let tableProfiles = document.getElementById("table-profiles");
let btnUser = document.getElementById("btnPestañaUser");
let btnProfiles = document.getElementById("btnPestañaProfiles");



function pestañaUser(){
	tableUser.classList.remove("noshow");
	tableProfiles.classList.remove("active");
		btnUser.classList.remove("nocolor");
	btnProfiles.classList.remove("color");
}
function pestañaProfiles(){
	btnProfiles.classList.add("color");
	btnUser.classList.add("nocolor");
	tableProfiles.classList.add("active");
	tableUser.classList.add("noshow");
}
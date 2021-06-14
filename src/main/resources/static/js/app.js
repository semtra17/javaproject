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
	btnUser.classList.add("color");
	btnProfiles.classList.remove("color");
}
function pestañaProfiles(){
	btnProfiles.classList.add("color");
	btnUser.classList.add("nocolor");
	btnUser.classList.remove("color");
	tableProfiles.classList.add("active");
	tableUser.classList.add("noshow");
}
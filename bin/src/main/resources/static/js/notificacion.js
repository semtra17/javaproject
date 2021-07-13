/**
 * 
 */



let mensajeError = document.getElementsByClassName("mensajeError");
let mensajeConfirmacion = document.getElementsByClassName("mensajeConfirmacion");
let notification = document.getElementById("notification");
let imgsNot = notification.getElementsByTagName("img");


function mostrarNotificacion(){
	if(mensajeConfirmacion[0].textContent !== ""){
		mensajeConfirmacion[0].classList.add("show");
		notification.classList.toggle("active");
		imgsNot[0].classList.toggle("active");
	}
	
	if(mensajeError[0].textContent !== ""){
		mensajeError[0].classList.add("show");
		notification.classList.toggle("active");
		imgsNot[1].classList.toggle("active");
	}
	
}
mostrarNotificacion();

function cerrarNot(){
	notification.classList.toggle("active");
}

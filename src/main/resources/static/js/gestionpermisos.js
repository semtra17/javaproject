/**
 * 
 */

let navPermisos = document.getElementById("navPermisos");
let btnNavsPermisos = (document.getElementById("navPermisos").getElementsByTagName("a"));

let forms = document.getElementById("forms").getElementsByTagName("section");
let navTipoPermiso = document.getElementById("navTipoPermiso");
let formsPeriodoDiario = document.getElementById("formPeriodoDiario").getElementsByTagName("form");
let btnTipoPermiso = document.getElementsByClassName("card btnTipoPermiso");
let advertencias = document.getElementsByClassName("advertencia");
let allForms = document.getElementsByClassName("formAction");

let cantidadDias = document.getElementById("cantidadDias");
let inputDocumentoPermiso = document.getElementsByClassName("inputDocumento");
let inputNombre = document.getElementsByClassName("inputNombre");
let inputDominio = document.getElementsByClassName("inputDominio");










navPermisos.addEventListener("click", function(e){
	
	if((e.target).className  == "btnNavPermiso"){
		
		for(let i = 0; i< 4; i++){
			btnNavsPermisos[i].classList.remove("active");
			
		}
		(e.target).classList.add("active");
	
	}
	
	
});

function removerCamposIncorrectos(){
let camposIncorrectos = document.getElementsByClassName("formularioIncorrecto");
let camposValidados = document.getElementsByClassName("formularioCorrecto");
	if(camposIncorrectos.length > 0){
		for(i = 0; i < camposIncorrectos.length; i++){
			camposIncorrectos[i].classList.remove("formularioIncorrecto");
		
		}
	
	}
	
	if(camposValidados.length > 0){
		for(i = 0; i < camposValidados.length; i++){
			camposValidados[i].classList.remove("formularioCorrecto");
		}
	
	}

}
// NAVEGACION MENU LATERAL
// NAVEGACION MENU LATERAL
// NAVEGACION MENU LATERAL
// NAVEGACION MENU LATERAL
function instrucciones(){
	removerCamposIncorrectos()
	for(let i = 0; i<4; i++){
		allForms[i].reset()
		forms[i].classList.remove("active");
		advertencias[i].classList.remove("show");
	}
	forms[0].classList.add("active");
}

function formPersona(){
	removerCamposIncorrectos()
	for(let i = 0; i<4; i++){
		allForms[i].reset()
		forms[i].classList.remove("active");
		advertencias[i].classList.remove("show");
	}
	forms[1].classList.add("active");
}

function formRodado(){
	removerCamposIncorrectos()
	for(let i = 0; i<4; i++){
		allForms[i].reset()
		advertencias[i].classList.remove("show");
		forms[i].classList.remove("active");
	}
	forms[2].classList.add("active");
}

function formPermiso(){
	removerCamposIncorrectos()
	for(let i = 0; i<4; i++){
		allForms[i].reset()
		forms[i].classList.remove("active");
		advertencias[i].classList.remove("show");
	}
	forms[3].classList.add("active");

}

// NAVEGACION TIPOS DE PERMISOS
// NAVEGACION TIPOS DE PERMISOS
// NAVEGACION TIPOS DE PERMISOS
// NAVEGACION TIPOS DE PERMISOS
navTipoPermiso.addEventListener("click",function(e){
	let btns = navTipoPermiso.getElementsByTagName("a");
	for(let i = 0; i<2 ; i++){
	
		btns[i].classList.remove("active");
	}
	(e.target).classList.toggle("active");

})
function formPermisoDiario(){
	
	for(let i = 0; i<2 ; i++){
	
		formsPeriodoDiario[i].classList.remove("active");
		btnTipoPermiso[i].classList.remove("active");
	}
	formsPeriodoDiario[0].classList.toggle("active");
	btnTipoPermiso[0].classList.toggle("active");
}
function formPermisoPeriodo(){
	
	for(let i = 0; i<2 ; i++){
	
		formsPeriodoDiario[i].classList.remove("active");
		btnTipoPermiso[i].classList.remove("active");
	}
	formsPeriodoDiario[1].classList.toggle("active");
		btnTipoPermiso[1].classList.toggle("active");
}




	









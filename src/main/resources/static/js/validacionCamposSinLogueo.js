/**
 * 
 */

/**
 * 
 */

let formularios = document.getElementsByClassName("formAction");
let inputs = document.getElementsByTagName("input");

let advertencia = document.getElementsByClassName("advertencia");
console.log(inputs);
const expresiones = {
	nombre: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
	documento: /^\d{7,8}$/, // 8 numeros.
	dominio: /^[a-zA-Z0-9]{6,7}$/, // Letras, numeros, guion y guion_bajo
}

	
	 function comprobarCampos(input){
		switch(input.name){
		case "nombre":
			if(!expresiones.nombre.test(input.value) || input.value == ""){
				input.classList.remove("formularioCorrecto");
				input.classList.add("formularioIncorrecto");
			}else{
				input.classList.add("formularioCorrecto");
				input.classList.remove("formularioIncorrecto");
			}
		
			break;
		case "apellido":
			if(!expresiones.nombre.test(input.value) || input.value == "" ){
				input.classList.remove("formularioCorrecto");
				input.classList.add("formularioIncorrecto");
			}else{
				input.classList.add("formularioCorrecto");
				input.classList.remove("formularioIncorrecto");
			}
			break;
		case "documento":
			if(!expresiones.documento.test(input.value) || input.value == ""){
				input.classList.remove("formularioCorrecto");
				input.classList.add("formularioIncorrecto");
			}else{
				input.classList.add("formularioCorrecto");
				input.classList.remove("formularioIncorrecto");
			}
			break;
		case "password":
			if(input.value == ""){
				input.classList.remove("formularioCorrecto");
				input.classList.add("formularioIncorrecto");
			}else{
				input.classList.add("formularioCorrecto");
				input.classList.remove("formularioIncorrecto");
			}
			break;
		case "email":
				if(input.value == ""){
					input.classList.remove("formularioCorrecto");
					input.classList.add("formularioIncorrecto");
				}else{
					input.classList.add("formularioCorrecto");
					input.classList.remove("formularioIncorrecto");
				}
			break;
		case "username":
				if(input.value == ""){
					input.classList.remove("formularioCorrecto");
					input.classList.add("formularioIncorrecto");
				}else{
					input.classList.add("formularioCorrecto");
					input.classList.remove("formularioIncorrecto");
				}
			break;
		
		case "description":
				if(input.value == ""){
					input.classList.remove("formularioCorrecto");
					input.classList.add("formularioIncorrecto");
				}else{
					input.classList.add("formularioCorrecto");
					input.classList.remove("formularioIncorrecto");
				}
			break;
		case "dominio":
				if(!expresiones.dominio.test(input.value)   || input.value == ""){
					input.classList.remove("formularioCorrecto");
					input.classList.add("formularioIncorrecto");
				}else{
					input.classList.add("formularioCorrecto");
					input.classList.remove("formularioIncorrecto");
				}
			break;
		case "fecha":
				if(input.value == ""){
					input.classList.remove("formularioCorrecto");
					input.classList.add("formularioIncorrecto");
				}else{
					input.classList.add("formularioCorrecto");
					input.classList.remove("formularioIncorrecto");
				}
			break;
		case "cantidadDias":
				if(input.value == "" || input.value == "0" ){
					input.classList.remove("formularioCorrecto");
					input.classList.add("formularioIncorrecto");
				}else{
					input.classList.add("formularioCorrecto");
					input.classList.remove("formularioIncorrecto");
				}
			break;
		case "vehiculo":
				if(input.value == ""){
					input.classList.remove("formularioCorrecto");
					input.classList.add("formularioIncorrecto");
				}else{
					input.classList.add("formularioCorrecto");
					input.classList.remove("formularioIncorrecto");
				}
			break;
		}
		
		
	}
function validarFormulario(tipoForm){
	if(document.getElementsByClassName("formularioIncorrecto").length > 0){
		switch(tipoForm.className){
			case "formAction persona":
				advertencia[0].classList.add("show");
			return false;
			case "formAction rodado":
				advertencia[1].classList.add("show");
			return false;
			case "formAction diario":
				advertencia[2].classList.add("show");
			return false;
			case "formAction periodo":
				advertencia[3].classList.add("show");
			return false;	
		}
		advertencia[0].classList.add("show");
		return false;
	}else{
		for(let i = 0; i< advertencia.length; i++){
		advertencia[i].classList.remove("show");
			
		}
		return true;
	}
	
}
function verificarIngreso(){
	for(let i = 0; i < inputs.length ; i++){
		inputs[i].addEventListener("keyup", function(e){
			comprobarCampos(e.target);
			
		})
		inputs[i].addEventListener("blur", function(e){
			comprobarCampos(e.target);
		})
		
		
	}
}
verificarIngreso();

for(let j = 0 ;j < formularios.length; j++){
	
	
		formularios[j].addEventListener("submit",function(e){
			e.preventDefault();
			verificarIngreso();
			let inputsDeFormEnAccion = e.target.getElementsByTagName("input");
			
			for(let i = 0; i< inputsDeFormEnAccion.length; i++){
				comprobarCampos(inputsDeFormEnAccion[i]);
			}
			if(validarFormulario(formularios[j])){
				e.target.submit()
			}
			
		})
		
		
}
	

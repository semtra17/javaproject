/**
 * 
 */

let form = document.getElementById("form");
	let inputs = document.getElementsByTagName("input");
	let advertencia = document.getElementsByClassName("advertencia");
	const expresiones = {
		nombre: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
		documento: /^\d{7,8}$/, // 8 numeros.
	}

	
	 function comprobarCampos(input){
		switch(input.name){
		case "name":
			if(!expresiones.nombre.test(input.value) || input.value == ""){
				input.classList.remove("formularioCorrecto");
				input.classList.add("formularioIncorrecto");
			}else{
				input.classList.add("formularioCorrecto");
				input.classList.remove("formularioIncorrecto");
			}
		
			break;
		case "lastname":
			if(!expresiones.nombre.test(input.value) || input.value == "" ){
				input.classList.remove("formularioCorrecto");
				input.classList.add("formularioIncorrecto");
			}else{
				input.classList.add("formularioCorrecto");
				input.classList.remove("formularioIncorrecto");
			}
			break;
		case "document":
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
		}
		
		
	}
	
function validarFormulario(){
	if(document.getElementsByClassName("formularioIncorrecto").length > 0){
		advertencia[0].classList.add("show");
		return false;
	}else{
	
		advertencia[0].classList.remove("show");
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
form.addEventListener("submit",function(e){
	e.preventDefault();
	verificarIngreso();
	for(let i = 0; i< inputs.length; i++){
		comprobarCampos(inputs[i]);
	}
	if(validarFormulario()){
		e.target.submit()
	}
	
})
	

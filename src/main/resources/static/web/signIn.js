//Ejecutando funciones
document.getElementById("btn__iniciar-sesion").addEventListener("click", iniciarSesion);
document.getElementById("btn__registrarse").addEventListener("click", register);
window.addEventListener("resize", anchoPage);

//Declarando variables
var formulario_login = document.querySelector(".formulario__login");
var formulario_register = document.querySelector(".formulario__register");
var contenedor_login_register = document.querySelector(".contenedor__login-register");
var caja_trasera_login = document.querySelector(".caja__trasera-login");
var caja_trasera_register = document.querySelector(".caja__trasera-register");
var btnSignIn = document.getElementById("signIn");
var btnSignUp = document.getElementById("signUp");

var emailSingIn = document.getElementById("emailSingIn");
var passSingIn = document.getElementById("passSingIn");

var emailSingUp = document.getElementById("emailSingUp");
var fistNameSingUp = document.getElementById("fistNameSingUp");
var lastNameSingUp = document.getElementById("lastNameSingUp");
var passSingUp = document.getElementById("passSingUp");

    //FUNCIONES

function anchoPage(){

    if (window.innerWidth > 850){
        caja_trasera_register.style.display = "block";
        caja_trasera_login.style.display = "block";
    }else{
        caja_trasera_register.style.display = "block";
        caja_trasera_register.style.opacity = "1";
        caja_trasera_login.style.display = "none";
        formulario_login.style.display = "block";
        contenedor_login_register.style.left = "0px";
        formulario_register.style.display = "none";   
    }
}

anchoPage();


    function iniciarSesion(){
        if (window.innerWidth > 850){
            formulario_login.style.display = "block";
            contenedor_login_register.style.left = "10px";
            formulario_register.style.display = "none";
            caja_trasera_register.style.opacity = "1";
            caja_trasera_login.style.opacity = "0";
        }else{
            formulario_login.style.display = "block";
            contenedor_login_register.style.left = "0px";
            formulario_register.style.display = "none";
            caja_trasera_register.style.display = "block";
            caja_trasera_login.style.display = "none";
        }
    }

    function register(){
        if (window.innerWidth > 850){
            formulario_register.style.display = "block";
            contenedor_login_register.style.left = "410px";
            formulario_login.style.display = "none";
            caja_trasera_register.style.opacity = "0";
            caja_trasera_login.style.opacity = "1";
        }else{
            formulario_register.style.display = "block";
            contenedor_login_register.style.left = "0px";
            formulario_login.style.display = "none";
            caja_trasera_register.style.display = "none";
            caja_trasera_login.style.display = "block";
            caja_trasera_login.style.opacity = "1";
        }
}

btnSignIn.addEventListener('click', (e)=>{
    e.preventDefault();

    axios.post('/api/login',`email=${emailSingIn.value}&password=${passSingIn.value}`)
    .then(response => window.location.href="/web/index.html")
    .catch(() =>{
          this.errorMsg = "Sign in failed, check the information"
          this.errorToats.show();
    })
});

btnSignUp.addEventListener('click', (e)=>{
    e.preventDefault();

    axios.post('/api/clients',`firstName=${fistNameSingUp.value}&lastName=${lastNameSingUp.value}&email=${emailSingUp.value}&password=${passSingUp.value}`)
    .then(() => {
        axios.post('/api/login',`email=${emailSingUp.value}&password=${passSingUp.value}`)
        .then(response => window.location.href="/web/index.html")
    })
    .catch(() =>{
         this.errorMsg = "Sign up failed, check the information"
         this.errorToats.show();
    })

});
const tarjeta = document.querySelector('#tarjeta');
const btnAbrirFormulario = document.querySelector('#btn-abrir-formulario');
const formulario = document.querySelector('#formulario-tarjeta');
const numeroTarjeta = document.querySelector('#tarjeta .numero');
const nombreTarjeta = document.querySelector('#tarjeta .nombre');
const logoMarca = document.querySelector('#logo-marca');
const firma = document.querySelector('#tarjeta .firma p');
const mesExpiracion = document.querySelector('#tarjeta .mes');
const yearExpiracion = document.querySelector('#tarjeta .year');
const ccv = document.querySelector('#tarjeta .ccv');
const btnSubmit = document.querySelector('.btn-enviar');
const dire = document.querySelector('#dire');
const direNum = document.querySelector('#direNum');
const codPostal = document.querySelector('#codPostal');
const ciudad = document.querySelector('#ciudad');

var total = 0;
var DOMtotal = document.querySelector('#total');
var DOMcarrito = document.querySelector('#carrito');
var carrito = [];
var srcImgCart = '';
var textCant = '';
var precioNowCart = 0;
var textPrecio = '';

// * Volteamos la tarjeta para mostrar el frente.
const mostrarFrente = () => {
	if(tarjeta.classList.contains('active')){
		tarjeta.classList.remove('active');
	}
}

// * Rotacion de la tarjeta
tarjeta.addEventListener('click', () => {
	tarjeta.classList.toggle('active');
});

// * Boton de abrir formulario
btnAbrirFormulario.addEventListener('click', () => {
	btnAbrirFormulario.classList.toggle('active');
	formulario.classList.toggle('active');
});

// * Select del mes generado dinamicamente.
for(let i = 1; i <= 12; i++){
	let opcion = document.createElement('option');
	opcion.value = i;
	opcion.innerText = i;
	formulario.selectMes.appendChild(opcion);
}

// * Select del año generado dinamicamente.
const yearActual = new Date().getFullYear();
for(let i = yearActual; i <= yearActual + 8; i++){
	let opcion = document.createElement('option');
	opcion.value = i;
	opcion.innerText = i;
	formulario.selectYear.appendChild(opcion);
}

// * Input numero de tarjeta
formulario.inputNumero.addEventListener('keyup', (e) => {
	let valorInput = e.target.value;

	formulario.inputNumero.value = valorInput
	// Eliminamos espacios en blanco
	.replace(/\s/g, '')
	// Eliminar las letras
	.replace(/\D/g, '')
	// Ponemos espacio cada cuatro numeros
	.replace(/([0-9]{4})/g, '$1 ')
	// Elimina el ultimo espaciado
	.trim();

	numeroTarjeta.textContent = valorInput;

	if(valorInput == ''){
		numeroTarjeta.textContent = '#### #### #### ####';

		logoMarca.innerHTML = '';
	}

	if(valorInput[0] == 4){
		logoMarca.innerHTML = '';
		const imagen = document.createElement('img');
		imagen.src = './images/visa.png';
		logoMarca.appendChild(imagen);
	} else if(valorInput[0] == 5){
		logoMarca.innerHTML = '';
		const imagen = document.createElement('img');
		imagen.src = './images/mastercard.png';
		logoMarca.appendChild(imagen);
	}

	// Volteamos la tarjeta para que el usuario vea el frente.
	mostrarFrente();
});

// * Input nombre de tarjeta
formulario.inputNombre.addEventListener('keyup', (e) => {
	let valorInput = e.target.value;

	formulario.inputNombre.value = valorInput.replace(/[0-9]/g, '');
	nombreTarjeta.textContent = valorInput;
	firma.textContent = valorInput;

	if(valorInput == ''){
		nombreTarjeta.textContent = 'Jhon Doe';
	}

	mostrarFrente();
});

// * Select mes
formulario.selectMes.addEventListener('change', (e) => {
	mesExpiracion.textContent = e.target.value;
	mostrarFrente();
});

// * Select Año
formulario.selectYear.addEventListener('change', (e) => {
	yearExpiracion.textContent = e.target.value.slice(2);
	mostrarFrente();
});

// * CCV
formulario.inputCCV.addEventListener('keyup', () => {
	if(!tarjeta.classList.contains('active')){
		tarjeta.classList.toggle('active');
	}

	formulario.inputCCV.value = formulario.inputCCV.value
	// Eliminar los espacios
	.replace(/\s/g, '')
	// Eliminar las letras
	.replace(/\D/g, '');

	ccv.textContent = formulario.inputCCV.value;
});

async function renderizarCarrito() {

    DOMcarrito.textContent = '';

    await axios.get("/api/carrito")
    .then((response) => {

         response.data.productos.forEach((item) => {
              let dataProd = {
                              "id": item.id,
                              "name": item.nombre,
                              "price": item.precio,
                              "discount": item.descuento,
                              "cant": item.cantidad
                             }

              carrito.push(dataProd);
              })

    }).catch((error)=>{
        // handle error
        console.log("Error getting data " + error)
    });

    carrito.forEach((item) => {

        console.log(item);
        const miNodo = document.createElement('div');
        miNodo.classList.add('cart');

        const miNodoImagCart = document.createElement('img');
        miNodoImagCart.classList.add('cart__img');
        srcImgCart = './images/productos/'+item.name+'.jfif';
        miNodoImagCart.setAttribute('src', srcImgCart);

        const miNodoDetalle = document.createElement('div');
        miNodoDetalle.classList.add('cart__detalle');

        const miNodoName = document.createElement('p');
        miNodoName.classList.add('cart__name');
        miNodoName.textContent = `${item.name}`;

        const miNodoCant = document.createElement('p');
        miNodoCant.classList.add('cart__cantidad');
        textCant = 'Cantidad: ' + item.cant + ' uni.';
        miNodoCant.textContent = textCant;


        const miNodoPrecio = document.createElement('p');
        miNodoPrecio.classList.add('cart__precio');
        precioNowCart = parseFloat(item.price * (100 - item.discount) / 100);
        textPrecio = '$ ' + precioNowCart;
        miNodoPrecio.textContent = textPrecio;

        const miNodoPrecioSpan = document.createElement('p');
        miNodoPrecioSpan.classList.add('cart__precio-span');
        miNodoPrecioSpan.textContent = ' X uni.';

        miNodoDetalle.appendChild(miNodoName);
        miNodoDetalle.appendChild(miNodoCant);

        miNodo.appendChild(miNodoImagCart);
        miNodo.appendChild(miNodoDetalle);
        miNodo.appendChild(miNodoPrecio);
        miNodo.appendChild(miNodoPrecioSpan);
        DOMcarrito.appendChild(miNodo);
    });

/**
 * Calcula el precio total teniendo en cuenta los productos repetidos
 */
    await axios.get("/api/carrito/total")
    .then((response) => {
        total = response.data;

    }).catch((error)=>{
        // handle error
        console.log("Error getting data " + error)
    });


    DOMtotal.textContent = total;
}

renderizarCarrito();


btnSubmit.addEventListener('click', (e)=>{

    e.preventDefault();
    var ccvNumber=0;
    var mesExpiracionNumber=0;
    var yearExpiracionNumber=0;

    ccvNumber=parseInt(ccv.innerHTML);
    mesExpiracionNumber=parseInt(mesExpiracion.innerHTML);
    yearExpiracionNumber=parseInt(yearExpiracion.innerHTML);

    console.log(DOMtotal.innerHTML);
    console.log(dire.value);
    console.log(direNum.value);
    console.log(codPostal.value);
    console.log(ciudad.value);
    console.log(numeroTarjeta.innerHTML);
    console.log(ccvNumber);
    console.log(yearExpiracionNumber);
    console.log(mesExpiracionNumber);

    let dataTicket = {
            "montoTotal": DOMtotal.innerHTML,
            "dirreccion": dire.value,
            "dirreccionNum": direNum.value,
            "codigoPostal": codPostal.value,
            "ciudad": ciudad.value,
            "numTarjeta": numeroTarjeta.innerHTML,
            "cvv": ccvNumber,
            "anioVencimiento": yearExpiracionNumber,
            "mesVencimiento": mesExpiracionNumber
    }

    console.log(dataTicket);

    axios.post("/api/tickets",{montoTotal: dataTicket.montoTotal, dirreccion: dataTicket.dirreccion, dirreccionNum: dataTicket.dirreccionNum, codigoPostal: dataTicket.codigoPostal, ciudad: dataTicket.ciudad, numTarjeta: dataTicket.numTarjeta, cvv: dataTicket.cvv, anioVencimiento: dataTicket.anioVencimiento, mesVencimiento: dataTicket.mesVencimiento})
    .then((response) => window.location.href="/web/index.html")
    .catch((error)=>{
        // handle error
        console.log("Error getting data " + error)
    });

});
const getProductoList = [
    {
        id: 1,
        name: 'remera1',
        price: 270,
        discount: 50,
        stock: 20,
        type: 'Remeras'
    },
    {
        id: 2,
        name: 'remera2',
        price: 300,
        discount: 20,
        stock: 20,
        type: 'Remeras'
    },
    {
        id: 3,
        name: 'remera3',
        price: 200,
        discount: 15,
        stock: 20,
        type: 'Remeras'
    },
    {
        id: 4,
        name: 'remera4',
        price: 250,
        discount: 30,
        stock: 20,
        type: 'Remeras'
    },
    {
        id: 5,
        name: 'zapatilla1',
        price: 270,
        discount: 50,
        stock: 20,
        type: 'Zapatillas'
    },
    {
        id: 6,
        name: 'zapatilla2',
        price: 300,
        discount: 20,
        stock: 20,
        type: 'Zapatillas'
    },
    {
        id: 7,
        name: 'zapatilla3',
        price: 200,
        discount: 15,
        stock: 20,
        type: 'Zapatillas'
    },
    {
        id: 8,
        name: 'gorra1',
        price: 270,
        discount: 50,
        stock: 20,
        type: 'Gorras'
    },
    {
        id: 9,
        name: 'gorra2',
        price: 300,
        discount: 20,
        stock: 20,
        type: 'Gorras'
    },
    {
        id: 10,
        name: 'gorra3',
        price: 200,
        discount: 15,
        stock: 20,
        type: 'Gorras'
    },
    {
        id: 11,
        name: 'gorra4',
        price: 250,
        discount: 30,
        stock: 20,
        type: 'Gorras'
    },
    {
        id: 12,
        name: 'capera1',
        price: 270,
        discount: 50,
        stock: 20,
        type: 'Camperas'
    },
    {
        id: 13,
        name: 'capera2',
        price: 300,
        discount: 20,
        stock: 20,
        type: 'Camperas'
    },
    {
        id: 14,
        name: 'capera3',
        price: 200,
        discount: 15,
        stock: 20,
        type: 'Camperas'
    },
    {
        id: 15,
        name: 'capera4',
        price: 250,
        discount: 30,
        stock: 20,
        type: 'Camperas'
    }
];

let productoList = [];
let carrito = [];
const divisa = '$';
const porcentaje = '% OFF';
const DOMitems = document.querySelector('#items');
var userImputNumber = 0;
var cartNotification = document.querySelector('.header__cart--notification');

var linkDeslogeado = document.querySelector('.modal-perfil__link');
var btnCompras = document.getElementById('compras');
var btnSignOut = document.getElementById('signOut');

const cartIconBtn = document.querySelector('.header__cart');
const cartModal = document.querySelector('.modal-cart');
const perfilIconBtn = document.querySelector('.header__avatar');
const perfilModal = document.querySelector('.modal-perfil');
const navIconBtn = document.querySelector('.header__menu');

const navProd1Btn = document.querySelector('#navbar-1');
const navProd2Btn = document.querySelector('#navbar-2');
const navProd3Btn = document.querySelector('#navbar-3');
const navProd4Btn = document.querySelector('#navbar-4');

const modalNavProd1Btn = document.querySelector('#modal-navbar-1');
const modalNavProd2Btn = document.querySelector('#modal-navbar-2');
const modalNavProd3Btn = document.querySelector('#modal-navbar-3');
const modalNavProd4Btn = document.querySelector('#modal-navbar-4');

const closeNavModalBtn = document.querySelector('.modal-navbar__close-icon');
const navModal = document.querySelector('.modal-navbar__background');
const DOMcarrito = document.querySelector('.modal-cart__details');
const DOMtotal = document.querySelector('#total');
var lastValue = 0;
var cantIntem = 0;
var modalCart = 0;
var modalPerfil = 0;
// Funciones
/**
* Dibuja todos los productos a partir de la base de datos.
*/
function recuperarProducto() {
    var product = document.querySelector('.navbar__link-select');
    productoList = [];
    getProductoList.forEach((item) => {
        if (item.type === product.innerText){
            productoList.push(item);
        };
    });
}

function renderizarProductos() {
    DOMitems.textContent = '';
    recuperarProducto();
    // Estructura
    const row = document.createElement('div');
    row.classList.add('row');
    // Estructura
    const col = document.createElement('div');
    col.classList.add('col-12','col-md-3','mt-2');

    productoList.forEach((proct) => {
        // Estructura
        const miNodo = document.createElement('div');
        miNodo.classList.add('product');
        // Titulo
        const miNodoTitle = document.createElement('h2');
        miNodoTitle.classList.add('product__name');
        miNodoTitle.textContent = proct.name;
        // Imagen
        const miNodoImagen = document.createElement('img');
        miNodoImagen.classList.add('product__img');
        var srcImg = './images/' + proct.type + '/' + proct.name + '.jfif';
        miNodoImagen.setAttribute('src', srcImg);
        
        miNodo.appendChild(miNodoTitle);
        miNodo.appendChild(miNodoImagen); 
        // Cuerpo Precio
         const miNodoCardBodyPrices = document.createElement('div');
        miNodoCardBodyPrices.classList.add('product__prices');
        // Precio
        const miNodoNow = document.createElement('p');
        miNodoNow.classList.add('product__now');
        var precioNow = parseFloat(proct.price * (100 - proct.discount) / 100);
        miNodoNow.textContent = `${divisa}${precioNow}`;
        const miNodoDiscount = document.createElement('p');
        miNodoDiscount.classList.add('product__discount');
        miNodoDiscount.textContent = `${proct.discount}${porcentaje}`;
        const miNodoBefore = document.createElement('p');
        miNodoBefore.classList.add('product__before');
        miNodoBefore.textContent = `${divisa}${proct.price}`;
        // Cuerpo Cantidad
        const miNodoCardBodyQuantity = document.createElement('div');
        miNodoCardBodyQuantity.classList.add('product__quantity');
        // Input
        const miNodoInput = document.createElement('div');
        miNodoInput.classList.add('input');
        const miNodoImagMinus = document.createElement('img');
        miNodoImagMinus.classList.add('input__minus');
        miNodoImagMinus.setAttribute('src', './images/icon-minus.svg');
        miNodoImagMinus.setAttribute('marcador', proct.id);
        miNodoImagMinus.addEventListener('click', restarProducto);
        const miNodoCant = document.createElement('input');
        miNodoCant.classList.add('input__number');
        miNodoCant.setAttribute('type', 'text');
        miNodoCant.setAttribute('value', '0');
        var idCant = 'number' + proct.id;
        miNodoCant.setAttribute('id', idCant);
        const miNodoImagPlus = document.createElement('img');
        miNodoImagPlus.classList.add('input__plus');
        miNodoImagPlus.setAttribute('src', './images/icon-plus.svg');
        miNodoImagPlus.setAttribute('marcador', proct.id);
        miNodoImagPlus.addEventListener('click', sumarProducto);
        miNodoInput.appendChild(miNodoImagMinus);
        miNodoInput.appendChild(miNodoCant);
        miNodoInput.appendChild(miNodoImagPlus);
        // Boton 
        const miNodoBoton = document.createElement('button');
        miNodoBoton.classList.add('product__button');
        const miNodoImagenBtn = document.createElement('img');
        miNodoImagenBtn.setAttribute('src', './images/icon-cart-white.svg');
        miNodoBoton.appendChild(miNodoImagenBtn);
        miNodoBoton.textContent = 'Add to cart';
        miNodoBoton.setAttribute('marcador', proct.id);
        miNodoBoton.addEventListener('click', aniadirProductoAlCarrito);
        // Insertamos
        miNodo.appendChild(miNodoTitle);
        miNodo.appendChild(miNodoImagen);
        miNodoCardBodyPrices.appendChild(miNodoNow);
        miNodoCardBodyPrices.appendChild(miNodoDiscount);
        miNodoCardBodyPrices.appendChild(miNodoBefore);
        miNodo.appendChild(miNodoCardBodyPrices);
        miNodoCardBodyQuantity.appendChild(miNodoInput);
        miNodoCardBodyQuantity.appendChild(miNodoBoton);
        miNodo.appendChild(miNodoCardBodyQuantity);
        col.appendChild(miNodo);
    });
    row.appendChild(col);
    DOMitems.appendChild(row);

    axios.get('/api/clients/current')
    .then(response => {
                btnCompras.style.display = 'block';
                btnSignOut.style.display = 'block';
                linkDeslogeado.style.display = 'none';
                console.log(response.data);
    })
    .catch((error) =>{
                btnCompras.style.display = 'none';
                btnSignOut.style.display = 'none';
                linkDeslogeado.style.display = 'block';
                console.log(error);
    })
}

function sumarProducto(evento) {

    var idCantFind = '#number' + evento.target.getAttribute('marcador');
    var userImput = document.querySelector(idCantFind);
    userImputNumber=parseInt(userImput.getAttribute('value'));
    userImputNumber++;
    userImput.setAttribute('value',userImputNumber);

}

function restarProducto(evento) {

    var idCantFind = '#number' + evento.target.getAttribute('marcador');
    var userImput = document.querySelector(idCantFind);
    userImputNumber=parseInt(userImput.getAttribute('value'));
    if(userImputNumber>=1){
        userImputNumber--;
        userImput.setAttribute('value',userImputNumber);
    }

}

function aniadirProductoAlCarrito(evento) {
    var idCantFind = '#number' + evento.target.getAttribute('marcador');
    var userImput = document.querySelector(idCantFind);
    userImputNumber=parseInt(userImput.getAttribute('value'));
    for (var i = 0 ; i < userImputNumber ; i++){
          carrito.push(evento.target.getAttribute('marcador'))
    }
    lastValue = parseInt(cartNotification.innerText);
    cantIntem = lastValue + userImputNumber;
    if(cantIntem>=1){
        cartNotification.innerText = cantIntem;
        cartNotification.style.display = 'block';
    };
    userImput.setAttribute('value','0');

}
   
renderizarProductos();

navProd1Btn.addEventListener('click', ()=>{
    if(navProd1Btn.getAttribute('class') == 'navbar__link'){
        var linkSelect = document.querySelector('.navbar__link-select');
        linkSelect.setAttribute('class','navbar__link');
        navProd1Btn.setAttribute('class','navbar__link-select');
        var modalLinkSelect = document.querySelector('.modal-navbar__link-select');
        modalLinkSelect.setAttribute('class','modal-navbar__link');
        modalNavProd1Btn.setAttribute('class','modal-navbar__link-select');
        renderizarProductos();
    };
});

navProd2Btn.addEventListener('click', ()=>{
    if(navProd2Btn.getAttribute('class') == 'navbar__link'){
        var linkSelect = document.querySelector('.navbar__link-select');
        linkSelect.setAttribute('class','navbar__link');
        navProd2Btn.setAttribute('class','navbar__link-select');
        var modalLinkSelect = document.querySelector('.modal-navbar__link-select');
        modalLinkSelect.setAttribute('class','modal-navbar__link');
        modalNavProd2Btn.setAttribute('class','modal-navbar__link-select');
        renderizarProductos();
    };
});

navProd3Btn.addEventListener('click', ()=>{
    if(navProd3Btn.getAttribute('class') == 'navbar__link'){
        var linkSelect = document.querySelector('.navbar__link-select');
        linkSelect.setAttribute('class','navbar__link');
        navProd3Btn.setAttribute('class','navbar__link-select');
        var modalLinkSelect = document.querySelector('.modal-navbar__link-select');
        modalLinkSelect.setAttribute('class','modal-navbar__link');
        modalNavProd3Btn.setAttribute('class','modal-navbar__link-select');
        renderizarProductos();
    };
});

navProd4Btn.addEventListener('click', ()=>{
    if(navProd4Btn.getAttribute('class') == 'navbar__link'){
        var linkSelect = document.querySelector('.navbar__link-select');
        linkSelect.setAttribute('class','navbar__link');
        navProd4Btn.setAttribute('class','navbar__link-select');
        var modalLinkSelect = document.querySelector('.modal-navbar__link-select');
        modalLinkSelect.setAttribute('class','modal-navbar__link');
        modalNavProd4Btn.setAttribute('class','modal-navbar__link-select');
        renderizarProductos();
    };
});



modalNavProd1Btn.addEventListener('click', ()=>{
    if(modalNavProd1Btn.getAttribute('class') == 'modal-navbar__link'){
        var linkSelect = document.querySelector('.navbar__link-select');
        linkSelect.setAttribute('class','navbar__link');
        navProd1Btn.setAttribute('class','navbar__link-select');
        var modalLinkSelect = document.querySelector('.modal-navbar__link-select');
        modalLinkSelect.setAttribute('class','modal-navbar__link');
        modalNavProd1Btn.setAttribute('class','modal-navbar__link-select');
        renderizarProductos();
    };
});

modalNavProd2Btn.addEventListener('click', ()=>{
    if(modalNavProd2Btn.getAttribute('class') == 'modal-navbar__link'){
        var linkSelect = document.querySelector('.navbar__link-select');
        linkSelect.setAttribute('class','navbar__link');
        navProd2Btn.setAttribute('class','navbar__link-select');
        var modalLinkSelect = document.querySelector('.modal-navbar__link-select');
        modalLinkSelect.setAttribute('class','modal-navbar__link');
        modalNavProd2Btn.setAttribute('class','modal-navbar__link-select');
        renderizarProductos();
    };
});

modalNavProd3Btn.addEventListener('click', ()=>{
    if(modalNavProd3Btn.getAttribute('class') == 'modal-navbar__link'){
        var linkSelect = document.querySelector('.navbar__link-select');
        linkSelect.setAttribute('class','navbar__link');
        navProd3Btn.setAttribute('class','navbar__link-select');
        var modalLinkSelect = document.querySelector('.modal-navbar__link-select');
        modalLinkSelect.setAttribute('class','modal-navbar__link');
        modalNavProd3Btn.setAttribute('class','modal-navbar__link-select');
        renderizarProductos();
    };
});

modalNavProd4Btn.addEventListener('click', ()=>{
    if(modalNavProd4Btn.getAttribute('class') == 'modal-navbar__link'){
        var modalLinkSelect = document.querySelector('.modal-navbar__link-select');
        modalLinkSelect.setAttribute('class','modal-navbar__link');
        modalNavProd4Btn.setAttribute('class','modal-navbar__link-select');
        var linkSelect = document.querySelector('.navbar__link-select');
        linkSelect.setAttribute('class','navbar__link');
        navProd4Btn.setAttribute('class','navbar__link-select');
        var modalLinkSelect = document.querySelector('.modal-navbar__link-select');
        modalLinkSelect.setAttribute('class','modal-navbar__link');
        modalNavProd4Btn.setAttribute('class','modal-navbar__link-select');
        renderizarProductos();
    };
});

cartIconBtn.addEventListener('click', ()=>{
    if(modalCart == 0){
        modalCart = 1;
        cartModal.style.display = 'block';
        modalPerfil = 0;
        perfilModal.style.display = 'none';
        renderizarCarrito();
    }else{
        modalCart = 0;
        cartModal.style.display = 'none';

    };
});

perfilIconBtn.addEventListener('click', ()=>{
    if(modalPerfil == 0){
        modalPerfil = 1;
        perfilModal.style.display = 'block';
        modalCart = 0;
        cartModal.style.display = 'none';

    }else{
        modalPerfil = 0;
        perfilModal.style.display = 'none';

    };
});


navIconBtn.addEventListener('click', ()=>{
    navModal.style.display = 'block';
});


closeNavModalBtn.addEventListener('click', ()=>{
    navModal.style.display = 'none';
});


function renderizarCarrito() {
    DOMcarrito.textContent = '';
    const carritoSinDuplicados = [...new Set(carrito)];
    carritoSinDuplicados.forEach((item) => {
        const miItem = getProductoList.filter((itemProductos) => {
            return itemProductos.id === parseInt(item);
        });
        const numeroUnidadesItem = carrito.reduce((total, itemId) => {
            return itemId === item ? total += 1 : total;
        }, 0);
        const miNodo = document.createElement('div');
        miNodo.classList.add('modal-cart__details-container');
        
        const miNodoImagCart = document.createElement('img');
        miNodoImagCart.classList.add('modal-cart__logo');
        var srcImgCart = './images/' + miItem[0].type + '/' + miItem[0].name + '.jfif';
        miNodoImagCart.setAttribute('src', srcImgCart);

        const miNodoTextCart = document.createElement('p');
        miNodoTextCart.classList.add('modal-cart__price');
        precioNowCart = parseFloat(miItem[0].price * (100 - miItem[0].discount) / 100);
        miNodoTextCart.textContent = `${numeroUnidadesItem} x ${miItem[0].name} - ${precioNowCart}${divisa}`;

        const miNodoDeleteCart = document.createElement('img');
        miNodoDeleteCart.classList.add('modal-cart__delete');
        miNodoDeleteCart.setAttribute('src', './images/icon-delete.svg');
        miNodoDeleteCart.setAttribute('marcador', miItem[0].id);
        miNodoDeleteCart.addEventListener('click', borrarItemCarrito);

        miNodo.appendChild(miNodoImagCart);
        miNodo.appendChild(miNodoTextCart);
        miNodo.appendChild(miNodoDeleteCart);
        DOMcarrito.appendChild(miNodo);
    });
    console.log(calcularTotal());
    DOMtotal.textContent = calcularTotal();
}

/**
* Evento para borrar un elemento del carrito
*/
function borrarItemCarrito(evento) {
    const id = evento.target.getAttribute('marcador');
    carrito = carrito.filter((carritoId) => {
        return carritoId !== id;
    });
    
    cartNotification.innerText = carrito.length;

    if (carrito.length == 0){
        cartNotification.style.display = 'none';
        modalCart = 0;
        cartModal.style.display = 'none';
    }else{
        renderizarCarrito();
    };

}

/**
 * Calcula el precio total teniendo en cuenta los productos repetidos
 */
function calcularTotal() {
    return carrito.reduce((total, item) => {
        const miItem = getProductoList.filter((itemProductos) => {
            return itemProductos.id === parseInt(item);
        });
        precioNowCart = parseFloat(miItem[0].price * (100 - miItem[0].discount) / 100);
        return total + precioNowCart;
    }, 0).toFixed(2);
}

btnSignOut.addEventListener('click', ()=>{
    axios.post('/api/logout')
                .then(response => window.location.href="/web/index.html")
                .catch(() =>{
                    this.errorMsg = "Sign out failed"
                    this.errorToats.show();
                })
});

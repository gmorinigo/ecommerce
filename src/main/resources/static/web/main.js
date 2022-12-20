let getProductoList = [];
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
var btnCheckOut = document.querySelector('.modal-cart__checkout');

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
var logeado = 0;
// Funciones
/**
* Dibuja todos los productos a partir de la base de datos.
*/
async function recuperarListProducto() {

    await axios.get("/api/products")
    .then((response) => {

         response.data.forEach((item) => {
              let dataProd = {
                              "id": item.id,
                              "name": item.nombre,
                              "price": item.precio,
                              "discount": item.descuento,
                              "stock": item.stock,
                              "types": item.categorias
                             }

              getProductoList.push(dataProd);
              })

    }).catch((error)=>{
        // handle error
        console.log("Error getting data " + error)
    });

    console.log(getProductoList);

    renderizarProductos();
}

recuperarListProducto();

function recuperarProducto() {
    var product = document.querySelector('.navbar__link-select');
    var categoriaEncontrada = 0;

    productoList = [];
    productoList = [];

    getProductoList.forEach((item) => {
             item.types.forEach((categoria) => {
                 if (categoria === product.innerText){
                     categoriaEncontrada = 1;
                 }
             });

             let dataProdPag = {
                              "id": item.id,
                              "name": item.name,
                              "price": item.price,
                              "discount": item.discount,
                              "stock": item.stock,
                              "type": product.innerText
                             }

             if (categoriaEncontrada == 1){
                 productoList.push(dataProdPag);
                 categoriaEncontrada = 0;
             };
    });

    console.log(productoList);
    console.log(productoList.length);
}

async function renderizarProductos() {
    DOMitems.textContent = '';
    recuperarProducto();
    // Estructura
    const row = document.createElement('div');
    row.classList.add('row');
    // Estructura
    const col = document.createElement('div');
    col.classList.add('col-12','col-md-3','mt-2');

    productoList.forEach((proct) => {
        console.log("5");
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
        var srcImg = './images/productos/' + proct.name + '.jfif';
        miNodoImagen.setAttribute('src', srcImg);
        
        miNodo.appendChild(miNodoTitle);
        miNodo.appendChild(miNodoImagen); 
        // Cuerpo Precio
         const miNodoCardBodyPrices = document.createElement('div');
        miNodoCardBodyPrices.classList.add('product__prices');
        // Precio
        const miNodoNow = document.createElement('p');
        miNodoNow.classList.add('product__now');

        const miNodoDiscount = document.createElement('p');
        const miNodoBefore = document.createElement('p');

        if (proct.discount > 0){
            var precioNow = parseFloat(proct.price * (100 - proct.discount) / 100);
            miNodoNow.textContent = `${divisa}${precioNow}`;
            miNodoDiscount.classList.add('product__discount');
            miNodoDiscount.textContent = `${proct.discount}${porcentaje}`;
            miNodoBefore.classList.add('product__before');
            miNodoBefore.textContent = `${divisa}${proct.price}`;
        }else{
            var precioNow = parseFloat(proct.price);
            miNodoNow.textContent = `${divisa}${precioNow}`;
        };

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
        miNodoCant.setAttribute('value', '1');
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
        if (proct.discount > 0){
            miNodoCardBodyPrices.appendChild(miNodoDiscount);
            miNodoCardBodyPrices.appendChild(miNodoBefore);
        }
        miNodo.appendChild(miNodoCardBodyPrices);
        miNodoCardBodyQuantity.appendChild(miNodoInput);
        miNodoCardBodyQuantity.appendChild(miNodoBoton);
        miNodo.appendChild(miNodoCardBodyQuantity);
        col.appendChild(miNodo);
    });
    row.appendChild(col);
    DOMitems.appendChild(row);

    await axios.get('/api/clients/current')
    .then(response => {
                btnCompras.style.display = 'block';
                btnSignOut.style.display = 'block';
                linkDeslogeado.style.display = 'none';
                console.log(response.data);
                logeado = 1;
    })
    .catch((error) =>{
                btnCompras.style.display = 'none';
                btnSignOut.style.display = 'none';
                linkDeslogeado.style.display = 'block';
                console.log(error);
                logeado = 0;
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

async function aniadirProductoAlCarrito(evento) {
    if (logeado == 1){
        var idCantFind = '#number' + evento.target.getAttribute('marcador');
        var userImput = document.querySelector(idCantFind);
        userImputNumber=parseInt(userImput.getAttribute('value'));
        idProducto=parseInt(evento.target.getAttribute('marcador'));

        console.log("Se va a agregar " + userImputNumber + " productos del ID " + idProducto + " al carrito")

        await axios.post(`/api/carrito/addproducto?idProducto=${idProducto}&cantidad=${userImputNumber}`)
        .then(response => console.log("Se agregaron " + userImputNumber + " productos del ID " + idProducto + " al carrito"))
        .catch((error) =>{
            // handle error
            console.log("Error getting data " + error)
        })

        console.log(evento.target.getAttribute('marcador'));

        lastValue = parseInt(cartNotification.innerText);
        cantIntem = lastValue + userImputNumber;
        if(cantIntem>=1){
            cartNotification.innerText = cantIntem;
            cartNotification.style.display = 'block';
        };
        userImput.setAttribute('value','0');
    } else {
        alert('Debes iniciar sesión para añadir a carrito');
    }

}

/**
* Evento para borrar un elemento del carrito
*/
async function borrarItemCarrito(evento) {

    const id = evento.target.getAttribute('marcador');

    await axios.delete(`/api/carrito/deleteproducto?idProducto=${id}`)
    .then((response) => {
        console.log(response.data);
        cartNotification.innerText = response.data;
    })
    .catch((error) =>{
        // handle error
        console.log("Error getting data " + error)
    })
    
    if (cartNotification.innerText == 0){
        cartNotification.style.display = 'none';
        modalCart = 0;
        cartModal.style.display = 'none';
    }else{
        renderizarCarrito();
    };

}

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


async function renderizarCarrito() {
    var total = 0;
    DOMcarrito.textContent = '';
    carrito = [];

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

        const miNodo = document.createElement('div');
        miNodo.classList.add('modal-cart__details-container');
        
        const miNodoImagCart = document.createElement('img');
        miNodoImagCart.classList.add('modal-cart__logo');
        var srcImgCart = './images/productos/' + item.name + '.jfif';
        miNodoImagCart.setAttribute('src', srcImgCart);

        const miNodoTextCart = document.createElement('p');
        miNodoTextCart.classList.add('modal-cart__price');
        precioNowCart = parseFloat(item.price * (100 - item.discount) / 100);
        miNodoTextCart.textContent = `${item.cant} x ${item.name} - ${precioNowCart}${divisa}`;

        const miNodoDeleteCart = document.createElement('img');
        miNodoDeleteCart.classList.add('modal-cart__delete');
        miNodoDeleteCart.setAttribute('src', './images/icon-delete.svg');
        miNodoDeleteCart.setAttribute('marcador', item.id);
        miNodoDeleteCart.addEventListener('click', borrarItemCarrito);

        miNodo.appendChild(miNodoImagCart);
        miNodo.appendChild(miNodoTextCart);
        miNodo.appendChild(miNodoDeleteCart);
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

    console.log(total);
    DOMtotal.textContent = total;
}

btnSignOut.addEventListener('click', ()=>{
    axios.post('/api/logout')
                .then(response => window.location.href="/web/index.html")
                .catch(() =>{
                    this.errorMsg = "Sign out failed"
                    this.errorToats.show();
                })
});

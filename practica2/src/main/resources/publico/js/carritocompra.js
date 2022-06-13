

var cantidadProducto = 0;

function addToCarrito(elements) {
    var element = elements;

    var table = document.getElementsByClassName(element.getAttribute("id"));
    
   // cantidadProducto += Number.parseInt(table[0].value);
   // table.value = 0;
  //  var carrito = document.getElementById("carrito");
  //  carrito.innerHTML = "Carrito" + "( " + cantidadProducto + " )";
    postIdRequest(element.getAttribute("id"), table[0].value);
    
}
function postIdRequest(id, value) {
    var xhr = new XMLHttpRequest();
    var myUrl = "/carritodecompra/" + id
    console.log(myUrl);
    xhr.open("POST", myUrl, true);
    xhr.setRequestHeader('Content-Type', 'document/html');
    xhr.send(value);
    location.reload();
    location.reload();
    location.reload();
    location.reload();
}

function removeTocarrito(element) {
    var element = elements;
    var table = document.getElementsByClassName(element.getAttribute("id"));
    document.removeChild(table);
    
}
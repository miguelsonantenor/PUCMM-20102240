<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Carrito De Compra</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">

    <link rel="stylesheet" href="/css/app.css" />
  </head>

  <body>
    <header>
      <nav class="navbar navbar-expand-lg navbar-light " style="background: #03a1fc; box-shadow: 13px 13px 20px #cbced1, -13px -13px 20px #fff; border-radius: 15px;">
        <div class="container">
          <div class="navbar-header">
            <a class="navbar-brand nav-link"  href="#"
              ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-book" viewBox="0 0 16 16">
                <path d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811V2.828zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492V2.687zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783z"/>
              </svg> Practica2</a
            >
          </div>
          <ul class="nav navbar-nav navbar-right ">
            <li><a class="nav-link  " href="/nuevousuario">Registrar</a></li>
            <li><a class="nav-link " href="/administrarproductos">Logear</a></li>
          </ul>
        </div>
      </nav>

  

    </header>

    <main>
      
      <div class="wrapper">
        <div class="logo">
          <img
            src="https://www.freepnglogos.com/uploads/book-png/book-clipart-transparent-17.png"
            alt="University Book"
          />
        </div>
        <div class="text-center mt-4 name">PUCMM</div>
        <form class="p-3 mt-3" method = "post" action="/administrarproductos">
          <div class="form-field d-flex align-items-center">
            <span class="far fa-user"></span>
            <input
              required
              type="text"
              name="usuario"
              id="userName"
              placeholder="Usuario"
            />
          </div>
          <div class="form-field d-flex align-items-center">
            <span class="fas fa-key"></span>
            <input
              required
              type="password"
              name="password"
              id="pwd"
              placeholder="Contrasena"
            />
          </div>
          <button class="btn mt-3">Login</button>
        </form>
        <div class="text-center fs-6">
          <a href="#">Forget password?</a> or <a href="/nuevousuario">Registrar</a>
        </div>
      </div>
    </main>

    <footer></footer>
  </body>
</html>

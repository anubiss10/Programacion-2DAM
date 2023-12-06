const mainContent = document.querySelector('.main-content');
const navbar = document.querySelector('.navbar');
const footer = document.querySelector('.footer');

function updateLayout() {
  let navbarWidth = navbar.offsetWidth;
  mainContent.style.marginLeft = `${navbarWidth}px`;
  footer.style.marginLeft = `${navbarWidth}px`;
}

// Llama a la función de actualización inicial
updateLayout();

// Manejar redimensión de la columna izquierda
let isResizing = false;
let startX;

navbar.addEventListener('mousedown', (event) => {
  isResizing = true;
  startX = event.clientX;
  document.addEventListener('mousemove', handleMouseMove);
  document.addEventListener('mouseup', () => {
    isResizing = false;
    document.removeEventListener('mousemove', handleMouseMove);
  });
});

function handleMouseMove(event) {
  if (isResizing) {
    let offsetX = event.clientX - startX;
    let newWidth = navbar.offsetWidth + offsetX;
    navbar.style.width = `${newWidth}px`;
    updateLayout();
    startX = event.clientX;
  }
}

// Nueva variable para almacenar ancho del navbar
window.addEventListener('resize', updateLayout);
function updateLayout() {
	let navbarWidth = navbar.offsetWidth;
	mainContent.style.marginLeft = `${navbarWidth}px`;
	footer.style.marginLeft = `${navbarWidth}px`;
  }
  
  // Cambiar opacidad con Scroll
  window.addEventListener('scroll', () => {
	if (window.scrollY > 0) {
	  topBar.classList.add('transparent');
	} else {
	  topBar.classList.remove('transparent');
	}
  });
  
  // Nueva variable para almacenar altura del Topbar
  let topbarHeight = topBar.offsetHeight;
  // Añadimos un paddingTop basado en la altura al main-content o el contenido principal
  mainContent.style.paddingTop = `${topbarHeight + 20}px`;
  
/* ********************************** */
/*             BOTON PLAY             */
/* ********************************** */
const containerConcentracion = document.querySelectorAll('.card-concentracion');
const containerSpotifyPlaylists = document.querySelectorAll('.card-spotify-playlists');

// Función que se va a repetir
const createButton = card => {
	// Crear el botón
	const button = document.createElement('button');
	button.innerHTML = '<i class="fas fa-play"></i>';

	// Agregar el botón al elemento hijo
	card.appendChild(button);

	// Ocultar el botón inicialmente
	button.style.display = 'none';
	button.classList.add('btn-play');

	// Agregamos un evento de hover a este elemento
	// Se necesita cuando el mouse entra y cuando sale
	card.addEventListener('mouseover', () => {
		button.style.display = 'block';
	});

	card.addEventListener('mouseout', () => {
		button.style.display = 'none';
	});
};

// Usamos la función en las dos filas de contenedores
containerConcentracion.forEach(card => {
	createButton(card);
});

containerSpotifyPlaylists.forEach(card => {
	createButton(card);
});

# Usamos una imagen base ligera de Nginx, un servidor web popular.
FROM nginx:alpine
# Copiamos nuestro archivo index.html dentro del contenedor.
# Nginx sirve archivos estáticos desde /usr/share/nginx/html/ por defecto.
COPY index.html /usr/share/nginx/html/index.html
# Nginx ya expone el puerto 80 por defecto.
# Si tuvieras otros archivos CSS/JS, también los copiarías aquí
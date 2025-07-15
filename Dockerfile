# --- PASO 1: Capa de construcción (si es necesario) ---
# Usa una imagen oficial de Node.js como base. La etiqueta 'alpine' es para una versión más ligera.
FROM node:18-alpine AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el package.json y package-lock.json para instalar dependencias
COPY package*.json ./

# Instala las dependencias del proyecto. 'npm ci' es más rápido y seguro para CI/CD.
RUN npm ci

# Copia el resto de los archivos de tu aplicación
COPY . .

# Si es una app de frontend (React, Vue), construye los archivos estáticos
# Si es una app de backend (Express), puedes saltarte este paso.
RUN npm run build


# --- PASO 2: Capa de producción ---
# Usamos una imagen más pequeña para la versión final, solo con lo necesario para correr la app.
FROM node:18-alpine

WORKDIR /app

# Copia solo las dependencias de producción desde la capa de construcción
COPY --from=build /app/node_modules ./node_modules
# Copia el package.json para que la app sepa qué dependencias tiene
COPY --from=build /app/package.json ./

# Si es una app de backend (Express), copia el código fuente
COPY --from=build /app/src ./src

# Si es una app de frontend (React), copia los archivos construidos
# COPY --from=build /app/build ./build

# Expone el puerto en el que tu aplicación se ejecuta
EXPOSE 3000

# El comando para iniciar tu aplicación
# Para backend:
CMD ["node", "src/index.js"]
# Para frontend (usando 'serve' para servir los archivos estáticos):
# CMD ["npx", "serve", "-s", "build"]
# Java Franchise Microservice

Este es un microservicio desarrollado en Java para la gestión de franquicias, que utiliza MySQL como base de datos principal y Redis como sistema de caché.

## 📋 Prerrequisitos

- Java 21
- Maven
- Docker y Docker Compose
- IDE de desarrollo (recomendado: IntelliJ IDEA)

## 🚀 Desarrollo Local

### Opción 1: Ejecución desde IDE

1. **Levantar las dependencias de desarrollo:**
   ```bash
   cd tools
   docker compose up -d
   cd ..
   ```

2. **Ejecutar desde tu IDE:**
   - Abre el proyecto en tu IDE favorito (IntelliJ IDEA, Eclipse, etc.)
   - Ejecuta la aplicación directamente
   - La aplicación se conectará automáticamente a MySQL y Redis locales

### Opción 2: Configuración con servicios externos

Si deseas conectarte a bases de datos y caché externos, configura las siguientes variables de entorno:

```bash
DB_HOST=tu_host_mysql
DB_PORT=3306
DB_NAME=nombre_base_datos
DB_USER=usuario_mysql
DB_PASSWORD=contraseña_mysql
REDIS_HOST=tu_host_redis
REDIS_PORT=6379
REDIS_PASSWORD=contraseña_redis
```

## 🐳 Despliegue con Docker

### Paso 1: Preparar el entorno

```bash
# Ubicarse en la raíz del proyecto
cd java-franchise-ms

# Levantar MySQL y Redis para desarrollo
cd tools
docker compose up -d
cd ..
```

### Paso 2: Compilar la aplicación

```bash
mvn clean package -DskipTests
```

### Paso 3: Construir la imagen Docker

```bash
docker build -t franchise-app .
```

### Paso 4: Configurar variables de entorno

Crea un archivo `.env` en la raíz del proyecto con la siguiente configuración:

```env
DB_HOST=mysql
DB_PORT=3306
DB_NAME=franchise_db
DB_USER=myuser
DB_PASSWORD=mypassword

REDIS_HOST=redis
REDIS_PORT=6379
REDIS_PASSWORD=
```

> **Nota:** Esta configuración corresponde a los servicios levantados por `tools/docker-compose.yml`

### Paso 5: Ejecutar el contenedor

```bash
docker run -d \
  --env-file .env \
  --network franchise-network \
  -p 8080:8080 \
  --name franchise-app \
  franchise-app:latest
```

## 🔧 Configuración

### Variables de Entorno

| Variable | Descripción | Valor por defecto |
|----------|-------------|-------------------|
| `DB_HOST` | Host de la base de datos MySQL | `localhost` |
| `DB_PORT` | Puerto de MySQL | `3306` |
| `DB_NAME` | Nombre de la base de datos | `franchise_db` |
| `DB_USER` | Usuario de MySQL | `myuser` |
| `DB_PASSWORD` | Contraseña de MySQL | `mypassword` |
| `REDIS_HOST` | Host de Redis | `localhost` |
| `REDIS_PORT` | Puerto de Redis | `6379` |
| `REDIS_PASSWORD` | Contraseña de Redis | _(vacío)_ |

### Puertos

- **Aplicación:** 8080
- **MySQL:** 3306
- **Redis:** 6379

## 📁 Estructura del Proyecto

```
java-franchise-ms/
├── src/                    # Código fuente
├── tools/                  # Herramientas de desarrollo
│   └── docker-compose.yml  # MySQL y Redis para desarrollo
├── Dockerfile              # Imagen de la aplicación
├── pom.xml                 # Configuración de Maven
└── README.md               # Este archivo
```


## 📝 Notas Adicionales

- La aplicación estará disponible en `http://localhost:8080`
- MySQL estará accesible en `localhost:3306`
- Redis estará accesible en `localhost:6379`
- Para desarrollo local, no es necesario configurar variables de entorno adicionales

## 📚 API Endpoints

La aplicación expone los siguientes endpoints REST:

### 🏢 Franquicias (Franchises)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/franchises` | Crear nueva franquicia |
| `GET` | `/api/franchises` | Obtener todas las franquicias |
| `GET` | `/api/franchises/{id}` | Obtener franquicia por ID |
| `PUT` | `/api/franchises/{id}` | Actualizar franquicia |

**Ejemplo de creación de franquicia:**
```json
POST /api/franchises
{
    "name": "Tostado"
}
```

### 🏪 Sucursales (Branches)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/branches` | Crear nueva sucursal |
| `GET` | `/api/branches` | Obtener todas las sucursales |
| `GET` | `/api/branches/{branchId}` | Obtener sucursal por ID |
| `PUT` | `/api/branches/{branchId}` | Actualizar sucursal |
| `GET` | `/api/branches/franchise/{franchiseId}` | Obtener sucursales por franquicia |

**Ejemplo de creación de sucursal:**
```json
POST /api/branches
{
    "name": "Tostado centro",
    "status": true,
    "franchiseId": 1
}
```

### 📦 Productos (Products)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/products` | Crear nuevo producto |
| `GET` | `/api/products` | Obtener todos los productos |
| `GET` | `/api/products/{productId}` | Obtener producto por ID |
| `PUT` | `/api/products/{productId}` | Actualizar producto |

**Ejemplo de creación de producto:**
```json
POST /api/products
{
    "name": "Cafe",
    "status": true
}
```

### 🛒 Productos por Sucursal (Branch Products)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/branches/{branchId}/products/{productId}` | Agregar producto a sucursal |
| `GET` | `/api/branches/{branchId}/products` | Obtener productos de una sucursal |
| `PUT` | `/api/branches/{branchId}/products/{productId}` | Actualizar stock/precio de producto |
| `DELETE` | `/api/branches/{branchId}/products/{productId}` | Eliminar producto de sucursal |

**Ejemplo de agregar producto a sucursal:**
```json
POST /api/branches/1/products/1
{
    "stock": 10,
    "price": 7000.00
}
```

### 📊 Reportes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/franchises/{franchiseId}/top-stock-products` | Productos con mayor stock por franquicia |

## 📁 Colección de Postman

Para facilitar las pruebas de la API, puedes descargar e importar nuestra colección de Postman que incluye todos los endpoints con ejemplos de uso.

**[📥 Descargar Colección de Postman](./postman_collection.json)**

### Cómo usar la colección:

1. Descarga el archivo `postman_collection.json`
2. Abre Postman
3. Ve a `File` → `Import`
4. Selecciona el archivo descargado
5. Configura la variable `{{host}}` con la URL de tu servidor (por defecto: `http://localhost:8080`)

### Variables de entorno sugeridas:

```json
{
    "host": "http://localhost:8080"
}
```


## ☁️ Infraestructura con Terraform

Para desplegar la aplicación en AWS con toda la infraestructura necesaria (EC2, MySQL RDS y Redis ElastiCache), hemos preparado scripts de Terraform que automatizan todo el proceso.

La infraestructura incluye:
- **EC2 Instance**: Servidor para la aplicación Java
- **RDS MySQL**: Base de datos gestionada
- **ElastiCache Redis**: Sistema de caché distribuido
- **Security Groups**: Configuración de seguridad apropiada
- **VPC y Subnets**: Red privada virtual configurada

**📖 [Ver documentación completa de Terraform](./tools/terraform/README.md)**

En la documentación encontrarás:
- Requisitos previos y configuración de AWS CLI
- Variables de configuración disponibles
- Comandos paso a paso para el despliegue
- Gestión de estados y recursos
- Instrucciones para destruir la infraestructura

> **Nota:** Asegúrate de revisar los costos de AWS antes de desplegar la infraestructura en producción.
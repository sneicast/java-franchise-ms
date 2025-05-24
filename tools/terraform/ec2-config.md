# Configuración del Proyecto Java Franchise

Esta guía te ayudará a configurar el entorno necesario para ejecutar el proyecto Java Franchise en Ubuntu Server en la instancia ec2.


## Prerrequisitos
- **Sistema Operativo:** Ubuntu Server 24.04
- **Acceso a Internet:** Asegúrate de que tu instancia EC2 tenga acceso a Internet.
- **SSH:** Acceso SSH a la instancia EC2.

### Instalar Java 21

1. **Agregar el repositorio y actualizar paquetes:**
   ```bash
   sudo add-apt-repository ppa:openjdk-r/ppa
   sudo apt update
   ```

2. **Instalar OpenJDK 21:**
   ```bash
   sudo apt install openjdk-21-jdk -y
   ```

3. **Verificar la instalación:**
   ```bash
   java -version
   ```

### Instalar Maven

1. **Instalar Maven:**
   ```bash
   sudo apt install maven -y
   ```

2. **Verificar la instalación:**
   ```bash
   mvn -v
   ```

### Instalar Docker

1. **Actualizar paquetes:**
   ```bash
   sudo apt update
   ```

2. **Instalar dependencias necesarias:**
   ```bash
   sudo apt install apt-transport-https ca-certificates curl software-properties-common -y
   ```

3. **Agregar la clave GPG de Docker:**
   ```bash
   curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
   ```

4. **Agregar el repositorio de Docker:**
   ```bash
   echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] \
   https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | \
   sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
   ```

5. **Actualizar paquetes e instalar Docker:**
   ```bash
   sudo apt update
   sudo apt install docker-ce -y
   ```

6. **Verificar la instalación de Docker:**
   ```bash
   sudo docker --version
   sudo systemctl status docker
   ```

7. **Configurar Docker para usar sin sudo:**
   ```bash
   sudo usermod -aG docker $USER
   ```
   > **Nota:** Debes cerrar sesión y volver a entrar para que los cambios surtan efecto.

## Configuración y Despliegue del Proyecto

### Clonar el Repositorio

```bash
git clone https://github.com/sneicast/java-franchise-ms.git
cd java-franchise-ms
```

### Configurar Variables de Entorno

Crear un archivo `.env` en la raíz del proyecto con la siguiente configuración (reemplaza con tus datos reales):

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

### Compilar y Ejecutar

1. **Compilar el proyecto:**
   ```bash
   mvn clean package -DskipTests
   ```

2. **Construir la imagen Docker:**
   ```bash
   docker build -t franchise-app .
   ```

3. **Ejecutar el contenedor:**
   ```bash
   docker run -d \
     --env-file .env \
     --network host \
     --restart unless-stopped \
     -p 8080:8080 \
     --name franchise-app \
     franchise-app:latest
   ```

## Verificación

Una vez completados todos los pasos, la aplicación estará disponible en `http://{MI_IP}:8080`.

Para verificar que el contenedor está ejecutándose correctamente:

```bash
docker ps
docker logs franchise-app
```

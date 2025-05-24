# Proyecto Terraform - Infraestructura AWS

Este proyecto crea una infraestructura completa en AWS con:
- VPC con subredes públicas y privadas
- Instancia EC2 con 4GB RAM
- Base de datos MySQL (RDS)
- Cache Redis (ElastiCache)
- Security Groups configurados

## Estructura del Proyecto

```
.
├── main.tf                    # Archivo principal
├── variables.tf               # Variables globales
├── outputs.tf                 # Outputs del proyecto
├── terraform.tfvars.example   # Ejemplo de variables
└── modules/
    ├── vpc/                   # Módulo de red
    │   ├── main.tf
    │   ├── variables.tf
    │   └── outputs.tf
    ├── security/             # Security Groups
    │   ├── main.tf
    │   ├── variables.tf
    │   └── outputs.tf
    ├── rds/                  # Base de datos MySQL
    │   ├── main.tf
    │   ├── variables.tf
    │   └── outputs.tf
    ├── redis/                # Cache Redis
    │   ├── main.tf
    │   ├── variables.tf
    │   └── outputs.tf
    └── ec2/                  # Servidor EC2
        ├── main.tf
        ├── variables.tf
        └── outputs.tf

```

## Requisitos Previos

1. **AWS CLI configurado** con credenciales válidas
2. **Terraform instalado** (versión >= 1.0)
3. **Key Pair creado en AWS** para acceso SSH

### Crear Key Pair

```bash
# En AWS Console o por CLI
aws ec2 create-key-pair --key-name franchise-key-pair --query 'KeyMaterial' --output text > franchise-key-pair.pem
chmod 400 franchise-key-pair.pem
```

## Instalación y Configuración

1. **Clonar/descargar el proyecto**
2. **Configurar variables:**
   ```bash
   cp terraform.tfvars.example terraform.tfvars
   # Editar terraform.tfvars con tus valores
   ```

3. **Inicializar Terraform:**
   ```bash
   terraform init
   ```

4. **Validar configuración:**
   ```bash
   terraform plan
   ```

5. **Aplicar infraestructura:**
   ```bash
   terraform apply
   ```

## Acceso a los Servicios

Después del despliegue, tendrás acceso a:

### EC2 Instance
- **SSH:** `ssh -i tu-key.pem ec2-user@IP_PUBLICA`
- **Web:** `http://IP_PUBLICA`
- **Api Backend:** `http://IP_PUBLICA:8080`

### Base de Datos MySQL
- **Host:** Endpoint interno (solo desde EC2)
- **Puerto:** 3306
- **Usuario:** admin
- **Base de datos:** franchisedb

### Redis Cache
- **Host:** Endpoint interno (solo desde EC2)
- **Puerto:** 6379

## Scripts Útiles

### Conectar a MySQL
```bash
mysql -h MYSQL_ENDPOINT -P 3306 -u admin -p
```

### Conectar a Redis
```bash
redis-cli -h REDIS_ENDPOINT -p 6379
```

## Puertos Expuestos

| Puerto | Servicio | Acceso |
|--------|----------|---------|
| 22     | SSH      | Internet |
| 80     | HTTP     | Internet |
| 443    | HTTPS    | Internet |
| 8080   | Api      | Internet |
| 3306   | MySQL    | Solo EC2 |
| 6379   | Redis    | Solo EC2 |

## Seguridad

- **MySQL y Redis** están en subredes privadas
- **Security Groups** configurados con acceso mínimo necesario
- **Almacenamiento cifrado** para RDS y EBS
- **Metadatos IMDSv2** habilitado en EC2

## Limpieza

Para destruir toda la infraestructura:
```bash
terraform destroy
```

## Personalización

Para modificar la configuración:

1. **Cambiar tipo de instancia:** Editar `ec2_instance_type` en `terraform.tfvars`
2. **Agregar puertos:** Modificar `modules/security/main.tf`
3. **Cambiar configuración de DB:** Editar variables MySQL/Redis

## Variables Importantes

| Variable | Descripción | Valor por defecto |
|----------|-------------|-------------------|
| `aws_region` | Región de AWS | us-east-1 |
| `ec2_instance_type` | Tipo de instancia | t3.medium (4GB RAM) |
| `ec2_key_name` | Key pair para SSH | (requerido) |
| `mysql_password` | Password de MySQL | (requerido) |
| `vpc_cidr` | CIDR de la VPC | 10.0.0.0/16 |

## Desplegar la aplicación en el ec2
**📖 [Ver documentación completa de despliegue app en ec2](./ec2-config.md)**
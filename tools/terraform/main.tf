# main.tf
terraform {
  required_version = ">= 1.0"
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = var.aws_region
}

# Data sources
data "aws_availability_zones" "available" {
  state = "available"
}

data "aws_ami" "ubuntu" {
  most_recent = true
  owners      = ["099720109477"] # Canonical

  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-jammy-22.04-amd64-server-*"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
}

# VPC Module
module "vpc" {
  source = "./modules/vpc"
  
  project_name        = var.project_name
  environment         = var.environment
  vpc_cidr           = var.vpc_cidr
  availability_zones = data.aws_availability_zones.available.names
  
  tags = var.common_tags
}

# Security Groups Module
module "security_groups" {
  source = "./modules/security"
  
  project_name = var.project_name
  environment  = var.environment
  vpc_id       = module.vpc.vpc_id
  
  tags = var.common_tags
}

# RDS MySQL Module
module "rds_mysql" {
  source = "./modules/rds"
  
  project_name     = var.project_name
  environment      = var.environment
  vpc_id          = module.vpc.vpc_id
  subnet_ids      = module.vpc.private_subnet_ids
  security_group_ids = [module.security_groups.rds_security_group_id]
  
  # Database configuration
  engine_version    = var.mysql_engine_version
  instance_class    = var.mysql_instance_class
  allocated_storage = var.mysql_allocated_storage
  db_name          = var.mysql_db_name
  username         = var.mysql_username
  password         = var.mysql_password
  
  tags = var.common_tags
}

# ElastiCache Redis Module
module "redis" {
  source = "./modules/redis"
  
  project_name       = var.project_name
  environment        = var.environment
  vpc_id            = module.vpc.vpc_id
  subnet_ids        = module.vpc.private_subnet_ids
  security_group_ids = [module.security_groups.redis_security_group_id]
  
  # Redis configuration
  node_type         = var.redis_node_type
  num_cache_nodes   = var.redis_num_nodes
  engine_version    = var.redis_engine_version
  
  tags = var.common_tags
}

# EC2 Instance Module
module "ec2" {
  source = "./modules/ec2"
  
  project_name = var.project_name
  environment  = var.environment
  
  # Instance configuration - Cambio: usar AMI de Ubuntu
  ami_id               = data.aws_ami.ubuntu.id
  instance_type        = var.ec2_instance_type
  key_name            = var.ec2_key_name
  subnet_id           = module.vpc.public_subnet_ids[0]
  security_group_ids  = [module.security_groups.ec2_security_group_id]
  
  # Database connection info
  mysql_endpoint = module.rds_mysql.endpoint
  redis_endpoint = module.redis.redis_endpoint  # Corrección: usar redis_endpoint
  
  tags = var.common_tags
  
  # Agregar dependencias explícitas para asegurar que Redis esté creado
  depends_on = [
    module.rds_mysql,
    module.redis
  ]
}
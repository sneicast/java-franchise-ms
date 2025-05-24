# outputs.tf
output "vpc_id" {
  description = "ID of the VPC"
  value       = module.vpc.vpc_id
}

output "ec2_public_ip" {
  description = "Public IP of EC2 instance"
  value       = module.ec2.public_ip
}

output "ec2_public_dns" {
  description = "Public DNS of EC2 instance"
  value       = module.ec2.public_dns
}

output "mysql_endpoint" {
  description = "MySQL database endpoint"
  value       = module.rds_mysql.endpoint
  sensitive   = true
}

output "mysql_port" {
  description = "MySQL database port"
  value       = module.rds_mysql.port
}

output "redis_endpoint" {
  description = "Redis cluster endpoint"
  value       = module.redis.endpoint
  sensitive   = true
}

output "redis_port" {
  description = "Redis port"
  value       = module.redis.port
}

output "ssh_connection" {
  description = "SSH connection command"
  value       = "ssh -i ${var.ec2_key_name}.pem ubuntu@${module.ec2.public_ip}"
}

output "web_url" {
  description = "Web application URL"
  value       = "http://${module.ec2.public_ip}"
}

output "api_url" {
  description = "api URL"
  value       = "http://${module.ec2.public_ip}:8080"
}
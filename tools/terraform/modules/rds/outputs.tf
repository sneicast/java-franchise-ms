# modules/rds/outputs.tf
output "endpoint" {
  description = "MySQL database endpoint"
  value       = aws_db_instance.main.endpoint
}

output "port" {
  description = "MySQL database port"
  value       = aws_db_instance.main.port
}

output "db_name" {
  description = "Database name"
  value       = aws_db_instance.main.db_name
}

output "username" {
  description = "Database username"
  value       = aws_db_instance.main.username
}

output "password" {
  description = "Database password"
  value       = var.password != "" ? var.password : random_password.db_password[0].result
  sensitive   = true
}

output "db_instance_id" {
  description = "RDS instance ID"
  value       = aws_db_instance.main.id
}
# modules/redis/outputs.tf
output "endpoint" {
  description = "Redis cluster endpoint"
  value       = aws_elasticache_cluster.main.cluster_address
}

output "redis_endpoint" {
  description = "Redis cluster endpoint (alias)"
  value       = aws_elasticache_cluster.main.cluster_address
}

output "port" {
  description = "Redis port"
  value       = aws_elasticache_cluster.main.port
}

output "cluster_id" {
  description = "Redis cluster ID"
  value       = aws_elasticache_cluster.main.cluster_id
}
##############################################################################
#                      GENERAL                                               #
##############################################################################

application = "hexburger-pedido"
aws_region  = "us-east-1"

##############################################################################
#                      HELM                                                  #
##############################################################################

ingress_nginx_name = "ingress-nginx-controller"

helm_service_template = [{
  name                = "hexburger-pedido"
  namespaces          = "api"
  is_there_config_map = true
  is_there_secret     = true
  secret_type         = "Opaque"

  helm_chart_key_value = {
    "chartName"                                     = "hexburger-pedido"
    "serviceAccount.create"                         = "true"
    "serviceAccount.name"                           = "hexburger-pedido-svc-acc"
    "service.type"                                  = "LoadBalancer"
    "service.port"                                  = "28082"
    "service.targetPort"                            = "28082"
    "ingress.enabled"                               = "false"
    "image.pullPolicy"                              = "Always"
    "resources.requests.cpu"                        = "100m"
    "resources.requests.memory"                     = "256Mi"
    "resources.limits.cpu"                          = "200m"
    "resources.limits.memory"                       = "512Mi"
    "livenessProbe.initialDelaySeconds"             = "300"
    "livenessProbe.periodSeconds"                   = "90"
    "livenessProbe.timeoutSeconds"                  = "30"
    "readinessProbe.initialDelaySeconds"            = "90"
    "readinessProbe.periodSeconds"                  = "90"
    "readinessProbe.timeoutSeconds"                 = "30"
    "autoscaling.enabled"                           = "true"
    "autoscaling.minReplicas"                       = "1"
    "autoscaling.maxReplicas"                       = "1"
    "autoscaling.targetCPUUtilizationPercentage"    = "70"
    "autoscaling.targetMemoryUtilizationPercentage" = "70"
    "secretName"                                    = "sc-hexburger-pedido"
    "configMap.enabled"                             = "true"
    "configMap.name"                                = "cm-hexburger-pedido"
    "nameOverride"                                  = "hexburger-pedido"
    "fullnameOverride"                              = "hexburger-pedido-api"
  }

  helm_chart_config_map = {
    "APPLICATION_NAME" = "hexburger-pedido"
    "API_DOCS_PATH"    = "/api-docs"
    "API_PORT"         = "28082"
    "DATABASE_URL"     = "jdbc:postgresql://hexburger-db.c9u7v4efxe2d.us-east-1.rds.amazonaws.com:5432/postgres"
  }


}]

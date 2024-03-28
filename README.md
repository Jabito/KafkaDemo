# Lesson - Kafka on K8s

## 1 - Creating a namespace

```
# Create namespace for kafka service group
kubectl create namespace kafka

# Set default namespace to kafka
kubectl config set-context --current --namespace=kafka
```

## 2 - Deploy Strimzi configurations
[Strimzi.io](https://strimzi.io/quickstarts/) provides easy management of Kafka on Kubernetes
```
# Deploy strimzi instance
kubectl create -f 'https://strimzi.io/install/latest?namespace=kafka' -n kafka

# Monitor instance creation
kubectl get pod -n kafka --watch

# Check deployment logs
kubectl logs deployment/strimzi-cluster-operator -n kafka -f
```

## 3 - Create Kafka Broker
```
# Create single persistent kafka broker
kubectl apply -f https://strimzi.io/examples/latest/kafka/kafka-persistent-single.yaml -n kafka

## For multiple replicas
# kubectl apply -f https://strimzi.io/examples/latest/kafka/kafka-persistent.yaml -n kafka

# Check instance creation
kubectl wait kafka/my-cluster --for=condition=Ready --timeout=300s -n kafka
```

## 4 - Test Producer and Consumer
### Producer
```
# Run and access producer instance
kubectl -n kafka run kafka-producer -ti --image=quay.io/strimzi/kafka:0.40.0-kafka-3.7.0 --rm=true --restart=Never -- bin/kafka-console-producer.sh --bootstrap-server my-cluster-kafka-bootstrap:9092 --topic my-topic

# Input some messages to be processed
```
### Consumer
```
# Listen to topic inside k8s
kubectl -n kafka run kafka-consumer -ti --image=quay.io/strimzi/kafka:0.40.0-kafka-3.7.0 --rm=true --restart=Never -- bin/kafka-console-consumer.sh --bootstrap-server my-cluster-kafka-bootstrap:9092 --topic my-topic --from-beginning

# Check and confirm if messages inputted are displayed and in sequence
```

## 5 - (As Needed) Delete Kafka and Strimzi from K8s
```
# Delete kafka
kubectl -n kafka delete $(kubectl get strimzi -o name -n kafka)
# Delete strimzi
kubectl -n kafka delete -f 'https://strimzi.io/install/latest?namespace=kafka'

## Delete all from namespace (This doesn't delete RoleBindings, ServiceAccounts or NetworkPolicy)
kubectl delete all --all -n kafka
## Delete namespace
kubectl delete namespace kafka
```

## 6 - Create MySQL Instance
### Deploy MySQL to k8s
```
# Create k8s secret (For password)
kubectl apply -f mysql-secret.yaml
# Spin up persistent storage
kubectl apply -f mysql-storage.yaml
# Apply mysql deployment
kubectl apply -f mysql-deployment.yaml
```

### Access pod and create user
```
# List pods and find mysql instance
kubectl get pods

# Access mysql pod
kubectl exec --stdin --tty mysql-{pod_id} -- /bin/bash

# Login as root user
mysql -p

# Use password from mysql-secret.yaml

# Create demo db
create database demo;
# Create user
create user 'demo_user'@'%' identified by 'dem0UserPassword!';
# Apply permissions
grant all on demo.* to 'demo_user'@'%';
```

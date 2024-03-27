# Deploy Kafka
1. Create namespace for kafka service group - `kubectl create namespace kafka`
   1. Set as default namespace of terminal -`kubectl config set-context --current --namespace=kafka`
2. Deploy kafka manager - `kubectl create -f 'https://strimzi.io/install/latest?namespace=kafka' -n kafka`
   1. Watch Status -`kubectl get pod -n kafka --watch`
   2. Check logs - `kubectl logs deployment/strimzi-cluster-operator -n kafka -f`
3. Create single persistent kafka broker - `kubectl apply -f https://strimzi.io/examples/latest/kafka/kafka-persistent-single.yaml -n kafka`
   1. `kubectl wait kafka/my-cluster --for=condition=Ready --timeout=300s -n kafka` 


# Test Produce Message (Demo service)
1. `kubectl -n kafka run kafka-producer -ti --image=quay.io/strimzi/kafka:0.40.0-kafka-3.7.0 --rm=true --restart=Never -- bin/kafka-console-producer.sh --bootstrap-server my-cluster-kafka-bootstrap:9092 --topic my-topic`
2. Input some text to be shown in Consumer App

# Test Consume Message
1. `kubectl -n kafka run kafka-consumer -ti --image=quay.io/strimzi/kafka:0.40.0-kafka-3.7.0 --rm=true --restart=Never -- bin/kafka-console-consumer.sh --bootstrap-server my-cluster-kafka-bootstrap:9092 --topic my-topic --from-beginning`
2. Check log if messages inputted are displayed and in sequence

# Delete Kafka and Strimzi
1. `kubectl -n kafka delete $(kubectl get strimzi -o name -n kafka)`
2. `kubectl -n kafka delete -f 'https://strimzi.io/install/latest?namespace=kafka'`

# Build images for deployment
1. Run `mvn clean` and `mvn package`
2. Run dockerize script in respective folders.

# Access Point
1. `kubectl get nodes -o wide` -> Get INTERNAL-IP value (or if docker-desktop, mapped to localhost)
2. `kubectl get svc` -> Get Node Port of service
3. You can try accessing service from local by {INTERNAL-IP or localhost}:{NodePort}

# Test
1. Listen to topic `kubectl logs -f --tail=50 kafka-consumer-{pod_id}`
2. Test Post `curl --location --request POST 'localhost:{NodePort}/post'` Post Message

# MySQL
1. `kubectl apply -f mysql-secret.yaml`
2. `kubectl apply -f mysql-storage.yaml`
3. `kubectl apply -f mysql-deployment.yaml`
4. `kubectl exec --stdin --tty mysql-{pod_id} -- /bin/bash`
5. `mysql -p`
6. Use password from mysql-secret.yaml

## Create new user
1. `create database demo;`
2. `create user 'demo_user'@'%' identified by 'dem0UserPassword!';`
2. `grant all on demo.* to 'demo_user'@'%';`
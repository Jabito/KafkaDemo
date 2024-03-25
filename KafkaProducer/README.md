# Start Kafka Broker on Docker
1. `docker-compose up -d`(if filename is docker-compose.yml) or `docker-compose -f <docker-compose_file_name> up -d`
2. Check if instantiated -> `docker ps -a`
3. Check logs of zookeeper -> `docker logs <zookeeper-1_containerId>`
4. Verify zookeeper is read -> `docker run --net=host --rm confluentinc/cp-zookeeper:latest bash -c “echo stat | nc localhost <ZOOKEEPER_CLIENT_PORT> | grep Mode”
   `

# Build Docker File
1. Build -> `docker build -t kafka-demo/kafka-producer .`
2. Run -> `docker run -p 8080:8080 kafka-demo/kafka-producer`
3. Stop -> `docker stop {container_id}`
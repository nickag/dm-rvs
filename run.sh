echo `date`
`bin/zookeeper-server-start.sh config/zookeeper.properties &`
`bin/kafka-server-start.sh config/server.properties &`

echo "running zookeeper and kafka.."

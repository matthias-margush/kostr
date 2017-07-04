package kostr.streams

import kostr.Topic
import org.apache.kafka.streams.kstream.KGroupedStream
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.KStreamBuilder
import org.apache.kafka.streams.kstream.KTable

fun <K, V> KStreamBuilder.stream(topic: Topic<K, V>): KStream<K, V> {
    return this.stream(topic.keySerde, topic.valueSerde, topic.name)
}

fun <K, V> KStream<K, V>.groupByKey(topic: Topic<K, V>): KGroupedStream<K, V> {
    return this.groupByKey(topic.keySerde, topic.valueSerde)
}

fun <K, V> KGroupedStream<K, V>.count(topic: Topic<K, Long>): KTable<K, Long> {
    return this.count(topic.name)
}

fun <K, V> KTable<K, V>.to(topic: Topic<K, V>) {
    this.to(topic.keySerde, topic.valueSerde, topic.name)
}
import kostr.client.*
import kostr.testConfigs
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.kstream.KStreamBuilder
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import wordcount.configs
import wordcount.topics
import wordcount.wordCount

class WordCountTest {
    companion object {
        @JvmStatic lateinit var streams: KafkaStreams

        @BeforeAll
        @JvmStatic
        fun setup() {
            val kStreamBuilder = KStreamBuilder()
            wordCount(kStreamBuilder)
            streams = KafkaStreams(kStreamBuilder, configs.kafka())
            streams.start()
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            streams.close()
        }
    }

    @Test
    fun wordCountTest() {
        val testLine =
                """
                all all streams lead to kafka
                hello kafka streams
                join kafka summit
                """

        KafkaSubscription(testConfigs.kafka(), topics.wordCounts).seekToEnd().use { wordCounts ->
            val messages = wordCounts.messages(timer(30000))

            KafkaProducer<String, String>(testConfigs.kafka(), topics.textLines).use { textLines ->
                for (line in testLine.trim().split("\n")) {
                    textLines.send(ProducerRecord(topics.textLines.name, line))
                }
                textLines.close()
            }

            assertTrue(messages.hasNext(), "No output messages found")
        }
    }
}
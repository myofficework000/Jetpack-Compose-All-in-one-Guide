package com.example.jetpack_compose_all_in_one.features.flows_demo

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@Composable
fun FlowDemoWithMultipleConsumer() {
    val producerJob = remember { mutableStateOf<Job?>(null) }
    val consumer1Items = remember { mutableStateListOf<String>() }
    val consumer2Items = remember { mutableStateListOf<String>() }
    val scope = rememberCoroutineScope()

    Column {
        Button(
            onClick = {
                producerJob.value = startFlow(scope, consumer1Items, consumer2Items)
            }
        ) {
            Text(text = "Start Flow")
        }

        DemoContentUI(consumer1Items, consumer2Items)
    }
}

@Composable
fun DemoContentUI(consumer1Items: List<String>, consumer2Items: List<String>) {
    Text(text = "Consumer 1:")
    consumer1Items.forEach { item ->
        Text(text = item)
    }

    Text(text = "Consumer 2:")
    consumer2Items.forEach { item ->
        Text(text = item)
    }
}


fun startFlow(
    coroutineScope: CoroutineScope,
    consumer1Items: MutableList<String>,
    consumer2Items: MutableList<String>
): Job {
    val sharedFlow = MutableSharedFlow<Int>()

    val producerJob = coroutineScope.launch {
        for (i in 1..10) {
            sharedFlow.emit(i)
            consumer1Items.add("Consumer 1 received item: $i")
            consumer2Items.add("Consumer 2 received item: $i")
            delay(1000)
        }
        sharedFlow.emit(-1) // Indicates the end of production
    }

    val consumerJob = coroutineScope.launch {
        sharedFlow
            .filter { it != -1 }
            .collect { item ->
                consumer1Items.add("Consumer 1 received item: $item")
                consumer2Items.add("Consumer 2 received item: $item")
                delay(2000)
            }
    }

    producerJob.invokeOnCompletion {
        consumerJob.cancel() // Stop consuming after producer finishes
    }

    return producerJob
}
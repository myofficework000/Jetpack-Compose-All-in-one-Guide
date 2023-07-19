package com.example.jetpack_compose_all_in_one.third_party_lib.rxjava

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton

@Composable
internal fun RxJavaDemoCompletable(vm: RxJavaDemoViewModel) {

        RxJavaDemoHeader(text = "Observable: Completable")

        SimpleTextButton(
            "Ping 10 times",
            enabled = vm.completableIsError != null
        ) { vm.completableEmit() }

        WiFiSwitch(isOn = vm.wiFiIsOn) {
            vm.completableToggleWiFi(it)
        }

        WiFiBox(vm.wiFiIsOn)

        vm.completableIsError?.let {
            Row {
                Text("Result: ")
                if (it) Text("Error") else Text("Completed")
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                Text("Completed")
                Text(vm.completableCompletes.toString())
            }
            Column {
                Text("Progress")
                Text("${vm.completableProgress ?: 0} / 10")
            }
            Column {
                Text("Error")
                Text(vm.completableErrors.toString())
            }
        }
}

@Composable
internal fun RxJavaDemoSingle(vm: RxJavaDemoViewModel) {
    RxJavaDemoHeader(text = "Observable: Single")

    SimpleTextButton(
        "Get a random number",
        enabled = vm.completableIsError != null
    ) {  }

    WiFiSwitch(isOn = vm.wiFiIsOn) {
        vm.completableToggleWiFi(it)
    }

    WiFiBox(vm.wiFiIsOn)
}

@Composable
internal fun RxJavaDemoMaybe(vm: RxJavaDemoViewModel) {

}

@Composable
internal fun RxJavaDemoObservable(vm: RxJavaDemoViewModel) {

}

@Composable
internal fun RxJavaDemoFlowable(vm: RxJavaDemoViewModel) {

}



@Composable
private fun WiFiSwitch(
    isOn: Boolean,
    onSwitch: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("WiFi: $isOn")

        Switch(
            checked = isOn,
            onCheckedChange = onSwitch
        )
    }
}

@Composable
private fun WiFiBox(isOn: Boolean) {
    Box(Modifier.padding(32.dp)) {
        Icon(
            painterResource(
                if (isOn) R.drawable.baseline_wifi_24
                else R.drawable.baseline_wifi_off_24
            ), null,
            Modifier.size(64.dp)
        )
    }
}
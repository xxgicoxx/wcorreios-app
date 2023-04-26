package dev.gico.wcorreios.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import dev.gico.wcorreios.R
import dev.gico.wcorreios.data.domain.MenuHeader
import dev.gico.wcorreios.data.domain.MenuItem
import dev.gico.wcorreios.data.entity.Code
import dev.gico.wcorreios.presentation.components.ChipList

@Composable
fun CodesList(
    menuHeader: MenuHeader,
    codes: MutableList<Code>,
    correstViewModel: CorrestViewModel,
    codeViewModel: CodeViewModel
) {
    val context = LocalContext.current
    val scalingLazyListState = rememberScalingLazyListState()

    Scaffold(
        Modifier.background(MaterialTheme.colors.background),
        positionIndicator = {
            PositionIndicator(scalingLazyListState = scalingLazyListState)
        }
    ) {
        val showDialog = remember { mutableStateOf(false) }
        val title = remember { mutableStateOf("") }
        val message = remember { mutableStateOf("") }
        val menuItems = mutableListOf<MenuItem>()

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ChipList(menuHeader, menuItems)

            if (showDialog.value) {
                AlertCode(
                    title = title.value,
                    message = message.value,
                    showDialog = showDialog.value,
                    onDismiss = {
                        showDialog.value = false
                    },
                    onDelete = {
                        codeViewModel.deleteById(title.value)

                        showDialog.value = false
                    }
                )
            }

            codes.forEach { entity ->
                menuItems.add(MenuItem("${entity.code}", onClick = {
                    title.value = entity.code
                    message.value = ""
                    showDialog.value = true

                    correstViewModel.correstData.removeObservers(context as LifecycleOwner)
                    correstViewModel.errorMessage.removeObservers(context as LifecycleOwner)

                    correstViewModel.getTrack(entity.code)

                    correstViewModel.correstData.observe(context as LifecycleOwner) {
                        if(it == null || it.data?.events!!.isEmpty() || it.httpCode != 200 || it.data?.code != entity.code) {
                            return@observe
                        }

                        message.value = "${context.getString(R.string.event)}: ${it.data?.events!![0].event}\n\n" +
                                "${context.getString(R.string.location)}: ${it.data?.events!![0].location}\n\n" +
                                "${context.getString(R.string.destination)}: ${it.data?.events!![0].destination}"
                    }

                    correstViewModel.errorMessage.observe(context as LifecycleOwner) { error ->
                        if(error.errorMessage != entity.code || error.httpCode == 200) {
                            return@observe
                        }

                        Toast.makeText(context, context.getString(R.string.object_not_found), Toast.LENGTH_LONG).show()
                    }
                }))
            }
        }
    }
}

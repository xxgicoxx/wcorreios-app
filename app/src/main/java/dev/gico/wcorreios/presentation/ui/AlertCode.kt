package dev.gico.wcorreios.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.dialog.Alert
import androidx.wear.compose.material.dialog.Dialog
import androidx.wear.compose.material.placeholder
import androidx.wear.compose.material.rememberPlaceholderState
import dev.gico.wcorreios.R

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun AlertCode(
    title: String,
    message: String,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onDelete: () -> Unit
) {
    if(!showDialog) {
        return
    }

    val scrollState = rememberScalingLazyListState()
    val chipPlaceholderState = rememberPlaceholderState {
        message.isNotEmpty() && message != null && title.isNotEmpty() && title != null
    }

    Scaffold(
        Modifier.background(MaterialTheme.colors.background)
    ) {
        Dialog(
            showDialog = true,
            onDismissRequest = onDismiss,
            scrollState = scrollState,
        ) {
            Alert(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                contentPadding = PaddingValues(
                    start = 10.dp,
                    end = 10.dp,
                    top = 24.dp,
                    bottom = 52.dp
                ),
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delivery),
                        contentDescription = "delivery",
                        modifier = Modifier.size(24.dp).wrapContentSize(align = Alignment.Center),
                    )
                },
                title = {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .placeholder(chipPlaceholderState)
                    )
                },
                negativeButton = {
                    Button(
                        colors = ButtonDefaults.secondaryButtonColors(),
                        onClick = onDelete
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = "delete",
                            modifier = Modifier.size(24.dp)
                                .wrapContentSize(align = Alignment.Center),
                        )
                    }
                },
                positiveButton = {
                    Button(
                        onClick = onDismiss
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = "confirm",
                            modifier = Modifier.size(24.dp)
                                .wrapContentSize(align = Alignment.Center),
                        )
                    }
                }
            )
            {
                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .placeholder(chipPlaceholderState)
                )
            }
        }

        if (!chipPlaceholderState.isShowContent) {
            LaunchedEffect(chipPlaceholderState) {
                chipPlaceholderState.startPlaceholderAnimation()
            }
        }
    }
}

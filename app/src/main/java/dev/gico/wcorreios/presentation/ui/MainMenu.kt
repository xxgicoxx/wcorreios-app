package dev.gico.wcorreios.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.gico.wcorreios.R
import dev.gico.wcorreios.presentation.components.ChipList
import dev.gico.wcorreios.data.domain.MenuHeader
import dev.gico.wcorreios.data.domain.MenuItem

@Composable
fun MainMenu(
    onClickNewCodeActivity: () ->  Unit,
    onClickCodesActivity: () -> Unit
) {
    val newCode = MenuItem(stringResource(R.string.add), onClick = onClickNewCodeActivity)
    val codes = MenuItem(stringResource(R.string.manage), onClick = onClickCodesActivity)

    newCode.secondaryLabel = stringResource(R.string.add_description)
    newCode.icon = R.drawable.ic_add

    codes.secondaryLabel = stringResource(R.string.manage_description)
    codes.icon = R.drawable.ic_list

    val menuHeader = MenuHeader(stringResource(R.string.app_name))
    val menuItems = listOf(newCode, codes).toMutableList()

    ChipList(menuHeader, menuItems)
}

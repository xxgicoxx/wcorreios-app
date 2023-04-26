package dev.gico.wcorreios.data.domain

data class MenuItem(var label: String, var onClick: () -> Unit) {
    var secondaryLabel: String? = null
    var icon: Int? = null
}

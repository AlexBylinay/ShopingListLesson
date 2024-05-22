package pl.alexbul.shopinglistlesson.settings_screen

sealed class SettingsEvent {
    data class OnItemSelected(val color: String): SettingsEvent()
}
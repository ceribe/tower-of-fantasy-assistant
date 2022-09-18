package com.kagamiapps.tofassistant.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ComboBox(
    label: String,
    testTag: String,
    value: String,
    suggestions: List<String>,
    onChangeCallback: (value: String) -> Unit,
    modifier: Modifier = Modifier,
    editable: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown

    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val clickableModifier = if(editable) Modifier else Modifier.clickable { expanded = true }

    Column {
        Box {
            TextField(
                value = value,
                onValueChange = { onChangeCallback(it) },
                modifier = modifier
                    .fillMaxWidth()
                    .testTag(testTag)
                    .onGloballyPositioned { coordinates ->
                        //This value is used to assign to the DropDown the same width
                        textFieldSize = coordinates.size.toSize()
                    },
                label = if (label != "") {{ Text(label) }} else null,
                singleLine = true,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                trailingIcon = {
                    if (editable)
                        Icon(
                            imageVector = icon,
                            null,
                            modifier = Modifier
                                .testTag("$testTag Arrow Icon")
                                .clickable {
                                    softwareKeyboardController?.hide()
                                    focusManager.clearFocus()
                                    expanded = !expanded
                                }
                        )
                }
            )
            Box(modifier = clickableModifier.matchParentSize())
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        onChangeCallback(label)
                        expanded = false
                    }
                ) {
                    Text(text = label)
                }
            }
        }
    }
}
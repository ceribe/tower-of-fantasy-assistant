package com.kagamiapps.tofassistant.ui.jo_loot_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kagamiapps.tofassistant.ui.composables.MainBottomNav
import com.kagamiapps.tofassistant.ui.composables.bottomRoutes
import com.kagamiapps.tofassistant.util.Routes
import com.kagamiapps.tofassistant.util.UiEvent

@Composable
fun JOLootListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: JOLootListViewModel = hiltViewModel()
) {
    val loots = viewModel.loots.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> {}
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { MainBottomNav(onNavigate = onNavigate, routes = bottomRoutes, selectedRoute = Routes.JO_LOOT_LIST) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(JOLootListEvent.OnCreateLootClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(loots.value.asReversed()) {loot ->
                JOLootItem(
                    loot = loot,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onEvent(JOLootListEvent.OnLootClick(loot))
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}
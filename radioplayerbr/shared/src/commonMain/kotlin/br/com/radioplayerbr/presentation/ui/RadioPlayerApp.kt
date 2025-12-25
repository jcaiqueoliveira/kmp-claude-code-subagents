package br.com.radioplayerbr.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import br.com.radioplayerbr.presentation.RadioPlayerViewModel
import br.com.radioplayerbr.presentation.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioPlayerApp(viewModel: RadioPlayerViewModel) {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Rádios Brasil") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        ) { paddingValues ->
            HomeScreen(
                viewModel = viewModel,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

package com.example.progettoparabellum.ui.screen.home.postCreation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.progettoparabellum.Routes
import com.example.progettoparabellum.ui.screen.home.BottomBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.progettoparabellum.ui.screen.home.HomeState
import com.example.progettoparabellum.ui.screen.home.HomeViewModel
import com.example.progettoparabellum.ui.screen.home.IdleScaffold
import com.example.progettoparabellum.ui.screen.home.Loading
import com.example.progettoparabellum.ui.screen.home.LoadingScaffold
import com.example.progettoparabellum.ui.screen.home.TopBar

@Composable
fun PostCreateScreen(
    navController: NavController,
    viewModel: PostCreationViewModel = hiltViewModel<PostCreationViewModel>()
) {
    val creationState by viewModel.creationState.collectAsState()
    when(creationState){
        CreationState.Idle -> IdleScaffold(navController, viewModel)
        CreationState.Loading -> LoadingScaffoldPost(navController)
        CreationState.Error -> IdleScaffold(navController, viewModel)
        CreationState.Success -> {
            navController.navigate(Routes.Home.route)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCreation(
    navController: NavController,
    page: String
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(page)
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(Routes.Home.route) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}


@Composable
fun IdleScaffold(navController: NavController, viewModel: PostCreationViewModel){
    Scaffold(
        topBar = { TopBarCreation(navController, "Create post") },
        bottomBar = { BottomBar(navController) }
    ) {
            innerPadding ->
        PostCreationBody(innerPadding, viewModel, navController)
    }
}

@Composable
fun LoadingScaffoldPost(navController: NavController){
    Scaffold(
        topBar = { TopBarCreation(navController, "Loading") },
        bottomBar = { BottomBar(navController)},
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        Loading(innerPadding)
    }
}

@Composable
fun PostCreationBody(innerPadding: PaddingValues,
                     viewModel: PostCreationViewModel,
                     navController: NavController){

    var content by remember { mutableStateOf("") }
    val contentState by viewModel.contentState.collectAsState()
    val creationState by viewModel.creationState.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(innerPadding),
    verticalArrangement = Arrangement.spacedBy(space = 10.dp, alignment =  Alignment.CenterVertically),
    horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(color = MaterialTheme.colorScheme.onBackground,
            text = "Create a post",
            style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = content,
            onValueChange = {
                content = it
                viewModel.onContentChange(it)
            },
            label = { Text("Insert here...")},
            singleLine = false,
            isError = contentState == ContentState.ERROR,
            modifier = Modifier.height(350.dp)
        )
        Button(onClick = {viewModel.verifyContent(content)
        }) {
            Text("Post")
        }
    }
}


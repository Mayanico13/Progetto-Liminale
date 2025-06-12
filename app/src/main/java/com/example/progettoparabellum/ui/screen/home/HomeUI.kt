package com.example.progettoparabellum.ui.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.progettoparabellum.data.model.Post
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.progettoparabellum.Routes
import com.example.progettoparabellum.ui.screen.auth.login.LoadingScreen
import com.example.progettoparabellum.ui.screen.auth.login.LoginViewModel
import com.example.progettoparabellum.ui.screen.auth.register.InitialScreen
import com.example.progettoparabellum.ui.screen.auth.register.RegisterUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
) {

    val postList by homeViewModel.postList.collectAsState()
    val uiState by homeViewModel.homeState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.loadPost()
        homeViewModel.updateUser()
    }
    when(uiState){
        HomeState.Idle -> IdleScaffold(navController, homeViewModel, postList)
        HomeState.Loading -> LoadingScaffold(navController, homeViewModel)
    }



}

@Composable
fun IdleScaffold(navController: NavController, homeViewModel: HomeViewModel, postList: List<Post>){
    Scaffold(
        topBar = { TopBar(navController, homeViewModel) },
        bottomBar = { BottomBar(navController)},
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        PostColumn(innerPadding, postList)
    }
}

@Composable
fun Loading(innerPadding: PaddingValues){
    Box (
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(innerPadding),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp)
        )
    }
}



@Composable
fun BottomBar(navController: NavController) {

    NavigationBar(

        // set background color
        containerColor = MaterialTheme.colorScheme.primaryContainer) {

        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route
        Log.d("TAG", currentRoute.toString())

        // Bottom nav items we declared
        BottomBarIcons.BottomNavItems.forEach { navItem ->

            // Place the bottom nav items
            NavigationBarItem(

                selected = currentRoute == navItem.route,

                // navigate on click
                onClick = {
                    navController.navigate(navItem.route)
                },

                // Icon of navItem
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },

                // label
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = true,

                colors = NavigationBarItemDefaults.colors()
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController,
           viewModel: HomeViewModel) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text("Home page")
        },
        actions = {
            IconButton(onClick = { viewModel.loadPost() }) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@Composable
fun LoadingScaffold(navController: NavController, homeViewModel: HomeViewModel){
    Scaffold(
        topBar = { TopBar(navController, homeViewModel) },
        bottomBar = { BottomBar(navController)},
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        Loading(innerPadding)
    }
}

@Composable
fun PostColumn(innerPadding: PaddingValues, postList: List<Post>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = innerPadding
    ) {
        items(postList) { post ->
            PostItem(post)
        }
    }
}

@Composable
fun PostItem(post: Post) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Gestisci click sul post, se serve */ }
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = post.username.firstOrNull()?.uppercase() ?: "",
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.align(Alignment.Center),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = post.username,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = post.content,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }



    }
    HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)
}




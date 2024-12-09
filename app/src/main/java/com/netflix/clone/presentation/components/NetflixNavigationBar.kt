package com.netflix.clone.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.netflix.clone.R
import com.netflix.clone.navigation.Screen
import com.netflix.clone.navigation.topLevelRoutes
import com.netflix.clone.ui.theme.ExtendedTheme
import com.netflix.clone.ui.theme.NetflixCloneTheme

@SuppressLint("RestrictedApi")
@Composable
fun NetflixNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = ExtendedTheme.colors.neutralGrayDark2,
        contentColor = ExtendedTheme.colors.neutralWhite,
        tonalElevation = 0.dp,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        topLevelRoutes.forEach { topLevelRoute ->
            val selected =
                currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true

            NetflixNavigationBarItemIcon(
                selected = selected,
                onClick = {
                    navController.navigate(topLevelRoute.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = if (selected) topLevelRoute.selectedIcon else topLevelRoute.unselectedIcon,
                label = stringResource(id = topLevelRoute.labelId),
            )
        }

        NetflixNavigationBarItemImage(
            selected = currentDestination?.hierarchy?.any { it.hasRoute(Screen.MyNetflix::class) } == true,
            onClick = {
                navController.navigate(Screen.MyNetflix) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = R.drawable.ic_profile_placeholder,
            label = stringResource(id = R.string.label_my_netflix),
        )
    }
}

@Composable
private fun RowScope.NetflixNavigationBarItemIcon(
    selected: Boolean,
    onClick: () -> Unit,
    icon: Int,
    label: String,
    modifier: Modifier = Modifier,
) {
    val contentColor =
        if (selected) ExtendedTheme.colors.neutralWhite else ExtendedTheme.colors.neutralGrayLight2

    Column(
        modifier =
            modifier
                .clickable(onClick = onClick)
                .height(60.dp)
                .weight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = contentColor,
        )
        Text(
            text = label,
            modifier = Modifier.padding(top = 4.dp),
            color = contentColor,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Composable
private fun RowScope.NetflixNavigationBarItemImage(
    selected: Boolean,
    onClick: () -> Unit,
    icon: Int,
    label: String,
    modifier: Modifier = Modifier,
) {
    val contentColor =
        if (selected) ExtendedTheme.colors.neutralWhite else ExtendedTheme.colors.neutralGrayLight2

    Column(
        modifier =
            modifier
                .clickable(onClick = onClick)
                .height(60.dp)
                .weight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = label,
            modifier =
                Modifier.size(24.dp).then(
                    if (selected) {
                        Modifier.border(
                            1.dp,
                            contentColor,
                            MaterialTheme.shapes.extraSmall,
                        )
                    } else {
                        Modifier
                    },
                ),
        )
        Text(
            text = label,
            modifier = Modifier.padding(top = 4.dp),
            color = contentColor,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Preview
@Composable
private fun NetflixNavigationBarPreview() {
    NetflixCloneTheme {
        NetflixNavigationBar(navController = rememberNavController())
    }
}

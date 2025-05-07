package com.rose.mobiletrack.ui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.R
import com.rose.mobiletrack.navigation.ROUT_LOGIN
import com.rose.mobiletrack.ui.theme.pink
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    var visible by remember { mutableStateOf(false) }

    // Launch animation & navigation
    LaunchedEffect(true) {
        visible = true
        delay(3000)
        navController.navigate(ROUT_LOGIN)
    }

    val scaleAnim = animateFloatAsState(
        targetValue = if (visible) 1f else 0.8f,
        animationSpec = tween(durationMillis = 800, easing = EaseOutBack),
        label = "scale"
    )

    val alphaAnim = animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(1500),
        label = "alpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.img_2),
                contentScale = ContentScale.Crop
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_1),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(300.dp)
                    .scale(scaleAnim.value)
                    .alpha(alphaAnim.value),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(32.dp))

            AnimatedVisibility(visible = visible) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Mobile Track",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = pink,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Track your movements with precision and speed.",
                        fontSize = 16.sp,
                        color = pink.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}

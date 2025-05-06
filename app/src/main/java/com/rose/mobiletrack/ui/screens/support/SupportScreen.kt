package com.rosemuhando.harakamall.ui.screens.support

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.R
import com.rose.mobiletrack.ui.theme.pink


@Composable
fun SupportScreen(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img_2), contentScale = ContentScale.FillBounds),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Need Help?",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = pink
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Email Support Button
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:support@example.com")
                    putExtra(Intent.EXTRA_SUBJECT, "Support Request")
                }
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00C6FF),
                contentColor = Color.White
            )
        ) {
            Icon(Icons.Default.Email, contentDescription = "Email")
            Spacer(Modifier.width(10.dp))
            Text("Contact via Email")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Phone Support Button
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:+254700000000")
                }
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0072FF),
                contentColor = Color.White
            )
        ) {
            Icon(Icons.Default.Phone, contentDescription = "Phone")
            Spacer(Modifier.width(10.dp))
            Text("Call Support")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SupportScreenPreview() {
    SupportScreen(rememberNavController())

}
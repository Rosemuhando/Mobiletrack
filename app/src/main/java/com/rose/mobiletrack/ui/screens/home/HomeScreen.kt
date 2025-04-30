package com.rose.mobiletrack.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.R
import com.rose.mobiletrack.ui.theme.pink


@Composable

fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img_2), contentScale = ContentScale.FillBounds)
    )
    {
        Text(
            text = "Mobile Track App",
            fontSize = 50.sp,
            color = pink,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(R.drawable.img_3),
            contentDescription = "home",
            modifier = Modifier.size(400.dp),
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Welcome to my app Mobile Track ",
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic


        )
        Text(
            text = "Mobile Track is an application provides mobility to any customer .",
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic


        )
        Text(
            text = "it also keeps track off the distance covered by the taxi driver hence, showing the given amount off money the customer ows the taxi driver according to the company's specifications for intance if the car is to cover 1km the customer is to pay ksh 500  ",
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )


                    Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(pink),
            shape = RoundedCornerShape(15.dp),
        ) {
            Text(text = "Book a Car")
        }
    }

}





@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())

}

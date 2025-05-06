package com.rose.mobiletrack.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.rose.mobiletrack.navigation.ROUT_ABOUT
import com.rose.mobiletrack.navigation.ROUT_DASHBOARD
import com.rose.mobiletrack.ui.theme.pink

@Composable

fun HomeScreen(navController: NavController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img_2), contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,


        ){
        Text(
            text = "Mobile Track App",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = pink

        )
        Spacer(modifier = Modifier.height(20.dp))


// circular image
        Image(
            painter = painterResource(R.drawable.img_3),
            contentDescription = "home",
            modifier = Modifier
                .size(300.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )
//end of circular image

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Make your booking!!!",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = pink
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Welcome to my app Mobile Track ",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,


            )
        Text(
            text = "Mobile Track is an application provides mobility to any customer .",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,


            )
        Text(
            text = "it also keeps track off the distance covered by the taxi driver hence, showing the given amount off money the customer ows the taxi driver according to the company's specifications for intance if the car is to cover 1km the customer is to pay ksh 500  ",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,

            )




        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate(ROUT_ABOUT)
            },
            colors = ButtonDefaults.buttonColors(pink),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp,end =20.dp),

            ) {
            Text(text = "get started")
        }

    }





}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}


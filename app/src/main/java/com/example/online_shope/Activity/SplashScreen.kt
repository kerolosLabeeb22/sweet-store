package com.example.online_shope.Activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.online_shope.R
import com.example.online_shope.destinaions.HomeDestination
import com.example.online_shope.destinaions.RegisterDestination


@Composable
fun SplashScreenContent(navHostController: NavHostController, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.brown))
            .verticalScroll(rememberScrollState())
            .padding(top = 70.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Satisfy your Cravings with Our \nFresh Cakes,Donates\nand pastries",
            fontSize = 24.sp,
            color = colorResource(R.color.black),
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp,
            modifier = Modifier
                .padding(top = 16.dp)
        )

        Text(
            text = "Brows the best edibles from top seller\nGet personalized recommendations\nEnjoy fast ,free shipping",
            fontSize = 16.sp,
            color = colorResource(R.color.black),
            textAlign = TextAlign.Center,
            lineHeight = 30.sp,
            modifier = Modifier
                .padding(top = 16.dp)
        )

        Button(
            onClick = {
                navHostController.navigate(HomeDestination)
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(50.dp), colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.green),
                contentColor = colorResource(R.color.white)
            )
        ) {
            Text(
                text = "Let's Get Started", textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account?",
                fontSize = 16.sp,
                color = colorResource(R.color.black),
            )

            Text(
                text = " Sign in",
                fontSize = 16.sp, color = colorResource(R.color.black),
                modifier = Modifier.clickable{
                    navHostController.navigate(RegisterDestination)
                }
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreenContent(rememberNavController())
}
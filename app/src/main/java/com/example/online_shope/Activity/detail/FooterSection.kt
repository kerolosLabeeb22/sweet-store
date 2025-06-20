package com.example.online_shope.Activity.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.online_shope.R


@Composable
fun FooterSection(
    onAddToCartClick: () -> Unit, onCartClick: () -> Unit, modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = onAddToCartClick,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.green)
            ),
            modifier = modifier
                .weight(1f)
                .padding(end = 8.dp)
                .height(50.dp)
        ) {
            Text(
                "Add to Cart",
                fontSize = 18.sp,
                color = colorResource(R.color.white)
            )
        }

        IconButton(
            onClick = onCartClick, modifier = modifier.background(
                colorResource(R.color.lightGrey), shape = RoundedCornerShape(10.dp)
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.btn_2),
                contentDescription = "Cart",
                tint = colorResource(R.color.black)
            )
        }
    }
}
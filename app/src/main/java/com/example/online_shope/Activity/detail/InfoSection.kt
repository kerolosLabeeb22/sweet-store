package com.example.online_shope.Activity.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.online_shope.Item.ItemsModel
import com.example.online_shope.R

@Composable
fun InfoSection(item: ItemsModel) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = item.title,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "$${item.price}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )


        }
        Text(
            text = "Seller",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = item.sellerPic,
                contentDescription = "Seller Image",
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = item.sellerName,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.call),
                contentDescription = "Call Icon",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.message),
                contentDescription = "Call Icon",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

    }
}
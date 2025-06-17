package com.example.online_shope.domain

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.online_shope.Item.ItemModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.online_shope.domain.SliderModel
import com.example.online_shope.viewmodel.MainViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.online_shope.R
import com.example.online_shope.category.CategoryList
import com.example.online_shope.category.CategoryModel
import com.example.online_shope.utils.Banners
import java.nio.file.WatchEvent


@Composable
fun ListItems(item: List<ItemModel>) {
    LazyRow(
        modifier = Modifier.padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(item.size){index:Int ->
            BestSellerItem(item,index)
        }
    }

}

@Composable
fun BestSellerItem(items: List<ItemModel>, pos: Int) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(4.dp)
            .wrapContentHeight()
    ) {
        AsyncImage(
            model = items[pos].picUrl.firstOrNull(),
            contentDescription = null,
            modifier = Modifier
                .width(180.dp)
                .background(colorResource(R.color.lightGrey), shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .height(180.dp)
                .clickable {

                }, contentScale = ContentScale.Crop


        )

        Text(
            text = items[pos].title,
            color = colorResource(R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.padding(top = 8.dp)
        )

        Row(
            modifier = Modifier
                .width(175.dp)
                .padding(top = 4.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(R.drawable.star),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = items[pos].rating.toString(),
                    color = colorResource(R.color.black),
                    fontSize = 15.sp,
                )
            }

        }

        Text(
            text = "$${items[pos].price}",
            color = colorResource(R.color.darkBrown), textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp

        )

    }
}
package com.example.online_shope.Activity

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.online_shope.Item.ItemModel
import com.example.online_shope.R
import com.example.online_shope.category.CategoryList
import com.example.online_shope.category.CategoryModel
import com.example.online_shope.domain.ListItems
import com.example.online_shope.utils.Banners

@Composable
@Preview
fun HomeScreenContent(navController: NavHostController = rememberNavController()) {


    val viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val banners = remember { mutableStateListOf<SliderModel>() }
    val categories = remember { mutableStateListOf<CategoryModel>() }
    val bestSeller = remember { mutableStateListOf<ItemModel>() }

    var showBannerLoading by remember { mutableStateOf(true) }
    var showCategoryLoading by remember { mutableStateOf(true) }
    var showBestSellerLoading by remember { mutableStateOf(true) }


    // Load banners
    LaunchedEffect(Unit) {
        viewModel.loadBanner().observeForever {
            Log.e("TAG", "Banners received: $it")
            banners.clear()
            banners.addAll(it)
            showBannerLoading = false
        }
    }

    // Load categories
    LaunchedEffect(Unit) {
        viewModel.loadCategory().observeForever {
            Log.e("TAG", "Banners received: $it")
            categories.clear()
            categories.addAll(it)
            showCategoryLoading = false
        }
    }

    // load best seller
    LaunchedEffect(Unit) {
        viewModel.loadBestSeller().observeForever {
            bestSeller.clear()
            bestSeller.addAll(it)
            showBestSellerLoading = false
        }
    }

    ConstraintLayout(modifier = Modifier.background(colorResource(R.color.white))) {

        val (scrollList, bottomMenu) = createRefs()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(scrollList) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)

                }

        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 70.dp)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {
                        Text("Welcome Back", color = colorResource(R.color.black))
                        Text(
                            "Jackie",
                            color = colorResource(R.color.black),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row {
                        Image(
                            painter = painterResource(R.drawable.search_icon),
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Image(
                            painter = painterResource(R.drawable.bell_icon),
                            contentDescription = null
                        )
                    }
                }

            }
            // banners
            item {
                if (showBannerLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    Banners(banners)
                }
            }

            // categories
            item {
                Text(
                    text = "Categories",
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.black)
                )
            }
            item {
                if (showCategoryLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    CategoryList(categories)
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Best Seller Product",
                        color = colorResource(R.color.black),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "See All",
                        color = colorResource(R.color.midBrown),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                if (showBestSellerLoading){
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                        contentAlignment = Alignment.Center){
                        CircularProgressIndicator()
                    }
                }else{
                    ListItems(bestSeller)
                }
            }
        }
    }


}



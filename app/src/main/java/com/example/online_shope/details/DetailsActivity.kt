package com.example.online_shope.details

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.example.online_shope.Helper.ManagmentCart
import com.example.online_shope.Item.ItemsModel
import com.example.online_shope.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp

class DetailsActivity : AppCompatActivity() {
    private lateinit var item: ItemsModel
    private lateinit var managementCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        item = intent.getSerializableExtra("object") as ItemsModel
        managementCart = ManagmentCart(this)

        setContent {
            DetailsScreenContent(
                item = item,
                onBackClick = { finish() },
                onAddCartClick = {
                    item.numberInCart = 1
                    managementCart.insertItems(item)
                },
                onCartClick = {

                }
            )
        }
    }


    @Composable
    fun DetailsScreenContent(
        //navController: NavHostController,
        item: ItemsModel,
        onBackClick: () -> Unit,
        onAddCartClick: () -> Unit,
        onCartClick: () -> Unit,
    ) {

        var selectedImageUrl by remember { mutableStateOf(item.picUrl.first()) }
        var selectedModelIndex by remember { mutableStateOf(-1) }

        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .background(
                    color = colorResource(
                        R.color.white
                    )
                )
                .verticalScroll(rememberScrollState())
        ) {
            HeaderSection(selectedImageUrl, item.picUrl, onBackClick) {
                selectedImageUrl = it
            }

            InfoSection(item)
            RatingBarRow(rating = item.rating)

            ModelSelector(
                models = item.size,
                selectedModelIndex = selectedModelIndex,
                onModelSelected = {
                    selectedModelIndex = it
                }
            )

            Text(
                text = item.description,
                fontSize = 14.sp,
                color = colorResource(R.color.black),
                modifier = Modifier.padding(16.dp)
            )

            FooterSection(onAddCartClick, onCartClick)

        }
    }


    @Composable
    fun HeaderSection(
        selectedImageUrl: String,
        imageUrls: List<String>,
        onBackClick: () -> Unit,
        onImageSelected: (String) -> Unit
    ) {

        ConstraintLayout(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .height(430.dp)
                .padding(bottom = 16.dp)
        ) {
            val (back, fav, mainImage, thumbnail) = createRefs()
            Image(
                painter = rememberAsyncImagePainter(model = selectedImageUrl),
                contentDescription = "Selected Image",
                contentScale = ContentScale.Companion.Crop,
                modifier = Modifier.Companion
                    .fillMaxSize()
                    .background(
                        colorResource(R.color.lightGrey),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .constrainAs(mainImage) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            BackButton(onBackClick, Modifier.Companion.constrainAs(back) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            })

            FavoriteButton(Modifier.Companion.constrainAs(fav) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            })

            LazyRow(
                modifier = Modifier.Companion
                    .padding(vertical = 16.dp)
                    .background(
                        color = colorResource(R.color.white),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                    )
                    .constrainAs(thumbnail) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }) {

                items(imageUrls) { imageUrls ->

                    ImageThumbnail(
                        imageUrl = imageUrls,
                        isSelected = selectedImageUrl == imageUrls,
                        onClick = { onImageSelected(imageUrls) }
                    )
                }
            }

        }

    }

    @Composable
    fun BackButton(onClick: () -> Unit, modifier: Modifier = Modifier.Companion) {
        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = "Back",
            modifier = modifier
                .padding(start = 16.dp, top = 48.dp)
                .clickable { onClick() }
        )

    }

    @Composable
    fun FavoriteButton(modifier: Modifier = Modifier.Companion) {
        Image(
            painter = painterResource(R.drawable.fav_icon),
            contentDescription = "Favorite",
            modifier = modifier
                .padding(end = 16.dp, top = 48.dp)
        )
    }


}